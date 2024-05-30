package logica;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.Set;

public class Assert {
	// Verifica que sean iguales como conjuntos
	public static void iguales(int[] esperado, Set<Integer> obtenido) {
		assertEquals(esperado.length, obtenido.size());

		for (int i = 0; i < esperado.length; ++i)
			assertTrue(obtenido.contains(esperado[i]));
	}
	
	public static void iguales(GrafoPonderado esperado, GrafoPonderado obtenido) {
		assertEquals(esperado.tamano(), obtenido.tamano());
		
		assertEquals(esperado.obtenerAristas().size(), esperado.obtenerAristas().size());
		
		for (Arista arista : esperado.obtenerAristas()) {
			assertTrue(obtenido.obtenerAristas().contains(arista));
		}
	}
}
