package logica;

import static org.junit.Assert.*;

import org.junit.Test;

public class ProvinciaTest {

    @Test
    public void obtenerNombreTest() {
        Provincia provincia = new Provincia("Buenos Aires", -36.6769, -60.5588);
        assertEquals("Buenos Aires", provincia.obtenerNombre());
    }

    @Test
    public void obtenerLatitudTest() {
        Provincia provincia = new Provincia("Buenos Aires", -36.6769, -60.5588);
        assertEquals(-36.6769, provincia.obtenerLatitud(), 0.0001);
    }

    @Test
    public void obtenerLongitudTest() {
        Provincia provincia = new Provincia("Buenos Aires", -36.6769, -60.5588);
        assertEquals(-60.5588, provincia.obtenerLongitud(), 0.0001);
    }
}
