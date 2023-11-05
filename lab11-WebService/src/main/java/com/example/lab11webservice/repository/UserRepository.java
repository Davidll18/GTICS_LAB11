package com.example.lab11webservice.repository;

import com.example.lab11webservice.entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<Usuarios, Integer> {
}
