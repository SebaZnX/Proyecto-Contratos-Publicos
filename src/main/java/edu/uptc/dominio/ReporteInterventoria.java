package edu.uptc.dominio;

import edu.uptc.enums.FaseContrato;

import java.time.LocalDateTime;

public class ReporteInterventoria {
    /**
     * Define los atributos específicos que componen la entidad de Reporte de Interventoría.
     * Almacena los datos necesarios para el seguimiento, control y supervisión técnica,
     * administrativa o financiera de un contrato.
     */
    private String idReporte;
    private Contrato contrato;
    private String informe;
    private LocalDateTime fechaHora;
    private FaseContrato faseAnterior;
    private FaseContrato faseNueva;

    /**
     * Constructor vacío de la clase.
     * Inicializa una nueva instancia sin asignar valores previos a sus atributos.
     */
    public ReporteInterventoria() {
    }

    /**
     * Constructor de la clase.
     * Inicializa una nueva instancia de la clase asignando los valores pasados por parámetro
     * a sus respectivos atributos.
     */
    public ReporteInterventoria(String idReporte, Contrato contrato, String informe, LocalDateTime fechaHora,
                                FaseContrato faseAnterior, FaseContrato faseNueva) {
        this.idReporte = idReporte;
        this.contrato = contrato;
        this.informe = informe;
        this.fechaHora = fechaHora;
        this.faseAnterior = faseAnterior;
        this.faseNueva = faseNueva;
    }
    /**
     * Métodos de acceso (getters) y de modificación (setters) para los atributos de la clase.
     * Permiten encapsular la información, controlando la lectura y escritura de las variables.
     */
    public String getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(String idReporte) {
        this.idReporte = idReporte;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public String getInforme() {
        return informe;
    }

    public void setInforme(String informe) {
        this.informe = informe;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public FaseContrato getFaseAnterior() {
        return faseAnterior;
    }

    public void setFaseAnterior(FaseContrato faseAnterior) {
        this.faseAnterior = faseAnterior;
    }

    public FaseContrato getFaseNueva() {
        return faseNueva;
    }

    public void setFaseNueva(FaseContrato faseNueva) {
        this.faseNueva = faseNueva;
    }

    public String mostrarReporte() {
        return "";
    }
}
