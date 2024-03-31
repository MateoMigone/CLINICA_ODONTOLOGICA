package com.example.clinicaodontologica.service.implementation;


import com.example.clinicaodontologica.entity.Odontologo;
import com.example.clinicaodontologica.entity.Paciente;
import com.example.clinicaodontologica.repository.IPacienteRepository;
import com.example.clinicaodontologica.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IPacienteService {
    private IPacienteRepository pacienteRepository;

    @Autowired
    public PacienteService(IPacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }


    @Override
    public Paciente guardar(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    @Override
    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll();
    }

    @Override
    public Paciente buscarPorId(Long id) {
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(id);
        if (pacienteOptional.isPresent()) {
            return pacienteOptional.get();
        }
        return null;
    }

    @Override
    public void eliminar(Long id) {
        pacienteRepository.deleteById(id);
    }

    @Override
    public void actualizar(Paciente paciente) {
        pacienteRepository.save(paciente);
    }

    @Override
    public Optional<Paciente> findByDni(String dni) {
        return pacienteRepository.findByDni(dni);
    }

    @Override
    public List<Paciente> findByNombre(String nombre) {
        return pacienteRepository.findByNombre(nombre);
    }

    @Override
    public List<Paciente> findByApellido(String apellido) {
        return pacienteRepository.findByApellido(apellido);
    }
}
