package logica;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class BFS {
	private static List<Integer> L;
	private static boolean[] marcados;
	
	public static boolean esConexo(GrafoPonderado grafo) {
		verificarGrafo(grafo);
		if (grafo.tamano() <= 1) {
			return true;
		}
		return alcanzables(grafo,0).size() == grafo.tamano();
	}
	
	public static Set<Integer> alcanzables(GrafoPonderado grafo, int origen){
		verificarGrafo(grafo);
		verificarOrigen(grafo, origen);
		Set<Integer> ret = new HashSet<Integer>();
		
		inicializarBusqueda(grafo, origen);
		
	    while (!L.isEmpty()) {
	    	int seleccionado = seleccionarYMarcar();
	    	ret.add(seleccionado);
	    	agregarVecinosNoMarcados(grafo, seleccionado);
	    	removerSeleccionado();
	    }
		return ret;
	}

	private static void verificarOrigen(GrafoPonderado grafo, int origen) {
		if (origen >= grafo.tamano() || origen < 0) {
			throw new IllegalArgumentException("El vertice debe existir en el grafo");
		}
	}

	private static void verificarGrafo(GrafoPonderado grafo) {
		if (grafo == null) {
			throw new IllegalArgumentException("El grafo no puede ser null");
		}
	}

	private static void inicializarBusqueda(GrafoPonderado grafo, int origen) {
		L = new LinkedList<Integer>();
	    marcados = new boolean[grafo.tamano()];
	    L.add(origen);
	}

	private static void removerSeleccionado() {
		L.remove(0);
	}

	private static int seleccionarYMarcar() {
		int seleccionado = L.get(0);
		marcados[seleccionado] = true;
		return seleccionado;
	}

	private static void agregarVecinosNoMarcados(GrafoPonderado grafo, int seleccionado) {
		for (int vecino : grafo.vecinos(seleccionado)) {
			if (marcados[vecino] == false && L.contains(vecino) == false) {
				L.add(vecino);
			}
		}
	}
	
}
