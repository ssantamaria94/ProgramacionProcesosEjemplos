package IOExamples;

import java.io.*;

public class ManualPipe {
    public static void main(String[] args) {
        ProcessBuilder pb1 = new ProcessBuilder("ls");
        ProcessBuilder pb2 = new ProcessBuilder("grep", ".txt");

        try {
            Process ls = pb1.start();
            Process grep = pb2.start();

            try (InputStream lsOut = ls.getInputStream();
                 OutputStream grepIn = grep.getOutputStream()) {

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = lsOut.read(buffer)) != -1) {
                    grepIn.write(buffer, 0, bytesRead);
                }
                grepIn.flush();
            }

            BufferedReader grepOut = new BufferedReader(new InputStreamReader(grep.getInputStream()));
            String line;
            while ((line = grepOut.readLine()) != null) {
                System.out.println(line);
            }

            int exitCodeLs = ls.waitFor();
            int exitCodeGrep = grep.waitFor();

            System.out.println("\nProceso 'ls' terminado con código: " + exitCodeLs);
            System.out.println("Proceso 'grep' terminado con código: " + exitCodeGrep);

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
