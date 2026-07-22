package com.example.demo.mapper;

import ch.qos.logback.core.model.ComponentModel;
import com.example.demo.dtos.CarRequestDTO;
import com.example.demo.dtos.CarResponseDTO;
import com.example.demo.entities.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CarMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    Car toEntity(CarRequestDTO dto);

    CarResponseDTO toResponseDTO(Car car);
}
