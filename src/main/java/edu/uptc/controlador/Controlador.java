package edu.uptc.controlador;

import edu.uptc.enums.Rol;
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

    public Rol rolLogueado(String numeroDocumento) {
        return this.servicioUsuarios.rolLogueado(numeroDocumento);
    }

    // USUARIOS CONTROLADOR

    public void crearAdministrador() {
        this.servicioUsuarios.crearAdministrador();
    }

    public boolean numeroDocumentoExiste(String numeroDocumentoBuscar) {
        return this.servicioUsuarios.numeroDocumentoExiste(numeroDocumentoBuscar);
    }

    // CONTROLADOR DE ACTUALIZAR USUARIOS GENERAL
    public void actualizarUsuario(TipoPersona tipoPersona, TipoDocumento tipoDocumento, String numeroDocumento, String nombre,
                                  String correo, String contrasenha, String telefono, String direccion, String ciudad) {
        this.servicioUsuarios.actualizarUsuario(tipoPersona, tipoDocumento, numeroDocumento, nombre, correo, contrasenha,
                telefono, direccion, ciudad);
    }

    // CRUD PARA CONTRATANTE
    public void crearContratante(TipoPersona tipoPersona, TipoDocumento tipoDocumento, String numeroDocumento, String nombre,
                                 String correo, String contrasenha, String telefono, String direccion, String ciudad, Rol rol,
                                 String sector, String nivelEntidad, String codigoUnicoEntidad) {
        this.servicioUsuarios.crearContratante(tipoPersona, tipoDocumento, numeroDocumento, nombre, correo, contrasenha,
                telefono, direccion, ciudad, rol, sector, nivelEntidad, codigoUnicoEntidad);
    }

    public String consultarContratantes() {
        return this.servicioUsuarios.consultarContratantes();
    }

    public void actualizarContratante(String numeroDocumento, String sector, String nivelEntidad, String codigoUnicoEntidad) {
        this.servicioUsuarios.actualizarContratante(numeroDocumento, sector, nivelEntidad, codigoUnicoEntidad);
    }

    public void eliminarContratante(String numeroDocumentoEliminarContratante) {
        this.servicioUsuarios.eliminarContratante(numeroDocumentoEliminarContratante);
    }

    // CRUD PARA CONTRATISTA
    public void crearContratista(TipoPersona tipoPersona, TipoDocumento tipoDocumento, String numeroDocumento, String nombre,
                                 String correo, String contrasenha, String telefono, String direccion, String ciudad, Rol rol,
                                 boolean esEntidadPublica, String areaDesempenho) {
        this.servicioUsuarios.crearContratista(tipoPersona, tipoDocumento, numeroDocumento, nombre, correo, contrasenha,
                telefono, direccion, ciudad, rol, esEntidadPublica, areaDesempenho);
    }

    public String consultarContratistas() {
        return this.servicioUsuarios.consultarContratistas();
    }

    public void actualizarContratista(String numeroDocumento, Boolean esEntidadPublica, String areaDesempenho) {
        this.servicioUsuarios.actualizarContratista(numeroDocumento, esEntidadPublica, areaDesempenho);
    }

    public void eliminarContratista(String numeroDocumentoEliminarContratista) {
        this.servicioUsuarios.eliminarContratista(numeroDocumentoEliminarContratista);
    }


}
