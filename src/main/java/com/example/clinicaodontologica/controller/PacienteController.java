package com.example.clinicaodontologica.controller;

import com.example.clinicaodontologica.entity.Odontologo;
import com.example.clinicaodontologica.entity.Paciente;
import com.example.clinicaodontologica.service.IPacienteService;
import com.example.clinicaodontologica.service.implementation.PacienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private IPacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public ResponseEntity<Paciente> guardar(@RequestBody Paciente paciente) {
        return ResponseEntity.ok(pacienteService.guardar(paciente));
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> listarTodos() {
        return ResponseEntity.ok(pacienteService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPorId(@PathVariable Long id) {

        return ResponseEntity.ok(pacienteService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        pacienteService.eliminar(id);
    }

    @PutMapping
    public ResponseEntity<String> actualizar(@RequestBody Paciente paciente) {
        ResponseEntity<String> response;
        Paciente pacienteBuscado = pacienteService.buscarPorId(paciente.getId());
        if (pacienteBuscado != null) {
            pacienteService.actualizar(paciente);
            response = ResponseEntity.ok("Se actualizó el paciente con id " + paciente.getId());
        } else {
            response = ResponseEntity.ok().body("No se puede actualizar el paciente");
        }
        return response;

    }

    @GetMapping("/dni/{dni}")
    public  ResponseEntity<Paciente> findByDni(@PathVariable String dni) throws Exception {
        Optional<Paciente> pacienteOptional = pacienteService.findByDni(dni);
        if (pacienteOptional.isPresent()) {
            return ResponseEntity.ok(pacienteOptional.get());
        }
        else {
            throw new Exception("No se encontró el odontólogo con dni " + dni);
        }
    }

    @GetMapping("/nombre/{nombre}")
    public  ResponseEntity<List<Paciente>> findByNombre(@PathVariable String nombre) {
        return ResponseEntity.ok(pacienteService.findByNombre(nombre));

    }

    @GetMapping("/apellido/{apellido}")
    public  ResponseEntity<List<Paciente>> findByApellido(@PathVariable String apellido) {
        return ResponseEntity.ok(pacienteService.findByApellido(apellido));

    }

}

