package edu.uptc.controlador;

import edu.uptc.enums.FaseContrato;
import edu.uptc.enums.Rol;
import edu.uptc.enums.TipoDocumento;
import edu.uptc.enums.TipoPersona;
import edu.uptc.servicios.ServicioContratos;
import edu.uptc.servicios.ServicioReportes;
import edu.uptc.servicios.ServicioUsuarios;

import java.time.LocalDate;

/**
 * Capa de Control de la aplicación.
 *
 * <p><b>Regla de acoplamiento:</b> Esta clase SOLO importa paquetes de enums y de servicios.
 * Está terminantemente prohibido importar, instanciar o manipular cualquier clase del paquete
 * {@code edu.uptc.dominio.*}. Toda interacción con entidades de dominio ocurre exclusivamente
 * dentro de la capa de servicios.</p>
 *
 * <p>Los métodos del Controlador reciben únicamente tipos primitivos, {@link String},
 * enums propios del sistema y tipos de la API estándar de Java (como {@link LocalDate}).
 * Nunca reciben ni devuelven objetos de dominio.</p>
 */
public class Controlador {

    private final ServicioUsuarios   servicioUsuarios;
    private final ServicioContratos  servicioContratos;
    private final ServicioReportes   servicioReportes;

    /**
     * Constructor del Controlador.
     * Crea e inyecta las dependencias entre servicios: ServicioContratos recibe
     * una referencia a ServicioUsuarios para poder resolver IDs internamente.
     */
    public Controlador() {
        this.servicioUsuarios  = new ServicioUsuarios();
        this.servicioContratos = new ServicioContratos(servicioUsuarios);
        this.servicioReportes  = new ServicioReportes();
    }

    /**
     * Crea el administrador por defecto del sistema (configuración inicial).
     */
    public void crearAdministrador() {
        servicioUsuarios.crearAdministrador();
    }

    /**
     * Valida las credenciales de inicio de sesión de un usuario.
     *
     * @param numeroDocumento Número de documento del usuario.
     * @param contrasenha     Contraseña del usuario.
     * @return true si las credenciales son correctas.
     */
    public boolean loginCorrecto(String numeroDocumento, String contrasenha) {
        return servicioUsuarios.loginCorrecto(numeroDocumento, contrasenha);
    }

    /**
     * Devuelve el rol del usuario identificado por su número de documento.
     *
     * @param numeroDocumento Número de documento del usuario.
     * @return El {@link Rol} del usuario, o null si no existe.
     */
    public Rol rolLogueado(String numeroDocumento) {
        return servicioUsuarios.rolLogueado(numeroDocumento);
    }

    /**
     * Devuelve la información del usuario logueado como texto formateado.
     *
     * @param numeroDocumento Número de documento del usuario.
     * @return Texto con la información del usuario.
     */
    public String obtenerInfoUsuario(String numeroDocumento) {
        return servicioUsuarios.obtenerInfoUsuario(numeroDocumento);
    }

    /**
     * Verifica si un número de documento ya existe en el sistema.
     *
     * @param numeroDocumento Número a verificar.
     * @return true si ya existe.
     */
    public boolean numeroDocumentoExiste(String numeroDocumento) {
        return servicioUsuarios.numeroDocumentoExiste(numeroDocumento);
    }

    /**
     * Crea un nuevo contratante en el sistema.
     * Todos los parámetros son tipos primitivos o String; el Controlador no conoce
     * la clase Contratante de dominio.
     *
     * @param tipoPersona        Código numérico: 1=NATURAL, 2=JURIDICA.
     * @param tipoDocumento      Código numérico: 1=CC, 2=CE, 3=PAS, 4=PPT, 5=NIT.
     * @param numeroDocumento    Número de documento único.
     * @param nombre             Nombre o razón social.
     * @param correo             Correo electrónico.
     * @param contrasenha        Contraseña de acceso.
     * @param telefono           Teléfono de contacto.
     * @param direccion          Dirección.
     * @param ciudad             Ciudad.
     * @param sector             Sector económico.
     * @param nivelEntidad       Nivel de la entidad.
     * @param codigoUnicoEntidad Código único de entidad.
     */
    public void crearContratante(int tipoPersona, int tipoDocumento, String numeroDocumento,
                                 String nombre, String correo, String contrasenha,
                                 String telefono, String direccion, String ciudad,
                                 String sector, String nivelEntidad, String codigoUnicoEntidad) {
        TipoPersona  tp  = mapearTipoPersona(tipoPersona);
        TipoDocumento td = mapearTipoDocumento(tipoDocumento);
        servicioUsuarios.crearContratante(tp, td, numeroDocumento, nombre, correo, contrasenha,
                telefono, direccion, ciudad, Rol.CONTRATANTE, sector, nivelEntidad, codigoUnicoEntidad);
    }

    /**
     * Consulta la información de un contratante por número de documento.
     *
     * @param numeroDocumento Número de documento del contratante.
     * @return Texto con la información del contratante.
     */
    public String consultarContratante(String numeroDocumento) {
        return servicioUsuarios.consultarContratantes(numeroDocumento);
    }

    /**
     * Actualiza la información general (campos comunes de Usuario) de un contratante.
     * Los parámetros nulos no modifican el campo correspondiente.
     *
     * @param numeroDocumento Número de documento del contratante a actualizar.
     * @param tipoPersona     Código: 1=NATURAL, 2=JURIDICA, 0=sin cambio.
     * @param tipoDocumento   Código: 1-5, 0=sin cambio.
     * @param nombre          Nuevo nombre (null=sin cambio).
     * @param correo          Nuevo correo (null=sin cambio).
     * @param contrasenha     Nueva contraseña (null=sin cambio).
     * @param telefono        Nuevo teléfono (null=sin cambio).
     * @param direccion       Nueva dirección (null=sin cambio).
     * @param ciudad          Nueva ciudad (null=sin cambio).
     */
    public void actualizarUsuarioBase(String numeroDocumento, int tipoPersona, int tipoDocumento,
                                      String nombre, String correo, String contrasenha,
                                      String telefono, String direccion, String ciudad) {
        TipoPersona  tp  = (tipoPersona  > 0) ? mapearTipoPersona(tipoPersona)   : null;
        TipoDocumento td = (tipoDocumento > 0) ? mapearTipoDocumento(tipoDocumento) : null;
        servicioUsuarios.actualizarUsuario(tp, td, numeroDocumento, nombre, correo,
                contrasenha, telefono, direccion, ciudad);
    }

    /**
     * Actualiza los campos exclusivos de un contratante.
     *
     * @param numeroDocumento    Número de documento del contratante.
     * @param sector             Nuevo sector (null=sin cambio).
     * @param nivelEntidad       Nuevo nivel de entidad (null=sin cambio).
     * @param codigoUnicoEntidad Nuevo código único (null=sin cambio).
     */
    public void actualizarContratante(String numeroDocumento, String sector,
                                      String nivelEntidad, String codigoUnicoEntidad) {
        servicioUsuarios.actualizarContratante(numeroDocumento, sector, nivelEntidad, codigoUnicoEntidad);
    }

    /**
     * Elimina un contratante por número de documento.
     *
     * @param numeroDocumento Número de documento a eliminar.
     */
    public void eliminarContratante(String numeroDocumento) {
        servicioUsuarios.eliminarContratante(numeroDocumento);
    }

    /**
     * Devuelve la lista de todos los contratantes como texto.
     *
     * @return Texto con todos los contratantes.
     */
    public String mostrarContratantes() {
        return servicioUsuarios.mostrarContratantes();
    }

    /**
     * Crea un nuevo contratista en el sistema.
     *
     * @param tipoPersona      Código: 1=NATURAL, 2=JURIDICA.
     * @param tipoDocumento    Código: 1=CC, 2=CE, 3=PAS, 4=PPT, 5=NIT.
     * @param numeroDocumento  Número de documento único.
     * @param nombre           Nombre o razón social.
     * @param correo           Correo electrónico.
     * @param contrasenha      Contraseña de acceso.
     * @param telefono         Teléfono de contacto.
     * @param direccion        Dirección.
     * @param ciudad           Ciudad.
     * @param esEntidadPublica true=entidad pública, false=privada.
     * @param areaDesempenho   Área de desempeño.
     */
    public void crearContratista(int tipoPersona, int tipoDocumento, String numeroDocumento,
                                 String nombre, String correo, String contrasenha,
                                 String telefono, String direccion, String ciudad,
                                 boolean esEntidadPublica, String areaDesempenho) {
        TipoPersona  tp  = mapearTipoPersona(tipoPersona);
        TipoDocumento td = mapearTipoDocumento(tipoDocumento);
        servicioUsuarios.crearContratista(tp, td, numeroDocumento, nombre, correo, contrasenha,
                telefono, direccion, ciudad, Rol.CONTRATISTA, esEntidadPublica, areaDesempenho);
    }

    /**
     * Consulta la información de un contratista por número de documento.
     *
     * @param numeroDocumento Número de documento del contratista.
     * @return Texto con la información del contratista.
     */
    public String consultarContratista(String numeroDocumento) {
        return servicioUsuarios.consultarContratistas(numeroDocumento);
    }

    /**
     * Actualiza los campos exclusivos de un contratista.
     *
     * @param numeroDocumento  Número de documento del contratista.
     * @param esEntidadPublica Nuevo valor (null=sin cambio).
     * @param areaDesempenho   Nueva área (null=sin cambio).
     */
    public void actualizarContratista(String numeroDocumento, Boolean esEntidadPublica,
                                      String areaDesempenho) {
        servicioUsuarios.actualizarContratista(numeroDocumento, esEntidadPublica, areaDesempenho);
    }

    /**
     * Elimina un contratista por número de documento.
     *
     * @param numeroDocumento Número de documento a eliminar.
     */
    public void eliminarContratista(String numeroDocumento) {
        servicioUsuarios.eliminarContratista(numeroDocumento);
    }

    /**
     * Devuelve la lista de todos los contratistas como texto.
     *
     * @return Texto con todos los contratistas.
     */
    public String mostrarContratistas() {
        return servicioUsuarios.mostrarContratistas();
    }

    /**
     * Crea un contrato de Prestación de Servicios.
     * El contratante se identifica por su número de documento.
     *
     * @param idContrato            ID único del contrato.
     * @param objetoContrato        Descripción del objeto.
     * @param documentoContratante  Documento del contratante (resuelto internamente por el servicio).
     * @param valorCelebrar         Valor total.
     * @param plazoEjecucion        Fecha límite.
     * @param perfilRequerido       Perfil requerido.
     * @param entregables           Entregables esperados.
     * @param valorHonorarioMensual Honorario mensual.
     * @return Mensaje de resultado ("OK" o mensaje de error).
     */
    public String crearContratoPrestacionServicios(String idContrato, String objetoContrato,
                                                   String documentoContratante, double valorCelebrar,
                                                   LocalDate plazoEjecucion, String perfilRequerido,
                                                   String entregables, double valorHonorarioMensual) {
        return servicioContratos.crearContratoPrestacionServicios(idContrato, objetoContrato,
                documentoContratante, valorCelebrar, plazoEjecucion,
                perfilRequerido, entregables, valorHonorarioMensual);
    }

    /**
     * Crea un contrato de Obra Pública.
     *
     * @param idContrato           ID único del contrato.
     * @param objetoContrato       Descripción del objeto.
     * @param documentoContratante Documento del contratante.
     * @param valorCelebrar        Valor total.
     * @param plazoEjecucion       Fecha límite.
     * @param ubicacionObra        Ubicación de la obra.
     * @param areaIntervencion     Área de intervención m².
     * @return Mensaje de resultado.
     */
    public String crearContratoObraPublica(String idContrato, String objetoContrato,
                                           String documentoContratante, double valorCelebrar,
                                           LocalDate plazoEjecucion, String ubicacionObra,
                                           double areaIntervencion) {
        return servicioContratos.crearContratoObraPublica(idContrato, objetoContrato,
                documentoContratante, valorCelebrar, plazoEjecucion, ubicacionObra, areaIntervencion);
    }

    /**
     * Crea un contrato de Compraventa.
     *
     * @param idContrato           ID único del contrato.
     * @param objetoContrato       Descripción del objeto.
     * @param documentoContratante Documento del contratante.
     * @param plazoEjecucion       Fecha límite.
     * @param itemAdquirir         Ítem a adquirir.
     * @param marca                Marca.
     * @param modelo               Modelo.
     * @param serie                Serie.
     * @param valorUnitario        Valor unitario.
     * @param cantidadAdquirir     Cantidad.
     * @return Mensaje de resultado.
     */
    public String crearContratoCompraVenta(String idContrato, String objetoContrato,
                                           String documentoContratante, LocalDate plazoEjecucion,
                                           String itemAdquirir, String marca, String modelo,
                                           String serie, double valorUnitario, int cantidadAdquirir) {
        return servicioContratos.crearContratoCompraVenta(idContrato, objetoContrato,
                documentoContratante, plazoEjecucion, itemAdquirir, marca, modelo,
                serie, valorUnitario, cantidadAdquirir);
    }

    /**
     * Consulta la información de un contrato por ID.
     *
     * @param idContrato ID del contrato.
     * @return Texto formateado del contrato.
     */
    public String consultarContrato(String idContrato) {
        return servicioContratos.consultarContrato(idContrato);
    }

    /**
     * Verifica si un ID de contrato ya existe.
     *
     * @param idContrato ID a verificar.
     * @return true si existe.
     */
    public boolean existeIdContrato(String idContrato) {
        return servicioContratos.existeIdContrato(idContrato);
    }

    /**
     * Actualiza los atributos generales de un contrato.
     *
     * @param idContrato     ID del contrato.
     * @param objetoContrato Nuevo objeto (null=sin cambio).
     * @param valorCelebrar  Nuevo valor (null=sin cambio).
     * @param plazoEjecucion Nuevo plazo (null=sin cambio).
     * @param faseActual     Nueva fase (null=sin cambio).
     */
    public void actualizarContratoGeneral(String idContrato, String objetoContrato,
                                          Double valorCelebrar, LocalDate plazoEjecucion,
                                          FaseContrato faseActual) {
        servicioContratos.actualizarContratoGeneral(idContrato, objetoContrato,
                valorCelebrar, plazoEjecucion, faseActual);
    }

    /**
     * Elimina un contrato por ID.
     *
     * @param idContrato ID del contrato a eliminar.
     */
    public void eliminarContrato(String idContrato) {
        servicioContratos.eliminarContrato(idContrato);
    }

    /**
     * Devuelve la información de todos los contratos.
     *
     * @return Texto con todos los contratos.
     */
    public String obtenerTodosLosContratos() {
        return servicioContratos.obtenerTodosLosContratos();
    }

    /**
     * Postula un contratista a un contrato (fase PUBLICACION → LICITACION).
     * El campo contratista del contrato permanece en null hasta la adjudicación.
     *
     * @param idContrato           ID del contrato.
     * @param documentoContratista Documento del contratista interesado.
     * @return Mensaje de resultado.
     */
    public String postularContratista(String idContrato, String documentoContratista) {
        return servicioContratos.postularContratista(idContrato, documentoContratista);
    }

    /**
     * Lista los contratistas postulados a un contrato en fase LICITACION.
     *
     * @param idContrato ID del contrato.
     * @return Texto con la lista de postulados.
     */
    public String listarPostulados(String idContrato) {
        return servicioContratos.listarPostulados(idContrato);
    }

    /**
     * Adjudica formalmente un contrato a un contratista (fase LICITACION → ADJUDICACION).
     * El contratista debe estar en la lista de postulados.
     *
     * @param idContrato           ID del contrato.
     * @param documentoContratista Documento del contratista a adjudicar.
     * @return Mensaje de resultado.
     */
    public String adjudicarContrato(String idContrato, String documentoContratista) {
        return servicioContratos.adjudicarContrato(idContrato, documentoContratista);
    }

    /**
     * Inicia la ejecución del contrato (fase ADJUDICACION → EJECUCION).
     *
     * @param idContrato ID del contrato.
     * @return Mensaje de resultado.
     */
    public String iniciarEjecucion(String idContrato) {
        return servicioContratos.iniciarEjecucion(idContrato);
    }

    /**
     * Cambia el estado (fase) de un contrato y genera el reporte de interventoría.
     *
     * @param idContrato ID del contrato.
     * @param nuevaFase  Nueva fase.
     * @param informe    Informe del cambio.
     * @return Mensaje de resultado.
     */
    public String cambiarEstadoContrato(String idContrato, FaseContrato nuevaFase, String informe) {
        return servicioContratos.cambiarEstadoContrato(idContrato, nuevaFase, informe, servicioReportes);
    }

    /**
     * Devuelve todos los reportes de interventoría.
     *
     * @return Texto con todos los reportes.
     */
    public String obtenerTodosLosReportes() {
        return servicioReportes.obtenerTodosLosReportes();
    }

    /**
     * Devuelve los reportes de interventoría de un contrato específico.
     *
     * @param idContrato ID del contrato.
     * @return Texto con los reportes del contrato.
     */
    public String obtenerReportesPorContrato(String idContrato) {
        return servicioReportes.obtenerReportesPorContrato(idContrato);
    }

    /**
     * Mapea un código numérico a un tipo de persona {@link TipoPersona}.
     *
     * @param codigo El código numérico del tipo de persona.
     * @return El {@link TipoPersona} correspondiente, o {@code null} si el código no es válido.
     */
    private TipoPersona mapearTipoPersona(int codigo) {
        return switch (codigo) {
            case 1 -> TipoPersona.NATURAL;
            case 2 -> TipoPersona.JURIDICA;
            default -> null;
        };
    }

    /**
     * Mapea un código numérico a un tipo de documento {@link TipoDocumento}.
     *
     * @param codigo El código numérico del tipo de documento.
     * @return El {@link TipoDocumento} correspondiente, o {@code null} si el código no es válido.
     */
    private TipoDocumento mapearTipoDocumento(int codigo) {
        return switch (codigo) {
            case 1 -> TipoDocumento.CC;
            case 2 -> TipoDocumento.CE;
            case 3 -> TipoDocumento.PAS;
            case 4 -> TipoDocumento.PPT;
            case 5 -> TipoDocumento.NIT;
            default -> null;
        };
    }
}
