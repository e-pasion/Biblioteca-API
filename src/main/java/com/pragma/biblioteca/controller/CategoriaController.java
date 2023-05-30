package com.pragma.biblioteca.controller;

import com.pragma.biblioteca.entity.Categoria;
import com.pragma.biblioteca.service.CategoriaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/categoria")
public class CategoriaController {
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<Categoria> crearCategoria(Categoria categoria, BindingResult bindingResult){
        return categoriaService.crearCategoria(categoria,bindingResult);
    }


}
