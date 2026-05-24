package edu.uptc.gui;

import edu.uptc.controlador.Controlador;
import edu.uptc.enums.Rol;
import edu.uptc.enums.TipoDocumento;
import edu.uptc.enums.TipoPersona;

import javax.swing.*;


public class Application {

    public static void main(String[] args) {

        Controlador controlador = new Controlador();

        // SE LLAMA AL METODO DE CREAR ADMINISTRADOR
        controlador.crearAdministrador();


        // MENUS DE LOS USUARIOS
        String menuPrincipal = """
                =======================================================
                
                ------------------------------ BIENVENIDO AL SECOP III ------------------------------
                
                1. Iniciar sesión
                2. Ver contratos publicos
                3. Salir
                
                =======================================================
                """;

        /*El administrador tendrá permisos exclusivos para realizar el CRUD únicamente en los módulos de Contratante
        y Contratista. Por motivos de seguridad y transparencia institucional, no tendrá permisos de modificación,
        actualización ni eliminación sobre los Contratos y Reportes, garantizando así la inmutabilidad de la
        información y previniendo posibles actos de corrupción.*/
        String menuAdministrador = """
                =======================================================
                -------------- MENÚ ADMINISTRADOR --------------
                1. Opciones para contratante
                2. Opciones para contratista
                3. Mostrar información del usuario
                4. Salir
                =======================================================
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

//        ===================================================================================================
        try {
            while (opcionMenuPrincipal != 3) {


                try {
                    // VERIFICAR SI ES NULO PARA PODER DALE X Y SALIR DEL PROGRAMA
                    String entrada = JOptionPane.showInputDialog(menuPrincipal);

                    if (entrada == null) {
                        break;
                    }

                    opcionMenuPrincipal = Integer.parseInt(entrada);
                    switch (opcionMenuPrincipal) {
                        case 1:
                            // MENSAJES DEL INICIO DE SESION
                            JOptionPane.showMessageDialog(null, "----- INICIAR SESION -----\nDigite los siguientes campos");
                            String numeroDocumentoLoguear = JOptionPane.showInputDialog("Ingrese su numero de documento");
                            String contrasenhaLoguear = JOptionPane.showInputDialog("Ingrese su contraseña");
                            // INVOCACION AL METODO PARA LOGUEAR
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
                                                        int opAdminMenuContratante = Integer.parseInt(JOptionPane.showInputDialog("""
                                                                =======================================================
                                                                1. Crear contratante
                                                                2. Ver contratantes
                                                                3. Actualizar contratante
                                                                4. Eliminar contratante
                                                                5. Salir  
                                                                =======================================================
                                                                """));
                                                        switch (opAdminMenuContratante) {
                                                            case 1:
                                                                break;
                                                            case 2:
                                                                break;
                                                            case 3:
                                                                String idModificarContratante = JOptionPane.showInputDialog
                                                                        ("Ingrese el numero de documento del contratante a modificar");

                                                                // SI ES NULO SALE DEL CASE
                                                                if (idModificarContratante == null) {
                                                                    break;
                                                                }

                                                                if (controlador.rolLogueado(idModificarContratante) == Rol.ADMINISTRADOR) {
                                                                    JOptionPane.showMessageDialog(null, "El administrador no esta disponible para actualizar\nContactese con el provedor");
                                                                    break;
                                                                }
                                                                if (controlador.numeroDocumentoExiste(idModificarContratante)) {


                                                                    // TODAS LAS VARIABLES EMPIEZAN EN NULL POR DEFECTO
                                                                    // ESTO ES PARA DESPUES LLAMAR AL METODO Y QUE SOLO SE CAMBIE
                                                                    // LO QUE INDICA EL ADMINISTRADOR
                                                                    TipoPersona tipoPersona = null;
                                                                    TipoDocumento tipoDocumento = null;
                                                                    String nombre = null;
                                                                    String correo = null;
                                                                    String contrasenha = null;
                                                                    String telefono = null;
                                                                    String direccion = null;
                                                                    String ciudad = null;
                                                                    // ATRIBUTOS EXCLUSIVOS DE CONTRATANTE
                                                                    String sector = null;
                                                                    String nivelEntidad = null;
                                                                    String codigoUnicoEntidad = null;

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
                                                                        // VALIDACION PARA SEGUIR LA EJECUCION, SI ES NULL SE DEVUELVE AL MENU ANTERIOR
                                                                        if (entradaSubmenu == null) {
                                                                            break;
                                                                        }

                                                                        int opSubMenu = Integer.parseInt(entradaSubmenu);

                                                                        switch (opSubMenu) {
                                                                            case 1:
                                                                                try {
                                                                                    String opcionesPersona = """
                                                                                            Seleccione el Tipo de Persona:
                                                                                            1. NATURAL
                                                                                            2. JURIDICA
                                                                                            """;
                                                                                    String entradaPersona = JOptionPane.showInputDialog(opcionesPersona);
                                                                                    // VALIDAMOS QUE NO SEA NULL LA ENTRADA
                                                                                    if (entradaPersona != null) {
                                                                                        // ASIGNAMOS LA CADENA A LA OPCION PARSEANDO A UN ENTERO
                                                                                        int opPersona = Integer.parseInt(entradaPersona);

                                                                                        // ASIGNACION CON LA CLASE ENUM TIPOPERSONA
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
                                                                                    String opcionesDocumento = """
                                                                                            Seleccione el Tipo de Documento:
                                                                                            1. CC (Cédula de Ciudadanía)
                                                                                            2. CE (Cédula de Extranjería)
                                                                                            3. PAS (Pasaporte)
                                                                                            4. PPT (Permiso por Protección Temporal)
                                                                                            5. NIT (Número de Identificación Tributaria)
                                                                                            """;
                                                                                    String entradaDoc = JOptionPane.showInputDialog(opcionesDocumento);
                                                                                    // VALIDAMOS QUE NO SEA NULL LA ENTRADA
                                                                                    if (entradaDoc != null) {
                                                                                        // ASIGNAMOS LA CADENA A LA OPCION PARSEANDO A UN ENTERO
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

                                                                        // SE LLAMAN LOS METODOS PARA ACTUALIZAR


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
                                                            default:
                                                                break;
                                                        }
                                                        break;
                                                    case 2:
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
