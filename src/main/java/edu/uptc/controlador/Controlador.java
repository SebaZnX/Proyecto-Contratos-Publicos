package edu.uptc.controlador;

import edu.uptc.enums.Rol;
import edu.uptc.enums.TipoDocumento;
import edu.uptc.enums.TipoPersona;
import edu.uptc.servicios.ServicioContratos;
import edu.uptc.servicios.ServicioReportes;
import edu.uptc.servicios.ServicioUsuarios;

/**
 * La clase {@code Controlador} actúa como la capa de control de la aplicación,
 * gestionando la interacción entre la interfaz de usuario y los servicios de negocio.
 * Se encarga de recibir las peticiones del cliente, coordinar las operaciones
 * a través de los servicios y devolver las respuestas adecuadas.
 */
public class Controlador {
    /**
     * Instancia de {@link ServicioUsuarios} para gestionar las operaciones relacionadas con los usuarios.
     */
    private ServicioUsuarios servicioUsuarios;
    /**
     * Instancia de {@link ServicioContratos} para gestionar las operaciones relacionadas con los contratos.
     */
    private ServicioContratos servicioContratos;
    /**
     * Instancia de {@link ServicioReportes} para gestionar las operaciones relacionadas con los reportes.
     */
    private ServicioReportes servicioReportes;

    /**
     * Constructor de la clase {@code Controlador}.
     * Inicializa las instancias de los servicios de usuario, contratos y reportes,
     * estableciendo las dependencias necesarias para el funcionamiento del controlador.
     */
    public Controlador() {
        this.servicioUsuarios = new ServicioUsuarios();
        this.servicioContratos = new ServicioContratos();
        this.servicioReportes = new ServicioReportes();
    }

    /**
     * Realiza el proceso de autenticación de un usuario.
     *
     * @param numeroDocumento El número de documento del usuario que intenta iniciar sesión.
     * @param contrasenha La contraseña del usuario.
     * @return {@code true} si las credenciales son correctas y el inicio de sesión es exitoso, {@code false} en caso contrario.
     */
    public boolean loginCorrecto(String numeroDocumento, String contrasenha) {
        return this.servicioUsuarios.loginCorrecto(numeroDocumento, contrasenha);
    }

    /**
     * Obtiene el rol de un usuario a partir de su número de documento.
     *
     * @param numeroDocumento El número de documento del usuario.
     * @return El {@link Rol} asociado al usuario, o {@code null} si el usuario no existe o no tiene un rol definido.
     */
    public Rol rolLogueado(String numeroDocumento) {
        return this.servicioUsuarios.rolLogueado(numeroDocumento);
    }

    /**
     * Crea un usuario con rol de administrador en el sistema.
     * Este método se utiliza para la configuración inicial del sistema.
     */
    public void crearAdministrador() {
        this.servicioUsuarios.crearAdministrador();
    }

    /**
     * Verifica si un número de documento ya existe en el sistema.
     *
     * @param numeroDocumentoBuscar El número de documento a buscar.
     * @return {@code true} si el número de documento existe, {@code false} en caso contrario.
     */
    public boolean numeroDocumentoExiste(String numeroDocumentoBuscar) {
        return this.servicioUsuarios.numeroDocumentoExiste(numeroDocumentoBuscar);
    }

    /**
     * Actualiza la información general de un usuario existente en el sistema.
     * Los campos que no se deseen actualizar pueden pasarse como {@code null}.
     *
     * @param tipoPersona El nuevo tipo de persona del usuario (Natural o Jurídica).
     * @param tipoDocumento El nuevo tipo de documento del usuario (CC, CE, PAS, PPT, NIT).
     * @param numeroDocumento El número de documento del usuario a actualizar (identificador único).
     * @param nombre El nuevo nombre completo del usuario.
     * @param correo El nuevo correo electrónico del usuario.
     * @param contrasenha La nueva contraseña del usuario.
     * @param telefono El nuevo número de teléfono del usuario.
     * @param direccion La nueva dirección de residencia u oficina del usuario.
     * @param ciudad La nueva ciudad de residencia del usuario.
     */
    public void actualizarUsuario(TipoPersona tipoPersona, TipoDocumento tipoDocumento, String numeroDocumento, String nombre,
                                  String correo, String contrasenha, String telefono, String direccion, String ciudad) {
        this.servicioUsuarios.actualizarUsuario(tipoPersona, tipoDocumento, numeroDocumento, nombre, correo, contrasenha,
                telefono, direccion, ciudad);
    }

    /**
     * Crea un nuevo contratante en el sistema con la información proporcionada.
     *
     * @param tipoPersona El tipo de persona del contratante (Natural o Jurídica).
     * @param tipoDocumento El tipo de documento del contratante (CC, CE, PAS, PPT, NIT).
     * @param numeroDocumento El número de documento único del contratante.
     * @param nombre El nombre completo o razón social del contratante.
     * @param correo El correo electrónico del contratante.
     * @param contrasenha La contraseña para el acceso del contratante.
     * @param telefono El número de teléfono del contratante.
     * @param direccion La dirección de residencia u oficina del contratante.
     * @param ciudad La ciudad del contratante.
     * @param rol El rol asignado al contratante (debería ser {@link Rol#CONTRATANTE}).
     * @param sector El sector económico al que pertenece el contratante.
     * @param nivelEntidad El nivel de la entidad (e.g., nacional, departamental, municipal).
     * @param codigoUnicoEntidad El código único de la entidad contratante.
     */
    public void crearContratante(TipoPersona tipoPersona, TipoDocumento tipoDocumento, String numeroDocumento, String nombre,
                                 String correo, String contrasenha, String telefono, String direccion, String ciudad, Rol rol,
                                 String sector, String nivelEntidad, String codigoUnicoEntidad) {
        this.servicioUsuarios.crearContratante(tipoPersona, tipoDocumento, numeroDocumento, nombre, correo, contrasenha,
                telefono, direccion, ciudad, rol, sector, nivelEntidad, codigoUnicoEntidad);
    }

    /**
     * Consulta la información de uno o todos los contratantes registrados en el sistema.
     *
     * @param numeroDocumento El número de documento del contratante a consultar. Si es {@code null} o vacío,
     *                        se consultarán todos los contratantes.
     * @return Una cadena de texto con la información del contratante específico o de todos los contratantes.
     */
    public String consultarContratantes(String numeroDocumento) {
        return this.servicioUsuarios.consultarContratantes(numeroDocumento);
    }

    /**
     * Actualiza la información específica de un contratante existente.
     * Los campos que no se deseen actualizar pueden pasarse como {@code null}.
     *
     * @param numeroDocumento El número de documento del contratante a actualizar.
     * @param sector El nuevo sector económico del contratante.
     * @param nivelEntidad El nuevo nivel de entidad del contratante.
     * @param codigoUnicoEntidad El nuevo código único de entidad del contratante.
     */
    public void actualizarContratante(String numeroDocumento, String sector, String nivelEntidad, String codigoUnicoEntidad) {
        this.servicioUsuarios.actualizarContratante(numeroDocumento, sector, nivelEntidad, codigoUnicoEntidad);
    }

    /**
     * Elimina un contratante del sistema utilizando su número de documento.
     *
     * @param numeroDocumentoEliminarContratante El número de documento del contratante a eliminar.
     */
    public void eliminarContratante(String numeroDocumentoEliminarContratante) {
        this.servicioUsuarios.eliminarContratante(numeroDocumentoEliminarContratante);
    }

    /**
     * Crea un nuevo contratista en el sistema con la información proporcionada.
     *
     * @param tipoPersona El tipo de persona del contratista (Natural o Jurídica).
     * @param tipoDocumento El tipo de documento del contratista (CC, CE, PAS, PPT, NIT).
     * @param numeroDocumento El número de documento único del contratista.
     * @param nombre El nombre completo o razón social del contratista.
     * @param correo El correo electrónico del contratista.
     * @param contrasenha La contraseña para el acceso del contratista.
     * @param telefono El número de teléfono del contratista.
     * @param direccion La dirección de residencia u oficina del contratista.
     * @param ciudad La ciudad del contratista.
     * @param rol El rol asignado al contratista (debería ser {@link Rol#CONTRATISTA}).
     * @param esEntidadPublica Indica si el contratista es una entidad pública.
     * @param areaDesempenho El área de desempeño o especialización del contratista.
     */
    public void crearContratista(TipoPersona tipoPersona, TipoDocumento tipoDocumento, String numeroDocumento, String nombre,
                                 String correo, String contrasenha, String telefono, String direccion, String ciudad, Rol rol,
                                 boolean esEntidadPublica, String areaDesempenho) {
        this.servicioUsuarios.crearContratista(tipoPersona, tipoDocumento, numeroDocumento, nombre, correo, contrasenha,
                telefono, direccion, ciudad, rol, esEntidadPublica, areaDesempenho);
    }

    /**
     * Consulta la información de uno o todos los contratistas registrados en el sistema.
     *
     * @param numeroDocumento El número de documento del contratista a consultar. Si es {@code null} o vacío,
     *                        se consultarán todos los contratistas.
     * @return Una cadena de texto con la información del contratista específico o de todos los contratistas.
     */
    public String consultarContratistas(String numeroDocumento) {
        return this.servicioUsuarios.consultarContratistas(numeroDocumento);
    }

    /**
     * Actualiza la información específica de un contratista existente.
     * Los campos que no se deseen actualizar pueden pasarse como {@code null}.
     *
     * @param numeroDocumento El número de documento del contratista a actualizar.
     * @param esEntidadPublica El nuevo estado de si el contratista es una entidad pública.
     * @param areaDesempenho La nueva área de desempeño del contratista.
     */
    public void actualizarContratista(String numeroDocumento, Boolean esEntidadPublica, String areaDesempenho) {
        this.servicioUsuarios.actualizarContratista(numeroDocumento, esEntidadPublica, areaDesempenho);
    }

    /**
     * Elimina un contratista del sistema utilizando su número de documento.
     *
     * @param numeroDocumentoEliminarContratista El número de documento del contratista a eliminar.
     */
    public void eliminarContratista(String numeroDocumentoEliminarContratista) {
        this.servicioUsuarios.eliminarContratista(numeroDocumentoEliminarContratista);
    }
}
