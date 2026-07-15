package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "cars")
@Data
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String modelo;
    @Column(unique = true)
    private String placa;
    private Integer ano;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}