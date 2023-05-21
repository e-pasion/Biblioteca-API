package com.pragma.biblioteca.controller;

import com.pragma.biblioteca.entity.Editorial;
import com.pragma.biblioteca.service.EditorialService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@AllArgsConstructor
@RequestMapping("/editorial")
public class EditorialController {
    private EditorialService editorialService;

    @GetMapping
    public ResponseEntity<List<Editorial>> verEditoriales(){
        return editorialService.verEditoriales();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Editorial> verEditorial(@PathVariable Long id){
        return editorialService.verEditorial(id);
    }

    @PostMapping
    public ResponseEntity<?> crearEditorial(@RequestBody Editorial editorial){
        return editorialService.crearEditorial(editorial);
    }
    @PutMapping
    public ResponseEntity<?> actualizarEditorial(@RequestBody Editorial editorial){
        return editorialService.actualizarEditorial(editorial);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Editorial> borrarEditorial(@PathVariable Long id){
        return editorialService.borrarEditorial(id);
    }
}
