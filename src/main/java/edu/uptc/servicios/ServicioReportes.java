package edu.uptc.servicios;

import edu.uptc.dominio.ReporteInterventoria;

import java.util.HashMap;

public class ServicioReportes {
    private HashMap<String, ReporteInterventoria> reportesInterventoria;

    /**
     * Inicializa el constructor y las listas de reportes del sistema.
     */
    public ServicioReportes() {
        this.reportesInterventoria = new HashMap<>();
    }
}
