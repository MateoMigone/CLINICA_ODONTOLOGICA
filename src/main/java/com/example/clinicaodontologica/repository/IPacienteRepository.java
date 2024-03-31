package com.example.clinicaodontologica.repository;

import com.example.clinicaodontologica.entity.Odontologo;
import com.example.clinicaodontologica.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPacienteRepository extends JpaRepository<Paciente,Long> {
    Optional<Paciente> findByDni(String matricula);

    List<Paciente> findByNombre(String nombre);

    List<Paciente> findByApellido(String apellido);
}
