package logica;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class AristaTest {

    @Test
    public void obtenerVertice1Test() {
        Arista arista = new Arista(1, 2, 3);
        assertEquals(1, arista.obtenerVertice1());
    }

    @Test
    public void obtenerVertice2Test() {
        Arista arista = new Arista(1, 2, 3);
        assertEquals(2, arista.obtenerVertice2());
    }

    @Test
    public void obtenerPesoTest() {
        Arista arista = new Arista(1, 2, 3);
        assertEquals(3, arista.obtenerPeso());
    }
    
    @Test
    public void equalsIgualesTest() {
        Arista arista = new Arista(1, 2, 3);
        Arista arista2 = new Arista(1, 2, 3);
        assertTrue(arista.equals(arista2));
    }
    
    @Test
    public void equalsIgualesInvertidaTest() {
        Arista arista = new Arista(1, 2, 3);
        Arista arista2 = new Arista(2, 1, 3);
        assertTrue(arista.equals(arista2));
    }
    
    @Test
    public void equalsDistintosTest() {
        Arista arista = new Arista(1, 2, 3);
        Arista arista2 = new Arista(1, 5, 3);
        assertFalse(arista.equals(arista2));
    }
}
