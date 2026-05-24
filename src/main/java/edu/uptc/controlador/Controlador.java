package edu.uptc.controlador;

import edu.uptc.servicios.ServicioContratos;
import edu.uptc.servicios.ServicioReportes;
import edu.uptc.servicios.ServicioUsuarios;

public class Controlador {

    private ServicioUsuarios servicioUsuarios;
    private ServicioContratos servicioContratos;
    private ServicioReportes servicioReportes;

    public Controlador() {
        this.servicioUsuarios = new ServicioUsuarios();
        this.servicioContratos = new ServicioContratos();
        this.servicioReportes = new ServicioReportes();
    }

    // CONTROLADOR LOGIN
    public boolean loginCorrecto(String numeroDocumento, String contrasenha) {
        return this.servicioUsuarios.loginCorrecto(numeroDocumento, contrasenha);
    }

    // USUARIOS CONTROLADOR

    public void crearAdministrador() {
        this.servicioUsuarios.crearAdministrador();
    }


}
