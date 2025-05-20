package ec.edu.espe.procesamientosecuencial;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class Tester {

    public static void main(String[] args) {
        int repeticiones = 50;
        String archivoResultados = "resultados_tiempos.txt";

        long sumaTiempoSecuencial = 0;
        long sumaTiempoLote = 0;

        try (PrintWriter pw = new PrintWriter(new FileWriter(archivoResultados))) {
            pw.println("Resultados de tiempos de ejecución");
            pw.println("=================================");

            for (int i = 1; i <= repeticiones; i++) {
                System.out.println("Iteración " + i);
                pw.println("Iteración " + i);

                try {
                    // Procesamiento secuencial de una imagen
                    long tiempoSecuencial = Procesamientosecuencial.procesarImagen();
                    String lineaSec = String.format("Procesamiento secuencial Iteración %d: %.2f ms", i, tiempoSecuencial / 1_000_000.0);
                    System.out.println(lineaSec);
                    pw.println(lineaSec);

                    // Procesamiento lote 100 imágenes (detalle se imprime dentro de procesarLote)
                    ResultadoLote resultadoLote = ProcesadorLoteSecuencial.procesarLote();

                    double promedioMs = resultadoLote.sumaTiempos / 1_000_000.0 / 100.0;
                    String lineaProm = String.format("Tiempo promedio de procesamiento por imagen (ms): %.2f", promedioMs);
                    System.out.println(lineaProm);
                    pw.println(lineaProm);

                } catch (Exception e) {
                    System.out.println("Error en la iteración " + i + ": " + e.getMessage());
                    pw.println("Error en la iteración " + i + ": " + e.getMessage());
                    e.printStackTrace(pw);
                }

                System.out.println("---------------------------");
                pw.println("---------------------------");
            }

        } catch (IOException e) {
            System.err.println("Error escribiendo en archivo: " + e.getMessage());
        }
    }
}
