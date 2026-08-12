package com.example.demo.dtos.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequestDTO(

        @NotBlank(message = "Name is required.")
        String name,

        @Size(min = 6, message = "Password must be at least 6 characters long")
        @NotBlank(message = "Password is required")
        String password
) {
}
