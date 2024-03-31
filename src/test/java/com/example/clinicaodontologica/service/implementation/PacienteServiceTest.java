package com.example.clinicaodontologica.service.implementation;

import com.example.clinicaodontologica.entity.Domicilio;
import com.example.clinicaodontologica.entity.Odontologo;
import com.example.clinicaodontologica.entity.Paciente;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class PacienteServiceTest {

    @Autowired
    private PacienteService pacienteService;

    @BeforeAll
    void settearAmbiente(){
        Domicilio domicilio = new Domicilio();
        domicilio.setCalle("Libertador");
        domicilio.setNumero(3980);
        domicilio.setLocalidad("Nuñez");
        domicilio.setProvincia("Buenos Aires");

        Domicilio domicilio2 = new Domicilio();
        domicilio2.setCalle("España");
        domicilio2.setNumero(3412);
        domicilio2.setLocalidad("San Isidro");
        domicilio2.setProvincia("Buenos Aires");

        Paciente paciente = new Paciente();
        paciente.setNombre("Pedro");
        paciente.setApellido("Gomez");
        paciente.setDni("32332332");
        paciente.setFechaIngreso(LocalDate.parse("2018-08-07"));
        paciente.setDomicilio(domicilio);
        pacienteService.guardar(paciente);

        Paciente paciente2 = new Paciente();
        paciente2.setNombre("Martin");
        paciente2.setApellido("Lopez");
        paciente2.setDni("98998998");
        paciente.setFechaIngreso(LocalDate.parse("2019-08-17"));
        paciente.setDomicilio(domicilio2);
        pacienteService.guardar(paciente2);

    }

    @Test
    @Transactional
    void guardar() {
        Domicilio domicilio = new Domicilio();
        domicilio.setCalle("Pichincha");
        domicilio.setNumero(5990);
        domicilio.setLocalidad("Caballito");
        domicilio.setProvincia("Buenos Aires");

        Paciente paciente = new Paciente();
        paciente.setNombre("Fernando");
        paciente.setApellido("Ramirez");
        paciente.setDni("22777666");
        paciente.setFechaIngreso(LocalDate.parse("2014-10-02"));
        paciente.setDomicilio(domicilio);

        pacienteService.guardar(paciente);

        List<Paciente> pacientes = pacienteService.listarTodos();

        assertNotNull(paciente);
        assertEquals(3,pacientes.size());
        assertEquals(3,paciente.getId());
    }

    @Test
    void listarTodos() {
        List<Paciente> pacientes =  pacienteService.listarTodos();

        assertEquals(2,pacientes.size());
        assertEquals(1,pacientes.get(0).getId());
        assertEquals("32332332",pacientes.get(0).getDni());
        assertEquals(2,pacientes.get(1).getId());
        assertEquals("98998998",pacientes.get(1).getDni());
    }

    @Test
    void buscarPorId() {
        Paciente pacienteBuscado = pacienteService.buscarPorId(2L);

        assertNotNull(pacienteBuscado);
        assertEquals(2,pacienteBuscado.getId());
        assertEquals("Lopez",pacienteBuscado.getApellido());
        assertEquals("98998998",pacienteBuscado.getDni());
    }

    @Test
    @Transactional
    void eliminar() {
        pacienteService.eliminar(1L);
        List<Paciente> pacientes = pacienteService.listarTodos();

        assertEquals(1,pacientes.size());
        assertEquals(2,pacientes.get(0).getId());
        assertEquals("Lopez",pacientes.get(0).getApellido());
        assertEquals("98998998",pacientes.get(0).getDni());
    }

    @Test
    @Transactional
    void actualizar() {
        Paciente nuevoPaciente = new Paciente();
        nuevoPaciente.setId(1L);
        nuevoPaciente.setNombre("Jorge");
        nuevoPaciente.setApellido("Diaz");
        nuevoPaciente.setDni("11222333");

        pacienteService.actualizar(nuevoPaciente);
        List<Paciente> pacientes = pacienteService.listarTodos();

        assertEquals(2,pacientes.size());
        assertEquals(1,pacientes.get(0).getId());
        assertEquals("Jorge",pacientes.get(0).getNombre());
        assertEquals("Diaz",pacientes.get(0).getApellido());
        assertEquals("11222333",pacientes.get(0).getDni());
    }

    @Test
    void buscarPorDni() {
        Optional<Paciente> pacienteBuscado = pacienteService.findByDni("98998998");

        assertNotNull(pacienteBuscado);
        assertEquals(2,pacienteBuscado.get().getId());
        assertEquals("Martin",pacienteBuscado.get().getNombre());
        assertEquals("Lopez",pacienteBuscado.get().getApellido());
        assertEquals("98998998",pacienteBuscado.get().getDni());
    }

    @Test
    void buscarPorNombre() {
        List<Paciente> pacientesEncontrados = pacienteService.findByNombre("Pedro");

        assertEquals(1,pacientesEncontrados.size());
        assertEquals(1,pacientesEncontrados.get(0).getId());
        assertEquals("Pedro",pacientesEncontrados.get(0).getNombre());
        assertEquals("Gomez",pacientesEncontrados.get(0).getApellido());
        assertEquals("32332332",pacientesEncontrados.get(0).getDni());
    }

    @Test
    void buscarPorApellido() {
        List<Paciente> pacientesEncontrados = pacienteService.findByApellido("Gomez");

        assertEquals(1,pacientesEncontrados.size());
        assertEquals(1,pacientesEncontrados.get(0).getId());
        assertEquals("Pedro",pacientesEncontrados.get(0).getNombre());
        assertEquals("Gomez",pacientesEncontrados.get(0).getApellido());
        assertEquals("32332332",pacientesEncontrados.get(0).getDni());
    }
}