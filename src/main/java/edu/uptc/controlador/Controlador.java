package edu.uptc.controlador;

import edu.uptc.dominio.Contratante;
import edu.uptc.dominio.Contratista;
import edu.uptc.dominio.ContratoObraPublica;
import edu.uptc.dominio.ReporteInterventoria;
import edu.uptc.enums.FaseContrato;
import edu.uptc.enums.Rol;
import edu.uptc.enums.TipoDocumento;
import edu.uptc.enums.TipoPersona;
import edu.uptc.servicios.ServicioContratos;
import edu.uptc.servicios.ServicioReportes;
import edu.uptc.servicios.ServicioUsuarios;

import java.time.LocalDate;

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
     * @param contrasenha     La contraseña del usuario.
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
     * @param tipoPersona     El nuevo tipo de persona del usuario (Natural o Jurídica).
     * @param tipoDocumento   El nuevo tipo de documento del usuario (CC, CE, PAS, PPT, NIT).
     * @param numeroDocumento El número de documento del usuario a actualizar (identificador único).
     * @param nombre          El nuevo nombre completo del usuario.
     * @param correo          El nuevo correo electrónico del usuario.
     * @param contrasenha     La nueva contraseña del usuario.
     * @param telefono        El nuevo número de teléfono del usuario.
     * @param direccion       La nueva dirección de residencia u oficina del usuario.
     * @param ciudad          La nueva ciudad de residencia del usuario.
     */
    public void actualizarUsuario(TipoPersona tipoPersona, TipoDocumento tipoDocumento, String numeroDocumento, String nombre,
                                  String correo, String contrasenha, String telefono, String direccion, String ciudad) {
        this.servicioUsuarios.actualizarUsuario(tipoPersona, tipoDocumento, numeroDocumento, nombre, correo, contrasenha,
                telefono, direccion, ciudad);
    }

    /**
     * Crea un nuevo contratante en el sistema con la información proporcionada.
     *
     * @param tipoPersona        El tipo de persona del contratante (Natural o Jurídica).
     * @param tipoDocumento      El tipo de documento del contratante (CC, CE, PAS, PPT, NIT).
     * @param numeroDocumento    El número de documento único del contratante.
     * @param nombre             El nombre completo o razón social del contratante.
     * @param correo             El correo electrónico del contratante.
     * @param contrasenha        La contraseña para el acceso del contratante.
     * @param telefono           El número de teléfono del contratante.
     * @param direccion          La dirección de residencia u oficina del contratante.
     * @param ciudad             La ciudad del contratante.
     * @param rol                El rol asignado al contratante (debería ser {@link Rol#CONTRATANTE}).
     * @param sector             El sector económico al que pertenece el contratante.
     * @param nivelEntidad       El nivel de la entidad (e.g., nacional, departamental, municipal).
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
     * @param numeroDocumento    El número de documento del contratante a actualizar.
     * @param sector             El nuevo sector económico del contratante.
     * @param nivelEntidad       El nuevo nivel de entidad del contratante.
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
     * @param tipoPersona      El tipo de persona del contratista (Natural o Jurídica).
     * @param tipoDocumento    El tipo de documento del contratista (CC, CE, PAS, PPT, NIT).
     * @param numeroDocumento  El número de documento único del contratista.
     * @param nombre           El nombre completo o razón social del contratista.
     * @param correo           El correo electrónico del contratista.
     * @param contrasenha      La contraseña para el acceso del contratista.
     * @param telefono         El número de teléfono del contratista.
     * @param direccion        La dirección de residencia u oficina del contratista.
     * @param ciudad           La ciudad del contratista.
     * @param rol              El rol asignado al contratista (debería ser {@link Rol#CONTRATISTA}).
     * @param esEntidadPublica Indica si el contratista es una entidad pública.
     * @param areaDesempenho   El área de desempeño o especialización del contratista.
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
     * @param numeroDocumento  El número de documento del contratista a actualizar.
     * @param esEntidadPublica El nuevo estado de si el contratista es una entidad pública.
     * @param areaDesempenho   La nueva área de desempeño del contratista.
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

    /**
     * Crea un nuevo contrato de prestación de servicios.
     *
     * @param idContrato          El identificador único del contrato.
     * @param objetoContrato      La descripción del objeto del contrato.
     * @param contratante         El objeto Contratante asociado al contrato.
     * @param valorCelebrar       El valor total a celebrar del contrato.
     * @param plazoEjecucion      La fecha de finalización del plazo de ejecución del contrato.
     * @param perfilRequerido     El perfil profesional requerido para el contrato.
     * @param entregables         Los entregables esperados del contrato.
     * @param valorHonorarioMensual El valor del honorario mensual para el contratista.
     */
    public void crearContratoPrestacionServicios(String idContrato, String objetoContrato,
                                                 Contratante contratante, double valorCelebrar, LocalDate plazoEjecucion,
                                                 String perfilRequerido,
                                                 String entregables, double valorHonorarioMensual) {
        this.servicioContratos.crearContratoPrestacionServicios(idContrato, objetoContrato, contratante, valorCelebrar, plazoEjecucion,
                perfilRequerido, entregables, valorHonorarioMensual);
    }

    /**
     * Crea un nuevo contrato de obra pública.
     *
     * @param idContrato         El identificador único del contrato.
     * @param objetoContrato     La descripción del objeto del contrato.
     * @param contratante        El objeto Contratante asociado al contrato.
     * @param valorCelebrar      El valor total a celebrar del contrato.
     * @param plazoEjecucion     La fecha de finalización del plazo de ejecución del contrato.
     * @param ubicacionObra      La ubicación donde se realizará la obra.
     * @param areaIntervencion   El área de intervención de la obra en metros cuadrados.
     */
    public void crearContratoObraPublica(String idContrato, String objetoContrato,
                                         Contratante contratante, double valorCelebrar, LocalDate plazoEjecucion,
                                         String ubicacionObra, double areaIntervencion) {
        this.servicioContratos.crearContratoObraPublica(idContrato, objetoContrato, contratante,
                valorCelebrar, plazoEjecucion, ubicacionObra, areaIntervencion);
    }

    /**
     * Crea un nuevo contrato de compraventa.
     *
     * @param idContrato         El identificador único del contrato.
     * @param objetoContrato     La descripción del objeto del contrato.
     * @param contratante        El objeto Contratante asociado al contrato.
     * @param plazoEjecucion     La fecha de finalización del plazo de ejecución del contrato.
     * @param itemAdquirir       El nombre del ítem a adquirir.
     * @param marca              La marca del ítem a adquirir.
     * @param modelo             El modelo del ítem a adquirir.
     * @param serie              El número de serie del ítem a adquirir.
     * @param valorUnitario      El valor unitario del ítem.
     * @param cantidadAdquirir   La cantidad de ítems a adquirir.
     */
    public void crearContratoCompraVenta(String idContrato, String objetoContrato,
                                         Contratante contratante, LocalDate plazoEjecucion,
                                         String itemAdquirir, String marca, String modelo,
                                         String serie, double valorUnitario, int cantidadAdquirir) {
        this.servicioContratos.crearContratoCompraVenta(idContrato, objetoContrato, contratante, plazoEjecucion,
                itemAdquirir, marca, modelo, serie, valorUnitario, cantidadAdquirir);
    }

    /**
     * Consulta la información de un contrato específico por su identificador.
     *
     * @param idConsultar El identificador del contrato a consultar.
     * @return Una cadena de texto con la información del contrato si existe, o un mensaje indicando que no existe.
     */
    public String consultarContrato(String idConsultar) {
        return this.servicioContratos.consultarContrato(idConsultar);
    }

    /**
     * Actualiza los atributos generales de un contrato existente.
     * Los parámetros nulos indican que el atributo correspondiente no debe ser modificado.
     *
     * @param idContrato     El identificador del contrato a actualizar.
     * @param objetoContrato La nueva descripción del objeto del contrato (puede ser null).
     * @param valorCelebrar  El nuevo valor a celebrar del contrato (puede ser null).
     * @param plazoEjecucion La nueva fecha de finalización del plazo de ejecución (puede ser null).
     * @param faseActual     La nueva fase actual del contrato (puede ser null).
     */
    public void actualizarContratoGeneral(String idContrato, String objetoContrato, Double valorCelebrar,
                                          LocalDate plazoEjecucion, FaseContrato faseActual) {
        this.servicioContratos.actualizarContratoGeneral(idContrato, objetoContrato, valorCelebrar, plazoEjecucion,
                faseActual);
    }

    /**
     * Actualiza los atributos específicos de un contrato de prestación de servicios.
     * Los parámetros nulos indican que el atributo correspondiente no debe ser modificado.
     *
     * @param idContrato          El identificador del contrato de prestación de servicios a actualizar.
     * @param perfilRequerido     El nuevo perfil requerido (puede ser null).
     * @param entregables         Los nuevos entregables (puede ser null).
     * @param valorHonorarioMensual El nuevo valor del honorario mensual (puede ser null).
     */
    public void actualizarContratoPrestacionServicios(String idContrato, String perfilRequerido, String entregables,
                                                      Double valorHonorarioMensual) {
        this.servicioContratos.actualizarContratoPrestacionServicios(idContrato, perfilRequerido, entregables, valorHonorarioMensual);
    }

    /**
     * Actualiza los atributos específicos de un contrato de compraventa.
     * Los parámetros nulos indican que el atributo correspondiente no debe ser modificado.
     *
     * @param idContrato       El identificador del contrato de compraventa a actualizar.
     * @param itemAdquirir     El nuevo nombre del ítem a adquirir (puede ser null).
     * @param marca            La nueva marca del ítem (puede ser null).
     * @param modelo           El nuevo modelo del ítem (puede ser null).
     * @param serie            El nuevo número de serie del ítem (puede ser null).
     * @param valorUnitario    El nuevo valor unitario del ítem (puede ser null).
     * @param cantidadAdquirir La nueva cantidad de ítems a adquirir (puede ser null).
     */
    public void actualizarContratoCompraVenta(String idContrato, String itemAdquirir, String marca, String modelo, String serie, Double valorUnitario, Integer cantidadAdquirir) {
        this.servicioContratos.actualizarContratoCompraVenta(idContrato, itemAdquirir, marca, modelo, serie, valorUnitario, cantidadAdquirir);
    }

    /**
     * Actualiza los atributos específicos de un contrato de obra pública.
     * Los parámetros nulos indican que el atributo correspondiente no debe ser modificado.
     *
     * @param idContrato       El identificador del contrato de obra pública a actualizar.
     * @param ubicacionObra    La nueva ubicación de la obra (puede ser null).
     * @param areaIntervencion La nueva área de intervención (puede ser null).
     */
    public void actualizarContratoObraPublica(String idContrato, String ubicacionObra, Double areaIntervencion) {
        this.servicioContratos.actualizarContratoObraPublica(idContrato, ubicacionObra, areaIntervencion);
    }

    /**
     * Elimina un contrato del sistema utilizando su identificador.
     *
     * @param idEliminar El identificador del contrato a eliminar.
     */
    public void eliminarContrato(String idEliminar) {
        this.servicioContratos.eliminarContrato(idEliminar);
    }

    /**
     * Asigna un contratista a un contrato específico y cambia su fase a ADJUDICACION.
     *
     * @param idContrato         El identificador del contrato al que se asignará el contratista.
     * @param contratistaAsignar El objeto Contratista a asignar.
     */
    public void asignarContrato(String idContrato, Contratista contratistaAsignar) {
        this.servicioContratos.asignarContrato(idContrato, contratistaAsignar);
    }

    /**
     * Selecciona un contratista para un contrato, actualizando el contratista asociado
     * y cambiando la fase del contrato a LICITACION.
     *
     * @param idContrato           El identificador del contrato a seleccionar.
     * @param documentoContratista El documento de identificación del contratista interesado.
     * @param servicioUsuarios     El servicio de usuarios para obtener la información del contratista.
     */
    public void seleccionarContrato(String idContrato, String documentoContratista, ServicioUsuarios servicioUsuarios) {
        this.servicioContratos.seleccionarContrato(idContrato, documentoContratista, servicioUsuarios);
    }

    /**
     * Cambia el estado (fase) de un contrato y genera un reporte de interventoría.
     *
     * @param idContrato       El identificador del contrato cuyo estado se va a cambiar.
     * @param nuevaFase        La nueva fase a la que se actualizará el contrato.
     * @param informe          El informe asociado al cambio de fase.
     * @param servicioReportes El servicio de reportes para guardar el nuevo reporte de interventoría.
     */
    public void cambiarEstadoContrato(String idContrato, FaseContrato nuevaFase, String informe, ServicioReportes servicioReportes) {
        this.servicioContratos.cambiarEstadoContrato(idContrato, nuevaFase, informe, servicioReportes);
    }

    /**
     * Valida un contrato de obra pública.
     *
     * @param contratoObraPublica El contrato de obra pública a validar.
     * @return {@code true} si el contrato es válido, {@code false} en caso contrario.
     */
    public boolean validarContratoObraPublica(ContratoObraPublica contratoObraPublica) {
        return this.servicioContratos.validarContratoObraPublica(contratoObraPublica);
    }

    /**
     * Calcula el valor total de adquisición para un contrato de compraventa.
     *
     * @param idContrato El identificador del contrato.
     * @return El valor total de adquisición si el contrato es de compraventa, de lo contrario 0.0.
     */
    public double calcularTotalAdquisicion(String idContrato) {
        return this.servicioContratos.calcularTotalAdquisicion(idContrato);
    }
    
    /**
     * Guarda un nuevo reporte de interventoría en el sistema.
     *
     * @param nuevoReporte El objeto ReporteInterventoria a guardar.
     */
    public void guardarReporte(ReporteInterventoria nuevoReporte){
        this.servicioReportes.guardarReporte(nuevoReporte);
    }

    /**
     * Genera una representación en cadena de texto de un reporte de interventoría específico.
     *
     * @param reporte El objeto ReporteInterventoria a mostrar.
     * @return Una cadena de texto con los detalles del reporte, o un mensaje de error si el reporte es nulo.
     */
    public String mostrarReporte(ReporteInterventoria reporte) {
        return this.servicioReportes.mostrarReporte(reporte);
    }
    
    /**
     * Obtiene una cadena de texto con la información detallada de todos los reportes de
     * interventoría registrados en el sistema.
     *
     * @return Una cadena de texto que contiene todos los reportes, o un mensaje indicando que no hay reportes.
     */
    public String obtenerTodosLosReportes() {
        return this.servicioReportes.obtenerTodosLosReportes();
    }

    /**
     * Obtiene una cadena de texto con la información detallada de los reportes de interventoría asociados a un contrato específico.
     *
     * @param idContrato El identificador del contrato cuyos reportes se desean obtener.
     * @return Una cadena de texto con los reportes del contrato especificado, o un mensaje si no se encuentran reportes para ese contrato.
     */
    public String obtenerReportesPorContrato(String idContrato){
        return this.servicioReportes.obtenerReportesPorContrato(idContrato);
    }
}
