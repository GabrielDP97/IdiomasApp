
package com.mycompany.idiomasapp;

/**
 *
 * @author Ángel Gabriel
 */


import java.io.FileReader;
import java.io.FileWriter;
import com.google.gson.Gson;

public class UserProgress {
    private String username;
    private String selectedLanguage;
    private String selectedLevel;
    private String selectedCategory;
    private int score;
    private int totalQuestions;
    private int correctAnswers;

    // Constructor
    public UserProgress(String username) {
        this.username = username;
        this.selectedLanguage = "Inglés"; // Idioma predeterminado
        this.selectedLevel = "Básico";   // Nivel predeterminado
        this.selectedCategory = "Animales"; // Categoría predeterminada
        this.score = 0;
        this.totalQuestions = 0;
        this.correctAnswers = 0;
    }

    // Getters y setters
    public String getUsername() { return username; }
    public String getSelectedLanguage() { return selectedLanguage; }
    public void setSelectedLanguage(String selectedLanguage) { this.selectedLanguage = selectedLanguage; }
    public String getSelectedLevel() { return selectedLevel; }
    public void setSelectedLevel(String selectedLevel) { this.selectedLevel = selectedLevel; }
    public String getSelectedCategory() { return selectedCategory; }
    public void setSelectedCategory(String selectedCategory) { this.selectedCategory = selectedCategory; }
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
    public int getTotalQuestions() { return totalQuestions; }
    public void setTotalQuestions(int totalQuestions) { this.totalQuestions = totalQuestions; }
    public int getCorrectAnswers() { return correctAnswers; }
    public void setCorrectAnswers(int correctAnswers) { this.correctAnswers = correctAnswers; }

    // Métodos para guardar y cargar progreso
    public void saveProgress() {
        try (FileWriter writer = new FileWriter("src/main/resources/progress_" + username + ".json")) {
            Gson gson = new Gson();
            gson.toJson(this, writer);
        } catch (Exception e) {
            System.err.println("Error al guardar el progreso: " + e.getMessage());
        }
    }

    public static UserProgress loadProgress(String username) {
        try (FileReader reader = new FileReader("src/main/resources/progress_" + username + ".json")) {
            Gson gson = new Gson();
            return gson.fromJson(reader, UserProgress.class);
        } catch (Exception e) {
            System.err.println("Error al cargar el progreso: " + e.getMessage());
            return null;
        }
    }
}
