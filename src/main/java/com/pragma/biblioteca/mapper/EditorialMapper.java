package com.pragma.biblioteca.mapper;

import com.pragma.biblioteca.dto.EditorialDTO;
import com.pragma.biblioteca.entity.Editorial;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EditorialMapper {
    EditorialDTO toEditorialDTO(Editorial editorial);
    List<EditorialDTO> toEditorialesDTO(List<Editorial> editoriales);

    @InheritInverseConfiguration
    @Mapping(target = "id",ignore = true)
    Editorial toEditorial(EditorialDTO editorialDTO);
    @InheritInverseConfiguration
    @Mapping(target = "id",source = "ID")
    Editorial toEditorialConID(EditorialDTO editorialDTO,Long ID);
}
