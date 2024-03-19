package com.example.clinicaodontologica.dao;

import com.example.clinicaodontologica.model.Domicilio;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface IDao<T> {

    T guardar(T t);
    List<T> listarTodos();
    T buscarPorId(Integer id);
}
