package edu.uptc.servicios;

import edu.uptc.dominio.ReporteInterventoria;

import java.util.HashMap;

/**
 * Servicio que gestiona las operaciones relacionadas con los reportes de interventoría en el sistema.
 * Permite guardar, mostrar y consultar reportes.
 */
public class ServicioReportes {
    /**
     * Mapa que almacena los reportes de interventoría, utilizando el ID del reporte como clave.
     */
    private HashMap<String, ReporteInterventoria> reportesInterventoria;

    /**
     * Constructor de la clase ServicioReportes.
     * Inicializa el mapa que almacenará los reportes de interventoría del sistema.
     */
    public ServicioReportes() {
        this.reportesInterventoria = new HashMap<>();
    }

    /**
     * Guarda un nuevo reporte de interventoría en el sistema.
     * El reporte se almacena utilizando su ID único.
     *
     * @param nuevoReporte El objeto ReporteInterventoria a guardar.
     */
    public void guardarReporte(ReporteInterventoria nuevoReporte) {
        if (nuevoReporte != null) {
            this.reportesInterventoria.put(nuevoReporte.getIdReporte(), nuevoReporte);
        }
    }

    /**
     * Genera una representación en cadena de texto de un reporte de interventoría específico.
     *
     * @param reporte El objeto ReporteInterventoria a mostrar.
     * @return Una cadena de texto con los detalles del reporte, o un mensaje de error si el reporte es nulo.
     */
    public String mostrarReporte(ReporteInterventoria reporte) {
        if (reporte == null) {
            return "Reporte no válido.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("================ REPORTE DE INTERVENTORÍA ================\n");
        sb.append("• ID Reporte: ").append(reporte.getIdReporte()).append("\n");
        sb.append("• Contrato Asociado (ID): ").append(reporte.getContrato().getIdContrato()).append("\n");
        sb.append("• Fecha y Hora: ").append(reporte.getFechaHora()).append("\n");
        sb.append("• Fase Anterior: ").append(reporte.getFaseAnterior()).append("\n");
        sb.append("• Fase Nueva: ").append(reporte.getFaseNueva()).append("\n");
        sb.append("• Informe/Detalle: ").append(reporte.getInforme()).append("\n");
        sb.append("==========================================================\n");
        return sb.toString();
    }

    /**
     * Obtiene una cadena de texto con la información detallada de todos los reportes de interventoría registrados en el sistema.
     *
     * @return Una cadena de texto que contiene todos los reportes, o un mensaje indicando que no hay reportes.
     */
    public String obtenerTodosLosReportes() {
        if (this.reportesInterventoria.isEmpty()) {
            return "No se han registrado reportes de interventoría en el sistema.";
        }

        StringBuilder sb = new StringBuilder();
        for (ReporteInterventoria reporte : this.reportesInterventoria.values()) {
            sb.append(this.mostrarReporte(reporte)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Obtiene una cadena de texto con la información detallada de los reportes de interventoría asociados a un contrato específico.
     *
     * @param idContrato El identificador del contrato cuyos reportes se desean obtener.
     * @return Una cadena de texto con los reportes del contrato especificado, o un mensaje si no se encuentran reportes para ese contrato.
     */
    public String obtenerReportesPorContrato(String idContrato) {
        StringBuilder sb = new StringBuilder();
        boolean encontrado = false;

        for (ReporteInterventoria reporte : this.reportesInterventoria.values()) {
            if (reporte.getContrato().getIdContrato().equalsIgnoreCase(idContrato)) {
                sb.append(this.mostrarReporte(reporte)).append("\n");
                encontrado = true;
            }
        }

        if (!encontrado) {
            return "El contrato con ID '" + idContrato + "' no registra reportes de interventoría.";
        }

        return sb.toString();
    }
}