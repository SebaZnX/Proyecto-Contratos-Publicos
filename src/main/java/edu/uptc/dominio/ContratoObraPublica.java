package edu.uptc.dominio;

import edu.uptc.enums.FaseContrato;
import edu.uptc.enums.TipoContrato;

import java.time.LocalDate;

/**
 * Representa un contrato de obra pública, extendiendo las funcionalidades de un {@link Contrato} base.
 * Incluye atributos específicos como la ubicación de la obra y el área de intervención.
 */
public class ContratoObraPublica extends Contrato {
    /**
     * La ubicación geográfica donde se realizará la obra pública.
     */
    private String ubicacionObra;
    /**
     * El área total en metros cuadrados sobre la cual se intervendrá en la obra.
     */
    private double areaIntervencion;

    /**
     * Constructor de la clase ContratoObraPublica.
     * Inicializa una nueva instancia de un contrato de obra pública con todos sus atributos.
     *
     * @param idContrato El identificador único del contrato.
     * @param objetoContrato La descripción del objeto del contrato.
     * @param fechaCreacion La fecha en que se creó el contrato.
     * @param contratante El objeto {@link Contratante} asociado al contrato.
     * @param contratista El objeto {@link Contratista} asignado al contrato (puede ser null inicialmente).
     * @param tipoContrato El {@link TipoContrato} del contrato, que debe ser CONTRATOOBRAPUBLICA.
     * @param valorCelebrar El valor total acordado para el contrato.
     * @param plazoEjecucion La fecha de finalización del plazo de ejecución del contrato.
     * @param faseActual La {@link FaseContrato} actual en la que se encuentra el contrato.
     * @param ubicacionObra La ubicación donde se realizará la obra.
     * @param areaIntervencion El área de intervención de la obra en metros cuadrados.
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
     * Obtiene la ubicación donde se realizará la obra pública.
     *
     * @return La ubicación de la obra.
     */
    public String getUbicacionObra() {
        return ubicacionObra;
    }

    /**
     * Establece la ubicación donde se realizará la obra pública.
     *
     * @param ubicacionObra La nueva ubicación de la obra.
     */
    public void setUbicacionObra(String ubicacionObra) {
        this.ubicacionObra = ubicacionObra;
    }

    /**
     * Obtiene el área de intervención de la obra en metros cuadrados.
     *
     * @return El área de intervención.
     */
    public double getAreaIntervencion() {
        return areaIntervencion;
    }

    /**
     * Establece el área de intervención de la obra en metros cuadrados.
     *
     * @param areaIntervencion La nueva área de intervención.
     */
    public void setAreaIntervencion(double areaIntervencion) {
        this.areaIntervencion = areaIntervencion;
    }

    /**
     * Valida la coherencia del contrato de obra pública.
     * Actualmente, este método siempre retorna {@code true}, indicando que no hay validaciones específicas
     * implementadas a nivel de la clase para este tipo de contrato.
     *
     * @return {@code true} siempre.
     */
    @Override
    public boolean validar() {
        return true;
    }

    /**
     * Genera una cadena de texto con la información detallada del contrato de obra pública.
     * Incluye tanto la información general del contrato base como los detalles específicos de la obra pública.
     *
     * @return Una cadena de texto formateada con la información del contrato.
     */
    @Override
    public String mostrarInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("=========================================\n");
        sb.append("• ID Contrato: ").append(this.idContrato).append("\n");
        sb.append("• Tipo Contrato: ").append(this.tipoContrato).append("\n");
        sb.append("• Objeto del Contrato: ").append(this.objetoContrato).append("\n");
        sb.append("• Fase Actual: ").append(this.faseActual).append("\n");
        sb.append("• Valor Total: $").append(this.valorCelebrar).append("\n");
        sb.append("• Fecha de Creación: ").append(this.fechaCreacion).append("\n");
        sb.append("• Plazo de Ejecución: ").append(this.plazoEjecucion).append("\n");
        sb.append("• Contratante: ").append(this.contratante != null ? this.contratante.getNombre() : "Sin asignar").append("\n");
        sb.append("• Contratista: ").append(this.contratista != null ? this.contratista.getNombre() : "Sin asignar").append("\n");
        sb.append("-----------------------------------------\n");
        sb.append("DETALLES DE OBRA PÚBLICA:\n");
        sb.append("• Ubicación de la Obra: ").append(this.ubicacionObra).append("\n");
        sb.append("• Área de Intervención: ").append(this.areaIntervencion).append(" m²\n");
        sb.append("=========================================");
        return sb.toString();
    }
}
