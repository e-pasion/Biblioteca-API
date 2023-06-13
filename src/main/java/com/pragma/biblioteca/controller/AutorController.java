package com.pragma.biblioteca.controller;

import com.pragma.biblioteca.dto.AutorDTO;
import com.pragma.biblioteca.entity.Autor;
import com.pragma.biblioteca.service.AutorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/autor")
@Tag(name = "Autor Controlador", description = "Controlador creado para autores nuevos")
public class AutorController {
    private AutorService autorService;


    @GetMapping
    @Operation(summary = "Ver todos los autores")
    public ResponseEntity<List<AutorDTO>> verAutores(){
        return autorService.verAutores();
    }
    @GetMapping("/{id}")
    @Operation(summary = "Ver un autor")
    public ResponseEntity<AutorDTO> verAutor(@PathVariable Long id){
        return autorService.verAutor(id);
    }

    @PostMapping
    @Operation(summary = "Crea un autor")
    public ResponseEntity<AutorDTO> crearAutor(@Valid @RequestBody AutorDTO autorDTO, BindingResult bindingResult){
        return autorService.crearAutor(autorDTO,bindingResult);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un autor")
    public ResponseEntity<AutorDTO> actualizarAutor( @PathVariable Long id,@Valid @RequestBody AutorDTO autorDTO, BindingResult bindingResult){
        return autorService.actualizarAutor(id,autorDTO,bindingResult);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Borrar un autor")
    public ResponseEntity<AutorDTO> borrarAutor(@PathVariable Long id){
        return autorService.borrarAutor(id);
    }
}
