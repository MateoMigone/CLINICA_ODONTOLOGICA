package com.example.clinicaodontologica.dao.implementation;

import com.example.clinicaodontologica.dao.IDao;
import com.example.clinicaodontologica.model.Turno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Component
public class TurnoDaoList implements IDao<Turno> {
    private List<Turno> turnoList;

    @Autowired
    public TurnoDaoList() {
        this.turnoList = new ArrayList<>();
    }

    @Override
    public Turno guardar(Turno turno) {
        turnoList.add(turno);
        return turno;
    }

    @Override
    public List<Turno> listarTodos() {
        return turnoList;
    }

    @Override
    public Turno buscarPorId(Integer id) {
        Turno turnoEncontrado = null;

        for (Turno t: turnoList) {
            if (t.getId().equals(id)) {
                turnoEncontrado = t;
                return turnoEncontrado;
            }
        }
        return turnoEncontrado;
    }
}
