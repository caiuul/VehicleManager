package com.example.demo.dtos.car;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record MaintenanceRequestDTO(

        @NotNull(message = "Id is required.")
        Long carId,

        @NotBlank(message = "Description is required")
        String description,

        @NotNull(message = "Date is required")
        LocalDate date,

        @NotNull(message = "Price is required")
        @Positive(message = "Price must be greater than zero")
        BigDecimal price
) {
}


