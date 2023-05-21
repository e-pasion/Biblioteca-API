package com.pragma.biblioteca.service;

import com.pragma.biblioteca.entity.Editorial;
import com.pragma.biblioteca.repository.EditorialRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EditorialService {
    EditorialRepository editorialRepository;

    public ResponseEntity<List<Editorial>>verEditoriales(){//devolver todas las editoriales
        List<Editorial> editorialList = editorialRepository.findAll();
        return ResponseEntity.ok(editorialList);
    }

    public ResponseEntity<Editorial> verEditorial(Long id){//devolver una editorial
        Optional<Editorial> resultado=editorialRepository.findById(id);
        return ResponseEntity.ok(resultado.get());
    }

    public ResponseEntity<?> crearEditorial(Editorial editorial){//crear una editorial
        if(editorial.getId()!=null){
            return ResponseEntity.badRequest().body("No se permite enviar un id al crear un objeto");
        } else if((editorial.getNombre().length()<2||editorial.getNombre().length()>30)){
            return ResponseEntity.badRequest().body("El nombre debe estar entre 2 y 30 caracteres");
        } else if (editorial.getDescripcion().length()>300) {
            return ResponseEntity.badRequest().body("La descripcion no debe ser mayor a 300 caracteres");
        } else if (editorialRepository.existsByNombre(editorial.getNombre())) {
            return ResponseEntity.badRequest().body("El nombre que estas pasando ya existe");
        }
        Editorial resultado=editorialRepository.save(editorial);
        return ResponseEntity.ok(resultado);
    }

    public ResponseEntity<?> actualizarEditorial(Editorial editorial){//actualizar una editorial
        if(editorial.getId()==null){
            return ResponseEntity.badRequest().body("Para actualizar tienes que pasar un ID");
        } else if (!editorialRepository.existsById(editorial.getId())) {
            return ResponseEntity.notFound().build();
        }
        return crearEditorial(editorial);
    }

    public ResponseEntity<Editorial> borrarEditorial(Long id){//borrar una editorial
        if(editorialRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        editorialRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
