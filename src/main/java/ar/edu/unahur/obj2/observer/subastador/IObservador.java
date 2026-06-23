package ar.edu.unahur.obj2.observer.subastador;

import ar.edu.unahur.obj2.observer.oferta.Oferta;

public interface IObservador {
    void recibirNotificacion(Oferta oferta);
}