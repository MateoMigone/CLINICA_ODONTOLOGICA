package com.example.clinicaodontologica.dao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class BD {

    private static final String SQL_CREATE_ODONTOLOGOS = "DROP TABLE IF EXISTS " +
            "ODONTOLOGOS; CREATE TABLE ODONTOLOGOS (ID INT AUTO_INCREMENT PRIMARY KEY," +
            " MATRICULA INT NOT NULL," +
            " NOMBRE VARCHAR(100) NOT NULL," +
            " APELLIDO VARCHAR(100) NOT NULL" +
            ")";

    private static final String SQL_CREATE_DOMICILIOS = "DROP TABLE IF EXISTS " +
            "DOMICILIOS; CREATE TABLE DOMICILIOS (ID INT AUTO_INCREMENT PRIMARY KEY," +
            " CALLE VARCHAR(100) NOT NULL," +
            " NUMERO INT NOT NULL," +
            " LOCALIDAD VARCHAR(100) NOT NULL," +
            " PROVINCIA VARCHAR(100) NOT NULL" +
            ")";

    private static final String SQL_CREATE_PACIENTES = "DROP TABLE IF EXISTS " +
            "PACIENTES; CREATE TABLE PACIENTES (ID INT AUTO_INCREMENT PRIMARY KEY," +
            " NOMBRE VARCHAR(100) NOT NULL," +
            " APELLIDO VARCHAR(100)  NOT NULL," +
            " DNI VARCHAR(100) NOT NULL ," +
            " FECHA_INGRESO DATE NOT NULL," +
            " DOMICILIO_ID INT NOT NULL)";

    private static final String SQL_INSERT_ODONTOLOGOS = "INSERT INTO ODONTOLOGOS (NOMBRE, APELLIDO, MATRICULA) " +
            "VALUES ('Lionel', 'Messi', '123')";
    private static final String SQL_INSERT_DOMICILIOS = "INSERT INTO DOMICILIOS (CALLE, NUMERO, LOCALIDAD, PROVINCIA) " +
            "VALUES ('Av. Libertador', 444, 'Núñez','Buenos Aires')";
    private static final String SQL_INSERT_PACIENTES = "INSERT INTO PACIENTES (NOMBRE, APELLIDO, DNI, FECHA_INGRESO, DOMICILIO_ID) " +
            "VALUES ('Mirtha', 'Legrand', '456','2024-01-14', 1)";

    private static final Logger LOGGER = Logger.getLogger(BD.class);

    public static Connection getConnection() throws Exception {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:./clinicaOdontologica",
                "sa", "sa");
    }

    public static void crearTablas() {
        LOGGER.info("Estamos creando las tablas");
        Connection connection = null;

        try {

            connection = getConnection();
            Statement statement = connection.createStatement();
            statement.execute(SQL_CREATE_ODONTOLOGOS);
            statement.execute(SQL_CREATE_DOMICILIOS);
            statement.execute(SQL_CREATE_PACIENTES);
            statement.execute(SQL_INSERT_ODONTOLOGOS);
            statement.execute(SQL_INSERT_DOMICILIOS);
            statement.execute(SQL_INSERT_PACIENTES);


        } catch (Exception e) {
            LOGGER.error("Error al crear las tablas: " + e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                LOGGER.error("Error al cerrar la conexión con la base de datos: " + e.getMessage());
            }
        }
        LOGGER.info("Se crearon las tablas exitosamente");
    }
}
