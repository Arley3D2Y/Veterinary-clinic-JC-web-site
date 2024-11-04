package com.example.demo.DTO;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.model.Veterinario;

@Mapper
public interface VeterinarioMapper {
    VeterinarioMapper INSTANCE = Mappers.getMapper(VeterinarioMapper.class);

    /* Lo importante */
    VeterinarioDTO convert(Veterinario veterinario);
}