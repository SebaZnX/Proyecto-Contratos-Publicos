package edu.uptc.dominio;

import edu.uptc.enums.FaseContrato;
import edu.uptc.enums.TipoContrato;

import java.time.LocalDate;

/**
 * Representa un contrato de prestación de servicios, extendiendo las funcionalidades de un {@link Contrato} base.
 * Incluye atributos específicos como el perfil requerido, los entregables, el valor del honorario mensual
 * y la duración en meses del contrato.
 */
public class ContratoPrestacionServicios extends Contrato {
    /**
     * El perfil profesional o técnico requerido para la ejecución de los servicios.
     */
    private String perfilRequerido;
    /**
     * Descripción de los productos o resultados esperados que el contratista debe entregar.
     */
    private String entregables;
    /**
     * El monto monetario que se pagará al contratista de forma mensual.
     */
    private double valorHonorarioMensual;
    /**
     * La duración total del contrato de prestación de servicios, expresada en meses.
     */
    private int mesesContratoPrestacionServicios;

    /**
     * Constructor de la clase ContratoPrestacionServicios.
     * Inicializa una nueva instancia de un contrato de prestación de servicios con todos sus atributos.
     *
     * @param idContrato El identificador único del contrato.
     * @param objetoContrato La descripción del objeto del contrato.
     * @param fechaCreacion La fecha en que se creó el contrato.
     * @param contratante El objeto {@link Contratante} asociado al contrato.
     * @param contratista El objeto {@link Contratista} asignado al contrato (puede ser null inicialmente).
     * @param valorCelebrar El valor total acordado para el contrato.
     * @param plazoEjecucion La fecha de finalización del plazo de ejecución del contrato.
     * @param faseActual La {@link FaseContrato} actual en la que se encuentra el contrato.
     * @param tipoContrato El {@link TipoContrato} del contrato, que debe ser CONTRATOPRESTACIONSERVICIOS.
     * @param perfilRequerido El perfil profesional requerido para el servicio.
     * @param entregables Los entregables esperados del servicio.
     * @param valorHonorarioMensual El valor del honorario mensual.
     * @param mesesContratoPrestacionServicios La duración del contrato en meses.
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
     * Obtiene el perfil profesional requerido para el contrato.
     *
     * @return El perfil requerido.
     */
    public String getPerfilRequerido() {
        return perfilRequerido;
    }

    /**
     * Establece el perfil profesional requerido para el contrato.
     *
     * @param perfilRequerido El nuevo perfil requerido.
     */
    public void setPerfilRequerido(String perfilRequerido) {
        this.perfilRequerido = perfilRequerido;
    }

    /**
     * Obtiene la descripción de los entregables del contrato.
     *
     * @return Los entregables.
     */
    public String getEntregables() {
        return entregables;
    }

    /**
     * Establece la descripción de los entregables del contrato.
     *
     * @param entregables Los nuevos entregables.
     */
    public void setEntregables(String entregables) {
        this.entregables = entregables;
    }

    /**
     * Obtiene el valor del honorario mensual del contrato.
     *
     * @return El valor del honorario mensual.
     */
    public double getValorHonorarioMensual() {
        return valorHonorarioMensual;
    }

    /**
     * Establece el valor del honorario mensual del contrato.
     *
     * @param valorHonorarioMensual El nuevo valor del honorario mensual.
     */
    public void setValorHonorarioMensual(double valorHonorarioMensual) {
        this.valorHonorarioMensual = valorHonorarioMensual;
    }

    /**
     * Obtiene la duración del contrato de prestación de servicios en meses.
     *
     * @return La cantidad de meses del contrato.
     */
    public int getMesesContratoPrestacionServicios() {
        return mesesContratoPrestacionServicios;
    }

    /**
     * Establece la duración del contrato de prestación de servicios en meses.
     *
     * @param mesesContratoPrestacionServicios La nueva cantidad de meses del contrato.
     */
    public void setMesesContratoPrestacionServicios(int mesesContratoPrestacionServicios) {
        this.mesesContratoPrestacionServicios = mesesContratoPrestacionServicios;
    }

    /**
     * Valida la coherencia del contrato de prestación de servicios.
     * Verifica que el valor total del contrato sea igual al producto del honorario mensual
     * por la cantidad de meses del contrato.
     *
     * @return {@code true} si el valor total coincide con el cálculo de honorarios mensuales, {@code false} en caso contrario.
     */
    @Override
    public boolean validar() {
        if (this.valorCelebrar == (this.valorHonorarioMensual * (double) this.mesesContratoPrestacionServicios)){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Genera una cadena de texto con la información detallada del contrato de prestación de servicios.
     * Incluye tanto la información general del contrato base como los detalles específicos de la prestación de servicios.
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
        sb.append("• Contratante: ").append(this.contratante.getNombre()).append("\n");
        sb.append("• Contratista: ").append(this.contratista.getNombre()).append("\n");
        sb.append("-----------------------------------------\n");
        sb.append("DETALLES DE PRESTACIÓN DE SERVICIOS:\n");
        sb.append("• Perfil Requerido: ").append(this.perfilRequerido).append("\n");
        sb.append("• Entregables: ").append(this.entregables).append("\n");
        sb.append("• Honorario Mensual: $").append(this.valorHonorarioMensual).append("\n");
        sb.append("• Duración Estimada: ").append(this.mesesContratoPrestacionServicios).append(" meses\n");
        sb.append("=========================================");
        return sb.toString();
    }
}
