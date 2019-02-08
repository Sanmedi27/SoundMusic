package co.com.soundMusic.Negocio.Regalias;

import co.com.soundMusic.Artista.Artista;
import co.com.soundMusic.EmpresaDifusora.EmpresaDifusora;
import co.com.soundMusic.EmpresaDifusora.TipoCosto.CostoActividad;
import co.com.soundMusic.Negocio.Regalias.ArtistaEmpresa.ArtistaEmpresa;
import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import org.apache.commons.dbutils.DbUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author medinas
 */
public class RegaliaTest {

    ArtistaEmpresa artistaEmpresa;
    Artista artista;
    EmpresaDifusora empresaDifusora;
    CostoActividad costo;

    public RegaliaTest() {
    }

    @Before
    public void setUp() throws SQLException, InvocationTargetException {
        String[] datosArtista = {"SANTIAGO", null, "MEDINA", "PELAEZ", "MC DINA", "M", "A"};
        artista = new Artista(1, datosArtista, Date.valueOf(LocalDate.now()));

        empresaDifusora = new EmpresaDifusora(1, "YOUTUBE", Date.valueOf("2018-06-01"), null, "A", 1, 1);
        artistaEmpresa = new ArtistaEmpresa(1, 1, 1);

        costo = new CostoActividad(1, 10, Date.valueOf("2018-08-08"), null);

    }

    /**
     * Test of calcularRegalias method, of class Regalia.
     */
    @Test
    public void testCalcularRegalias() {
        System.out.println("calcularRegalias");
        Regalia regaliaPrueba = new Regalia(1, 0.0f, 10, Timestamp.valueOf("2018-08-05"), artistaEmpresa, costo);
        float resultadoEsperado = 100F;
        regaliaPrueba.calcularRegalias();
        float resultadoActual = regaliaPrueba.getTotalGanado();
        assertEquals(resultadoEsperado, resultadoActual, 0.0);
    }

}
