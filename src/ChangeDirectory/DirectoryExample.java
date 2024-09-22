package ChangeDirectory;

import java.io.File;
import java.io.IOException;

public class DirectoryExample {
    public static void changeDirectory(String directory) {
        // Crear un objeto File para el nuevo directorio de trabajo
        File nuevoDirectorio = new File(directory);  // Cambia esta ruta por una existente

        // Crear el proceso con el comando "mkdir" para crear un nuevo directorio "NuevoDirectorio"
        ProcessBuilder processBuilder = new ProcessBuilder("mkdir", "NuevoDirectorio");

        // Establecer el directorio de trabajo
        processBuilder.directory(nuevoDirectorio);

        try {
            // Iniciar el proceso
            Process process = processBuilder.start();

            // Esperar a que el proceso termine y obtener el código de salida
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Directorio 'NuevoDirectorio' creado exitosamente en: " + nuevoDirectorio.getAbsolutePath());
            } else {
                System.out.println("Error al crear el directorio. Código de salida: " + exitCode);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        changeDirectory("/home/user/Escritorio");
    }
}
