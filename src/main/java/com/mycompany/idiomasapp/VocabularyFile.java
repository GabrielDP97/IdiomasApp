
package com.mycompany.idiomasapp;

/**
 *
 * @author Ángel Gabriel
 */


import java.util.List;

public class VocabularyFile {
    private String idioma;
    private List<WordEntry> palabras;

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public List<WordEntry> getPalabras() {
        return palabras;
    }

    public void setPalabras(List<WordEntry> palabras) {
        this.palabras = palabras;
    }

    public static class WordEntry {
        private String clave;
        private String traduccion;
        private String nivel;

        public String getClave() {
            return clave;
        }

        public void setClave(String clave) {
            this.clave = clave;
        }

        public String getTraduccion() {
            return traduccion;
        }

        public void setTraduccion(String traduccion) {
            this.traduccion = traduccion;
        }

        public String getNivel() {
            return nivel;
        }

        public void setNivel(String nivel) {
            this.nivel = nivel;
        }
    }
}
