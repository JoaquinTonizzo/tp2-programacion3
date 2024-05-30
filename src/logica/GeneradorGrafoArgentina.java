package logica;

import java.util.ArrayList;
import java.util.List;

public class GeneradorGrafoArgentina {
    private GrafoPonderado grafo;
    private ArrayList<Provincia> provincias;
    
    public GeneradorGrafoArgentina() {
        grafo = new GrafoPonderado(24);
        provincias = new ArrayList<>();
        provincias.add(new Provincia("Jujuy", -23.1858, -66.2995));
        provincias.add(new Provincia("Salta", -25.1829, -65.4122));
        provincias.add(new Provincia("Tucumán", -26.8083, -65.2176));
        provincias.add(new Provincia("Catamarca", -27.4696, -66.7788));
        provincias.add(new Provincia("La Rioja", -29.4131, -66.8551));
        provincias.add(new Provincia("San Juan", -31.1375, -68.5364));
        provincias.add(new Provincia("Santiago del Estero", -27.7834, -63.2642));
        provincias.add(new Provincia("Mendoza", -33.8894, -68.8458));
        provincias.add(new Provincia("San Luis", -33.7578, -66.0431));
        provincias.add(new Provincia("Córdoba", -31.4201, -64.1888));
        provincias.add(new Provincia("Santa Fe", -29.9107, -61.0973));
        provincias.add(new Provincia("Entre Ríos", -31.5225, -59.3276));
        provincias.add(new Provincia("Chaco", -26.4514, -60.4856));
        provincias.add(new Provincia("Formosa", -25.1852, -59.1756));
        provincias.add(new Provincia("Corrientes", -28.4691, -57.8309));
        provincias.add(new Provincia("Misiones", -26.8754, -54.6001));
        provincias.add(new Provincia("Buenos Aires", -36.6769, -60.5588));
        provincias.add(new Provincia("CABA", -34.6037, -58.3816));
        provincias.add(new Provincia("La Pampa", -36.9792, -66.1420));
        provincias.add(new Provincia("Río Negro", -40.8251, -67.4755));
        provincias.add(new Provincia("Neuquén", -38.9516, -70.0591));
        provincias.add(new Provincia("Chubut", -43.3002, -69.1023));
        provincias.add(new Provincia("Santa Cruz", -48.8155, -69.9558));
        provincias.add(new Provincia("Tierra del Fuego", -54.4019, -67.3029));
    }
    
    public void agregarArista(int origen, int destino, int peso) {
		grafo.agregarArista(origen, destino, peso);
	} 
    
    public void eliminarArista(int origen, int destino) {
		grafo.eliminarArista(origen, destino);
	} 
    
    public void obtenerAGM() {
    	if (BFS.esConexo(grafo)) {
    		grafo = Prim.AGM(grafo);
    	}
    }

    public void generarRegiones(int cantidadRegiones) {
    	if (!verificarCantidadDeRegionesValida(cantidadRegiones)) {
    		throw new IllegalArgumentException("El número de regiones debe estar entre 1 y " + grafo.tamano());
    	}
    	obtenerAGM();
        for (int i = 0; i < cantidadRegiones - 1; i++) {
        	eliminarAristaMayorPeso();
        }
    }

    public void eliminarAristaMayorPeso() {
        Arista aristaMayorPeso = null;
        Integer pesoMayor = Integer.MIN_VALUE;
        for (Arista arista : grafo.obtenerAristas()) {
            if (arista.obtenerPeso() > pesoMayor) {
                pesoMayor = arista.obtenerPeso();
                aristaMayorPeso = arista;
            }
        }
        if (aristaMayorPeso != null) {
            grafo.eliminarArista(aristaMayorPeso.obtenerVertice1(), aristaMayorPeso.obtenerVertice2());
        }
    }

	public ArrayList<Arista> obtenerAristas() {
		ArrayList<Arista> aristas = grafo.obtenerAristas();
		return aristas;
	} 
	
	public GeneradorGrafoArgentina clonar() {
	    GeneradorGrafoArgentina clon = new GeneradorGrafoArgentina();
	    for (Arista arista : grafo.obtenerAristas()) {
	        clon.agregarArista(arista.obtenerVertice1(), arista.obtenerVertice2(), arista.obtenerPeso());
	    }
	    return clon;
	}

	public int obtenerIndiceProvincia(String nombreProvincia) {
		int indice = 0;
	    for (Provincia provincia : provincias) {
	        if (provincia.obtenerNombre().equals(nombreProvincia)) {
	            return indice;
	        }
	        indice++;
	    }
	    return -1;
    }
	
	public Provincia obtenerProvinciaPorIndice(int indice) {
	    if (indice >= 0 && indice < provincias.size()) {
	        return provincias.get(indice);
	    } else {
	        throw new IllegalArgumentException("El índice proporcionado (" + indice + ") está fuera del rango válido [0, " + (provincias.size() - 1) + "]");
	    }
	}

	public ArrayList<Provincia> obtenerProvincias() {
		@SuppressWarnings("unchecked")
		ArrayList<Provincia> copia = (ArrayList<Provincia>) provincias.clone();
	    return copia;
	}

	public boolean verificarCantidadDeRegionesValida(int cantidadRegiones) {
		if (cantidadRegiones < 1 || cantidadRegiones > provincias.size()) {
			return false;
		}
		return true;
	}
	
	public List<Tupla<Integer, Integer>> obtenerConexionesProvincias() {
        List<Tupla<Integer, Integer>> conexiones = new ArrayList<>(); 
        conexiones.add(new Tupla<>(obtenerIndiceProvincia("Jujuy"), obtenerIndiceProvincia("Salta"))); // Jujuy - Salta
        conexiones.add(new Tupla<>(obtenerIndiceProvincia("Salta"), obtenerIndiceProvincia("Catamarca"))); // Salta - Catamarca
        conexiones.add(new Tupla<>(obtenerIndiceProvincia("Salta"), obtenerIndiceProvincia("Tucumán"))); // Salta - Tucumán
        conexiones.add(new Tupla<>(obtenerIndiceProvincia("Salta"), obtenerIndiceProvincia("Santiago del Estero"))); // Salta - Santiago del Estero
        conexiones.add(new Tupla<>(obtenerIndiceProvincia("Salta"), obtenerIndiceProvincia("Chaco"))); // Salta - Chaco
        conexiones.add(new Tupla<>(obtenerIndiceProvincia("Salta"), obtenerIndiceProvincia("Formosa"))); // Salta - Formosa
        conexiones.add(new Tupla<>(obtenerIndiceProvincia("Tucumán"), obtenerIndiceProvincia("Catamarca"))); // Tucumán - Catamarca
        conexiones.add(new Tupla<>(obtenerIndiceProvincia("Tucumán"), obtenerIndiceProvincia("Santiago del Estero"))); // Tucumán - Santiago del Estero
        conexiones.add(new Tupla<>(obtenerIndiceProvincia("Catamarca"), obtenerIndiceProvincia("La Rioja"))); // Catamarca - La Rioja
        conexiones.add(new Tupla<>(obtenerIndiceProvincia("Catamarca"), obtenerIndiceProvincia("Córdoba"))); // Catamarca - Córdoba
        conexiones.add(new Tupla<>(obtenerIndiceProvincia("Catamarca"), obtenerIndiceProvincia("Santiago del Estero"))); // Catamarca - Santiago del Estero
        conexiones.add(new Tupla<>(obtenerIndiceProvincia("Santiago del Estero"), obtenerIndiceProvincia("Santa Fe"))); // Santiago del Estero - Santa Fe
        conexiones.add(new Tupla<>(obtenerIndiceProvincia("Santiago del Estero"), obtenerIndiceProvincia("Chaco"))); // Santiago del Estero - Chaco
        conexiones.add(new Tupla<>(obtenerIndiceProvincia("Santiago del Estero"), obtenerIndiceProvincia("Córdoba"))); // Santiago del Estero - Córdoba
        conexiones.add(new Tupla<>(obtenerIndiceProvincia("Chaco"), obtenerIndiceProvincia("Santa Fe"))); // Chaco - Santa Fe
        conexiones.add(new Tupla<>(obtenerIndiceProvincia("Chaco"), obtenerIndiceProvincia("Corrientes"))); // Chaco - Corrientes
        conexiones.add(new Tupla<>(obtenerIndiceProvincia("Chaco"), obtenerIndiceProvincia("Formosa"))); // Chaco - Formosa
        conexiones.add(new Tupla<>(obtenerIndiceProvincia("Corrientes"), obtenerIndiceProvincia("Santa Fe"))); // Corrientes - Santa Fe
        conexiones.add(new Tupla<>(obtenerIndiceProvincia("Corrientes"), obtenerIndiceProvincia("Entre Ríos"))); // Corrientes - Entre Ríos
        conexiones.add(new Tupla<>(obtenerIndiceProvincia("Corrientes"), obtenerIndiceProvincia("Misiones"))); // Corrientes - Misiones
        conexiones.add(new Tupla<>(obtenerIndiceProvincia("La Rioja"), obtenerIndiceProvincia("San Juan"))); // La Rioja - San Juan
        conexiones.add(new Tupla<>(obtenerIndiceProvincia("La Rioja"), obtenerIndiceProvincia("San Luis"))); // La Rioja - San Luis
        conexiones.add(new Tupla<>(obtenerIndiceProvincia("La Rioja"), obtenerIndiceProvincia("Córdoba"))); // La Rioja - Córdoba
        conexiones.add(new Tupla<>(obtenerIndiceProvincia("San Juan"), obtenerIndiceProvincia("Mendoza"))); // San Juan - Mendoza
        conexiones.add(new Tupla<>(obtenerIndiceProvincia("San Juan"), obtenerIndiceProvincia("San Luis"))); // San Juan - San Luis
        conexiones.add(new Tupla<>(obtenerIndiceProvincia("Mendoza"), obtenerIndiceProvincia("San Luis"))); // Mendoza - San Luis
        conexiones.add(new Tupla<>(obtenerIndiceProvincia("Mendoza"), obtenerIndiceProvincia("La Pampa"))); // Mendoza - La Pampa
        conexiones.add(new Tupla<>(obtenerIndiceProvincia("Mendoza"), obtenerIndiceProvincia("Neuquén"))); // Mendoza - Neuquén
        conexiones.add(new Tupla<>(obtenerIndiceProvincia("San Luis"), obtenerIndiceProvincia("Córdoba"))); // San Luis - Córdoba
        conexiones.add(new Tupla<>(obtenerIndiceProvincia("San Luis"), obtenerIndiceProvincia("La Pampa"))); // San Luis - La Pampa
        conexiones.add(new Tupla<>(obtenerIndiceProvincia("Córdoba"), obtenerIndiceProvincia("Santa Fe"))); // Córdoba - Santa Fe
        conexiones.add(new Tupla<>(obtenerIndiceProvincia("Córdoba"), obtenerIndiceProvincia("Buenos Aires"))); // Córdoba - Buenos Aires
        conexiones.add(new Tupla<>(obtenerIndiceProvincia("Córdoba"), obtenerIndiceProvincia("La Pampa"))); // Córdoba - La Pampa
        conexiones.add(new Tupla<>(obtenerIndiceProvincia("Santa Fe"), obtenerIndiceProvincia("Buenos Aires"))); // Santa Fe - Buenos Aires
        conexiones.add(new Tupla<>(obtenerIndiceProvincia("Santa Fe"), obtenerIndiceProvincia("Entre Ríos"))); // Santa Fe - Entre Ríos
        conexiones.add(new Tupla<>(obtenerIndiceProvincia("Buenos Aires"), obtenerIndiceProvincia("Entre Ríos"))); // Buenos Aires - Entre Ríos
        conexiones.add(new Tupla<>(obtenerIndiceProvincia("Buenos Aires"), obtenerIndiceProvincia("La Pampa"))); // Buenos Aires - La Pampa
        conexiones.add(new Tupla<>(obtenerIndiceProvincia("Buenos Aires"), obtenerIndiceProvincia("Río Negro"))); // Buenos Aires - Río Negro
        conexiones.add(new Tupla<>(obtenerIndiceProvincia("Buenos Aires"), obtenerIndiceProvincia("CABA"))); // Buenos Aires - CABA
        conexiones.add(new Tupla<>(obtenerIndiceProvincia("La Pampa"), obtenerIndiceProvincia("Río Negro"))); // La Pampa - Río Negro
        conexiones.add(new Tupla<>(obtenerIndiceProvincia("La Pampa"), obtenerIndiceProvincia("Neuquén"))); // La Pampa - Neuquén
        conexiones.add(new Tupla<>(obtenerIndiceProvincia("Río Negro"), obtenerIndiceProvincia("Neuquén"))); // Río Negro - Neuquén
        conexiones.add(new Tupla<>(obtenerIndiceProvincia("Chubut"), obtenerIndiceProvincia("Río Negro"))); // Chubut - Río Negro
        conexiones.add(new Tupla<>(obtenerIndiceProvincia("Santa Cruz"), obtenerIndiceProvincia("Chubut"))); // Santa Cruz - Chubut
        conexiones.add(new Tupla<>(obtenerIndiceProvincia("Tierra del Fuego"), obtenerIndiceProvincia("Santa Cruz"))); // Tierra del Fuego - Santa Cruz
        return conexiones;
    }

}

