package co.com.soundMusic.EmpresaDifusora.TipoCosto;

/**
 *
 * @author Santiago Medina Pelaez
 */
public interface ICostoActividadDao {

    CostoActividad obtenerCostoActividad(int idCostoActividad);

    int crearCostoActividad(CostoActividad costoActividad);

    void actualizarCostoActividad(CostoActividad costoActividad);

    void eliminarCostoActividad(CostoActividad costoActividad);

    int getUltimoIdCostoActividad();
}
