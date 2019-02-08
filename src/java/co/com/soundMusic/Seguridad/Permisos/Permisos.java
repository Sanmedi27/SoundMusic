package co.com.soundMusic.Seguridad.Permisos;

/**
 *
 * @author Santiago Medina Pelaez
 */
public class Permisos {

    private int idPermiso;
    private String nombrePermiso;

    public Permisos() {
    }

    public Permisos(int idPermiso) {
        this.idPermiso = idPermiso;
    }

    public Permisos(int idPermiso, String nombrePermiso) {
        this.idPermiso = idPermiso;
        this.nombrePermiso = nombrePermiso;
    }

    public int getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(int idPermiso) {
        this.idPermiso = idPermiso;
    }

    public String getNombrePermiso() {
        return nombrePermiso;
    }

    public void setNombrePermiso(String nombrePermiso) {
        this.nombrePermiso = nombrePermiso;
    }

}
