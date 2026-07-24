package com.example.demo.dtos.car;

import java.math.BigDecimal;
import java.time.LocalDate;

public record MaintenanceRequestDTO(Long carId, String description, LocalDate date, BigDecimal price) {
}


