package ar.edu.unahur.obj2.observer.subastador;

import ar.edu.unahur.obj2.observer.estrategias.EstrategiaArriesgado;
import ar.edu.unahur.obj2.observer.estrategias.EstrategiaOferta;
import ar.edu.unahur.obj2.observer.oferta.Oferta;
import ar.edu.unahur.obj2.observer.producto.Producto;

public class Subastador implements IObservador {
    private final String nombre;
    private Oferta ultimaOferta = new Oferta(null, 0.0);
    private EstrategiaOferta estrategia = new EstrategiaArriesgado(this);

    public Subastador(String nombre) { this.nombre = nombre; }

    @Override
    public void recibirNotificacion(Oferta oferta) { ultimaOferta = oferta; }

    public void realizarOferta(Producto producto) { estrategia.realizarOferta(producto); }

    public void reiniciar() { ultimaOferta = new Oferta(null, 0.0); }
    
    public Oferta getUltimaOferta() { return ultimaOferta; }

    public String getNombre() { return nombre; }

    public void cambiarEstrategia(EstrategiaOferta estrategia) { this.estrategia = estrategia; }
}