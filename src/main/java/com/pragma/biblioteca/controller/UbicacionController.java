package com.pragma.biblioteca.controller;

import com.pragma.biblioteca.dto.EditorialDTO;
import com.pragma.biblioteca.dto.UbicacionDTO;
import com.pragma.biblioteca.entity.Ubicacion;
import com.pragma.biblioteca.service.UbicacionService;
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
@RequestMapping("/ubicacion")
@Tag(name = "Ubicacion Controlador", description = "Controlador creado para añadir ubicaciones de la biblioteca")
public class UbicacionController {
    private UbicacionService ubicacionService;

    @GetMapping
    @Operation(summary = "Ver todas las ubicaciones")
    public ResponseEntity<List<UbicacionDTO>> verUbicaciones(){
        return ubicacionService.verUbicacones();
    }
    @GetMapping("/{id}")
    @Operation(summary = "Ver una ubicacion")
    public ResponseEntity<UbicacionDTO> verUbicacion(@PathVariable Long id){
        return ubicacionService.verUbicacion(id);
    }

    @PostMapping
    @Operation(summary = "Crear una ubicacion")
    public ResponseEntity<UbicacionDTO> crearUbicacion(@Valid @RequestBody UbicacionDTO ubicacionDTO, BindingResult bindingResult){
        return ubicacionService.crearUbicacion(ubicacionDTO, bindingResult );
    }
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una ubicacion")
    public ResponseEntity<UbicacionDTO> actualizarUbicacion(@PathVariable Long id, @Valid @RequestBody UbicacionDTO ubicacionDTO, BindingResult bindingResult){
        return ubicacionService.actualizarUbicacion(id,ubicacionDTO, bindingResult);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Borrar una ubicacion")
    public ResponseEntity<UbicacionDTO> borrarUbicacion(@PathVariable Long id){
        return ubicacionService.borrarUbicacon(id);
    }

}
