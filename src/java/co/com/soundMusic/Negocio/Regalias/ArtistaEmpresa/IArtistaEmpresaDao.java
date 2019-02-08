package co.com.soundMusic.Negocio.Regalias.ArtistaEmpresa;

import java.util.List;

/**
 *
 * @author Santiago Medina Pelaez
 */
public interface IArtistaEmpresaDao {

    List<ArtistaEmpresa> obtenerTodoArtistaEmpresa();

    int obtenerNumeroDeArtistas(int idEmpresaDifusora);

    List<String> obtenerEmpresas(int idArtista);

    ArtistaEmpresa obtenerArtistaEmpresa(int idArtistaEmpresa);

    int crearArtistaEmpresa(ArtistaEmpresa artistaEmpresa);

    void actualizarArtistaEmpresa(ArtistaEmpresa artistaEmpresa);
}
