package edu.uptc;

import java.time.LocalDate;

public class ContratoCompraventa extends Contrato {
    private String itemAdquirir;
    private String marca;
    private String modelo;
    private String serie;
    private double valorUnitario;
    private int cantidadAdquirir;

    public ContratoCompraventa(String idContrato, String objetoContrato, LocalDate fechaCreacion,
                               Contratante contratante, Contratista contratista, double valorCelebrar,
                               LocalDate plazoEjecucion, FaseContrato faseActual, String itemAdquirir, String marca,
                               String modelo, String serie, double valorUnitario, int cantidadAdquirir) {
        super(idContrato, objetoContrato, fechaCreacion, contratante, contratista, valorCelebrar, plazoEjecucion,
                faseActual);
        this.itemAdquirir = itemAdquirir;
        this.marca = marca;
        this.modelo = modelo;
        this.serie = serie;
        this.valorUnitario = valorUnitario;
        this.cantidadAdquirir = cantidadAdquirir;
    }

    public String getItemAdquirir() {
        return itemAdquirir;
    }

    public void setItemAdquirir(String itemAdquirir) {
        this.itemAdquirir = itemAdquirir;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public int getCantidadAdquirir() {
        return cantidadAdquirir;
    }

    public void setCantidadAdquirir(int cantidadAdquirir) {
        this.cantidadAdquirir = cantidadAdquirir;
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
