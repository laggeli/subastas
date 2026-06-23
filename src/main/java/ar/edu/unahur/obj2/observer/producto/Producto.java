package ar.edu.unahur.obj2.observer.producto;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unahur.obj2.observer.excepcion.SubastadorException;
import ar.edu.unahur.obj2.observer.oferta.Oferta;
import ar.edu.unahur.obj2.observer.subastador.Subastador;

public class Producto implements IObservable {
    private List<Subastador> participantes = new ArrayList<>();
    private List<Oferta> ofertas = new ArrayList<>();

    public Producto() {}

    @Override
    public void registrarSubastador(Subastador subastador) { participantes.add(subastador); }

    @Override
    public void sacarSubastador(Subastador subastador) {
        if (participantes.contains(subastador)) { participantes.remove(subastador); }
    }

    public void registrarOferta(Subastador subastador, Oferta oferta) { 
        if (!participantes.contains(subastador)) { throw new SubastadorException("El subastador no participa en la subasta."); }
        ofertas.add(oferta); 
        notificar(oferta);
    }

    @Override
    public void notificar(Oferta oferta) { participantes.forEach(p -> p.recibirNotificacion(oferta)); }

    public void reiniciar() { 
        participantes.clear();
        ofertas.clear();
    }

    public List<Subastador> getParticipantes() { return participantes; }

    public List<Oferta> getOfertas() { return ofertas; }

    public Oferta getUltimaOferta() { return ofertas.get(ofertas.size() - 1); }
}