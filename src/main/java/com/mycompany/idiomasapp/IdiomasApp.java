
package com.mycompany.idiomasapp;

/**
 *
 * @author Ángel Gabriel
 */


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.mycompany.idiomasapp.AppState;

public class IdiomasApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Cargar el archivo FXML
        Parent root = FXMLLoader.load(getClass().getResource("/com/mycompany/idiomasapp/LoginView.fxml"));

        // Configurar la escena
        Scene scene = new Scene(root);

        // Configurar el Stage (ventana)
        primaryStage.setTitle("Aprendizaje de Idiomas");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args); // Inicia la aplicación JavaFX
    }
}


