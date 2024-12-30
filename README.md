
# **IdiomasApp**

IdiomasApp es una aplicación interactiva diseñada para aprender vocabulario en diferentes idiomas. La aplicación permite a los usuarios practicar vocabulario, ver estadísticas de aprendizaje y personalizar su experiencia según el idioma, nivel y categoría seleccionados.

---

## **Características principales**
- Selección de idioma, nivel y categoría de vocabulario.
- Práctica de vocabulario con preguntas de opción múltiple.
- Seguimiento de estadísticas como preguntas respondidas y respuestas correctas.
- Interfaz gráfica amigable usando JavaFX.
- Gestión de usuarios y progreso personalizado.

---

## **Requisitos previos**
1. **Java Development Kit (JDK) 17 o superior**  
   Descarga desde [Oracle](https://www.oracle.com/java/technologies/javase-downloads.html) o usa una alternativa como [OpenJDK](https://openjdk.org/).
2. **Maven**  
   Descarga desde [Maven Downloads](https://maven.apache.org/download.cgi) y asegúrate de que está configurado en tu sistema.
3. **JavaFX SDK**  
   Descarga desde [GluonHQ](https://gluonhq.com/products/javafx/).
4. **SQLite**  
   Asegúrate de tener SQLite instalado para la gestión de la base de datos.

---

## **Configuración inicial**

### **1. Clonar el repositorio**

1. Asegúrate de tener `git` instalado en tu sistema. Si no, puedes descargarlo desde [git-scm.com](https://git-scm.com/).
2. Clona el repositorio en tu máquina local utilizando el siguiente comando en tu terminal o consola:

```bash
git clone https://github.com/tu-usuario/idiomasapp.git
```

3. Navega al directorio del proyecto:

```bash
cd idiomasapp
```

---

### **2. Instalar dependencias**

El proyecto utiliza **Maven** para la gestión de dependencias. Asegúrate de que Maven está instalado en tu sistema:

- Para comprobar si Maven está instalado, ejecuta:
  ```bash
  mvn -version
  ```
- Si no está instalado, puedes descargarlo desde [Maven Downloads](https://maven.apache.org/download.cgi) y seguir las instrucciones de instalación.

Una vez instalado Maven, ejecuta el siguiente comando desde el directorio raíz del proyecto:

```bash
mvn install
```

Este comando:
- Descargará todas las dependencias necesarias especificadas en el archivo `pom.xml`.
- Compilará el proyecto.

---

### **3. Configurar JavaFX**

La aplicación utiliza **JavaFX** como framework para la interfaz gráfica. Necesitarás configurar el entorno para que reconozca el SDK de JavaFX.

1. **Descargar JavaFX SDK**:
   - Descarga la última versión del SDK desde [gluonhq.com](https://gluonhq.com/products/javafx/).
   - Extrae los archivos en una ubicación de tu sistema (por ejemplo: `C:\javafx-sdk-23.0.1`).

2. **Configurar las variables de entorno**:
   - **JAVA_HOME**:
     - Asegúrate de que la variable `JAVA_HOME` apunte a la carpeta donde está instalado tu JDK. Por ejemplo:
       ```
       C:\Program Files\Java\jdk-17
       ```
   - **PATH**:
     - Añade `JAVA_HOME/bin` al `PATH` para que los comandos de Java estén disponibles en la terminal.

3. **Añadir el módulo de JavaFX**:
   - Edita el comando de ejecución para incluir el módulo de JavaFX:
     ```bash
     --module-path "C:\javafx-sdk-23.0.1\lib" --add-modules javafx.controls,javafx.fxml
     ```

---

### **4. Crear la base de datos**

La aplicación utiliza SQLite como base de datos. Asegúrate de que el archivo `usuarios.db` existe en la ruta `src/main/resources`.

1. Verifica que SQLite esté instalado:
   - En Windows, descarga desde [sqlite.org/download.html](https://www.sqlite.org/download.html).
   - En macOS/Linux, instala SQLite con:
     ```bash
     sudo apt install sqlite3 # Para distribuciones basadas en Debian
     ```
   - Comprueba que funciona ejecutando:
     ```
     sqlite3 --version
     ```

2. Crear la base de datos inicial (opcional):
   Si el archivo `usuarios.db` no está presente, la aplicación lo creará automáticamente al iniciar.

---

### **5. Ejecutar la aplicación**

Puedes ejecutar la aplicación de las siguientes maneras:

#### **Opción 1: Usar Maven**
Ejecuta el siguiente comando para iniciar la aplicación directamente desde Maven:
```bash
mvn javafx:run
```

#### **Opción 2: Crear un archivo JAR ejecutable**
1. Empaqueta el proyecto en un archivo JAR ejecutable:
   ```bash
   mvn package
   ```
2. Encuentra el archivo generado en el directorio `target` con un nombre como:
   ```
   idiomasapp-1.0-SNAPSHOT-jar-with-dependencies.jar
   ```
3. Ejecuta el archivo JAR con:
   ```bash
   java --module-path "C:\javafx-sdk-23.0.1\lib" --add-modules javafx.controls,javafx.fxml -jar target/idiomasapp-1.0-SNAPSHOT-jar-with-dependencies.jar
   ```

---

## **Estructura del proyecto**
- `src/main/java`:
  - Contiene el código fuente de la aplicación, dividido en controladores y utilidades.
- `src/main/resources`:
  - Archivos estáticos como vistas FXML, bases de datos y archivos JSON de vocabulario.

---

## **Licencia**
Este proyecto está bajo la licencia MIT. Consulta el archivo `LICENSE` para más detalles.
