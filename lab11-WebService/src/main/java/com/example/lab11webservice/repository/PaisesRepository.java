package com.example.lab11webservice.repository;

import com.example.lab11webservice.entity.Paises;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PaisesRepository extends JpaRepository<Paises,Integer> {
}
