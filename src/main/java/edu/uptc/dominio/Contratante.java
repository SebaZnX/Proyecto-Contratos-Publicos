package edu.uptc.dominio;

import edu.uptc.enums.TipoDocumento;
import edu.uptc.enums.TipoPersona;

public class Contratante extends Usuario {
    private String sector;
    private String nivelEntidad;
    private String codigoUnicoEntidad;

    public Contratante(TipoPersona tipoPersona, TipoDocumento tipoDocumento, String numeroDocumento, String nombre,
                       String correo, String contrasenha, String telefono, String direccion, String ciudad, String sector, String nivelEntidad, String codigoUnicoEntidad) {
        super(tipoPersona, tipoDocumento, numeroDocumento, nombre, correo, contrasenha, telefono, direccion, ciudad);
        this.sector = sector;
        this.nivelEntidad = nivelEntidad;
        this.codigoUnicoEntidad = codigoUnicoEntidad;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getNivelEntidad() {
        return nivelEntidad;
    }

    public void setNivelEntidad(String nivelEntidad) {
        this.nivelEntidad = nivelEntidad;
    }

    public String getCodigoUnicoEntidad() {
        return codigoUnicoEntidad;
    }

    public void setCodigoUnicoEntidad(String codigoUnicoEntidad) {
        this.codigoUnicoEntidad = codigoUnicoEntidad;
    }

    @Override
    public void mostrarMenu() {
    }
}
