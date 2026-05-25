package edu.uptc.dominio;

import edu.uptc.enums.Rol;
import edu.uptc.enums.TipoDocumento;
import edu.uptc.enums.TipoPersona;

public abstract class Usuario {
    /**
     * Define los atributos generales y compartidos que componen la entidad de Usuario.
     */
    protected TipoPersona tipoPersona;
    protected TipoDocumento tipoDocumento;
    protected String numeroDocumento;
    protected String nombre;
    protected String correo;
    protected String contrasenha;
    protected String telefono;
    protected String direccion;
    protected String ciudad;
    protected Rol rol;

    /**
     * Constructor vacío de la clase.
     * Inicializa una nueva instancia sin asignar valores previos a sus atributos.
     */
    public Usuario() {
    }

    /**
     * Constructor de la clase.
     * Inicializa una nueva instancia de la clase asignando los valores pasados por parámetro
     * a sus respectivos atributos.
     */
    public Usuario(TipoPersona tipoPersona, TipoDocumento tipoDocumento, String numeroDocumento, String nombre, String correo,
                   String contrasenha, String telefono, String direccion, String ciudad, Rol rol) {
        this.tipoPersona = tipoPersona;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasenha = contrasenha;
        this.telefono = telefono;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.rol = rol;
    }

    /**
     * Métodos de acceso (getters) y de modificación (setters) para los atributos de la clase.
     * Permiten encapsular la información, controlando la lectura y escritura de las variables.
     */
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

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    /**
     * Declara los métodos abstractos de la clase o interfaz.
     * Estos métodos definen la estructura y comportamiento obligatorio que deben
     * implementar las subclases concretas.
     */
    public abstract String mostrarInfoUsuario();
}
