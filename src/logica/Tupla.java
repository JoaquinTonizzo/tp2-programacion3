package logica;

public class Tupla<A, B> {
    private A primerElemento;
    private B segundoElemento;

    public Tupla(A primero, B segundo) {
        this.primerElemento = primero;
        this.segundoElemento = segundo;
    }

    public A getPrimero() {
        return primerElemento;
    }

    public B getSegundo() {
        return segundoElemento;
    }

}
