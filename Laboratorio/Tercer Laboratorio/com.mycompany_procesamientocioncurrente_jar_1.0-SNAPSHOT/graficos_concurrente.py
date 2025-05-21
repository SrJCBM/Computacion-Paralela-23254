import matplotlib.pyplot as plt

archivo = "resultados_tiempos_concurrente.txt"

iteraciones = []
tiempo_individual = []
tiempo_promedio_lote = []

with open(archivo, "r", encoding="utf-8") as f:
    lines = f.readlines()

for i in range(len(lines)):
    line = lines[i].strip()
    if line.startswith("Iteración"):
        iter_num = int(line.split()[1])
        # Línea 1: tiempo de imagen individual
        tiempo_img_line = lines[i + 1].split(":")[1].replace("ms", "").replace(",", ".").strip()
        tiempo_lote_line = lines[i + 2].split(":")[1].replace("ms", "").replace(",", ".").strip()

        tiempo_img = float(tiempo_img_line)
        tiempo_lote = float(tiempo_lote_line)

        iteraciones.append(iter_num)
        tiempo_individual.append(tiempo_img)
        tiempo_promedio_lote.append(tiempo_lote)

# Mostrar tabla por consola
print("Iteración\tImagen (ms)\tLote Promedio (ms)")
for i in range(len(iteraciones)):
    print(f"{iteraciones[i]}\t\t{tiempo_individual[i]:.2f}\t\t{tiempo_promedio_lote[i]:.2f}")

# Graficar los resultados
plt.figure(figsize=(10, 6))
plt.plot(iteraciones, tiempo_individual, label="Imagen única (concurrente)", marker='o')
plt.plot(iteraciones, tiempo_promedio_lote, label="Lote (promedio por imagen)", marker='x')
plt.title("Tiempos de procesamiento por iteración (Concurrente)")
plt.xlabel("Iteración")
plt.ylabel("Tiempo (ms)")
plt.legend()
plt.grid(True)
plt.tight_layout()
plt.show()
