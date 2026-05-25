package edu.uptc.servicios;

import edu.uptc.dominio.*;
import edu.uptc.enums.FaseContrato;
import edu.uptc.enums.TipoContrato;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

public class ServicioContratos {

    private HashMap<String, Contrato> contratos;

    /**
     * Inicializa el constructor y las listas de contratos del sistema.
     */
    public ServicioContratos() {
        this.contratos = new HashMap<>();
    }

    /**
     * Operaciones exclusivas del usuario contratante.
     * * NOTA: Se definen en este servicio centralizado de contratos y no en el de usuarios
     * debido a que la lógica de negocio pertenece directamente al ciclo de vida del contrato.
     */
    public void crearContratoPrestacionServicios(String idContrato, String objetoContrato,
                                                 Contratante contratante, double valorCelebrar, LocalDate plazoEjecucion,
                                                 String perfilRequerido,
                                                 String entregables, double valorHonorarioMensual) {
        TipoContrato tipoContrato = TipoContrato.CONTRATOPRESTACIONSERVICIOS;
        LocalDate fechaCreacion = LocalDate.now();
        int mesesContratoPrestacionServicios = calcularPlazoMeses(fechaCreacion, plazoEjecucion);

        Contrato contratoPrestacionServicioAux = new ContratoPrestacionServicios(idContrato, objetoContrato, fechaCreacion,
                contratante, null, valorCelebrar, plazoEjecucion, FaseContrato.PUBLICACION, tipoContrato,
                perfilRequerido, entregables, valorHonorarioMensual, mesesContratoPrestacionServicios);

        if (validarContratoPrestacionServicios((ContratoPrestacionServicios) contratoPrestacionServicioAux)) {
            agregarContrato(contratoPrestacionServicioAux);
        }
    }

    public void crearContratoObraPublica(String idContrato, String objetoContrato,
                                         Contratante contratante, double valorCelebrar, LocalDate plazoEjecucion,
                                         String ubicacionObra, double areaIntervencion) {
        LocalDate fechaCreacion = LocalDate.now();

        Contrato contratoObraPublica = new ContratoObraPublica(idContrato, objetoContrato, fechaCreacion, contratante,
                null, TipoContrato.CONTRATOOBRAPUBLICA, valorCelebrar, plazoEjecucion, FaseContrato.PUBLICACION,
                ubicacionObra, areaIntervencion);
        agregarContrato(contratoObraPublica);
    }

    public void crearContratoCompraVenta(String idContrato, String objetoContrato, LocalDate fechaCreacion,
                                         Contratante contratante, Contratista contratista, double valorCelebrar,
                                         LocalDate plazoEjecucion, FaseContrato faseActual, TipoContrato tipoContrato, String perfilRequerido,
                                         String entregables, double valorHonorarioMensual) {

    }

    public void consultarContrato() {

    }

    public void actualizarContrato() {

    }

    public void eliminarContrato() {

    }

    /**
     * Agrega un nuevo contrato a la lista de registros del sistema.
     */
    public void agregarContrato(Contrato contrato) {
        this.contratos.put(contrato.getIdContrato(), contrato);
    }

    /**
     * Operaciones exclusivas del usuario contratista.
     * * TODO: Pendiente revisar la inclusión de los atributos (Contrato c,
     * FaseContrato f, String informe) para la correcta inicialización de este método.
     */
    public void cambiarEstadoContrato() {

    }

    /**
     * Secciones de validación del sistema.
     * * Validación para contratos de prestación de servicios:
     * Verifica que el valor del honorario mensual coincida exactamente
     * con la sumatoria del valor total del contrato.
     */
    public boolean validarContratoPrestacionServicios(ContratoPrestacionServicios contratoPrestacionServicios) {
        return contratoPrestacionServicios.validar();
    }

    /**
     * Gestiona o valida la duración y los tiempos estipulados del contrato.
     */
    public int calcularPlazoMeses(LocalDate fechaCreacion, LocalDate fechaPlazoEjecucion) {

        /**
         * Valida que los campos de fecha no sean nulos (null).
         */
        if (fechaCreacion != null && fechaPlazoEjecucion != null) {
            /**
             * Calcula la diferencia en meses entre las fechas previamente definidas
             * utilizando ChronoUnit.MONTHS.
             */
            long meses = ChronoUnit.MONTHS.between(fechaCreacion, fechaPlazoEjecucion);
            /**
             * Convierte el resultado de la diferencia de meses a un tipo entero (int),
             * dado que el cálculo original retorna un valor de tipo long.
             */
            return (int) meses;
        }

        return 0;
    }

}
