package ProcessInfo;

import java.io.IOException;
import java.time.Instant;

public class ProcessInfoExample {
    public static void main(String[] args) {
        try {
            // Crear el proceso para 'sleep' (durante 5 segundos)
            ProcessBuilder pbSleep = new ProcessBuilder("sleep", "5");
            Process procesoSleep = pbSleep.start();

            // Obtener el handle del proceso creado
            ProcessHandle handleSleep = procesoSleep.toHandle();

            // Mostrar información básica del proceso sleep
            System.out.println("ID del proceso: " + handleSleep.pid());

            // Obtener información adicional sobre el proceso
            ProcessHandle.Info info = handleSleep.info();

            // Mostrar el comando que se está ejecutando
            String command = info.command().orElse("No disponible");
            String arguments = info.arguments().map(arg -> String.join(", ", arg)).orElse("No disponible");
            String user = info.user().orElse("No disponible");
            Instant processInstant = info.startInstant().orElse(Instant.now());

            System.out.println("Nombre del proceso: " + command);
            System.out.println("Estado del proceso: " + processInstant);
            System.out.println("Argumentos: " + arguments);
            System.out.println("Nombre del usuario: " + user);

            // Esperar a que el proceso termine
            int exitCode = procesoSleep.waitFor();
            System.out.println("Proceso 'sleep' terminado con código: " + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        }
}
