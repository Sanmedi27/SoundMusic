package co.com.soundMusic.EmpresaDifusora.TipoCosto;

import java.sql.Date;

/**
 *
 * @author Santiago Medina Pelaez
 */
public class CostoActividad {

    private int idCostoActividad;
    private float costoPorOperacion;
    private Date fechaCreacion;
    private Date fechaUsoFinal;

    public CostoActividad() {
    }

    public CostoActividad(int idCostoActividad) {
        this.idCostoActividad = idCostoActividad;
    }

    public CostoActividad(int idCostoActividad, float costoPorOperacion, Date fechaCreacion, Date fechaUsoFinal) {
        this.idCostoActividad = idCostoActividad;
        this.costoPorOperacion = costoPorOperacion;
        this.fechaCreacion = fechaCreacion;
        this.fechaUsoFinal = fechaUsoFinal;
    }

    public int getIdCostoActividad() {
        return idCostoActividad;
    }

    public void setIdCostoActividad(int idCostoActividad) {
        this.idCostoActividad = idCostoActividad;
    }

    public float getCostoPorOperacion() {
        return costoPorOperacion;
    }

    public void setCostoPorOperacion(float costoPorOperacion) {
        this.costoPorOperacion = costoPorOperacion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaUsoFinal() {
        return fechaUsoFinal;
    }

    public void setFechaUsoFinal(Date fechaUsoFinal) {
        this.fechaUsoFinal = fechaUsoFinal;
    }

}
