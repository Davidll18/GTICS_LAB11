package com.example.lab11webservice.controller;

import com.example.lab11webservice.entity.Distribuidoras;
import com.example.lab11webservice.repository.DistribuidorasRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/distribuidoras")
public class DistribuidorasController {

    final DistribuidorasRepository distribuidorasRepository;


    public DistribuidorasController(DistribuidorasRepository distribuidorasRepository) {
        this.distribuidorasRepository = distribuidorasRepository;
    }
    //listado:  GET localhost:8081/distribuidoras
    @GetMapping(value = {"","/" })
    public List<Distribuidoras> listar() {
        return distribuidorasRepository.findAll();
    }

    //listado:  GET /distribuidoras/id
    @GetMapping(value = "/{id}")
    public ResponseEntity<HashMap<String, Object>> buscarPorId(@PathVariable("id") String idStr) {
        try {
            int id = Integer.parseInt(idStr);
            Optional<Distribuidoras> byId = distribuidorasRepository.findById(id);
            HashMap<String, Object> respuesta = new HashMap<>();
            if (byId.isPresent()) {
                respuesta.put("result", "ok");
                respuesta.put("distribuidora", byId.get());
            } else {
                respuesta.put("result", "no existe");
            }
            return ResponseEntity.ok(respuesta);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // CREAR POST localhost:8081/distribuidoras
    @PostMapping(value = {"", "/"})
    public ResponseEntity<HashMap<String, Object>> guardar(
            @RequestBody Distribuidoras distribuidoras,
            @RequestParam(value = "fetchId", required = false) boolean fetchId) {
        HashMap<String, Object> responseJson = new HashMap<>();
        distribuidorasRepository.save(distribuidoras);
        if (fetchId) {
            responseJson.put("id", distribuidoras.getId());
        }
        responseJson.put("estado", "creado");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseJson);
    }
//    {
//        "nombre": "...",
//            "descripcion": "...",
//            "fundacion": ...,
//            "web": "...",
//            "idsede": {
//                "id": ...
//            }
//    }

    //Editar PUT localhost:8081/distribuidoras.     SE EDITA CON X-WWW-FORM-URLENCODED, NO RAW
    @PutMapping(value = {"", "/"}, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<HashMap<String, Object>> actualizar(@RequestBody Distribuidoras distribuidoras1) {
        HashMap<String, Object> rpta = new HashMap<>();
        if (distribuidoras1.getId() != null && distribuidoras1.getId() > 0) {
            Optional<Distribuidoras> byId = distribuidorasRepository.findById(distribuidoras1.getId());
            if (byId.isPresent()) {
                Distribuidoras distribuidorasBD = byId.get();
                if (distribuidoras1.getNombre() != null)
                    distribuidorasBD.setNombre(distribuidoras1.getNombre());
                if (distribuidoras1.getDescripcion() != null)
                    distribuidorasBD.setDescripcion(distribuidoras1.getDescripcion());
                if (distribuidoras1.getFundacion() != null)
                    distribuidorasBD.setFundacion(distribuidoras1.getFundacion());
                if (distribuidoras1.getWeb() != null)
                    distribuidorasBD.setWeb(distribuidoras1.getWeb());

                distribuidorasRepository.save(distribuidorasBD);
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


    //Borrar DELETE localhost:8081/distribuidoras?id=..
    @DeleteMapping("")
    public ResponseEntity<HashMap<String, Object>> borrar(@RequestParam("id") String idStr){
        try{
            int id = Integer.parseInt(idStr);
            HashMap<String, Object> rpta = new HashMap<>();
            Optional<Distribuidoras> byId = distribuidorasRepository.findById(id);
            if(byId.isPresent()){
                distribuidorasRepository.deleteById(id);
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
