package edu.pucp.gtics.lab11_gtics_20232.dao;

import edu.pucp.gtics.lab11_gtics_20232.entity.Juegos;
import edu.pucp.gtics.lab11_gtics_20232.entity.JuegosxUsuario;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Component
public class JuegosDao {
    public List<Juegos> listar(){
        List<Juegos> lista = new ArrayList<>();

        RestTemplate restTemplate = new RestTemplate();
        String endPoint = "http://localhost:8081/juegos";//cambiar si el ws tiene otro url
        ResponseEntity<Juegos[]> responseEntity = restTemplate.getForEntity(endPoint,Juegos[].class);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            Juegos[] body = responseEntity.getBody();
            lista = Arrays.asList(body);
        }
        return lista;
    }


    public List<JuegosxUsuario> listarMisJuegos(Integer idUsuario){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8081/juegos/listaMisJuegos?id="+idUsuario; //cambiar si el ws tiene otro url
        ResponseEntity<JuegosxUsuario[]> responseEntity = restTemplate.getForEntity(url, JuegosxUsuario[].class);
        return Arrays.asList(responseEntity.getBody());
    }


    public void guardar(Juegos juego){

        RestTemplate restTemplate = new RestTemplate(); //cambiar si el ws tiene otro url
        String endPoint = "http://localhost:8081/juegos";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Juegos> httpEntity = new HttpEntity<>(juego,httpHeaders);

        if(juego.getIdjuego() == null){
            restTemplate.postForEntity(endPoint,httpEntity,Juegos.class);
        }else{
            restTemplate.put(endPoint,httpEntity,Juegos.class);
        }

    }

    public Juegos buscarPorId(int id){

        Juegos juego = null;

        RestTemplate restTemplate = new RestTemplateBuilder().basicAuthentication("elarios@pucp.pe", "prueba").build();
        String url = "http://localhost:8081/juegos?id=" + id;

        ResponseEntity<Juegos> forEntity = restTemplate.getForEntity(url, Juegos.class);

        if (forEntity.getStatusCode().is2xxSuccessful()) {
            juego = forEntity.getBody();
        }

        return juego;
    }

    public void borrarJuego(int id) {

        RestTemplate restTemplate = new RestTemplate();

        restTemplate.delete("http://localhost:8081/juegos?id="+id);

    }
}
