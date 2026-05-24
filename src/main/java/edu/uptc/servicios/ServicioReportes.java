package edu.uptc.servicios;

import edu.uptc.dominio.ReporteInterventoria;

import java.util.HashMap;

public class ServicioReportes {
    private HashMap<String, ReporteInterventoria> reportesInterventoria;

    // METODO CONSTRUCTOR INICIANDO LAS LISTAS DE LOS REPORTES
    public ServicioReportes() {
        this.reportesInterventoria = new HashMap<>();
    }
}
