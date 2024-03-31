package com.example.clinicaodontologica.service.implementation;


import com.example.clinicaodontologica.dto.request.TurnoRequestDTO;
import com.example.clinicaodontologica.dto.response.TurnoResponseDTO;
import com.example.clinicaodontologica.entity.Odontologo;
import com.example.clinicaodontologica.entity.Paciente;
import com.example.clinicaodontologica.entity.Turno;
import com.example.clinicaodontologica.repository.ITurnoRepository;
import com.example.clinicaodontologica.service.ITurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService implements ITurnoService {

    private ITurnoRepository turnoRepository;

    @Autowired
    public TurnoService(ITurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }


    @Override
    public TurnoResponseDTO guardar(TurnoRequestDTO turnoRequestDTO) {
        Turno turnoEntity = new Turno();

        Paciente paciente = new Paciente();
        paciente.setId(turnoRequestDTO.getPaciente_id());

        Odontologo odontologo = new Odontologo();
        odontologo.setId(turnoRequestDTO.getOdontologo_id());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(turnoRequestDTO.getFecha(), formatter);

        turnoEntity.setOdontologo(odontologo);
        turnoEntity.setPaciente(paciente);
        turnoEntity.setFecha(date);

        turnoRepository.save(turnoEntity);

        TurnoResponseDTO turnoResponseDTO = new TurnoResponseDTO();
        turnoResponseDTO.setId(turnoEntity.getId());
        turnoResponseDTO.setOdontologo_id(turnoEntity.getOdontologo().getId());
        turnoResponseDTO.setPaciente_id(turnoEntity.getPaciente().getId());
        turnoResponseDTO.setFecha(turnoEntity.getFecha().toString());

        return turnoResponseDTO;
    }

    @Override
    public List<Turno> listarTodos() {
        return turnoRepository.findAll();
    }

    @Override
    public Turno buscarPorId(Long id) {
        Optional<Turno> turnoOptional = turnoRepository.findById(id);
        if (turnoOptional.isPresent()) {
            return turnoOptional.get();
        }
        return null;
    }

    @Override
    public void eliminar(Long id) {
        turnoRepository.deleteById(id);
    }

    @Override
    public void actualizar(TurnoResponseDTO turnoResponseDTO) {
        Turno turnoEntity = new Turno();

        Paciente paciente = new Paciente();
        paciente.setId(turnoResponseDTO.getPaciente_id());

        Odontologo odontologo = new Odontologo();
        odontologo.setId(turnoResponseDTO.getOdontologo_id());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(turnoResponseDTO.getFecha(), formatter);

        turnoEntity.setId(turnoResponseDTO.getId());
        turnoEntity.setOdontologo(odontologo);
        turnoEntity.setPaciente(paciente);
        turnoEntity.setFecha(date);

        turnoRepository.save(turnoEntity);
    }
}
