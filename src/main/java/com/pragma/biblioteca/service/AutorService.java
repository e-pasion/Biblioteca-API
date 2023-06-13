package com.pragma.biblioteca.service;

import com.pragma.biblioteca.Util.ValidacionesUtil;
import com.pragma.biblioteca.dto.AutorDTO;
import com.pragma.biblioteca.dto.EditorialDTO;
import com.pragma.biblioteca.entity.Autor;
import com.pragma.biblioteca.entity.Editorial;
import com.pragma.biblioteca.entity.Pais;
import com.pragma.biblioteca.error.ValidacionException;
import com.pragma.biblioteca.mapper.AutorMapper;
import com.pragma.biblioteca.repository.AutorRepository;
import com.pragma.biblioteca.repository.PaisRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AutorService {
    private AutorRepository autorRepository;
    private PaisRepository paisRepository;
    private AutorMapper mapper;

    public ResponseEntity<List<AutorDTO>> verAutores(){
        List<Autor> autores= autorRepository.findAll();
        List<AutorDTO> autoresDTO =  mapper.toAutoresDTO(autores);
        return ResponseEntity.ok(autoresDTO);
    }

    public ResponseEntity<AutorDTO> verAutor(Long id){//devolver una editorial
        Optional<Autor> resultado=autorRepository.findById(id);
        Autor autor= resultado.orElseThrow(() -> new ValidacionException("No existe ningun Autor con este id"));
        AutorDTO autorDTO=mapper.toAutorDTO(autor);
        return ResponseEntity.ok(autorDTO);
    }

    public ResponseEntity<AutorDTO> crearAutor(AutorDTO autorDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ValidacionException(bindingResult.getFieldError().getDefaultMessage());
        }
        validarAutor(autorDTO);
        Autor autor=mapper.toAutor(autorDTO);
        Pais pais=paisRepository.findByNombre(autorDTO.getNombrePais()).get();
        autor.setPais(pais);
        Autor result= autorRepository.save(autor);
        return ResponseEntity.ok(mapper.toAutorDTO(result));
    }

    public ResponseEntity<AutorDTO> actualizarAutor(Long id,AutorDTO autorDTO, BindingResult bindingResult){
        if(!autorRepository.existsById(id)){
            throw new ValidacionException("El autor que intentas actualizar no existe");
        } else if(bindingResult.hasErrors()){
            throw new ValidacionException(bindingResult.getFieldError().getDefaultMessage());
        }validarAutor(autorDTO);
        Autor autor=mapper.toAutorConID(autorDTO,id);
        Pais pais=paisRepository.findByNombre(autorDTO.getNombrePais()).get();
        autor.setPais(pais);
        Autor result= autorRepository.save(autor);
        return ResponseEntity.ok(mapper.toAutorDTO(result));

    }

    public ResponseEntity<AutorDTO> borrarAutor(Long id){//borrar una editorial
        if(!autorRepository.existsById(id)) {
            throw new ValidacionException("No existe ningun Autor con este id");
        }
        autorRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    public void validarAutor(AutorDTO autorDTO) throws ValidacionException {
        validarNombreOPseudonimo(autorDTO);
        if (autorRepository.existsByPseudonimo(autorDTO.getPseudonimo()) && !autorDTO.getPseudonimo().isBlank()) {
            throw new ValidacionException("El pseudonimo no puede estar repetido");
        }
        else if (autorRepository.existsByEmail(autorDTO.getEmail())) {
            throw new ValidacionException("El Email no puede estar repetido");
        }
        else if (!paisRepository.existsByNombre(autorDTO.getNombrePais())) {
            throw new ValidacionException("El pais que asignaste no existe");
        }
    }

    public void validarNombreOPseudonimo(AutorDTO autorDTO){
        if(ValidacionesUtil.esNuloOVacio(autorDTO.getNombre()) || ValidacionesUtil.esNuloOVacio(autorDTO.getApellido())){
            if(ValidacionesUtil.esNuloOVacio(autorDTO.getPseudonimo())){
                throw new ValidacionException("El autor como minimo debe tener un nombre y apellido o un pseudonimo");
            }
        }
    }





}
