/*package com.example.clinicaodontologica.service.implementation;

import com.example.clinicaodontologica.dao.IDao;
import com.example.clinicaodontologica.dao.implementation.OdontologoDaoH2;
import com.example.clinicaodontologica.dao.implementation.OdontologoDaoEnMemoria;
import com.example.clinicaodontologica.model.Odontologo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OdontologoServiceTest {
    private IDao<Odontologo> odontologoDaoEnMemoria = new OdontologoDaoEnMemoria(new ArrayList());
    private IDao<Odontologo> odontologoDaoH2 = new OdontologoDaoH2();
    private OdontologoService odontologoServiceEnMemoria = new OdontologoService(odontologoDaoEnMemoria);
    private OdontologoService odontologoServiceH2 = new OdontologoService(odontologoDaoH2);

    @Test
    void guardarYListarOdontologos() {
        Odontologo odontologo1 = new Odontologo(123,"Juan","Perez");
        Odontologo odontologo2 = new Odontologo(321,"Pedro","Lopez");
        Odontologo odontologo3 = new Odontologo(456,"Matias","Gomez");
        Odontologo odontologo4 = new Odontologo(654,"Susana","Gimenez");

        odontologoServiceEnMemoria.guardar(odontologo1);
        odontologoServiceEnMemoria.guardar(odontologo2);
        List<Odontologo> listaOdontologosEnMemoria =  odontologoServiceEnMemoria.listarTodos();


        odontologoServiceH2.guardar(odontologo3);
        odontologoServiceH2.guardar(odontologo4);
        List<Odontologo> listaOdontologosDaoH2 =  odontologoServiceH2.listarTodos();

        assertTrue(listaOdontologosEnMemoria.size() == 2);

        assertEquals(listaOdontologosEnMemoria.get(0).getMatricula(),123);
        assertEquals(listaOdontologosEnMemoria.get(0).getNombre(),"Juan");
        assertEquals(listaOdontologosEnMemoria.get(0).getApellido(),"Perez");

        assertEquals(listaOdontologosEnMemoria.get(1).getMatricula(),321);
        assertEquals(listaOdontologosEnMemoria.get(1).getNombre(),"Pedro");
        assertEquals(listaOdontologosEnMemoria.get(1).getApellido(),"Lopez");

        assertTrue(listaOdontologosDaoH2.size() == 2);

        assertEquals(listaOdontologosDaoH2.get(0).getMatricula(),456);
        assertEquals(listaOdontologosDaoH2.get(0).getNombre(),"Matias");
        assertEquals(listaOdontologosDaoH2.get(0).getApellido(),"Gomez");

        assertEquals(listaOdontologosDaoH2.get(1).getMatricula(),654);
        assertEquals(listaOdontologosDaoH2.get(1).getNombre(),"Susana");
        assertEquals(listaOdontologosDaoH2.get(1).getApellido(),"Gimenez");
    }

}*/