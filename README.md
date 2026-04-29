# 🏙️ Skyscraper Puzzle — Java

Un juego de lógica basado en el clásico puzzle **Skyscraper**, implementado completamente en Java como proyecto de programación.

---

## 📦 Contenido del Proyecto

El proyecto contiene la implementación completa del puzzle Skyscraper en Java, incluyendo:

- Generación y representación del tablero de juego en una cuadrícula 2D
- Lógica de validación de las reglas del puzzle
- Interfaz de interacción con el usuario por consola
- Métodos auxiliares para el procesamiento y verificación del estado del juego

---

## ⚙️ Funcionalidades Principales

- **Tablero interactivo:** El jugador puede introducir valores en la cuadrícula del puzzle
- **Validación de reglas:** Comprueba que cada fila y columna contenga números únicos y que las pistas de visibilidad de rascacielos sean correctas
- **Detección de victoria:** Identifica automáticamente cuando el puzzle ha sido resuelto correctamente
- **Manejo de errores:** Controla entradas inválidas e informa al usuario de los fallos
- **Reinicio de partida:** Permite al jugador volver a intentar el puzzle desde cero

---

## 🛠️ Stack Tecnológico

| Tecnología | Uso |
|---|---|
| **Java** | Lenguaje principal del proyecto |
| **Bucles** (`for`, `while`) | Recorrido del tablero y repetición de turnos |
| **Condicionales** (`if`, `else`) | Validación de reglas y control del flujo |
| **Arreglos** (`int[]`) | Representación de filas y columnas individuales |
| **Arreglos 2D** (`int[][]`) | Representación de la cuadrícula completa del puzzle |
| **ArrayList** | Almacenamiento dinámico de datos durante la ejecución |
| **Métodos** | Modularización de la lógica del juego en funciones reutilizables |

---

## 🧩 ¿Qué es el Puzzle Skyscraper?

Skyscraper es un puzzle de lógica que se juega en una cuadrícula N×N. Cada celda debe contener un número del 1 al N (que representa la altura de un rascacielos). Las pistas en los bordes del tablero indican cuántos rascacielos son **visibles** desde ese extremo mirando hacia adentro — un rascacielos más alto bloquea la vista de los más bajos detrás de él.

---

## 🚀 Cómo Ejecutar

```bash
# Compilar
javac Main.java

# Ejecutar
java Main
```

---

## 👤 Autor

Desarrollado como proyecto de práctica de fundamentos de programación en Java.
