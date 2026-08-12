package com.example.demo.dtos.car;

import jakarta.validation.constraints.NotNull;

public record DeleteMaintenanceRequestDTO(
        @NotNull(message = "Maintenance id  is required.")
        Long maintenanceId,
        @NotNull(message = "Id Car is required.")
        Long carId
) {
}
