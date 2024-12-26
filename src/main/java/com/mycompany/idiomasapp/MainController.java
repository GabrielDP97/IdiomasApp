
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
import javafx.stage.Stage;

public class MainController {

    @FXML
    private Button selectLanguageButton;

    @FXML
    private Button practiceVocabularyButton;

    @FXML
    private Button viewStatisticsButton;

    @FXML
    private Button logoutButton;

    @FXML
    public void initialize() {
        // Aquí puedes inicializar valores o configurar eventos
    }

    @FXML
private void handleSelectLanguage() {
    try {
        // Cargar la vista de selección de idioma
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/idiomasapp/SelectLanguageView.fxml"));
        Parent selectLanguageView = loader.load();

        // Mostrar la nueva escena
        Stage stage = (Stage) selectLanguageButton.getScene().getWindow();
        stage.setScene(new Scene(selectLanguageView));
        stage.setTitle("Seleccionar Idioma");
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    @FXML
    private void handlePracticeVocabulary() {
        System.out.println("Practicar vocabulario seleccionado.");
        // Aquí puedes cargar la vista de práctica de vocabulario
    }

    @FXML
    private void handleViewStatistics() {
        System.out.println("Ver estadísticas seleccionado.");
        // Aquí puedes cargar la vista de estadísticas
    }

    @FXML
    private void handleLogout() {
        System.out.println("Cerrar sesión seleccionado.");
        // Aquí puedes redirigir a la pantalla de inicio de sesión
    }
}
