package com.example.lab11webservice.repository;
import com.example.lab11webservice.entity.Juegosxusuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JuegosxUsuarioRepository extends JpaRepository<Juegosxusuario, Integer> {

}
