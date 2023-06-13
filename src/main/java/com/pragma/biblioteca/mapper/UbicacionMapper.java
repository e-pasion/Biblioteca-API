package com.pragma.biblioteca.mapper;

import com.pragma.biblioteca.dto.UbicacionDTO;
import com.pragma.biblioteca.entity.Ubicacion;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UbicacionMapper {

    UbicacionDTO toUbicacionDto(Ubicacion ubicacion);
    List<UbicacionDTO> toUbicacionesDto(List<Ubicacion> ubicaciones);

    @InheritInverseConfiguration
    @Mapping(target = "id",ignore = true)
    Ubicacion toUbicacion(UbicacionDTO ubicacionDTO);
    @InheritInverseConfiguration
    @Mapping(target = "id",source = "ID")
    Ubicacion toUbicacionConID(UbicacionDTO ubicacionDTO,Long ID);
}
