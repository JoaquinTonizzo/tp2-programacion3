package logica;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class GeneradorGrafoArgentinaTest {

    @Test
    public void obtenerProvinciasTest() {
        GeneradorGrafoArgentina generador = new GeneradorGrafoArgentina();
        List<Provincia> provincias = generador.obtenerProvincias();
        assertEquals(24, provincias.size());
    }
	
    @Test
    public void obtenerConexionesTest() {
        GeneradorGrafoArgentina generador = new GeneradorGrafoArgentina();
        List<Tupla<Integer, Integer>> conexiones = generador.obtenerConexionesProvincias();
        assertEquals(45, conexiones.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void generarCeroRegionesTest() {
    	 GeneradorGrafoArgentina generador = new GeneradorGrafoArgentina();
         generador.generarRegiones(0);             
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void generarVeinticincoRegionesTest() {
    	GeneradorGrafoArgentina generador = new GeneradorGrafoArgentina();
        generador.generarRegiones(25); 
    }
    
    @Test
    public void generarUnaRegionTest() {
    	GeneradorGrafoArgentina generador = generarGrafoArgentina();
    	cargarAristasGenerador(generador);
        generador.generarRegiones(1); 
        assertEquals(23, generador.obtenerAristas().size());
    }
    
    @Test
    public void generarCincoRegionTest() {
    	GeneradorGrafoArgentina generador = generarGrafoArgentina();
    	cargarAristasGenerador(generador);
        generador.generarRegiones(5); 
        assertEquals(19, generador.obtenerAristas().size());
    }
    
    @Test
    public void generarVeinticuatroRegionTest() {
    	GeneradorGrafoArgentina generador = generarGrafoArgentina();
    	cargarAristasGenerador(generador);
        generador.generarRegiones(24); 
        assertEquals(0, generador.obtenerAristas().size());
    }

    @Test
    public void agregarAristaTest() {
        GeneradorGrafoArgentina generador = new GeneradorGrafoArgentina();
        generador.agregarArista(0, 1, 1);
        assertTrue(generador.obtenerAristas().size() == 1);
    }

    @Test
    public void eliminarAristaTest() {
        GeneradorGrafoArgentina generador = new GeneradorGrafoArgentina();
        generador.agregarArista(0, 1, 1);
        generador.eliminarArista(0, 1);
        assertTrue(generador.obtenerAristas().isEmpty());
    }

    @Test
    public void obtenerIndiceProvinciaExistenteTest() {
        GeneradorGrafoArgentina generador = generarGrafoArgentina();
        int indice = generador.obtenerIndiceProvincia("Buenos Aires");
        assertTrue(indice == 16);
    }
    
    @Test
    public void obtenerIndiceProvinciaInexistenteTest() {
        GeneradorGrafoArgentina generador = generarGrafoArgentina();
        int indice = generador.obtenerIndiceProvincia("Nada");
        assertTrue(indice == -1);
    }

    @Test
    public void obtenerProvinciaExistentePorIndiceCeroTest() {
        GeneradorGrafoArgentina generador = generarGrafoArgentina();
        Provincia provincia = generador.obtenerProvinciaPorIndice(0);
        assertTrue(provincia.obtenerNombre().equals("Jujuy"));
    }
    
    @Test
    public void obtenerProvinciaExistentePorIndiceTopeTest() {
        GeneradorGrafoArgentina generador = generarGrafoArgentina();
        Provincia provincia = generador.obtenerProvinciaPorIndice(23);
        assertTrue(provincia.obtenerNombre().equals("Tierra del Fuego"));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void obtenerProvinciaPorIndiceNegativoTest() {
    	GeneradorGrafoArgentina generador = generarGrafoArgentina();
        generador.obtenerProvinciaPorIndice(-1);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void obtenerProvinciaPorIndiceExcedidoTest() {
    	GeneradorGrafoArgentina generador = generarGrafoArgentina();
        generador.obtenerProvinciaPorIndice(24);
    }
    
    @Test
    public void cantidadRegionesValidaVeinticincoTest() {
        GeneradorGrafoArgentina generador = generarGrafoArgentina();
        assertFalse(generador.verificarCantidadDeRegionesValida(25));
    }

    @Test
    public void cantidadRegionesValidaUnoTest() {
        GeneradorGrafoArgentina generador = generarGrafoArgentina();
        assertTrue(generador.verificarCantidadDeRegionesValida(1));
    }

    @Test
    public void cantidadRegionesValidaVeinticuatroTest() {
        GeneradorGrafoArgentina generador = generarGrafoArgentina();
        assertTrue(generador.verificarCantidadDeRegionesValida(24));
    }
    

    @Test
    public void eliminarAristaMayorPesoTest() {
        GeneradorGrafoArgentina generador = generarGrafoArgentina();  
        generador.eliminarAristaMayorPeso();             
        assertFalse(generador.obtenerAristas().contains(new Arista(23, 22, 400)));

    }
    
    @Test
    public void cloneTest() {
        GeneradorGrafoArgentina generador = generarGrafoArgentina();
        GeneradorGrafoArgentina generadorAux = generador.clonar();
        assertEquals(generadorAux.obtenerAristas(), generador.obtenerAristas());
    }
    
    @Test
    public void aristasEliminadasEsperadasTest() {
        GeneradorGrafoArgentina generador = generarGrafoArgentina();
        generador.generarRegiones(2);
        assertFalse(generador.obtenerAristas().contains(new Arista(23,22,400)));
    	
    }
    
    @Test
    public void aristasEliminadasEsperadas2Test() {
        GeneradorGrafoArgentina generador = generarGrafoArgentina2();
        generador.generarRegiones(3);
        assertFalse(generador.obtenerAristas().contains(new Arista(16,17,500)));
        assertFalse(generador.obtenerAristas().contains(new Arista(22,23,600)));

    }
    
    @Test
    public void aristasEliminadasEsperadas3Test() {
        GeneradorGrafoArgentina generador = generarGrafoArgentina2();
        generador.generarRegiones(4);
        assertFalse(generador.obtenerAristas().contains(new Arista(0,1,400)));
        assertFalse(generador.obtenerAristas().contains(new Arista(16,17,500)));
        assertFalse(generador.obtenerAristas().contains(new Arista(22,23,600)));
    }
    
    private void cargarAristasGenerador(GeneradorGrafoArgentina generador) {
		for (Tupla<Integer, Integer> conexion : generador.obtenerConexionesProvincias()) {
            generador.agregarArista(conexion.getPrimero(), conexion.getSegundo(), 0); 
        }
	}
    
    private GeneradorGrafoArgentina generarGrafoArgentina() {
    	GeneradorGrafoArgentina grafo = new GeneradorGrafoArgentina();
    	//Jujuy
    	grafo.agregarArista(0,1,1);
    	//Salta
    	grafo.agregarArista(1,3,1);
    	grafo.agregarArista(1,2,5);
    	grafo.agregarArista(1,6,2);
    	grafo.agregarArista(1,12,1);
    	grafo.agregarArista(1,13,5);
    	//Tucuman
    	grafo.agregarArista(2,3,3);
    	grafo.agregarArista(2,6,1);
    	//Catamarca
    	grafo.agregarArista(3,4,1);
    	grafo.agregarArista(3,9,2);
    	grafo.agregarArista(3,6,1);
    	//Santiago Del Estero
    	grafo.agregarArista(6,10,1);
    	grafo.agregarArista(6,12,1);
    	grafo.agregarArista(6,9,1);
    	//Chaco
    	grafo.agregarArista(12,10,1);
    	grafo.agregarArista(12,14,3);
    	grafo.agregarArista(12,13,1);
    	//Corrientes
    	grafo.agregarArista(14,10,1);
    	grafo.agregarArista(14,11,5);
    	grafo.agregarArista(14,15,1);
    	//La Rioja
    	grafo.agregarArista(4,5,1);
    	grafo.agregarArista(4,8,1);
    	grafo.agregarArista(4,9,5);
    	//San Juan
    	grafo.agregarArista(5,7,1);
    	grafo.agregarArista(5,8,5);
    	//Mendoza
    	grafo.agregarArista(7,8,1);
    	grafo.agregarArista(7,18,2);
    	grafo.agregarArista(7,20,1);
    	//San Luis
    	grafo.agregarArista(8,9,1);
    	grafo.agregarArista(8,18,1);
    	//Cordoba
    	grafo.agregarArista(9,10,1);
    	grafo.agregarArista(9,16,3);
    	grafo.agregarArista(9,18,1);
    	//Santa Fe
    	grafo.agregarArista(10,16,3);
    	grafo.agregarArista(10,11,1);
    	//Buenos Aires
    	grafo.agregarArista(16,11,1);
    	grafo.agregarArista(16,18,2);
    	grafo.agregarArista(16,19,4);
    	grafo.agregarArista(16,17,1);
    	//La Pampa
    	grafo.agregarArista(18,19,1);
    	grafo.agregarArista(18,20,2);
    	//Rio Negro
    	grafo.agregarArista(19,20,4);
    	//Chubut
    	grafo.agregarArista(21,19,1);
    	//Santa Cruz
    	grafo.agregarArista(22,19,3);
    	//Tierra del Fuego
    	grafo.agregarArista(23,22,400);
    	return grafo;
    }
    
 private GeneradorGrafoArgentina generarGrafoArgentina2() {
    	GeneradorGrafoArgentina grafo = new GeneradorGrafoArgentina();
    	//Jujuy
    	grafo.agregarArista(0,1,400);
    	//Salta
    	grafo.agregarArista(1,3,1);
    	grafo.agregarArista(1,2,2);
    	grafo.agregarArista(1,6,2);
    	grafo.agregarArista(1,12,1);
    	grafo.agregarArista(1,13,5);
    	//Tucuman
    	grafo.agregarArista(2,3,3);
    	grafo.agregarArista(2,6,1);
    	//Catamarca
    	grafo.agregarArista(3,4,1);
    	grafo.agregarArista(3,9,2);
    	grafo.agregarArista(3,6,1);
    	//Santiago Del Estero
    	grafo.agregarArista(6,10,1);
    	grafo.agregarArista(6,12,1);
    	grafo.agregarArista(6,9,1);
    	//Chaco
    	grafo.agregarArista(12,10,1);
    	grafo.agregarArista(12,14,2);
    	grafo.agregarArista(12,13,1);
    	//Corrientes
    	grafo.agregarArista(14,10,1);
    	grafo.agregarArista(14,11,5);
    	grafo.agregarArista(14,15,1);
    	//La Rioja
    	grafo.agregarArista(4,5,1);
    	grafo.agregarArista(4,8,2);
    	grafo.agregarArista(4,9,5);
    	//San Juan
    	grafo.agregarArista(5,7,1);
    	grafo.agregarArista(5,8,5);
    	//Mendoza
    	grafo.agregarArista(7,8,1);
    	grafo.agregarArista(7,18,2);
    	grafo.agregarArista(7,20,1);
    	//San Luis
    	grafo.agregarArista(8,9,2);
    	grafo.agregarArista(8,18,1);
    	//Cordoba
    	grafo.agregarArista(9,10,1);
    	grafo.agregarArista(9,16,3);
    	grafo.agregarArista(9,18,1);
    	//Santa Fe
    	grafo.agregarArista(10,16,3);
    	grafo.agregarArista(10,11,2);
    	//Buenos Aires
    	grafo.agregarArista(16,11,1);
    	grafo.agregarArista(16,18,2);
    	grafo.agregarArista(16,19,2);
    	grafo.agregarArista(16,17,500);
    	//La Pampa
    	grafo.agregarArista(18,19,1);
    	grafo.agregarArista(18,20,2);
    	//Rio Negro
    	grafo.agregarArista(19,20,4);
    	//Chubut
    	grafo.agregarArista(21,19,1);
    	//Santa Cruz
    	grafo.agregarArista(22,19,3);
    	//Tierra del Fuego
    	grafo.agregarArista(23,22,600);
    	return grafo;
    }

}
