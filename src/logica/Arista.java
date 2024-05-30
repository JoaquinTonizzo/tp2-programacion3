package logica;

public class Arista {

	private int vertice1;
	private int vertice2; 
	private int peso;

	public Arista(int i, int j, int pesoArista) {
		vertice1 = i;
		vertice2 = j;
		peso = pesoArista;
		
	}

	public int obtenerVertice1() {
		return vertice1;
	}

	public int obtenerVertice2() {
		return vertice2;
	}

	public int obtenerPeso() {
		return peso;
	}

	@Override
	public boolean equals(Object obj) {
		if (getClass() != obj.getClass())
			return false;
		Arista otra = (Arista) obj;
	    return peso == otra.peso && ((vertice1 == otra.vertice1 && vertice2 == otra.vertice2) ||
                (vertice1 == otra.vertice2 && vertice2 == otra.vertice1));
	}
}

