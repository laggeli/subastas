package ar.edu.unahur.obj2.observer.estrategias;

import ar.edu.unahur.obj2.observer.oferta.Oferta;
import ar.edu.unahur.obj2.observer.producto.Producto;
import ar.edu.unahur.obj2.observer.subastador.Subastador;

public class EstrategiaLimite implements EstrategiaOferta {
    private final Subastador subastador;
    private final Double limite;

    public EstrategiaLimite(Double limite, Subastador subastador) {
        this.limite = limite;
        this.subastador = subastador;
    }

    @Override
    public void realizarOferta(Producto producto) {
        if (subastador.getUltimaOferta().getValor() + 10 <= limite) { producto.registrarOferta(subastador, new Oferta(subastador, subastador.getUltimaOferta().getValor() + 10)); }
    }
}