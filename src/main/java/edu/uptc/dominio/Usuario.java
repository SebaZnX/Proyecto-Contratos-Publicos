package edu.uptc.dominio;

import edu.uptc.enums.TipoDocumento;
import edu.uptc.enums.TipoPersona;

public abstract class Usuario {
    // ATRIBUTOS DE USUARIO
    protected TipoPersona tipoPersona;
    protected TipoDocumento tipoDocumento;
    protected String numeroDocumento;
    protected String nombre;
    protected String correo;
    protected String contrasenha;
    protected String telefono;
    protected String direccion;
    protected String ciudad;

    // METODO CONSTRUCTOR VACIO
    public Usuario() {
    }

    // METODO CONSTRUCTOR
    public Usuario(TipoPersona tipoPersona, TipoDocumento tipoDocumento, String numeroDocumento, String nombre, String correo,
                   String contrasenha, String telefono, String direccion, String ciudad) {
        this.tipoPersona = tipoPersona;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasenha = contrasenha;
        this.telefono = telefono;
        this.direccion = direccion;
        this.ciudad = ciudad;
    }

    public TipoPersona getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(TipoPersona tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenha() {
        return contrasenha;
    }

    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public abstract String mostrarInfoUsuario();
}
