package edu.uptc.dominio;

import edu.uptc.enums.Rol;
import edu.uptc.enums.TipoDocumento;
import edu.uptc.enums.TipoPersona;

/**
 * Representa a un Contratante en el sistema, extendiendo las funcionalidades básicas de un {@link Usuario}.
 * Un contratante es una entidad (persona natural o jurídica) que participa en procesos de contratación pública.
 */
public class Contratante extends Usuario {
    /**
     * El sector económico al que pertenece el contratante.
     */
    private String sector;
    /**
     * El nivel de la entidad del contratante (e.g., nacional, departamental, municipal).
     */
    private String nivelEntidad;
    /**
     * El código único de identificación de la entidad contratante.
     */
    private String codigoUnicoEntidad;

    /**
     * Constructor para crear una nueva instancia de {@code Contratante}.
     * Inicializa un contratante con los detalles básicos de un usuario y sus atributos específicos como contratante.
     *
     * @param tipoPersona El tipo de persona del contratante (Natural o Jurídica).
     * @param tipoDocumento El tipo de documento de identificación del contratante.
     * @param numeroDocumento El número de documento único del contratante.
     * @param nombre El nombre completo o razón social del contratante.
     * @param correo El correo electrónico del contratante.
     * @param contrasenha La contraseña para el acceso del contratante al sistema.
     * @param telefono El número de teléfono de contacto del contratante.
     * @param direccion La dirección de residencia u oficina del contratante.
     * @param ciudad La ciudad donde reside o trabaja el contratante.
     * @param rol El rol asignado al contratante (debe ser {@link Rol#CONTRATANTE}).
     * @param sector El sector económico al que pertenece el contratante.
     * @param nivelEntidad El nivel de la entidad del contratante.
     * @param codigoUnicoEntidad El código único de la entidad contratante.
     */
    public Contratante(TipoPersona tipoPersona, TipoDocumento tipoDocumento, String numeroDocumento, String nombre,
                       String correo, String contrasenha, String telefono, String direccion, String ciudad, Rol rol,
                       String sector, String nivelEntidad, String codigoUnicoEntidad) {
        super(tipoPersona, tipoDocumento, numeroDocumento, nombre, correo, contrasenha, telefono, direccion, ciudad, rol);
        this.sector = sector;
        this.nivelEntidad = nivelEntidad;
        this.codigoUnicoEntidad = codigoUnicoEntidad;
    }

    /**
     * Obtiene el sector económico al que pertenece el contratante.
     *
     * @return El sector del contratante.
     */
    public String getSector() {
        return sector;
    }

    /**
     * Establece el sector económico del contratante.
     *
     * @param sector El nuevo sector del contratante.
     */
    public void setSector(String sector) {
        this.sector = sector;
    }

    /**
     * Obtiene el nivel de la entidad del contratante.
     *
     * @return El nivel de la entidad.
     */
    public String getNivelEntidad() {
        return nivelEntidad;
    }

    /**
     * Establece el nivel de la entidad del contratante.
     *
     * @param nivelEntidad El nuevo nivel de la entidad.
     */
    public void setNivelEntidad(String nivelEntidad) {
        this.nivelEntidad = nivelEntidad;
    }

    /**
     * Obtiene el código único de identificación de la entidad contratante.
     *
     * @return El código único de la entidad.
     */
    public String getCodigoUnicoEntidad() {
        return codigoUnicoEntidad;
    }

    /**
     * Establece el código único de identificación de la entidad contratante.
     *
     * @param codigoUnicoEntidad El nuevo código único de la entidad.
     */
    public void setCodigoUnicoEntidad(String codigoUnicoEntidad) {
        this.codigoUnicoEntidad = codigoUnicoEntidad;
    }

    /**
     * Proporciona una representación en cadena de la información detallada del contratante.
     * Este método sobrescribe el comportamiento heredado de {@link Usuario#mostrarInfoUsuario()}
     * para incluir los atributos específicos del contratante.
     *
     * @return Una cadena de texto que contiene la información completa del contratante.
     */
    @Override
    public String mostrarInfoUsuario() {
        StringBuilder sb = new StringBuilder();
        sb.append("• Nombre: ").append(nombre).append("\n");
        sb.append("• Documento: ").append(tipoDocumento).append(" - ").append(numeroDocumento).append("\n");
        sb.append("• Tipo de Persona: ").append(tipoPersona).append("\n");
        sb.append("• Correo electrónico: ").append(correo).append("\n");
        sb.append("• Teléfono: ").append(telefono).append("\n");
        sb.append("• Ubicación: ").append(direccion).append(" (").append(ciudad).append(")\n");
        sb.append("• Sector: ").append(sector).append("\n");
        sb.append("• Nivel de la Entidad: ").append(nivelEntidad).append("\n");
        sb.append("• Código Único: ").append(codigoUnicoEntidad).append("\n");
        sb.append("-----------------------------------------");
        return sb.toString();
    }
}
