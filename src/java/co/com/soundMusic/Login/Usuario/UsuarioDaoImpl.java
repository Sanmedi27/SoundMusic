package co.com.soundMusic.Login.Usuario;

import co.com.soundMusic.Login.CuentaUsuario.UsuarioLogin;
import co.com.soundMusic.Seguridad.Perfiles.Perfil;
import co.com.soundMusic.utilidades.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.dbutils.DbUtils;

/**
 *
 * @author Santiago Medina Pelaez
 * @since 0.0.
 */
public class UsuarioDaoImpl implements IUsuarioDao {

    //Conexion a la base de datos
    private Connection conexion;
    private Boolean isProduction = true;
    private Statement stmt;
    private ResultSet rs;
    //Constantes con las querys a la base de datos
    private static final String SELECT_USUARIOS;
    private static final String SELECT_USUARIO_POR_ID;
    private static final String INSERT_USUARIO;
    private static final String UPDATE_STATUS;
    private static final String UPDTAE_USUARIO;
    private static final String SELECT_ULTIMO_ID;

    /**
     *
     * @param production
     */
    public UsuarioDaoImpl(Boolean production) {
        isProduction = production;
    }

    @Override
    public List<Usuario> obtenerUsuarios() {
        getConexion();
        List<Usuario> listaUsuarios = new ArrayList<>();
        try {
            stmt = conexion.createStatement();
            rs = stmt.executeQuery(SELECT_USUARIOS);

            while (rs.next()) {
                Perfil perfil = new Perfil();
                UsuarioLogin usuarioLogin = new UsuarioLogin();
                Usuario usuario = new Usuario();

                //Datos Perfil
                perfil.setIdPerfil(rs.getInt("ID_PERFIL"));
                perfil.setNombrePerfil(rs.getString("NOMBRE_PERFIL"));

                //Datos Usuario_Login
                usuarioLogin.setIdUsuarioLogin(rs.getInt("ID_USUARIO_LOGIN"));
                usuarioLogin.setNombreUsuario(rs.getString("NOMBRE_USUARIO"));
                usuarioLogin.setContrasena(rs.getString("CONTRASENA"));

                //Datos Usuario
                usuario.setIdUsuario(rs.getInt("ID_USUARIO"));
                usuario.setPrimerNombre(rs.getString("PRIMER_NOMBRE"));
                usuario.setSegundoNombre(validacion(rs.getString("SEGUNDO_NOMBRE")));
                usuario.setPrimerApellido(rs.getString("PRIMER_APELLIDO"));
                usuario.setSegundoApellido(validacion(rs.getString("SEGUNDO_APELLIDO")));
                usuario.setFechaCreacion(rs.getDate("FECHA_CREACION"));
                usuario.setStatus(rs.getString("STATUS"));
                usuario.setGenero(validacion(rs.getString("GENERO")));
                usuario.setPerfil(perfil);
                usuario.setUsuarioLogin(usuarioLogin);

                listaUsuarios.add(usuario);
            }
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Excepción " + ex.getMessage());
            Logger.getLogger(UsuarioDaoImpl.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                if (conexion != null) {
                    DbUtils.closeQuietly(rs);
                    DbUtils.closeQuietly(stmt);
                    DbUtils.closeQuietly(conexion);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(UsuarioDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listaUsuarios;
    }

    @Override
    public Usuario obtenerUsuario(int idUsuario) {
        getConexion();
        Usuario usuario = new Usuario();
        try {
            PreparedStatement ps = conexion.prepareStatement(SELECT_USUARIO_POR_ID);
            ps.setInt(1, idUsuario);
            rs = ps.executeQuery();

            while (rs.next()) {
                Perfil perfil = new Perfil();
                UsuarioLogin usuarioLogin = new UsuarioLogin();

                //Datos Perfil
                perfil.setIdPerfil(rs.getInt("ID_PERFIL"));
                perfil.setNombrePerfil(rs.getString("NOMBRE_PERFIL"));

                //Datos Usuario_Login
                usuarioLogin.setIdUsuarioLogin(rs.getInt("ID_USUARIO_LOGIN"));
                usuarioLogin.setNombreUsuario(rs.getString("NOMBRE_USUARIO"));
                usuarioLogin.setContrasena(rs.getString("CONTRASENA"));

                //Datos Usuario
                usuario.setIdUsuario(idUsuario);
                usuario.setPrimerNombre(rs.getString("PRIMER_NOMBRE"));
                usuario.setSegundoNombre(validacion(rs.getString("SEGUNDO_NOMBRE")));
                usuario.setPrimerApellido(rs.getString("PRIMER_APELLIDO"));
                usuario.setSegundoApellido(validacion(rs.getString("SEGUNDO_APELLIDO")));
                usuario.setFechaCreacion(rs.getDate("FECHA_CREACION"));
                usuario.setStatus(rs.getString("STATUS"));
                usuario.setGenero(validacion(rs.getString("GENERO")));
                usuario.setPerfil(perfil);
                usuario.setUsuarioLogin(usuarioLogin);

                return usuario;
            }
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Excepción " + ex.getMessage());
            Logger.getLogger(UsuarioDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexion != null) {
                    DbUtils.close(rs);
                    DbUtils.close(conexion);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException | SQLException ex) {
                Logger.getLogger(UsuarioDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return usuario;
    }

    @Override
    public int crearUsuario(Usuario usuario) {
        getConexion();
        int id = -1;
        try {
            PreparedStatement ps = conexion.prepareStatement(INSERT_USUARIO);

            ps.setString(1, usuario.getPrimerNombre());
            ps.setString(2, usuario.getSegundoNombre());
            ps.setString(3, usuario.getPrimerApellido());
            ps.setString(4, usuario.getSegundoApellido());
            ps.setDate(5, usuario.getFechaCreacion());
            ps.setString(6, usuario.getStatus());
            ps.setString(7, usuario.getGenero());
            ps.setInt(8, usuario.getPerfil().getIdPerfil());
            ps.setInt(9, usuario.getUsuarioLogin().getIdUsuarioLogin());
            ps.executeUpdate();
            id = getUltimoIdUsuario();
        } catch (SQLException ex) {
            System.out.println("Excepción " + ex.getMessage());
            Logger.getLogger(UsuarioDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexion != null) {
                    DbUtils.close(conexion);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException | SQLException ex) {
                Logger.getLogger(UsuarioDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return id;
    }

    @Override
    public void eliminarUsuario(String status, int idUsuario) {
        getConexion();
        try {
            PreparedStatement ps = conexion.prepareStatement(UPDATE_STATUS);
            ps.setString(1, status);
            ps.setInt(2, idUsuario);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Excepción " + ex.getMessage());
            Logger.getLogger(UsuarioDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexion != null) {
                    DbUtils.close(conexion);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException | SQLException ex) {
                Logger.getLogger(UsuarioDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void actualizarUsuario(Usuario usuario) {
        getConexion();
        try {
            PreparedStatement ps = conexion.prepareStatement(UPDTAE_USUARIO);

            ps.setString(1, usuario.getPrimerNombre());
            ps.setString(2, usuario.getSegundoNombre());
            ps.setString(3, usuario.getPrimerApellido());
            ps.setString(4, usuario.getSegundoApellido());
            ps.setString(5, usuario.getStatus());
            ps.setString(6, usuario.getGenero());
            ps.setInt(7, usuario.getPerfil().getIdPerfil());
            ps.setInt(8, usuario.getUsuarioLogin().getIdUsuarioLogin());
            ps.setInt(9, usuario.getIdUsuario());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Excepción " + ex.getMessage());
            Logger.getLogger(UsuarioDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexion != null) {
                    DbUtils.closeQuietly(conexion);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(UsuarioDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public int getUltimoIdUsuario() {
        int idUsuario = -1;
        try {
            PreparedStatement ps = conexion.prepareStatement(SELECT_ULTIMO_ID);
            rs = ps.executeQuery();

            while (rs.next()) {
                idUsuario = rs.getInt("CURRVAL");
                return idUsuario;
            }
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Excepción " + ex.getMessage());
            Logger.getLogger(UsuarioDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexion != null) {
                    DbUtils.close(rs);
                    DbUtils.close(conexion);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException | SQLException ex) {
                Logger.getLogger(UsuarioDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return idUsuario;
    }

    static {
        SELECT_USUARIOS = "SELECT US.ID_USUARIO,US.PRIMER_NOMBRE,US.SEGUNDO_NOMBRE,US.PRIMER_APELLIDO,US.SEGUNDO_APELLIDO,\n"
                + "US.FECHA_CREACION,US.STATUS,US.GENERO,US.ID_PERFIL,US.ID_USUARIO_LOGIN,\n"
                + "PER.NOMBRE_PERFIL, US_LO.NOMBRE_USUARIO, US_LO.CONTRASENA\n"
                + "FROM USUARIO US INNER JOIN PERFIL PER\n"
                + "ON US.ID_PERFIL = PER.ID_PERFIL\n"
                + "INNER JOIN USUARIO_LOGIN US_LO\n"
                + "ON US.ID_USUARIO_LOGIN = US_LO.ID_USUARIO_LOGIN\n"
                + "ORDER BY ID_USUARIO";

        SELECT_USUARIO_POR_ID = "SELECT US.PRIMER_NOMBRE,US.SEGUNDO_NOMBRE,US.PRIMER_APELLIDO,US.SEGUNDO_APELLIDO,\n"
                + "US.FECHA_CREACION,US.STATUS,US.GENERO,US.ID_PERFIL,US.ID_USUARIO_LOGIN,\n"
                + "PER.NOMBRE_PERFIL, US_LO.NOMBRE_USUARIO, US_LO.CONTRASENA\n"
                + "FROM USUARIO US INNER JOIN PERFIL PER\n"
                + "ON US.ID_PERFIL = PER.ID_PERFIL\n"
                + "INNER JOIN USUARIO_LOGIN US_LO\n"
                + "ON US.ID_USUARIO_LOGIN = US_LO.ID_USUARIO_LOGIN \n"
                + "WHERE ID_USUARIO=?";

        INSERT_USUARIO = "INSERT INTO USUARIO (PRIMER_NOMBRE,SEGUNDO_NOMBRE,PRIMER_APELLIDO,SEGUNDO_APELLIDO,\n"
                + "FECHA_CREACION,STATUS,GENERO,ID_PERFIL,ID_USUARIO_LOGIN)\n"
                + "VALUES (?,?,?,?,?,?,?,?,?)";

        UPDATE_STATUS = "UPDATE USUARIO\n"
                + "SET STATUS=?\n"
                + "WHERE ID_USUARIO=?";

        UPDTAE_USUARIO = "UPDATE USUARIO\n"
                + "SET PRIMER_NOMBRE=?,SEGUNDO_NOMBRE=?,PRIMER_APELLIDO=?,SEGUNDO_APELLIDO=?,\n"
                + "STATUS=?, GENERO=?,ID_PERFIL=?,ID_USUARIO_LOGIN=?\n"
                + "WHERE ID_USUARIO=?";

        SELECT_ULTIMO_ID = "SELECT USUARIO_SEQ.CURRVAL\n"
                + "FROM DUAL";
    }

    private String validacion(String aValidar) {
        if (aValidar != null) {
            return aValidar.trim();
        } else {
            return "";
        }
    }

    private void getConexion() {
        if (isProduction) {
            conexion = DBUtil.getConexion();
        } else {
            conexion = DBUtil.getTestConexion();
        }
        stmt = null;
        rs = null;
    }
}
