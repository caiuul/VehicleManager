package com.example.demo.controller;


import com.example.demo.dtos.CarRequestDTO;
import com.example.demo.dtos.CarResponseDTO;
import com.example.demo.entities.User;
import com.example.demo.services.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

}
