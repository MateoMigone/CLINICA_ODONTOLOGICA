package com.example.clinicaodontologica.repository;

import com.example.clinicaodontologica.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IOdontologoRepository extends JpaRepository<Odontologo,Long> {

    Optional <Odontologo> findByMatricula(String matricula);

    List<Odontologo> findByNombre(String nombre);

    List<Odontologo> findByApellido(String apellido);
}
