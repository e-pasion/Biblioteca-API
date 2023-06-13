package com.pragma.biblioteca.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UbicacionDTO {
    @NotBlank(message = "El piso no puede estar vacio")
    private String piso;
    @NotBlank(message = "El salon no puede estar vacio")
    private String salon;
    @NotBlank(message = "El estante no puede estar vacio")
    private String estante;
}
