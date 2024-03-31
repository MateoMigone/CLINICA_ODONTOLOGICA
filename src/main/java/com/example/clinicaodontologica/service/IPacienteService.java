package com.example.clinicaodontologica.service;

import com.example.clinicaodontologica.entity.Odontologo;
import com.example.clinicaodontologica.entity.Paciente;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IPacienteService {
    Paciente guardar(Paciente paciente);
    List<Paciente> listarTodos();
    Paciente buscarPorId(Long id);
    void eliminar(Long id);
    void actualizar(Paciente paciente);

    Optional<Paciente> findByDni(String dni);

    List<Paciente> findByNombre(String nombre);

    List<Paciente> findByApellido(String apellido);

}
