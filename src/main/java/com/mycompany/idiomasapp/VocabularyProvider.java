package com.mycompany.idiomasapp;

/**
 *
 * @author Ángel Gabriel
 */

import com.google.gson.Gson;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class VocabularyProvider {

    public static Map<String, String> getVocabulary(String language, String level, String category) {
        Map<String, String> vocabulary = new HashMap<>();
        String fileName = "src/main/resources/vocabulario_" + language.toLowerCase() + ".json";

        try (FileReader reader = new FileReader(fileName)) {
            // Parsear el archivo JSON
            Gson gson = new Gson();
            Map<String, Object> vocabFile = gson.fromJson(reader, Map.class);

            // Validar estructura del JSON
            if (vocabFile == null || !vocabFile.containsKey(level.toLowerCase())) {
                System.err.println("El nivel " + level + " no existe en el archivo JSON.");
                return vocabulary;
            }

            Map<String, Object> levels = (Map<String, Object>) vocabFile.get(level.toLowerCase());
            if (!levels.containsKey(category.toLowerCase())) {
                System.err.println("La categoría " + category + " no existe en el nivel " + level + ".");
                return vocabulary;
            }

            // Obtener las palabras de la categoría
            Map<String, String> categoryWords = (Map<String, String>) levels.get(category.toLowerCase());
            if (categoryWords == null || categoryWords.isEmpty()) {
                System.err.println("No hay palabras disponibles en la categoría " + category + ".");
                return vocabulary;
            }

            vocabulary.putAll(categoryWords);
            System.out.println("Se cargaron " + vocabulary.size() + " palabras para " + language + " (" + level + ", " + category + ")");
        } catch (Exception e) {
            System.err.println("Error al cargar el vocabulario desde " + fileName + ": " + e.getMessage());
        }

        return vocabulary;
    }
}
