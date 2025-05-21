package com.mycompany.imagenes;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class Tester {

    public static void main(String[] args) {
        int repeticiones = 50;
        String archivoResultados = "resultados_tiempos_concurrente.txt";

        try (PrintWriter pw = new PrintWriter(new FileWriter(archivoResultados))) {
            pw.println("Resultados de tiempos de ejecución (Concurrente)");
            pw.println("=============================================");

            for (int i = 1; i <= repeticiones; i++) {
                System.out.println("Iteración " + i);
                pw.println("Iteración " + i);

                try {
                    // Procesamiento concurrente de una imagen
                    long tiempoImagen = Procesamientoconcurrente.procesarImagen();
                    String lineaImg = String.format("Procesamiento concurrente (1 imagen) Iteración %d: %.2f ms", i, tiempoImagen / 1_000_000.0);
                    System.out.println(lineaImg);
                    pw.println(lineaImg);

                    // Procesamiento concurrente de lote de imágenes
                    ResultadoLote resultadoLote = ProcesadorLoteConcurrente.procesarLote();
                    double promedioLote = resultadoLote.sumaTiempos / 1_000_000.0 / 100.0;
                    String lineaProm = String.format("Tiempo promedio procesamiento por imagen (lote): %.2f ms", promedioLote);
                    System.out.println(lineaProm);
                    pw.println(lineaProm);

                } catch (Exception e) {
                    String error = "Error en la iteración " + i + ": " + e.getMessage();
                    System.out.println(error);
                    pw.println(error);
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
