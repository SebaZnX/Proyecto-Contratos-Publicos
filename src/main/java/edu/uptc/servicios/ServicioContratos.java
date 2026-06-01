package edu.uptc.servicios;

import edu.uptc.dominio.*;
import edu.uptc.enums.FaseContrato;
import edu.uptc.enums.TipoContrato;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

/**
 * Servicio que gestiona las operaciones relacionadas con los contratos en el sistema.
 * Incluye la creación, consulta, actualización, eliminación y validación de diferentes tipos de contratos.
 */
public class ServicioContratos {

    private HashMap<String, Contrato> contratos;

    /**
     * Constructor de la clase ServicioContratos.
     * Inicializa el mapa de contratos que almacena todos los contratos del sistema.
     */
    public ServicioContratos() {
        this.contratos = new HashMap<>();
    }

    /**
     * Crea un nuevo contrato de prestación de servicios y lo agrega al sistema.
     *
     * @param idContrato El identificador único del contrato.
     * @param objetoContrato La descripción del objeto del contrato.
     * @param contratante El objeto Contratante asociado al contrato.
     * @param valorCelebrar El valor total a celebrar del contrato.
     * @param plazoEjecucion La fecha de finalización del plazo de ejecución del contrato.
     * @param perfilRequerido El perfil profesional requerido para el contrato.
     * @param entregables Los entregables esperados del contrato.
     * @param valorHonorarioMensual El valor del honorario mensual para el contratista.
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

    /**
     * Crea un nuevo contrato de obra pública y lo agrega al sistema.
     *
     * @param idContrato El identificador único del contrato.
     * @param objetoContrato La descripción del objeto del contrato.
     * @param contratante El objeto Contratante asociado al contrato.
     * @param valorCelebrar El valor total a celebrar del contrato.
     * @param plazoEjecucion La fecha de finalización del plazo de ejecución del contrato.
     * @param ubicacionObra La ubicación donde se realizará la obra.
     * @param areaIntervencion El área de intervención de la obra en metros cuadrados.
     */
    public void crearContratoObraPublica(String idContrato, String objetoContrato,
                                         Contratante contratante, double valorCelebrar, LocalDate plazoEjecucion,
                                         String ubicacionObra, double areaIntervencion) {
        LocalDate fechaCreacion = LocalDate.now();

        Contrato contratoObraPublica = new ContratoObraPublica(idContrato, objetoContrato, fechaCreacion, contratante,
                null, TipoContrato.CONTRATOOBRAPUBLICA, valorCelebrar, plazoEjecucion, FaseContrato.PUBLICACION,
                ubicacionObra, areaIntervencion);
        agregarContrato(contratoObraPublica);
    }

    /**
     * Crea un nuevo contrato de compraventa y lo agrega al sistema.
     *
     * @param idContrato El identificador único del contrato.
     * @param objetoContrato La descripción del objeto del contrato.
     * @param contratante El objeto Contratante asociado al contrato.
     * @param plazoEjecucion La fecha de finalización del plazo de ejecución del contrato.
     * @param itemAdquirir El nombre del ítem a adquirir.
     * @param marca La marca del ítem a adquirir.
     * @param modelo El modelo del ítem a adquirir.
     * @param serie El número de serie del ítem a adquirir.
     * @param valorUnitario El valor unitario del ítem.
     * @param cantidadAdquirir La cantidad de ítems a adquirir.
     */
    public void crearContratoCompraVenta(String idContrato, String objetoContrato,
                                         Contratante contratante, LocalDate plazoEjecucion,
                                         String itemAdquirir, String marca, String modelo,
                                         String serie, double valorUnitario, int cantidadAdquirir) {
        LocalDate fechaCreacion = LocalDate.now();
        TipoContrato tipoContrato = TipoContrato.CONTRATOCOMPRAVENTA;
        double valorCelebrar = valorUnitario * cantidadAdquirir;

        Contrato contratoCompraVenta = new ContratoCompraventa(
                idContrato, objetoContrato, fechaCreacion, contratante, null, valorCelebrar,
                plazoEjecucion, FaseContrato.PUBLICACION, tipoContrato, itemAdquirir, marca,
                modelo, serie, valorUnitario, cantidadAdquirir
        );
        agregarContrato(contratoCompraVenta);
    }

    /**
     * Consulta la información de un contrato específico por su identificador.
     *
     * @param idConsultar El identificador del contrato a consultar.
     * @return Una cadena de texto con la información del contrato si existe, o un mensaje indicando que no existe.
     */
    public String consultarContrato(String idConsultar) {
        if (!existeIdContrato(idConsultar)) {
            return "No existe ese contrato";
        } else {
            Contrato contratoAux = this.contratos.get(idConsultar);
            return contratoAux.mostrarInfo();
        }
    }

    /**
     * Actualiza los atributos generales de un contrato existente.
     * Los parámetros nulos indican que el atributo correspondiente no debe ser modificado.
     *
     * @param idContrato El identificador del contrato a actualizar.
     * @param objetoContrato La nueva descripción del objeto del contrato (puede ser null).
     * @param valorCelebrar El nuevo valor a celebrar del contrato (puede ser null).
     * @param plazoEjecucion La nueva fecha de finalización del plazo de ejecución (puede ser null).
     * @param faseActual La nueva fase actual del contrato (puede ser null).
     */
    public void actualizarContratoGeneral(String idContrato, String objetoContrato, Double valorCelebrar,
                                          LocalDate plazoEjecucion, FaseContrato faseActual) {
        Contrato contratoEncontrado = this.contratos.get(idContrato);

        if (contratoEncontrado != null) {
            Double valorAValidar = (valorCelebrar != null) ? valorCelebrar : contratoEncontrado.getValorCelebrar();
            LocalDate plazoAValidar = (plazoEjecucion != null) ? plazoEjecucion : contratoEncontrado.getPlazoEjecucion();

            boolean actualizacionValida = true;

            if (contratoEncontrado instanceof ContratoPrestacionServicios) {
                ContratoPrestacionServicios cps = (ContratoPrestacionServicios) contratoEncontrado;
                int nuevosMeses = calcularPlazoMeses(cps.getFechaCreacion(), plazoAValidar);

                if (valorAValidar == (cps.getValorHonorarioMensual() * nuevosMeses)) {
                    cps.setMesesContratoPrestacionServicios(nuevosMeses);
                } else {
                    actualizacionValida = false;
                }
            }

            if (actualizacionValida) {
                if (objetoContrato != null) {
                    contratoEncontrado.setObjetoContrato(objetoContrato);
                }
                if (valorCelebrar != null) {
                    contratoEncontrado.setValorCelebrar(valorCelebrar);
                }
                if (plazoEjecucion != null) {
                    contratoEncontrado.setPlazoEjecucion(plazoEjecucion);
                }
                if (faseActual != null) {
                    contratoEncontrado.setFaseActual(faseActual);
                }
            }
        }
    }

    /**
     * Actualiza los atributos específicos de un contrato de prestación de servicios.
     * Los parámetros nulos indican que el atributo correspondiente no debe ser modificado.
     *
     * @param idContrato El identificador del contrato de prestación de servicios a actualizar.
     * @param perfilRequerido El nuevo perfil requerido (puede ser null).
     * @param entregables Los nuevos entregables (puede ser null).
     * @param valorHonorarioMensual El nuevo valor del honorario mensual (puede ser null).
     */
    public void actualizarContratoPrestacionServicios(String idContrato, String perfilRequerido, String entregables,
                                                      Double valorHonorarioMensual) {
        Contrato contratoEncontrado = this.contratos.get(idContrato);

        if (contratoEncontrado != null && contratoEncontrado instanceof ContratoPrestacionServicios) {
            ContratoPrestacionServicios contrato = (ContratoPrestacionServicios) contratoEncontrado;

            if (perfilRequerido != null) {
                contrato.setPerfilRequerido(perfilRequerido);
            }
            if (entregables != null) {
                contrato.setEntregables(entregables);
            }
            if (valorHonorarioMensual != null) {
                contrato.setValorHonorarioMensual(valorHonorarioMensual);
            }
        }
    }

    /**
     * Actualiza los atributos específicos de un contrato de compraventa.
     * Los parámetros nulos indican que el atributo correspondiente no debe ser modificado.
     *
     * @param idContrato El identificador del contrato de compraventa a actualizar.
     * @param itemAdquirir El nuevo nombre del ítem a adquirir (puede ser null).
     * @param marca La nueva marca del ítem (puede ser null).
     * @param modelo El nuevo modelo del ítem (puede ser null).
     * @param serie El nuevo número de serie del ítem (puede ser null).
     * @param valorUnitario El nuevo valor unitario del ítem (puede ser null).
     * @param cantidadAdquirir La nueva cantidad de ítems a adquirir (puede ser null).
     */
    public void actualizarContratoCompraVenta(String idContrato, String itemAdquirir, String marca, String modelo, String serie, Double valorUnitario, Integer cantidadAdquirir) {
        Contrato contratoEncontrado = this.contratos.get(idContrato);

        if (contratoEncontrado != null && contratoEncontrado instanceof ContratoCompraventa) {
            ContratoCompraventa contrato = (ContratoCompraventa) contratoEncontrado;

            if (itemAdquirir != null) {
                contrato.setItemAdquirir(itemAdquirir);
            }
            if (marca != null) {
                contrato.setMarca(marca);
            }
            if (modelo != null) {
                contrato.setModelo(modelo);
            }
            if (serie != null) {
                contrato.setSerie(serie);
            }
            if (valorUnitario != null) {
                contrato.setValorUnitario(valorUnitario);
            }
            if (cantidadAdquirir != null) {
                contrato.setCantidadAdquirir(cantidadAdquirir);
            }
            contrato.setValorCelebrar(contrato.getValorUnitario() * contrato.getCantidadAdquirir());
        }
    }

    /**
     * Actualiza los atributos específicos de un contrato de obra pública.
     * Los parámetros nulos indican que el atributo correspondiente no debe ser modificado.
     *
     * @param idContrato El identificador del contrato de obra pública a actualizar.
     * @param ubicacionObra La nueva ubicación de la obra (puede ser null).
     * @param areaIntervencion La nueva área de intervención (puede ser null).
     */
    public void actualizarContratoObraPublica(String idContrato, String ubicacionObra, Double areaIntervencion) {
        Contrato contratoEncontrado = this.contratos.get(idContrato);

        if (contratoEncontrado != null && contratoEncontrado instanceof ContratoObraPublica) {
            ContratoObraPublica contrato = (ContratoObraPublica) contratoEncontrado;

            if (ubicacionObra != null) {
                contrato.setUbicacionObra(ubicacionObra);
            }
            if (areaIntervencion != null) {
                contrato.setAreaIntervencion(areaIntervencion);
            }
        }
    }

    /**
     * Elimina un contrato del sistema utilizando su identificador.
     *
     * @param idEliminar El identificador del contrato a eliminar.
     */
    public void eliminarContrato(String idEliminar) {
        this.contratos.remove(idEliminar);
    }

    /**
     * Agrega un nuevo contrato al mapa de contratos del sistema.
     *
     * @param contrato El objeto Contrato a agregar.
     */
    public void agregarContrato(Contrato contrato) {
        this.contratos.put(contrato.getIdContrato(), contrato);
    }

    /**
     * Asigna un contratista a un contrato específico y cambia su fase a ADJUDICACION.
     *
     * @param idContrato El identificador del contrato al que se asignará el contratista.
     * @param contratistaAsignar El objeto Contratista a asignar.
     */
    public void asignarContrato(String idContrato, Contratista contratistaAsignar) {
        Contrato contratoEncontrado = this.contratos.get(idContrato);

        if (contratoEncontrado != null) {
            contratoEncontrado.setContratista(contratistaAsignar);
            contratoEncontrado.setFaseActual(FaseContrato.ADJUDICACION);
        }
    }

    /**
     * Selecciona un contratista para un contrato, actualizando el contratista asociado
     * y cambiando la fase del contrato a LICITACION.
     *
     * @param idContrato El identificador del contrato a seleccionar.
     * @param documentoContratista El documento de identificación del contratista interesado.
     * @param servicioUsuarios El servicio de usuarios para obtener la información del contratista.
     */
    public void seleccionarContrato(String idContrato, String documentoContratista, ServicioUsuarios servicioUsuarios) {
        Contrato contratoASeleccionar = this.contratos.get(idContrato);
        Usuario contratistaInteresado = servicioUsuarios.obtenerUsuario(documentoContratista);

        if (contratoASeleccionar != null && contratistaInteresado instanceof Contratista) {
            contratoASeleccionar.setContratista((Contratista) contratistaInteresado);
            contratoASeleccionar.setFaseActual(FaseContrato.LICITACION);
        }
    }

    /**
     * Cambia el estado (fase) de un contrato y genera un reporte de interventoría.
     *
     * @param idContrato El identificador del contrato cuyo estado se va a cambiar.
     * @param nuevaFase La nueva fase a la que se actualizará el contrato.
     * @param informe El informe asociado al cambio de fase.
     * @param servicioReportes El servicio de reportes para guardar el nuevo reporte de interventoría.
     */
    public void cambiarEstadoContrato(String idContrato, FaseContrato nuevaFase, String informe, ServicioReportes servicioReportes) {
        Contrato contratoEncontrado = this.contratos.get(idContrato);

        if (contratoEncontrado != null) {
            FaseContrato faseAnterior = contratoEncontrado.getFaseActual();
            contratoEncontrado.setFaseActual(nuevaFase);

            ReporteInterventoria nuevoReporte = new ReporteInterventoria(
                    contratoEncontrado,
                    informe,
                    java.time.LocalDateTime.now(),
                    faseAnterior,
                    nuevaFase
            );

            servicioReportes.guardarReporte(nuevoReporte);
        }
    }

    /**
     * Valida un contrato de prestación de servicios.
     * Este método delega la validación a la lógica interna del objeto ContratoPrestacionServicios.
     *
     * @param contratoPrestacionServicios El contrato de prestación de servicios a validar.
     * @return true si el contrato es válido, false en caso contrario.
     */
    public boolean validarContratoPrestacionServicios(ContratoPrestacionServicios contratoPrestacionServicios) {
        return contratoPrestacionServicios.validar();
    }

    /**
     * Valida un contrato de obra pública.
     * Este método delega la validación a la lógica interna del objeto ContratoObraPublica.
     *
     * @param contratoObraPublica El contrato de obra pública a validar.
     * @return true si el contrato es válido, false en caso contrario.
     */
    public boolean validarContratoObraPublica(ContratoObraPublica contratoObraPublica) {
        return contratoObraPublica.validar();
    }

    /**
     * Verifica si un identificador de contrato ya existe en el sistema.
     *
     * @param idExiste El identificador del contrato a verificar.
     * @return true si el identificador existe, false en caso contrario.
     */
    public boolean existeIdContrato(String idExiste) {
        return this.contratos.containsKey(idExiste);
    }

    /**
     * Calcula el plazo de ejecución de un contrato en meses, basándose en la fecha de creación y la fecha de plazo de ejecución.
     *
     * @param fechaCreacion La fecha de creación del contrato.
     * @param fechaPlazoEjecucion La fecha de finalización del plazo de ejecución del contrato.
     * @return El número de meses entre la fecha de creación y la fecha de plazo de ejecución, o 0 si alguna fecha es nula.
     */
    public int calcularPlazoMeses(LocalDate fechaCreacion, LocalDate fechaPlazoEjecucion) {
        if (fechaCreacion != null && fechaPlazoEjecucion != null) {
            long meses = ChronoUnit.MONTHS.between(fechaCreacion, fechaPlazoEjecucion);
            return (int) meses;
        }
        return 0;
    }

    /**
     * Calcula el valor total de adquisición para un contrato de compraventa.
     *
     * @param idContrato El identificador del contrato.
     * @return El valor total de adquisición si el contrato es de compraventa, de lo contrario 0.0.
     */
    public double calcularTotalAdquisicion(String idContrato) {
        Contrato contratoEncontrado = this.contratos.get(idContrato);

        if (contratoEncontrado instanceof ContratoCompraventa) {
            ContratoCompraventa compraventa = (ContratoCompraventa) contratoEncontrado;
            return compraventa.getValorUnitario() * compraventa.getCantidadAdquirir();
        }
        return 0.0;
    }

    public String obtenerTodosLosContratos() {
        if (this.contratos.isEmpty()) {
            return "No hay contratos registrados en el sistema.";
        }
        StringBuilder sb = new StringBuilder();
        for (Contrato contrato : this.contratos.values()) {
            sb.append(contrato.mostrarInfo()).append("\n");
        }
        return sb.toString();
    }
}
