package co.com.soundMusic.Contacto.Ciudad;

import co.com.soundMusic.Contacto.Pais.Pais;
import co.com.soundMusic.Contacto.Pais.PaisDaoImpl;
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
public class CiudadDaoImpl implements ICiudadDao {
    
    private Connection conexion;
    private PaisDaoImpl paisDao;
    
    public CiudadDaoImpl() {
        conexion = DBUtil.getConexion();
        paisDao = new PaisDaoImpl();
    }
    
    @Override
    public List<Ciudad> obtenerCiudades() throws SQLException {
        List<Ciudad> listaCiudades = new ArrayList<>();
        
        Statement stmt = conexion.createStatement();
        String sql = "SELECT CIU.ID_CIUDAD, CIU.NOMBRE AS NOMBRE_CIUDAD, PA.ID_PAIS, PA.NOMBRE AS NOMBRE_PAIS\n"
                + "FROM CIUDAD CIU INNER JOIN PAIS PA\n"
                + "ON CIU.ID_PAIS = PA.ID_PAIS\n"
                + "ORDER BY CIU.ID_CIUDAD";
        
        ResultSet rs = stmt.executeQuery(sql);
        
        while (rs.next()) {
            Pais pais = new Pais(rs.getInt("ID_PAIS"), rs.getString("NOMBRE_PAIS"));
            
            Ciudad ciudad = new Ciudad(rs.getInt("ID_CIUDAD"), rs.getString("NOMBRE_CIUDAD"), pais);
            
            listaCiudades.add(ciudad);
        }
        
        stmt.close();
        return listaCiudades;
    }
    
    @Override
    public Ciudad obtenerCiudad(int idCiudad) throws SQLException {
        //String sql = "SELECT ID_CIUDAD, NOMBRE, ID_PAIS\n" + "FROM CIUDAD\n" + "WHERE ID_CIUDAD=?\n";
        String sql = "SELECT ID_CIUDAD, NOMBRE AS NOMBRE_CIUDAD, ID_PAIS \n"
                + "FROM CIUDAD\n"
                + "WHERE ID_CIUDAD=?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setInt(1, idCiudad);
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {            
            Ciudad ciudad = new Ciudad(idCiudad, rs.getString("NOMBRE_CIUDAD"),
                    paisDao.obtenerPais(rs.getInt("ID_PAIS")));
            
            return ciudad;
        }
        
        return null;
    }
    
    @Override
    public void crearCiudad(Ciudad ciudad) throws SQLException {
        String sql = "INSERT INTO CIUDAD (NOMBRE, ID_PAIS)\n"
                + "VALUES (?,?)\n";
        PreparedStatement ps = conexion.prepareStatement(sql);
        
        ps.setString(1, ciudad.getNombre());
        ps.setInt(2, ciudad.getPais().getIdPais());
        ps.executeUpdate();
    }
    
    @Override
    public void actualizarCiudad(Ciudad ciudad) throws SQLException {
        String sql = "UPDATE CIUDAD \n"
                + "SET NOMBRE=?\n"
                + "WHERE ID_CIUDAD=?\n";
        PreparedStatement ps = conexion.prepareStatement(sql);
        
        ps.setString(1, ciudad.getNombre());
        ps.setInt(2, ciudad.getIdCiudad());
        ps.executeUpdate();
    }
    
}
