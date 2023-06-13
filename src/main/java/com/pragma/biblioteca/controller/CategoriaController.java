package com.pragma.biblioteca.controller;

import com.pragma.biblioteca.dto.CategoriaDTO;
import com.pragma.biblioteca.dto.EditorialDTO;
import com.pragma.biblioteca.entity.Categoria;
import com.pragma.biblioteca.service.CategoriaService;
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
@RequestMapping("/categoria")
@Tag(name = "Categoria Controlador", description = "Controlador creado para añadir categorias para los libros")
public class CategoriaController {
    private CategoriaService categoriaService;
    @GetMapping
    @Operation(summary = "Ver todas las categorias")
    public ResponseEntity<List<CategoriaDTO>> verCategorias(){
        return categoriaService.verCategorias();
    }
    @GetMapping("/{id}")
    @Operation(summary = "Ver una categoria")
    public ResponseEntity<CategoriaDTO> verCategoria(@PathVariable Long id){
        return categoriaService.verCategoria(id);
    }

    @PostMapping
    @Operation(summary = "Crear una categoria")
    public ResponseEntity<CategoriaDTO> crearCategoria(@Valid @RequestBody CategoriaDTO categoriaDTO, BindingResult bindingResult){
        return categoriaService.crearCategoria(categoriaDTO,bindingResult);
    }
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una categoria")
    public ResponseEntity<CategoriaDTO> actualizarCategoria(@PathVariable Long id, @Valid @RequestBody CategoriaDTO categoriaDTO, BindingResult bindingResult){
        return categoriaService.actualizarCategoria(id,categoriaDTO, bindingResult);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Borrar una categoria")
    public ResponseEntity<CategoriaDTO> borrarCategoria(@PathVariable Long id){
        return categoriaService.borrarCategoria(id);
    }


}
