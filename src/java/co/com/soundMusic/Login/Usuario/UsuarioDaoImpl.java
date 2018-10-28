package co.com.soundMusic.Login.Usuario;

import co.com.soundMusic.Contacto.Ciudad.Ciudad;
import co.com.soundMusic.Contacto.Contacto;
import co.com.soundMusic.Contacto.Pais.Pais;
import co.com.soundMusic.Login.CuentaUsuario.UsuarioLogin;
import co.com.soundMusic.Seguridad.Perfiles.Perfil;
import co.com.soundMusic.utilidades.DBUtil;
import java.sql.Connection;
import java.sql.Date;
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

    public UsuarioDaoImpl(Boolean production) {
        isProduction = production;
    }

    @Override
    public List<Usuario> obtenerUsuarios() {
        getConexion();
        List<Usuario> listaUsuarios = new ArrayList<>();
        try {
            //conexion = DBUtil.getConexion();
            stmt = conexion.createStatement();
            rs = stmt.executeQuery(SELECT_USUARIOS);

            while (rs.next()) {
                //Datos Usuario
                Integer idUsuario = rs.getInt("ID_USUARIO");
                String primerNombre = rs.getString("PRIMER_NOMBRE");
                String segundoNombre = validacion(rs.getString("SEGUNDO_NOMBRE"));
                String primerApellido = rs.getString("PRIMER_APELLIDO");
                String segundoApellido = validacion(rs.getString("SEGUNDO_APELLIDO"));
                Date fechaCreacion = rs.getDate("FECHA_CREACION");
                String status = rs.getString("STATUS");
                String genero = validacion(rs.getString("GENERO"));

                //Datos Perfil
                Integer idPerfilUsuario = rs.getInt("ID_PERFIL");
                String nombrePerfil = rs.getString("NOMBRE_PERFIL");

                //Datos Usuario_Login
                Integer idLoginUsuario = rs.getInt("ID_USUARIO_LOGIN");
                String nombreUsLog = rs.getString("NOMBRE_USUARIO");
                String passUsLog = rs.getString("CONTRASENA");

                //Datos Contacto
                Integer idContacto = rs.getInt("ID_CONTACTO");
                String celular = rs.getString("CELULAR");
                String telefono = validacion(rs.getString("TELEFONO"));
                String direccion = validacion(rs.getString("DIRECCION"));
                String barrio = validacion(rs.getString("BARRIO"));
                String email = validacion(rs.getString("EMAIL"));

                Integer idPais = rs.getInt("PAIS");
                String nombrePais = rs.getString("NOMBRE_PAIS");

                Integer idCiudad = rs.getInt("CIUDAD");
                String nombreCiudad = rs.getString("NOMBRE_CIUDAD");

                Perfil perfil = new Perfil(idPerfilUsuario, nombrePerfil);
                UsuarioLogin usuarioLogin = new UsuarioLogin(idLoginUsuario,
                        nombreUsLog, passUsLog);

                Pais pais = new Pais(idPais, nombrePais);
                Ciudad ciudad = new Ciudad(idCiudad, nombreCiudad);
                ciudad.setPais(pais);

                Contacto contacto = new Contacto(idContacto, celular, telefono,
                        direccion, barrio, email);
                contacto.setCiudad(ciudad);

                Usuario usuario = new Usuario(idUsuario, primerNombre, segundoNombre,
                        primerApellido, segundoApellido, fechaCreacion, status,
                        genero);

                usuario.setPerfil(perfil);
                usuario.setUsuarioLogin(usuarioLogin);
                usuario.setContacto(contacto);

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
                String primerNombre = rs.getString("PRIMER_NOMBRE");
                String segundoNombre = validacion(rs.getString("SEGUNDO_NOMBRE"));
                String primerApellido = rs.getString("PRIMER_APELLIDO");
                String segundoApellido = validacion(rs.getString("SEGUNDO_APELLIDO"));
                Date fechaCreacion = rs.getDate("FECHA_CREACION");
                String status = rs.getString("STATUS");
                String genero = validacion(rs.getString("GENERO"));

                //Datos Perfil
                Integer idPerfilUsuario = rs.getInt("ID_PERFIL");
                String nombrePerfil = rs.getString("NOMBRE_PERFIL");

                //Datos Usuario_Login
                Integer idLoginUsuario = rs.getInt("ID_USUARIO_LOGIN");
                String nombreUsLog = rs.getString("NOMBRE_USUARIO");
                String passUsLog = rs.getString("CONTRASENA");

                //Datos Contacto
                Integer idContacto = rs.getInt("ID_CONTACTO");
                String celular = rs.getString("CELULAR");
                String telefono = rs.getString("TELEFONO");
                String direccion = rs.getString("DIRECCION");
                String barrio = rs.getString("BARRIO");
                String email = rs.getString("EMAIL");

                Integer idPais = rs.getInt("PAIS");
                String nombrePais = rs.getString("NOMBRE_PAIS");

                Integer idCiudad = rs.getInt("CIUDAD");
                String nombreCiudad = rs.getString("NOMBRE_CIUDAD");

                Perfil perfil = new Perfil(idPerfilUsuario, nombrePerfil);
                UsuarioLogin usuarioLogin = new UsuarioLogin(idLoginUsuario,
                        nombreUsLog, passUsLog);

                Pais pais = new Pais(idPais, nombrePais);
                Ciudad ciudad = new Ciudad(idCiudad, nombreCiudad);
                ciudad.setPais(pais);

                Contacto contacto = new Contacto(idContacto, celular, telefono,
                        direccion, barrio, email);
                contacto.setCiudad(ciudad);

                usuario = new Usuario(idUsuario, primerNombre, segundoNombre,
                        primerApellido, segundoApellido, fechaCreacion, status,
                        genero);

                usuario.setPerfil(perfil);
                usuario.setUsuarioLogin(usuarioLogin);
                usuario.setContacto(contacto);

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
    public void crearUsuario(Usuario usuario) {
        getConexion();
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
            ps.setInt(10, usuario.getContacto().getIdContacto());
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
            ps.setInt(9, usuario.getContacto().getIdContacto());
            ps.setInt(10, usuario.getIdUsuario());
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
        getConexion();
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
            if (conexion != null) {
                DbUtils.closeQuietly(conexion, stmt, rs);
            }
        }
        return idUsuario;
    }

    static {
        SELECT_USUARIOS = "SELECT US.ID_USUARIO,US.PRIMER_NOMBRE,US.SEGUNDO_NOMBRE,US.PRIMER_APELLIDO,US.SEGUNDO_APELLIDO,\n"
                + "US.FECHA_CREACION,US.STATUS,US.GENERO,US.ID_PERFIL,US.ID_USUARIO_LOGIN,US.ID_CONTACTO,\n"
                + "CONT.CELULAR AS CELULAR, CONT.TELEFONO AS TELEFONO,\n"
                + "CONT.DIRECCION AS DIRECCION, CONT.BARRIO AS BARRIO, CONT.EMAIL AS EMAIL, CIU.ID_CIUDAD AS CIUDAD, \n"
                + "CIU.NOMBRE AS NOMBRE_CIUDAD,\n"
                + "PA.ID_PAIS AS PAIS, PA.NOMBRE AS NOMBRE_PAIS, PER.NOMBRE_PERFIL, US_LO.NOMBRE_USUARIO, US_LO.CONTRASENA\n"
                + "FROM USUARIO US INNER JOIN CONTACTO CONT\n"
                + "ON US.ID_CONTACTO = CONT.ID_CONTACTO\n"
                + "INNER JOIN CIUDAD CIU\n"
                + "ON CONT.ID_CIUDAD = CIU.ID_CIUDAD\n"
                + "INNER JOIN PAIS PA\n"
                + "ON CIU.ID_PAIS = PA.ID_PAIS\n"
                + "INNER JOIN PERFIL PER\n"
                + "ON US.ID_PERFIL = PER.ID_PERFIL\n"
                + "INNER JOIN USUARIO_LOGIN US_LO\n"
                + "ON US.ID_USUARIO_LOGIN = US_LO.ID_USUARIO_LOGIN\n"
                + "ORDER BY ID_USUARIO";

        SELECT_USUARIO_POR_ID = "SELECT US.PRIMER_NOMBRE,US.SEGUNDO_NOMBRE,US.PRIMER_APELLIDO,US.SEGUNDO_APELLIDO,\n"
                + "US.FECHA_CREACION,US.STATUS,US.GENERO,US.ID_PERFIL,US.ID_USUARIO_LOGIN,US.ID_CONTACTO,\n"
                + "CONT.CELULAR AS CELULAR, CONT.TELEFONO AS TELEFONO,\n"
                + "CONT.DIRECCION AS DIRECCION, CONT.BARRIO AS BARRIO, CONT.EMAIL AS EMAIL, CIU.ID_CIUDAD AS CIUDAD, \n"
                + "CIU.NOMBRE AS NOMBRE_CIUDAD,\n"
                + "PA.ID_PAIS AS PAIS, PA.NOMBRE AS NOMBRE_PAIS, PER.NOMBRE_PERFIL, US_LO.NOMBRE_USUARIO, US_LO.CONTRASENA\n"
                + "FROM USUARIO US INNER JOIN CONTACTO CONT\n"
                + "ON US.ID_CONTACTO = CONT.ID_CONTACTO\n"
                + "INNER JOIN CIUDAD CIU\n"
                + "ON CONT.ID_CIUDAD = CIU.ID_CIUDAD\n"
                + "INNER JOIN PAIS PA\n"
                + "ON CIU.ID_PAIS = PA.ID_PAIS\n"
                + "INNER JOIN PERFIL PER\n"
                + "ON US.ID_PERFIL = PER.ID_PERFIL\n"
                + "INNER JOIN USUARIO_LOGIN US_LO\n"
                + "ON US.ID_USUARIO_LOGIN = US_LO.ID_USUARIO_LOGIN \n"
                + "WHERE ID_USUARIO=?";

        INSERT_USUARIO = "INSERT INTO USUARIO (PRIMER_NOMBRE,SEGUNDO_NOMBRE,PRIMER_APELLIDO,SEGUNDO_APELLIDO,\n"
                + "FECHA_CREACION,STATUS,GENERO,ID_PERFIL,ID_USUARIO_LOGIN,ID_CONTACTO)\n"
                + "VALUES (?,?,?,?,?,?,?,?,?,?)";

        UPDATE_STATUS = "UPDATE USUARIO\n"
                + "SET STATUS=?\n"
                + "WHERE ID_USUARIO=?";

        UPDTAE_USUARIO = "UPDATE USUARIO\n"
                + "SET PRIMER_NOMBRE=?,SEGUNDO_NOMBRE=?,PRIMER_APELLIDO=?,SEGUNDO_APELLIDO=?,\n"
                + "STATUS=?, GENERO=?,ID_PERFIL=?,ID_USUARIO_LOGIN=?,ID_CONTACTO=?\n"
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
