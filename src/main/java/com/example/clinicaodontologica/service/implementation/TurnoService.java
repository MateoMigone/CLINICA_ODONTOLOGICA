package com.example.clinicaodontologica.service.implementation;

import com.example.clinicaodontologica.dao.IDao;
import com.example.clinicaodontologica.dao.implementation.TurnoDaoList;
import com.example.clinicaodontologica.model.Turno;
import com.example.clinicaodontologica.service.ITurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService implements ITurnoService {

    private IDao<Turno> turnoDaoList;

    @Autowired
    public TurnoService(TurnoDaoList turnoDaoList) {
        this.turnoDaoList = turnoDaoList;
    }

    @Override
    public Turno guardar(Turno turno) {
        return turnoDaoList.guardar(turno);
    }

    @Override
    public List<Turno> listarTodos() {
        return turnoDaoList.listarTodos();
    }

    @Override
    public Turno buscarPorId(Integer id) {
        return turnoDaoList.buscarPorId(id);
    }

    @Override
    public void eliminar(Integer id) {

    }

    @Override
    public void actualizar(Turno turno) {

    }
}
