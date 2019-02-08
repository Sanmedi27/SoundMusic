package co.com.soundMusic.EmpresaDifusora;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Ignore;

/**
 *
 * @author Santiago Medina Pelaez
 */
public class EmpresaDifusoraDaoImplTest {

    EmpresaDifusora empresaPrueba, empresaPruebaAct;
    List<EmpresaDifusora> pruebaLstEmpresa;
    EmpresaDifusoraDaoImpl daoEmpresaDifusora;

    public EmpresaDifusoraDaoImplTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws SQLException {
        daoEmpresaDifusora = new EmpresaDifusoraDaoImpl(false);
        empresaPrueba = new EmpresaDifusora(1, "YOUTUBE", Date.valueOf("2018-06-01"), null, "A",
                1, 1);
        empresaPruebaAct = new EmpresaDifusora(1, "YOUTUBE", Date.valueOf("2018-06-01"), null, "A",
                1, 1);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of obtenerEmpresasDifusoras method, of class EmpresaDifusoraDaoImpl.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testObtenerEmpresasDifusoras() throws Exception {
        System.out.println("obtenerEmpresasDifusoras");
        List<EmpresaDifusora> resultadoEsperado = null;
        List<EmpresaDifusora> resultadoActual = daoEmpresaDifusora.obtenerEmpresasDifusoras();
        assertFalse(resultadoActual.isEmpty());
    }

    /**
     * Test of obtenerEmpresaDifusora method, of class EmpresaDifusoraDaoImpl.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testObtenerEmpresaDifusora() throws Exception {
        System.out.println("obtenerEmpresaDifusora");
        int idEmpresaDifusora = 1;
        EmpresaDifusora resultadoActual = daoEmpresaDifusora.obtenerEmpresaDifusora(idEmpresaDifusora);
        assertEquals(empresaPrueba.getIdEmpresaDifusora(), resultadoActual.getIdEmpresaDifusora());
        assertEquals(empresaPrueba.getNombre(), resultadoActual.getNombre());
        assertEquals(empresaPrueba.getStatus(), resultadoActual.getStatus());

    }

    /**
     * Test of crearEmpresaDifusora method, of class EmpresaDifusoraDaoImpl.
     */
    @Test
    public void testCrearEmpresaDifusora() throws Exception {
        System.out.println("crearEmpresaDifusora");
        daoEmpresaDifusora.crearEmpresaDifusora(empresaPrueba);
        int idEmpresaDifusoraPrueba = daoEmpresaDifusora.getUltimoIdEmpresaDifusora();
        EmpresaDifusora empresaEsperado = empresaPrueba;
        empresaEsperado.setIdEmpresaDifusora(idEmpresaDifusoraPrueba);
        EmpresaDifusora empresaActual = daoEmpresaDifusora.obtenerEmpresaDifusora(idEmpresaDifusoraPrueba);

        assertEquals(empresaEsperado, empresaActual);
    }

    /**
     * Test of eliminarEmpresaDifusora method, of class EmpresaDifusoraDaoImpl.
     */
    @Test
    public void testEliminarEmpresaDifusora() throws Exception {
        System.out.println("eliminarEmpresaDifusora");
        String status = "I";
        int idEmpresaDifusora = 1;
        EmpresaDifusora empresaDifusoraPruebaElm = empresaPrueba;
        empresaDifusoraPruebaElm.setStatus("I");
        daoEmpresaDifusora.eliminarEmpresaDifusora(status, idEmpresaDifusora);
        EmpresaDifusora resultadoActual = daoEmpresaDifusora.obtenerEmpresaDifusora(idEmpresaDifusora);

        assertEquals(empresaDifusoraPruebaElm, resultadoActual);
    }

    /**
     * Test of actualizarEmpresaDifusora method, of class
     * EmpresaDifusoraDaoImpl.
     */
    @Test
    @Ignore
    public void testActualizarEmpresaDifusora() throws Exception {
        System.out.println("actualizarEmpresaDifusora");

        daoEmpresaDifusora.actualizarEmpresaDifusora(empresaPruebaAct);
        EmpresaDifusora resultadoActual = daoEmpresaDifusora.obtenerEmpresaDifusora(1);

        assertEquals(empresaPruebaAct, resultadoActual);
    }

    /**
     * Test of getUltimoIdEmpresaDifusora method, of class
     * EmpresaDifusoraDaoImpl.
     */
    @Test
    public void testGetUltimoIdEmpresaDifusora() throws Exception {
        System.out.println("getUltimoIdEmpresaDifusora");
        EmpresaDifusoraDaoImpl instance = new EmpresaDifusoraDaoImpl(false);
        int expResult = 0;
        int result = instance.getUltimoIdEmpresaDifusora();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
