package co.com.soundMusic.Seguridad.Perfiles;

import java.util.List;

/**
 *
 * @author Santiago Medina Pelaez
 */
public interface IPerfilDao {

    List<Perfil> obtenerPerfiles();

    Perfil obtenerPerfil(int idPerfil);

    void crearPerfil(Perfil perfil);

    void actualizarPerfil(Perfil perfil);
}
