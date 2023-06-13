package com.pragma.biblioteca.mapper;

import com.pragma.biblioteca.dto.AutorDTO;
import com.pragma.biblioteca.entity.Autor;
import com.pragma.biblioteca.entity.Pais;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AutorMapper {

    @Mappings({
            @Mapping(target = "nombrePais", expression = "java(autor.getPais().getNombre())")
    })

    AutorDTO toAutorDTO (Autor autor);
    List<AutorDTO> toAutoresDTO(List<Autor> autores);


    @InheritInverseConfiguration
    @Mapping(target = "id",ignore = true)
    @Mapping(target ="pais",ignore = true)
    Autor toAutor (AutorDTO autorDTO);

    @InheritInverseConfiguration
    @Mapping(target = "id",source = "ID")
    @Mapping(target = "pais",ignore = true)
    Autor toAutorConID (AutorDTO autorDTO,Long ID);








}
