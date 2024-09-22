package IOExamples;

import java.io.File;
import java.io.IOException;

public class RedirectIOExample {
    public static void main(String[] args) {
        // Archivos de entrada y salida
        File archivoEntrada = new File("entrada.txt"); // Asegúrate de que este archivo exista
        File archivoSalida = new File("salida.txt");
        File archivoErrores = new File("errores.txt");

        try {
            // Crear el proceso para 'cat'
            ProcessBuilder pbCat = new ProcessBuilder("cat");
            pbCat.redirectInput(archivoEntrada); // Redirigir la entrada desde 'entrada.txt'
            pbCat.redirectOutput(archivoSalida); // Redirigir la salida a 'salida.txt'
            pbCat.redirectError(ProcessBuilder.Redirect.appendTo(archivoErrores));

            // Iniciar el proceso
            Process procesoCat = pbCat.start();

            // Esperar a que el proceso termine
            int exitCode = procesoCat.waitFor();
            System.out.println("Proceso 'cat' terminado con código: " + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
