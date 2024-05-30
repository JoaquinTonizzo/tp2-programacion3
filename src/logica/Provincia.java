package logica;

public class Provincia {
    private String nombre;
    private double latitud;
    private double longitud;

    public Provincia(String nombre, double latitud, double longitud) {
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public String obtenerNombre() {
        return nombre;
    }

    public double obtenerLatitud() {
        return latitud;
    }

    public double obtenerLongitud() {
        return longitud;
    }
}
