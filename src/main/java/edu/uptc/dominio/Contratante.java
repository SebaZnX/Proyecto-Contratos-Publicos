package edu.uptc.dominio;

import edu.uptc.enums.Rol;
import edu.uptc.enums.TipoDocumento;
import edu.uptc.enums.TipoPersona;

public class Contratante extends Usuario {
    /**
     * Define los atributos específicos de la clase Contratante,
     * la cual hereda las propiedades y comportamientos de la clase base Usuario.
     */
    private String sector;
    private String nivelEntidad;
    private String codigoUnicoEntidad;
    /**
     * Constructor de la clase.
     * Inicializa una nueva instancia de la clase asignando los valores pasados por parámetro
     * a sus respectivos atributos.
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
     * Métodos de acceso (getters) y de modificación (setters) para los atributos de la clase.
     * Permiten encapsular la información, controlando la lectura y escritura de las variables.
     */
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

    /**
     * Implementa o sobrescribe los métodos abstractos heredados de la clase padre.
     * Define el comportamiento específico que la clase hija debe proporcionar para cumplir
     * con el contrato establecido por la superclase.
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
