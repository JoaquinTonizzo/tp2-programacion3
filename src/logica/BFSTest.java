package logica;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.Set;
import org.junit.Test;


public class BFSTest {
	
	@Test(expected=IllegalArgumentException.class)
	public void grafoNullTest() {
		BFS.esConexo(null);
	}

    @Test
    public void grafoVacioTest() {
        GrafoPonderado grafoVacio = new GrafoPonderado(0);
        assertTrue(BFS.esConexo(grafoVacio));
    }

    @Test
    public void conexoUnVerticeTest() {
        GrafoPonderado grafoUnVertice = new GrafoPonderado(1);
        assertTrue(BFS.esConexo(grafoUnVertice));
    }

    @Test
    public void grafoConexoTest() {
        GrafoPonderado grafoConexo = generarGrafoConexo();
        assertTrue(BFS.esConexo(grafoConexo));
    }

    @Test
    public void grafoNoConexoTest() {
        GrafoPonderado grafoNoConexo = generarGrafoNoConexo();
        assertFalse(BFS.esConexo(grafoNoConexo));
    }
    
	@Test
	public void grafoConexoTest2() {
		GrafoPonderado grafo = generarGrafoNoConexo();
		grafo.agregarArista(2, 1, 4);
		grafo.agregarArista(1, 2, 5);
		assertTrue(BFS.esConexo(grafo));
	}

    @Test
    public void alcanzablesTest() {
        GrafoPonderado grafo = generarGrafo1();

        Set<Integer> alcanzables = BFS.alcanzables(grafo, 0);
        int[] esperados = {0, 1, 2, 3};

        Assert.iguales(esperados, alcanzables);
    }
    
	@Test
	public void alcanzablesInconexoTest() {
		GrafoPonderado grafo = generarGrafoNoConexo2();
		
        Set<Integer> alcanzables = BFS.alcanzables(grafo, 1);
        int[] esperados = {0, 1, 2, 3, 4};

        Assert.iguales(esperados, alcanzables);
	}
	
	private GrafoPonderado generarGrafoNoConexo() {
		GrafoPonderado grafoNoConexo = new GrafoPonderado(3);
        grafoNoConexo.agregarArista(0, 1, 1);
		return grafoNoConexo;
	}
	
	private GrafoPonderado generarGrafoConexo() {
		GrafoPonderado grafoConexo = generarGrafoNoConexo();
        grafoConexo.agregarArista(1, 2, 2);
        grafoConexo.agregarArista(0, 2, 3);
		return grafoConexo;
	}
	
	private GrafoPonderado generarGrafo1() {
		GrafoPonderado grafo = new GrafoPonderado(4);
        grafo.agregarArista(0, 1, 1);
        grafo.agregarArista(0, 2, 2);
        grafo.agregarArista(1, 2, 3);
        grafo.agregarArista(2, 3, 4);
		return grafo;
	}
	
	private GrafoPonderado generarGrafoNoConexo2() {
		GrafoPonderado grafo = new GrafoPonderado(7);
		grafo.agregarArista(0, 1, 2);
		grafo.agregarArista(0, 2, 10);
		grafo.agregarArista(1, 2, 20);
		grafo.agregarArista(1, 3, 5);
		grafo.agregarArista(2, 4, 3);
		grafo.agregarArista(3, 4, 50);
		grafo.agregarArista(5, 6, 11);
		
		return grafo;		
	}

	
}

