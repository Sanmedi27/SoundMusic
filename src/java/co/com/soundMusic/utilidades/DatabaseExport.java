package co.com.soundMusic.utilidades;

import java.io.FileOutputStream;
import java.sql.Connection;
import org.dbunit.database.AmbiguousTableNameException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.ext.oracle.Oracle10DataTypeFactory;

/**
 *
 * @author Santiago Medina Peláez
 */
public class DatabaseExport {

    private static final String[] TABLAS = {"ALBUM", "PAIS", "CIUDAD", "CONTACTO", "ARTISTA",
        "PERMISO", "LOG_AUDITORIA", "EMPRESA_DIFUSORA", "ARTISTA_EMPRESA", "COSTO_ACTIVITIDAD",
        "LOGRO", "LOGRO_ARTISTA", "PERFIL", "PERFIL_PERMISO", "REGALIA", "TIPO_ACTIVIDAD",
        "USUARIO", "USUARIO_LOGIN"};

    public static void main(String[] args) throws Exception {
        // database connection        
        Class driverClass = Class.forName("oracle.jdbc.OracleDriver");
        //CONEXION A LA BASE DE DATOS PARA PRUEBAS
        Connection jdbcConnection = DBUtil.getTestConexion();
        IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);

        DatabaseConfig config = connection.getConfig();
        config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new Oracle10DataTypeFactory());

        // partial database export
        partialExport(connection);

        // full database export
        fullExport(connection);
    }

    private static void partialExport(IDatabaseConnection connection)
            throws AmbiguousTableNameException, Exception {
        QueryDataSet partialDataSet = new QueryDataSet(connection);
        //partialDataSet.addTable("FOO", "SELECT * FROM TABLE WHERE COL='VALUE'");
        partialDataSet.addTable("ARTISTA_EMPRESA");
        FlatXmlDataSet.write(partialDataSet, new FileOutputStream("test/dataBase/partial.xml"));
    }

    private static void fullExport(IDatabaseConnection connection)
            throws AmbiguousTableNameException, Exception {
        IDataSet fullDataSet = connection.createDataSet(TABLAS);
        FlatXmlDataSet.write(fullDataSet, new FileOutputStream("test/dataBase/full.xml"));
    }

}
