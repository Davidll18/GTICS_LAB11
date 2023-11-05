package com.example.lab11webservice.repository;

import com.example.lab11webservice.entity.Generos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface GenerosRepository  extends JpaRepository<Generos,Integer> {
}
