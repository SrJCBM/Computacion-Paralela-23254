import pandas as pd
import matplotlib.pyplot as plt

archivos = ['tiempos1.csv', 'tiempos2.csv', 'tiempos3.csv', 'tiempos4.csv']
sistemas = ['Sistema 1', 'Sistema 2', 'Sistema 3', 'Sistema 4']

# Inicializar listas de resultados
secuenciales = []
simds = []
aceleraciones = []

for archivo in archivos:
    df = pd.read_csv(archivo, header=None, names=['Secuencial', 'SIMD'])
    prom_sec = df['Secuencial'].mean()
    prom_simd = df['SIMD'].mean()
    secuenciales.append(prom_sec)
    simds.append(prom_simd)
    aceleraciones.append(prom_sec / prom_simd if prom_simd > 0 else float('inf'))

# Crear tabla visual con matplotlib
fig, ax = plt.subplots(figsize=(9, 2))
ax.axis('off')

col_labels = ['Sistema', 'Secuencial (s)', 'SIMD (s)', 'Aceleración']
table_data = [
    [s, f"{sec:.5f}", f"{sim:.5f}", f"{acc:.2f}x"]
    for s, sec, sim, acc in zip(sistemas, secuenciales, simds, aceleraciones)
]

table = ax.table(cellText=table_data, colLabels=col_labels, loc='center')
table.scale(1, 2)
table.auto_set_font_size(False)
table.set_fontsize(10)

plt.title("Resumen de tiempos promedio y aceleración", pad=20)
plt.tight_layout()
plt.show()
