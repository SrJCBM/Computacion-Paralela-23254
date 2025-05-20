package ec.edu.espe.procesamientosecuencial;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class ProcesadorLoteSecuencial {

    public static ResultadoLote procesarLote() throws Exception {
        String carpetaEntrada = "C:\\Users\\jcbla\\Desktop\\ESPE\\Cuarto Semestre\\Computacion Paralela 23254\\Laboratorio\\Tercer Laboratorio\\procesamientosecuencial\\imagenes\\";
        String carpetaSalida = "C:\\Users\\jcbla\\Desktop\\ESPE\\Cuarto Semestre\\Computacion Paralela 23254\\Laboratorio\\Tercer Laboratorio\\procesamientosecuencial\\imagenes_grises_secuencial\\";

        long sumaTiemposProcesamiento = 0;
        int totalImagenesProcesadas = 0;
        List<String> lineas = new ArrayList<>();

        for (int i = 1; i <= 100; i++) {
            String nombreEntrada = carpetaEntrada + "imagen" + i + ".jpg";
            String nombreSalida = carpetaSalida + "imagen" + i + "_gris.jpg";

            try {
                File archivoEntrada = new File(nombreEntrada);
                BufferedImage imagen = ImageIO.read(archivoEntrada);

                if (imagen == null) {
                    String mensaje = "No se pudo cargar la imagen: " + nombreEntrada;
                    System.out.println(mensaje);
                    lineas.add(mensaje);
                    continue;
                }

                int ancho = imagen.getWidth();
                int alto = imagen.getHeight();

                long inicioProcesamiento = System.nanoTime();

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

                long finProcesamiento = System.nanoTime();

                File archivoSalida = new File(nombreSalida);
                ImageIO.write(imagen, "jpg", archivoSalida);

                long tiempoProcesamiento = finProcesamiento - inicioProcesamiento;
                String linea = "Imagen " + i + " - TiempoProcesamiento(ms): " + (tiempoProcesamiento / 1_000_000);
                System.out.println(linea);
                lineas.add(linea);

                sumaTiemposProcesamiento += tiempoProcesamiento;
                totalImagenesProcesadas++;

            } catch (Exception e) {
                String errorLinea = "Error al procesar imagen " + i + ": " + e.getMessage();
                System.out.println(errorLinea);
                lineas.add(errorLinea);
            }
        }

        if (totalImagenesProcesadas == 0) {
            throw new Exception("No se procesaron imágenes correctamente.");
        }

        return new ResultadoLote(lineas, sumaTiemposProcesamiento);
    }
public static void main(String[] args) {
    try {
        ResultadoLote resultado = procesarLote();
        long tiempoTotalMs = resultado.sumaTiempos / 1_000_000;
        System.out.printf("Tiempo total de procesamiento (ms): %d%n", tiempoTotalMs);
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}
