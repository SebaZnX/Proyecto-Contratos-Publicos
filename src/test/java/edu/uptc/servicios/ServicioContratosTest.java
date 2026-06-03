package edu.uptc.servicios;

import edu.uptc.dominio.Contratante;
import edu.uptc.dominio.Contratista;
import edu.uptc.dominio.ReporteInterventoria;
import edu.uptc.dominio.Usuario;
import edu.uptc.enums.FaseContrato;
import edu.uptc.enums.Rol;
import edu.uptc.enums.TipoDocumento;
import edu.uptc.enums.TipoPersona;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para {@link ServicioContratos}.
 *
 * Se usan stubs manuales (clases internas) en lugar de Mockito
 * para simular ServicioUsuarios y ServicioReportes.
 */
class ServicioContratosTest {

    /**
     * Stub de ServicioUsuarios que devuelve el usuario según el documento
     * configurado previamente en un mapa.
     */
    static class ServicioUsuariosStub extends ServicioUsuarios {
        private final HashMap<String, Usuario> usuarios = new HashMap<>();

        void registrar(String documento, Usuario usuario) {
            usuarios.put(documento, usuario);
        }

        @Override
        public Usuario obtenerUsuario(String documento) {
            return usuarios.get(documento);
        }
    }

    /**
     * Stub de ServicioReportes que guarda los reportes en memoria
     * para poder verificarlos después.
     */
    static class ServicioReportesStub extends ServicioReportes {
        private final List<ReporteInterventoria> reportesGuardados = new ArrayList<>();

        @Override
        public void guardarReporte(ReporteInterventoria reporte) {
            reportesGuardados.add(reporte);
        }

        List<ReporteInterventoria> getReportes() {
            return reportesGuardados;
        }
    }

    private ServicioUsuariosStub servicioUsuarios;
    private ServicioReportesStub servicioReportes;
    private ServicioContratos servicio;

    private Contratante contratante;
    private Contratista contratista;

    private static final String DOC_CONTRATANTE = "111111111";
    private static final String DOC_CONTRATISTA = "222222222";
    private static final String ID_CONTRATO     = "CON-001";
    private static final LocalDate PLAZO_FUTURO = LocalDate.now().plusMonths(6);

    @BeforeEach
    void setUp() {
        servicioUsuarios = new ServicioUsuariosStub();
        servicioReportes = new ServicioReportesStub();
        servicio = new ServicioContratos(servicioUsuarios);

        // Crear objetos de dominio reales
        contratante = new Contratante(
                TipoPersona.JURIDICA, TipoDocumento.NIT, DOC_CONTRATANTE,
                "Entidad Pública", "entidad@gov.co", "pass123",
                "3001112233", "Calle 1", "Sogamoso", Rol.CONTRATANTE,
                "Público", "Municipal", "CUE-001");

        contratista = new Contratista(
                TipoPersona.NATURAL, TipoDocumento.CC, DOC_CONTRATISTA,
                "Juan Pérez", "juan@email.com", "pass456",
                "3009998877", "Carrera 5", "Tunja", Rol.CONTRATISTA,
                false, "Ingeniería de Sistemas");
    }

    @Nested
    @DisplayName("crearContratoPrestacionServicios")
    class CrearPrestacionServicios {

        @Test
        @DisplayName("Retorna OK cuando los datos son válidos y el valor coincide con honorario × meses")
        void crea_exitosamente() {
            servicioUsuarios.registrar(DOC_CONTRATANTE, contratante);

            // 6 meses × $500_000 = $3_000_000
            String resultado = servicio.crearContratoPrestacionServicios(
                    ID_CONTRATO, "Consultoría técnica",
                    DOC_CONTRATANTE, 3_000_000,
                    LocalDate.now().plusMonths(6),
                    "Ingeniero de sistemas", "Informe mensual", 500_000);

            assertEquals("OK", resultado);
            assertTrue(servicio.existeIdContrato(ID_CONTRATO));
        }

        @Test
        @DisplayName("Retorna ERROR cuando el contratante no existe en el sistema")
        void contratante_no_existe() {
            // No registramos contratante → obtenerUsuario devuelve null
            String resultado = servicio.crearContratoPrestacionServicios(
                    ID_CONTRATO, "Consultoría", DOC_CONTRATANTE,
                    3_000_000, PLAZO_FUTURO, "Perfil", "Entregable", 500_000);

            assertTrue(resultado.startsWith("ERROR"));
            assertFalse(servicio.existeIdContrato(ID_CONTRATO));
        }

        @Test
        @DisplayName("Retorna ERROR cuando ya existe un contrato con el mismo ID")
        void id_duplicado() {
            servicioUsuarios.registrar(DOC_CONTRATANTE, contratante);

            servicio.crearContratoPrestacionServicios(
                    ID_CONTRATO, "Consultoría", DOC_CONTRATANTE,
                    3_000_000, LocalDate.now().plusMonths(6), "Perfil", "Entregable", 500_000);

            String resultado = servicio.crearContratoPrestacionServicios(
                    ID_CONTRATO, "Otro objeto", DOC_CONTRATANTE,
                    3_000_000, LocalDate.now().plusMonths(6), "Perfil", "Entregable", 500_000);

            assertTrue(resultado.startsWith("ERROR"));
        }

        @Test
        @DisplayName("Retorna ERROR cuando el valor total no coincide con honorario × meses")
        void valor_no_coincide() {
            servicioUsuarios.registrar(DOC_CONTRATANTE, contratante);

            // 6 meses × $500_000 = $3_000_000, pero se pasa $999
            String resultado = servicio.crearContratoPrestacionServicios(
                    ID_CONTRATO, "Consultoría", DOC_CONTRATANTE,
                    999, LocalDate.now().plusMonths(6),
                    "Perfil", "Entregable", 500_000);

            assertTrue(resultado.startsWith("ERROR"));
            assertFalse(servicio.existeIdContrato(ID_CONTRATO));
        }
    }

    @Nested
    @DisplayName("crearContratoObraPublica")
    class CrearObraPublica {

        @Test
        @DisplayName("Retorna OK con datos válidos")
        void crea_exitosamente() {
            servicioUsuarios.registrar(DOC_CONTRATANTE, contratante);

            String resultado = servicio.crearContratoObraPublica(
                    ID_CONTRATO, "Pavimentación vía",
                    DOC_CONTRATANTE, 50_000_000, PLAZO_FUTURO,
                    "Carretera 4 Norte", 1200.0);

            assertEquals("OK", resultado);
            assertTrue(servicio.existeIdContrato(ID_CONTRATO));
        }

        @Test
        @DisplayName("Retorna ERROR cuando el contratante no existe")
        void contratante_inexistente() {
            String resultado = servicio.crearContratoObraPublica(
                    ID_CONTRATO, "Obra", DOC_CONTRATANTE,
                    50_000_000, PLAZO_FUTURO, "Ubicación", 500.0);

            assertTrue(resultado.startsWith("ERROR"));
        }

        @Test
        @DisplayName("Retorna ERROR cuando el ID ya está registrado")
        void id_duplicado() {
            servicioUsuarios.registrar(DOC_CONTRATANTE, contratante);

            servicio.crearContratoObraPublica(
                    ID_CONTRATO, "Obra", DOC_CONTRATANTE,
                    50_000_000, PLAZO_FUTURO, "Ubicación", 500.0);

            String resultado = servicio.crearContratoObraPublica(
                    ID_CONTRATO, "Otra obra", DOC_CONTRATANTE,
                    10_000_000, PLAZO_FUTURO, "Otra ubicación", 200.0);

            assertTrue(resultado.startsWith("ERROR"));
        }
    }

    @Nested
    @DisplayName("crearContratoCompraVenta")
    class CrearCompraVenta {

        @Test
        @DisplayName("Retorna OK y calcula valorCelebrar = valorUnitario × cantidad")
        void crea_exitosamente() {
            servicioUsuarios.registrar(DOC_CONTRATANTE, contratante);

            String resultado = servicio.crearContratoCompraVenta(
                    ID_CONTRATO, "Compra de equipos",
                    DOC_CONTRATANTE, PLAZO_FUTURO,
                    "Laptop", "Dell", "Latitude 5520", "SN-001",
                    2_500_000, 10);

            assertEquals("OK", resultado);
            assertTrue(servicio.existeIdContrato(ID_CONTRATO));
            assertEquals(25_000_000.0, servicio.calcularTotalAdquisicion(ID_CONTRATO));
        }

        @Test
        @DisplayName("Retorna ERROR cuando el contratante no existe")
        void contratante_inexistente() {
            String resultado = servicio.crearContratoCompraVenta(
                    ID_CONTRATO, "Compra", DOC_CONTRATANTE,
                    PLAZO_FUTURO, "Item", "Marca", "Modelo", "Serie",
                    100_000, 5);

            assertTrue(resultado.startsWith("ERROR"));
        }
    }

    @Nested
    @DisplayName("postularContratista")
    class PostularContratista {

        @BeforeEach
        void crearContratoBase() {
            servicioUsuarios.registrar(DOC_CONTRATANTE, contratante);
            servicio.crearContratoObraPublica(
                    ID_CONTRATO, "Obra test", DOC_CONTRATANTE,
                    1_000_000, PLAZO_FUTURO, "Sogamoso", 300.0);
        }

        @Test
        @DisplayName("Postulación exitosa retorna OK y avanza fase a LICITACION")
        void postulacion_exitosa() {
            servicioUsuarios.registrar(DOC_CONTRATISTA, contratista);

            String resultado = servicio.postularContratista(ID_CONTRATO, DOC_CONTRATISTA);

            assertTrue(resultado.startsWith("OK"));
        }

        @Test
        @DisplayName("Retorna ERROR cuando el contrato no existe")
        void contrato_inexistente() {
            String resultado = servicio.postularContratista("INEXISTENTE", DOC_CONTRATISTA);
            assertTrue(resultado.startsWith("ERROR"));
        }

        @Test
        @DisplayName("Retorna ERROR cuando el contratista no está registrado")
        void contratista_no_registrado() {
            // DOC_CONTRATISTA no registrado en el stub
            String resultado = servicio.postularContratista(ID_CONTRATO, DOC_CONTRATISTA);
            assertTrue(resultado.startsWith("ERROR"));
        }

        @Test
        @DisplayName("Retorna ERROR si el contrato ya pasó de fase LICITACION")
        void fase_incorrecta() {
            servicioUsuarios.registrar(DOC_CONTRATISTA, contratista);
            servicio.postularContratista(ID_CONTRATO, DOC_CONTRATISTA);   // → LICITACION
            servicio.adjudicarContrato(ID_CONTRATO, DOC_CONTRATISTA);     // → ADJUDICACION

            Contratista otro = new Contratista(
                    TipoPersona.NATURAL, TipoDocumento.CC, "333333333",
                    "Otro Contratista", "otro@email.com", "pass789",
                    "3001230000", "Calle 10", "Duitama", Rol.CONTRATISTA,
                    false, "Arquitectura");
            servicioUsuarios.registrar("333333333", otro);

            String resultado = servicio.postularContratista(ID_CONTRATO, "333333333");
            assertTrue(resultado.startsWith("ERROR"));
        }
    }

    @Nested
    @DisplayName("adjudicarContrato")
    class AdjudicarContrato {

        @BeforeEach
        void prepararContratoEnLicitacion() {
            servicioUsuarios.registrar(DOC_CONTRATANTE, contratante);
            servicio.crearContratoObraPublica(
                    ID_CONTRATO, "Obra", DOC_CONTRATANTE,
                    1_000_000, PLAZO_FUTURO, "Boyacá", 200.0);

            servicioUsuarios.registrar(DOC_CONTRATISTA, contratista);
            servicio.postularContratista(ID_CONTRATO, DOC_CONTRATISTA);
        }

        @Test
        @DisplayName("Adjudicación exitosa retorna OK")
        void adjudicacion_exitosa() {
            String resultado = servicio.adjudicarContrato(ID_CONTRATO, DOC_CONTRATISTA);
            assertTrue(resultado.startsWith("OK"));
        }

        @Test
        @DisplayName("Retorna ERROR si el contrato no existe")
        void contrato_inexistente() {
            String resultado = servicio.adjudicarContrato("INEXISTENTE", DOC_CONTRATISTA);
            assertTrue(resultado.startsWith("ERROR"));
        }

        @Test
        @DisplayName("Retorna ERROR si el contratista no está en la lista de postulados")
        void contratista_no_postulado() {
            String resultado = servicio.adjudicarContrato(ID_CONTRATO, "999999999");
            assertTrue(resultado.startsWith("ERROR"));
        }

        @Test
        @DisplayName("Retorna ERROR si la fase no es LICITACION")
        void fase_incorrecta() {
            servicio.adjudicarContrato(ID_CONTRATO, DOC_CONTRATISTA); // → ADJUDICACION
            String resultado = servicio.adjudicarContrato(ID_CONTRATO, DOC_CONTRATISTA);
            assertTrue(resultado.startsWith("ERROR"));
        }
    }

    @Nested
    @DisplayName("iniciarEjecucion")
    class IniciarEjecucion {

        @BeforeEach
        void prepararContratoAdjudicado() {
            servicioUsuarios.registrar(DOC_CONTRATANTE, contratante);
            servicio.crearContratoObraPublica(
                    ID_CONTRATO, "Obra", DOC_CONTRATANTE,
                    1_000_000, PLAZO_FUTURO, "Tunja", 100.0);

            servicioUsuarios.registrar(DOC_CONTRATISTA, contratista);
            servicio.postularContratista(ID_CONTRATO, DOC_CONTRATISTA);
            servicio.adjudicarContrato(ID_CONTRATO, DOC_CONTRATISTA);
        }

        @Test
        @DisplayName("Inicio de ejecución exitoso desde fase ADJUDICACION")
        void inicio_exitoso() {
            String resultado = servicio.iniciarEjecucion(ID_CONTRATO);
            assertTrue(resultado.startsWith("OK"));
        }

        @Test
        @DisplayName("Retorna ERROR cuando el contrato no existe")
        void contrato_inexistente() {
            String resultado = servicio.iniciarEjecucion("INEXISTENTE");
            assertTrue(resultado.startsWith("ERROR"));
        }

        @Test
        @DisplayName("Retorna ERROR si el contrato no está en fase ADJUDICACION")
        void fase_incorrecta() {
            servicio.iniciarEjecucion(ID_CONTRATO); // → EJECUCION
            String resultado = servicio.iniciarEjecucion(ID_CONTRATO);
            assertTrue(resultado.startsWith("ERROR"));
        }
    }

    @Nested
    @DisplayName("cambiarEstadoContrato")
    class CambiarEstadoContrato {

        @Test
        @DisplayName("Retorna OK y guarda exactamente un reporte de interventoría")
        void cambia_estado_y_guarda_reporte() {
            servicioUsuarios.registrar(DOC_CONTRATANTE, contratante);
            servicio.crearContratoObraPublica(
                    ID_CONTRATO, "Obra", DOC_CONTRATANTE,
                    1_000_000, PLAZO_FUTURO, "Duitama", 100.0);

            String resultado = servicio.cambiarEstadoContrato(
                    ID_CONTRATO, FaseContrato.LICITACION, "Avance inicial", servicioReportes);

            assertEquals("OK", resultado);
            assertEquals(1, servicioReportes.getReportes().size());
            assertNotNull(servicioReportes.getReportes().get(0));
        }

        @Test
        @DisplayName("Retorna ERROR cuando el contrato no existe y no guarda reporte")
        void contrato_inexistente() {
            String resultado = servicio.cambiarEstadoContrato(
                    "INEXISTENTE", FaseContrato.EJECUCION, "Informe", servicioReportes);

            assertTrue(resultado.startsWith("ERROR"));
            assertEquals(0, servicioReportes.getReportes().size());
        }
    }

    @Nested
    @DisplayName("eliminarContrato")
    class EliminarContrato {

        @Test
        @DisplayName("Elimina el contrato correctamente")
        void elimina_existente() {
            servicioUsuarios.registrar(DOC_CONTRATANTE, contratante);
            servicio.crearContratoObraPublica(
                    ID_CONTRATO, "Obra", DOC_CONTRATANTE,
                    500_000, PLAZO_FUTURO, "Paipa", 80.0);

            assertTrue(servicio.existeIdContrato(ID_CONTRATO));
            servicio.eliminarContrato(ID_CONTRATO);
            assertFalse(servicio.existeIdContrato(ID_CONTRATO));
        }

        @Test
        @DisplayName("No lanza excepción al eliminar un ID inexistente")
        void eliminar_inexistente_no_falla() {
            assertDoesNotThrow(() -> servicio.eliminarContrato("NO_EXISTE"));
        }
    }

    @Nested
    @DisplayName("Consultas")
    class Consultas {

        @Test
        @DisplayName("consultarContrato retorna mensaje de error si el contrato no existe")
        void consultar_inexistente() {
            String resultado = servicio.consultarContrato("FAKE");
            assertTrue(resultado.contains("No existe"));
        }

        @Test
        @DisplayName("obtenerTodosLosContratos retorna mensaje cuando no hay contratos")
        void sin_contratos_registrados() {
            String resultado = servicio.obtenerTodosLosContratos();
            assertTrue(resultado.contains("No hay contratos"));
        }

        @Test
        @DisplayName("obtenerTodosLosContratos retorna contenido cuando hay contratos")
        void lista_contratos() {
            servicioUsuarios.registrar(DOC_CONTRATANTE, contratante);
            servicio.crearContratoObraPublica(
                    ID_CONTRATO, "Obra test", DOC_CONTRATANTE,
                    1_000_000, PLAZO_FUTURO, "Sogamoso", 100.0);

            String resultado = servicio.obtenerTodosLosContratos();
            assertFalse(resultado.isBlank());
            assertFalse(resultado.contains("No hay contratos"));
        }
    }

    @Nested
    @DisplayName("calcularTotalAdquisicion")
    class CalcularTotalAdquisicion {

        @Test
        @DisplayName("Retorna valorUnitario × cantidad para ContratoCompraventa")
        void calculo_correcto() {
            servicioUsuarios.registrar(DOC_CONTRATANTE, contratante);
            servicio.crearContratoCompraVenta(
                    ID_CONTRATO, "Compra sillas", DOC_CONTRATANTE, PLAZO_FUTURO,
                    "Silla ergonómica", "Marca X", "Mod A", "S-01",
                    300_000, 20);

            assertEquals(6_000_000.0, servicio.calcularTotalAdquisicion(ID_CONTRATO));
        }

        @Test
        @DisplayName("Retorna 0.0 si el contrato no es Compraventa")
        void no_es_compraventa() {
            servicioUsuarios.registrar(DOC_CONTRATANTE, contratante);
            servicio.crearContratoObraPublica(
                    ID_CONTRATO, "Obra", DOC_CONTRATANTE,
                    1_000_000, PLAZO_FUTURO, "Ubicación", 100.0);

            assertEquals(0.0, servicio.calcularTotalAdquisicion(ID_CONTRATO));
        }

        @Test
        @DisplayName("Retorna 0.0 si el contrato no existe")
        void contrato_inexistente() {
            assertEquals(0.0, servicio.calcularTotalAdquisicion("NO_EXISTE"));
        }
    }

    @Nested
    @DisplayName("actualizarContratoCompraVenta")
    class ActualizarCompraVenta {

        @Test
        @DisplayName("Actualiza campos y recalcula valorCelebrar correctamente")
        void actualiza_correctamente() {
            servicioUsuarios.registrar(DOC_CONTRATANTE, contratante);
            servicio.crearContratoCompraVenta(
                    ID_CONTRATO, "Compra PCs", DOC_CONTRATANTE, PLAZO_FUTURO,
                    "PC", "HP", "ProBook", "SN-X", 2_000_000, 5);

            servicio.actualizarContratoCompraVenta(
                    ID_CONTRATO, null, null, null, null, 1_500_000.0, 8);

            assertEquals(12_000_000.0, servicio.calcularTotalAdquisicion(ID_CONTRATO));
        }

        @Test
        @DisplayName("No lanza excepción si el contrato no es Compraventa")
        void no_es_compraventa_no_falla() {
            servicioUsuarios.registrar(DOC_CONTRATANTE, contratante);
            servicio.crearContratoObraPublica(
                    ID_CONTRATO, "Obra", DOC_CONTRATANTE,
                    500_000, PLAZO_FUTURO, "Ubicación", 100.0);

            assertDoesNotThrow(() -> servicio.actualizarContratoCompraVenta(
                    ID_CONTRATO, "Item", "Marca", "Modelo", "Serie", 100.0, 2));
        }
    }

    @Nested
    @DisplayName("listarPostulados")
    class ListarPostulados {

        @Test
        @DisplayName("Retorna mensaje cuando no hay postulados")
        void sin_postulados() {
            servicioUsuarios.registrar(DOC_CONTRATANTE, contratante);
            servicio.crearContratoObraPublica(
                    ID_CONTRATO, "Obra", DOC_CONTRATANTE,
                    1_000_000, PLAZO_FUTURO, "Boyacá", 100.0);

            String resultado = servicio.listarPostulados(ID_CONTRATO);
            assertTrue(resultado.contains("No hay contratistas postulados"));
        }

        @Test
        @DisplayName("Lista el contratista postulado con su nombre")
        void con_postulados() {
            servicioUsuarios.registrar(DOC_CONTRATANTE, contratante);
            servicio.crearContratoObraPublica(
                    ID_CONTRATO, "Obra", DOC_CONTRATANTE,
                    1_000_000, PLAZO_FUTURO, "Boyacá", 100.0);

            servicioUsuarios.registrar(DOC_CONTRATISTA, contratista);
            servicio.postularContratista(ID_CONTRATO, DOC_CONTRATISTA);

            String resultado = servicio.listarPostulados(ID_CONTRATO);
            assertTrue(resultado.contains("Juan Pérez"));
        }
    }
}