package com.example.lab11webservice.repository;

import com.example.lab11webservice.entity.Distribuidoras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface DistribuidorasRepository extends JpaRepository<Distribuidoras,Integer> {
}
