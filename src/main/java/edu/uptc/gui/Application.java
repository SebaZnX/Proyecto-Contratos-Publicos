package edu.uptc.gui;

import edu.uptc.controlador.Controlador;

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
                3. Mostrar informacion del usuario
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

                    if (entrada == null){
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
                                JOptionPane.showInputDialog(menuAdministrador);
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
                    JOptionPane.showMessageDialog(null, "CARACTER NO ACEPTADO", "ERROR!!" ,JOptionPane.ERROR_MESSAGE);
                }
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "", "ERROR" , JOptionPane.ERROR_MESSAGE);
        } finally {
            JOptionPane.showMessageDialog(null, "Gracias por utilizar el programa");
        }


    }
}
