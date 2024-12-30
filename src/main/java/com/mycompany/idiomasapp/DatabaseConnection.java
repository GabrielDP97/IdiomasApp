
package com.mycompany.idiomasapp;

/**
 *
 * @author Ángel Gabriel
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static final String DB_URL = "jdbc:sqlite:src/main/resources/usuarios.db";

    public static Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL);
            System.out.println("Conexión a SQLite establecida.");
            createTables(connection); // Crear tablas si no existen
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
        }
        return connection;
    }

    private static void createTables(Connection connection) {
        String createUsersTable = """
            CREATE TABLE IF NOT EXISTS usuarios (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                username TEXT UNIQUE NOT NULL,
                password TEXT NOT NULL
            );
        """;

        try (Statement statement = connection.createStatement()) {
            statement.execute(createUsersTable);
            System.out.println("Tabla 'usuarios' verificada/creada.");
        } catch (SQLException e) {
            System.err.println("Error al crear la tabla: " + e.getMessage());
        }
    }
}
