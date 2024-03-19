package com.example.clinicaodontologica.service;

import com.example.clinicaodontologica.model.Turno;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ITurnoService {
    Turno guardar(Turno turno);
    List<Turno> listarTodos();

    Turno buscarPorId(Integer id);

    void eliminar(Integer id);

    void actualizar(Turno turno);
}
