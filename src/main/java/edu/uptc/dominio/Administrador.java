package edu.uptc.dominio;

import edu.uptc.enums.Rol;
import edu.uptc.enums.TipoDocumento;
import edu.uptc.enums.TipoPersona;

public class Administrador extends Usuario {
    /**
     * Constructor de la clase.
     * Inicializa una nueva instancia de la clase asignando los valores pasados por parámetro
     * a sus respectivos atributos.
     */
    public Administrador(TipoPersona tipoPersona, TipoDocumento tipoDocumento, String numeroDocumento, String nombre,
                         String correo, String contrasenha, String telefono, String direccion, String ciudad, Rol rol) {
        super(tipoPersona, tipoDocumento, numeroDocumento, nombre, correo, contrasenha, telefono, direccion, ciudad, rol);
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
        sb.append("-----------------------------------------");
        return sb.toString();
    }


}
