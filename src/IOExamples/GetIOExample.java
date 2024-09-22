package IOExamples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GetIOExample {
    // Función que ejecuta el comando "ls" sobre un directorio
    public static void listarDirectorio(String directorio) {
        // Crear el proceso para ejecutar el comando "ls"
        ProcessBuilder processBuilder = new ProcessBuilder("ls", directorio);

        try {
            // Iniciar el proceso
            Process process = processBuilder.start();

            // Capturar la salida estándar del proceso
            BufferedReader stdOutput = new BufferedReader(new InputStreamReader(process.getInputStream()));
            // Capturar la salida de error del proceso
            BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            // Leer e imprimir la salida estándar
            System.out.println("Salida estándar:");
            String line;
            while ((line = stdOutput.readLine()) != null) {
                System.out.println(line);
            }

            // Leer e imprimir la salida de error (si la hay)
            System.out.println("Salida de error:");
            while ((line = stdError.readLine()) != null) {
                System.out.println(line);
            }

            // Esperar a que el proceso termine
            int exitCode = process.waitFor();
            System.out.println("Proceso terminado con código de salida: " + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        listarDirectorio("/home");
        listarDirectorio("/xoxo");
    }
}
