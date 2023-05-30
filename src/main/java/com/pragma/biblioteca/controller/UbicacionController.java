package com.pragma.biblioteca.controller;

import com.pragma.biblioteca.entity.Ubicacion;
import com.pragma.biblioteca.service.UbicacionService;
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
@RequestMapping("/ubicacion")
public class UbicacionController {
    private UbicacionService ubicacionService;

    @PostMapping
    public ResponseEntity<Ubicacion> crearUbicacion(@Valid @RequestBody Ubicacion ubicacion,BindingResult bindingResult){
        return ubicacionService.crearUbicacion(ubicacion, bindingResult );
    }

}
