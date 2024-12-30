
package com.mycompany.idiomasapp;
/**
 *
 * @author Ángel Gabriel
 */


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.mycompany.idiomasapp.AppState;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Label messageLabel;
    
    @FXML
    private Button newUserButton;


    // Este método se ejecuta automáticamente cuando se carga el FXML
    @FXML
    public void initialize() {
        messageLabel.setText(""); // Inicializamos la etiqueta de mensajes
    }
    
   @FXML
private void handleNewUser() {
    try {
        // Cargar la vista de creación de usuario
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/idiomasapp/NewUserView.fxml"));
        Parent newUserView = loader.load();

        // Configurar la nueva escena
        Stage stage = (Stage) newUserButton.getScene().getWindow();
        stage.setScene(new Scene(newUserView));
        stage.setTitle("Crear Nuevo Usuario");
    } catch (IOException e) {
        e.printStackTrace();
        System.err.println("Error al cargar la vista de creación de usuario.");
    }
}



    // Método que se ejecuta al hacer clic en el botón
    @FXML
public void handleLogin() {
    String username = usernameField.getText();
    String password = passwordField.getText();

    if (username.isEmpty() || password.isEmpty()) {
        showAlert("Error", "Por favor, introduce un nombre de usuario y contraseña.");
        return;
    }

    try (Connection connection = DatabaseConnection.connect()) {
        String sql = "SELECT * FROM usuarios WHERE username = ? AND password = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            // Establece el nombre de usuario en AppState
            AppState.setUsername(username);
            showAlert("Éxito", "Inicio de sesión exitoso.");

            // Cambiar a la pantalla principal
            navigateToDashboard(username);
        } else {
            showAlert("Error", "Nombre de usuario o contraseña incorrectos.");
        }
    } catch (SQLException e) {
        showAlert("Error", "Error al verificar el usuario: " + e.getMessage());
    }
}

   private void navigateToDashboard(String username) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/idiomasapp/MainView.fxml"));
        Parent root = loader.load();

        // Pasar el nombre del usuario al controlador principal
        MainController controller = loader.getController();
        controller.setUsername(username);

        // Cambiar la escena
        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Menú Principal");
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
        showAlert("Error", "Error al cargar la pantalla principal: " + e.getMessage());
    }
}



    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}


