package com.example.demo.controller;

import com.example.demo.dtos.CarRequestDTO;
import com.example.demo.entities.Car;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class CarController {

    @PostMapping
    public ResponseEntity<Car> adicionarCarro(@RequestBody CarRequestDTO carro) {
        return null;
    }
}
