package IOExamples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class PipelineExample {
    public static void main(String[] args) {
        try {
            // Crear una lista de ProcessBuilder para los procesos
            List<ProcessBuilder> builders = Arrays.asList(
                    new ProcessBuilder("ls"),
                    new ProcessBuilder("grep", ".txt")
            );

            // Iniciar los procesos en una pipeline
            List<Process> procesos = ProcessBuilder.startPipeline(builders);

            // Capturar la salida final de 'grep'
            try (BufferedReader salidaGrep = new BufferedReader(new InputStreamReader(procesos.get(1).getInputStream()))) {
                String line;
                System.out.println("Archivos .txt encontrados:");
                while ((line = salidaGrep.readLine()) != null) {
                    System.out.println(line);
                }
            }

            // Esperar a que ambos procesos terminen
            for (Process proceso : procesos) {
                int exitCode = proceso.waitFor();
                System.out.println("Proceso terminado con código: " + exitCode);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
