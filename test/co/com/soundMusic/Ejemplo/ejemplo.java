package co.com.soundMusic.Ejemplo;

import co.com.soundMusic.Login.Usuario.UsuarioDaoImpl;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.oracle.OracleDataTypeFactory;
import org.junit.Test;

/**
 * * * @author Munna * Test class for SalaryCalcutation using DBUNIT *
 */
public class ejemplo extends DatabaseTestCase {

    public static final String TABLE_LOGIN = "salarydetails";
    private FlatXmlDataSet loadedDataSet;
    private UsuarioDaoImpl salaryCalicutation;
    private Connection jdbcConnection;

    /**
     * * Provide a connection to the database * @return IDatabaseConnection
     *
     * @return
     * @throws java.lang.Exception
     */
    @Override
    protected IDatabaseConnection getConnection() throws Exception {
        Class.forName("oracle.jdbc.OracleDriver");
        jdbcConnection = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:XE", "SOUNDMUSIC_TEST", "SOUNDMUSIC2018");

        DatabaseConnection newconection = new DatabaseConnection(jdbcConnection);
        DatabaseConfig config = newconection.getConfig();
        config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new OracleDataTypeFactory());
        return newconection;
    }

    /**
     * * Load the data which will be inserted for the test * @return IDataSet
     *
     * @return
     * @throws java.lang.Exception
     */
    @Override
    protected IDataSet getDataSet() throws Exception {
        /*loadedDataSet = new FlatXmlDataSet(this.getClass().getClassLoader()
                .getResourceAsStream("dbunitData.xml"));*/
        loadedDataSet = new FlatXmlDataSetBuilder().build(new File("test/dataBase/partial.xml"));
        return loadedDataSet;
    }

    /**
     * * Test case for calculator * positive scenario---Valid Employee
     * @throws java.sql.SQLException
     */
    @Test
    public void testCalculator() throws SQLException {
        salaryCalicutation = new UsuarioDaoImpl(false);
        int salary = salaryCalicutation.obtenerUsuario(1).getIdPerfil();
        assertEquals(1, salary);
    }

    /**
     * *Test case for calculator *negative scenario---InValid Employee
     * @throws java.sql.SQLException
     */
    @Test
    public void testCalculatorNeg() throws SQLException {
        salaryCalicutation = new UsuarioDaoImpl(false);
        int salary = salaryCalicutation.obtenerUsuario(1).getIdPerfil();
        assertEquals(1, salary);
    }
}
