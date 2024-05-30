package logica;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class GrafoPonderadoTest {

	@Test(expected = IllegalArgumentException.class)
	public void crearGrafoConCantidadVericesNegativaTest() {
		new GrafoPonderado(-1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void primerVerticeNegativoTest() {
		GrafoPonderado grafo = new GrafoPonderado(5);
		grafo.agregarArista(-1, 3, 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void primerVerticeExcedidoTest() {
		GrafoPonderado grafo = new GrafoPonderado(5);
		grafo.agregarArista(5, 2, 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void segundoVerticeNegativoTest() {
		GrafoPonderado grafo = new GrafoPonderado(5);
		grafo.agregarArista(2, -1, 10);
	}

	@Test(expected = IllegalArgumentException.class)
	public void segundoVerticeExcedidoTest() {
		GrafoPonderado grafo = new GrafoPonderado(5);
		grafo.agregarArista(2, 5, 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void agregarLoopTest() {
		GrafoPonderado grafo = new GrafoPonderado(5);
		grafo.agregarArista(2, 2, 1);
	}

	@Test
	public void aristaExistenteTest() {
		GrafoPonderado grafo = new GrafoPonderado(5);
		grafo.agregarArista(2, 3, 1);
		assertTrue(grafo.existeArista(2, 3));
	}

	@Test
	public void aristaOpuestaTest() {
		GrafoPonderado grafo = new GrafoPonderado(5);
		grafo.agregarArista(2, 3, 1);
		assertTrue(grafo.existeArista(3, 2));
	}

	@Test
	public void aristaInexistenteTest() {
		GrafoPonderado grafo = new GrafoPonderado(5);
		grafo.agregarArista(2, 3, 1);
		assertFalse(grafo.existeArista(1, 4));
	}

	@Test
	public void agregarAristaDosVecesTest() {
		GrafoPonderado grafo = new GrafoPonderado(5);
		grafo.agregarArista(2, 3, 1);
		grafo.agregarArista(2, 3, 1);

		assertTrue(grafo.existeArista(2, 3));
	}

	@Test
	public void eliminarAristaExistenteTest() {
		GrafoPonderado grafo = new GrafoPonderado(5);
		grafo.agregarArista(2, 4 , 1);

		grafo.eliminarArista(2, 4);
		assertFalse(grafo.existeArista(2, 4));
	}

	@Test
	public void eliminarAristaInexistenteTest() {
		GrafoPonderado grafo = new GrafoPonderado(5);
		grafo.eliminarArista(2, 4);
		assertFalse(grafo.existeArista(2, 4));
	}

	@Test
	public void eliminarAristaDosVecesTest() {
		GrafoPonderado grafo = new GrafoPonderado(5);
		grafo.agregarArista(2, 4, 1);

		grafo.eliminarArista(2, 4);
		grafo.eliminarArista(2, 4);
		assertFalse(grafo.existeArista(2, 4));
	}

	@Test(expected = IllegalArgumentException.class)
	public void vecionosVerticeNegativoTest() {
		GrafoPonderado grafo = new GrafoPonderado(5);
		grafo.vecinos(-1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void vecinosVerticeExcedidoTest() {
		GrafoPonderado grafo = new GrafoPonderado(5);
		grafo.vecinos(5);
	}

	@Test
	public void todosAisladosTest() {
		GrafoPonderado grafo = new GrafoPonderado(5);
		assertEquals(0, grafo.vecinos(2).size());
	}

	@Test
	public void verticeUniversalTest() {
		GrafoPonderado grafo = new GrafoPonderado(4);
		grafo.agregarArista(1, 0, 1);
		grafo.agregarArista(1, 2, 2);
		grafo.agregarArista(1, 3, 3);

		int[] esperados = { 0, 2, 3 };
		Assert.iguales(esperados, grafo.vecinos(1));
	}

	@Test
	public void verticeNormalTest() {
		GrafoPonderado grafo = new GrafoPonderado(5);
		grafo.agregarArista(1, 3, 1);
		grafo.agregarArista(2, 3, 2);
		grafo.agregarArista(2, 4, 3);

		int[] esperados = { 1, 2 };
		Assert.iguales(esperados, grafo.vecinos(3));
	}

	@Test
	public void obtenerAristasGrafoSinAristasTest() {
		GrafoPonderado grafo = new GrafoPonderado(5);
		ArrayList<Arista> aristas = grafo.obtenerAristas();
		assertEquals(0, aristas.size());
	}

	@Test
	public void obtenerAristasTest() {
		GrafoPonderado grafo = new GrafoPonderado(5);
		grafo.agregarArista(0, 1, 10);
		grafo.agregarArista(0, 2, 20);
		ArrayList<Arista> aristas = grafo.obtenerAristas();

		ArrayList<Arista> aristasAux = new ArrayList<>();
		aristasAux.add(new Arista(0, 1, 10));
		aristasAux.add(new Arista(0, 2, 20));
		assertEquals(aristasAux, aristas);
	}
	
	@Test
	public void obtenerPesoAristaTest() {
		GrafoPonderado grafo = new GrafoPonderado(5);
		grafo.agregarArista(0, 1, 10);
		assertEquals(10, grafo.pesoArista(0, 1));
	}
	
	@Test
	public void obtenerPesoAristaInvertidaTest() {
		GrafoPonderado grafo = new GrafoPonderado(5);
		grafo.agregarArista(0, 1, 10);
		assertEquals(10, grafo.pesoArista(1, 0));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void obtenerPesoAristaInexisteneteTest() {
		GrafoPonderado grafo = new GrafoPonderado(5);
		grafo.agregarArista(0, 1, 10);
		grafo.pesoArista(1, 2);
	}

	@Test
	public void obtenerTamañoGrafoVacioTest() {
		GrafoPonderado grafo = new GrafoPonderado(0);
		assertEquals(0, grafo.tamano());
	}
	
	@Test
	public void obtenerTamañoGrafoUnVerticeTest() {
		GrafoPonderado grafo = new GrafoPonderado(1);
		assertEquals(1, grafo.tamano());
	}
	
	@Test
	public void obtenerTamañoGrafoConVerticesTest() {
		GrafoPonderado grafo = new GrafoPonderado(10);
		assertEquals(10, grafo.tamano());
	}
	
}
