package edu.uptc.servicios;

import edu.uptc.dominio.*;
import edu.uptc.enums.FaseContrato;
import edu.uptc.enums.TipoContrato;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

public class ServicioContratos {

    private HashMap<String, Contrato> contratos;

    // METODO CONSTRUCTOR INICIANDO LAS LISTAS DE LOS CONTRATOS
    public ServicioContratos() {
        this.contratos = new HashMap<>();
    }

// ===============================================================================================================
    // COMO AQUI SE MANEJARAN LOS CONTRATOS QUE SE HACE EN ESTE SERVICIO Y NO EN EL DE USUARIOS
    // APESAR DE SER METODOS DE USUARIOS CONTRATANTE Y CONTRATISTA :)


    // METODOS DEL CONTRATANTE
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

    // ===============================================================================================================
    // AGREGAR A LA LISTA DE CONTRATOS
    public void agregarContrato(Contrato contrato) {
        this.contratos.put(contrato.getIdContrato(), contrato);
    }

    // ===============================================================================================================
    // METODOS DEL CONTRATISTA

    // falta mirar esto( c: Contrato, f: FaseContrato,
    //informe: String) en los atributos para iniciar este metodo
    public void cambiarEstadoContrato() {

    }

    // ===============================================================================================================
    // VALIDACIONES
    // ----- VALIDACION CONTRATO PRESTACION SERVICIOS ------
    //  valor del honorario mensual, cuya
    //  suma no puede ser distinta al valor total del contrato.

    public boolean validarContratoPrestacionServicios(ContratoPrestacionServicios contratoPrestacionServicios) {
        return contratoPrestacionServicios.validar();
    }

    // TIEMPO DEL CONTRATO
    public int calcularPlazoMeses(LocalDate fechaCreacion, LocalDate fechaPlazoEjecucion) {

        // QUE LAS FECHAS TAMPOCO SEAN NULL
        if (fechaCreacion != null && fechaPlazoEjecucion != null) {
            // USAMOS CHRONOUNIT EN MONTHS ENTRE LAS FECHAS QUE TENIAMOS ANTERIORMENTE
            long meses = ChronoUnit.MONTHS.between(fechaCreacion, fechaPlazoEjecucion);
            // CASTEO EL RESULTADO EN INT YA QUE MESES EN UN TIPO LONG
            return (int) meses;
        }

        return 0;
    }

}
