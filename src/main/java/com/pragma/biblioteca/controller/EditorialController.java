package com.pragma.biblioteca.controller;

import com.pragma.biblioteca.dto.EditorialDTO;
import com.pragma.biblioteca.entity.Editorial;
import com.pragma.biblioteca.service.EditorialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@AllArgsConstructor
@RequestMapping("/editorial")
@Tag(name = "Editorial Controlador", description = "Controlador creado para añadir Editoriales")
public class EditorialController {
    private EditorialService editorialService;

    @GetMapping
    @Operation(summary = "Ver todas las editoriales")
    public ResponseEntity<List<EditorialDTO>> verEditoriales(){
        return ResponseEntity.ok(editorialService.verEditoriales());
    }
    @GetMapping("/{id}")
    @Operation(summary = "Ver una editorial")
    public ResponseEntity<EditorialDTO> verEditorial(@PathVariable Long id){
        return ResponseEntity.ok(editorialService.verEditorial(id));
    }

    @PostMapping
    @Operation(summary = "Crear una editorial")
    public ResponseEntity<EditorialDTO> crearEditorial(@Valid @RequestBody EditorialDTO editorialDTO, BindingResult bindingResult){
        return ResponseEntity.ok(editorialService.crearEditorial(editorialDTO, bindingResult));
    }
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una editorial")
    public ResponseEntity<EditorialDTO> actualizarEditorial(@PathVariable Long id, @Valid @RequestBody EditorialDTO editorialDTO, BindingResult bindingResult){
        return ResponseEntity.ok(editorialService.actualizarEditorial(id,editorialDTO, bindingResult));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Borrar una editorial")
    public ResponseEntity<EditorialDTO> borrarEditorial(@PathVariable Long id){
        editorialService.borrarEditorial(id);
        return ResponseEntity.noContent().build();
    }
}
