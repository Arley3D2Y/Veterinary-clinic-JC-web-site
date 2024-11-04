package com.example.demo.DTO;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.model.Cliente;

@Mapper
public interface ClienteMapper {
    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    /* Lo importante */
    ClienteDTO convert(Cliente cliente);
}