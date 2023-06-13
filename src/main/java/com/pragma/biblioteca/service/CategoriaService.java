package com.pragma.biblioteca.service;

import com.pragma.biblioteca.dto.CategoriaDTO;
import com.pragma.biblioteca.dto.EditorialDTO;
import com.pragma.biblioteca.entity.Categoria;
import com.pragma.biblioteca.entity.Editorial;
import com.pragma.biblioteca.error.ValidacionException;
import com.pragma.biblioteca.mapper.CategoriaMapper;
import com.pragma.biblioteca.repository.CategoriaRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoriaService {
    private CategoriaRepository categoriaRepository;
    private CategoriaMapper mapper;

    public ResponseEntity<List<CategoriaDTO>>verCategorias(){
        List<Categoria> categoriaList = categoriaRepository.findAll();
        List<CategoriaDTO> categoriaDTOList= mapper.toCategoriasDTO(categoriaList);
        return ResponseEntity.ok(categoriaDTOList);
    }

    public ResponseEntity<CategoriaDTO> verCategoria(Long id){
        Optional<Categoria> resultado=categoriaRepository.findById(id);
        Categoria categoria= resultado.orElseThrow(() -> new ValidacionException("No existe ninguna categoria con este id"));
        CategoriaDTO categoriaDTO=mapper.toCategoriaDTO(categoria);
        return ResponseEntity.ok(categoriaDTO);
    }

    public ResponseEntity<CategoriaDTO> crearCategoria(CategoriaDTO categoriaDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ValidacionException(bindingResult.getFieldError().getDefaultMessage());
        }
        validarCategoria(categoriaDTO);
        Categoria categoria= mapper.toCategoria(categoriaDTO);
        Categoria result=categoriaRepository.save(categoria);
        return ResponseEntity.ok(mapper.toCategoriaDTO(result));
    }

    public ResponseEntity<CategoriaDTO> actualizarCategoria(Long id,CategoriaDTO categoriaDTO, BindingResult bindingResult){
        if (!categoriaRepository.existsById(id)) {
            throw new ValidacionException("Esta categoria no existe");
        }
        else if(bindingResult.hasErrors()){
            throw new ValidacionException(bindingResult.getFieldError().getDefaultMessage());
        }
        validarCategoria(categoriaDTO);
        Categoria categoria= mapper.toCategoriaConID(categoriaDTO,id);
        Categoria resultado=categoriaRepository.save(categoria);
        return ResponseEntity.ok(mapper.toCategoriaDTO(resultado));
    }

    public ResponseEntity<CategoriaDTO> borrarCategoria(Long id){
        if(!categoriaRepository.existsById(id)) {
            throw new ValidacionException("Esta categoria no existe");
        }
        categoriaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public void validarCategoria(CategoriaDTO categoriaDTO){
        if(categoriaRepository.existsByNombre(categoriaDTO.getNombre())){
            throw new ValidacionException("Este nombre ya existe");
        }
    }
}
