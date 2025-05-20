import pandas as pd
import matplotlib.pyplot as plt

# Leer archivo
df = pd.read_csv('tiempos.csv', header=None, names=['Secuencial', 'SIMD'])

# Crear eje X de 1 a 50
x = range(1, len(df) + 1)

# Graficar
plt.plot(x, df['Secuencial'], marker='o', label='Secuencial')
plt.plot(x, df['SIMD'], marker='s', label='SIMD')
plt.xlabel('Ejecución')
plt.ylabel('Tiempo (segundos)')
plt.title('Comparación de tiempos: Secuencial vs SIMD')

plt.xticks(x)
plt.legend()
plt.grid(True)
plt.tight_layout()
plt.show()
