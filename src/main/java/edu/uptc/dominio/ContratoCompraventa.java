package edu.uptc.dominio;

import edu.uptc.enums.FaseContrato;
import edu.uptc.enums.TipoContrato;

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
                               LocalDate plazoEjecucion, FaseContrato faseActual, TipoContrato tipoContrato, String itemAdquirir, String marca,
                               String modelo, String serie, double valorUnitario, int cantidadAdquirir) {
        super(idContrato, objetoContrato, fechaCreacion, contratante, contratista, valorCelebrar, plazoEjecucion,
                faseActual, tipoContrato);
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

    /**
     * Implementa o sobrescribe los métodos abstractos heredados de la clase padre.
     * Define el comportamiento específico que la clase hija debe proporcionar para cumplir
     * con el contrato establecido por la superclase.
     */
    @Override
    public boolean validar() {
        return false;
    }

    @Override
    public String mostrarInfo() {
        return "";
    }
}
