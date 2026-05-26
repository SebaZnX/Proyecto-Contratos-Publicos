package edu.uptc.dominio;

import edu.uptc.enums.FaseContrato;

import java.time.LocalDateTime;

/**
 * Representa un reporte de interventoría para un contrato específico.
 * Esta clase almacena información detallada sobre los cambios de fase de un contrato,
 * incluyendo el informe asociado, la fecha y hora del cambio, y las fases anterior y nueva.
 */
public class ReporteInterventoria {
    /**
     * Contador estático para generar identificadores únicos para los reportes de interventoría.
     * Se incrementa automáticamente con cada nueva instancia de ReporteInterventoria.
     */
    private static int contadorId = 0;
    /**
     * Identificador único del reporte de interventoría.
     */
    private String idReporte;
    /**
     * El contrato al que está asociado este reporte de interventoría.
     */
    private Contrato contrato;
    /**
     * Informe detallado que describe el motivo o los eventos relacionados con el cambio de fase del contrato.
     */
    private String informe;
    /**
     * Fecha y hora exacta en que se generó el reporte de interventoría.
     */
    private LocalDateTime fechaHora;
    /**
     * La fase del contrato antes de que se realizara el cambio registrado en este reporte.
     */
    private FaseContrato faseAnterior;
    /**
     * La nueva fase del contrato después de que se realizara el cambio registrado en este reporte.
     */
    private FaseContrato faseNueva;

    /**
     * Constructor por defecto de la clase ReporteInterventoria.
     * Inicializa una nueva instancia de ReporteInterventoria sin asignar valores iniciales a sus atributos.
     */
    public ReporteInterventoria() {
    }

    /**
     * Constructor parametrizado de la clase ReporteInterventoria.
     * Inicializa una nueva instancia de ReporteInterventoria con los valores proporcionados
     * y asigna un identificador único generado automáticamente.
     *
     * @param contrato El contrato asociado a este reporte.
     * @param informe El informe detallado del reporte.
     * @param fechaHora La fecha y hora de creación del reporte.
     * @param faseAnterior La fase anterior del contrato.
     * @param faseNueva La nueva fase del contrato.
     */
    public ReporteInterventoria(Contrato contrato, String informe, LocalDateTime fechaHora,
                                FaseContrato faseAnterior, FaseContrato faseNueva) {
        this.idReporte = "" + contadorId++;
        this.contrato = contrato;
        this.informe = informe;
        this.fechaHora = fechaHora;
        this.faseAnterior = faseAnterior;
        this.faseNueva = faseNueva;
    }

    /**
     * Obtiene el identificador único del reporte de interventoría.
     *
     * @return El identificador del reporte.
     */
    public String getIdReporte() {
        return idReporte;
    }

    /**
     * Establece el identificador único del reporte de interventoría.
     *
     * @param idReporte El nuevo identificador del reporte.
     */
    public void setIdReporte(String idReporte) {
        this.idReporte = idReporte;
    }

    /**
     * Obtiene el contrato asociado a este reporte de interventoría.
     *
     * @return El objeto Contrato asociado.
     */
    public Contrato getContrato() {
        return contrato;
    }

    /**
     * Establece el contrato asociado a este reporte de interventoría.
     *
     * @param contrato El nuevo objeto Contrato a asociar.
     */
    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    /**
     * Obtiene el informe detallado del reporte de interventoría.
     *
     * @return El informe del reporte.
     */
    public String getInforme() {
        return informe;
    }

    /**
     * Establece el informe detallado del reporte de interventoría.
     *
     * @param informe El nuevo informe del reporte.
     */
    public void setInforme(String informe) {
        this.informe = informe;
    }

    /**
     * Obtiene la fecha y hora de creación del reporte de interventoría.
     *
     * @return La fecha y hora del reporte.
     */
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    /**
     * Establece la fecha y hora de creación del reporte de interventoría.
     *
     * @param fechaHora La nueva fecha y hora del reporte.
     */
    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    /**
     * Obtiene la fase anterior del contrato registrada en este reporte.
     *
     * @return La fase anterior del contrato.
     */
    public FaseContrato getFaseAnterior() {
        return faseAnterior;
    }

    /**
     * Establece la fase anterior del contrato para este reporte.
     *
     * @param faseAnterior La nueva fase anterior del contrato.
     */
    public void setFaseAnterior(FaseContrato faseAnterior) {
        this.faseAnterior = faseAnterior;
    }

    /**
     * Obtiene la nueva fase del contrato registrada en este reporte.
     *
     * @return La nueva fase del contrato.
     */
    public FaseContrato getFaseNueva() {
        return faseNueva;
    }

    /**
     * Establece la nueva fase del contrato para este reporte.
     *
     * @param faseNueva La nueva fase del contrato.
     */
    public void setFaseNueva(FaseContrato faseNueva) {
        this.faseNueva = faseNueva;
    }
}
