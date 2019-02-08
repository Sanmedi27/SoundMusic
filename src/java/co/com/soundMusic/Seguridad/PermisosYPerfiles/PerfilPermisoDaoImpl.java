package co.com.soundMusic.Seguridad.PermisosYPerfiles;

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
public class PerfilPermisoDaoImpl implements IPerfilPermisoDao {

    //Conexion a la base de datos
    private Connection conexion;

    //Constantes con las querys a la base de datos
    private static final String SELECT_PERFILPERMISOS;
    private static final String SELECT_PERMISOS_PERFIL;

    public PerfilPermisoDaoImpl() {
        conexion = DBUtil.getConexion();
    }

    @Override
    public List<PerfilPermiso> obtenePerfilPermisos() throws SQLException {
        List<PerfilPermiso> listaPerfilPermiso = new ArrayList<>();

        Statement stmt = conexion.createStatement();
        ResultSet rs = stmt.executeQuery(SELECT_PERFILPERMISOS);
        while (rs.next()) {
            int idPerfilPermiso = rs.getInt("ID_PERFIL_PERMISO");
            int idPerfil = rs.getInt("ID_PERFIL");
            int idPermiso = rs.getInt("ID_PERMISO");

            PerfilPermiso prfilPermiso = new PerfilPermiso(idPerfilPermiso, idPerfil, idPermiso);
            listaPerfilPermiso.add(prfilPermiso);
        }
        stmt.close();
        return listaPerfilPermiso;
    }

    @Override
    public List<PerfilPermiso> obtenerPermisosDePerfil(int idPerfil) throws SQLException {
        List<PerfilPermiso> listaPermisosPorPerfil = new ArrayList<>();

        PreparedStatement ps = conexion.prepareStatement(SELECT_PERMISOS_PERFIL);
        ps.setInt(1, idPerfil);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int idPerfilPermiso = rs.getInt("ID_PERFIL_PERMISO");
            int idPermiso = rs.getInt("ID_PERMISO");

            PerfilPermiso prfilPermiso = new PerfilPermiso(idPerfilPermiso, idPerfil, idPermiso);
            listaPermisosPorPerfil.add(prfilPermiso);
        }

        return listaPermisosPorPerfil;
    }

    static {
        SELECT_PERFILPERMISOS = "SELECT ID_PERFIL_PERMISO,ID_PERFIL,ID_PERMISO\n"
                + "FROM PERFIL_PERMISO";

        SELECT_PERMISOS_PERFIL = "SELECT ID_PERFIL_PERMISO,ID_PERFIL,ID_PERMISO\n"
                + "FROM PERFIL_PERMISO\n"
                + "WHERE ID_PERFIL=?";
    }
}
