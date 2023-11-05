package com.example.lab11webservice.controller;

import com.example.lab11webservice.entity.Generos;
import com.example.lab11webservice.repository.GenerosRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/editoras")
public class EditorasController {

    final GenerosRepository generosRepository;

    public EditorasController(GenerosRepository generosRepository) {
        this.generosRepository = generosRepository;
    }

    @GetMapping(value = {"","/" })
    public List<Generos> listar() {
        return generosRepository.findAll();
    }

}
