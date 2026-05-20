package edu.uptc;

import java.time.LocalDate;

public class ContratoObraPublica extends Contrato {
    private String ubicacionObra;
    private double areaIntervencion;

    public ContratoObraPublica(String idContrato, String objetoContrato, LocalDate fechaCreacion,
                               Contratante contratante, Contratista contratista, double valorCelebrar,
                               LocalDate plazoEjecucion, FaseContrato faseActual, String ubicacionObra,
                               double areaIntervencion) {
        super(idContrato, objetoContrato, fechaCreacion, contratante, contratista, valorCelebrar, plazoEjecucion,
                faseActual);
        this.ubicacionObra = ubicacionObra;
        this.areaIntervencion = areaIntervencion;
    }

    public String getUbicacionObra() {
        return ubicacionObra;
    }

    public void setUbicacionObra(String ubicacionObra) {
        this.ubicacionObra = ubicacionObra;
    }

    public double getAreaIntervencion() {
        return areaIntervencion;
    }

    public void setAreaIntervencion(double areaIntervencion) {
        this.areaIntervencion = areaIntervencion;
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
