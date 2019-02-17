package co.com.soundMusic.Seguridad.Permisos;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Santiago Medina Pelaez
 */
public interface IPermisosDao {

    List<Permisos> obtenerPermisos();

    public Permisos obtenerPermiso(int idPermisos);

    public int crearPermiso(Permisos permisos);

    public void actualizarPermiso(Permisos permisos);

    public void eliminarPermiso(int idPermiso);
}
