package ar.edu.unahur.obj2.observer.estrategias;

import ar.edu.unahur.obj2.observer.oferta.Oferta;
import ar.edu.unahur.obj2.observer.producto.Producto;
import ar.edu.unahur.obj2.observer.subastador.Subastador;

public class EstrategiaUnico implements EstrategiaOferta {
    private final Subastador subastador;
    private Boolean ofertado = Boolean.FALSE;

    public EstrategiaUnico(Subastador subastador) { this.subastador = subastador; }

    @Override
    public void realizarOferta(Producto producto) {
        if (!ofertado) { 
            ofertado = Boolean.TRUE;
            producto.registrarOferta(subastador, new Oferta(subastador, subastador.getUltimaOferta().getValor() + 10)); 
        }
    }
}