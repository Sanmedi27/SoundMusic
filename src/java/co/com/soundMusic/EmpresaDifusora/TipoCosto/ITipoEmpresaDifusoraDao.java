package co.com.soundMusic.EmpresaDifusora.TipoCosto;

import java.util.List;

/**
 *
 * @author Santiago Medina Pelaez
 */
public interface ITipoEmpresaDifusoraDao {

    List<TipoEmpresaDifusora> obtenerTipoEmpresaDifusora();

    TipoEmpresaDifusora obtenerTipoEmpresaDifusora(int idTipoEmpresaDifusora);

    int crearTipoEmpresaDifusora(TipoEmpresaDifusora tipoEmpresaDifusora);

    void actualizarTipoEmpresaDifusora(TipoEmpresaDifusora tipoEmpresaDifusora);
}
