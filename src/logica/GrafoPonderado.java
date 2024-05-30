package logica;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class GrafoPonderado {
    private Integer[][] matrizAdyacencia;
 
    public GrafoPonderado(int vertices) {
        if (vertices < 0) {
            throw new IllegalArgumentException("El número de vértices no puede ser negativo");
        }
        matrizAdyacencia = new Integer[vertices][vertices];
    }


    public void agregarArista(int i, int j, Integer peso) {
        verificarVertice(i);
        verificarVertice(j);
        verificarDistintos(i, j);
        matrizAdyacencia[i][j] = peso;
        matrizAdyacencia[j][i] = peso; 
    }

    public void eliminarArista(int i, int j) {
        verificarVertice(i);
        verificarVertice(j);
        verificarDistintos(i, j);
        matrizAdyacencia[i][j] = null;
        matrizAdyacencia[j][i] = null;
    }

    public boolean existeArista(int i, int j) {
        verificarVertice(i);
        verificarVertice(j);
        verificarDistintos(i, j);
        return matrizAdyacencia[i][j] != null;
    }

    public int tamano() {
        return matrizAdyacencia.length;
    }

    public Set<Integer> vecinos(int i) {
        verificarVertice(i);
        Set<Integer> ret = new HashSet<>();
        for (int j = 0; j < this.tamano(); ++j) {
            if (i != j && matrizAdyacencia[i][j] != null) {
                ret.add(j);
            }
        }
        return ret;
    }

    public ArrayList<Arista> obtenerAristas() {
    	ArrayList<Arista> aristasList = new ArrayList<>();
        for (int i = 0; i < matrizAdyacencia.length; i++) {
            for (int j = i + 1; j < matrizAdyacencia[i].length; j++) {
                if (matrizAdyacencia[i][j] != null) {
                    aristasList.add(new Arista(i, j, matrizAdyacencia[i][j]));
                }
            }
        }
        return aristasList;
    }
    
    public int pesoArista(int i, int j) {
        if (existeArista(i, j)) {
            return matrizAdyacencia[i][j];
        }
        throw new IllegalArgumentException("No existe la arista que une: (" + i + ", " + j + ")");
    }

    private void verificarVertice(int i) {
        if (i < 0)
            throw new IllegalArgumentException("El vértice no puede ser negativo: " + i);

        if (i >= matrizAdyacencia.length)
            throw new IllegalArgumentException("Los vértices deben estar entre 0 y |V|-1: " + i);
    }

    private void verificarDistintos(int i, int j) {
        if (i == j)
            throw new IllegalArgumentException("No se permiten loops: (" + i + ", " + j + ")");
    }
}
