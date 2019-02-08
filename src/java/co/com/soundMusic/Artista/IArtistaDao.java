package co.com.soundMusic.Artista;

import java.util.List;

/**
 *
 * @author Santiago Medina Pelaez
 */
public interface IArtistaDao {

    List<Artista> obtenerArtistas();

    Artista obtenerArtista(int idArtista);

    void crearArtista(Artista artista);

    void eliminarArtista(String status, int idArtista);

    void actualizarArtista(Artista artista);
}
