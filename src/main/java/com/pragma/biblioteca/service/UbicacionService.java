package com.pragma.biblioteca.service;

import com.pragma.biblioteca.dto.CategoriaDTO;
import com.pragma.biblioteca.dto.EditorialDTO;
import com.pragma.biblioteca.dto.UbicacionDTO;
import com.pragma.biblioteca.entity.Categoria;
import com.pragma.biblioteca.entity.Ubicacion;
import com.pragma.biblioteca.error.ValidacionException;
import com.pragma.biblioteca.mapper.UbicacionMapper;
import com.pragma.biblioteca.repository.UbicacionRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UbicacionService {
    private UbicacionRepository ubicacionRepository;
    private UbicacionMapper mapper;


    public ResponseEntity<List<UbicacionDTO>>verUbicacones(){
        List<Ubicacion> ubicacionList = ubicacionRepository.findAll();
        List<UbicacionDTO> ubicacionDTOList= mapper.toUbicacionesDto(ubicacionList);
        return ResponseEntity.ok(ubicacionDTOList);
    }

    public ResponseEntity<UbicacionDTO> verUbicacion(Long id){
        Optional<Ubicacion> resultado=ubicacionRepository.findById(id);
        Ubicacion ubicacion= resultado.orElseThrow(() -> new ValidacionException("No existe ninguna ubicacion con este id"));
        UbicacionDTO ubicacionDTO=mapper.toUbicacionDto(ubicacion);
        return ResponseEntity.ok(ubicacionDTO);
    }

    public ResponseEntity<UbicacionDTO> crearUbicacion(UbicacionDTO ubicacionDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ValidacionException(bindingResult.getFieldError().getDefaultMessage());
        }
        validarUbicacion(ubicacionDTO);
        Ubicacion ubicacion=mapper.toUbicacion(ubicacionDTO);
        Ubicacion result=ubicacionRepository.save(ubicacion);
        return ResponseEntity.ok(mapper.toUbicacionDto(result));
    }
    public ResponseEntity<UbicacionDTO> actualizarUbicacion(Long id,UbicacionDTO ubicacionDTO, BindingResult bindingResult){
        if (!ubicacionRepository.existsById(id)) {
            throw new ValidacionException("Esta ubicacion no existe");
        }
        else if(bindingResult.hasErrors()){
            throw new ValidacionException(bindingResult.getFieldError().getDefaultMessage());
        }
        validarUbicacion(ubicacionDTO);
        Ubicacion ubicacion= mapper.toUbicacionConID(ubicacionDTO,id);
        Ubicacion resultado=ubicacionRepository.save(ubicacion);
        return ResponseEntity.ok(mapper.toUbicacionDto(resultado));
    }

    public ResponseEntity<UbicacionDTO> borrarUbicacon(Long id){
        if(!ubicacionRepository.existsById(id)) {
            throw new ValidacionException("Esta ubicacion no existe");
        }
        ubicacionRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    private void validarUbicacion(UbicacionDTO ubicacionDTO){
        if(ubicacionRepository.existsByPiso(ubicacionDTO.getPiso())
                && ubicacionRepository.existsBySalon(ubicacionDTO.getSalon())
                && ubicacionRepository.existsByEstante(ubicacionDTO.getEstante())){
            throw new ValidacionException("Esta ubicacion exacta ya existe");
        }
    }


}
