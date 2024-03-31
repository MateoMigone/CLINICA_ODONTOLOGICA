package com.example.clinicaodontologica.service.implementation;

import com.example.clinicaodontologica.entity.Odontologo;
import com.example.clinicaodontologica.repository.IOdontologoRepository;
import com.example.clinicaodontologica.service.IOdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService implements IOdontologoService {

    private IOdontologoRepository odontologoRepository;

    @Autowired
    public OdontologoService(IOdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    @Override
    public Odontologo guardar (Odontologo odontologo) {
        return odontologoRepository.save(odontologo);
    }

    @Override
    public List<Odontologo> listarTodos() {
        return odontologoRepository.findAll();
    }

    @Override
    public Odontologo buscarPorId(Long id) {
        Optional<Odontologo> odontologoOptional = odontologoRepository.findById(id);
        if(odontologoOptional.isPresent()) {
            return odontologoOptional.get();
        }
        return null;
    }

    @Override
    public void eliminar(Long id) {
        odontologoRepository.deleteById(id);
    }

    @Override
    public void actualizar(Odontologo odontologo) {
         odontologoRepository.save(odontologo);
    }

    @Override
    public Optional<Odontologo> findByMatricula(String matricula) {
        return odontologoRepository.findByMatricula(matricula);
    }

    @Override
    public List<Odontologo> findByNombre(String nombre) {
        return odontologoRepository.findByNombre(nombre);
    }

    @Override
    public List<Odontologo> findByApellido(String apellido) {
        return odontologoRepository.findByApellido(apellido);
    }

}
