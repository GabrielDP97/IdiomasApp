
package com.mycompany.idiomasapp;

/**
 *
 * @author Ángel Gabriel
 */


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import com.mycompany.idiomasapp.AppState;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.Alert;

public class StatisticsController {

    @FXML
    private Label totalQuestionsLabel;

    @FXML
    private Label correctAnswersLabel;

    @FXML
    private Label accuracyLabel;
    
    private boolean isReset = false;

@FXML
public void initialize() {
    // Obtener el nombre del usuario actual desde AppState
    String username = AppState.getUsername();
    
    

    if (username == null || username.isEmpty()) {
        // Caso en el que no se encuentre un usuario en AppState
        showAlert("Error", "No se encontró un usuario activo para cargar las estadísticas.");
        totalQuestionsLabel.setText("Preguntas Totales: 0");
        correctAnswersLabel.setText("Respuestas Correctas: 0");
        accuracyLabel.setText("Porcentaje de Acierto: 0.00%");
        return;
        
        
    }
    
    if(isReset){
         
         try (Connection connection = DatabaseConnection.connect()) {
        // Consulta SQL para resetear estadísticas
        String resetSql = "UPDATE estadisticas SET total_preguntas = 0, respuestas_correctas = 0 WHERE username = ?";
        PreparedStatement resetStatement = connection.prepareStatement(resetSql);
        resetStatement.setString(1, username);

        int rowsAffected = resetStatement.executeUpdate(); // Número de filas afectadas
        if (rowsAffected > 0) {
            System.out.println("Estadísticas reseteadas en la base de datos para el usuario: " + username);
        } else {
            System.err.println("No se encontraron estadísticas para resetear en la base de datos para el usuario: " + username);
        }
    } catch (SQLException e) {
        System.err.println("Error al resetear estadísticas en la base de datos: " + e.getMessage());
    }
    }

    try (Connection connection = DatabaseConnection.connect()) {
        // Consulta SQL para obtener las estadísticas del usuario
        String sql = "SELECT total_preguntas, respuestas_correctas FROM estadisticas WHERE username = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            // Extraer estadísticas de la base de datos
            int totalQuestions = resultSet.getInt("total_preguntas");
            int correctAnswers = resultSet.getInt("respuestas_correctas");

            double accuracyPercentage = (totalQuestions > 0) 
                    ? (correctAnswers * 100.0 / totalQuestions) 
                    : 0.0;

            // Actualizar etiquetas con las estadísticas cargadas
            totalQuestionsLabel.setText("Preguntas Totales: " + totalQuestions);
            correctAnswersLabel.setText("Respuestas Correctas: " + correctAnswers);
            accuracyLabel.setText(String.format("Porcentaje de Acierto: %.2f%%", accuracyPercentage));

            // Actualizar AppState para mantener los datos sincronizados
            AppState.setTotalQuestions(totalQuestions);
            AppState.setCorrectAnswers(correctAnswers);
        } else {
            // Caso en el que no se encuentren estadísticas para el usuario
            totalQuestionsLabel.setText("Preguntas Totales: 0");
            correctAnswersLabel.setText("Respuestas Correctas: 0");
            accuracyLabel.setText("Porcentaje de Acierto: 0.00%");
        }
    } catch (SQLException e) {
        // Mostrar un mensaje de error en caso de fallo al acceder a la base de datos
        showAlert("Error", "No se pudieron cargar las estadísticas: " + e.getMessage());
        e.printStackTrace();

        // Establecer valores predeterminados en caso de error
        totalQuestionsLabel.setText("Preguntas Totales: 0");
        correctAnswersLabel.setText("Respuestas Correctas: 0");
        accuracyLabel.setText("Porcentaje de Acierto: 0.00%");
    }
}

// Método auxiliar para mostrar alertas
private void showAlert(String title, String message) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}


    
    @FXML
    private void handleBack() {
        // Volver a la pantalla principal
        try {
            Stage stage = (Stage) totalQuestionsLabel.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/idiomasapp/MainView.fxml"));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void handleResetStatistics() {
    // Restablecer estadísticas en AppState
    AppState.resetStats();

    // Restablecer estadísticas en la base de datos
    String username = AppState.getUsername();
    
     totalQuestionsLabel.setText("Preguntas Totales: 0");
     correctAnswersLabel.setText("Respuestas Correctas: 0");
     accuracyLabel.setText("Porcentaje de Acierto: 0.00%");
    if (username != null && !username.isEmpty()) {
        DatabaseUtils.saveStatistics(username, 0, 0);
        totalQuestionsLabel.setText("Preguntas Totales: 0");
        correctAnswersLabel.setText("Respuestas Correctas: 0");
        accuracyLabel.setText("Porcentaje de Acierto: 0.00%");
        isReset = true;
        initialize(); // Recargar las estadísticas en la interfaz
        showAlert("Éxito", "Las estadísticas han sido reiniciadas.");
    } else {
        showAlert("Error", "No se pudo reiniciar las estadísticas. Usuario no autenticado.");
    }
}
}

