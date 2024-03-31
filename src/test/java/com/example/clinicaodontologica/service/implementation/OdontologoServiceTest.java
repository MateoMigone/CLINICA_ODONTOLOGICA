package com.example.clinicaodontologica.service.implementation;

import com.example.clinicaodontologica.entity.Odontologo;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
class OdontologoServiceTest {

    @Autowired
    private OdontologoService odontologoService;

    @BeforeAll
    void settearAmbiente(){
        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("Carlos");
        odontologo.setApellido("Alcaraz");
        odontologo.setMatricula("967");
        odontologoService.guardar(odontologo);

        Odontologo odontologo2 = new Odontologo();
        odontologo2.setNombre("Lionel");
        odontologo2.setApellido("Messi");
        odontologo2.setMatricula("100");
        odontologoService.guardar(odontologo2);
    }

    @Test
    @Transactional
    void guardar() {
        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("Sergio");
        odontologo.setApellido("Aguero");
        odontologo.setMatricula("160");
        odontologoService.guardar(odontologo);

        List<Odontologo> odontologos = odontologoService.listarTodos();

        assertNotNull(odontologo);
        assertEquals(3,odontologos.size());
        assertEquals(3,odontologo.getId());
    }

    @Test
    void listarTodos() {
        List<Odontologo> odontologos =  odontologoService.listarTodos();

        assertEquals(2,odontologos.size());
        assertEquals(1,odontologos.get(0).getId());
        assertEquals("967",odontologos.get(0).getMatricula());
        assertEquals(2,odontologos.get(1).getId());
        assertEquals("100",odontologos.get(1).getMatricula());
    }

    @Test
    void buscarPorId() {
        Odontologo odontologoBuscado = odontologoService.buscarPorId(2L);

        assertNotNull(odontologoBuscado);
        assertEquals(2,odontologoBuscado.getId());
        assertEquals("Messi",odontologoBuscado.getApellido());
        assertEquals("100",odontologoBuscado.getMatricula());
    }

    @Test
    @Transactional
    void eliminar() {
        odontologoService.eliminar(1L);
        List<Odontologo> odontologos = odontologoService.listarTodos();

        assertEquals(1,odontologos.size());
        assertEquals(2,odontologos.get(0).getId());
        assertEquals("Messi",odontologos.get(0).getApellido());
        assertEquals("100",odontologos.get(0).getMatricula());
    }

    @Test
    @Transactional
    void actualizar() {
        Odontologo nuevoOdontologo = new Odontologo();
        nuevoOdontologo.setId(1L);
        nuevoOdontologo.setNombre("Julian");
        nuevoOdontologo.setApellido("Alvarez");
        nuevoOdontologo.setMatricula("1919");

        odontologoService.actualizar(nuevoOdontologo);
        List<Odontologo> odontologos = odontologoService.listarTodos();

        assertEquals(2,odontologos.size());
        assertEquals(1,odontologos.get(0).getId());
        assertEquals("Julian",odontologos.get(0).getNombre());
        assertEquals("Alvarez",odontologos.get(0).getApellido());
        assertEquals("1919",odontologos.get(0).getMatricula());
    }

    @Test
    void buscarPorMatricula() {
        Optional<Odontologo> odontologoBuscado = odontologoService.findByMatricula("100");

        assertNotNull(odontologoBuscado);
        assertEquals(2,odontologoBuscado.get().getId());
        assertEquals("Lionel",odontologoBuscado.get().getNombre());
        assertEquals("Messi",odontologoBuscado.get().getApellido());
        assertEquals("100",odontologoBuscado.get().getMatricula());
    }

    @Test
    void buscarPorNombre() {
        List<Odontologo> odontologosEncontrados = odontologoService.findByNombre("Lionel");

        assertEquals(1,odontologosEncontrados.size());
        assertEquals(2,odontologosEncontrados.get(0).getId());
        assertEquals("Lionel",odontologosEncontrados.get(0).getNombre());
        assertEquals("Messi",odontologosEncontrados.get(0).getApellido());
        assertEquals("100",odontologosEncontrados.get(0).getMatricula());
    }

    @Test
    void buscarPorApellido() {
        List<Odontologo> odontologosEncontrados = odontologoService.findByApellido("Alcaraz");

        assertEquals(1,odontologosEncontrados.size());
        assertEquals(1,odontologosEncontrados.get(0).getId());
        assertEquals("Carlos",odontologosEncontrados.get(0).getNombre());
        assertEquals("Alcaraz",odontologosEncontrados.get(0).getApellido());
        assertEquals("967",odontologosEncontrados.get(0).getMatricula());
    }

}