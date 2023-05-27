package com.pragma.biblioteca.service;

import com.pragma.biblioteca.entity.Autor;
import com.pragma.biblioteca.error.ValidacionException;
import com.pragma.biblioteca.repository.AutorRepository;
import com.pragma.biblioteca.repository.PaisRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class AutorService {
    private AutorRepository autorRepository;
    private PaisRepository paisRepository;
    
    public boolean validarAutor(Autor autor) throws ValidacionException {
        if(autor.getId()!=null){
            throw  new ValidacionException("No se permite enviar un id al crear un objeto");
        } else if (((autor.getNombre() == null || autor.getNombre().isEmpty())
                || (autor.getApellido() == null || autor.getApellido().isEmpty()))
                && (autor.getPseudonimo()==null|| autor.getPseudonimo().isEmpty())) {
            throw new ValidacionException("El autor como minimo debe tener un nombre y apellido o un pseudonimo");
        } else if (autorRepository.existsByPseudonimo(autor.getPseudonimo())) {
            throw new ValidacionException("El pseudonimo no puede estar repetido");
        } else if (autorRepository.existsByEmail(autor.getEmail())) {
            throw new ValidacionException("El email no puede estar repetido");
        } else if (autor.getPais()==null || autor.getPais().getId()==null) {
            throw new ValidacionException("El autor debe tener asignado un pais");
        } else if (!paisRepository.existsById(autor.getPais().getId())) {
            throw new ValidacionException("El pais que asignaste no existe");
        } else if (autor.getEmail()== null || autor.getEmail().isEmpty()) {
            throw new ValidacionException("El correo no puede estar vacio");
        } else if (!validarEmail(autor.getEmail())) {
            throw new ValidacionException("El correo no esta en el formato adecuado");
        }
        return true;
    }

    public boolean validarEmail(String email){
        String emailRegex = "^[a-zA-Z0-9.!#$%&'*+/=?^_{|}~-]+@[a-zA-Z0-9-]+(?:.[a-zA-Z0-9-]+)*$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public ResponseEntity<Autor> crearAutor(Autor autor){
        if (!validarAutor(autor)){
            return ResponseEntity.badRequest().build();
        }
        Autor result= autorRepository.save(autor);
        return ResponseEntity.ok(result);
    }
}
