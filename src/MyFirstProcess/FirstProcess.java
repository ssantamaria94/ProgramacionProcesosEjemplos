package MyFirstProcess;

import java.io.IOException;

public class FirstProcess {
    public static void main(String[] args) {
        // Crear el proceso con el comando mkdir para crear el directorio "HolaMundo"
        ProcessBuilder processBuilder = new ProcessBuilder("mkdir", "HolaMundo");

        try {
            // Iniciar el proceso
            Process process = processBuilder.start();

            // Esperar a que el proceso termine y obtener el código de salida
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Directorio 'HolaMundo' creado exitosamente.");
            } else {
                System.out.println("Error al crear el directorio. Código de salida: " + exitCode);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
