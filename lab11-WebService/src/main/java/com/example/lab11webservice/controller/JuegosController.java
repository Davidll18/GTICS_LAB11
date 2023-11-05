package com.example.lab11webservice.controller;

import com.example.lab11webservice.entity.Distribuidoras;
import com.example.lab11webservice.entity.Juegos;
import com.example.lab11webservice.repository.JuegosRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/juegos")
public class JuegosController {

    final JuegosRepository juegosRepository;


    public JuegosController(JuegosRepository juegosRepository) {
        this.juegosRepository = juegosRepository;
    }

    //listado:  GET localhost:8081/juegos
    @GetMapping(value = {"/list", ""})
    public List<Juegos> lista() {
        return juegosRepository.findAll();
    }


    //listado:  GET /juegos/id
    @GetMapping(value = "/{id}")
    public ResponseEntity<HashMap<String, Object>> buscarPorId(@PathVariable("id") String idStr) {
        try {
            int id = Integer.parseInt(idStr);
            Optional<Juegos> byId = juegosRepository.findById(id);
            HashMap<String, Object> respuesta = new HashMap<>();
            if (byId.isPresent()) {
                respuesta.put("result", "ok");
                respuesta.put("juego", byId.get());
            } else {
                respuesta.put("result", "no existe");
            }
            return ResponseEntity.ok(respuesta);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // CREAR POST localhost:8081/juegos
    @PostMapping(value = {"", "/"})
    public ResponseEntity<HashMap<String, Object>> crearJuego(
            @RequestBody Juegos juegos,
            @RequestParam(value = "fetchId", required = false) boolean fetchId) {
        HashMap<String, Object> responseJson = new HashMap<>();
        juegosRepository.save(juegos);
        if (fetchId) {
            responseJson.put("id", juegos.getId());
        }
        responseJson.put("estado", "creado");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseJson);
    }
    //Editar PUT localhost:8081/juegos     SE EDITA CON X-WWW-FORM-URLENCODED, NO RAW
    @PutMapping(value = {"", "/"},
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<HashMap<String, Object>> actualizar(Juegos juegos1) {
        HashMap<String, Object> rpta = new HashMap<>();
        if (juegos1.getId() != null && juegos1.getId() > 0) {
            Optional<Juegos> byId = juegosRepository.findById(juegos1.getId());
            if (byId.isPresent()) {
                Juegos juegosBD = byId.get();
                if (juegos1.getNombre() != null)
                    juegosBD.setNombre(juegos1.getNombre());
                if (juegos1.getDescripcion() != null)
                    juegosBD.setDescripcion(juegos1.getDescripcion());
                if (juegos1.getPrecio() != null)
                    juegosBD.setPrecio(juegos1.getPrecio());
                if (juegos1.getImage() != null)
                    juegosBD.setImage(juegos1.getImage());
                if (juegos1.getIdgenero() != null)
                    juegosBD.setIdgenero(juegos1.getIdgenero());
                if (juegos1.getIdplataforma() != null)
                    juegosBD.setIdplataforma(juegos1.getIdplataforma());
                if (juegos1.getIdeditora() != null)
                    juegosBD.setIdeditora(juegos1.getIdeditora());
                if (juegos1.getIddistribuidora() != null)
                    juegosBD.setIddistribuidora(juegos1.getIddistribuidora());

                juegosRepository.save(juegosBD);
                rpta.put("result", "ok");
                return ResponseEntity.ok(rpta);
            } else {
                rpta.put("result", "error");
                rpta.put("msg", "El ID enviado no existe");
                return ResponseEntity.badRequest().body(rpta);
            }
        } else {
            rpta.put("result", "error");
            rpta.put("msg", "debe enviar Id para editar");
            return ResponseEntity.badRequest().body(rpta);
        }
    }

    //Borrar DELETE localhost:8081/juegos?id=..
    @DeleteMapping("")
    public ResponseEntity<HashMap<String, Object>> borrar(@RequestParam("id") String idStr){
        try{
            int id = Integer.parseInt(idStr);
            HashMap<String, Object> rpta = new HashMap<>();
            Optional<Juegos> byId = juegosRepository.findById(id);
            if(byId.isPresent()){
                juegosRepository.deleteById(id);
                rpta.put("result","ok");
            }else{
                rpta.put("result","no ok");
                rpta.put("msg","el Id enviado no existe");
            }
            return ResponseEntity.ok(rpta);
        }catch (NumberFormatException e){
            return ResponseEntity.badRequest().body(null);
        }
    }

}
