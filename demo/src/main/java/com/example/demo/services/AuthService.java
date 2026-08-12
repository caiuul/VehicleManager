package com.example.demo.services;

import com.example.demo.dtos.auth.LoginRequestDTO;
import com.example.demo.dtos.auth.RegisterRequestDTO;
import com.example.demo.entities.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User efetuarLogin(LoginRequestDTO loginRequestDTO) {
        User user = userRepository.findByName(loginRequestDTO.name())
                .orElseThrow(() -> new RuntimeException("Usuario nao encontrado."));

        if (!passwordEncoder.matches(loginRequestDTO.password(), user.getPassword())) {
            throw new RuntimeException("Senha incorreta.");
        }

        return user;

    }

    public String efetuarCadastro(RegisterRequestDTO registerRequestDTO) {
        if (userRepository.findByName(registerRequestDTO.name()).isPresent()) {
            throw new RuntimeException("Este nome ja esta em uso");
        }

        User user = new User();
        user.setName(registerRequestDTO.name());

        String passwordCrpyt = passwordEncoder.encode(registerRequestDTO.password());
        user.setPassword(passwordCrpyt);

        userRepository.save(user);
        return "Cadastro realizado com sucesso";


    }
}
