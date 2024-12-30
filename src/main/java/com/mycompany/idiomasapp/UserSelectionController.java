
package com.mycompany.idiomasapp;

/**
 *
 * @author Ángel Gabriel
 */

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

public class UserSelectionController {

    @FXML
    private TextField usernameField;

    @FXML
    private Button loadButton;

    @FXML
    private Button newButton;

    @FXML
    private void handleLoad() {
        String username = usernameField.getText();
        if (username.isEmpty()) {
            System.err.println("Por favor, introduce un nombre de usuario.");
            return;
        }
        UserProgress progress = UserProgress.loadProgress(username);
        if (progress != null) {
            AppState.loadFromProgress(progress);
            navigateToMainView();
        } else {
            System.err.println("Progreso no encontrado para el usuario: " + username);
        }
    }

    @FXML
    private void handleNew() {
        String username = usernameField.getText();
        if (username.isEmpty()) {
            System.err.println("Por favor, introduce un nombre de usuario.");
            return;
        }
        UserProgress progress = new UserProgress(username);
        AppState.loadFromProgress(progress);
        progress.saveProgress(); // Crear un archivo inicial para el nuevo usuario
        navigateToMainView();
    }

    private void navigateToMainView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/idiomasapp/MainView.fxml"));
            Parent mainView = loader.load();
            Stage stage = (Stage) loadButton.getScene().getWindow();
            stage.setScene(new Scene(mainView));
            stage.setTitle("Pantalla Principal");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
