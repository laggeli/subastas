package ar.edu.unahur.obj2.observer.producto;

import ar.edu.unahur.obj2.observer.oferta.Oferta;
import ar.edu.unahur.obj2.observer.subastador.Subastador;

public interface IObservable {
    void registrarSubastador(Subastador subastador);
    void sacarSubastador(Subastador subastador);
    void notificar(Oferta oferta);
}