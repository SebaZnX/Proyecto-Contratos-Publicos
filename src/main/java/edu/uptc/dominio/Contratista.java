package edu.uptc.dominio;

import edu.uptc.enums.Rol;
import edu.uptc.enums.TipoDocumento;
import edu.uptc.enums.TipoPersona;

public class Contratista extends Usuario {
    /**
     * Define los atributos específicos de la clase Contratista,
     * la cual hereda las propiedades y comportamientos de la clase base Usuario.
     */
    private boolean esEntidadPublica;
    private String areaDesempenho;

    /**
     * Constructor de la clase.
     * Inicializa una nueva instancia de la clase asignando los valores pasados por parámetro
     * a sus respectivos atributos.
     */
    public Contratista(TipoPersona tipoPersona, TipoDocumento tipoDocumento, String numeroDocumento, String nombre,
                       String correo, String contrasenha, String telefono, String direccion, String ciudad, Rol rol,
                       boolean esEntidadPublica, String areaDesempenho) {
        super(tipoPersona, tipoDocumento, numeroDocumento, nombre, correo, contrasenha, telefono, direccion, ciudad, rol);
        this.esEntidadPublica = esEntidadPublica;
        this.areaDesempenho = areaDesempenho;
    }

    /**
     * Métodos de acceso (getters) y de modificación (setters) para los atributos de la clase.
     * Permiten encapsular la información, controlando la lectura y escritura de las variables.
     */
    public boolean isEsEntidadPublica() {
        return esEntidadPublica;
    }

    public void setEsEntidadPublica(boolean esEntidadPublica) {
        this.esEntidadPublica = esEntidadPublica;
    }

    public String getAreaDesempenho() {
        return areaDesempenho;
    }

    public void setAreaDesempenho(String areaDesempenho) {
        this.areaDesempenho = areaDesempenho;
    }

    public void seleccionarContrato() {

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
        sb.append("• Área de Desempeño: ").append(areaDesempenho).append("\n");
        sb.append("• ¿Es Entidad Pública?: ").append(esEntidadPublica ? "Sí" : "No").append("\n");
        sb.append("-----------------------------------------");
        return sb.toString();
    }


}
