package com.example.demo.controller;

import com.example.demo.dtos.auth.LoginRequestDTO;
import com.example.demo.dtos.auth.LoginResponseDTO;
import com.example.demo.dtos.auth.RegisterRequestDTO;
import com.example.demo.entities.User;
import com.example.demo.security.TokenService;
import com.example.demo.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginDetails) {
        try {
            User user = authService.efetuarLogin(loginDetails);
            var token = tokenService.generateToken(user);

            return ResponseEntity.ok(new LoginResponseDTO(token));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new LoginResponseDTO(e.getMessage()));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequestDTO registerDetails) {

        try{
            String sucess = authService.efetuarCadastro(registerDetails);
            return ResponseEntity.ok(sucess);
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
