package edu.uptc.dominio;

import edu.uptc.enums.Rol;
import edu.uptc.enums.TipoDocumento;
import edu.uptc.enums.TipoPersona;

/**
 * Clase abstracta que representa la entidad base de un usuario en el sistema.
 * Define los atributos y comportamientos comunes que cualquier tipo de usuario
 * (Administrador, Contratante, Contratista) debe poseer.
 */
public abstract class Usuario {
    /**
     * El tipo de persona del usuario (Natural o Jurídica).
     */
    protected TipoPersona tipoPersona;
    /**
     * El tipo de documento de identificación del usuario (CC, CE, PAS, PPT, NIT).
     */
    protected TipoDocumento tipoDocumento;
    /**
     * El número de documento único del usuario.
     */
    protected String numeroDocumento;
    /**
     * El nombre completo o razón social del usuario.
     */
    protected String nombre;
    /**
     * El correo electrónico del usuario.
     */
    protected String correo;
    /**
     * La contraseña de acceso del usuario al sistema.
     */
    protected String contrasenha;
    /**
     * El número de teléfono de contacto del usuario.
     */
    protected String telefono;
    /**
     * La dirección de residencia u oficina del usuario.
     */
    protected String direccion;
    /**
     * La ciudad donde reside o trabaja el usuario.
     */
    protected String ciudad;
    /**
     * El rol asignado al usuario en el sistema (Administrador, Contratante, Contratista).
     */
    protected Rol rol;

    /**
     * Constructor vacío de la clase {@code Usuario}.
     * Permite la creación de instancias de usuarios sin inicializar sus atributos,
     * los cuales pueden ser establecidos posteriormente mediante los métodos setters.
     */
    public Usuario() {
    }

    /**
     * Constructor para crear una nueva instancia de {@code Usuario} con todos sus atributos inicializados.
     *
     * @param tipoPersona El tipo de persona del usuario (Natural o Jurídica).
     * @param tipoDocumento El tipo de documento de identificación del usuario.
     * @param numeroDocumento El número de documento único del usuario.
     * @param nombre El nombre completo o razón social del usuario.
     * @param correo El correo electrónico del usuario.
     * @param contrasenha La contraseña de acceso del usuario.
     * @param telefono El número de teléfono de contacto del usuario.
     * @param direccion La dirección de residencia u oficina del usuario.
     * @param ciudad La ciudad donde reside o trabaja el usuario.
     * @param rol El rol asignado al usuario.
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
     * Obtiene el tipo de persona del usuario.
     *
     * @return El {@link TipoPersona} del usuario.
     */
    public TipoPersona getTipoPersona() {
        return tipoPersona;
    }

    /**
     * Establece el tipo de persona del usuario.
     *
     * @param tipoPersona El nuevo {@link TipoPersona} del usuario.
     */
    public void setTipoPersona(TipoPersona tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    /**
     * Obtiene el tipo de documento de identificación del usuario.
     *
     * @return El {@link TipoDocumento} del usuario.
     */
    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * Establece el tipo de documento de identificación del usuario.
     *
     * @param tipoDocumento El nuevo {@link TipoDocumento} del usuario.
     */
    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    /**
     * Obtiene el número de documento único del usuario.
     *
     * @return El número de documento del usuario.
     */
    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    /**
     * Establece el número de documento único del usuario.
     *
     * @param numeroDocumento El nuevo número de documento del usuario.
     */
    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    /**
     * Obtiene el nombre completo o razón social del usuario.
     *
     * @return El nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre completo o razón social del usuario.
     *
     * @param nombre El nuevo nombre del usuario.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el correo electrónico del usuario.
     *
     * @return El correo electrónico del usuario.
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Establece el correo electrónico del usuario.
     *
     * @param correo El nuevo correo electrónico del usuario.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Obtiene la contraseña de acceso del usuario.
     *
     * @return La contraseña del usuario.
     */
    public String getContrasenha() {
        return contrasenha;
    }

    /**
     * Establece la contraseña de acceso del usuario.
     *
     * @param contrasenha La nueva contraseña del usuario.
     */
    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }

    /**
     * Obtiene el número de teléfono de contacto del usuario.
     *
     * @return El número de teléfono del usuario.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Establece el número de teléfono de contacto del usuario.
     *
     * @param telefono El nuevo número de teléfono del usuario.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Obtiene la dirección de residencia u oficina del usuario.
     *
     * @return La dirección del usuario.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Establece la dirección de residencia u oficina del usuario.
     *
     * @param direccion La nueva dirección del usuario.
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Obtiene la ciudad donde reside o trabaja el usuario.
     *
     * @return La ciudad del usuario.
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * Establece la ciudad donde reside o trabaja el usuario.
     *
     * @param ciudad La nueva ciudad del usuario.
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * Obtiene el rol asignado al usuario en el sistema.
     *
     * @return El {@link Rol} del usuario.
     */
    public Rol getRol() {
        return rol;
    }

    /**
     * Establece el rol asignado al usuario en el sistema.
     *
     * @param rol El nuevo {@link Rol} del usuario.
     */
    public void setRol(Rol rol) {
        this.rol = rol;
    }

    /**
     * Método abstracto para obtener una representación en cadena de la información detallada del usuario.
     * Cada subclase de {@code Usuario} debe implementar su propia forma de mostrar su información.
     *
     * @return Una cadena de texto que contiene la información formateada del usuario.
     */
    public abstract String mostrarInfoUsuario();
}
