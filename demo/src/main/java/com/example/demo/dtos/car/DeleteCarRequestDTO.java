package com.example.demo.dtos.car;

import jakarta.validation.constraints.NotNull;

public record DeleteCarRequestDTO(
        @NotNull(message = "Id is required.")
        Long id) {
}
