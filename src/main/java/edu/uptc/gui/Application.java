package edu.uptc.gui;

import edu.uptc.controlador.Controlador;
import edu.uptc.enums.FaseContrato;
import edu.uptc.enums.Rol;

import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Punto de entrada de la aplicación SECOP III.
 *
 * <p><b>Regla de acoplamiento:</b> Esta clase SOLO importa {@link Controlador},
 * enums del sistema ({@link Rol}, {@link FaseContrato}) y la API estándar de Java
 * ({@link JOptionPane}, {@link LocalDate}).
 * Está terminantemente prohibido importar, instanciar o referenciar cualquier clase
 * del paquete {@code edu.uptc.dominio.*}.</p>
 *
 * <p>La Vista únicamente delega al Controlador pasando tipos primitivos, Strings y enums.
 * Nunca recibe ni procesa objetos de dominio.</p>
 */
public class Application {

    /**
     * Menú principal de la aplicación.
     */
    private static final String MENU_PRINCIPAL = """
            =======================================================
                    BIENVENIDO AL SECOP III
            =======================================================
              1. Iniciar sesion
              2. Ver contratos publicos
              3. Salir
            =======================================================""";

    /**
     * Menú para el rol de Administrador.
     */
    private static final String MENU_ADMIN = """
            =======================================================
                          MENU ADMINISTRADOR
            =======================================================
              1. Opciones para Contratante
              2. Opciones para Contratista
              3. Mostrar mi informacion
              4. Salir
            =======================================================""";

    /**
     * Menú para el rol de Contratante.
     */
    private static final String MENU_CONTRATANTE = """
            =======================================================
                           MENU CONTRATANTE
            =======================================================
              1. Crear contrato
              2. Consultar contrato
              3. Actualizar contrato
              4. Eliminar contrato
              5. Adjudicar contrato (LICITACION -> ADJUDICACION)
              6. Iniciar ejecucion   (ADJUDICACION -> EJECUCION)
              7. Ver reportes de interventoria
              8. Salir
            =======================================================""";

    /**
     * Menú para el rol de Contratista.
     */
    private static final String MENU_CONTRATISTA = """
            =======================================================
                           MENU CONTRATISTA
            =======================================================
              1. Postularse a un contrato
              2. Registrar cambio de fase
              3. Salir
            =======================================================""";

    /**
     * Opciones para seleccionar el tipo de persona (Natural o Jurídica).
     */
    private static final String OPCIONES_TIPO_PERSONA = """
            Seleccione el Tipo de Persona:
              1. NATURAL
              2. JURÍDICA
            """;

    /**
     * Opciones para seleccionar el tipo de documento de identificación.
     */
    private static final String OPCIONES_TIPO_DOCUMENTO = """
            Seleccione el Tipo de Documento:
              1. CC  (Cédula de Ciudadanía)
              2. CE  (Cédula de Extranjería)
              3. PAS (Pasaporte)
              4. PPT (Permiso por Protección Temporal)
              5. NIT (Número de Identificación Tributaria)
            """;

    /**
     * Método principal que inicia la aplicación.
     * Configura el controlador y gestiona el flujo del menú principal.
     *
     * @param args Argumentos de la línea de comandos (no utilizados).
     */
    public static void main(String[] args) {

        Controlador controlador = new Controlador();
        controlador.crearAdministrador();

        int opcionMenuPrincipal = 0;

        try {
            while (opcionMenuPrincipal != 3) {
                try {
                    String entrada = JOptionPane.showInputDialog(null, MENU_PRINCIPAL,
                            "SECOP III", JOptionPane.PLAIN_MESSAGE);
                    if (entrada == null) break;

                    opcionMenuPrincipal = Integer.parseInt(entrada.trim());

                    switch (opcionMenuPrincipal) {
                        case 1:
                            manejarLogin(controlador);
                            break;

                        case 2:
                            String todosLosContratos = controlador.obtenerTodosLosContratos();
                            JOptionPane.showMessageDialog(null, todosLosContratos,
                                    "Contratos Públicos Registrados", JOptionPane.INFORMATION_MESSAGE);
                            break;

                        case 3:
                            JOptionPane.showMessageDialog(null, "Saliendo de la aplicación. ¡Hasta pronto!");
                            break;

                        default:
                            JOptionPane.showMessageDialog(null,
                                    "Opción no válida. Ingrese 1, 2 ó 3.", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null,
                            "Por favor, ingrese un número válido.", "Error de formato", JOptionPane.ERROR_MESSAGE);
                }
            }
        } finally {
            JOptionPane.showMessageDialog(null, "Gracias por utilizar el sistema SECOP III.");
        }
    }

    /**
     * Gestiona el proceso de inicio de sesión del usuario, valida credenciales
     * y redirige al menú correspondiente según el rol.
     *
     * @param controlador Instancia del {@link Controlador} para interactuar con la lógica de negocio.
     */
    private static void manejarLogin(Controlador controlador) {
        String doc = JOptionPane.showInputDialog(null, "Ingrese su número de documento:");
        if (doc == null) return;
        String pass = JOptionPane.showInputDialog(null, "Ingrese su contraseña:");
        if (pass == null) return;

        if (!controlador.loginCorrecto(doc, pass)) {
            JOptionPane.showMessageDialog(null,
                    "Credenciales incorrectas. Verifique su documento y contraseña.",
                    "Acceso denegado", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Rol rol = controlador.rolLogueado(doc);
        if (rol == null) {
            JOptionPane.showMessageDialog(null, "Rol no reconocido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        switch (rol) {
            case ADMINISTRADOR -> menuAdministrador(controlador, doc);
            case CONTRATANTE -> menuContratante(controlador, doc);
            case CONTRATISTA -> menuContratista(controlador, doc);
            default -> JOptionPane.showMessageDialog(null, "Rol no reconocido.");
        }
    }

    /**
     * Muestra el menú del administrador y gestiona las opciones seleccionadas.
     *
     * @param controlador Instancia del {@link Controlador}.
     * @param docAdmin    Número de documento del administrador logueado.
     */
    private static void menuAdministrador(Controlador controlador, String docAdmin) {
        int op = 0;
        while (op != 4) {
            try {
                String entrada = JOptionPane.showInputDialog(null, MENU_ADMIN,
                        "Administrador", JOptionPane.PLAIN_MESSAGE);
                if (entrada == null) break;
                op = Integer.parseInt(entrada.trim());

                switch (op) {
                    case 1 -> menuGestionContratantes(controlador);
                    case 2 -> menuGestionContratistas(controlador);
                    case 3 -> JOptionPane.showMessageDialog(null,
                            controlador.obtenerInfoUsuario(docAdmin),
                            "Mi Información", JOptionPane.INFORMATION_MESSAGE);
                    case 4 -> JOptionPane.showMessageDialog(null, "Sesión cerrada.");
                    default ->
                            JOptionPane.showMessageDialog(null, "Opción no válida.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Muestra el menú de gestión de contratantes y gestiona las opciones seleccionadas.
     *
     * @param controlador Instancia del {@link Controlador}.
     */
    private static void menuGestionContratantes(Controlador controlador) {
        try {
            String entrada = JOptionPane.showInputDialog(null, """
                    =======================================================
                                    GESTIÓN DE CONTRATANTES
                    =======================================================
                      1. Crear contratante
                      2. Consultar contratante
                      3. Actualizar contratante
                      4. Eliminar contratante
                      5. Mostrar todos los contratantes
                      6. Salir
                    =======================================================
                    Ingrese una opción:""");
            if (entrada == null) return;
            int op = Integer.parseInt(entrada.trim());

            switch (op) {
                case 1 -> crearContratante(controlador);
                case 2 -> {
                    String doc = JOptionPane.showInputDialog("Número de documento del contratante:");
                    if (doc != null)
                        JOptionPane.showMessageDialog(null, controlador.consultarContratante(doc));
                }
                case 3 -> actualizarContratante(controlador);
                case 4 -> eliminarContratante(controlador);
                case 5 -> JOptionPane.showMessageDialog(null,
                        controlador.mostrarContratantes(), "Todos los Contratantes", JOptionPane.PLAIN_MESSAGE);
                case 6 -> { /* Salir */ }
                default -> JOptionPane.showMessageDialog(null, "Opción no válida.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Solicita los datos para crear un nuevo contratante y lo registra en el sistema.
     *
     * @param controlador Instancia del {@link Controlador}.
     */
    private static void crearContratante(Controlador controlador) {
        try {
            int tipoPersona = pedirTipoPersona();
            if (tipoPersona == 0) return;
            int tipoDocumento = pedirTipoDocumento();
            if (tipoDocumento == 0) return;

            String doc = JOptionPane.showInputDialog("Número de documento:");
            if (doc == null) return;
            if (controlador.numeroDocumentoExiste(doc)) {
                JOptionPane.showMessageDialog(null, "El número de documento ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String nombre = JOptionPane.showInputDialog("Nombre o razón social:");
            if (nombre == null) return;
            String correo = JOptionPane.showInputDialog("Correo electrónico:");
            if (correo == null) return;
            String pass = JOptionPane.showInputDialog("Contraseña:");
            if (pass == null) return;
            String tel = JOptionPane.showInputDialog("Teléfono:");
            if (tel == null) return;
            String dir = JOptionPane.showInputDialog("Dirección:");
            if (dir == null) return;
            String ciudad = JOptionPane.showInputDialog("Ciudad:");
            if (ciudad == null) return;
            String sector = JOptionPane.showInputDialog("Sector económico:");
            if (sector == null) return;
            String nivel = JOptionPane.showInputDialog("Nivel de entidad (ej: Nacional, Departamental):");
            if (nivel == null) return;
            String codigo = JOptionPane.showInputDialog("Código único de entidad:");
            if (codigo == null) return;

            controlador.crearContratante(tipoPersona, tipoDocumento, doc, nombre, correo,
                    pass, tel, dir, ciudad, sector, nivel, codigo);
            JOptionPane.showMessageDialog(null, "  Contratante creado exitosamente.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Dato numérico inválido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Solicita el número de documento de un contratante y los datos a actualizar, luego aplica los cambios.
     *
     * @param controlador Instancia del {@link Controlador}.
     */
    private static void actualizarContratante(Controlador controlador) {
        try {
            String doc = JOptionPane.showInputDialog("Número de documento del contratante a actualizar:");
            if (doc == null) return;
            if (!controlador.numeroDocumentoExiste(doc)) {
                JOptionPane.showMessageDialog(null, "Contratante no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (controlador.rolLogueado(doc) == Rol.ADMINISTRADOR) {
                JOptionPane.showMessageDialog(null,
                        "No se puede modificar la cuenta del Administrador.", "Restringido", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String submenu = """
                    ¿Qué campo desea modificar del Contratante?
                      1. Tipo de Persona      6. Dirección
                      2. Tipo de Documento    7. Ciudad
                      3. Nombre               8. Sector
                      4. Correo               9. Nivel de Entidad
                      5. Teléfono            10. Código Único
                                             11. Cancelar
                    """;
            String entradaSub = JOptionPane.showInputDialog(submenu);
            if (entradaSub == null) return;
            int opSub = Integer.parseInt(entradaSub.trim());

            int tipoPersona = 0, tipoDocumento = 0;
            String nombre = null, correo = null, contrasenha = null,
                    tel = null, dir = null, ciudad = null;
            String sector = null, nivel = null, codigo = null;

            switch (opSub) {
                case 1 -> {
                    tipoPersona = pedirTipoPersona();
                    if (tipoPersona == 0) return;
                }
                case 2 -> {
                    tipoDocumento = pedirTipoDocumento();
                    if (tipoDocumento == 0) return;
                }
                case 3 -> nombre = JOptionPane.showInputDialog("Nuevo nombre:");
                case 4 -> correo = JOptionPane.showInputDialog("Nuevo correo:");
                case 5 -> tel = JOptionPane.showInputDialog("Nuevo teléfono:");
                case 6 -> dir = JOptionPane.showInputDialog("Nueva dirección:");
                case 7 -> ciudad = JOptionPane.showInputDialog("Nueva ciudad:");
                case 8 -> sector = JOptionPane.showInputDialog("Nuevo sector:");
                case 9 -> nivel = JOptionPane.showInputDialog("Nuevo nivel de entidad:");
                case 10 -> codigo = JOptionPane.showInputDialog("Nuevo código único de entidad:");
                case 11 -> {
                    JOptionPane.showMessageDialog(null, "Actualización cancelada.");
                    return;
                }
                default -> {
                    JOptionPane.showMessageDialog(null, "Opción no válida.");
                    return;
                }
            }

            if (opSub >= 1 && opSub <= 7) {
                controlador.actualizarUsuarioBase(doc, tipoPersona, tipoDocumento,
                        nombre, correo, contrasenha, tel, dir, ciudad);
            }
            if (opSub >= 8 && opSub <= 10) {
                controlador.actualizarContratante(doc, sector, nivel, codigo);
            }
            JOptionPane.showMessageDialog(null, "  Contratante actualizado correctamente.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Dato numérico inválido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Solicita el número de documento de un contratante y lo elimina del sistema previa confirmación.
     *
     * @param controlador Instancia del {@link Controlador}.
     */
    private static void eliminarContratante(Controlador controlador) {
        String doc = JOptionPane.showInputDialog("Número de documento del contratante a eliminar:");
        if (doc == null) return;
        if (!controlador.numeroDocumentoExiste(doc)) {
            JOptionPane.showMessageDialog(null, "No existe un contratante con ese documento.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(null,
                "¿Confirma eliminar al contratante con documento " + doc + "?",
                "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            controlador.eliminarContratante(doc);
            JOptionPane.showMessageDialog(null, "  Contratante eliminado exitosamente.");
        }
    }

    /**
     * Muestra el menú de gestión de contratistas y gestiona las opciones seleccionadas.
     *
     * @param controlador Instancia del {@link Controlador}.
     */
    private static void menuGestionContratistas(Controlador controlador) {
        try {
            String entrada = JOptionPane.showInputDialog(null, """
                    =======================================================
                                    GESTIÓN DE CONTRATISTAS
                    =======================================================
                      1. Crear contratista
                      2. Consultar contratista
                      3. Actualizar contratista
                      4. Mostrar todos los contratistas
                      5. Eliminar contratista
                      6. Salir
                    =======================================================
                    Ingrese una opción:""");
            if (entrada == null) return;
            int op = Integer.parseInt(entrada.trim());

            switch (op) {
                case 1 -> crearContratista(controlador);
                case 2 -> {
                    String doc = JOptionPane.showInputDialog("Número de documento del contratista:");
                    if (doc != null)
                        JOptionPane.showMessageDialog(null, controlador.consultarContratista(doc));
                }
                case 3 -> actualizarContratista(controlador);
                case 4 -> JOptionPane.showMessageDialog(null,
                        controlador.mostrarContratistas(), "Todos los Contratistas", JOptionPane.PLAIN_MESSAGE);
                case 5 -> eliminarContratista(controlador);
                case 6 -> { /* Salir */ }
                default -> JOptionPane.showMessageDialog(null, "Opción no válida.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Solicita los datos para crear un nuevo contratista y lo registra en el sistema.
     *
     * @param controlador Instancia del {@link Controlador}.
     */
    private static void crearContratista(Controlador controlador) {
        try {
            int tipoPersona = pedirTipoPersona();
            if (tipoPersona == 0) return;
            int tipoDocumento = pedirTipoDocumento();
            if (tipoDocumento == 0) return;

            String doc = JOptionPane.showInputDialog("Número de documento:");
            if (doc == null) return;
            String nombre = JOptionPane.showInputDialog("Nombre o razón social:");
            if (nombre == null) return;
            String correo = JOptionPane.showInputDialog("Correo electrónico:");
            if (correo == null) return;
            String pass = JOptionPane.showInputDialog("Contraseña:");
            if (pass == null) return;
            String tel = JOptionPane.showInputDialog("Teléfono:");
            if (tel == null) return;
            String dir = JOptionPane.showInputDialog("Dirección:");
            if (dir == null) return;
            String ciudad = JOptionPane.showInputDialog("Ciudad:");
            if (ciudad == null) return;

            String entEntrada = JOptionPane.showInputDialog(
                    "¿Pertenece a una entidad pública?\n  1. Sí\n  2. No");
            if (entEntrada == null) return;
            int opEnt = Integer.parseInt(entEntrada.trim());
            if (opEnt != 1 && opEnt != 2) {
                JOptionPane.showMessageDialog(null, "Opción inválida (1 o 2).", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            boolean esEntidadPublica = (opEnt == 1);

            String area = JOptionPane.showInputDialog("Área de desempeño:");
            if (area == null) return;

            controlador.crearContratista(tipoPersona, tipoDocumento, doc, nombre, correo,
                    pass, tel, dir, ciudad, esEntidadPublica, area);
            JOptionPane.showMessageDialog(null, "  Contratista creado exitosamente.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Dato numérico inválido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Solicita el número de documento de un contratista y los datos a actualizar, luego aplica los cambios.
     *
     * @param controlador Instancia del {@link Controlador}.
     */
    private static void actualizarContratista(Controlador controlador) {
        try {
            String doc = JOptionPane.showInputDialog("Número de documento del contratista a actualizar:");
            if (doc == null) return;
            if (!controlador.numeroDocumentoExiste(doc)) {
                JOptionPane.showMessageDialog(null, "Contratista no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (controlador.rolLogueado(doc) == Rol.ADMINISTRADOR) {
                JOptionPane.showMessageDialog(null,
                        "No se puede modificar la cuenta del Administrador.", "Restringido", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String submenu = """
                    ¿Qué campo desea modificar del Contratista?
                      1. Tipo de Persona      5. Teléfono
                      2. Tipo de Documento    6. Dirección
                      3. Nombre               7. Ciudad
                      4. Correo               8. Entidad Pública (Exclusivo)
                                              9. Área de Desempeño (Exclusivo)
                                             10. Cancelar
                    """;
            String entradaSub = JOptionPane.showInputDialog(submenu);
            if (entradaSub == null) return;
            int opSub = Integer.parseInt(entradaSub.trim());

            int tipoPersona = 0, tipoDocumento = 0;
            String nombre = null, correo = null, contrasenha = null,
                    tel = null, dir = null, ciudad = null;
            Boolean esEntidadPublica = null;
            String area = null;

            switch (opSub) {
                case 1 -> {
                    tipoPersona = pedirTipoPersona();
                    if (tipoPersona == 0) return;
                }
                case 2 -> {
                    tipoDocumento = pedirTipoDocumento();
                    if (tipoDocumento == 0) return;
                }
                case 3 -> nombre = JOptionPane.showInputDialog("Nuevo nombre:");
                case 4 -> correo = JOptionPane.showInputDialog("Nuevo correo:");
                case 5 -> tel = JOptionPane.showInputDialog("Nuevo teléfono:");
                case 6 -> dir = JOptionPane.showInputDialog("Nueva dirección:");
                case 7 -> ciudad = JOptionPane.showInputDialog("Nueva ciudad:");
                case 8 -> {
                    String optEnt = JOptionPane.showInputDialog(
                            "¿Pertenece a una entidad pública?\n  1. Sí\n  2. No");
                    if (optEnt == null) return;
                    int v = Integer.parseInt(optEnt.trim());
                    if (v != 1 && v != 2) {
                        JOptionPane.showMessageDialog(null, "Opción inválida (1 o 2).");
                        return;
                    }
                    esEntidadPublica = (v == 1);
                }
                case 9 -> area = JOptionPane.showInputDialog("Nueva área de desempeño:");
                case 10 -> {
                    JOptionPane.showMessageDialog(null, "Actualización cancelada.");
                    return;
                }
                default -> {
                    JOptionPane.showMessageDialog(null, "Opción no válida.");
                    return;
                }
            }

            if (opSub >= 1 && opSub <= 7) {
                controlador.actualizarUsuarioBase(doc, tipoPersona, tipoDocumento,
                        nombre, correo, contrasenha, tel, dir, ciudad);
            }
            if (opSub == 8 || opSub == 9) {
                controlador.actualizarContratista(doc, esEntidadPublica, area);
            }
            JOptionPane.showMessageDialog(null, "  Contratista actualizado correctamente.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Dato numérico inválido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Solicita el número de documento de un contratista y lo elimina del sistema previa confirmación.
     *
     * @param controlador Instancia del {@link Controlador}.
     */
    private static void eliminarContratista(Controlador controlador) {
        String doc = JOptionPane.showInputDialog("Número de documento del contratista a eliminar:");
        if (doc == null) return;
        if (!controlador.numeroDocumentoExiste(doc)) {
            JOptionPane.showMessageDialog(null, "No existe un contratista con ese documento.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(null,
                "¿Confirma eliminar al contratista con documento " + doc + "?",
                "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            controlador.eliminarContratista(doc);
            JOptionPane.showMessageDialog(null, "  Contratista eliminado exitosamente.");
        }
    }

    /**
     * Muestra el menú del contratante y gestiona las opciones seleccionadas.
     *
     * @param controlador    Instancia del {@link Controlador}.
     * @param docContratante Número de documento del contratante logueado.
     */
    private static void menuContratante(Controlador controlador, String docContratante) {
        int op = 0;
        while (op != 8) {
            try {
                String entrada = JOptionPane.showInputDialog(null, MENU_CONTRATANTE,
                        "Contratante", JOptionPane.PLAIN_MESSAGE);
                if (entrada == null) break;
                op = Integer.parseInt(entrada.trim());

                switch (op) {
                    case 1 -> crearContrato(controlador, docContratante);
                    case 2 -> {
                        String id = JOptionPane.showInputDialog("ID del contrato a consultar:");
                        if (id != null)
                            JOptionPane.showMessageDialog(null, controlador.consultarContrato(id),
                                    "Contrato", JOptionPane.PLAIN_MESSAGE);
                    }
                    case 3 -> actualizarContrato(controlador);
                    case 4 -> eliminarContrato(controlador);
                    case 5 -> adjudicarContrato(controlador);
                    case 6 -> iniciarEjecucion(controlador);
                    case 7 -> verReportesInterventoria(controlador);
                    case 8 -> JOptionPane.showMessageDialog(null, "Sesión cerrada.");
                    default ->
                            JOptionPane.showMessageDialog(null, "Opción no válida.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Permite al contratante crear un nuevo contrato, solicitando los datos necesarios
     * según el tipo de contrato seleccionado.
     *
     * @param controlador    Instancia del {@link Controlador}.
     * @param docContratante Número de documento del contratante que crea el contrato.
     */
    private static void crearContrato(Controlador controlador, String docContratante) {
        try {
            String menuTipo = """
                    Seleccione el tipo de contrato:
                      1. Prestación de Servicios
                      2. Compraventa
                      3. Obra Pública
                    """;
            String entTipo = JOptionPane.showInputDialog(menuTipo);
            if (entTipo == null) return;
            int tipo = Integer.parseInt(entTipo.trim());

            String id = JOptionPane.showInputDialog("ID único del contrato:");
            if (id == null) return;
            if (controlador.existeIdContrato(id)) {
                JOptionPane.showMessageDialog(null, "Ya existe un contrato con el ID '" + id + "'.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String objeto = JOptionPane.showInputDialog("Objeto / descripción del contrato:");
            if (objeto == null) return;

            String strPlazo = JOptionPane.showInputDialog("Fecha límite de ejecución (yyyy-MM-dd):");
            if (strPlazo == null) return;
            LocalDate plazo;
            try {
                plazo = LocalDate.parse(strPlazo.trim());
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(null, "Formato de fecha inválido. Use yyyy-MM-dd.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String resultado;

            switch (tipo) {
                case 1 -> { // Prestación de Servicios
                    String strValor = JOptionPane.showInputDialog("Valor total del contrato ($):");
                    if (strValor == null) return;
                    double valorPs = Double.parseDouble(strValor.trim());
                    String perfil = JOptionPane.showInputDialog("Perfil requerido:");
                    if (perfil == null) return;
                    String entregables = JOptionPane.showInputDialog("Entregables esperados:");
                    if (entregables == null) return;
                    String strHonorario = JOptionPane.showInputDialog("Honorario mensual ($):");
                    if (strHonorario == null) return;
                    double honorario = Double.parseDouble(strHonorario.trim());
                    resultado = controlador.crearContratoPrestacionServicios(
                            id, objeto, docContratante, valorPs, plazo, perfil, entregables, honorario);
                }
                case 2 -> { // Compraventa
                    String item = JOptionPane.showInputDialog("Ítem a adquirir:");
                    if (item == null) return;
                    String strCant = JOptionPane.showInputDialog("Cantidad de ítems:");
                    if (strCant == null) return;
                    int cantidad = Integer.parseInt(strCant.trim());
                    String strVu = JOptionPane.showInputDialog("Valor unitario ($):");
                    if (strVu == null) return;
                    double valorUnitario = Double.parseDouble(strVu.trim());
                    String marca = JOptionPane.showInputDialog("Marca:");
                    if (marca == null) return;
                    String modelo = JOptionPane.showInputDialog("Modelo:");
                    if (modelo == null) return;
                    String serie = JOptionPane.showInputDialog("Serie:");
                    if (serie == null) return;
                    resultado = controlador.crearContratoCompraVenta(
                            id, objeto, docContratante, plazo, item, marca, modelo, serie, valorUnitario, cantidad);
                    if ("OK".equals(resultado)) {
                        double total = valorUnitario * cantidad;
                        resultado = "OK — Valor total calculado: $" + String.format("%.2f", total);
                    }
                }
                case 3 -> { // Obra Pública
                    String strValorOp = JOptionPane.showInputDialog("Valor total del contrato ($):");
                    if (strValorOp == null) return;
                    double valorOp = Double.parseDouble(strValorOp.trim());
                    String ubicacion = JOptionPane.showInputDialog("Ubicación de la obra:");
                    if (ubicacion == null) return;
                    String strArea = JOptionPane.showInputDialog("Área de intervención (m²):");
                    if (strArea == null) return;
                    double area = Double.parseDouble(strArea.trim());
                    resultado = controlador.crearContratoObraPublica(
                            id, objeto, docContratante, valorOp, plazo, ubicacion, area);
                }
                default -> {
                    JOptionPane.showMessageDialog(null, "Tipo de contrato inválido.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            if ("OK".equals(resultado) || resultado.startsWith("OK")) {
                JOptionPane.showMessageDialog(null,
                        "  Contrato creado exitosamente.\n" + resultado.replace("OK", "").trim());
            } else {
                JOptionPane.showMessageDialog(null, resultado, "Error al crear contrato", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Dato numérico inválido. Revise valores y cantidades.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Permite al contratante actualizar los atributos generales de un contrato existente.
     *
     * @param controlador Instancia del {@link Controlador}.
     */
    private static void actualizarContrato(Controlador controlador) {
        try {
            String id = JOptionPane.showInputDialog("ID del contrato a actualizar:");
            if (id == null) return;
            if (!controlador.existeIdContrato(id)) {
                JOptionPane.showMessageDialog(null, "El contrato '" + id + "' no existe.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String submenu = """
                    ¿Qué campo desea modificar?
                      1. Objeto del contrato
                      2. Valor a celebrar
                      3. Plazo de ejecución
                      4. Cancelar
                    """;
            String entSub = JOptionPane.showInputDialog(submenu);
            if (entSub == null) return;
            int opSub = Integer.parseInt(entSub.trim());

            String nuevoObjeto = null;
            Double nuevoValor = null;
            LocalDate nuevoPlazo = null;

            switch (opSub) {
                case 1 -> nuevoObjeto = JOptionPane.showInputDialog("Nuevo objeto del contrato:");
                case 2 -> {
                    String sv = JOptionPane.showInputDialog("Nuevo valor a celebrar ($):");
                    if (sv != null) nuevoValor = Double.parseDouble(sv.trim());
                }
                case 3 -> {
                    String sp = JOptionPane.showInputDialog("Nuevo plazo (yyyy-MM-dd):");
                    if (sp != null) {
                        try {
                            nuevoPlazo = LocalDate.parse(sp.trim());
                        } catch (DateTimeParseException e) {
                            JOptionPane.showMessageDialog(null, "Formato de fecha inválido.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                }
                case 4 -> {
                    JOptionPane.showMessageDialog(null, "Actualización cancelada.");
                    return;
                }
                default -> {
                    JOptionPane.showMessageDialog(null, "Opción no válida.");
                    return;
                }
            }

            controlador.actualizarContratoGeneral(id, nuevoObjeto, nuevoValor, nuevoPlazo, null);
            JOptionPane.showMessageDialog(null, "  Contrato actualizado exitosamente.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Dato numérico inválido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Solicita el ID de un contrato y lo elimina del sistema previa confirmación.
     *
     * @param controlador Instancia del {@link Controlador}.
     */
    private static void eliminarContrato(Controlador controlador) {
        String id = JOptionPane.showInputDialog("ID del contrato a eliminar:");
        if (id == null) return;
        if (!controlador.existeIdContrato(id)) {
            JOptionPane.showMessageDialog(null, "El contrato '" + id + "' no existe.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(null,
                "¿Confirma eliminar el contrato con ID '" + id + "'?",
                "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            controlador.eliminarContrato(id);
            JOptionPane.showMessageDialog(null, "  Contrato eliminado exitosamente.");
        }
    }

    /**
     * Gestiona el proceso de adjudicación de un contrato a un contratista.
     * Cambia la fase del contrato de LICITACION a ADJUDICACION.
     *
     * @param controlador Instancia del {@link Controlador}.
     */
    private static void adjudicarContrato(Controlador controlador) {
        String idContrato = JOptionPane.showInputDialog("ID del contrato a adjudicar:");
        if (idContrato == null) return;
        if (!controlador.existeIdContrato(idContrato)) {
            JOptionPane.showMessageDialog(null, "El contrato '" + idContrato + "' no existe.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String postulados = controlador.listarPostulados(idContrato);
        JOptionPane.showMessageDialog(null, postulados, "Contratistas Postulados", JOptionPane.INFORMATION_MESSAGE);

        String docContratista = JOptionPane.showInputDialog(
                "Ingrese el número de documento del contratista a adjudicar:");
        if (docContratista == null) return;

        String resultado = controlador.adjudicarContrato(idContrato, docContratista);
        if (resultado.startsWith("OK")) {
            JOptionPane.showMessageDialog(null, "  " + resultado.substring(3).trim());
        } else {
            JOptionPane.showMessageDialog(null, resultado, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Gestiona el inicio de la ejecución de un contrato.
     * Cambia la fase del contrato de ADJUDICACION a EJECUCION.
     *
     * @param controlador Instancia del {@link Controlador}.
     */
    private static void iniciarEjecucion(Controlador controlador) {
        String idContrato = JOptionPane.showInputDialog("ID del contrato a iniciar ejecución:");
        if (idContrato == null) return;
        if (!controlador.existeIdContrato(idContrato)) {
            JOptionPane.showMessageDialog(null, "El contrato '" + idContrato + "' no existe.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String resultado = controlador.iniciarEjecucion(idContrato);
        if (resultado.startsWith("OK")) {
            JOptionPane.showMessageDialog(null, "  " + resultado.substring(3).trim());
        } else {
            JOptionPane.showMessageDialog(null, resultado, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Permite al contratante ver los reportes de interventoría, ya sean todos o los de un contrato específico.
     *
     * @param controlador Instancia del {@link Controlador}.
     */
    private static void verReportesInterventoria(Controlador controlador) {
        try {
            String opcion = JOptionPane.showInputDialog("""
                    Reportes de Interventoría:
                      1. Ver todos los reportes
                      2. Ver reportes de un contrato específico
                    """);
            if (opcion == null) return;
            int op = Integer.parseInt(opcion.trim());
            switch (op) {
                case 1 -> JOptionPane.showMessageDialog(null,
                        controlador.obtenerTodosLosReportes(),
                        "Todos los Reportes de Interventoría", JOptionPane.PLAIN_MESSAGE);
                case 2 -> {
                    String id = JOptionPane.showInputDialog("ID del contrato:");
                    if (id != null)
                        JOptionPane.showMessageDialog(null,
                                controlador.obtenerReportesPorContrato(id),
                                "Reportes del Contrato " + id, JOptionPane.PLAIN_MESSAGE);
                }
                default -> JOptionPane.showMessageDialog(null, "Opción no válida.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Muestra el menú del contratista y gestiona las opciones seleccionadas.
     *
     * @param controlador    Instancia del {@link Controlador}.
     * @param docContratista Número de documento del contratista logueado.
     */
    private static void menuContratista(Controlador controlador, String docContratista) {
        int op = 0;
        while (op != 3) {
            try {
                String entrada = JOptionPane.showInputDialog(null, MENU_CONTRATISTA,
                        "Contratista", JOptionPane.PLAIN_MESSAGE);
                if (entrada == null) break;
                op = Integer.parseInt(entrada.trim());

                switch (op) {
                    case 1 -> postularseContrato(controlador, docContratista);
                    case 2 -> registrarCambioFase(controlador);
                    case 3 -> JOptionPane.showMessageDialog(null, "Sesión cerrada.");
                    default ->
                            JOptionPane.showMessageDialog(null, "Opción no válida.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Permite a un contratista postularse a un contrato existente.
     *
     * @param controlador    Instancia del {@link Controlador}.
     * @param docContratista Número de documento del contratista que se postula.
     */
    private static void postularseContrato(Controlador controlador, String docContratista) {
        String contratos = controlador.obtenerTodosLosContratos();
        JOptionPane.showMessageDialog(null, contratos, "Contratos Disponibles", JOptionPane.PLAIN_MESSAGE);

        String idContrato = JOptionPane.showInputDialog(
                "Ingrese el ID del contrato al que desea postularse:");
        if (idContrato == null) return;
        if (!controlador.existeIdContrato(idContrato)) {
            JOptionPane.showMessageDialog(null, "El contrato '" + idContrato + "' no existe.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String resultado = controlador.postularContratista(idContrato, docContratista);
        if (resultado.startsWith("OK")) {
            JOptionPane.showMessageDialog(null, "  " + resultado.substring(3).trim());
        } else {
            JOptionPane.showMessageDialog(null, resultado, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Permite a un contratista registrar un cambio de fase para un contrato
     * y generar un reporte de interventoría.
     *
     * @param controlador Instancia del {@link Controlador}.
     */
    private static void registrarCambioFase(Controlador controlador) {
        try {
            String contratos = controlador.obtenerTodosLosContratos();
            JOptionPane.showMessageDialog(null, contratos, "Contratos Existentes", JOptionPane.PLAIN_MESSAGE);

            String idContrato = JOptionPane.showInputDialog("ID del contrato:");
            if (idContrato == null) return;
            if (!controlador.existeIdContrato(idContrato)) {
                JOptionPane.showMessageDialog(null, "El contrato '" + idContrato + "' no existe.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String menuFases = """
                    Seleccione la nueva fase del contrato:
                      1. PUBLICACION
                      2. LICITACION
                      3. ADJUDICACION
                      4. EJECUCION
                    """;
            String entFase = JOptionPane.showInputDialog(menuFases);
            if (entFase == null) return;
            int opFase = Integer.parseInt(entFase.trim());

            FaseContrato nuevaFase = switch (opFase) {
                case 1 -> FaseContrato.PUBLICACION;
                case 2 -> FaseContrato.LICITACION;
                case 3 -> FaseContrato.ADJUDICACION;
                case 4 -> FaseContrato.EJECUCION;
                default -> null;
            };

            if (nuevaFase == null) {
                JOptionPane.showMessageDialog(null, "Fase no válida.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String informe = JOptionPane.showInputDialog("Redacte el informe del cambio de fase:");
            if (informe == null) return;

            String resultado = controlador.cambiarEstadoContrato(idContrato, nuevaFase, informe);
            if (resultado.startsWith("OK")) {
                JOptionPane.showMessageDialog(null, "  Cambio de fase registrado y reporte de interventoría generado.");
            } else {
                JOptionPane.showMessageDialog(null, resultado, "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Dato numérico inválido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Solicita al usuario seleccionar el tipo de persona mediante código numérico.
     *
     * @return 1=NATURAL, 2=JURIDICA, 0=cancelado/inválido.
     */
    private static int pedirTipoPersona() {
        try {
            String entrada = JOptionPane.showInputDialog(OPCIONES_TIPO_PERSONA);
            if (entrada == null) return 0;
            int op = Integer.parseInt(entrada.trim());
            if (op == 1 || op == 2) return op;
            JOptionPane.showMessageDialog(null, "Opción inválida (1 o 2).", "Error", JOptionPane.ERROR_MESSAGE);
            return 0;
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Ingrese 1 o 2.", "Error", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }

    /**
     * Solicita al usuario seleccionar el tipo de documento mediante código numérico.
     *
     * @return 1-5 según el tipo, 0=cancelado/inválido.
     */
    private static int pedirTipoDocumento() {
        try {
            String entrada = JOptionPane.showInputDialog(OPCIONES_TIPO_DOCUMENTO);
            if (entrada == null) return 0;
            int op = Integer.parseInt(entrada.trim());
            if (op >= 1 && op <= 5) return op;
            JOptionPane.showMessageDialog(null, "Opción inválida (1-5).", "Error", JOptionPane.ERROR_MESSAGE);
            return 0;
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Ingrese un número entre 1 y 5.", "Error", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }
}
