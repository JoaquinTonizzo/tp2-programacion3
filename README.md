# Trabajo Práctico 2 - Programación III

## Descripción 🗺️

Este proyecto consiste en la implementación de una aplicación para diseñar regiones de un país basada en un grafo de provincias, similar al planteamiento propuesto por R. Assunção et al. El grafo G representa las provincias con vértices y aristas que conectan provincias limítrofes, donde cada arista tiene un peso que indica la similaridad entre provincias.

El objetivo es separar el país en k regiones conexas utilizando el siguiente método:
1. Construir un árbol generador mínimo T de G.
2. Eliminar las k − 1 aristas de mayor peso de T.
3. Las k componentes conexas del grafo resultante son las regiones buscadas.

La aplicación implementada incluye una interfaz para cargar y visualizar los vértices, aristas y pesos del grafo, así como una función para visualizar las regiones formadas después de aplicar el algoritmo descrito.

## Características 🌎

- **Interfaz Gráfica:** Visualización de vértices, aristas y pesos del grafo.
- **Algoritmo de Diseño de Regiones:** Implementación del método para separar el país en k regiones conexas.
- **Ejecución Dinámica:** Se puede ejecutar el algoritmo cada vez que se modifica el grafo.

## Tecnologías Utilizadas 🛠️

- Java: Lenguaje de programación principal.
- Swing: Librería para la creación de interfaces gráficas en Java.
- JUnit: Framework de pruebas unitarias para Java.

## Contacto

Para más información, puedes contactar a [joaquintonizzo1@gmail.com](mailto:joaquintonizzo1@gmail.com).

¡Gracias por tu interés!
