
package com.mycompany.idiomasapp;

/**
 *
 * @author Ángel Gabriel
 */



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewUserController {

    @FXML
    private TextField newUsernameField;

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private Button createUserButton;

    @FXML
    private Button backButton;

    @FXML
   public void handleCreateUser() {
        String username = newUsernameField.getText();
        String password = newPasswordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Error", "El nombre de usuario y la contraseña no pueden estar vacíos.");
            return;
        }

        try (Connection connection = DatabaseConnection.connect()) {
            String sql = "INSERT INTO usuarios (username, password) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.executeUpdate();

            showAlert("Éxito", "Usuario creado correctamente.");
            newUsernameField.clear();
            newPasswordField.clear();
        } catch (SQLException e) {
            if (e.getMessage().contains("UNIQUE constraint failed")) {
                showAlert("Error", "El nombre de usuario ya existe.");
            } else {
                showAlert("Error", "Error al crear el usuario: " + e.getMessage());
            }
        }
    }
   
        private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void handleBack(ActionEvent event) {
        try {
            // Cargar la vista de inicio de sesión
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/idiomasapp/LoginView.fxml"));
            Parent loginView = loader.load();

            // Obtener la ventana actual y establecer la nueva escena
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(loginView));
            stage.setTitle("Inicio de Sesión");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al cargar la vista de inicio de sesión: " + e.getMessage());
        }
    }
}
