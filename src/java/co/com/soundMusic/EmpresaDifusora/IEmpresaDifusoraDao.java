package co.com.soundMusic.EmpresaDifusora;

import java.util.List;

/**
 *
 * @author Santiago Medina Pelaez
 */
public interface IEmpresaDifusoraDao {

    List<EmpresaDifusora> obtenerEmpresasDifusoras();

    EmpresaDifusora obtenerEmpresaDifusora(int idEmpresaDifusora);

    void crearEmpresaDifusora(EmpresaDifusora empresaDifusora);

    void eliminarEmpresaDifusora(String status, int idEmpresaDifusora);

    void actualizarEmpresaDifusora(EmpresaDifusora empresaDifusora);
}
