package com.mycompany.imagenes;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Procesamientoconcurrente {

    public static long procesarImagen() throws Exception {
        File archivoEntrada = new File("C:\\Users\\jcbla\\Desktop\\ESPE\\Cuarto Semestre\\Computacion Paralela 23254\\Laboratorio\\Tercer Laboratorio\\procesamientoconcurrente\\imagenes\\imagen.png");
        BufferedImage imagen = ImageIO.read(archivoEntrada);

        if (imagen == null) {
            throw new Exception("No se pudo cargar la imagen.");
        }

        int ancho = imagen.getWidth();
        int alto = imagen.getHeight();
        int numHilos = 64;
        Thread[] hilos = new Thread[numHilos];
        int alturaPorHilo = alto / numHilos;

        long inicioProcesamiento = System.nanoTime();

        for (int i = 0; i < numHilos; i++) {
            final int yInicio = i * alturaPorHilo;
            final int yFin = (i == numHilos - 1) ? alto : yInicio + alturaPorHilo;

            hilos[i] = new Thread(() -> {
                for (int y = yInicio; y < yFin; y++) {
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
            });

            hilos[i].start();
        }

        for (Thread hilo : hilos) {
            hilo.join();
        }

        long finProcesamiento = System.nanoTime();

        File archivoSalida = new File("C:\\Users\\jcbla\\Desktop\\ESPE\\Cuarto Semestre\\Computacion Paralela 23254\\Laboratorio\\Tercer Laboratorio\\procesamientoconcurrente\\imagenes_grises_concurrente\\imagen_gris_hilos.png");
        ImageIO.write(imagen, "png", archivoSalida);

        return finProcesamiento - inicioProcesamiento;
    }

    public static void main(String[] args) {
        try {
            long tiempo = procesarImagen();
            System.out.println("Tiempo de procesamiento CONCURRENTE (ms): " + tiempo / 1_000_000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
