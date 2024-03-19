package com.example.clinicaodontologica.dao.implementation;

import com.example.clinicaodontologica.dao.BD;
import com.example.clinicaodontologica.dao.IDao;
import com.example.clinicaodontologica.model.Odontologo;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class OdontologoDaoH2 implements IDao<Odontologo> {

    private static final Logger LOGGER = Logger.getLogger(OdontologoDaoH2.class);
    private static final String INSERT_ODONTOLOGO = "INSERT INTO ODONTOLOGOS (MATRICULA, NOMBRE, APELLIDO) VALUES (?,?,?)";
    private static final String SELECT_ALL = "SELECT * FROM ODONTOLOGOS";
    private static final String SELECT_BY_ID = "SELECT * FROM ODONTOLOGOS WHERE ID = ?";

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        LOGGER.info("Estamos persistiendo un odontologo");
        Connection connection = null;

        try {

            connection = BD.getConnection();
            PreparedStatement psInsert = connection.prepareStatement(INSERT_ODONTOLOGO, Statement.RETURN_GENERATED_KEYS);
            psInsert.setInt(1, odontologo.getMatricula());
            psInsert.setString(2, odontologo.getNombre());
            psInsert.setString(3, odontologo.getApellido());

            psInsert.execute();

            ResultSet rs = psInsert.getGeneratedKeys();
            while (rs.next()) {
                odontologo.setId(rs.getInt(1));
            }

        }catch (Exception e) {
            LOGGER.error("Error al persistir un odontologo: " + e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                LOGGER.error("Error al cerrar la conexión con la base de datos:" + e.getMessage());
            }
        }

        LOGGER.info("Se persistio un nuevo odontologo exitosamente");
        return odontologo;

    }

    @Override
    public List<Odontologo> listarTodos() {
        LOGGER.info("Estamos consultando todos los odontologos");
        Connection connection = null;
        List<Odontologo> listaOdontologos = new ArrayList<>();
        Odontologo odontologo = null;

        try {
            connection = BD.getConnection();
            PreparedStatement psSelect = connection.prepareStatement(SELECT_ALL);
            ResultSet rs = psSelect.executeQuery();

            while (rs.next()) {
                odontologo = new Odontologo(rs.getInt(1), rs.getInt(2), rs.getString(3),
                        rs.getString(4));

                listaOdontologos.add(odontologo);
            }

        } catch (Exception e) {
            LOGGER.error("Error al consultar todos los odontologos: " + e.getMessage());

            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                LOGGER.error("Error al cerrar la conexión con la base de datos:" + e.getMessage());

            }
        }
        LOGGER.info("Se consultaron todos los odontologos exitosamente");
        return listaOdontologos;
    }

    @Override
    public Odontologo buscarPorId(Integer id) {
        LOGGER.info("Estamos buscando un odontologo por el id: " + id);
        Connection connection = null;
        Odontologo odontologo = null;
        try {
            connection = BD.getConnection();
            PreparedStatement psSearchByID =  connection.prepareStatement(SELECT_BY_ID);
            psSearchByID.setInt(1, id);
            ResultSet rs  = psSearchByID.executeQuery();

            while (rs.next()){
                odontologo = new Odontologo();
                odontologo.setId(rs.getInt(1));
                odontologo.setMatricula(rs.getInt(2));
                odontologo.setNombre(rs.getString(3));
                odontologo.setApellido(rs.getString(4));
            }

        }catch (Exception e){
            LOGGER.error("Error al buscar odontologo por id: " + e.getMessage());
        }finally {
            try {
                connection.close();
            } catch (Exception e) {
                LOGGER.error("Error al cerrar la conexión con la base de datos:" + e.getMessage());
            }
        }
        LOGGER.info("Se realizo una busqueda de odontologo por su id: " + id);
        return odontologo;
    }
}
