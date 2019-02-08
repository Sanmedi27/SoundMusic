package co.com.soundMusic.Negocio.Regalias;

import co.com.soundMusic.EmpresaDifusora.TipoCosto.CostoActividad;
import co.com.soundMusic.EmpresaDifusora.TipoCosto.CostoActividadDaoImpl;
import co.com.soundMusic.Negocio.Regalias.ArtistaEmpresa.ArtistaEmpresa;
import co.com.soundMusic.Negocio.Regalias.ArtistaEmpresa.ArtistaEmpresaDaoImpl;
import java.sql.Timestamp;

/**
 *
 * @author Santiago Medina Pelaez
 */
public class Regalia {

    private int idRegalia;
    private float totalGanado;
    private int numeroOperaciones;
    private Timestamp fecha;
    private ArtistaEmpresa artistaEmpresa;
    private CostoActividad costo;

    public Regalia() {
        this.artistaEmpresa = new ArtistaEmpresa();
        this.costo = new CostoActividad();
    }

    public Regalia(int idRegalia, float totalGanado, int numeroOperaciones, Timestamp fecha) {
        this.idRegalia = idRegalia;
        this.totalGanado = totalGanado;
        this.numeroOperaciones = numeroOperaciones;
        this.fecha = fecha;
        this.artistaEmpresa = new ArtistaEmpresa();
        this.costo = new CostoActividad();
    }

    public Regalia(int idRegalia, float totalGanado, int numeroOperaciones, Timestamp fecha, ArtistaEmpresa artistaEmpresa, CostoActividad costo) {
        this.idRegalia = idRegalia;
        this.totalGanado = totalGanado;
        this.numeroOperaciones = numeroOperaciones;
        this.fecha = fecha;
        this.artistaEmpresa = artistaEmpresa;
        this.costo = costo;
    }

    public int getIdRegalia() {
        return idRegalia;
    }

    public void setIdRegalia(int idRegalia) {
        this.idRegalia = idRegalia;
    }

    public float getTotalGanado() {
        return totalGanado;
    }

    public void setTotalGanado(float totalGanado) {
        this.totalGanado = totalGanado;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public ArtistaEmpresa getArtistaEmpresa() {
        return artistaEmpresa;
    }

    public void setArtistaEmpresa(ArtistaEmpresa artistaEmpresa) {
        this.artistaEmpresa = artistaEmpresa;
    }

    public CostoActividad getCosto() {
        return costo;
    }

    public void setCosto(CostoActividad costo) {
        this.costo = costo;
    }

    public int getNumeroOperaciones() {
        return numeroOperaciones;
    }

    public void setNumeroOperaciones(int numeroOperaciones) {
        this.numeroOperaciones = numeroOperaciones;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.idRegalia;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Regalia other = (Regalia) obj;
        if (this.idRegalia != other.idRegalia) {
            return false;
        }
        return true;
    }

    public void calcularRegalias() {
        this.setTotalGanado(this.numeroOperaciones * this.costo.getCostoPorOperacion());
    }

    public void obtenerArtistaEmpresa() {
        ArtistaEmpresaDaoImpl daoArtistaEmpresa = new ArtistaEmpresaDaoImpl(true);
        this.setArtistaEmpresa(daoArtistaEmpresa.obtenerArtistaEmpresa(this.artistaEmpresa.getIdArtistaEmpresa()));
    }

    public void obtenerCosto() {
        CostoActividadDaoImpl daoCosto = new CostoActividadDaoImpl(true);
        this.setCosto(daoCosto.obtenerCostoActividad(this.costo.getIdCostoActividad()));
    }
}
