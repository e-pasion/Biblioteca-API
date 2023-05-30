package com.pragma.biblioteca.service;

import com.pragma.biblioteca.entity.Ubicacion;
import com.pragma.biblioteca.error.ValidacionException;
import com.pragma.biblioteca.repository.UbicacionRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
@AllArgsConstructor
public class UbicacionService {
    private UbicacionRepository ubicacionRepository;

    public ResponseEntity<Ubicacion> crearUbicacion(Ubicacion ubicacion, BindingResult bindingResult){
        if(ubicacion.getId()!=null){
            throw new ValidacionException("No se permite enviar un id al crear un objeto");
        }else if(bindingResult.hasErrors()){
            throw new ValidacionException(bindingResult.getFieldError().getDefaultMessage());
        }
        validarUbicacion(ubicacion);
        Ubicacion result=ubicacionRepository.save(ubicacion);
        return ResponseEntity.ok(result);
    }
//selectiva repetitiva secuenciales
    private void validarUbicacion(Ubicacion ubicacion){
        if(ubicacionRepository.existsByPiso(ubicacion.getPiso())
                && ubicacionRepository.existsBySalon(ubicacion.getSalon())
                && ubicacionRepository.existsByEstante(ubicacion.getEstante())){
            throw new ValidacionException("Esta ubicacion exacta ya existe");
        }
    }


}
