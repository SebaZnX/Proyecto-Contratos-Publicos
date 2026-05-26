package edu.uptc.dominio;

import edu.uptc.enums.FaseContrato;
import edu.uptc.enums.TipoContrato;

import java.time.LocalDate;

/**
 * Representa un contrato de compraventa, extendiendo las funcionalidades de un {@link Contrato} base.
 * Incluye atributos específicos para describir el ítem a adquirir, como marca, modelo, serie,
 * valor unitario y cantidad.
 */
public class ContratoCompraventa extends Contrato {
    /**
     * El nombre o descripción del ítem que se va a adquirir mediante el contrato de compraventa.
     */
    private String itemAdquirir;
    /**
     * La marca del ítem que se va a adquirir.
     */
    private String marca;
    /**
     * El modelo específico del ítem que se va a adquirir.
     */
    private String modelo;
    /**
     * El número de serie único del ítem que se va a adquirir.
     */
    private String serie;
    /**
     * El valor monetario por cada unidad del ítem a adquirir.
     */
    private double valorUnitario;
    /**
     * La cantidad de unidades del ítem que se van a adquirir.
     */
    private int cantidadAdquirir;

    /**
     * Constructor de la clase ContratoCompraventa.
     * Inicializa una nueva instancia de un contrato de compraventa con todos sus atributos.
     *
     * @param idContrato El identificador único del contrato.
     * @param objetoContrato La descripción del objeto del contrato.
     * @param fechaCreacion La fecha en que se creó el contrato.
     * @param contratante El objeto {@link Contratante} asociado al contrato.
     * @param contratista El objeto {@link Contratista} asignado al contrato (puede ser null inicialmente).
     * @param valorCelebrar El valor total acordado para el contrato.
     * @param plazoEjecucion La fecha de finalización del plazo de ejecución del contrato.
     * @param faseActual La {@link FaseContrato} actual en la que se encuentra el contrato.
     * @param tipoContrato El {@link TipoContrato} del contrato, que debe ser CONTRATOCOMPRAVENTA.
     * @param itemAdquirir El nombre del ítem a adquirir.
     * @param marca La marca del ítem a adquirir.
     * @param modelo El modelo del ítem a adquirir.
     * @param serie El número de serie del ítem a adquirir.
     * @param valorUnitario El valor unitario del ítem.
     * @param cantidadAdquirir La cantidad de ítems a adquirir.
     */
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

    /**
     * Obtiene el nombre o descripción del ítem a adquirir.
     *
     * @return El ítem a adquirir.
     */
    public String getItemAdquirir() {
        return itemAdquirir;
    }

    /**
     * Establece el nombre o descripción del ítem a adquirir.
     *
     * @param itemAdquirir El nuevo ítem a adquirir.
     */
    public void setItemAdquirir(String itemAdquirir) {
        this.itemAdquirir = itemAdquirir;
    }

    /**
     * Obtiene la marca del ítem a adquirir.
     *
     * @return La marca del ítem.
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Establece la marca del ítem a adquirir.
     *
     * @param marca La nueva marca del ítem.
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * Obtiene el modelo del ítem a adquirir.
     *
     * @return El modelo del ítem.
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * Establece el modelo del ítem a adquirir.
     *
     * @param modelo El nuevo modelo del ítem.
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * Obtiene el número de serie del ítem a adquirir.
     *
     * @return El número de serie del ítem.
     */
    public String getSerie() {
        return serie;
    }

    /**
     * Establece el número de serie del ítem a adquirir.
     *
     * @param serie El nuevo número de serie del ítem.
     */
    public void setSerie(String serie) {
        this.serie = serie;
    }

    /**
     * Obtiene el valor unitario del ítem a adquirir.
     *
     * @return El valor unitario del ítem.
     */
    public double getValorUnitario() {
        return valorUnitario;
    }

    /**
     * Establece el valor unitario del ítem a adquirir.
     *
     * @param valorUnitario El nuevo valor unitario del ítem.
     */
    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    /**
     * Obtiene la cantidad de ítems a adquirir.
     *
     * @return La cantidad de ítems.
     */
    public int getCantidadAdquirir() {
        return cantidadAdquirir;
    }

    /**
     * Establece la cantidad de ítems a adquirir.
     *
     * @param cantidadAdquirir La nueva cantidad de ítems.
     */
    public void setCantidadAdquirir(int cantidadAdquirir) {
        this.cantidadAdquirir = cantidadAdquirir;
    }

    /**
     * Valida la coherencia del contrato de compraventa.
     * Verifica que el valor total del contrato sea igual al producto del valor unitario
     * por la cantidad de ítems a adquirir.
     *
     * @return {@code true} si el valor total coincide con el cálculo de valor unitario por cantidad, {@code false} en caso contrario.
     */
    @Override
    public boolean validar() {
        if (this.valorCelebrar == (this.valorUnitario * (double) this.cantidadAdquirir)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Genera una cadena de texto con la información detallada del contrato de compraventa.
     * Incluye tanto la información general del contrato base como los detalles específicos de la compraventa.
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
        sb.append("DETALLES DE COMPRAVENTA:\n");
        sb.append("• Ítem a Adquirir: ").append(this.itemAdquirir).append("\n");
        sb.append("• Marca: ").append(this.marca).append("\n");
        sb.append("• Modelo: ").append(this.modelo).append("\n");
        sb.append("• Serie: ").append(this.serie).append("\n");
        sb.append("• Valor Unitario: $").append(this.valorUnitario).append("\n");
        sb.append("• Cantidad: ").append(this.cantidadAdquirir).append("\n");
        sb.append("=========================================");
        return sb.toString();
    }
}
