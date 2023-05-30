package com.pragma.biblioteca.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ubicacion")
public class Ubicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El piso no puede estar vacio")
    private String piso;
    @NotBlank(message = "El salon no puede estar vacio")
    private String salon;
    @NotBlank(message = "El estante no puede estar vacio")
    private String estante;
}
