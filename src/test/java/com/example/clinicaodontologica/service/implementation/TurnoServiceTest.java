package com.example.clinicaodontologica.service.implementation;

import com.example.clinicaodontologica.dto.request.TurnoRequestDTO;
import com.example.clinicaodontologica.dto.response.TurnoResponseDTO;
import com.example.clinicaodontologica.entity.Domicilio;
import com.example.clinicaodontologica.entity.Odontologo;
import com.example.clinicaodontologica.entity.Paciente;
import com.example.clinicaodontologica.entity.Turno;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class TurnoServiceTest {

    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private TurnoService turnoService;

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

        TurnoRequestDTO turnoRequestDTO = new TurnoRequestDTO();
        turnoRequestDTO.setOdontologo_id(odontologo.getId());
        turnoRequestDTO.setPaciente_id(paciente.getId());
        turnoRequestDTO.setFecha("2020-02-20");
        turnoService.guardar(turnoRequestDTO);

        TurnoRequestDTO turnoRequestDTO2 = new TurnoRequestDTO();
        turnoRequestDTO2.setOdontologo_id(odontologo2.getId());
        turnoRequestDTO2.setPaciente_id(paciente2.getId());
        turnoRequestDTO2.setFecha("2021-04-25");
        turnoService.guardar(turnoRequestDTO2);

    }

    @Test
    @Transactional
    void guardar() {
        Odontologo odontologo = odontologoService.buscarPorId(1L);
        Paciente paciente = pacienteService.buscarPorId(2L);

        TurnoRequestDTO turnoRequestDTO = new TurnoRequestDTO();
        turnoRequestDTO.setOdontologo_id(odontologo.getId());
        turnoRequestDTO.setPaciente_id(paciente.getId());
        turnoRequestDTO.setFecha("2023-09-09");

        turnoService.guardar(turnoRequestDTO);

        List<Turno> turnos =  turnoService.listarTodos();

        assertEquals(3,turnos.size());
        assertEquals(3,turnos.get(2).getId());
        assertEquals(1,turnos.get(2).getOdontologo().getId());
        assertEquals(2,turnos.get(2).getPaciente().getId());
    }

    @Test
    void listarTodos() {
        List<Turno> turnos =  turnoService.listarTodos();

        assertEquals(2,turnos.size());
        assertEquals(1,turnos.get(0).getId());
        assertEquals(1,turnos.get(0).getOdontologo().getId());
        assertEquals(1,turnos.get(0).getPaciente().getId());
        assertEquals(2,turnos.get(1).getId());
        assertEquals(2,turnos.get(1).getOdontologo().getId());
        assertEquals(2,turnos.get(1).getPaciente().getId());
    }

    @Test
    void buscarPorId() {
        Turno turnoBuscado = turnoService.buscarPorId(2L);

        assertNotNull(turnoBuscado);
        assertEquals(2,turnoBuscado.getId());
        assertEquals(2,turnoBuscado.getOdontologo().getId());
        assertEquals(2,turnoBuscado.getPaciente().getId());
    }

    @Test
    @Transactional
    void eliminar() {
        turnoService.eliminar(1L);
        List<Turno> turnos = turnoService.listarTodos();

        assertEquals(1,turnos.size());
        assertEquals(2,turnos.get(0).getId());
        assertEquals(2,turnos.get(0).getId());
        assertEquals(2,turnos.get(0).getId());
    }

    @Test
    @Transactional
    void actualizar() {
        Odontologo odontologo = odontologoService.buscarPorId(1L);
        Paciente paciente = pacienteService.buscarPorId(2L);

        TurnoResponseDTO turnoResponseDTO = new TurnoResponseDTO();
        turnoResponseDTO.setId(1L);
        turnoResponseDTO.setOdontologo_id(odontologo.getId());
        turnoResponseDTO.setPaciente_id(paciente.getId());
        turnoResponseDTO.setFecha("2024-01-18");

        turnoService.actualizar(turnoResponseDTO);

        List<Turno> turnos =  turnoService.listarTodos();

        assertEquals(2,turnos.size());
        assertEquals(1,turnos.get(0).getId());
        assertEquals(1,turnos.get(0).getOdontologo().getId());
        assertEquals(2,turnos.get(0).getPaciente().getId());
    }
}