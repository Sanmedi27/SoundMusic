package co.com.soundMusic.Artista;

import java.io.FileInputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.oracle.OracleDataTypeFactory;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Santiago Medina Pelaez
 */
public class ArtistaDaoImplTest {

    ArtistaDaoImpl daoArtista;
    private IDatabaseTester databaseTester;
    public final static String FILENAME = "test/dataBase/SoundMusicDataSet.xml";

    @Before
    public void setUp() throws Exception {
        daoArtista = new ArtistaDaoImpl(false);
        databaseTester = new JdbcDatabaseTester(
                "oracle.jdbc.OracleDriver",
                "jdbc:oracle:thin:@localhost:1521:XE",
                "DBTest",
                "DBTest");
        DatabaseConfig dbConfig = databaseTester.getConnection().getConfig();
        dbConfig.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new OracleDataTypeFactory());

        IDataSet dataSet = new FlatXmlDataSetBuilder().build(new FileInputStream(FILENAME));

        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();
    }

    /**
     * Test of obtenerArtistas method, of class ArtistaDaoImpl.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testObtenerArtistas() throws Exception {
        List<Artista> resultadoActual = daoArtista.obtenerArtistas();
        assertFalse(resultadoActual.isEmpty());
    }

    /**
     * Test of obtenerArtista method, of class ArtistaDaoImpl.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testObtenerArtista() throws Exception {
        int idArtista = 1;
        String[] datosArtista = {"SANTIAGO", null,
            "MEDINA", "PELAEZ", "MC DINA", "M", "A", null};

        Artista resultadoEsperado = new Artista(1, datosArtista, Date.valueOf("2018-09-23"));
        Artista resultadoActual = daoArtista.obtenerArtista(idArtista);

        assertEquals(resultadoEsperado, resultadoActual);

    }

    /**
     * Test of crearArtista method, of class ArtistaDaoImpl.
     *
     * @throws java.lang.Exception
     */
    @Ignore
    public void testCrearArtista() throws Exception {
        /*System.out.println("crearArtista");
        daoArtista.crearArtista(artistaPrueba);
        int idArtistaPrueba = daoArtista.getUltimmoIdArtista();
        Artista artistaEsperado = artistaPrueba;
        artistaEsperado.setIdArtista(idArtistaPrueba);
        Artista artistaActual = daoArtista.obtenerArtista(idArtistaPrueba);

        assertEquals(artistaEsperado, artistaActual);*/
    }

    /**
     * Test of eliminarArtista method, of class ArtistaDaoImpl.
     *
     * @throws java.lang.Exception
     */
    @Ignore
    public void testEliminarArtista() throws Exception {
        /*System.out.println("eliminarArtista");
        String status = "I";
        int idArtista = 6;
        Artista artistaPruebaElim = artistaPrueba;
        artistaPruebaElim.setStatus("I");
        artistaPruebaElim.setIdArtista(idArtista);
        daoArtista.eliminarArtista(status, idArtista);
        Artista resultadoActual = daoArtista.obtenerArtista(idArtista);

        assertEquals(artistaPruebaElim, resultadoActual);*/
    }

    /**
     * Test of actualizarArtista method, of class ArtistaDaoImpl.
     *
     * @throws java.lang.Exception
     */
    @Ignore
    public void testActualizarArtista() throws Exception {
        /* System.out.println("actualizarArtista");

        daoArtista.actualizarArtista(artistaPruebaAct);
        Artista resultadoActual = daoArtista.obtenerArtista(7);

        assertEquals(artistaPruebaAct, resultadoActual);*/
    }

    /**
     * Test of GetUltimmoIdArtista method, of class ArtistaDaoImpl.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetUltimmoIdArtista() throws Exception {
        String[] datosArtista = {"SANTIAGO", null,
            "MEDINA", "PELAEZ", "MC DINA", "M", "A", null};
        Artista artistaPrueba = new Artista(1, datosArtista, Date.valueOf(LocalDate.now()));
        daoArtista.crearArtista(artistaPrueba);
        List<Artista> pruebaLstArtista = daoArtista.obtenerArtistas();

        int resultadoEsperado = pruebaLstArtista.get(pruebaLstArtista.size() - 1).getIdArtista();
        int resultadoActual = daoArtista.getUltimmoIdArtista();

        assertEquals(resultadoEsperado, resultadoActual);
    }
}
