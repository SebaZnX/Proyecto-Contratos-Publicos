package edu.uptc.servicios;

import edu.uptc.dominio.Contratante;
import edu.uptc.dominio.Contratista;
import edu.uptc.dominio.Contrato;
import edu.uptc.dominio.ContratoObraPublica;
import edu.uptc.dominio.ReporteInterventoria;
import edu.uptc.enums.FaseContrato;
import edu.uptc.enums.Rol;
import edu.uptc.enums.TipoContrato;
import edu.uptc.enums.TipoDocumento;
import edu.uptc.enums.TipoPersona;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para {@link ServicioReportes}.
 * Se usan objetos de dominio reales para construir los reportes,
 * sin necesidad de dependencias externas mockeadas.
 */
class ServicioReportesTest {

    private ServicioReportes servicioReportes;

    /** Contrato de prueba reutilizable en todos los tests */
    private Contrato contrato;

    /** Reporte de prueba reutilizable */
    private ReporteInterventoria reporte;

    @BeforeEach
    void setUp() {
        servicioReportes = new ServicioReportes();

        Contratante contratante = new Contratante(
                TipoPersona.JURIDICA, TipoDocumento.NIT, "111111111",
                "Entidad Pública", "entidad@gov.co", "pass123",
                "3001112233", "Calle 1", "Sogamoso", Rol.CONTRATANTE,
                "Público", "Municipal", "CUE-001");

        contrato = new ContratoObraPublica(
                "CON-001", "Pavimentación vía", LocalDate.now(),
                contratante, null,
                TipoContrato.CONTRATOOBRAPUBLICA, 1_000_000,
                LocalDate.now().plusMonths(6),
                FaseContrato.PUBLICACION, "Sogamoso", 200.0);

        reporte = new ReporteInterventoria(
                contrato, "Avance del 30%", LocalDateTime.now(),
                FaseContrato.PUBLICACION, FaseContrato.LICITACION);
    }

    @Nested
    @DisplayName("guardarReporte")
    class GuardarReporte {

        @Test
        @DisplayName("Guarda un reporte válido correctamente")
        void guarda_reporte_valido() {
            servicioReportes.guardarReporte(reporte);

            String resultado = servicioReportes.obtenerTodosLosReportes();
            assertFalse(resultado.contains("No se han registrado"));
        }

        @Test
        @DisplayName("No lanza excepción al guardar un reporte nulo")
        void guardar_reporte_nulo_no_falla() {
            assertDoesNotThrow(() -> servicioReportes.guardarReporte(null));
        }

        @Test
        @DisplayName("Un reporte nulo no se almacena")
        void reporte_nulo_no_se_almacena() {
            servicioReportes.guardarReporte(null);

            String resultado = servicioReportes.obtenerTodosLosReportes();
            assertTrue(resultado.contains("No se han registrado"));
        }

        @Test
        @DisplayName("Guardar múltiples reportes los almacena todos")
        void guarda_multiples_reportes() {
            ReporteInterventoria reporte2 = new ReporteInterventoria(
                    contrato, "Avance del 60%", LocalDateTime.now(),
                    FaseContrato.LICITACION, FaseContrato.ADJUDICACION);

            servicioReportes.guardarReporte(reporte);
            servicioReportes.guardarReporte(reporte2);

            String resultado = servicioReportes.obtenerTodosLosReportes();
            // Ambos reportes deben aparecer en el texto
            assertTrue(resultado.contains("Avance del 30%"));
            assertTrue(resultado.contains("Avance del 60%"));
        }
    }

    @Nested
    @DisplayName("mostrarReporte")
    class MostrarReporte {

        @Test
        @DisplayName("Retorna texto con los datos del reporte")
        void muestra_datos_correctos() {
            String resultado = servicioReportes.mostrarReporte(reporte);

            assertAll(
                    () -> assertTrue(resultado.contains("CON-001")),
                    () -> assertTrue(resultado.contains("Avance del 30%")),
                    () -> assertTrue(resultado.contains("PUBLICACION")),
                    () -> assertTrue(resultado.contains("LICITACION"))
            );
        }

        @Test
        @DisplayName("Retorna mensaje de error al recibir reporte nulo")
        void reporte_nulo_retorna_error() {
            String resultado = servicioReportes.mostrarReporte(null);
            assertEquals("Reporte no válido.", resultado);
        }
    }

    @Nested
    @DisplayName("obtenerTodosLosReportes")
    class ObtenerTodosLosReportes {

        @Test
        @DisplayName("Retorna mensaje cuando no hay reportes registrados")
        void sin_reportes() {
            String resultado = servicioReportes.obtenerTodosLosReportes();
            assertTrue(resultado.contains("No se han registrado"));
        }

        @Test
        @DisplayName("Retorna los reportes cuando hay al menos uno guardado")
        void con_reportes() {
            servicioReportes.guardarReporte(reporte);

            String resultado = servicioReportes.obtenerTodosLosReportes();
            assertFalse(resultado.contains("No se han registrado"));
            assertTrue(resultado.contains("REPORTE DE INTERVENTORÍA"));
        }
    }

    @Nested
    @DisplayName("obtenerReportesPorContrato")
    class ObtenerReportesPorContrato {

        @Test
        @DisplayName("Retorna mensaje cuando el contrato no tiene reportes")
        void sin_reportes_para_contrato() {
            String resultado = servicioReportes.obtenerReportesPorContrato("CON-999");
            assertTrue(resultado.contains("no registra reportes"));
        }

        @Test
        @DisplayName("Retorna los reportes del contrato correcto")
        void retorna_reportes_del_contrato() {
            servicioReportes.guardarReporte(reporte);

            String resultado = servicioReportes.obtenerReportesPorContrato("CON-001");
            assertTrue(resultado.contains("CON-001"));
            assertTrue(resultado.contains("Avance del 30%"));
        }

        @Test
        @DisplayName("No mezcla reportes de contratos distintos")
        void no_mezcla_contratos() {
            // Segundo contrato con ID diferente
            Contratante contratante2 = new Contratante(
                    TipoPersona.JURIDICA, TipoDocumento.NIT, "999999999",
                    "Otra Entidad", "otra@gov.co", "pass999",
                    "3007778888", "Av 2", "Tunja", Rol.CONTRATANTE,
                    "Privado", "Nacional", "CUE-002");

            Contrato contrato2 = new ContratoObraPublica(
                    "CON-002", "Construcción puente", LocalDate.now(),
                    contratante2, null,
                    TipoContrato.CONTRATOOBRAPUBLICA, 5_000_000,
                    LocalDate.now().plusMonths(12),
                    FaseContrato.PUBLICACION, "Tunja", 500.0);

            ReporteInterventoria reporteContrato2 = new ReporteInterventoria(
                    contrato2, "Inicio de obra", LocalDateTime.now(),
                    FaseContrato.PUBLICACION, FaseContrato.LICITACION);

            servicioReportes.guardarReporte(reporte);
            servicioReportes.guardarReporte(reporteContrato2);

            String resultadoCon001 = servicioReportes.obtenerReportesPorContrato("CON-001");
            String resultadoCon002 = servicioReportes.obtenerReportesPorContrato("CON-002");

            assertTrue(resultadoCon001.contains("Avance del 30%"));
            assertFalse(resultadoCon001.contains("Inicio de obra"));

            assertTrue(resultadoCon002.contains("Inicio de obra"));
            assertFalse(resultadoCon002.contains("Avance del 30%"));
        }

        @Test
        @DisplayName("La búsqueda por contrato no distingue mayúsculas/minúsculas")
        void busqueda_case_insensitive() {
            servicioReportes.guardarReporte(reporte);

            // El método usa equalsIgnoreCase, así que "con-001" debe funcionar igual
            String resultado = servicioReportes.obtenerReportesPorContrato("con-001");
            assertTrue(resultado.contains("CON-001"));
        }
    }
}