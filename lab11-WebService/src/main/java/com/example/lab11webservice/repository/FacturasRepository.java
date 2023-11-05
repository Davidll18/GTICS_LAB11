package com.example.lab11webservice.repository;

import com.example.lab11webservice.entity.Facturas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturasRepository extends JpaRepository<Facturas,Integer> {
}
