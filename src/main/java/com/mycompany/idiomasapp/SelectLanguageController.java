package com.mycompany.idiomasapp;

/**
 *
 * @author Ángel Gabriel
 */


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class SelectLanguageController {

    @FXML
    private ComboBox<String> languageComboBox;

    @FXML
    private Button acceptButton;

    @FXML
    private Label messageLabel;

    private String selectedLanguage;

    @FXML
    public void initialize() {
        // Inicializar la lista de idiomas
        languageComboBox.getItems().addAll("Español", "Inglés", "Francés", "Alemán", "Italiano");
        messageLabel.setText("");
    }

    @FXML
    private void handleAccept() {
        // Obtener el idioma seleccionado
        selectedLanguage = languageComboBox.getValue();

        if (selectedLanguage == null) {
            messageLabel.setText("Por favor, selecciona un idioma.");
        } else {
            messageLabel.setText("Idioma seleccionado: " + selectedLanguage);
            System.out.println("Idioma seleccionado: " + selectedLanguage);

            // Aquí puedes redirigir a otra pantalla o guardar la selección
        }
    }

    public String getSelectedLanguage() {
        return selectedLanguage;
    }
}

