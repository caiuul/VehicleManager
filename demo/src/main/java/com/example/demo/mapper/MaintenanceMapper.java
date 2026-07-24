package com.example.demo.mapper;

import com.example.demo.dtos.car.MaintenanceRequestDTO;
import com.example.demo.dtos.car.MaintenanceResponseDTO;
import com.example.demo.entities.Maintenance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface MaintenanceMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "car", ignore = true)
    Maintenance toEntity(MaintenanceRequestDTO dto);

    MaintenanceResponseDTO toResponseDTO(Maintenance maintenance);

    List<MaintenanceResponseDTO> toResponseDTOList(List<Maintenance> maintenances);


}
