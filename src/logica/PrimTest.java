
package logica;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;


public class PrimTest {

	@Test(expected = IllegalArgumentException.class)
	public void grafoNullTest() {
		Prim.AGM(null);
	}

	@Test
	public void grafoVacioTest() {
		GrafoPonderado grafoVacio = generarGrafoVacio();
		GrafoPonderado arbolMinimoVacio = Prim.AGM(grafoVacio);
		assertEquals(0, arbolMinimoVacio.tamano());
	}

	@Test
	public void grafoUnVerticeTest() {
		GrafoPonderado grafoUnVertice = generarGrafoUnVertice();
		GrafoPonderado arbolMinimo = Prim.AGM(grafoUnVertice);
		Assert.iguales(arbolMinimo, grafoUnVertice);
	}

	@Test
	public void grafoDosVerticesConexoTest() {
		GrafoPonderado grafoDosVerticesUnaArista = generarGrafoDosVerticesUnaArista();
		GrafoPonderado arbolMinimo = Prim.AGM(grafoDosVerticesUnaArista);
		Assert.iguales(arbolMinimo, grafoDosVerticesUnaArista);
	}

	@Test
	public void grafoPequeñoTest() {
		GrafoPonderado grafoPequeno = generarGrafoPequeño();
		GrafoPonderado arbolMinimo = Prim.AGM(grafoPequeno);
		Assert.iguales(arbolMinimo, this.agrmGrafoPequeño());
	}
	
	@Test
	public void grafoComplejoTest() {
		GrafoPonderado grafoComplejo = generarGrafoComplejo();
		GrafoPonderado arbolMinimo = Prim.AGM(grafoComplejo);
		Assert.iguales(arbolMinimo, agmGrafoComplejo());
	}

	@Test
	public void encontrarAristaMinimaConGrafoPequeñoTest() {
		GrafoPonderado grafoPequeno = generarGrafoPequeño();
		Set<Integer> visitados = new HashSet<>();
		visitados.add(0);
		Arista aristaMinima = Prim.encontrarAristaMinima(grafoPequeno, visitados);
		assertEquals(new Arista(0, 3, 5), aristaMinima);
	}
	
	@Test
	public void encontrarAristaMinimaConGrafoComplejoTest() {	
		GrafoPonderado grafoComplejo = generarGrafoComplejo();
		Set<Integer> visitados = new HashSet<>();
		visitados.add(0);
		Arista aristaMinima = Prim.encontrarAristaMinima(grafoComplejo, visitados);
		assertEquals(new Arista(0, 2, 6), aristaMinima);
	}
	
	private GrafoPonderado generarGrafoVacio() {
		return new GrafoPonderado(0);
	}

	private GrafoPonderado generarGrafoUnVertice() {
		GrafoPonderado grafo = new GrafoPonderado(1);
		return grafo;
	}
	
	private GrafoPonderado generarGrafoDosVerticesUnaArista() {
		GrafoPonderado grafo = new GrafoPonderado(2);
		grafo.agregarArista(0, 1, 10);
		return grafo;
	}
	
	private GrafoPonderado generarGrafoComplejo() {
		GrafoPonderado grafo = new GrafoPonderado(6);
		grafo.agregarArista(0, 1, 10);
		grafo.agregarArista(0, 2, 6);
		grafo.agregarArista(0, 3, 20);
		grafo.agregarArista(1, 3, 15);
		grafo.agregarArista(2, 3, 14);
		grafo.agregarArista(1, 4, 9);
		grafo.agregarArista(2, 4, 12);
		grafo.agregarArista(3, 5, 7);
		grafo.agregarArista(4, 5, 11);
		return grafo;
	}
	
	private GrafoPonderado generarGrafoPequeño() {
		GrafoPonderado grafo = new GrafoPonderado(4);
		grafo.agregarArista(0, 1, 10);
		grafo.agregarArista(0, 2, 6);
		grafo.agregarArista(0, 3, 5);
		grafo.agregarArista(1, 3, 15);
		grafo.agregarArista(2, 3, 11);
		return grafo;
	}
	
	private GrafoPonderado agmGrafoComplejo() {
		GrafoPonderado grafo = new GrafoPonderado(6);
		grafo.agregarArista(0, 1, 10);
		grafo.agregarArista(0, 2, 6);
		grafo.agregarArista(1, 4, 9);
		grafo.agregarArista(3, 5, 7);
		grafo.agregarArista(4, 5, 11);
		return grafo;
	}
	
	private GrafoPonderado agrmGrafoPequeño() {
		GrafoPonderado grafo = new GrafoPonderado(4);
		grafo.agregarArista(0, 1, 10);
		grafo.agregarArista(0, 2, 6);
		grafo.agregarArista(0, 3, 5);
		return grafo;
	}

}
