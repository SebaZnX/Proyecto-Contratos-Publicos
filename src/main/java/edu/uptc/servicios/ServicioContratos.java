package edu.uptc.servicios;

import edu.uptc.dominio.Contratante;
import edu.uptc.dominio.Contratista;
import edu.uptc.dominio.Contrato;
import edu.uptc.dominio.ContratoCompraventa;
import edu.uptc.dominio.ContratoObraPublica;
import edu.uptc.dominio.ContratoPrestacionServicios;
import edu.uptc.dominio.ReporteInterventoria;
import edu.uptc.dominio.Usuario;
import edu.uptc.enums.FaseContrato;
import edu.uptc.enums.TipoContrato;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Servicio que gestiona las operaciones relacionadas con los contratos en el sistema.
 * Esta capa es la ÚNICA autorizada para instanciar, modificar y consultar objetos de dominio
 * relacionados con contratos. Recibe identificadores (Strings) y resuelve las referencias
 * a entidades de dominio internamente, delegando la búsqueda de usuarios a {@link ServicioUsuarios}.
 */
public class ServicioContratos {

    /** Mapa de contratos indexado por idContrato. */
    private HashMap<String, Contrato> contratos;

    /**
     * Referencia al servicio de usuarios, inyectada en el constructor,
     * para resolver internamente los IDs de contratante y contratista.
     */
    private ServicioUsuarios servicioUsuarios;

    /** Mapa interno de postulaciones por contrato (idContrato → lista de documentos). */
    private HashMap<String, List<String>> postulaciones = new HashMap<>();

    /**
     * Constructor de ServicioContratos.
     *
     * @param servicioUsuarios Instancia del servicio de usuarios necesaria para resolver
     *                         referencias internas de contratante/contratista por documento.
     */
    public ServicioContratos(ServicioUsuarios servicioUsuarios) {
        this.contratos = new HashMap<>();
        this.servicioUsuarios = servicioUsuarios;
    }

    /**
     * Crea un nuevo contrato de Prestación de Servicios en fase PUBLICACION.
     * El contratista se inicializa en null hasta la fase de LICITACION/ADJUDICACION.
     *
     * @param idContrato            Identificador único del contrato.
     * @param objetoContrato        Descripción del objeto del contrato.
     * @param documentoContratante  Número de documento del contratante (se resuelve internamente).
     * @param valorCelebrar         Valor total acordado.
     * @param plazoEjecucion        Fecha límite de ejecución.
     * @param perfilRequerido       Perfil profesional requerido.
     * @param entregables           Descripción de los entregables.
     * @param valorHonorarioMensual Honorario mensual del contratista.
     * @return Mensaje de resultado de la operación.
     */
    public String crearContratoPrestacionServicios(String idContrato, String objetoContrato,
                                                   String documentoContratante, double valorCelebrar,
                                                   LocalDate plazoEjecucion, String perfilRequerido,
                                                   String entregables, double valorHonorarioMensual) {
        Contratante contratante = resolverContratante(documentoContratante);
        if (contratante == null) {
            return "ERROR: El contratante con documento '" + documentoContratante + "' no existe en el sistema.";
        }
        if (existeIdContrato(idContrato)) {
            return "ERROR: Ya existe un contrato con el ID '" + idContrato + "'.";
        }

        LocalDate fechaCreacion = LocalDate.now();
        int meses = calcularPlazoMeses(fechaCreacion, plazoEjecucion);

        ContratoPrestacionServicios contrato = new ContratoPrestacionServicios(
                idContrato, objetoContrato, fechaCreacion,
                contratante, null,
                valorCelebrar, plazoEjecucion, FaseContrato.PUBLICACION,
                TipoContrato.CONTRATOPRESTACIONSERVICIOS,
                perfilRequerido, entregables, valorHonorarioMensual, meses);

        if (!contrato.validar()) {
            return "ERROR: El valor total ($" + valorCelebrar + ") no coincide con "
                    + "honorario mensual ($" + valorHonorarioMensual + ") × " + meses + " meses.";
        }

        contratos.put(idContrato, contrato);
        return "OK";
    }

    /**
     * Crea un nuevo contrato de Obra Pública en fase PUBLICACION.
     * El contratista se inicializa en null hasta la fase de ADJUDICACION.
     *
     * @param idContrato           Identificador único del contrato.
     * @param objetoContrato       Descripción del objeto del contrato.
     * @param documentoContratante Número de documento del contratante.
     * @param valorCelebrar        Valor total acordado.
     * @param plazoEjecucion       Fecha límite de ejecución.
     * @param ubicacionObra        Ubicación donde se realizará la obra.
     * @param areaIntervencion     Área de intervención en m².
     * @return Mensaje de resultado de la operación.
     */
    public String crearContratoObraPublica(String idContrato, String objetoContrato,
                                           String documentoContratante, double valorCelebrar,
                                           LocalDate plazoEjecucion, String ubicacionObra,
                                           double areaIntervencion) {
        Contratante contratante = resolverContratante(documentoContratante);
        if (contratante == null) {
            return "ERROR: El contratante con documento '" + documentoContratante + "' no existe en el sistema.";
        }
        if (existeIdContrato(idContrato)) {
            return "ERROR: Ya existe un contrato con el ID '" + idContrato + "'.";
        }

        Contrato contratoObraPublica = new ContratoObraPublica(
                idContrato, objetoContrato, LocalDate.now(),
                contratante, null,
                TipoContrato.CONTRATOOBRAPUBLICA, valorCelebrar, plazoEjecucion,
                FaseContrato.PUBLICACION, ubicacionObra, areaIntervencion);

        contratos.put(idContrato, contratoObraPublica);
        return "OK";
    }

    /**
     * Crea un nuevo contrato de Compraventa en fase PUBLICACION.
     * El valor total se calcula automáticamente como valorUnitario × cantidad.
     * El contratista se inicializa en null hasta la fase de ADJUDICACION.
     *
     * @param idContrato           Identificador único del contrato.
     * @param objetoContrato       Descripción del objeto del contrato.
     * @param documentoContratante Número de documento del contratante.
     * @param plazoEjecucion       Fecha límite de ejecución.
     * @param itemAdquirir         Nombre del ítem a adquirir.
     * @param marca                Marca del ítem.
     * @param modelo               Modelo del ítem.
     * @param serie                Serie del ítem.
     * @param valorUnitario        Valor unitario del ítem.
     * @param cantidadAdquirir     Cantidad de ítems.
     * @return Mensaje de resultado de la operación.
     */
    public String crearContratoCompraVenta(String idContrato, String objetoContrato,
                                           String documentoContratante, LocalDate plazoEjecucion,
                                           String itemAdquirir, String marca, String modelo,
                                           String serie, double valorUnitario, int cantidadAdquirir) {
        Contratante contratante = resolverContratante(documentoContratante);
        if (contratante == null) {
            return "ERROR: El contratante con documento '" + documentoContratante + "' no existe en el sistema.";
        }
        if (existeIdContrato(idContrato)) {
            return "ERROR: Ya existe un contrato con el ID '" + idContrato + "'.";
        }

        double valorCelebrar = valorUnitario * cantidadAdquirir;
        Contrato contratoCompraVenta = new ContratoCompraventa(
                idContrato, objetoContrato, LocalDate.now(),
                contratante, null,
                valorCelebrar, plazoEjecucion, FaseContrato.PUBLICACION,
                TipoContrato.CONTRATOCOMPRAVENTA,
                itemAdquirir, marca, modelo, serie, valorUnitario, cantidadAdquirir);

        contratos.put(idContrato, contratoCompraVenta);
        return "OK";
    }

    /**
     * Registra la postulación de un contratista a un contrato en fase PUBLICACION.
     * La fase pasa a LICITACION. El campo contratista del contrato se mantiene en null
     * (múltiples postulaciones se gestionan como lista interna).
     *
     * <ul>
     *   <li>Fase requerida en el contrato: PUBLICACION.</li>
     *   <li>Al postularse, la fase avanza a LICITACION.</li>
     *   <li>El contratista del contrato permanece null hasta ADJUDICACION.</li>
     * </ul>
     *
     * @param idContrato           ID del contrato al que el contratista desea postularse.
     * @param documentoContratista Documento del contratista interesado.
     * @return Mensaje de resultado de la operación.
     */
    public String postularContratista(String idContrato, String documentoContratista) {
        Contrato contrato = contratos.get(idContrato);
        if (contrato == null) {
            return "ERROR: El contrato con ID '" + idContrato + "' no existe.";
        }
        if (contrato.getFaseActual() != FaseContrato.PUBLICACION
                && contrato.getFaseActual() != FaseContrato.LICITACION) {
            return "ERROR: Solo se pueden recibir postulaciones en fase PUBLICACION o LICITACION. "
                    + "Fase actual: " + contrato.getFaseActual();
        }

        Contratista contratista = resolverContratista(documentoContratista);
        if (contratista == null) {
            return "ERROR: El contratista con documento '" + documentoContratista + "' no existe.";
        }

        if (contrato.getFaseActual() == FaseContrato.PUBLICACION) {
            contrato.setFaseActual(FaseContrato.LICITACION);
        }

        if (!postulaciones.containsKey(idContrato)) {
            postulaciones.put(idContrato, new ArrayList<>());
        }
        List<String> lista = postulaciones.get(idContrato);
        if (!lista.contains(documentoContratista)) {
            lista.add(documentoContratista);
        }
        return "OK: " + contratista.getNombre() + " postulado exitosamente. Fase → LICITACION.";
    }

    /**
     * Devuelve la lista de contratistas postulados a un contrato, formateada como texto legible.
     *
     * @param idContrato ID del contrato.
     * @return Texto con los postulados o mensaje de ausencia.
     */
    public String listarPostulados(String idContrato) {
        List<String> lista = postulaciones.get(idContrato);
        if (lista == null || lista.isEmpty()) {
            return "No hay contratistas postulados para el contrato '" + idContrato + "'.";
        }
        StringBuilder sb = new StringBuilder("Contratistas postulados al contrato " + idContrato + ":\n");
        for (int i = 0; i < lista.size(); i++) {
            String doc = lista.get(i);
            Contratista c = resolverContratista(doc);
            String nombre = (c != null) ? c.getNombre() : "(desconocido)";
            sb.append("  ").append(i + 1).append(". ").append(nombre)
                    .append(" — Doc: ").append(doc).append("\n");
        }
        return sb.toString();
    }

    /**
     * Adjudica formalmente el contrato a un contratista seleccionado de la lista de postulados.
     * La fase pasa a ADJUDICACION y el contratista queda asignado al contrato.
     *
     * @param idContrato           ID del contrato.
     * @param documentoContratista Documento del contratista a adjudicar.
     * @return Mensaje de resultado de la operación.
     */
    public String adjudicarContrato(String idContrato, String documentoContratista) {
        Contrato contrato = contratos.get(idContrato);
        if (contrato == null) {
            return "ERROR: El contrato con ID '" + idContrato + "' no existe.";
        }
        if (contrato.getFaseActual() != FaseContrato.LICITACION) {
            return "ERROR: Para adjudicar el contrato debe estar en fase LICITACION. "
                    + "Fase actual: " + contrato.getFaseActual();
        }

        List<String> postulados = postulaciones.get(idContrato);
        if (postulados == null || !postulados.contains(documentoContratista)) {
            return "ERROR: El contratista con documento '" + documentoContratista
                    + "' no está en la lista de postulados. Postule al contratista primero.";
        }

        Contratista contratista = resolverContratista(documentoContratista);
        if (contratista == null) {
            return "ERROR: El contratista con documento '" + documentoContratista + "' no existe en el sistema.";
        }

        contrato.setContratista(contratista);
        contrato.setFaseActual(FaseContrato.ADJUDICACION);
        return "OK: Contrato adjudicado a " + contratista.getNombre() + ". Fase → ADJUDICACION.";
    }

    /**
     * Avanza el contrato de ADJUDICACION a EJECUCION, habilitando el inicio de actividades.
     *
     * @param idContrato ID del contrato a iniciar.
     * @return Mensaje de resultado de la operación.
     */
    public String iniciarEjecucion(String idContrato) {
        Contrato contrato = contratos.get(idContrato);
        if (contrato == null) {
            return "ERROR: El contrato con ID '" + idContrato + "' no existe.";
        }
        if (contrato.getFaseActual() != FaseContrato.ADJUDICACION) {
            return "ERROR: Para iniciar ejecución el contrato debe estar en fase ADJUDICACION. "
                    + "Fase actual: " + contrato.getFaseActual();
        }
        if (contrato.getContratista() == null) {
            return "ERROR: No hay contratista adjudicado. Adjudique el contrato antes de iniciar ejecución.";
        }
        contrato.setFaseActual(FaseContrato.EJECUCION);
        return "OK: Ejecución iniciada. Actividades habilitadas para el contrato " + idContrato + ".";
    }

    /**
     * Cambia la fase de un contrato de forma genérica y genera el reporte de interventoría correspondiente.
     * Este método es utilizado por el Contratista para reportar avances durante EJECUCION.
     *
     * @param idContrato       ID del contrato.
     * @param nuevaFase        Nueva fase a asignar.
     * @param informe          Texto del informe del cambio.
     * @param servicioReportes Servicio de reportes donde se guardará el reporte generado.
     * @return Mensaje de resultado de la operación.
     */
    public String cambiarEstadoContrato(String idContrato, FaseContrato nuevaFase,
                                        String informe, ServicioReportes servicioReportes) {
        Contrato contrato = contratos.get(idContrato);
        if (contrato == null) {
            return "ERROR: El contrato con ID '" + idContrato + "' no existe.";
        }

        FaseContrato faseAnterior = contrato.getFaseActual();
        contrato.setFaseActual(nuevaFase);

        ReporteInterventoria reporte = new ReporteInterventoria(
                contrato, informe, LocalDateTime.now(), faseAnterior, nuevaFase);
        servicioReportes.guardarReporte(reporte);
        return "OK";
    }

    /**
     * Consulta la información de un contrato por su ID.
     *
     * @param idConsultar ID del contrato.
     * @return Texto formateado del contrato o mensaje de error.
     */
    public String consultarContrato(String idConsultar) {
        if (!existeIdContrato(idConsultar)) {
            return "No existe un contrato con el ID '" + idConsultar + "'.";
        }
        return contratos.get(idConsultar).mostrarInfo();
    }

    /**
     * Actualiza los atributos generales de un contrato.
     * Los parámetros nulos no modifican el campo correspondiente.
     *
     * @param idContrato     ID del contrato.
     * @param objetoContrato Nuevo objeto del contrato (null = sin cambio).
     * @param valorCelebrar  Nuevo valor (null = sin cambio).
     * @param plazoEjecucion Nuevo plazo (null = sin cambio).
     * @param faseActual     Nueva fase (null = sin cambio).
     */
    public void actualizarContratoGeneral(String idContrato, String objetoContrato,
                                          Double valorCelebrar, LocalDate plazoEjecucion,
                                          FaseContrato faseActual) {
        Contrato c = contratos.get(idContrato);
        if (c == null) return;

        if (c instanceof ContratoPrestacionServicios) {
            ContratoPrestacionServicios cps = (ContratoPrestacionServicios) c;
            double valorAValidar = (valorCelebrar != null) ? valorCelebrar : cps.getValorCelebrar();
            LocalDate plazoAValidar = (plazoEjecucion != null) ? plazoEjecucion : cps.getPlazoEjecucion();
            int nuevosMeses = calcularPlazoMeses(cps.getFechaCreacion(), plazoAValidar);
            if (valorAValidar != (cps.getValorHonorarioMensual() * nuevosMeses)) return;
            cps.setMesesContratoPrestacionServicios(nuevosMeses);
        }

        if (objetoContrato != null) c.setObjetoContrato(objetoContrato);
        if (valorCelebrar != null)  c.setValorCelebrar(valorCelebrar);
        if (plazoEjecucion != null) c.setPlazoEjecucion(plazoEjecucion);
        if (faseActual != null)     c.setFaseActual(faseActual);
    }

    /**
     * Actualiza atributos específicos de un contrato de Prestación de Servicios.
     *
     * @param idContrato            ID del contrato.
     * @param perfilRequerido       Nuevo perfil (null = sin cambio).
     * @param entregables           Nuevos entregables (null = sin cambio).
     * @param valorHonorarioMensual Nuevo honorario mensual (null = sin cambio).
     */
    public void actualizarContratoPrestacionServicios(String idContrato, String perfilRequerido,
                                                      String entregables, Double valorHonorarioMensual) {
        Contrato c = contratos.get(idContrato);
        if (!(c instanceof ContratoPrestacionServicios)) return;
        ContratoPrestacionServicios cps = (ContratoPrestacionServicios) c;
        if (perfilRequerido != null)       cps.setPerfilRequerido(perfilRequerido);
        if (entregables != null)           cps.setEntregables(entregables);
        if (valorHonorarioMensual != null) cps.setValorHonorarioMensual(valorHonorarioMensual);
    }

    /**
     * Actualiza atributos específicos de un contrato de Compraventa.
     *
     * @param idContrato       ID del contrato.
     * @param itemAdquirir     Nuevo ítem (null = sin cambio).
     * @param marca            Nueva marca (null = sin cambio).
     * @param modelo           Nuevo modelo (null = sin cambio).
     * @param serie            Nueva serie (null = sin cambio).
     * @param valorUnitario    Nuevo valor unitario (null = sin cambio).
     * @param cantidadAdquirir Nueva cantidad (null = sin cambio).
     */
    public void actualizarContratoCompraVenta(String idContrato, String itemAdquirir,
                                              String marca, String modelo, String serie,
                                              Double valorUnitario, Integer cantidadAdquirir) {
        Contrato c = contratos.get(idContrato);
        if (!(c instanceof ContratoCompraventa)) return;
        ContratoCompraventa cv = (ContratoCompraventa) c;
        if (itemAdquirir != null)     cv.setItemAdquirir(itemAdquirir);
        if (marca != null)            cv.setMarca(marca);
        if (modelo != null)           cv.setModelo(modelo);
        if (serie != null)            cv.setSerie(serie);
        if (valorUnitario != null)    cv.setValorUnitario(valorUnitario);
        if (cantidadAdquirir != null) cv.setCantidadAdquirir(cantidadAdquirir);
        cv.setValorCelebrar(cv.getValorUnitario() * cv.getCantidadAdquirir());
    }

    /**
     * Actualiza atributos específicos de un contrato de Obra Pública.
     *
     * @param idContrato       ID del contrato.
     * @param ubicacionObra    Nueva ubicación (null = sin cambio).
     * @param areaIntervencion Nueva área (null = sin cambio).
     */
    public void actualizarContratoObraPublica(String idContrato, String ubicacionObra,
                                              Double areaIntervencion) {
        Contrato c = contratos.get(idContrato);
        if (!(c instanceof ContratoObraPublica)) return;
        ContratoObraPublica op = (ContratoObraPublica) c;
        if (ubicacionObra != null)    op.setUbicacionObra(ubicacionObra);
        if (areaIntervencion != null) op.setAreaIntervencion(areaIntervencion);
    }

    /**
     * Elimina un contrato del sistema por su ID.
     *
     * @param idEliminar ID del contrato a eliminar.
     */
    public void eliminarContrato(String idEliminar) {
        contratos.remove(idEliminar);
        postulaciones.remove(idEliminar);
    }

    /**
     * Verifica si un contrato con el ID dado existe en el sistema.
     *
     * @param idContrato ID a verificar.
     * @return true si existe, false en caso contrario.
     */
    public boolean existeIdContrato(String idContrato) {
        return contratos.containsKey(idContrato);
    }

    /**
     * Devuelve la información de todos los contratos registrados.
     *
     * @return Texto formateado con todos los contratos.
     */
    public String obtenerTodosLosContratos() {
        if (contratos.isEmpty()) {
            return "No hay contratos registrados en el sistema.";
        }
        StringBuilder sb = new StringBuilder();
        for (Contrato contrato : contratos.values()) {
            sb.append(contrato.mostrarInfo()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Calcula el total de adquisición de un contrato de Compraventa.
     *
     * @param idContrato ID del contrato.
     * @return Total calculado, o 0.0 si no es Compraventa.
     */
    public double calcularTotalAdquisicion(String idContrato) {
        Contrato c = contratos.get(idContrato);
        if (c instanceof ContratoCompraventa) {
            ContratoCompraventa cv = (ContratoCompraventa) c;
            return cv.getValorUnitario() * cv.getCantidadAdquirir();
        }
        return 0.0;
    }

    /**
     * Resuelve internamente un {@link Contratante} dado su número de documento.
     * Nunca expone objetos de dominio hacia capas superiores.
     */
    private Contratante resolverContratante(String documento) {
        Usuario u = servicioUsuarios.obtenerUsuario(documento);
        return (u instanceof Contratante) ? (Contratante) u : null;
    }

    /**
     * Resuelve internamente un {@link Contratista} dado su número de documento.
     * Nunca expone objetos de dominio hacia capas superiores.
     */
    private Contratista resolverContratista(String documento) {
        Usuario u = servicioUsuarios.obtenerUsuario(documento);
        return (u instanceof Contratista) ? (Contratista) u : null;
    }

    /**
     * Calcula la cantidad de meses entre dos fechas.
     */
    private int calcularPlazoMeses(LocalDate inicio, LocalDate fin) {
        if (inicio != null && fin != null) {
            return (int) ChronoUnit.MONTHS.between(inicio, fin);
        }
        return 0;
    }
}
