package com.example.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entities.User;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/me")
    public String me(@AuthenticationPrincipal User user) {
        return "Você está autenticado como: " + user.getName();
    }
}