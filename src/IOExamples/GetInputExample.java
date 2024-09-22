package IOExamples;

import java.io.*;

public class GetInputExample {
    public static void escribirEnProcesoCat(String inputText) {
        // Crear el proceso para ejecutar el comando "cat"
        ProcessBuilder processBuilder = new ProcessBuilder("cat");

        try {
            // Iniciar el proceso
            Process process = processBuilder.start();

            // Escribir en la entrada del proceso (stdin) usando PrintWriter
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(process.getOutputStream()), true);
            writer.println(inputText); // Escribimos el argumento en la entrada de 'cat'
            writer.close();  // Cerramos la escritura para indicar que hemos terminado

            // Capturar y mostrar la salida estándar (la salida de 'cat' debería ser lo mismo que se le envió)
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            System.out.println("Salida del comando 'cat':");
            while ((line = reader.readLine()) != null) {
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
        escribirEnProcesoCat("Esto es un ejemplo de como modificamos manualmente la entrada estandar (stdin) del proceso cat");
    }
}
