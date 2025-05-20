import matplotlib.pyplot as plt
import re

# Archivos
archivo_secuencial = "resultados_tiempos.txt"
archivo_concurrente = "resultados_tiempos_concurrente.txt"

# Función para cargar datos de un archivo dado
def cargar_datos(archivo, modo="secuencial"):
    with open(archivo, 'r', encoding='utf-8') as f:
        contenido = f.read()

    iteraciones = re.findall(r"^Iteración (\d+)", contenido, re.MULTILINE)
    if modo == "secuencial":
        tiempos_ind = re.findall(r"Procesamiento secuencial Iteración \d+:\s*([\d,\.]+)", contenido)
        tiempos_lote = re.findall(r"Tiempo promedio de procesamiento por imagen \(ms\):\s*([\d,\.]+)", contenido)
    else:
        tiempos_ind = re.findall(r"Procesamiento concurrente \(1 imagen\) Iteración \d+:\s*([\d,\.]+)", contenido)
        tiempos_lote = re.findall(r"Tiempo promedio procesamiento por imagen \(lote\):\s*([\d,\.]+)", contenido)

    # Convertir a float y corregir comas
    tiempos_ind = [float(t.replace(",", ".")) for t in tiempos_ind]
    tiempos_lote = [float(t.replace(",", ".")) for t in tiempos_lote]
    iteraciones = list(map(int, iteraciones))

    return iteraciones, tiempos_ind, tiempos_lote

# Cargar ambos archivos
iter_sec, tiempo_sec_ind, tiempo_sec_lote = cargar_datos(archivo_secuencial, modo="secuencial")
iter_conc, tiempo_conc_ind, tiempo_conc_lote = cargar_datos(archivo_concurrente, modo="concurrente")

# Graficar
plt.figure(figsize=(12, 7))
plt.plot(iter_sec, tiempo_sec_ind, label="Imagen única (Secuencial)", marker='o')
plt.plot(iter_sec, tiempo_sec_lote, label="Promedio por imagen en lote (Secuencial)", marker='x')
plt.plot(iter_conc, tiempo_conc_ind, label="Imagen única (Concurrente)", marker='o')
plt.plot(iter_conc, tiempo_conc_lote, label="Promedio por imagen en lote (Concurrente)", marker='x')

plt.title("Comparación de Tiempos de Procesamiento por Iteración")
plt.xlabel("Iteración")
plt.ylabel("Tiempo (ms)")
plt.legend()
plt.grid(True)
plt.tight_layout()
plt.show()
