package co.com.soundMusic.Login.Usuario;

import java.util.List;

/**
 *
 * @author Santiago Medina Pelaez
 */
public interface IUsuarioDao {

    List<Usuario> obtenerUsuarios();

    Usuario obtenerUsuario(int idUsuario);

    int crearUsuario(Usuario usuario);

    void eliminarUsuario(String status, int idUsuario);

    void actualizarUsuario(Usuario usuario);
}
