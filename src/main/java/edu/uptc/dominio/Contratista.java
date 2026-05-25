package edu.uptc.dominio;

import edu.uptc.enums.Rol;
import edu.uptc.enums.TipoDocumento;
import edu.uptc.enums.TipoPersona;

/**
 * Representa a un Contratista en el sistema, extendiendo las funcionalidades básicas de un {@link Usuario}.
 * Un contratista es una entidad (persona natural o jurídica) que ofrece bienes o servicios
 * y participa en procesos de contratación pública.
 */
public class Contratista extends Usuario {
    /**
     * Indica si el contratista es una entidad pública (true) o privada (false).
     */
    private boolean esEntidadPublica;
    /**
     * Describe el área de desempeño o especialización del contratista.
     */
    private String areaDesempenho;

    /**
     * Constructor para crear una nueva instancia de {@code Contratista}.
     * Inicializa un contratista con los detalles básicos de un usuario y sus atributos específicos como contratista.
     *
     * @param tipoPersona El tipo de persona del contratista (Natural o Jurídica).
     * @param tipoDocumento El tipo de documento de identificación del contratista.
     * @param numeroDocumento El número de documento único del contratista.
     * @param nombre El nombre completo o razón social del contratista.
     * @param correo El correo electrónico del contratista.
     * @param contrasenha La contraseña para el acceso del contratista al sistema.
     * @param telefono El número de teléfono de contacto del contratista.
     * @param direccion La dirección de residencia u oficina del contratista.
     * @param ciudad La ciudad donde reside o trabaja el contratista.
     * @param rol El rol asignado al contratista (debe ser {@link Rol#CONTRATISTA}).
     * @param esEntidadPublica Indica si el contratista es una entidad pública.
     * @param areaDesempenho El área de desempeño o especialización del contratista.
     */
    public Contratista(TipoPersona tipoPersona, TipoDocumento tipoDocumento, String numeroDocumento, String nombre,
                       String correo, String contrasenha, String telefono, String direccion, String ciudad, Rol rol,
                       boolean esEntidadPublica, String areaDesempenho) {
        super(tipoPersona, tipoDocumento, numeroDocumento, nombre, correo, contrasenha, telefono, direccion, ciudad, rol);
        this.esEntidadPublica = esEntidadPublica;
        this.areaDesempenho = areaDesempenho;
    }

    /**
     * Verifica si el contratista es una entidad pública.
     *
     * @return {@code true} si el contratista es una entidad pública, {@code false} en caso contrario.
     */
    public boolean isEsEntidadPublica() {
        return esEntidadPublica;
    }

    /**
     * Establece si el contratista es una entidad pública.
     *
     * @param esEntidadPublica El nuevo estado de entidad pública del contratista.
     */
    public void setEsEntidadPublica(boolean esEntidadPublica) {
        this.esEntidadPublica = esEntidadPublica;
    }

    /**
     * Obtiene el área de desempeño o especialización del contratista.
     *
     * @return El área de desempeño del contratista.
     */
    public String getAreaDesempenho() {
        return areaDesempenho;
    }

    /**
     * Establece el área de desempeño o especialización del contratista.
     *
     * @param areaDesempenho La nueva área de desempeño del contratista.
     */
    public void setAreaDesempenho(String areaDesempenho) {
        this.areaDesempenho = areaDesempenho;
    }

    /**
     * Método placeholder para la selección de contratos por parte del contratista.
     * La implementación específica de esta funcionalidad se definiría en futuras versiones.
     */
    public void seleccionarContrato() {
        // TODO: Implementar la lógica para que el contratista seleccione un contrato.
    }

    /**
     * Proporciona una representación en cadena de la información detallada del contratista.
     * Este método sobrescribe el comportamiento heredado de {@link Usuario#mostrarInfoUsuario()}
     * para incluir los atributos específicos del contratista.
     *
     * @return Una cadena de texto que contiene la información completa del contratista.
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
        sb.append("• Área de Desempeño: ").append(areaDesempenho).append("\n");
        sb.append("• ¿Es Entidad Pública?: ").append(esEntidadPublica ? "Sí" : "No").append("\n");
        sb.append("-----------------------------------------");
        return sb.toString();
    }
}
