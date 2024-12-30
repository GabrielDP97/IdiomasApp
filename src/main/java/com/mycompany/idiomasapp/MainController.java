
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
import javafx.stage.Stage;
import com.mycompany.idiomasapp.AppState;
import java.io.IOException;

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
    private Label languageLabel;

    @FXML
    private Label welcomeLabel; // Agregar referencia al Label para el mensaje de bienvenida

    @FXML
    private String username; // Variable para almacenar el nombre del usuario conectado
    
@FXML
public void initialize() {
    // Obtener el idioma seleccionado desde AppState
    String selectedLanguage = AppState.getSelectedLanguage();

    // Verificar si se seleccionó un idioma
    if (selectedLanguage != null && languageLabel != null) {
        languageLabel.setText("Idioma seleccionado: " + selectedLanguage);
    } else if (languageLabel != null) {
        languageLabel.setText("No se ha seleccionado un idioma.");
    }
}


    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MainView.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
}

    public void setUsername(String username) {
    this.username = username;
    System.out.println("Usuario establecido: " + username); // Línea de depuración
    updateWelcomeMessage();
}


    // Método para actualizar el mensaje de bienvenida
    private void updateWelcomeMessage() {
        if (welcomeLabel != null) {
            if (username != null && !username.isEmpty()) {
                welcomeLabel.setText("Bienvenido a IdiomasApp, " + username + "!");
            } else {
                welcomeLabel.setText("Bienvenido a IdiomasApp!");
            }
        }
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
    loadView("/com/mycompany/idiomasapp/PracticeVocabularyView.fxml", "Practicar Vocabulario");
}


   @FXML
private void handleViewStatistics() {
    loadView("/com/mycompany/idiomasapp/StatisticsView.fxml", "Estadísticas");
}


   @FXML
private void handleLogout() {
    saveUserStatistics(); // Guarda estadísticas antes de cerrar sesión

    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/idiomasapp/LoginView.fxml"));
        Parent loginView = loader.load();
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.setScene(new Scene(loginView));
        stage.setTitle("Inicio de Sesión");
    } catch (IOException e) {
        e.printStackTrace();
        System.err.println("Error al cargar la vista de inicio de sesión.");
    }
}

    public void saveUserStatistics() {
    String username = AppState.getUsername();
    int totalPreguntas = AppState.getTotalQuestions();
    int respuestasCorrectas = AppState.getCorrectAnswers();
    int puntuacion = AppState.getScore();
    String idiomaSeleccionado = AppState.getSelectedLanguage();

    StatisticsManager.saveStatistics(username, totalPreguntas, respuestasCorrectas, puntuacion, idiomaSeleccionado);
}
   private void loadView(String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent newView = loader.load();
            Stage currentStage = (Stage) selectLanguageButton.getScene().getWindow();
            currentStage.setScene(new Scene(newView));
            currentStage.setTitle(title);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al cargar la vista: " + fxmlPath);
        }
    }

}
