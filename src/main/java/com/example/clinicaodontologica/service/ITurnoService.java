package com.example.clinicaodontologica.service;

import com.example.clinicaodontologica.dto.request.TurnoRequestDTO;
import com.example.clinicaodontologica.dto.response.TurnoResponseDTO;
import com.example.clinicaodontologica.entity.Turno;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ITurnoService {
    TurnoResponseDTO guardar(TurnoRequestDTO turnoRequestDTO);
    List<Turno> listarTodos();

    Turno buscarPorId(Long id);

    void eliminar(Long id);

    void actualizar(TurnoResponseDTO turnoResponseDTO);
}
