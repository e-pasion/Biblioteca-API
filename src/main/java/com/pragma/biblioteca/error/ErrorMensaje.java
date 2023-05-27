package com.pragma.biblioteca.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMensaje {
    private HttpStatus estado;
    private String mensaje;
}
