package co.com.soundMusic.Negocio.Regalias;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Santiago Medina Pelaez
 */
public interface IRegaliaDao {

    List<Regalia> obtenerRegalias();

    Regalia obtenerRegalia(int idRegalia);

    int crearRegalia(Regalia regalia);

    void actualizarRegalia(Regalia regalia) throws SQLException;
}
