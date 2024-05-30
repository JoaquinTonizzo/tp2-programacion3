package logica;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Prim {

    public static GrafoPonderado AGM(GrafoPonderado grafo) {
    	verificarGrafo(grafo);
        List<Arista> aristasArbol = new ArrayList<>();
        Set<Integer> visitados = new HashSet<>();
        
        int verticeInicial = 0;
        visitados.add(verticeInicial);
 
        while (visitados.size() < grafo.tamano()) {
            Arista aristaMinima = encontrarAristaMinima(grafo, visitados);
            aristasArbol.add(aristaMinima);
            visitados.add(aristaMinima.obtenerVertice2());
        }

        GrafoPonderado arbolMinimo = new GrafoPonderado(grafo.tamano());
        for (Arista arista : aristasArbol) {
            arbolMinimo.agregarArista(arista.obtenerVertice1(), arista.obtenerVertice2(), arista.obtenerPeso());
        }
        return arbolMinimo;
    }

    public static Arista encontrarAristaMinima(GrafoPonderado grafo, Set<Integer> visitados) {
    	verificarGrafo(grafo);
        Arista aristaMinima = null;
        int pesoMinimo = Integer.MAX_VALUE;
        for (int i = 0; i < grafo.tamano(); i++) {
            if (visitados.contains(i)) {
                for (int j = 0; j < grafo.tamano(); j++) {
                    if (!visitados.contains(j) && grafo.existeArista(i, j) && grafo.pesoArista(i, j) < pesoMinimo) {
                        pesoMinimo = grafo.pesoArista(i, j);
                        aristaMinima = new Arista(i, j, pesoMinimo);
                    }
                }
            }
        }
        return aristaMinima;
    }
    
    private static void verificarGrafo(GrafoPonderado grafo) {
		if (grafo == null) {
			throw new IllegalArgumentException("El grafo no puede ser null");
		}
	}
    
}


