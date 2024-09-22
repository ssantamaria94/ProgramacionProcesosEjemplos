package ModifyEnviroment;

import java.io.IOException;

public class Enviroment {
    public static void main(String[] args) {
        // Crear un ProcessBuilder para ejecutar el comando mkdir
        ProcessBuilder processBuilder = new ProcessBuilder("mkdir", "NuevoDirectorio");

        // Modificar el entorno del proceso
        processBuilder.environment().put("DIR_NAME", "NuevoDirectorio");

        // Crear el directorio usando la variable de entorno
        processBuilder.command("mkdir", processBuilder.environment().get("DIR_NAME"));

        try {
            // Iniciar el proceso
            Process process = processBuilder.start();

            // Esperar a que el proceso termine y obtener el código de salida
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Directorio '" + processBuilder.environment().get("DIR_NAME") + "' creado exitosamente.");
            } else {
                System.out.println("Error al crear el directorio. Código de salida: " + exitCode);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
