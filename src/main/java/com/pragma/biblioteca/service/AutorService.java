package com.pragma.biblioteca.service;

import com.pragma.biblioteca.entity.Autor;
import com.pragma.biblioteca.error.ValidacionException;
import com.pragma.biblioteca.repository.AutorRepository;
import com.pragma.biblioteca.repository.PaisRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class AutorService {
    private AutorRepository autorRepository;
    private PaisRepository paisRepository;

    public ResponseEntity<Autor> crearAutor(Autor autor, BindingResult bindingResult){
        if(autor.getId()!=null) {
            throw new ValidacionException("No se permite enviar un id al crear un objeto");
        }
        else if(bindingResult.hasErrors()){
            throw new ValidacionException(bindingResult.getFieldError().getDefaultMessage());
        }
        validarAutor(autor);

        Autor result= autorRepository.save(autor);
        return ResponseEntity.ok(result);
    }


    public void validarAutor(Autor autor) throws ValidacionException {

        if (((autor.getNombre() == null || autor.getNombre().isBlank())
                || (autor.getApellido() == null || autor.getApellido().isBlank()))
                && (autor.getPseudonimo()==null|| autor.getPseudonimo().isBlank())) {
            throw new ValidacionException("El autor como minimo debe tener un nombre y apellido o un pseudonimo");
        }
        else if (autorRepository.existsByPseudonimo(autor.getPseudonimo())) {
            throw new ValidacionException("El pseudonimo no puede estar repetido");
        }
        else if (autor.getPais().getId()==null) {
            throw new ValidacionException("Debes poner el ID del pais");
        }
        else if (!paisRepository.existsById(autor.getPais().getId())) {
            throw new ValidacionException("El pais que asignaste no existe");
        }
    }



}
