package edu.uptc;

import java.time.LocalDate;

public class ContratoPrestacionServicios extends Contrato {
    private String perfilRequerido;
    private String entregables;
    private double valorHonorarioMensual;

    public ContratoPrestacionServicios(String idContrato, String objetoContrato, LocalDate fechaCreacion,
                                       Contratante contratante, Contratista contratista, double valorCelebrar,
                                       LocalDate plazoEjecucion, FaseContrato faseActual, String perfilRequerido,
                                       String entregables, double valorHonorarioMensual) {
        super(idContrato, objetoContrato, fechaCreacion, contratante, contratista, valorCelebrar, plazoEjecucion,
                faseActual);
        this.perfilRequerido = perfilRequerido;
        this.entregables = entregables;
        this.valorHonorarioMensual = valorHonorarioMensual;
    }

    public String getPerfilRequerido() {
        return perfilRequerido;
    }

    public void setPerfilRequerido(String perfilRequerido) {
        this.perfilRequerido = perfilRequerido;
    }

    public String getEntregables() {
        return entregables;
    }

    public void setEntregables(String entregables) {
        this.entregables = entregables;
    }

    public double getValorHonorarioMensual() {
        return valorHonorarioMensual;
    }

    public void setValorHonorarioMensual(double valorHonorarioMensual) {
        this.valorHonorarioMensual = valorHonorarioMensual;
    }

    @Override
    public boolean validar() {
        return false;
    }

    @Override
    public String mostrarInfo() {
        return "";
    }
}
