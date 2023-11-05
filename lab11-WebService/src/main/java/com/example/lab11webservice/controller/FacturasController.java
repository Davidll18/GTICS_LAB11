package com.example.lab11webservice.controller;

import com.example.lab11webservice.entity.Facturas;
import com.example.lab11webservice.entity.Plataformas;
import com.example.lab11webservice.repository.FacturasRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/facturas")
public class FacturasController {
    final FacturasRepository facturasRepository;

    public FacturasController(FacturasRepository facturasRepository) {
        this.facturasRepository = facturasRepository;
    }


    @GetMapping(value = {"","/" })
    public List<Facturas> listar() {
        return facturasRepository.findAll();
    }
}
