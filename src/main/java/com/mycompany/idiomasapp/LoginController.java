
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

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Label messageLabel;

    // Este método se ejecuta automáticamente cuando se carga el FXML
    @FXML
    public void initialize() {
        messageLabel.setText(""); // Inicializamos la etiqueta de mensajes
    }

    // Método que se ejecuta al hacer clic en el botón
    @FXML
private void handleLogin() {
    String username = usernameField.getText();
    String password = passwordField.getText();

    if ("admin".equals(username) && "1234".equals(password)) {
        try {
            // Cargar la vista principal
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/idiomasapp/MainView.fxml"));
            Parent mainView = loader.load();

            // Obtener el Stage actual y configurar la nueva escena
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.setScene(new Scene(mainView));
            stage.setTitle("Pantalla Principal");
        } catch (Exception e) {
            e.printStackTrace();
        }
    } else {
        messageLabel.setText("Usuario o contraseña incorrectos.");
    }
}

}
