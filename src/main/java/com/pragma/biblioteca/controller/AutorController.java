package com.pragma.biblioteca.controller;

import com.pragma.biblioteca.entity.Autor;
import com.pragma.biblioteca.service.AutorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/autor")
@ApiResponse(responseCode = "200", description = "Todo esta OK")
@Tag(name = "Autor Controlador", description = "Controlador creado para permitir a los bibliotecarios añadir autores nuevos")
public class AutorController {
    private AutorService autorService;

    @PostMapping
    @Operation(summary = "Crea un autor Con las reglas:"+
            "-Cada autor debe estar asociado a un pais origen" +
            "-De cada autor se desea conocer: nombre, apellido, pseudonimo (campo unico), email (validar estructura de email)" +
            "-cada autor debe tener nombre y apellido o pseudonimo.")

    public ResponseEntity<Autor> crearAutor(@Valid @RequestBody Autor autor, BindingResult bindingResult){
        return autorService.crearAutor(autor,bindingResult);
    }
}
