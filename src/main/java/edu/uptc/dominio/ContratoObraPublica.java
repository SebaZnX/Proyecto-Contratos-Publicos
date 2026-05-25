package edu.uptc.dominio;

import edu.uptc.enums.FaseContrato;
import edu.uptc.enums.TipoContrato;

import java.time.LocalDate;

public class ContratoObraPublica extends Contrato {
    /**
     * Define los atributos específicos de la clase ContratoObraPublica,
     * la cual hereda las propiedades y comportamientos de la clase base Contrato.
     */
    private String ubicacionObra;
    private double areaIntervencion;

    /**
     * Constructor de la clase.
     * Inicializa una nueva instancia de la clase asignando los valores pasados por parámetro
     * a sus respectivos atributos.
     */
    public ContratoObraPublica(String idContrato, String objetoContrato, LocalDate fechaCreacion,
                               Contratante contratante, Contratista contratista, TipoContrato tipoContrato, double valorCelebrar,
                               LocalDate plazoEjecucion, FaseContrato faseActual, String ubicacionObra,
                               double areaIntervencion) {
        super(idContrato, objetoContrato, fechaCreacion, contratante, contratista, valorCelebrar, plazoEjecucion,
                faseActual, tipoContrato);
        this.ubicacionObra = ubicacionObra;
        this.areaIntervencion = areaIntervencion;
    }

    /**
     * Métodos de acceso (getters) y de modificación (setters) para los atributos de la clase.
     * Permiten encapsular la información, controlando la lectura y escritura de las variables.
     */
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
