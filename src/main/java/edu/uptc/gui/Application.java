package edu.uptc.gui;

import edu.uptc.controlador.Controlador;
import edu.uptc.servicios.ServicioContratos;
import edu.uptc.servicios.ServicioReportes;
import edu.uptc.servicios.ServicioUsuarios;

import javax.swing.*;

public class Application {

    public static void main(String[] args) {

        Controlador controlador = new Controlador();

        // MENUS DE LOS USUARIOS
        String menuPrincipal = """
                =======================================================
                
                ------------------------------ BIENVENIDO AL SECOP III ------------------------------
                
                1. Iniciar sesión
                2. Ver contratos publicos
                3. Salir
                
                =======================================================
                """;
        String menuAdministrador = """
                =======================================================
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



    }
}
