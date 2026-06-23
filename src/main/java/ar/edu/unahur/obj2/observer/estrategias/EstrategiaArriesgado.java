package ar.edu.unahur.obj2.observer.estrategias;

import ar.edu.unahur.obj2.observer.oferta.Oferta;
import ar.edu.unahur.obj2.observer.producto.Producto;
import ar.edu.unahur.obj2.observer.subastador.Subastador;

public class EstrategiaArriesgado implements EstrategiaOferta {
    private final Subastador subastador;
    public EstrategiaArriesgado(Subastador subastador) { this.subastador = subastador; }

    @Override
    public void realizarOferta(Producto producto) {
        producto.registrarOferta(subastador, new Oferta(subastador, subastador.getUltimaOferta().getValor() + 10));
    }
}