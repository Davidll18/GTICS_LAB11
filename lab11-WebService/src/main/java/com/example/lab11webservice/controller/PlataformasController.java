package com.example.lab11webservice.controller;

import com.example.lab11webservice.entity.Plataformas;
import com.example.lab11webservice.repository.PlataformasRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/plataformas")
public class PlataformasController {
    final PlataformasRepository plataformasRepository;

    public PlataformasController(PlataformasRepository plataformasRepository) {
        this.plataformasRepository = plataformasRepository;
    }


    @GetMapping(value = {"","/" })
    public List<Plataformas> listar() {
        return plataformasRepository.findAll();
    }
}
