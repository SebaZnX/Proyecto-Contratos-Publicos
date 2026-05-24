package edu.uptc.gui;

import edu.uptc.controlador.Controlador;
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
                                    JOptionPane.showInputDialog(menuAdministrador);
                                    int opAdmin = Integer.parseInt(JOptionPane.showInputDialog("""
                                            =======================================================
                                            1. Crear contratante
                                            2. Ver contratantes
                                            3. Actualizar contratante
                                            4. Eliminar contratante
                                            5. Salir  
                                            =======================================================
                                            """));
                                    switch (opAdmin) {
                                        case 1:
                                            break;
                                        case 2:
                                            break;
                                        case 3:
                                            String idModificarContratante = JOptionPane.showInputDialog
                                                    ("Ingrese el numero de documento del contratante a modificar");

                                            // SI ES NULO SALE DEL CASE
                                            if (idModificarContratante == null){
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
                                                        ¿Qué campo desea modificar?
                                                        1. Nombre
                                                        2. Correo
                                                        3. Teléfono
                                                        4. Dirección
                                                        5. Ciudad
                                                        6. Tipo de Persona
                                                        7. Tipo de documento
                                                        8. Sector (Exclusivo)
                                                        9. Nivel de Entidad (Exclusivo)
                                                        10. Código Único de Entidad (Exclusivo)
                                                        11. Cancelar
                                                        """;

                                                try {
                                                    int opSubMenu = Integer.parseInt(JOptionPane.showInputDialog(submenuModificar));



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
