package com.example.demo.services;

import com.example.demo.dtos.CarRequestDTO;
import com.example.demo.dtos.CarResponseDTO;
import com.example.demo.entities.Car;
import com.example.demo.entities.User;
import com.example.demo.mapper.CarMapper;
import com.example.demo.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;

    public CarService(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

    public CarResponseDTO adicionarCarro(CarRequestDTO dto, User usuarioLogado) {
        Car car = carMapper.toEntity(dto);
        car.setUser(usuarioLogado);

        Car Salvo = carRepository.save(car);
        return carMapper.toResponseDTO(Salvo);
    }


}
