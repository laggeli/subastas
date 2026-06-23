package ar.edu.unahur.obj2.observer.oferta;

import ar.edu.unahur.obj2.observer.subastador.Subastador;

public class Oferta {
    private final Subastador subastador;
    private final Double valor;

    public Oferta(Subastador subastador, Double valor) {
        this.subastador = subastador;
        this.valor = valor;
    }

    public Double getValor() { return valor; }

    public Subastador getSubastador() { return subastador; }
}