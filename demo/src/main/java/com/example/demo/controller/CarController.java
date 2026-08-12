package com.example.demo.controller;


import com.example.demo.dtos.car.CarRequestDTO;
import com.example.demo.dtos.car.CarResponseDTO;
import com.example.demo.dtos.car.CarUpdateRequestDTO;
import com.example.demo.entities.User;
import com.example.demo.services.CarService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/addcar")
    public ResponseEntity<CarResponseDTO> adicionarCarro(
            @Valid
            @RequestBody CarRequestDTO dto,
            @AuthenticationPrincipal User usuarioLogado) {
        CarResponseDTO response = carService.adicionarCarro(dto, usuarioLogado);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/listcars")
    public ResponseEntity<List<CarResponseDTO>> listarCarros(
            @AuthenticationPrincipal User usuarioLogado) {

        List<CarResponseDTO> response = carService.listarCarros(usuarioLogado);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/deletecar/{id}")
    public ResponseEntity<Void> deletarCarro(
            @PathVariable Long id,
            @AuthenticationPrincipal User usuarioLogado) {

        carService.deletarCarro(id, usuarioLogado);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/updatecar/{id}")
    public ResponseEntity<CarResponseDTO> atualizarCarro(
            @PathVariable Long id,
            @Valid
            @RequestBody CarUpdateRequestDTO dto,
            @AuthenticationPrincipal User usuarioLogado) {
        CarResponseDTO response = carService.atualizarCarro(id, dto, usuarioLogado);
        return ResponseEntity.ok(response);
    }


}
