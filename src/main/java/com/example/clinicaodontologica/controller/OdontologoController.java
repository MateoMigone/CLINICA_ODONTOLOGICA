package com.example.clinicaodontologica.controller;

import com.example.clinicaodontologica.entity.Odontologo;
import com.example.clinicaodontologica.service.IOdontologoService;
import com.example.clinicaodontologica.service.implementation.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private IOdontologoService odontologoService;

    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @PostMapping
    public ResponseEntity<Odontologo> guardar(@RequestBody Odontologo odontologo) {
        return ResponseEntity.ok(odontologoService.guardar(odontologo));
    }

    @GetMapping
    public ResponseEntity<List<Odontologo>> listarTodos() {
        return ResponseEntity.ok(odontologoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(odontologoService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        odontologoService.eliminar(id);
    }

    @PutMapping
    public ResponseEntity<String> actualizar(@RequestBody Odontologo odontologo) {
        ResponseEntity<String> response;
        Odontologo odontologoBuscado = odontologoService.buscarPorId(odontologo.getId());
        if (odontologoBuscado != null) {
            odontologoService.actualizar(odontologo);
            response = ResponseEntity.ok("Se actualizó el odontologo con id " + odontologo.getId());
        } else {
            response = ResponseEntity.ok().body("No se puede actualizar el odontologo");
        }
        return response;

    }

    @GetMapping("/matricula/{matricula}")
    public  ResponseEntity<Odontologo> findByMatricula(@PathVariable String matricula) throws Exception {
        Optional<Odontologo> odontologoOptional = odontologoService.findByMatricula(matricula);
        if (odontologoOptional.isPresent()) {
            return ResponseEntity.ok(odontologoOptional.get());
        }
        else {
            throw new Exception("No se encontró el odontólogo con matrícula " + matricula);
        }
    }

    @GetMapping("/nombre/{nombre}")
    public  ResponseEntity<List<Odontologo>> findByNombre(@PathVariable String nombre) {
        return ResponseEntity.ok(odontologoService.findByNombre(nombre));

    }

    @GetMapping("/apellido/{apellido}")
    public  ResponseEntity<List<Odontologo>> findByApellido(@PathVariable String apellido) {
        return ResponseEntity.ok(odontologoService.findByApellido(apellido));

    }

}
