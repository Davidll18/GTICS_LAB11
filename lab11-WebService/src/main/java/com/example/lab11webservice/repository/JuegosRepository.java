package com.example.lab11webservice.repository;

import com.example.lab11webservice.entity.Juegos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface JuegosRepository extends JpaRepository<Juegos,Integer> {
}
