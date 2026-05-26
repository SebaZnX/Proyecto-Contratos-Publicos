package edu.uptc.gui;

import edu.uptc.controlador.Controlador;
import edu.uptc.enums.Rol;
import edu.uptc.enums.TipoDocumento;
import edu.uptc.enums.TipoPersona;

import javax.swing.*;

/**
 * Clase principal que inicia la aplicación y gestiona la interfaz de usuario para el sistema de Contratos Públicos.
 * Proporciona los menús de interacción y maneja el flujo principal de la aplicación.
 */
public class Application {

    /**
     * Método principal que sirve como punto de entrada para la aplicación.
     *
     * @param args Argumentos de la línea de comandos (no utilizados en esta aplicación).
     */
    public static void main(String[] args) {
        //Ya que las Enum las usamos tanto para crear contratante y contratista y actualizarlos
        //Se asignaron afuera para evitar declarar variables multiples veces
        //Los menus se dejaron afuera y solo se llaman cuando sean necesarios
        TipoPersona tipoPersona = null;
        TipoDocumento tipoDocumento = null;
        Controlador controlador = new Controlador();

        /**
         * Invoca el método para crear el administrador inicial del sistema.
         * Este paso es crucial para la configuración inicial de la aplicación.
         */
        controlador.crearAdministrador();


        /**
         * Define y muestra el menú principal de la aplicación, permitiendo al usuario
         * seleccionar entre iniciar sesión, ver contratos públicos o salir.
         */
        String menuPrincipal = """
                =======================================================
                
                ------------------------------ BIENVENIDO AL SECOP III ------------------------------
                
                1. Iniciar sesión
                2. Ver contratos publicos
                3. Salir
                
                =======================================================
                """;

        /**
         * Define el menú de opciones disponible para el rol de Administrador.
         * Este menú permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
         * exclusivamente sobre los módulos de Contratante y Contratista.
         * <p>
         * NOTA DE SEGURIDAD: Por razones de transparencia institucional, las operaciones
         * de modificación, actualización o eliminación sobre los módulos de Contratos y Reportes
         * están restringidas. Esto asegura la inmutabilidad de la información y previene
         * posibles riesgos de corrupción.
         */
        String menuAdministrador = """
                =======================================================
                -------------- MENÚ ADMINISTRADOR --------------
                1. Opciones para contratante
                2. Opciones para contratista
                3. Mostrar información del usuario
                4. Salir
                =======================================================
                """;
        String opcionesPersona = """
                Seleccione el Tipo de Persona:
                1. NATURAL
                2. JURIDICA
                """;
        String opcionesDocumento = """
                Seleccione el Tipo de Documento:
                1. CC (Cédula de Ciudadanía)
                2. CE (Cédula de Extranjería)
                3. PAS (Pasaporte)
                4. PPT (Permiso por Protección Temporal)
                5. NIT (Número de Identificación Tributaria)
                """;
        String menuContratante = """
                =======================================================
                =======================================================
                """;
        String menuContratista = """
                =======================================================
                =======================================================
                """;

        int opcionMenuPrincipal = 0;

        try {
            while (opcionMenuPrincipal != 3) {


                try {
                    /**
                     * Verifica si la entrada del usuario es nula (por ejemplo, si el usuario cierra el diálogo).
                     * Si es nula, interrumpe el flujo actual y permite la salida del programa.
                     */
                    String entrada = JOptionPane.showInputDialog(menuPrincipal);

                    if (entrada == null) {
                        break;
                    }

                    opcionMenuPrincipal = Integer.parseInt(entrada);
                    switch (opcionMenuPrincipal) {
                        case 1:
                            /**
                             * Gestiona el proceso de inicio de sesión, solicitando al usuario sus credenciales
                             * y mostrando mensajes informativos.
                             */
                            JOptionPane.showMessageDialog(null, "----- INICIAR SESION -----\nDigite los siguientes campos");
                            String numeroDocumentoLoguear = JOptionPane.showInputDialog("Ingrese su numero de documento");
                            String contrasenhaLoguear = JOptionPane.showInputDialog("Ingrese su contraseña");
                            /**
                             * Invoca el método del controlador para autenticar y validar las credenciales
                             * proporcionadas por el usuario durante el inicio de sesión.
                             */
                            boolean logueado = controlador.loginCorrecto(numeroDocumentoLoguear, contrasenhaLoguear);
                            if (logueado) {
                                try {
                                    Rol rolLogueado = controlador.rolLogueado(numeroDocumentoLoguear);
                                    switch (rolLogueado) {
                                        case ADMINISTRADOR:

                                            int opcionMenuAdministrador = 0;

                                            while (opcionMenuAdministrador != 4) {
                                                String entradaMenuAdminstrador = JOptionPane.showInputDialog(menuAdministrador);

                                                if (entradaMenuAdminstrador == null) {
                                                    break;
                                                }

                                                opcionMenuAdministrador = Integer.parseInt(entradaMenuAdminstrador);
                                                switch (opcionMenuAdministrador) {
                                                    case 1:
                                                        // METODO PARA VER TODOS LOS CONTRATANTES FALTA!!!!!
                                                        int opAdminMenuContratante = Integer.parseInt(JOptionPane.showInputDialog("""
                                                                =======================================================
                                                                1. Crear contratante
                                                                2. Consultar contratante
                                                                3. Actualizar contratante
                                                                4. Eliminar contratante
                                                                5. Mostrar todos los contratantes
                                                                6. Salir
                                                                =======================================================
                                                                """));
                                                        switch (opAdminMenuContratante) {
                                                            case 1:
                                                                String opcionPersona = JOptionPane.showInputDialog(opcionesPersona);
                                                                if (opcionPersona != null) {
                                                                    int opcPersona = Integer.parseInt(opcionPersona);
                                                                    if (opcPersona == 1) {
                                                                        tipoPersona = TipoPersona.NATURAL;
                                                                    } else if (opcPersona == 2) {
                                                                        tipoPersona = TipoPersona.JURIDICA;
                                                                    } else {
                                                                        JOptionPane.showMessageDialog(null, "Opcion no valida");
                                                                    }
                                                                }
                                                                String opcionDocumento = JOptionPane.showInputDialog(opcionesDocumento);
                                                                if (opcionDocumento != null) {
                                                                    int opcDocumento = Integer.parseInt(opcionDocumento);
                                                                    if (opcDocumento == 1) {
                                                                        tipoDocumento = TipoDocumento.CC;
                                                                    } else if (opcDocumento == 2) {
                                                                        tipoDocumento = TipoDocumento.CE;
                                                                    } else if (opcDocumento == 3) {
                                                                        tipoDocumento = TipoDocumento.PAS;
                                                                    } else if (opcDocumento == 4) {
                                                                        tipoDocumento = TipoDocumento.PPT;
                                                                    } else if (opcDocumento == 5) {
                                                                        tipoDocumento = TipoDocumento.NIT;
                                                                    } else {
                                                                        JOptionPane.showMessageDialog(null, "Opcion no valida");
                                                                    }
                                                                }
                                                                String numeroDocumento = JOptionPane.showInputDialog("Ingrese el numero de documento del contratante");
                                                                String nombre = JOptionPane.showInputDialog("Ingrese el nombre del contratante");
                                                                String correo = JOptionPane.showInputDialog("Ingrese el correo del contratante");
                                                                String contrasenha = JOptionPane.showInputDialog("Ingrese la contraseña del contratante");
                                                                String telefono = JOptionPane.showInputDialog("Ingrese el telefono del contratante");
                                                                String direccion = JOptionPane.showInputDialog("Ingrese la direccion del contratante");
                                                                String ciudad = JOptionPane.showInputDialog("Ingrese la ciudad del contratante");
                                                                Rol rol = Rol.CONTRATANTE;
                                                                String sector = JOptionPane.showInputDialog("Ingrese el sector del contratante");
                                                                String nivelEntidad = JOptionPane.showInputDialog("Ingrese el nivel de entidad del contratante");
                                                                String codigoUnicoEntidad = JOptionPane.showInputDialog("Ingrese el codigo unico de entidad del contratante");
                                                                if (controlador.numeroDocumentoExiste(numeroDocumento)) {
                                                                    JOptionPane.showMessageDialog(null, "El numero de documento ya existe");
                                                                } else {
                                                                    controlador.crearContratante(tipoPersona, tipoDocumento, numeroDocumento, nombre, correo, contrasenha, telefono,
                                                                            direccion, ciudad, rol, sector, nivelEntidad, codigoUnicoEntidad);
                                                                }
                                                                break;
                                                            case 2:
                                                                break;
                                                            case 3:
                                                                String idModificarContratante = JOptionPane.showInputDialog
                                                                        ("Ingrese el numero de documento del contratante a modificar");

                                                                /**
                                                                 * Interrumpe el flujo del bloque switch si el valor de entrada es nulo,
                                                                 * indicando que el usuario ha cancelado la operación.
                                                                 */
                                                                if (idModificarContratante == null) {
                                                                    break;
                                                                }

                                                                if (controlador.rolLogueado(idModificarContratante) == Rol.ADMINISTRADOR) {
                                                                    JOptionPane.showMessageDialog(null, "El administrador no esta disponible para actualizar\nContactese con el provedor");
                                                                    break;
                                                                }
                                                                if (controlador.numeroDocumentoExiste(idModificarContratante)) {


                                                                    /**
                                                                     * Inicializa las variables de los atributos del contratante a `null` por defecto.
                                                                     * Esto permite que, al llamar al método de actualización, solo se modifiquen
                                                                     * los campos para los cuales el administrador ha proporcionado un nuevo valor.
                                                                     */
                                                                    tipoPersona = null;
                                                                    tipoDocumento = null;
                                                                    nombre = null;
                                                                    correo = null;
                                                                    contrasenha = null;
                                                                    telefono = null;
                                                                    direccion = null;
                                                                    ciudad = null;
                                                                    /**
                                                                     * Atributos específicos y exclusivos del rol de Contratante.
                                                                     */
                                                                    sector = null;
                                                                    nivelEntidad = null;
                                                                    codigoUnicoEntidad = null;

                                                                    String submenuModificar = """
                                                                            ¿Qué campo desea modificar del Contratante?
                                                                            1. Tipo de Persona 
                                                                            2. Tipo de Documento 
                                                                            3. Nombre
                                                                            4. Correo
                                                                            5. Teléfono
                                                                            6. Dirección
                                                                            7. Ciudad
                                                                            8. Sector (Exclusivo)
                                                                            9. Nivel de Entidad (Exclusivo)
                                                                            10. Código Único de Entidad (Exclusivo)
                                                                            11. Cancelar
                                                                            """;


                                                                    try {

                                                                        String entradaSubmenu = JOptionPane.showInputDialog(submenuModificar);
                                                                        /**
                                                                         * Evalúa si la entrada del submenú es nula. Si lo es,
                                                                         * indica que el usuario ha cancelado la operación y se regresa al menú anterior.
                                                                         */
                                                                        if (entradaSubmenu == null) {
                                                                            break;
                                                                        }

                                                                        int opSubMenu = Integer.parseInt(entradaSubmenu);

                                                                        switch (opSubMenu) {
                                                                            case 1:
                                                                                try {

                                                                                    String entradaPersona = JOptionPane.showInputDialog(opcionesPersona);
                                                                                    /**
                                                                                     * Valida que la entrada de datos para el tipo de persona no sea nula antes de procesarla.
                                                                                     */
                                                                                    if (entradaPersona != null) {
                                                                                        /**
                                                                                         * Convierte la entrada de texto del usuario a un valor entero para determinar la opción seleccionada.
                                                                                         */
                                                                                        int opPersona = Integer.parseInt(entradaPersona);

                                                                                        /**
                                                                                         * Asigna el tipo de persona correspondiente utilizando la enumeración {@link TipoPersona}.
                                                                                         */
                                                                                        if (opPersona == 1) {
                                                                                            tipoPersona = TipoPersona.NATURAL;
                                                                                        } else if (opPersona == 2) {
                                                                                            tipoPersona = TipoPersona.JURIDICA;
                                                                                        } else {
                                                                                            JOptionPane.showMessageDialog(null, "Opcion no valida");
                                                                                        }
                                                                                    }
                                                                                } catch (NumberFormatException e) {
                                                                                    JOptionPane.showMessageDialog(null, "Debe ingresar un dato valido",
                                                                                            "ERROR", JOptionPane.ERROR_MESSAGE);
                                                                                }
                                                                                break;
                                                                            case 2:
                                                                                try {

                                                                                    String entradaDoc = JOptionPane.showInputDialog(opcionesDocumento);
                                                                                    /**
                                                                                     * Valida que la entrada de datos para el tipo de documento no sea nula antes de procesarla.
                                                                                     */
                                                                                    if (entradaDoc != null) {
                                                                                        /**
                                                                                         * Convierte la entrada de texto del usuario a un valor entero para determinar la opción seleccionada.
                                                                                         */
                                                                                        int opDocumento = Integer.parseInt(entradaDoc);
                                                                                        switch (opDocumento) {
                                                                                            case 1:
                                                                                                tipoDocumento = TipoDocumento.CC;
                                                                                                break;
                                                                                            case 2:
                                                                                                tipoDocumento = TipoDocumento.CE;
                                                                                                break;
                                                                                            case 3:
                                                                                                tipoDocumento = TipoDocumento.PAS;
                                                                                                break;
                                                                                            case 4:
                                                                                                tipoDocumento = TipoDocumento.PPT;
                                                                                                break;
                                                                                            case 5:
                                                                                                tipoDocumento = TipoDocumento.NIT;
                                                                                                break;
                                                                                            default:
                                                                                                JOptionPane.showMessageDialog(null, "Opcion no valida");
                                                                                                break;
                                                                                        }

                                                                                    }
                                                                                } catch (NumberFormatException ex) {
                                                                                    JOptionPane.showMessageDialog(null, "Debe ingresar un dato valido",
                                                                                            "ERROR", JOptionPane.ERROR_MESSAGE);
                                                                                }
                                                                                break;
                                                                            case 3:
                                                                                nombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre:");
                                                                                break;
                                                                            case 4:
                                                                                correo = JOptionPane.showInputDialog("Ingrese el nuevo correo electrónico:");
                                                                                break;
                                                                            case 5:
                                                                                telefono = JOptionPane.showInputDialog("Ingrese el nuevo número de teléfono:");
                                                                                break;
                                                                            case 6:
                                                                                direccion = JOptionPane.showInputDialog("Ingrese la nueva dirección de residencia/oficina:");
                                                                                break;
                                                                            case 7:
                                                                                ciudad = JOptionPane.showInputDialog("Ingrese la nueva ciudad:");
                                                                                break;
                                                                            case 8:
                                                                                sector = JOptionPane.showInputDialog("Ingrese el nuevo sector:");
                                                                                break;
                                                                            case 9:
                                                                                nivelEntidad = JOptionPane.showInputDialog("Ingrese el nuevo nivel de entidad:");
                                                                                break;
                                                                            case 10:
                                                                                codigoUnicoEntidad = JOptionPane.showInputDialog("Ingrese el nuevo Código Único de Entidad:");
                                                                                break;
                                                                            case 11:
                                                                                JOptionPane.showMessageDialog(null, "Contratante no actualizado");
                                                                                break;
                                                                            default:
                                                                                break;
                                                                        }

                                                                        /**
                                                                         * Invoca los métodos del controlador para realizar la actualización de los datos
                                                                         * del usuario y del contratante con la información proporcionada.
                                                                         */
                                                                        if (opSubMenu > 0 && opSubMenu < 11) {
                                                                            controlador.actualizarUsuario(tipoPersona, tipoDocumento, numeroDocumentoLoguear, nombre, correo,
                                                                                    contrasenha, telefono, direccion, ciudad);
                                                                            controlador.actualizarContratante(numeroDocumentoLoguear, sector, nivelEntidad,
                                                                                    codigoUnicoEntidad);
                                                                            JOptionPane.showMessageDialog(null, "Contratante actualizado");
                                                                        }
                                                                    } catch (NumberFormatException ex) {
                                                                        JOptionPane.showMessageDialog(null, "ERROR", "ERROR", JOptionPane.ERROR_MESSAGE);
                                                                    }


                                                                } else {
                                                                    JOptionPane.showMessageDialog(null, "Contratante no encontrado");
                                                                }

                                                                break;
                                                            case 4:
                                                                break;
                                                            case 5:
                                                                break;
                                                            case 6:
                                                                break;
                                                            default:
                                                                JOptionPane.showMessageDialog(null, "Opcion no valida");
                                                                break;
                                                        }
                                                        break;
                                                    case 2:
                                                        //Menu para opciones de contratista
                                                        int opAdminMenuContratista = Integer.parseInt(JOptionPane.showInputDialog("""
                                                                =======================================================
                                                                1. Crear contratista
                                                                2. Consultar contratistas
                                                                3. Actualizar contratista
                                                                4. Mostrar todos los contratistas
                                                                5. Eliminar contratista
                                                                6. Salir
                                                                =======================================================
                                                                """));
                                                        switch (opAdminMenuContratista){
                                                            case 1:
                                                                //Crear al contratista
                                                                JOptionPane.showMessageDialog(null,"A continuacion va a ingresar los datos del contratista");
                                                                String opcionPersona = JOptionPane.showInputDialog(opcionesPersona);
                                                                if (opcionPersona != null){
                                                                    int opcPersona = Integer.parseInt(opcionPersona);
                                                                    if (opcPersona == 1){
                                                                        tipoPersona = TipoPersona.NATURAL;
                                                                    }else if (opcPersona == 2){
                                                                        tipoPersona = TipoPersona.JURIDICA;
                                                                    }else {
                                                                        JOptionPane.showMessageDialog(null, "Opcion no valida");
                                                                    }
                                                                }
                                                                String opcionDocumento = JOptionPane.showInputDialog(opcionesDocumento);
                                                                if (opcionDocumento != null){
                                                                    int opcDocumento = Integer.parseInt(opcionDocumento);
                                                                    if (opcDocumento == 1){
                                                                        tipoDocumento = TipoDocumento.CC;
                                                                    }else if (opcDocumento == 2){
                                                                        tipoDocumento = TipoDocumento.CE;
                                                                    } else if (opcDocumento == 3){
                                                                        tipoDocumento = TipoDocumento.PAS;
                                                                    } else if (opcDocumento == 4){
                                                                        tipoDocumento = TipoDocumento.PPT;
                                                                    } else if (opcDocumento == 5){
                                                                        tipoDocumento = TipoDocumento.NIT;
                                                                    }else{
                                                                        JOptionPane.showMessageDialog(null, "Opcion no valida");
                                                                    }
                                                                }
                                                                String numeroDocumento = JOptionPane.showInputDialog("Ingrese el numero de documento");
                                                                String nombre = JOptionPane.showInputDialog("Ingrese el nombre");
                                                                String correo = JOptionPane.showInputDialog("Ingrese el correo del contratista");
                                                                String contrasenha = JOptionPane.showInputDialog("Ingrese la contraseña");
                                                                String telefono = JOptionPane.showInputDialog("Ingrese el telefono");
                                                                String direccion = JOptionPane.showInputDialog("Ingrese la direccion");
                                                                String ciudad = JOptionPane.showInputDialog("Ingrese la ciudad");
                                                                Rol rol = Rol.CONTRATISTA;
                                                                String ansEntPublica = JOptionPane.showInputDialog("¿Pertenece a alguna entidad Publica? [si/no]");
                                                                Boolean entidadPublica = ansEntPublica != null && ansEntPublica.trim().equalsIgnoreCase("si");
                                                                String areaDesempenho = JOptionPane.showInputDialog("Ingrese el area de desempeño");

                                                                controlador.crearContratista(tipoPersona,tipoDocumento,numeroDocumento,nombre,correo,
                                                                        contrasenha,telefono,direccion,ciudad,rol,entidadPublica,areaDesempenho);
                                                                break;
                                                        }


                                                        break;
                                                    case 3:
                                                        break;
                                                    case 4:
                                                        JOptionPane.showMessageDialog(null, "Salio de su sesion");
                                                        break;
                                                    default:
                                                        JOptionPane.showMessageDialog(null, "Opcion no valida");
                                                        break;
                                                }

                                            }


                                            break;
                                        case CONTRATANTE:
                                            break;
                                        case CONTRATISTA:
                                            break;
                                        default:
                                            break;
                                    }
                                } catch (Exception exception) {
                                    JOptionPane.showMessageDialog(null, "Error");
                                }

                            } else {
                                JOptionPane.showMessageDialog(null, "INCORRECTO");
                            }

                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        default:
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "CARACTER NO ACEPTADO", "ERROR!!", JOptionPane.ERROR_MESSAGE);
                }
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "", "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
            JOptionPane.showMessageDialog(null, "Gracias por utilizar el programa");
        }


    }
}
