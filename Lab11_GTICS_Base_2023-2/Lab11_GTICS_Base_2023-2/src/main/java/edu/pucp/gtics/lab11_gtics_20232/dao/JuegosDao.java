package edu.pucp.gtics.lab11_gtics_20232.dao;

import edu.pucp.gtics.lab11_gtics_20232.entity.Juegos;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JuegosDao {
    public List<Juegos> listar(){
        List<Juegos> lista = new ArrayList<>();

        RestTemplate restTemplate = new RestTemplate();
        String endPoint = "http://localhost:8080/juegos";
        ResponseEntity<Juegos[]> responseEntity = restTemplate.getForEntity(endPoint,Juegos[].class);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            Juegos[] body = responseEntity.getBody();
            lista = Arrays.asList(body);
        }
        return lista;
    }
}
