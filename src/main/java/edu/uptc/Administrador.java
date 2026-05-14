package edu.uptc;

public class Administrador extends Usuario {
    public Administrador(TipoPersona tipoPersona, String tipoDocumento, String numeroDocumento, String nombre,
                         String correo, String contrasenha, String telefono, String direccion, String ciudad) {
        super(tipoPersona, tipoDocumento, numeroDocumento, nombre, correo, contrasenha, telefono, direccion, ciudad);
    }

    public void crearContratante() {

    }
    public void consultarContratantes() {

    }
    public void actualizarContratante() {

    }
    public void eliminarContratante() {

    }
    public void crearContratista() {

    }
    public void consultarContratistas() {

    }
    public void actualizarContratista() {

    }public void eliminarContratista() {

    }


    @Override
    public void mostrarMenu() {
    }
}
