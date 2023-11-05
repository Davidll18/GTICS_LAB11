package com.example.lab11webservice.controller;

import com.example.lab11webservice.entity.Juegosxusuario;
import com.example.lab11webservice.repository.JuegosxUsuarioRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/juegosxusuario")
public class JuegosxusuarioController {

    final JuegosxUsuarioRepository juegosxUsuarioRepository;

    public JuegosxusuarioController(JuegosxUsuarioRepository juegosxUsuarioRepository) {
        this.juegosxUsuarioRepository = juegosxUsuarioRepository;
    }
    @GetMapping(value = {"/list", ""})
    public List<Juegosxusuario> listaProductos() {
        return juegosxUsuarioRepository.findAll();
    }

}
