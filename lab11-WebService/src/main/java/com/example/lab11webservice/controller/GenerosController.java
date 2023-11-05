package com.example.lab11webservice.controller;

import com.example.lab11webservice.entity.Generos;
import com.example.lab11webservice.entity.Paises;
import com.example.lab11webservice.repository.GenerosRepository;
import com.example.lab11webservice.repository.PaisesRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/generos")
public class GenerosController {

    final GenerosRepository generosRepository;

    public GenerosController(GenerosRepository generosRepository) {
        this.generosRepository = generosRepository;
    }

    @GetMapping(value = {"","/" })
    public List<Generos> listar() {
        return generosRepository.findAll();
    }

}
