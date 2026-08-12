package com.example.demo.repository;

import com.example.demo.entities.Car;
import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findByUser(User user);

    Optional<Car> findByIdAndUser(Long id, User user);
}
