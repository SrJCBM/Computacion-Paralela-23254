@echo off
cd /d "C:\Users\jcbla\Desktop\ESPE\Cuarto Semestre\Computacion Paralela 23254"

rem Eliminar archivo de resultados anterior
if exist "tiempos.csv" del "tiempos.csv"

rem Ejecutar 50 veces: compilar y correr cada vez
for /l %%i in (1,1,50) do (
    gcc -O2 -msse 1TrabajoGrupal.c -o temp.exe
    temp.exe
)

rem Eliminar ejecutable temporal
del temp.exe

echo Ejecuciones completadas.
pause

