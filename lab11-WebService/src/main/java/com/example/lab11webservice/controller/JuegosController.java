package com.example.lab11webservice.controller;

import com.example.lab11webservice.entity.Juegos;
import com.example.lab11webservice.repository.JuegosRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/juegos")
public class JuegosController {

    final JuegosRepository juegosRepository;


    public JuegosController(JuegosRepository juegosRepository) {
        this.juegosRepository = juegosRepository;
    }

    //listado:  GET /juegos
    @GetMapping(value = {"/list", ""})
    public List<Juegos> listaProductos() {
        return juegosRepository.findAll();
    }



}
