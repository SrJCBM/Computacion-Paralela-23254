import matplotlib.pyplot as plt

# Datos de aceleración
sistemas = ['Sistema 1', 'Sistema 2', 'Sistema 3', 'Sistema 4']
aceleraciones = [4.09, 4.07, 1.41, 3.89]

plt.figure(figsize=(8, 5))
bars = plt.bar(sistemas, aceleraciones, color='seagreen')
plt.ylabel('Aceleración (speedup)')
plt.title('Aceleración lograda con SIMD (SSE) vs Suma Secuencial')
plt.ylim(0, max(aceleraciones) + 1)
plt.grid(axis='y', linestyle='--', alpha=0.7)

# Etiquetas numéricas sobre las barras
for bar in bars:
    yval = bar.get_height()
    plt.text(bar.get_x() + bar.get_width()/2.0, yval + 0.1, f'{yval:.2f}', ha='center', va='bottom')

plt.tight_layout()
plt.show()
