package com.pragma.biblioteca.service;

import com.pragma.biblioteca.entity.Categoria;
import com.pragma.biblioteca.error.ValidacionException;
import com.pragma.biblioteca.repository.CategoriaRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
@AllArgsConstructor
public class CategoriaService {
    private CategoriaRepository categoriaRepository;

    public ResponseEntity<Categoria> crearCategoria(Categoria categoria, BindingResult bindingResult){
        if(categoria.getId()!=null){
            throw new ValidacionException("No se permite enviar un id al crear un objeto");
        }else if(bindingResult.hasErrors()){
            throw new ValidacionException(bindingResult.getFieldError().getDefaultMessage());
        }
        validarCategoria(categoria);
        Categoria result=categoriaRepository.save(categoria);
        return ResponseEntity.ok(result);

    }

    public void validarCategoria(Categoria categoria){
        if(categoriaRepository.existsByNombre(categoria.getNombre())){
            throw new ValidacionException("Este nombre ya existe");
        }
    }
}
