package inheritIOExample;

import java.io.IOException;

public class lsExample {
    public static void main(String[] args) {
        // Crear un ProcessBuilder para ejecutar el comando "ls"
        ProcessBuilder processBuilder = new ProcessBuilder("ls");

        // Usar inheritIO() para que la salida del proceso se muestre en la consola actual
        processBuilder.inheritIO();

        try {
            // Iniciar el proceso
            Process process = processBuilder.start();

            // Esperar a que el proceso termine
            int exitCode = process.waitFor();
            System.out.println("Proceso terminado con código de salida: " + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
