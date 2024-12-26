
package com.mycompany.idiomasapp;
/**
 *
 * @author Ángel Gabriel
 */


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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

        // Lógica de autenticación básica
        if ("admin".equals(username) && "1234".equals(password)) {
            messageLabel.setText("¡Inicio de sesión exitoso!");
        } else {
            messageLabel.setText("Usuario o contraseña incorrectos.");
        }
    }
}
