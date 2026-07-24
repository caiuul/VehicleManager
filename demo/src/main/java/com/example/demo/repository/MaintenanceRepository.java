package com.example.demo.repository;

import com.example.demo.entities.Car;
import com.example.demo.entities.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MaintenanceRepository extends JpaRepository<Maintenance,Long> {
    List<Maintenance> findByCar(Car car);

    Optional<Maintenance> findByIdAndCar(Long id, Car car);

}
