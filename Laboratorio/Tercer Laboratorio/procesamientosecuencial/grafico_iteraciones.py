import matplotlib.pyplot as plt
import re

archivo = "resultados_tiempos.txt"

iteraciones = []
tiempo_individual = []
tiempo_promedio_lote = []

with open(archivo, 'r', encoding='utf-8') as f:
    contenido = f.read()

iteraciones_raw = re.findall(r"^Iteración (\d+)", contenido, re.MULTILINE)
secuenciales_raw = re.findall(r"Procesamiento secuencial Iteración \d+:\s*([\d,\.]+)", contenido)
promedios_raw = re.findall(r"Tiempo promedio de procesamiento por imagen \(ms\):\s*([\d,\.]+)", contenido)

# Verificación antes del bucle
if len(secuenciales_raw) != len(iteraciones_raw) or len(promedios_raw) != len(iteraciones_raw):
    print("¡Error! Las longitudes de los datos no coinciden.")
    print(f"Iteraciones: {len(iteraciones_raw)} | Secuenciales: {len(secuenciales_raw)} | Promedios: {len(promedios_raw)}")
    exit()

# Cargar los valores
for i in range(len(iteraciones_raw)):
    iteraciones.append(int(iteraciones_raw[i]))
    tiempo_individual.append(float(secuenciales_raw[i].replace(",", ".")))
    tiempo_promedio_lote.append(float(promedios_raw[i].replace(",", ".")))

# Tabla en consola
print("Iteración\tImagen (ms)\tLote Promedio (ms)")
for i in range(len(iteraciones)):
    print(f"{iteraciones[i]}\t\t{tiempo_individual[i]:.2f}\t\t{tiempo_promedio_lote[i]:.2f}")

# Gráfico
plt.figure(figsize=(10, 6))
plt.plot(iteraciones, tiempo_individual, label="Imagen única (secuencial)", marker='o')
plt.plot(iteraciones, tiempo_promedio_lote, label="Lote (promedio por imagen)", marker='x')
plt.title("Tiempos de procesamiento por iteración (Secuencial)")
plt.xlabel("Iteración")
plt.ylabel("Tiempo (ms)")
plt.legend()
plt.grid(True)
plt.tight_layout()
plt.show()
