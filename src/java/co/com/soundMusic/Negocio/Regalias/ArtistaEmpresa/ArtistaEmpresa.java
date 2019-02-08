package co.com.soundMusic.Negocio.Regalias.ArtistaEmpresa;

import co.com.soundMusic.Artista.Artista;
import co.com.soundMusic.EmpresaDifusora.EmpresaDifusora;

/**
 *
 * @author Santiago Medina Pelaez
 */
public class ArtistaEmpresa {

    private int idArtistaEmpresa;
    private Artista artista;
    private EmpresaDifusora empresaDifusora;

    public ArtistaEmpresa() {
        this.artista = new Artista();
        this.empresaDifusora = new EmpresaDifusora();
    }

    public ArtistaEmpresa(int idArtistaEmpresa) {
        this.idArtistaEmpresa = idArtistaEmpresa;
        this.artista = new Artista();
        this.empresaDifusora = new EmpresaDifusora();
    }

    public ArtistaEmpresa(int idArtistaEmpresa, int idArtista, int idEmpresaDifusora) {
        this.idArtistaEmpresa = idArtistaEmpresa;
        this.artista = new Artista();
        this.empresaDifusora = new EmpresaDifusora();
        this.artista.setIdArtista(idArtista);
        this.empresaDifusora.setIdEmpresaDifusora(idEmpresaDifusora);
    }

    public int getIdArtistaEmpresa() {
        return idArtistaEmpresa;
    }

    public void setIdArtistaEmpresa(int idArtistaEmpresa) {
        this.idArtistaEmpresa = idArtistaEmpresa;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public EmpresaDifusora getEmpresaDifusora() {
        return empresaDifusora;
    }

    public void setEmpresaDifusora(EmpresaDifusora empresaDifusora) {
        this.empresaDifusora = empresaDifusora;
    }
}
