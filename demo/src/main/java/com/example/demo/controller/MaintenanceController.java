package com.example.demo.controller;

import com.example.demo.dtos.car.DeleteMaintenanceRequestDTO;
import com.example.demo.dtos.car.MaintenanceRequestDTO;
import com.example.demo.dtos.car.MaintenanceResponseDTO;
import com.example.demo.entities.User;
import com.example.demo.services.MaintenanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MaintenanceController {

    private final MaintenanceService maintenanceService;

    public MaintenanceController(MaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }

    @PostMapping("/addMaint")
    public ResponseEntity<MaintenanceResponseDTO> addMaintenance(
            @RequestBody MaintenanceRequestDTO dto,
            @AuthenticationPrincipal User usuarioLogado){
        MaintenanceResponseDTO response = maintenanceService.adicionarManutencao(dto, usuarioLogado);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/listMaint/{carId}")
    public ResponseEntity<List<MaintenanceResponseDTO>> getMaintenance(
            @PathVariable Long carId,
            @AuthenticationPrincipal User usuarioLogado){

        List<MaintenanceResponseDTO> response = maintenanceService.listarManutencoesDoCarro(carId, usuarioLogado);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/deleteMaint")
    public ResponseEntity<Void> deleteMaintenance(
            @RequestBody DeleteMaintenanceRequestDTO dto,
            @AuthenticationPrincipal User usuarioLogado) {

        maintenanceService.deletarManutecao(dto.maintenanceId(), dto.carId(), usuarioLogado);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
