package com.example.demo.controller;


import com.example.demo.dtos.car.CarRequestDTO;
import com.example.demo.dtos.car.CarResponseDTO;
import com.example.demo.dtos.car.DeleteCarRequestDTO;
import com.example.demo.entities.User;
import com.example.demo.services.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/addcar")
    public ResponseEntity<CarResponseDTO> adicionarCarro(
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

    @DeleteMapping("/deletecar")
    public ResponseEntity<CarResponseDTO> deletarCarro(
            @AuthenticationPrincipal User usuarioLogado,
            @RequestBody DeleteCarRequestDTO dto) {

        carService.deletarCarro(usuarioLogado, dto.id());
        return ResponseEntity.status(HttpStatus.OK).build();
    }



}
