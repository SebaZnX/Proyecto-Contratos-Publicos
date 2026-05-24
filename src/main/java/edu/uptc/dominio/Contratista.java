package edu.uptc.dominio;

import edu.uptc.enums.Rol;
import edu.uptc.enums.TipoDocumento;
import edu.uptc.enums.TipoPersona;

public class Contratista extends Usuario {
    private boolean esEntidadPublica;
    private String areaDesempenho;

    public Contratista(TipoPersona tipoPersona, TipoDocumento tipoDocumento, String numeroDocumento, String nombre,
                       String correo, String contrasenha, String telefono, String direccion, String ciudad, Rol rol, boolean esEntidadPublica, String areDesempenho) {
        super(tipoPersona, tipoDocumento, numeroDocumento, nombre, correo, contrasenha, telefono, direccion, ciudad, rol);
        this.esEntidadPublica = esEntidadPublica;
        this.areaDesempenho = areDesempenho;
    }

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
