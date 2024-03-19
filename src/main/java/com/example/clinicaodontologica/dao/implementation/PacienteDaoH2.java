package com.example.clinicaodontologica.dao.implementation;

import com.example.clinicaodontologica.dao.BD;
import com.example.clinicaodontologica.dao.IDao;
import com.example.clinicaodontologica.model.Domicilio;
import com.example.clinicaodontologica.model.Paciente;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PacienteDaoH2 implements IDao<Paciente> {

    private static final Logger LOGGER = Logger.getLogger(DomicilioDaoH2.class);

    private static final String INSERT_PACIENTES = "INSERT INTO PACIENTES (NOMBRE, APELLIDO, DNI, FECHA_INGRESO, DOMICILIO_ID) VALUES (?,?,?,?,?)";
    private static final String SELECT_ALL = "SELECT * FROM PACIENTES";
    private static final String SQL_SELECT_ID = "SELECT * FROM PACIENTES WHERE ID= ?";

    @Override
    public Paciente guardar(Paciente paciente) {
        LOGGER.info("Estamos persistiendo un paciente");
        Connection connection = null;

        try {
            DomicilioDaoH2 domicilioDaoH2 = new DomicilioDaoH2();
            domicilioDaoH2.guardar(paciente.getDomicilio());

            connection = BD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PACIENTES, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, paciente.getNombre());
            preparedStatement.setString(2, paciente.getApellido());
            preparedStatement.setString(3, paciente.getDni());
            preparedStatement.setDate(4, Date.valueOf(paciente.getFechaIngreso()));
            preparedStatement.setInt(5, paciente.getDomicilio().getId());
            preparedStatement.execute();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            while (rs.next()) {
                paciente.setId(rs.getInt(1));
            }

        } catch (Exception e) {
            LOGGER.error("Error al crear un paciente nuevo:" + e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                LOGGER.error("Error al cerrar la conexión con la base de datos:" + e.getMessage());
            }
        }
        LOGGER.info("Se creo un paciente nuevo");
        return paciente;
    }

    @Override
    public List<Paciente> listarTodos() {
        LOGGER.info("Estamos consultando todos los pacientes");
        Connection connection = null;
        List<Paciente> listaPacientes = new ArrayList<>();
        Paciente paciente = null;

        try {
            connection = BD.getConnection();
            PreparedStatement psSelect = connection.prepareStatement(SELECT_ALL);
            ResultSet rs = psSelect.executeQuery();

            DomicilioDaoH2 domicilioDaoH2 = new DomicilioDaoH2();
            while (rs.next()) {
                Domicilio domicilio = domicilioDaoH2.buscarPorId(rs.getInt(6));
                paciente = new Paciente(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getDate(5).toLocalDate(),domicilio);

                listaPacientes.add(paciente);
            }

        } catch (Exception e) {
            LOGGER.error("Error al consultar todos los pacientes: " + e.getMessage());

            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                LOGGER.error("Error al cerrar la conexión con la base de datos:" + e.getMessage());

            }
        }
        LOGGER.info("Se consultaron todos los pacientes exitosamente");
        return listaPacientes;
    }

    @Override
    public Paciente buscarPorId(Integer id) {
        LOGGER.info("Estamos buscando un paciente por el id: " + id);
        Connection connection = null;
        Paciente paciente = null;
        try {
            connection = BD.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_SELECT_ID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            DomicilioDaoH2 domicilioDaoH2 = new DomicilioDaoH2();
            while (rs.next()) {
                Domicilio domicilio = domicilioDaoH2.buscarPorId(rs.getInt(6));

                paciente = new Paciente(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4),
                        rs.getDate(5).toLocalDate(), domicilio);
            }

        } catch (Exception e) {
            LOGGER.error("Error al buscar un paciente por id:" + e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                LOGGER.error("Error al cerrar la conexión con la base de datos:" + e.getMessage());
            }
        }
        LOGGER.info("Se realizo la busqueda del paciente con id: " + id);
        return paciente;
    }
}
