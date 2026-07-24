package com.example.demo.dtos.car;

import java.math.BigDecimal;
import java.time.LocalDate;

public record MaintenanceResponseDTO(Long id, String description, LocalDate date, BigDecimal price) {
}
