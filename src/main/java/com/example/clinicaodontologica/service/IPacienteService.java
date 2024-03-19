package com.example.clinicaodontologica.service;

import com.example.clinicaodontologica.model.Paciente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPacienteService {
    Paciente guardar(Paciente paciente);
    List<Paciente> listarTodos();
    Paciente buscarPorId(Integer id);

}
