package com.pragma.biblioteca.entity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private String pseudonimo;
    @NotNull(message = "El autor debe tener asignado un pais")
    @ManyToOne
    @JoinColumn(name = "pais_id")
    private Pais pais;

    @NotBlank(message = "El email no puede estar vacio")
    @Email(message = "El formato del email debe ser valido\n Ejemplo:prueba@gmail.com")
    private String email;
}
