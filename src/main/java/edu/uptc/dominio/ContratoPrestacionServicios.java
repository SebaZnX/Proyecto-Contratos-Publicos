package edu.uptc.dominio;

import edu.uptc.enums.FaseContrato;
import edu.uptc.enums.TipoContrato;

import java.time.LocalDate;

public class ContratoPrestacionServicios extends Contrato {
    /**
     * Define los atributos específicos de la clase ContratoPrestacionServicios,
     * la cual hereda las propiedades y comportamientos de la clase base Contrato.
     */
    private String perfilRequerido;
    private String entregables;
    private double valorHonorarioMensual;
    private int mesesContratoPrestacionServicios;

    /**
     * Constructor de la clase.
     * Inicializa una nueva instancia de la clase asignando los valores pasados por parámetro
     * a sus respectivos atributos.
     */
    public ContratoPrestacionServicios(String idContrato, String objetoContrato, LocalDate fechaCreacion,
                                       Contratante contratante, Contratista contratista, double valorCelebrar,
                                       LocalDate plazoEjecucion, FaseContrato faseActual, TipoContrato tipoContrato, String perfilRequerido,
                                       String entregables, double valorHonorarioMensual, int mesesContratoPrestacionServicios) {
        super(idContrato, objetoContrato, fechaCreacion, contratante, contratista, valorCelebrar, plazoEjecucion,
                faseActual, tipoContrato);
        this.perfilRequerido = perfilRequerido;
        this.entregables = entregables;
        this.valorHonorarioMensual = valorHonorarioMensual;
        this.mesesContratoPrestacionServicios = mesesContratoPrestacionServicios;
    }

    /**
     * Métodos de acceso (getters) y de modificación (setters) para los atributos de la clase.
     * Permiten encapsular la información, controlando la lectura y escritura de las variables.
     */
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

    public int getMesesContratoPrestacionServicios() {
        return mesesContratoPrestacionServicios;
    }

    public void setMesesContratoPrestacionServicios(int mesesContratoPrestacionServicios) {
        this.mesesContratoPrestacionServicios = mesesContratoPrestacionServicios;
    }

    /**
     * Implementa o sobrescribe los métodos abstractos heredados de la clase padre.
     * Define el comportamiento específico que la clase hija debe proporcionar para cumplir
     * con el contrato establecido por la superclase.
     */
    @Override
    public boolean validar() {
        if (this.valorCelebrar == (this.valorHonorarioMensual * (double) this.mesesContratoPrestacionServicios)){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String mostrarInfo() {
        return "";
    }
}
