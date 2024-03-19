package com.example.clinicaodontologica.service.implementation;

import com.example.clinicaodontologica.dao.IDao;
import com.example.clinicaodontologica.model.Odontologo;
import com.example.clinicaodontologica.service.IOdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService implements IOdontologoService {

    private IDao<Odontologo> odontologoIDao;

    @Autowired
    public OdontologoService(IDao<Odontologo> odontologo) {
        this.odontologoIDao = odontologo;
    }

    @Override
    public Odontologo guardar (Odontologo odontologo) {
        return odontologoIDao.guardar(odontologo);
    }

    @Override
    public List<Odontologo> listarTodos() {
        return odontologoIDao.listarTodos();
    }

    @Override
    public Odontologo buscarPorId(Integer id) {
        return odontologoIDao.buscarPorId(id);}


}
