package com.example.clinicaodontologica.service.implementation;

import com.example.clinicaodontologica.dao.IDao;
import com.example.clinicaodontologica.dao.implementation.PacienteDaoH2;
import com.example.clinicaodontologica.model.Paciente;
import com.example.clinicaodontologica.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService implements IPacienteService {
    private IDao<Paciente> pacienteIDao;

    @Autowired
    public PacienteService(IDao<Paciente> pacienteIDao) {
        this.pacienteIDao = pacienteIDao;
    }

    @Override
    public Paciente guardar(Paciente paciente) {
        return pacienteIDao.guardar(paciente);
    }

    @Override
    public List<Paciente> listarTodos() {
        return pacienteIDao.listarTodos();
    }

    @Override
    public Paciente buscarPorId(Integer id) {
        return pacienteIDao.buscarPorId(id);
    }

}
