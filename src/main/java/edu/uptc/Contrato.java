package edu.uptc;

import java.time.LocalDate;

public abstract class Contrato {
    protected String idContrato;
    protected String objetoContrato;
    protected LocalDate fechaCreacion;
    protected Contratante contratante;
    protected Contratista contratista;
    protected double valorCelebrar;
    protected LocalDate plazoEjecucion;
    protected FaseContrato faseActual;

    public Contrato() {
    }

    public Contrato(String idContrato, String objetoContrato, LocalDate fechaCreacion, Contratante contratante,
                    Contratista contratista, double valorCelebrar, LocalDate plazoEjecucion, FaseContrato faseActual) {
        this.idContrato = idContrato;
        this.objetoContrato = objetoContrato;
        this.fechaCreacion = fechaCreacion;
        this.contratante = contratante;
        this.contratista = contratista;
        this.valorCelebrar = valorCelebrar;
        this.plazoEjecucion = plazoEjecucion;
        this.faseActual = faseActual;
    }

    public String getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(String idContrato) {
        this.idContrato = idContrato;
    }

    public String getObjetoContrato() {
        return objetoContrato;
    }

    public void setObjetoContrato(String objetoContrato) {
        this.objetoContrato = objetoContrato;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Contratante getContratante() {
        return contratante;
    }

    public void setContratante(Contratante contratante) {
        this.contratante = contratante;
    }

    public Contratista getContratista() {
        return contratista;
    }

    public void setContratista(Contratista contratista) {
        this.contratista = contratista;
    }

    public double getValorCelebrar() {
        return valorCelebrar;
    }

    public void setValorCelebrar(double valorCelebrar) {
        this.valorCelebrar = valorCelebrar;
    }

    public LocalDate getPlazoEjecucion() {
        return plazoEjecucion;
    }

    public void setPlazoEjecucion(LocalDate plazoEjecucion) {
        this.plazoEjecucion = plazoEjecucion;
    }

    public FaseContrato getFaseActual() {
        return faseActual;
    }

    public void setFaseActual(FaseContrato faseActual) {
        this.faseActual = faseActual;
    }

    public abstract boolean validar();

    public abstract String mostrarInfo();
}
