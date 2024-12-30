package com.mycompany.idiomasapp;

/**
 *
 * @author Ángel Gabriel
 */
public class AppState {
    private static String selectedLanguage = "Español"; // Idioma predeterminado
    private static String selectedLevel = "Básico";     // Nivel predeterminado
    private static String selectedCategory = "Animales"; // Categoría predeterminada
    private static String username; // Nombre del usuario
    private static int totalQuestions = 0;
    private static int correctAnswers = 0;
    private static int score = 0; // Puntuación
    
    // Getters y Setters para el nombre de usuario
    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        AppState.username = username;
    }

    // Getters y setters para el idioma seleccionado
    public static String getSelectedLanguage() {
        return selectedLanguage;
    }

    public static void setSelectedLanguage(String language) {
        if (language != null && !language.isEmpty()) {
            selectedLanguage = language;
            System.out.println("Idioma seleccionado: " + selectedLanguage); // Debug
        }
    }
    
     // Getter y Setter para totalQuestions
    
    public static int getTotalQuestions() {
        return totalQuestions;
    }

    public static void setTotalQuestions(int totalQuestions) {
        AppState.totalQuestions = totalQuestions;
    }
    
     // Getter y Setter para correctAnswers
    public static int getCorrectAnswers() {
        return correctAnswers;
    }

    public static void setCorrectAnswers(int correctAnswers) {
        AppState.correctAnswers = correctAnswers;
    }

    // Getters y setters para el nivel seleccionado
    public static String getSelectedLevel() {
        return selectedLevel;
    }

    public static void setSelectedLevel(String level) {
        if (level != null && !level.isEmpty()) {
            selectedLevel = level;
            System.out.println("Nivel seleccionado: " + selectedLevel); // Debug
        }
    }

    // Getters y setters para la categoría seleccionada
    public static String getSelectedCategory() {
        return selectedCategory;
    }

    public static void setSelectedCategory(String category) {
        if (category != null && !category.isEmpty()) {
            selectedCategory = category;
            System.out.println("Categoría seleccionada: " + selectedCategory); // Debug
        }
    }

    
    // Añadimos un método para cargar progreso:
public static void loadFromProgress(UserProgress progress) {
    setSelectedLanguage(progress.getSelectedLanguage());
    setSelectedLevel(progress.getSelectedLevel());
    setSelectedCategory(progress.getSelectedCategory());
    resetStats();
    addPoints(progress.getScore());
    totalQuestions = progress.getTotalQuestions();
    correctAnswers = progress.getCorrectAnswers();
}

public static UserProgress exportProgress(String username) {
    UserProgress progress = new UserProgress(username);
    progress.setSelectedLanguage(getSelectedLanguage());
    progress.setSelectedLevel(getSelectedLevel());
    progress.setSelectedCategory(getSelectedCategory());
    progress.setScore(getScore());
    progress.setTotalQuestions(getTotalQuestions());
    progress.setCorrectAnswers(getCorrectAnswers());
    return progress;
}

    

    public static void incrementTotalQuestions() {
        totalQuestions = 1;
        
    }


    public static void incrementCorrectAnswers() {
        correctAnswers = 1;
    }

    public static double getAccuracyPercentage() {
        return totalQuestions > 0 ? (double) correctAnswers / totalQuestions * 100 : 0;
    }

    // Métodos para manejar la puntuación
    public static int getScore() {
        return score;
    }

    public static void addPoints(int points) {
        score += points;
        System.out.println("Puntos añadidos: " + points + ". Puntuación actual: " + score); // Debug
    }

    public static void subtractPoints(int points) {
        score -= points;
        if (score < 0) {
            score = 0;
        }
        System.out.println("Puntos restados: " + points + ". Puntuación actual: " + score); // Debug
    }

    // Método para reiniciar estadísticas y puntuación
    public static void resetStats() {
        totalQuestions = 0;
        correctAnswers = 0;
        score = 0;
        System.out.println("Estadísticas y puntuación reiniciadas."); // Debug
    }
    
    public static void saveUserStatistics(int totalPreguntas, int respuestasCorrectas) {
    String username = getUsername();
    if (username != null && !username.isEmpty()) {
        // Verifica si realmente hay cambios antes de guardar
        int[] currentStats = DatabaseUtils.loadStatistics(username);
        if (currentStats[0] != totalPreguntas || currentStats[1] != respuestasCorrectas) {
            DatabaseUtils.saveStatistics(username, totalPreguntas, respuestasCorrectas);
            System.out.println("Estadísticas guardadas para el usuario: " + username);
        } else {
            System.out.println("No hay cambios en las estadísticas, no se guardaron.");
        }
    }
}


public static int[] loadUserStatistics() {
    String username = getUsername();
    if (username != null && !username.isEmpty()) {
        return DatabaseUtils.loadStatistics(username);
    }
    return new int[] { 0, 0 };
}


    // Método para imprimir el estado actual (útil para depuración)
    public static void printState() {
        System.out.println("Idioma: " + selectedLanguage);
        System.out.println("Nivel: " + selectedLevel);
        System.out.println("Categoría: " + selectedCategory);
        System.out.println("Total de preguntas: " + totalQuestions);
        System.out.println("Respuestas correctas: " + correctAnswers);
        System.out.println("Puntuación: " + score);
    }
}
