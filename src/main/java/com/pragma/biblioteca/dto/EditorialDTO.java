package com.pragma.biblioteca.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class EditorialDTO {
    @NotBlank(message = "El nombre no debe estar vacio")
    @Size(min = 2,max = 30,message = "El nombre debe estar entre 2 y 30")
    private String nombre;

    @NotBlank(message = "La descripcion no debe estar vacia")
    @Size(max = 300,message = "La descripcion no debe ser mayor a 300 caracteres")
    private String descripcion;
}
