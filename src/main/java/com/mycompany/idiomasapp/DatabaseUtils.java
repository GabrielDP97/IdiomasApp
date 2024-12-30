
package com.mycompany.idiomasapp;

/**
 *
 * @author Ángel Gabriel
 */


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.fxml.FXML;

public class DatabaseUtils {

    /**
     * Método para inicializar la base de datos, verificando que la tabla de estadísticas exista.
     */
    public static void initializeDatabase() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS estadisticas ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "username TEXT NOT NULL UNIQUE, "
                + "total_preguntas INTEGER DEFAULT 0, " // Cambiado a total_preguntas
                + "respuestas_correctas INTEGER DEFAULT 0, " // Cambiado a respuestas_correctas
                + "FOREIGN KEY (username) REFERENCES usuarios (username)"
                + ");";

        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement statement = connection.prepareStatement(createTableSQL)) {
            statement.execute();
            System.out.println("Tabla 'estadisticas' verificada/creada.");
        } catch (SQLException e) {
            System.err.println("Error al inicializar la base de datos: " + e.getMessage());
        }
    }

    /**
     * Método para guardar o actualizar estadísticas del usuario.
     * @param username Nombre de usuario.
     * @param totalPreguntas Total de preguntas respondidas.
     * @param respuestasCorrectas Total de respuestas correctas.
     */
    public static void saveStatistics(String username, int totalPreguntas, int respuestasCorrectas) {
         int preguntasAAgregar = (totalPreguntas % 2 == 0) ? totalPreguntas / 2 : (totalPreguntas / 2) + 1;
         int correctasAAgregar = (respuestasCorrectas % 2 == 0) ? respuestasCorrectas / 2 : (respuestasCorrectas / 2) + 1;
        String sql = "INSERT INTO estadisticas (username, total_preguntas, respuestas_correctas) "
        + "VALUES (?, ?, ?) "
        + "ON CONFLICT(username) DO UPDATE SET "
        + "total_preguntas = total_preguntas + ?, "
        + "respuestas_correctas = respuestas_correctas + ?;";


        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);
            statement.setInt(2, totalPreguntas);
            statement.setInt(3, respuestasCorrectas);
            statement.setInt(4, preguntasAAgregar);
            statement.setInt(5, correctasAAgregar);

            statement.executeUpdate();
            System.out.println("Estadísticas guardadas/actualizadas para el usuario: " + username);

        } catch (SQLException e) {
            System.err.println("Error al guardar estadísticas: " + e.getMessage());
        }
    }

    /**
     * Método para cargar estadísticas de un usuario.
     * @param username Nombre de usuario.
     * @return Un array con las estadísticas [totalPreguntas, respuestasCorrectas].
     */
    public static int[] loadStatistics(String username) {
        String sql = "SELECT total_preguntas, respuestas_correctas FROM estadisticas WHERE username = ?"; // Cambiado a total_preguntas y respuestas_correctas
        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int totalPreguntas = resultSet.getInt("total_preguntas"); // Cambiado a total_preguntas
                int respuestasCorrectas = resultSet.getInt("respuestas_correctas"); // Cambiado a respuestas_correctas
                System.out.println("Estadísticas cargadas para el usuario: " + username);
                return new int[]{totalPreguntas, respuestasCorrectas};
            }
        } catch (SQLException e) {
            System.err.println("Error al cargar estadísticas: " + e.getMessage());
        }

        // Devolver valores predeterminados si no se encuentran estadísticas
        return new int[]{0, 0};
    }
    
    


}
