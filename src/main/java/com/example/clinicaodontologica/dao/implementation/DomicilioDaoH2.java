package com.example.clinicaodontologica.dao.implementation;

import com.example.clinicaodontologica.dao.BD;
import com.example.clinicaodontologica.dao.IDao;
import com.example.clinicaodontologica.model.Domicilio;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class DomicilioDaoH2 implements IDao<Domicilio> {

    private static final Logger LOGGER = Logger.getLogger(DomicilioDaoH2.class);
    private static final String INSERT_DOMICILIO = "INSERT INTO DOMICILIOS (CALLE, NUMERO, LOCALIDAD, PROVINCIA) VALUES (?,?,?,?)";
    private static final String SELECT_ALL = "SELECT * FROM DOMICILIOS";

    @Override
    public Domicilio guardar(Domicilio domicilio) {
        LOGGER.info("Estamos guardando un domicilio");
        Connection connection = null;

        try {

            connection = BD.getConnection();
            PreparedStatement psInsert = connection.prepareStatement(INSERT_DOMICILIO, Statement.RETURN_GENERATED_KEYS);
            psInsert.setString(1, domicilio.getCalle());
            psInsert.setInt(2, domicilio.getNumero());
            psInsert.setString(3, domicilio.getLocalidad());
            psInsert.setString(4, domicilio.getProvincia());

            psInsert.execute();

            ResultSet resultSet = psInsert.getGeneratedKeys();
            while (resultSet.next()) {
                domicilio.setId(resultSet.getInt(1));
            }

        }catch (Exception e) {
            LOGGER.error("Error al persistir un domicilio: " + e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                LOGGER.error("Error al cerrar la conexión con la base de datos: " + e.getMessage());
            }
        }
        LOGGER.info("Se guardo un nuevo domicilio exitosamente");
        return domicilio;
    }

    @Override
    public List<Domicilio> listarTodos() {
        LOGGER.info("Estamos consultando todos los domicilios");
        Connection connection = null;
        List<Domicilio> domicilioList = new ArrayList<>();
        Domicilio domicilio = null;

        try {
            connection = BD.getConnection();
            PreparedStatement psSelect = connection.prepareStatement(SELECT_ALL);
            ResultSet rs = psSelect.executeQuery();

            while (rs.next()) {
                domicilio = new Domicilio(rs.getInt(1), rs.getString(2), rs.getInt(3),
                        rs.getString(4), rs.getString(5));

                domicilioList.add(domicilio);
            }

        } catch (Exception e) {
            LOGGER.error("Error al consultar todos los domicilios: " + e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                LOGGER.error("Error al cerrar la conexión con la base de datos:" + e.getMessage());
            }
        }
        LOGGER.info("Se consulto exitosamente todos los domicilios");
        return domicilioList;
    }

    @Override
    public Domicilio buscarPorId(Integer id) {
        return null;
    }
}
