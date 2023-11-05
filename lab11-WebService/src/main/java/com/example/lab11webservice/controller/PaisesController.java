package com.example.lab11webservice.controller;

import com.example.lab11webservice.entity.Distribuidoras;
import com.example.lab11webservice.entity.Paises;
import com.example.lab11webservice.repository.PaisesRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/paises")
public class PaisesController {

    final PaisesRepository paisesRepository;

    public PaisesController(PaisesRepository paisesRepository) {
        this.paisesRepository = paisesRepository;
    }

    @GetMapping(value = {"","/" })
    public List<Paises> listar() {
        return paisesRepository.findAll();
    }
}
