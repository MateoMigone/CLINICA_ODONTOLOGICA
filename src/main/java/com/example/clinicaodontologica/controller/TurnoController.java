package com.example.clinicaodontologica.controller;

import com.example.clinicaodontologica.model.Paciente;
import com.example.clinicaodontologica.model.Turno;
import com.example.clinicaodontologica.service.IOdontologoService;
import com.example.clinicaodontologica.service.IPacienteService;
import com.example.clinicaodontologica.service.ITurnoService;
import com.example.clinicaodontologica.service.implementation.OdontologoService;
import com.example.clinicaodontologica.service.implementation.PacienteService;
import com.example.clinicaodontologica.service.implementation.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private ITurnoService turnoService;
    private IOdontologoService odontologoService;
    private IPacienteService pacienteService;

    @Autowired
    public TurnoController(TurnoService turnoService, OdontologoService odontologoService, PacienteService pacienteService) {
        this.turnoService = turnoService;
        this.odontologoService = odontologoService;
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public ResponseEntity<Turno> guardar(@RequestBody Turno turno) {
        ResponseEntity<Turno> response;

        if (odontologoService.buscarPorId(turno.getOdontologo().getId()) != null &&
                pacienteService.buscarPorId(turno.getPaciente().getId()) != null) {

            response = ResponseEntity.ok(turnoService.guardar(turno));

        } else {

            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        }
        return response;
    }

    @GetMapping
    public ResponseEntity<List<Turno>> listarTodos() {
        return ResponseEntity.ok(turnoService.listarTodos());
    }
}
