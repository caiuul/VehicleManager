package com.example.demo.dtos.car;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CarUpdateRequestDTO(

        @NotBlank(message = "Model is required")
        String model,

        @NotBlank(message = "License plate is required")
        @Pattern(regexp = "^[A-Z]{4}-?\\d{3}$", message = "Invalid license plate format")
        String licensePlate,

        @NotNull(message = "Year is required")
        @Min(value = 1950, message = "Year must be greater than 1950")
        Integer year
) {
}
