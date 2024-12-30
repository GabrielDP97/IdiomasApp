
package com.mycompany.idiomasapp;

/**
 *
 * @author Ángel Gabriel
 */

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import java.util.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PracticeVocabularyController {

    @FXML
    private Label wordLabel;
    @FXML
    private Button option1Button, option2Button, option3Button, option4Button;
    @FXML
    private Label feedbackLabel, timerLabel, scoreLabel;
    @FXML
    private Button backButton;

    private boolean isAnswered = false;

    private Timeline timer;
    private int timeRemaining = 30;

    private Map<String, String> vocabulary;
    private List<String> usedWords = new ArrayList<>();
    private String correctAnswer;

    @FXML
    public void initialize() {
        String selectedLanguage = AppState.getSelectedLanguage();
        String selectedLevel = AppState.getSelectedLevel();
        String selectedCategory = AppState.getSelectedCategory();

        vocabulary = VocabularyProvider.getVocabulary(selectedLanguage, selectedLevel, selectedCategory);
        if (vocabulary == null || vocabulary.isEmpty()) {
            feedbackLabel.setText("No hay palabras disponibles para practicar.");
            disableButtons();
        } else {
            AppState.resetStats();
            updateScoreLabel();
            loadNextWord();
        }
    }

    private void disableButtons() {
        option1Button.setDisable(true);
        option2Button.setDisable(true);
        option3Button.setDisable(true);
        option4Button.setDisable(true);
        backButton.setDisable(false);
    }

    private void startTimer() {
        timeRemaining = 30;
        timerLabel.setText("Tiempo: " + timeRemaining + "s");

        if (timer != null) timer.stop();
        timer = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            if (isAnswered) {
                timer.stop();
                return;
            }

            timeRemaining--;
            timerLabel.setText("Tiempo: " + timeRemaining + "s");

            if (timeRemaining <= 0) {
                timer.stop();
                feedbackLabel.setText("¡Tiempo agotado!");
                
                isAnswered = true;
                loadNextWord();
            }
        }));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }

    private void loadNextWord() {
        if (vocabulary.isEmpty() || vocabulary.keySet().size() == usedWords.size()) {
            feedbackLabel.setText("No hay más palabras disponibles.");
            disableButtons();
            return;
        }

        isAnswered = false;

        List<String> keys = new ArrayList<>(vocabulary.keySet());
        String randomWord;

        do {
            randomWord = keys.get(new Random().nextInt(keys.size()));
        } while (usedWords.contains(randomWord));

        usedWords.add(randomWord);
        wordLabel.setText(randomWord);
        correctAnswer = vocabulary.get(randomWord);

        List<String> options = new ArrayList<>(List.of(correctAnswer));
        while (options.size() < 4) {
            String randomOption = vocabulary.get(keys.get(new Random().nextInt(keys.size())));
            if (!options.contains(randomOption)) {
                options.add(randomOption);
            }
        }
        Collections.shuffle(options);

        option1Button.setText(options.get(0));
        option2Button.setText(options.get(1));
        option3Button.setText(options.get(2));
        option4Button.setText(options.get(3));

        startTimer();
    }

    private void updateScoreLabel() {
        scoreLabel.setText("Puntuación: " + AppState.getScore());
    }

    private void checkAnswer(String selectedAnswer) {
        if (isAnswered) return;

        isAnswered = true;
        if (timer != null) timer.stop();

        AppState.incrementTotalQuestions();

        if (selectedAnswer.equals(correctAnswer)) {
            AppState.incrementCorrectAnswers();
            AppState.addPoints(10);
            feedbackLabel.setText("¡Correcto!");
        } else {
            AppState.subtractPoints(5);
            feedbackLabel.setText("Incorrecto. La respuesta correcta es: " + correctAnswer);
        }

        String username = AppState.getUsername();
    if (username != null) {
        saveUserStatistics(username);
    }

        updateScoreLabel();
        loadNextWord();
    }

    private void saveUserStatistics(String username) {
        if (username != null) {
            DatabaseUtils.saveStatistics(username, AppState.getTotalQuestions(), AppState.getCorrectAnswers());
        }
    }

    @FXML
    private void handleOption1() {
        checkAnswer(option1Button.getText());
    }

    @FXML
    private void handleOption2() {
        checkAnswer(option2Button.getText());
    }

    @FXML
    private void handleOption3() {
        checkAnswer(option3Button.getText());
    }

    @FXML
    private void handleOption4() {
        checkAnswer(option4Button.getText());
    }

    @FXML
    private void handleBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/idiomasapp/MainView.fxml"));
            Parent mainView = loader.load();
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(new Scene(mainView));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
        @FXML
private void handleNext() {
    loadNextWord(); // Lógica para cargar la siguiente palabra
}
}
