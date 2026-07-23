package com.example.demo.mapper;

import com.example.demo.dtos.car.CarRequestDTO;
import com.example.demo.dtos.car.CarResponseDTO;
import com.example.demo.entities.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    Car toEntity(CarRequestDTO dto);

    CarResponseDTO toResponseDTO(Car car);

    List<CarResponseDTO> toResponseDTOList(List<Car> cars);
}

