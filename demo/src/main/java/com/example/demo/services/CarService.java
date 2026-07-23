package com.example.demo.services;

import com.example.demo.dtos.car.CarRequestDTO;
import com.example.demo.dtos.car.CarResponseDTO;
import com.example.demo.entities.Car;
import com.example.demo.entities.User;
import com.example.demo.mapper.CarMapper;
import com.example.demo.repository.CarRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<CarResponseDTO> listarCarros(User usuarioLogado) {
        List<Car> cars = carRepository.findByUser(usuarioLogado);
        return carMapper.toResponseDTOList(cars);


    }

    public ResponseEntity deletarCarro(User usuarioLogado, Long id) {
        Car car = carRepository.findByIdAndUser(id, usuarioLogado)
                .orElseThrow(() -> new RuntimeException("Carro não encontrado"));

        carRepository.delete(car);
        return ResponseEntity.status(HttpStatus.OK).body("Carro deletado com sucesso");
    }


}
