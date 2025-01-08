package org.example.proyecto_intercambio.Intercambio.controller;

import org.example.proyecto_intercambio.Intercambio.models.IntercambioModel;
import org.example.proyecto_intercambio.Intercambio.models.JoinIntercambioModel;
import org.example.proyecto_intercambio.Intercambio.models.UserIntercambioModel;
import org.example.proyecto_intercambio.Intercambio.service.IntercambioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = {"*"})
public class IntercambioController {

    @Autowired
    private IntercambioService intercambioService;

    @PostMapping("/intercambio")
    public IntercambioModel createIntercambio(@RequestBody IntercambioModel intercambio){
        return intercambioService.crearIntercambio(intercambio);
    }

    @PostMapping("/intercambio/join-intercambio")
    public ResponseEntity<String> joinIntercambio(@RequestBody JoinIntercambioModel joinIntercambio) {
        intercambioService.buscarporIdInvitacion(joinIntercambio);
        return ResponseEntity.ok("Ingreso a intercambio exitoso");
    }
    @GetMapping("/intercambio/{id}")
    public IntercambioModel getIntercambio(@PathVariable Long id) {
        return intercambioService.readIntercambio(id);
    }
    @GetMapping("/intercambios/by-user/{id}")
    public List<UserIntercambioModel> getIntercambiosByUser(@PathVariable Long id) {
        return intercambioService.readIntercambiosByUser(id);
    }
}
