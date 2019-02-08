package co.com.soundMusic.Login.Usuario;

import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.List;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.oracle.OracleDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import org.junit.Ignore;

/**
 *
 * @author Santiago Medina Pelaez
 */
public class UsuarioDaoImplTest {

    private UsuarioDaoImpl daoUsuario;
    private IDatabaseTester databaseTester;
    public final static String FILENAME = "test/dataBase/SoundMusicDataSet.xml";

    @Before
    public void setUp() throws Exception {
        daoUsuario = new UsuarioDaoImpl(false);
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
        try {
            DatabaseOperation.CLEAN_INSERT.execute(databaseTester.getConnection(), dataSet);
        } finally {
            databaseTester.getConnection().close();
        }
    }
   

    @Test
    public void testObtenerUsuarios() throws SQLException {
        List<Usuario> resultadoActual = daoUsuario.obtenerUsuarios();
        assertFalse(resultadoActual.isEmpty());
    }
}
