package ar.edu.unahur.obj2.observer.producto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.observer.estrategias.EstrategiaLimite;
import ar.edu.unahur.obj2.observer.estrategias.EstrategiaUnico;
import ar.edu.unahur.obj2.observer.excepcion.SubastadorException;
import ar.edu.unahur.obj2.observer.subastador.Subastador;

public class ProductoTest {
    Producto producto = new Producto();
    Subastador gonzager = new Subastador("gonzager");
    Subastador diazdan = new Subastador("diazdan");
    Subastador martomau = new Subastador("martomau");

    @BeforeEach
    void setUp() {
        producto.reiniciar();
        gonzager.reiniciar();
        diazdan.reiniciar();
        martomau.reiniciar();
        producto.registrarSubastador(gonzager);
        producto.registrarSubastador(martomau);
    }

    private void escenario1() {
        martomau.realizarOferta(producto);
        gonzager.realizarOferta(producto);
        martomau.realizarOferta(producto);
    }

    @Test
    void dadoElEscenario1_cuandoSeVerificaLaUltimaOferta_entoncesLaRecibieronCorrectamente() {
        escenario1();
        assertEquals(gonzager.getUltimaOferta(), martomau.getUltimaOferta());
    }

    @Test
    void dadoElEscenario1_cuandoSeVerificaLaUltimaOferta_entoncesEsDeMartomau() {
        escenario1();
        assertEquals("martomau", gonzager.getUltimaOferta().getSubastador().getNombre());
    }

    @Test
    void dadoElEscenario1_cuandoSeVerificaLaUltimaOferta_entoncesElValorEsDe30() {
        escenario1();
        assertEquals(30.0, gonzager.getUltimaOferta().getValor());
    }

    @Test
    void dadoElEscenario1_cuandoSeVerificaLaCantidadDeOfertasRegistradas_entoncesLasOfertasSon3() {
        escenario1();
        assertEquals(3, producto.getOfertas().size());
    }

    @Test
    void dadoElEscenario1_cuandoSeQuiereAgregarNuevaOferta_entoncesLanzaExcepcionPorqueDiazdanNoEstaRegistrado() {
        escenario1();
        SubastadorException excepcion = assertThrows(SubastadorException.class,() -> diazdan.realizarOferta(producto));

        assertEquals("El subastador no participa en la subasta.", excepcion.getMessage());
    }

    @Test
    void dadoUnSubastador_cuandoSuEstrategiaEsArriesgada_entoncesSiempreOferta() {
        gonzager.realizarOferta(producto);

        assertEquals(1, producto.getOfertas().size());
        assertEquals(10.0, producto.getUltimaOferta().getValor());
    }

    @Test
    void dadoDosSubastadores_cuandoSusEstrategiasSonArriesgadas_entoncesOfertanVariasVeces() {
        gonzager.realizarOferta(producto);
        martomau.realizarOferta(producto);
        gonzager.realizarOferta(producto);

        assertEquals(3, producto.getOfertas().size());
        assertEquals(30.0, producto.getUltimaOferta().getValor());
    }

    @Test
    void dadoUnSubastador_cuandoSuEstrategiaEsUnico_entoncesSoloPuedeOfertarUnaVez() {
        gonzager.cambiarEstrategia(new EstrategiaUnico(gonzager));

        gonzager.realizarOferta(producto);
        gonzager.realizarOferta(producto);
        gonzager.realizarOferta(producto);

        assertEquals(1, producto.getOfertas().size());
    }

    @Test
    void dadoUnSubastador_cuandoSuEstrategiaEsUnico_entoncesGuardaLaPrimeraOferta() {
        gonzager.cambiarEstrategia(new EstrategiaUnico(gonzager));

        gonzager.realizarOferta(producto);

        assertEquals(10.0, producto.getUltimaOferta().getValor());
    }

    @Test
    void dadoUnSubastador_cuandoSuEstrategiaEsLimite_entoncesSoloOfertaSiNoSuperaElLimite() {
        gonzager.cambiarEstrategia(new EstrategiaLimite(30.0, gonzager));

        gonzager.realizarOferta(producto);

        assertEquals(1, producto.getOfertas().size());
        assertEquals(10.0, producto.getUltimaOferta().getValor());
    }

    @Test
    void dadoUnSubastador_cuandoSuEstrategiaEsLimite_entoncesNoOfertaSiSuperaElLimite() {
        gonzager.cambiarEstrategia(new EstrategiaLimite(20.0, gonzager));

        gonzager.realizarOferta(producto);
        martomau.realizarOferta(producto);
        gonzager.realizarOferta(producto);

        assertEquals(2, producto.getOfertas().size());
        assertEquals(20.0, producto.getUltimaOferta().getValor());
    }

    @Test
    void dadoUnaSubasta_cuandoUnoRealizaOferta_entoncesTodosLosParticipantesRecibenLaUltimaOferta() {
        gonzager.realizarOferta(producto);

        assertEquals(10.0, gonzager.getUltimaOferta().getValor());
        assertEquals(10.0, martomau.getUltimaOferta().getValor());
    }

    @Test
    void dadoUnaSubasta_cuandoSeReiniciaElProducto_entoncesLimpiaParticipantesYOfertas() {
        gonzager.realizarOferta(producto);
        producto.reiniciar();

        assertTrue(producto.getParticipantes().isEmpty());
        assertTrue(producto.getOfertas().isEmpty());
    }

    @Test
    void dadoUnaSubasta_cuandoSeReiniciaElProducto_entoncesDejaLaUltimaOfertaEnCero() {
        gonzager.realizarOferta(producto);
        gonzager.reiniciar();

        assertEquals(0.0, gonzager.getUltimaOferta().getValor());
    }
}