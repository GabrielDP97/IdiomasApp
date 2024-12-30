package com.mycompany.idiomasapp;

/**
 *
 * @author Ángel Gabriel
 */

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class SelectLanguageController {

    @FXML
    private ComboBox<String> languageComboBox;

    @FXML
    private ComboBox<String> levelComboBox;

    @FXML
    private ComboBox<String> categoryComboBox;

    @FXML
    private Button acceptButton;

    @FXML
    private Label messageLabel;

    @FXML
    public void initialize() {
        // Opciones para el idioma
        languageComboBox.getItems().addAll("Inglés", "Francés", "Alemán");

        // Opciones para el nivel
        levelComboBox.getItems().addAll("Básico", "Intermedio", "Avanzado");

        // Opciones para las categorías (estas son fijas y comunes a todos los idiomas)
        categoryComboBox.getItems().addAll("Animales", "Colores", "Comida", "Ropa");
    }

    @FXML
    private void handleAccept() {
        // Validar que el usuario seleccionó todas las opciones necesarias
        String selectedLanguage = languageComboBox.getValue();
        String selectedLevel = levelComboBox.getValue();
        String selectedCategory = categoryComboBox.getValue();

        if (selectedLanguage == null || selectedLevel == null || selectedCategory == null) {
            messageLabel.setText("Por favor, selecciona idioma, nivel y categoría.");
            return;
        }

        // Guardar el idioma, nivel y categoría seleccionados en AppState
        AppState.setSelectedLanguage(selectedLanguage);
        AppState.setSelectedLevel(selectedLevel);
        AppState.setSelectedCategory(selectedCategory);

        // Reiniciar estadísticas y puntuación
        AppState.resetStats();

        // Navegar a la pantalla principal
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/idiomasapp/MainView.fxml"));
            Parent mainView = loader.load();
            Stage stage = (Stage) acceptButton.getScene().getWindow();
            stage.setScene(new Scene(mainView));
            stage.setTitle("Pantalla Principal");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
