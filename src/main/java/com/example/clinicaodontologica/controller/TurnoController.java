package com.example.clinicaodontologica.controller;

import com.example.clinicaodontologica.dto.request.TurnoRequestDTO;
import com.example.clinicaodontologica.dto.response.TurnoResponseDTO;
import com.example.clinicaodontologica.entity.Odontologo;
import com.example.clinicaodontologica.entity.Turno;
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
    public ResponseEntity<TurnoResponseDTO> guardar(@RequestBody TurnoRequestDTO turnoRequestDTO) {
        ResponseEntity<TurnoResponseDTO> response;

        if (odontologoService.buscarPorId(turnoRequestDTO.getOdontologo_id()) != null &&
                pacienteService.buscarPorId(turnoRequestDTO.getPaciente_id()) != null) {

            response = ResponseEntity.ok(turnoService.guardar(turnoRequestDTO));

        } else {

            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        }
        return response;
    }

    @GetMapping
    public ResponseEntity<List<Turno>> listarTodos() {
        return ResponseEntity.ok(turnoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turno> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(turnoService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        turnoService.eliminar(id);
    }

    @PutMapping
    public ResponseEntity<String> actualizar(@RequestBody TurnoResponseDTO turnoResponseDTO) {
        ResponseEntity<String> response;
        Turno turnoBuscado = turnoService.buscarPorId(turnoResponseDTO.getId());
        if (turnoBuscado != null) {
            turnoService.actualizar(turnoResponseDTO);
            response = ResponseEntity.ok("Se actualizó el turno con id " + turnoResponseDTO.getId());
        } else {
            response = ResponseEntity.ok().body("No se puede actualizar el turno");
        }
        return response;

    }
}
