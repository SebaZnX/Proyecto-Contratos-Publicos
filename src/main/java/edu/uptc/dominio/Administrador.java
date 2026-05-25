package edu.uptc.dominio;

import edu.uptc.enums.Rol;
import edu.uptc.enums.TipoDocumento;
import edu.uptc.enums.TipoPersona;

/**
 * Representa a un administrador del sistema, extendiendo las funcionalidades básicas de un {@link Usuario}.
 * Los administradores tienen roles específicos dentro de la aplicación, como la gestión de contratantes y contratistas.
 */
public class Administrador extends Usuario {
    /**
     * Constructor para crear una nueva instancia de {@code Administrador}.
     * Inicializa un administrador con los detalles básicos de un usuario y su rol específico.
     *
     * @param tipoPersona El tipo de persona del administrador (Natural o Jurídica).
     * @param tipoDocumento El tipo de documento de identificación del administrador.
     * @param numeroDocumento El número de documento único del administrador.
     * @param nombre El nombre completo o razón social del administrador.
     * @param correo El correo electrónico del administrador.
     * @param contrasenha La contraseña para el acceso del administrador al sistema.
     * @param telefono El número de teléfono de contacto del administrador.
     * @param direccion La dirección de residencia u oficina del administrador.
     * @param ciudad La ciudad donde reside o trabaja el administrador.
     * @param rol El rol asignado al administrador (debe ser {@link Rol#ADMINISTRADOR}).
     */
    public Administrador(TipoPersona tipoPersona, TipoDocumento tipoDocumento, String numeroDocumento, String nombre,
                         String correo, String contrasenha, String telefono, String direccion, String ciudad, Rol rol) {
        super(tipoPersona, tipoDocumento, numeroDocumento, nombre, correo, contrasenha, telefono, direccion, ciudad, rol);
    }

    /**
     * Proporciona una representación en cadena de la información detallada del administrador.
     * Este método sobrescribe el comportamiento heredado de {@link Usuario#mostrarInfoUsuario()}
     * para asegurar que la información se muestre de manera específica para un administrador.
     *
     * @return Una cadena de texto que contiene la información completa del administrador.
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
        sb.append("-----------------------------------------");
        return sb.toString();
    }
}
