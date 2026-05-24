package edu.uptc.controlador;

import edu.uptc.enums.TipoDocumento;
import edu.uptc.enums.TipoPersona;
import edu.uptc.servicios.ServicioContratos;
import edu.uptc.servicios.ServicioReportes;
import edu.uptc.servicios.ServicioUsuarios;

public class Controlador {

    private ServicioUsuarios servicioUsuarios;
    private ServicioContratos servicioContratos;
    private ServicioReportes servicioReportes;

    public Controlador() {
        this.servicioUsuarios = new ServicioUsuarios();
        this.servicioContratos = new ServicioContratos();
        this.servicioReportes = new ServicioReportes();
    }

    // CONTROLADOR LOGIN
    public boolean loginCorrecto(String numeroDocumento, String contrasenha) {
        return this.servicioUsuarios.loginCorrecto(numeroDocumento, contrasenha);
    }

    // USUARIOS CONTROLADOR

    public void crearAdministrador() {
        this.servicioUsuarios.crearAdministrador();
    }

    public boolean numeroDocumentoExiste(String numeroDocumentoBuscar) {
        return this.servicioUsuarios.numeroDocumentoExiste(numeroDocumentoBuscar);
    }

    public void crearContratante(TipoPersona tipoPersona, TipoDocumento tipoDocumento, String numeroDocumento, String nombre,
                                 String correo, String contrasenha, String telefono, String direccion, String ciudad,
                                 String sector, String nivelEntidad, String codigoUnicoEntidad) {
        this.servicioUsuarios.crearContratante(tipoPersona, tipoDocumento, numeroDocumento, nombre, correo, contrasenha,
                telefono, direccion, ciudad, sector, nivelEntidad, codigoUnicoEntidad);
    }

    public void actualizarUsuario(TipoPersona tipoPersona, TipoDocumento tipoDocumento, String numeroDocumento, String nombre,
                                  String correo, String contrasenha, String telefono, String direccion, String ciudad) {
        this.servicioUsuarios.actualizarUsuario(tipoPersona, tipoDocumento, numeroDocumento, nombre, correo, contrasenha,
                telefono, direccion, ciudad);
    }


    public void actualizarContratante(String numeroDocumento, String sector, String nivelEntidad, String codigoUnicoEntidad) {
        this.servicioUsuarios.actualizarContratante(numeroDocumento, sector, nivelEntidad, codigoUnicoEntidad);
    }


}
