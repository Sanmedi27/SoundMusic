package co.com.soundMusic.Negocio.Regalias.ArtistaEmpresa;
import co.com.soundMusic.Artista.Artista;
import co.com.soundMusic.EmpresaDifusora.EmpresaDifusora;
 import java.sql.SQLException;
import java.util.List;
 /**
 *
 * @author Santiago Medina Pelaez
 */
public interface IArtistaEmpresaDao {
     List<ArtistaEmpresa> obtenerTodoArtistaEmpresa() throws SQLException;
     List<Artista> obtenerArtistasPorEmpresa(int idEmpresaDifusora) throws SQLException;
     List<EmpresaDifusora> obtenerEmpresasPorArtista(int idArtista) throws SQLException;
     ArtistaEmpresa obtenerArtistaEmpresa(int idArtistaEmpresa) throws SQLException;
     void crearArtistaEmpresa(ArtistaEmpresa artistaEmpresa) throws SQLException;
     void eliminarArtistaEmpresa(int idArtistaEmpresa) throws SQLException;
     void actualizarArtistaEmpresa(ArtistaEmpresa artistaEmpresa) throws SQLException;
}