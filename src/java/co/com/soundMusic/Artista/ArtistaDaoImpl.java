package co.com.soundMusic.Artista;

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
 */
public class ArtistaDaoImpl implements IArtistaDao {

    //Conexion a la base de datos
    private Connection conexion;
    private Boolean isProduction = true;
    private Statement stmt;
    private ResultSet rs;

    //Constantes con las querys a la base de datos
    private static final String SELECT_ARTISTAS;
    private static final String SELECT_ID_ARTISTAS;
    private static final String SELECT_ARTISTA_POR_ID;
    private static final String INSERT_ARTISTA;
    private static final String UPDATE_ARTISTA;
    private static final String UPDATE_STATUS;
    private static final String SELECT_ULTIMO_ID;

    public ArtistaDaoImpl(Boolean production) {
        isProduction = production;
    }

    @Override
    public List<Artista> obtenerArtistas() {
        getConexion();
        List<Artista> listaArtistas = new ArrayList<>();
        try {
            stmt = conexion.createStatement();
            rs = stmt.executeQuery(SELECT_ARTISTAS);

            while (rs.next()) {
                Artista artista = new Artista();

                //Crear Artista
                artista.setIdArtista(rs.getInt("ID_ARTISTA"));
                artista.setPrimerNombre(rs.getString("PRIMER_NOMBRE"));
                artista.setSegundoNombre(validacion(rs.getString("SEGUNDO_NOMBRE")));
                artista.setPrimerApellido(rs.getString("PRIMER_APELLIDO"));
                artista.setSegundoApellido(validacion(rs.getString("SEGUNDO_APELLIDO")));
                artista.setNombreArtistico(rs.getString("NOMBRE_ARTISTICO"));
                artista.setGenero(validacion(rs.getString("GENERO")));
                artista.setFechaCreacion(rs.getDate("FECHA_CREACION"));
                artista.setStatus(rs.getString("STATUS"));
                artista.setRutaImagen(validacion(rs.getString("RUTA_IMAGEN")));

                listaArtistas.add(artista);
            }

        } catch (SQLException | NullPointerException ex) {
            System.out.println("Excepción " + ex.getMessage());
            Logger.getLogger(ArtistaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexion != null) {
                    DbUtils.closeQuietly(rs);
                    DbUtils.closeQuietly(stmt);
                    DbUtils.closeQuietly(conexion);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(ArtistaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listaArtistas;
    }

    @Override
    public Artista obtenerArtista(int idArtista) {
        getConexion();
        Artista artista = new Artista();
        try {
            PreparedStatement ps = conexion.prepareStatement(SELECT_ARTISTA_POR_ID);
            ps.setInt(1, idArtista);
            rs = ps.executeQuery();

            while (rs.next()) {
                //Crear Artista
                artista.setIdArtista(idArtista);
                artista.setPrimerNombre(rs.getString("PRIMER_NOMBRE"));
                artista.setSegundoNombre(validacion(rs.getString("SEGUNDO_NOMBRE")));
                artista.setPrimerApellido(rs.getString("PRIMER_APELLIDO"));
                artista.setSegundoApellido(validacion(rs.getString("SEGUNDO_APELLIDO")));
                artista.setNombreArtistico(rs.getString("NOMBRE_ARTISTICO"));
                artista.setGenero(validacion(rs.getString("GENERO")));
                artista.setFechaCreacion(rs.getDate("FECHA_CREACION"));
                artista.setStatus(rs.getString("STATUS"));
                artista.setRutaImagen(validacion(rs.getString("RUTA_IMAGEN")));

                return artista;
            }
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Excepción " + ex.getMessage());
            Logger.getLogger(ArtistaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexion != null) {
                    DbUtils.closeQuietly(rs);
                    DbUtils.closeQuietly(conexion);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(ArtistaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return artista;
    }

    @Override
    public void crearArtista(Artista artista) {
        getConexion();
        try {
            PreparedStatement ps = conexion.prepareStatement(INSERT_ARTISTA);

            ps.setString(1, artista.getPrimerNombre());
            ps.setString(2, artista.getSegundoNombre());
            ps.setString(3, artista.getPrimerApellido());
            ps.setString(4, artista.getSegundoApellido());
            ps.setString(5, artista.getNombreArtistico());
            ps.setString(6, artista.getGenero());            
            ps.setDate(7, artista.getFechaCreacion());
            ps.setString(8, artista.getStatus());
            ps.setString(9, artista.getRutaImagen());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Excepción " + ex.getMessage());
            Logger.getLogger(ArtistaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexion != null) {
                    DbUtils.closeQuietly(conexion);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(ArtistaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void eliminarArtista(String status, int idArtista) {
        getConexion();
        try {
            PreparedStatement ps = conexion.prepareStatement(UPDATE_STATUS);
            ps.setString(1, status);
            ps.setInt(2, idArtista);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Excepción " + ex.getMessage());
            Logger.getLogger(ArtistaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexion != null) {
                    DbUtils.closeQuietly(conexion);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(ArtistaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void actualizarArtista(Artista artista) {
        getConexion();
        try {
            PreparedStatement ps = conexion.prepareStatement(UPDATE_ARTISTA);

            ps.setString(1, artista.getPrimerNombre());
            ps.setString(2, artista.getSegundoNombre());
            ps.setString(3, artista.getPrimerApellido());
            ps.setString(4, artista.getSegundoApellido());
            ps.setString(5, artista.getNombreArtistico());
            ps.setString(6, artista.getGenero());
            ps.setString(7, artista.getStatus());
            ps.setString(8, artista.getRutaImagen());
            ps.setInt(9, artista.getIdArtista());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Excepción " + ex.getMessage());
            Logger.getLogger(ArtistaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexion != null) {
                    DbUtils.closeQuietly(conexion);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(ArtistaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public int getUltimmoIdArtista() {
        int idArtista = -1;
        try {
            stmt = conexion.createStatement();
            rs = stmt.executeQuery(SELECT_ULTIMO_ID);
            while (rs.next()) {
                idArtista = rs.getInt("CURRVAL");
                return idArtista;
            }
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Excepción " + ex.getMessage());
            Logger.getLogger(ArtistaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexion != null) {
                    DbUtils.closeQuietly(rs);
                    DbUtils.closeQuietly(stmt);
                    DbUtils.closeQuietly(conexion);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(ArtistaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return idArtista;
    }

    public int getNumeroDeArtistas() {
        int artistas = 0;
        getConexion();
        try {
            stmt = conexion.createStatement();
            rs = stmt.executeQuery(SELECT_ID_ARTISTAS);
            while (rs.next()) {
                artistas++;
            }
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Excepción " + ex.getMessage());
            Logger.getLogger(ArtistaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexion != null) {
                    DbUtils.closeQuietly(rs);
                    DbUtils.closeQuietly(stmt);
                    DbUtils.closeQuietly(conexion);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(ArtistaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return artistas;
    }

    static {
        SELECT_ARTISTAS = "SELECT ID_ARTISTA, PRIMER_NOMBRE, SEGUNDO_NOMBRE, \n"
                + "PRIMER_APELLIDO, SEGUNDO_APELLIDO, NOMBRE_ARTISTICO, GENERO, \n"
                + "FECHA_CREACION, STATUS, \n"
                + "RUTA_IMAGEN                \n"
                + "FROM ARTISTA\n"
                + "ORDER BY ID_ARTISTA";

        SELECT_ARTISTA_POR_ID = "SELECT ID_ARTISTA, PRIMER_NOMBRE, SEGUNDO_NOMBRE, \n"
                + "PRIMER_APELLIDO, SEGUNDO_APELLIDO, NOMBRE_ARTISTICO, GENERO, \n"
                + "FECHA_CREACION, STATUS, RUTA_IMAGEN\n"
                + "FROM ARTISTA AR\n"
                + "WHERE ID_ARTISTA=?";

        INSERT_ARTISTA = "INSERT INTO ARTISTA (PRIMER_NOMBRE,SEGUNDO_NOMBRE,PRIMER_APELLIDO,SEGUNDO_APELLIDO,\n"
                + "NOMBRE_ARTISTICO,GENERO,FECHA_CREACION,STATUS,RUTA_IMAGEN)\n"
                + "VALUES (?,?,?,?,?,?,?,?,?)";

        UPDATE_STATUS = "UPDATE ARTISTA\n"
                + "SET STATUS=? \n"
                + "WHERE ID_ARTISTA=?";

        UPDATE_ARTISTA = "UPDATE ARTISTA\n"
                + "SET PRIMER_NOMBRE=?,SEGUNDO_NOMBRE=?,PRIMER_APELLIDO=?,SEGUNDO_APELLIDO=?,\n"
                + "NOMBRE_ARTISTICO=?,GENERO=?,STATUS=?, RUTA_IMAGEN=?\n"
                + "WHERE ID_ARTISTA=?";

        SELECT_ULTIMO_ID = "SELECT ARTISTA_SEQ.CURRVAL\n"
                + "FROM DUAL";

        SELECT_ID_ARTISTAS = "SELECT ID_ARTISTA "
                + "FROM ARTISTA";
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
