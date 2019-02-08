package co.com.soundMusic.Negocio.Logros;

import java.util.List;

/**
 *
 * @author Santiago Medina Pelaez
 */
public interface ILogroDao {

    List<Logro> obtenerLogros();

    Logro obtenerLogro(int idLogro);

    void crearLogro(Logro logro);

    void eliminarLogro(Logro logro);

    void actualizarLogro(Logro logro);

}
