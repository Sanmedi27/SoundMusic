package co.com.soundMusic.Seguridad.Permisos;

import co.com.soundMusic.utilidades.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Santiago Medina Pelaez
 */
public class PermisosDaoImpl implements IPermisosDao {

    //Conexion a la base de datos
    private Connection conexion;

    //Constantes con las querys a la base de datos
    private static final String SELECT_PERMISOS;
    private static final String SELECT_PERMISO_POR_ID;
    private static final String INSERT_PERMISO;
    private static final String UPDATE_PERMISO;
    private static final String DELETE_PERMISO;

    public PermisosDaoImpl() {
        conexion = DBUtil.getConexion();
    }

    @Override
    public List<Permisos> obtenerPermisos() throws SQLException {
        List<Permisos> listaPermisos = new ArrayList<>();

        Statement stmt = conexion.createStatement();
        ResultSet rs = stmt.executeQuery(SELECT_PERMISOS);

        while (rs.next()) {
            int idPermisos = rs.getInt("ID_PERMISO");
            String nombrePermiso = rs.getString("NOMBRE_PERMISO");

            Permisos permisos = new Permisos(idPermisos, nombrePermiso);
            listaPermisos.add(permisos);
        }
        stmt.close();
        return listaPermisos;
    }

    @Override
    public Permisos obtenerPermiso(int idPermisos) throws SQLException {
        PreparedStatement ps = conexion.prepareStatement(SELECT_PERMISO_POR_ID);
        ps.setInt(1, idPermisos);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String nombrePermiso = rs.getString("NOMBRE_PERMISO");

            Permisos permisos = new Permisos(idPermisos, nombrePermiso);
            return permisos;
        }
        return null;
    }

    @Override
    public void crearPermiso(Permisos permisos) throws SQLException {
        PreparedStatement ps = conexion.prepareStatement(INSERT_PERMISO);

        ps.setString(1, permisos.getNombrePermiso());
        ps.executeUpdate();
    }

    @Override
    public void actualizarPermiso(Permisos permisos) throws SQLException {
        PreparedStatement ps = conexion.prepareStatement(UPDATE_PERMISO);

        ps.setString(1, permisos.getNombrePermiso());
        ps.setInt(2, permisos.getIdPermiso());
        ps.executeUpdate();
    }

    @Override
    public void eliminarPermiso(int idPermiso) throws SQLException {
        PreparedStatement ps = conexion.prepareStatement(DELETE_PERMISO);
        ps.setInt(1, idPermiso);
        ps.executeUpdate();
    }

    static {
        SELECT_PERMISOS = "SELECT NOMBRE_PERMISO\n"
                + "FROM PERMISO ORDER BY ID_PERMISO";

        SELECT_PERMISO_POR_ID = "SELECT NOMBRE_PERMISO\n"
                + "FROM PERMISO \n"
                + "WHERE ID_PERMISO=?";

        INSERT_PERMISO = "INSERT INTO PERMISO (NOMBRE_PERMISO)\n"
                + "VALUES (?)";

        UPDATE_PERMISO = "UPDATE PERMISO \n"
                + "SET NOMBRE_PERMISO=? \n"
                + "WHERE ID_PERMISO=?";

        DELETE_PERMISO = "DELETE \n"
                + "FROM PERMISO \n"
                + "WHERE ID_PERMISO=?";
    }
}
