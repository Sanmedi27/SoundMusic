package co.com.soundMusic.Negocio.Regalias.ArtistaEmpresa;

import co.com.soundMusic.Artista.Artista;
import co.com.soundMusic.EmpresaDifusora.EmpresaDifusora;
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
public class ArtistaEmpresaDaoImpl implements IArtistaEmpresaDao {

    //Conexion a la base de datos
    private Connection conexion;
    private Statement stmt;
    private ResultSet rs;
    private Boolean isProduction = true;
    //Constantes con las querys a la base de datos
    private static final String SELECT_ARTISTAS_EMPRESAS;
    private static final String SELECT_ARTISTA_EMPRESA_POR_ID;
    private static final String SELECT_ARTISTAS_POR_ID;
    private static final String INSERT_ARTISTA_EMPRESA;
    private static final String UPDATE_ARTISTA_EMPRESA;
    private static final String SELECT_ARTISTAS_POR_ID_EMPRESA;
    private static final String SELECT_EMPRESAS_POR_ID_ARTISTA;
    private static final String SELECT_ULTIMO_ID;
    private static final String SELECT_ID_ARTISTA_EMPRESA;

    public ArtistaEmpresaDaoImpl(Boolean production) {
        isProduction = production;
    }

    @Override
    public List<ArtistaEmpresa> obtenerTodoArtistaEmpresa() {
        getConexion();
        List<ArtistaEmpresa> lstArtistaEmpresa = new ArrayList<>();
        try {
            stmt = conexion.createStatement();
            rs = stmt.executeQuery(SELECT_ARTISTAS_EMPRESAS);

            while (rs.next()) {
                ArtistaEmpresa artistaEmpresa = new ArtistaEmpresa();
                Artista artista = new Artista();
                EmpresaDifusora empresaDifusora = new EmpresaDifusora();

                artista.setIdArtista(rs.getInt("ID_ARTISTA"));
                empresaDifusora.setIdEmpresaDifusora(rs.getInt("ID_EMPRESA_DIFUSORA"));

                artistaEmpresa.setIdArtistaEmpresa(rs.getInt("ID_ARTISTA_EMPRESA"));
                artistaEmpresa.setArtista(artista);
                artistaEmpresa.setEmpresaDifusora(empresaDifusora);

                lstArtistaEmpresa.add(artistaEmpresa);
            }
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Excepción " + ex.getMessage());
            Logger.getLogger(ArtistaEmpresaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexion != null) {
                    DbUtils.closeQuietly(rs);
                    DbUtils.closeQuietly(stmt);
                    DbUtils.closeQuietly(conexion);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(ArtistaEmpresaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lstArtistaEmpresa;
    }

    @Override
    public ArtistaEmpresa obtenerArtistaEmpresa(int idArtistaEmpresa) {
        getConexion();
        ArtistaEmpresa artistaEmpresa = new ArtistaEmpresa();
        try {
            PreparedStatement ps = conexion.prepareStatement(SELECT_ARTISTA_EMPRESA_POR_ID);
            ps.setInt(1, idArtistaEmpresa);
            rs = ps.executeQuery();

            while (rs.next()) {
                Artista artista = new Artista();
                EmpresaDifusora empresaDifusora = new EmpresaDifusora();

                artista.setIdArtista(rs.getInt("ID_ARTISTA"));
                empresaDifusora.setIdEmpresaDifusora(rs.getInt("ID_EMPRESA_DIFUSORA"));

                artistaEmpresa.setIdArtistaEmpresa(idArtistaEmpresa);
                artistaEmpresa.setArtista(artista);
                artistaEmpresa.setEmpresaDifusora(empresaDifusora);

                return artistaEmpresa;
            }
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Excepción " + ex.getMessage());
            Logger.getLogger(ArtistaEmpresaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexion != null) {
                    DbUtils.closeQuietly(rs);
                    DbUtils.closeQuietly(conexion);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(ArtistaEmpresaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return artistaEmpresa;
    }

    public List<Artista> obtenerArtistas(int idArtista) {
        getConexion();
        List<Artista> lstArtistasPorEmpresa = new ArrayList<>();
        try {
            PreparedStatement ps = conexion.prepareStatement(SELECT_ARTISTAS_POR_ID);
            ps.setInt(1, idArtista);
            rs = ps.executeQuery();

            while (rs.next()) {
                String[] datosArtista = {rs.getString("PRIMER_NOMBRE"), rs.getString("SEGUNDO_NOMBRE"),
                    rs.getString("PRIMER_APELLIDO"), rs.getString("SEGUNDO_APELLIDO"), rs.getString("NOMBRE_ARTISTICO"),
                    rs.getString("GENERO"), rs.getString("STATUS"), rs.getString("RUTA_IMAGEN")};

                Artista artista = new Artista(idArtista, datosArtista, rs.getDate("FECHA_CREACION"));
                lstArtistasPorEmpresa.add(artista);
            }
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Excepción " + ex.getMessage());
            Logger.getLogger(ArtistaEmpresaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexion != null) {
                    DbUtils.closeQuietly(rs);
                    DbUtils.closeQuietly(conexion);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(ArtistaEmpresaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lstArtistasPorEmpresa;
    }

    @Override
    public int crearArtistaEmpresa(ArtistaEmpresa artistaEmpresa) {
        getConexion();
        int id = -1;
        try {
            PreparedStatement ps = conexion.prepareStatement(INSERT_ARTISTA_EMPRESA);

            ps.setInt(1, artistaEmpresa.getArtista().getIdArtista());
            ps.setInt(2, artistaEmpresa.getEmpresaDifusora().getIdEmpresaDifusora());
            ps.executeUpdate();
            id = getUltimoIdArtistaEmpresa();
        } catch (SQLException ex) {
            System.out.println("Excepción " + ex.getMessage());
            Logger.getLogger(ArtistaEmpresaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexion != null) {
                    DbUtils.closeQuietly(conexion);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(ArtistaEmpresaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return id;
    }

    public void insertarArtistaEmpresa(ArtistaEmpresa artistaEmpresa) {
        getConexion();
        try {
            PreparedStatement ps = conexion.prepareStatement(INSERT_ARTISTA_EMPRESA);
            ps.setInt(1, artistaEmpresa.getArtista().getIdArtista());
            ps.setInt(2, artistaEmpresa.getEmpresaDifusora().getIdEmpresaDifusora());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Excepción " + ex.getMessage());
            Logger.getLogger(ArtistaEmpresaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexion != null) {
                    DbUtils.closeQuietly(conexion);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(ArtistaEmpresaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void actualizarArtistaEmpresa(ArtistaEmpresa artistaEmpresa) {
        throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
        // Tools | Templates.
    }

    @Override
    public int obtenerNumeroDeArtistas(int idEmpresaDifusora) {
        getConexion();
        int NumeroDeArtistas = 0;
        try {
            PreparedStatement ps = conexion.prepareStatement(SELECT_ARTISTAS_POR_ID_EMPRESA);
            ps.setInt(1, idEmpresaDifusora);
            rs = ps.executeQuery();
            while (rs.next()) {
                NumeroDeArtistas++;
            }
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Excepción " + ex.getMessage());
            Logger.getLogger(ArtistaEmpresaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexion != null) {
                    DbUtils.closeQuietly(rs);
                    DbUtils.closeQuietly(conexion);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(ArtistaEmpresaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return NumeroDeArtistas;
    }

    public List<ArtistaEmpresa> obtenerArtistaDeEmpresa(int idEmpresaDifusora) {
        getConexion();
        List<ArtistaEmpresa> lstArtistas = new ArrayList<>();
        try {
            PreparedStatement ps = conexion.prepareStatement(SELECT_ARTISTAS_POR_ID_EMPRESA);
            ps.setInt(1, idEmpresaDifusora);
            rs = ps.executeQuery();
            while (rs.next()) {
                ArtistaEmpresa artistaEmpresa = new ArtistaEmpresa();
                artistaEmpresa.setIdArtistaEmpresa(rs.getInt("ID_ARTISTA_EMPRESA"));
                artistaEmpresa.getArtista().setIdArtista(rs.getInt("ID_ARTISTA"));
                artistaEmpresa.getEmpresaDifusora().setIdEmpresaDifusora(rs.getInt("ID_EMPRESA_DIFUSORA"));

                lstArtistas.add(artistaEmpresa);
            }
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Excepción " + ex.getMessage());
            Logger.getLogger(ArtistaEmpresaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexion != null) {
                    DbUtils.closeQuietly(rs);
                    DbUtils.closeQuietly(conexion);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(ArtistaEmpresaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lstArtistas;
    }

    public int[] obtenerNumeroDeArtistas2(List<EmpresaDifusora> lstEmpresa) {
        getConexion();
        int[] numeroArtistas = new int[lstEmpresa.size()];
        try {
            stmt = conexion.createStatement();
            rs = stmt.executeQuery(SELECT_ARTISTAS_EMPRESAS);
            while (rs.next()) {
                for (int i = 0; i < lstEmpresa.size(); i++) {
                    if (lstEmpresa.get(i).getIdEmpresaDifusora() == rs.getInt("ID_EMPRESA_DIFUSORA")) {
                        numeroArtistas[i] = numeroArtistas[i] + 1;
                        break;
                    }
                }
            }
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Excepción " + ex.getMessage());
            Logger.getLogger(ArtistaEmpresaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexion != null) {
                    DbUtils.closeQuietly(rs);
                    DbUtils.closeQuietly(conexion);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(ArtistaEmpresaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return numeroArtistas;
    }

    @Override
    public List<String> obtenerEmpresas(int idArtista) {
        getConexion();
        List<String> lstNombreEmpesas = new ArrayList<>();
        try {
            PreparedStatement ps = conexion.prepareStatement(SELECT_EMPRESAS_POR_ID_ARTISTA);
            ps.setInt(1, idArtista);
            rs = ps.executeQuery();

            while (rs.next()) {
                lstNombreEmpesas.add(rs.getString("NOMBRE"));
            }
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Excepción " + ex.getMessage());
            Logger.getLogger(ArtistaEmpresaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexion != null) {
                    DbUtils.closeQuietly(rs);
                    DbUtils.closeQuietly(conexion);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(ArtistaEmpresaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lstNombreEmpesas;
    }

    private int getUltimoIdArtistaEmpresa() {
        try {
            stmt = conexion.createStatement();
            rs = stmt.executeQuery(SELECT_ULTIMO_ID);
            while (rs.next()) {
                return rs.getInt("CURRVAL");
            }
        } catch (SQLException ex) {
            System.out.println("Excepción " + ex.getMessage());
            Logger.getLogger(ArtistaEmpresaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexion != null) {
                    DbUtils.closeQuietly(rs);
                    DbUtils.closeQuietly(stmt);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(ArtistaEmpresaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return -1;
    }

    public int getIdArtistaEmpresaPorArtistayEmpresa(int idArtista, int IdEmpresa) {
        int id=-1;        
        try {
            getConexion();
            PreparedStatement ps = conexion.prepareStatement(SELECT_ID_ARTISTA_EMPRESA);
            ps.setInt(1, idArtista);
            ps.setInt(2, IdEmpresa);
            rs = ps.executeQuery();
            while (rs.next()) {
                id= rs.getInt("ID_ARTISTA_EMPRESA");
            }
        } catch (SQLException ex) {
            System.out.println("Excepción " + ex.getMessage());
            Logger.getLogger(ArtistaEmpresaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conexion != null) {
                    DbUtils.closeQuietly(rs);
                    DbUtils.closeQuietly(conexion);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(ArtistaEmpresaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return id;
    }

    static {
        SELECT_ARTISTAS_EMPRESAS = "SELECT ID_ARTISTA_EMPRESA, ID_ARTISTA, ID_EMPRESA_DIFUSORA \n"
                + "FROM ARTISTA_EMPRESA \n"
                + "ORDER BY ID_ARTISTA_EMPRESA";

        String SELECT_ARTISTAS_EMPRESAS_C = "SELECT ARTEMP.ID_ARTISTA_EMPRESA, \n"
                + "ARTEMP.ID_ARTISTA, AR.PRIMER_NOMBRE, AR.SEGUNDO_NOMBRE, \n"
                + "AR.PRIMER_APELLIDO, AR.SEGUNDO_APELLIDO, AR.NOMBRE_ARTISTICO, AR.GENERO, \n"
                + "AR.FECHA_CREACION, AR.STATUS, \n"
                + "AR.RUTA_IMAGEN, AR.ID_CONTACTO,\n"
                + "ARTEMP.ID_EMPRESA_DIFUSORA, EMDI.NOMBRE,EMDI.FECHA_CREACION AS FECHA_EMPRESA, \n"
                + "EMDI.FECHA_TERMINACION,EMDI.STATUS, EMDI.ID_TIPO_ACTIVIDAD, \n"
                + "EMDI.ID_CONTACTO, EMDI.ID_COSTO_ACTIVIDAD\n"
                + "FROM ARTISTA_EMPRESA ARTEMP \n"
                + "INNER JOIN ARTISTA AR\n"
                + "ON ARTEMP.ID_ARTISTA=AR.ID_ARTISTA\n"
                + "INNER JOIN EMPRESA_DIFUSORA EMDI\n"
                + "ON ARTEMP.ID_EMPRESA_DIFUSORA=EMDI.ID_EMPRESA_DIFUSORA\n"
                + "ORDER BY ID_ARTISTA_EMPRESA";

        SELECT_ARTISTA_EMPRESA_POR_ID = "SELECT ID_ARTISTA, ID_EMPRESA_DIFUSORA \n"
                + "FROM ARTISTA_EMPRESA \n"
                + "WHERE ID_ARTISTA_EMPRESA=?";

        String SELECT_ARTISTA_EMPRESA_POR_ID_C = "SELECT ARTEMP.ID_ARTISTA_EMPRESA, \n"
                + "ARTEMP.ID_ARTISTA, AR.PRIMER_NOMBRE, AR.SEGUNDO_NOMBRE, \n"
                + "AR.PRIMER_APELLIDO, AR.SEGUNDO_APELLIDO, AR.NOMBRE_ARTISTICO, AR.GENERO, \n"
                + "AR.FECHA_CREACION, AR.STATUS, \n"
                + "AR.RUTA_IMAGEN, AR.ID_CONTACTO,\n"
                + "ARTEMP.ID_EMPRESA_DIFUSORA, EMDI.NOMBRE,EMDI.FECHA_CREACION AS FECHA_EMPRESA, \n"
                + "EMDI.FECHA_TERMINACION,EMDI.STATUS, EMDI.ID_TIPO_ACTIVIDAD, \n"
                + "EMDI.ID_CONTACTO, EMDI.ID_COSTO_ACTIVIDAD\n"
                + "FROM ARTISTA_EMPRESA ARTEMP \n"
                + "INNER JOIN ARTISTA AR\n"
                + "ON ARTEMP.ID_ARTISTA=AR.ID_ARTISTA\n"
                + "INNER JOIN EMPRESA_DIFUSORA EMDI\n"
                + "ON ARTEMP.ID_EMPRESA_DIFUSORA=EMDI.ID_EMPRESA_DIFUSORA\n"
                + "WHERE ID_ARTISTA_EMPRESA=?";

        SELECT_ARTISTAS_POR_ID = "SELECT AR.ID_ARTISTA, AR.PRIMER_NOMBRE, AR.SEGUNDO_NOMBRE, "
                + "AR.PRIMER_APELLIDO, AR.SEGUNDO_APELLIDO, AR.NOMBRE_ARTISTICO, AR.GENERO, \n"
                + "AR.FECHA_CREACION, AR.STATUS, \n"
                + "AR.RUTA_IMAGEN, AR.ID_CONTACTO AS CONTACTO \n"
                + "FROM ARTISTA_EMPRESA AE INNER JOIN ARTISTA AR \n"
                + "ON AE.ID_ARTISTA = AR.ID_ARTISTA \n"
                + "WHERE AE.ID_ARTISTA=? ORDER BY AR.ID_ARTISTA";

        INSERT_ARTISTA_EMPRESA = "INSERT INTO ARTISTA_EMPRESA(ID_ARTISTA,ID_EMPRESA_DIFUSORA)\n"
                + "VALUES (?,?)";

        UPDATE_ARTISTA_EMPRESA = " ";

        SELECT_ARTISTAS_POR_ID_EMPRESA = "SELECT ID_ARTISTA_EMPRESA, ID_ARTISTA, ID_EMPRESA_DIFUSORA \n"
                + "FROM ARTISTA_EMPRESA \n"
                + "WHERE ID_EMPRESA_DIFUSORA = ? ORDER BY ID_ARTISTA";

        SELECT_EMPRESAS_POR_ID_ARTISTA = "SELECT ED.NOMBRE \n"
                + "FROM ARTISTA_EMPRESA AE INNER JOIN EMPRESA_DIFUSORA ED \n"
                + "ON AE.ID_EMPRESA_DIFUSORA = ED.ID_EMPRESA_DIFUSORA \n"
                + "WHERE AE.ID_ARTISTA=? ORDER BY ED.ID_EMPRESA_DIFUSORA";

        SELECT_ULTIMO_ID = "SELECT ARTISTA_EMPRESA_SEQ.CURRVAL\n"
                + "FROM DUAL";

        SELECT_ID_ARTISTA_EMPRESA = "SELECT ID_ARTISTA_EMPRESA\n"
                + "FROM ARTISTA_EMPRESA\n"
                + "WHERE ID_ARTISTA=? AND ID_EMPRESA_DIFUSORA=?";
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
