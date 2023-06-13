package com.pragma.biblioteca.dto;

import com.pragma.biblioteca.entity.Pais;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AutorDTO {
    private String nombre;
    private String apellido;
    private String pseudonimo;
    @NotNull(message = "El autor debe tener asignado un pais")
    private String nombrePais;

    @NotBlank(message = "El email no puede estar vacio")
    @Email(message = "El formato del email debe ser valido\n Ejemplo:prueba@gmail.com")
    private String email;
}
