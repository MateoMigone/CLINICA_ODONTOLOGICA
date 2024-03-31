package com.example.clinicaodontologica.service;


import com.example.clinicaodontologica.entity.Odontologo;

import java.util.List;
import java.util.Optional;

public interface IOdontologoService {
    Odontologo guardar (Odontologo odontologo);

    List<Odontologo> listarTodos();

    Odontologo buscarPorId(Long id);

    void eliminar(Long id);

    void actualizar(Odontologo odontologo);

    Optional<Odontologo> findByMatricula(String matricula);

    List<Odontologo> findByNombre(String nombre);

    List<Odontologo> findByApellido(String apellido);

}
