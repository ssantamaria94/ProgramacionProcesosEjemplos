package DestroyProcess;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class DestroyProcessExample {
    public static void ejecutarSleepConTiempo(int tiempoSleep) {
        // Crear el comando "sleep" con el tiempo recibido como parámetro
        ProcessBuilder processBuilder = new ProcessBuilder("sleep", String.valueOf(tiempoSleep));

        try {
            // Iniciar el proceso
            Process process = processBuilder.start();

            // Esperar 5 segundos a que el proceso termine
            boolean terminado = process.waitFor(5, TimeUnit.SECONDS);

            if (terminado) {
                System.out.println("El proceso terminó correctamente.");
            } else {
                // Si el proceso no termina en 5 segundos, lo destruimos
                System.out.println("El proceso no ha terminado en 5 segundos. Destruyendo el proceso...");
                process.destroy();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Ejecutar el comando sleep con un tiempo de 10 segundos como ejemplo
        ejecutarSleepConTiempo(10); // El proceso será destruido porque no terminará en 5 segundos
    }
}
