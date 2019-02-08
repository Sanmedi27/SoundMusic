package co.com.soundMusic.Negocio.Logros.LogroArtista;

import co.com.soundMusic.Artista.Artista;
import co.com.soundMusic.Artista.ArtistaDaoImpl;
import co.com.soundMusic.Negocio.Logros.Logro;
import co.com.soundMusic.Negocio.Logros.LogroDaoImpl;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Santiago Meidna Peláez
 */
public class LogroArtista {

    private int idLogroArtista;
    private int idLogro;
    private int idArtista;
    private Date fechaPremiacion;
    private Artista artista;
    private Logro logro;

    public LogroArtista() {
    }

    public LogroArtista(int idLogroArtista, int idLogro, int idArtista, Date fechaPremiacion) {
        this.idLogroArtista = idLogroArtista;
        this.idLogro = idLogro;
        this.idArtista = idArtista;
        this.fechaPremiacion = fechaPremiacion;
    }

    public int getIdLogroArtista() {
        return idLogroArtista;
    }

    public void setIdLogroArtista(int idLogroArtista) {
        this.idLogroArtista = idLogroArtista;
    }

    public int getIdLogro() {
        return idLogro;
    }

    public void setIdLogro(int idLogro) {
        this.idLogro = idLogro;
    }

    public int getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(int idArtista) {
        this.idArtista = idArtista;
    }

    public Date getFechaPremiacion() {
        return fechaPremiacion;
    }

    public void setFechaPremiacion(Date fechaPremiacion) {
        this.fechaPremiacion = fechaPremiacion;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public Logro getLogro() {
        return logro;
    }

    public void setLogro(Logro logro) {
        this.logro = logro;
    }

    public void obtenerArtista() {
        ArtistaDaoImpl daoArtista = new ArtistaDaoImpl(true);
        this.setArtista(daoArtista.obtenerArtista(this.idArtista));

    }

    public void obtenerLogro() {
        LogroDaoImpl daoLogro = new LogroDaoImpl(true);
        this.setLogro(daoLogro.obtenerLogro(this.idLogro));
    }
}
