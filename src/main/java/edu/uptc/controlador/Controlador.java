package edu.uptc.controlador;

import edu.uptc.enums.Rol;
import edu.uptc.enums.TipoDocumento;
import edu.uptc.enums.TipoPersona;
import edu.uptc.servicios.ServicioContratos;
import edu.uptc.servicios.ServicioReportes;
import edu.uptc.servicios.ServicioUsuarios;

public class Controlador {
    /**
     * Invoca los servicios correspondientes desde la capa del controlador
     * para procesar las peticiones del cliente y gestionar el flujo de la aplicación.
     */
    private ServicioUsuarios servicioUsuarios;
    private ServicioContratos servicioContratos;
    private ServicioReportes servicioReportes;

    /**
     * Inicializa los atributos del controlador, configurando las dependencias
     * y variables necesarias para gestionar el flujo de las peticiones.
     */
    public Controlador() {
        this.servicioUsuarios = new ServicioUsuarios();
        this.servicioContratos = new ServicioContratos();
        this.servicioReportes = new ServicioReportes();
    }

    /**
     * Controlador encargado de gestionar el proceso de autenticación de usuarios.
     * Recibe las credenciales de acceso, coordina la verificación de identidad
     * y administra la creación o el flujo de la sesión en el sistema.
     */
    public boolean loginCorrecto(String numeroDocumento, String contrasenha) {
        return this.servicioUsuarios.loginCorrecto(numeroDocumento, contrasenha);
    }

    public Rol rolLogueado(String numeroDocumento) {
        return this.servicioUsuarios.rolLogueado(numeroDocumento);
    }

    /**
     * Controlador encargado de gestionar las operaciones y peticiones relacionadas
     * con la administración de usuarios en el sistema.
     */
    public void crearAdministrador() {
        this.servicioUsuarios.crearAdministrador();
    }

    public boolean numeroDocumentoExiste(String numeroDocumentoBuscar) {
        return this.servicioUsuarios.numeroDocumentoExiste(numeroDocumentoBuscar);
    }

    /**
     * Controlador encargado de gestionar las peticiones para la actualización
     * general de la información de los usuarios en el sistema.
     */
    public void actualizarUsuario(TipoPersona tipoPersona, TipoDocumento tipoDocumento, String numeroDocumento, String nombre,
                                  String correo, String contrasenha, String telefono, String direccion, String ciudad) {
        this.servicioUsuarios.actualizarUsuario(tipoPersona, tipoDocumento, numeroDocumento, nombre, correo, contrasenha,
                telefono, direccion, ciudad);
    }

    /**
     * Controlador encargado de gestionar las operaciones CRUD (Crear, Leer, Actualizar y Eliminar)
     * relacionadas con la entidad Contratante en el sistema.
     */
    public void crearContratante(TipoPersona tipoPersona, TipoDocumento tipoDocumento, String numeroDocumento, String nombre,
                                 String correo, String contrasenha, String telefono, String direccion, String ciudad, Rol rol,
                                 String sector, String nivelEntidad, String codigoUnicoEntidad) {
        this.servicioUsuarios.crearContratante(tipoPersona, tipoDocumento, numeroDocumento, nombre, correo, contrasenha,
                telefono, direccion, ciudad, rol, sector, nivelEntidad, codigoUnicoEntidad);
    }

    public String consultarContratantes(String numeroDocumento) {
        return this.servicioUsuarios.consultarContratantes(numeroDocumento);
    }

    public void actualizarContratante(String numeroDocumento, String sector, String nivelEntidad, String codigoUnicoEntidad) {
        this.servicioUsuarios.actualizarContratante(numeroDocumento, sector, nivelEntidad, codigoUnicoEntidad);
    }

    public void eliminarContratante(String numeroDocumentoEliminarContratante) {
        this.servicioUsuarios.eliminarContratante(numeroDocumentoEliminarContratante);
    }

    /**
     * Controlador encargado de gestionar las operaciones CRUD (Crear, Leer, Actualizar y Eliminar)
     * relacionadas con la entidad Contratista en el sistema.
     */
    public void crearContratista(TipoPersona tipoPersona, TipoDocumento tipoDocumento, String numeroDocumento, String nombre,
                                 String correo, String contrasenha, String telefono, String direccion, String ciudad, Rol rol,
                                 boolean esEntidadPublica, String areaDesempenho) {
        this.servicioUsuarios.crearContratista(tipoPersona, tipoDocumento, numeroDocumento, nombre, correo, contrasenha,
                telefono, direccion, ciudad, rol, esEntidadPublica, areaDesempenho);
    }

    public String consultarContratistas(String numeroDocumento) {
        return this.servicioUsuarios.consultarContratistas(numeroDocumento);
    }

    public void actualizarContratista(String numeroDocumento, Boolean esEntidadPublica, String areaDesempenho) {
        this.servicioUsuarios.actualizarContratista(numeroDocumento, esEntidadPublica, areaDesempenho);
    }

    public void eliminarContratista(String numeroDocumentoEliminarContratista) {
        this.servicioUsuarios.eliminarContratista(numeroDocumentoEliminarContratista);
    }


}
