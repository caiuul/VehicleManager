package com.example.demo.services;

import com.example.demo.dtos.car.MaintenanceRequestDTO;
import com.example.demo.dtos.car.MaintenanceResponseDTO;
import com.example.demo.entities.Car;
import com.example.demo.entities.Maintenance;
import com.example.demo.entities.User;
import com.example.demo.mapper.MaintenanceMapper;
import com.example.demo.repository.CarRepository;
import com.example.demo.repository.MaintenanceRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaintenanceService {

    private final MaintenanceRepository maintenanceRepository;
    private final CarRepository carRepository;
    private final MaintenanceMapper maintenanceMapper;

    public MaintenanceService(MaintenanceRepository maintenanceRepository,
                              CarRepository carRepository,
                              MaintenanceMapper maintenanceMapper) {
        this.maintenanceRepository = maintenanceRepository;
        this.carRepository = carRepository;
        this.maintenanceMapper = maintenanceMapper;
    }

    public MaintenanceResponseDTO adicionarManutencao(MaintenanceRequestDTO dto, User usuarioLogado) {
        Car car = carRepository.findByIdAndUser(dto.carId(), usuarioLogado)
                .orElseThrow(()-> new RuntimeException("Carro não encontrado para esse usuário"));

        Maintenance maintenance = maintenanceMapper.toEntity(dto);
        maintenance.setCar(car);

        Maintenance salvo = maintenanceRepository.save(maintenance);
        return maintenanceMapper.toResponseDTO(salvo);
    }

    public List<MaintenanceResponseDTO> listarManutencoesDoCarro(Long carId, User usuarioLogado) {
        Car car = carRepository.findByIdAndUser(carId, usuarioLogado)
                .orElseThrow(() -> new RuntimeException("Carro não encontrado para esse usuário"));


        List<Maintenance> maintenances = maintenanceRepository.findByCar(car);
        return maintenanceMapper.toResponseDTOList(maintenances);
    }

    public void deletarManutecao(Long maintenanceId, Long carId, User usuarioLogado) {
        Car car = carRepository.findByIdAndUser(carId, usuarioLogado)
                .orElseThrow(() -> new RuntimeException("Carro não encontrado para esse usuário"));

        Maintenance maintenance = maintenanceRepository.findByIdAndCar(maintenanceId, car)
                .orElseThrow(() -> new RuntimeException("Manutenção não encontrada para esse carro"));

        maintenanceRepository.delete(maintenance);
    }

}
