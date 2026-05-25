package edu.uptc.dominio;

import edu.uptc.enums.FaseContrato;
import edu.uptc.enums.TipoContrato;

import java.time.LocalDate;

/**
 * Clase abstracta que define la estructura base y los atributos comunes para todos los tipos de contratos
 * en el sistema de Contratación Pública. Sirve como plantilla para contratos específicos,
 * asegurando la consistencia en la información fundamental.
 */
public abstract class Contrato {
    /**
     * Identificador único del contrato.
     */
    protected String idContrato;
    /**
     * Descripción del objeto o propósito del contrato.
     */
    protected String objetoContrato;
    /**
     * Fecha en la que el contrato fue creado.
     */
    protected LocalDate fechaCreacion;
    /**
     * El {@link Contratante} que celebra el contrato.
     */
    protected Contratante contratante;
    /**
     * El {@link Contratista} que ejecuta el contrato.
     */
    protected Contratista contratista;
    /**
     * El valor monetario total acordado para la celebración del contrato.
     */
    protected double valorCelebrar;
    /**
     * La fecha límite para la ejecución del contrato.
     */
    protected LocalDate plazoEjecucion;
    /**
     * La fase actual en la que se encuentra el contrato (e.g., Pre-contractual, Ejecución, Liquidación).
     */
    protected FaseContrato faseActual;
    /**
     * El tipo específico de contrato (e.g., Obra Pública, Prestación de Servicios, Compraventa).
     */
    protected TipoContrato tipoContrato;

    /**
     * Constructor vacío de la clase {@code Contrato}.
     * Permite la creación de instancias de contratos sin inicializar sus atributos,
     * los cuales pueden ser establecidos posteriormente mediante los métodos setters.
     */
    public Contrato() {
    }

    /**
     * Constructor para crear una nueva instancia de {@code Contrato} con todos sus atributos inicializados.
     *
     * @param idContrato Identificador único del contrato.
     * @param objetoContrato Descripción del objeto o propósito del contrato.
     * @param fechaCreacion Fecha de creación del contrato.
     * @param contratante El contratante involucrado en el contrato.
     * @param contratista El contratista involucrado en el contrato.
     * @param valorCelebrar El valor monetario del contrato.
     * @param plazoEjecucion La fecha límite para la ejecución del contrato.
     * @param faseActual La fase actual del contrato.
     * @param tipoContrato El tipo específico de contrato.
     */
    public Contrato(String idContrato, String objetoContrato, LocalDate fechaCreacion, Contratante contratante,
                    Contratista contratista, double valorCelebrar, LocalDate plazoEjecucion, FaseContrato faseActual,
                    TipoContrato tipoContrato) {
        this.idContrato = idContrato;
        this.objetoContrato = objetoContrato;
        this.fechaCreacion = fechaCreacion;
        this.contratante = contratante;
        this.contratista = contratista;
        this.valorCelebrar = valorCelebrar;
        this.plazoEjecucion = plazoEjecucion;
        this.faseActual = faseActual;
        this.tipoContrato = tipoContrato;
    }

    /**
     * Obtiene el identificador único del contrato.
     *
     * @return El ID del contrato.
     */
    public String getIdContrato() {
        return idContrato;
    }

    /**
     * Establece el identificador único del contrato.
     *
     * @param idContrato El nuevo ID del contrato.
     */
    public void setIdContrato(String idContrato) {
        this.idContrato = idContrato;
    }

    /**
     * Obtiene la descripción del objeto o propósito del contrato.
     *
     * @return El objeto del contrato.
     */
    public String getObjetoContrato() {
        return objetoContrato;
    }

    /**
     * Establece la descripción del objeto o propósito del contrato.
     *
     * @param objetoContrato El nuevo objeto del contrato.
     */
    public void setObjetoContrato(String objetoContrato) {
        this.objetoContrato = objetoContrato;
    }

    /**
     * Obtiene la fecha de creación del contrato.
     *
     * @return La fecha de creación.
     */
    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * Establece la fecha de creación del contrato.
     *
     * @param fechaCreacion La nueva fecha de creación.
     */
    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Obtiene el contratante asociado al contrato.
     *
     * @return El objeto {@link Contratante}.
     */
    public Contratante getContratante() {
        return contratante;
    }

    /**
     * Establece el contratante asociado al contrato.
     *
     * @param contratante El nuevo objeto {@link Contratante}.
     */
    public void setContratante(Contratante contratante) {
        this.contratante = contratante;
    }

    /**
     * Obtiene el contratista asociado al contrato.
     *
     * @return El objeto {@link Contratista}.
     */
    public Contratista getContratista() {
        return contratista;
    }

    /**
     * Establece el contratista asociado al contrato.
     *
     * @param contratista El nuevo objeto {@link Contratista}.
     */
    public void setContratista(Contratista contratista) {
        this.contratista = contratista;
    }

    /**
     * Obtiene el valor monetario acordado para la celebración del contrato.
     *
     * @return El valor a celebrar del contrato.
     */
    public double getValorCelebrar() {
        return valorCelebrar;
    }

    /**
     * Establece el valor monetario acordado para la celebración del contrato.
     *
     * @param valorCelebrar El nuevo valor a celebrar del contrato.
     */
    public void setValorCelebrar(double valorCelebrar) {
        this.valorCelebrar = valorCelebrar;
    }

    /**
     * Obtiene la fecha límite para la ejecución del contrato.
     *
     * @return La fecha de plazo de ejecución.
     */
    public LocalDate getPlazoEjecucion() {
        return plazoEjecucion;
    }

    /**
     * Establece la fecha límite para la ejecución del contrato.
     *
     * @param plazoEjecucion La nueva fecha de plazo de ejecución.
     */
    public void setPlazoEjecucion(LocalDate plazoEjecucion) {
        this.plazoEjecucion = plazoEjecucion;
    }

    /**
     * Obtiene la fase actual en la que se encuentra el contrato.
     *
     * @return La {@link FaseContrato} actual.
     */
    public FaseContrato getFaseActual() {
        return faseActual;
    }

    /**
     * Establece la fase actual en la que se encuentra el contrato.
     *
     * @param faseActual La nueva {@link FaseContrato} actual.
     */
    public void setFaseActual(FaseContrato faseActual) {
        this.faseActual = faseActual;
    }

    /**
     * Obtiene el tipo específico de contrato.
     *
     * @return El {@link TipoContrato} del contrato.
     */
    public TipoContrato getTipoContrato() {
        return tipoContrato;
    }

    /**
     * Establece el tipo específico de contrato.
     *
     * @param tipoContrato El nuevo {@link TipoContrato} del contrato.
     */
    public void setTipoContrato(TipoContrato tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    /**
     * Método abstracto para validar la información específica de un contrato.
     * Cada subclase de {@code Contrato} debe implementar su propia lógica de validación.
     *
     * @return {@code true} si el contrato es válido según sus reglas específicas, {@code false} en caso contrario.
     */
    public abstract boolean validar();

    /**
     * Método abstracto para obtener una representación en cadena de la información detallada del contrato.
     * Cada subclase de {@code Contrato} debe implementar su propia forma de mostrar su información.
     *
     * @return Una cadena de texto que contiene la información formateada del contrato.
     */
    public abstract String mostrarInfo();
}
