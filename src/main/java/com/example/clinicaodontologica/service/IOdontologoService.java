package com.example.clinicaodontologica.service;


import com.example.clinicaodontologica.model.Odontologo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IOdontologoService {
    Odontologo guardar (Odontologo odontologo);

    List<Odontologo> listarTodos();

    Odontologo buscarPorId(Integer id);

}
