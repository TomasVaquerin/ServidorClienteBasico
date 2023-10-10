package org.example.servives;

import org.example.repositories.Crud;
import org.example.models.Temperatura;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import java.util.List;

import java.sql.Connection;

public class TemperaturaManjer implements Crud<Temperatura> {
    private Connection connection;
    private static TemperaturaManjer instance;

    private TemperaturaManjer(Connection connection) {
        this.connection=connection;

    }

    public static TemperaturaManjer getInstance(Connection connection) {
        if (instance == null) {
            instance = new TemperaturaManjer(connection);
        }
        return instance;
    }

    @Override
    public void create(Temperatura t) {
        try {
            String sqlQuery = "INSERT INTO TEMPERATURAS (localidad, provincia, tempMax, hsTempMax, tempMin, hsTempMin, precipitacion) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement precompilado = connection.prepareStatement(sqlQuery);
            precompilado.setString(1, t.getLocalidad());
            precompilado.setString(2, t.getProvincia());
            precompilado.setDouble(3, t.getTempMax());
            precompilado.setTimestamp(4, Timestamp.valueOf(String.valueOf(t.getHsTempMax())));
            precompilado.setDouble(5, t.getTempMin());
            precompilado.setTimestamp(6, Timestamp.valueOf(String.valueOf(t.getHsTempMin())));
            precompilado.setDouble(7, t.getPrecipitacion());

            precompilado.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al insertar: " + e.getMessage());
        }
    }

    @Override
    public void read(Temperatura t) {

    }

    @Override
    public void update(Temperatura t) {

    }

    @Override
    public void delete(Temperatura t) {

    }


}
