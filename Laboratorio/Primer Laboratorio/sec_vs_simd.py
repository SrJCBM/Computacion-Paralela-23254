import matplotlib.pyplot as plt

# Datos procesados
sistemas = ['Sistema 1', 'Sistema 2', 'Sistema 3', 'Sistema 4']
secuencial = [0.00188, 0.00334, 0.00212, 0.00210]
simd = [0.00046, 0.00082, 0.00150, 0.00054]

x = range(len(sistemas))
width = 0.35

plt.figure(figsize=(10, 6))
plt.bar([i - width/2 for i in x], secuencial, width, label='Secuencial', color='steelblue')
plt.bar([i + width/2 for i in x], simd, width, label='SIMD (SSE)', color='orange')

plt.ylabel('Tiempo promedio (segundos)')
plt.title('Comparación de tiempos promedio: Secuencial vs SIMD')
plt.xticks(x, sistemas)
plt.legend()
plt.grid(axis='y', linestyle='--', alpha=0.7)
plt.tight_layout()
plt.show()
