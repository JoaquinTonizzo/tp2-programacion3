package logica;

import static org.junit.Assert.*;

import org.junit.Test;

public class TuplaTest {

    @Test
    public void obtenerPrimerElementoTest() {
        Tupla<Integer, String> tupla = new Tupla<>(5, "cinco");
        assertEquals(Integer.valueOf(5), tupla.getPrimero());
    }

    @Test
    public void obtenerSegundoElementoTest() {
        Tupla<Integer, String> tupla = new Tupla<>(5, "cinco");
        assertEquals("cinco", tupla.getSegundo());
    }
}
