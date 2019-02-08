package co.com.soundMusic.EmpresaDifusora;

import co.com.soundMusic.EmpresaDifusora.TipoCosto.CostoActividad;
import co.com.soundMusic.EmpresaDifusora.TipoCosto.TipoEmpresaDifusora;
import co.com.soundMusic.Negocio.Regalias.ArtistaEmpresa.ArtistaEmpresa;
import co.com.soundMusic.Negocio.Regalias.ArtistaEmpresa.ArtistaEmpresaDaoImpl;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Santiago Medina Pelaez
 */
public class EmpresaDifusora {

    private Integer idEmpresaDifusora;
    private String nombre;
    private Date fechaCreacion;
    private Date fechaTerminacion;
    private String status;
    private TipoEmpresaDifusora tipoActividad;
    private CostoActividad costoOperacion;

    public EmpresaDifusora() {
        this.tipoActividad = new TipoEmpresaDifusora();
        this.costoOperacion = new CostoActividad();
    }

    public EmpresaDifusora(Integer idEmpresaDifusora, String nombre, Date fechaCreacion, Date fechaTerminacion,
            String status, Integer idTipoActividad, Integer idCostoOperacion) {
        this.idEmpresaDifusora = idEmpresaDifusora;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.fechaTerminacion = fechaTerminacion;
        this.status = status;
        this.tipoActividad = new TipoEmpresaDifusora();
        this.costoOperacion = new CostoActividad();
        this.tipoActividad.setIdTipoActividad(idTipoActividad);
        this.costoOperacion.setIdCostoActividad(idCostoOperacion);
    }

    public Integer getIdEmpresaDifusora() {
        return idEmpresaDifusora;
    }

    public void setIdEmpresaDifusora(Integer idEmpresaDifusora) {
        this.idEmpresaDifusora = idEmpresaDifusora;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaTerminacion() {
        return fechaTerminacion;
    }

    public void setFechaTerminacion(Date fechaTerminacion) {
        this.fechaTerminacion = fechaTerminacion;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public TipoEmpresaDifusora getTipoActividad() {
        return tipoActividad;
    }

    public void setTipoActividad(TipoEmpresaDifusora tipoActividad) {
        this.tipoActividad = tipoActividad;
    }

    public CostoActividad getCostoOperacion() {
        return costoOperacion;
    }

    public void setCostoOperacion(CostoActividad costoOperacion) {
        this.costoOperacion = costoOperacion;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + this.idEmpresaDifusora;
        hash = 13 * hash + Objects.hashCode(this.nombre);
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
        final EmpresaDifusora other = (EmpresaDifusora) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.idEmpresaDifusora, other.idEmpresaDifusora)) {
            return false;
        }
        return true;
    }

    /*public Integer getNumeroArtistas() {
        ArtistaEmpresaDaoImpl daoArtistaEmpresa = new ArtistaEmpresaDaoImpl(true);
        return daoArtistaEmpresa.obtenerNumeroDeArtistas(this.idEmpresaDifusora);
    }*/
    public int[] getNumeroArtistas(List<EmpresaDifusora> lstEmpresa) {
        ArtistaEmpresaDaoImpl daoArtistaEmpresa = new ArtistaEmpresaDaoImpl(true);
        int[] numeroArtistas = daoArtistaEmpresa.obtenerNumeroDeArtistas2(lstEmpresa);
        return numeroArtistas;
    }
}
