package com.mycompany.imagenes;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class ProcesadorLoteConcurrente {

    public static ResultadoLote procesarLote() throws Exception {
        String carpetaEntrada = "C:\\Users\\jcbla\\Desktop\\ESPE\\Cuarto Semestre\\Computacion Paralela 23254\\Laboratorio\\Tercer Laboratorio\\procesamientoconcurrente\\imagenes\\";
        String carpetaSalida = "C:\\Users\\jcbla\\Desktop\\ESPE\\Cuarto Semestre\\Computacion Paralela 23254\\Laboratorio\\Tercer Laboratorio\\procesamientoconcurrente\\imagenes_grises_concurrente\\";

        int totalImagenes = 100;
        int numHilos = 32;
        Thread[] hilos = new Thread[numHilos];
        List<String> lineas = new ArrayList<>();
        long inicioProcesamiento = System.nanoTime();

        for (int h = 0; h < numHilos; h++) {
            final int inicio = h * (totalImagenes / numHilos) + 1;
            final int fin = (h == numHilos - 1) ? totalImagenes : inicio + (totalImagenes / numHilos) - 1;

            hilos[h] = new Thread(() -> {
                for (int i = inicio; i <= fin; i++) {
                    try {
                        File archivoEntrada = new File(carpetaEntrada + "imagen" + i + ".jpg");
                        BufferedImage imagen = ImageIO.read(archivoEntrada);
                        if (imagen == null) continue;

                        int ancho = imagen.getWidth();
                        int alto = imagen.getHeight();

                        for (int y = 0; y < alto; y++) {
                            for (int x = 0; x < ancho; x++) {
                                int pixel = imagen.getRGB(x, y);
                                int alpha = (pixel >> 24) & 0xff;
                                int red = (pixel >> 16) & 0xff;
                                int green = (pixel >> 8) & 0xff;
                                int blue = pixel & 0xff;

                                int gris = (red + green + blue) / 3;
                                int nuevoPixel = (alpha << 24) | (gris << 16) | (gris << 8) | gris;
                                imagen.setRGB(x, y, nuevoPixel);
                            }
                        }

                        File archivoSalida = new File(carpetaSalida + "imagen" + i + "_gris.jpg");
                        ImageIO.write(imagen, "jpg", archivoSalida);
                    } catch (Exception e) {
                        System.out.println("Error al procesar imagen " + i + ": " + e.getMessage());
                    }
                }
            });

            hilos[h].start();
        }

        for (Thread hilo : hilos) {
            hilo.join();
        }

        long finProcesamiento = System.nanoTime();
        long tiempoTotal = finProcesamiento - inicioProcesamiento;

        return new ResultadoLote(lineas, tiempoTotal);
    }
    
    public static void main(String[] args) {
        try {
            ResultadoLote resultado = ProcesadorLoteConcurrente.procesarLote();

            double promedioMs = resultado.sumaTiempos / 1_000_000.0 / 100.0;
            double totalMs = resultado.sumaTiempos / 1_000_000.0;

            System.out.printf("Tiempo total de procesamiento (ms): %.2f%n", totalMs);
            System.out.printf("Tiempo promedio de procesamiento por imagen (ms): %.2f%n", promedioMs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}