
package com.mycompany.idiomasapp;

/**
 *
 * @author Ángel Gabriel
 */


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StatisticsManager {

    // Método para guardar estadísticas de un usuario
    public static void saveStatistics(String username, int totalPreguntas, int respuestasCorrectas, int puntuacion, String idiomaSeleccionado) {
        String sql = "INSERT INTO estadisticas (username, total_preguntas, respuestas_correctas, puntuacion, idioma_seleccionado) " +
                     "VALUES (?, ?, ?, ?, ?) " +
                     "ON CONFLICT(username) DO UPDATE SET " +
                     "total_preguntas = ?, respuestas_correctas = ?, puntuacion = ?, idioma_seleccionado = ?";

        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setInt(2, totalPreguntas);
            statement.setInt(3, respuestasCorrectas);
            statement.setInt(4, puntuacion);
            statement.setString(5, idiomaSeleccionado);

            // Para el caso de actualización
            statement.setInt(6, totalPreguntas);
            statement.setInt(7, respuestasCorrectas);
            statement.setInt(8, puntuacion);
            statement.setString(9, idiomaSeleccionado);

            statement.executeUpdate();
            System.out.println("Estadísticas guardadas para el usuario: " + username);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al guardar las estadísticas: " + e.getMessage());
        }
    }

    // Método para cargar estadísticas de un usuario
    public static ResultSet loadStatistics(String username) {
        String sql = "SELECT * FROM estadisticas WHERE username = ?";
        try {
            Connection connection = DatabaseConnection.connect();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al cargar las estadísticas: " + e.getMessage());
            return null;
        }
    }
}

