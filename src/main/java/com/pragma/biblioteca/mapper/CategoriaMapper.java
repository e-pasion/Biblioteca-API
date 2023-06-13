package com.pragma.biblioteca.mapper;

import com.pragma.biblioteca.dto.CategoriaDTO;
import com.pragma.biblioteca.entity.Categoria;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    CategoriaDTO toCategoriaDTO(Categoria categoria);
    List<CategoriaDTO> toCategoriasDTO(List<Categoria> categorias);

    @InheritInverseConfiguration
    @Mapping(target = "id",ignore = true)
    Categoria toCategoria(CategoriaDTO categoriaDTO);

    @InheritInverseConfiguration
    @Mapping(target = "id",source = "ID")
    Categoria toCategoriaConID(CategoriaDTO categoriaDTO,Long ID);
}
