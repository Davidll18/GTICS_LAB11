package com.example.lab11webservice.controller;

import com.example.lab11webservice.entity.Facturas;
import com.example.lab11webservice.entity.Usuarios;
import com.example.lab11webservice.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UserController {

    final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(value = {"","/" })
    public List<Usuarios> listar() {
        return userRepository.findAll();
    }

}
