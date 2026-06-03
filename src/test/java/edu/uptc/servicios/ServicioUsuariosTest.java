package edu.uptc.servicios;
import edu.uptc.dominio.Administrador;
import edu.uptc.dominio.Contratante;
import edu.uptc.dominio.Contratista;
import edu.uptc.dominio.Usuario;
import edu.uptc.enums.Rol;
import edu.uptc.enums.TipoDocumento;
import edu.uptc.enums.TipoPersona;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServicioUsuariosTest {

    private ServicioUsuarios servicio;

    @BeforeEach
    void setUp() {
        servicio = new ServicioUsuarios();
    }

    @Test
    void crearAdministrador() {
        servicio.crearAdministrador();

        assertTrue(servicio.numeroDocumentoExiste("6568777378"));

        Usuario admin = servicio.obtenerUsuario("6568777378");
        assertInstanceOf(Administrador.class, admin);
        assertEquals("ADMINISTRADOR", admin.getNombre());
        assertEquals("administrador@secop.org.co", admin.getCorreo());
        assertEquals(Rol.ADMINISTRADOR, admin.getRol());
        assertEquals(TipoPersona.JURIDICA, admin.getTipoPersona());
        assertEquals(TipoDocumento.NIT, admin.getTipoDocumento());
    }

    @Test
    void agregarUsuario() {
        Administrador admin = new Administrador(
                TipoPersona.JURIDICA, TipoDocumento.NIT, "12345678",
                "Admin Prueba", "admin@prueba.com", "clave1",
                "6011234567", "Calle 1 # 2-3", "Bogota", Rol.ADMINISTRADOR
        );

        servicio.agregarUsuario(admin);

        assertTrue(servicio.numeroDocumentoExiste("12345678"));
        assertEquals(admin, servicio.obtenerUsuario("12345678"));
    }

    @Test
    void loginCorrecto() {
        servicio.crearContratante(
                TipoPersona.NATURAL, TipoDocumento.CC, "11111111",
                "Ana Gomez", "ana@correo.com", "miClave",
                "3119876543", "Av 3 # 4-5", "Medellin", Rol.CONTRATANTE,
                "Educacion", "Departamental", "COD002"
        );

        assertTrue(servicio.loginCorrecto("11111111", "miClave"));

        // Contraseña incorrecta → false
        assertFalse(servicio.loginCorrecto("11111111", "otraClave"));

        // Documento que no existe → false
        assertFalse(servicio.loginCorrecto("99999999", "miClave"));
    }

    @Test
    void rolLogueado() {
        servicio.crearAdministrador();

        servicio.crearContratante(
                TipoPersona.NATURAL, TipoDocumento.CC, "22222222",
                "Luis Rios", "luis@correo.com", "clave2",
                "3201112233", "Cra 5 # 6-7", "Cali", Rol.CONTRATANTE,
                "Construccion", "Nacional", "COD003"
        );

        servicio.crearContratista(
                TipoPersona.NATURAL, TipoDocumento.CC, "33333333",
                "Maria Vera", "maria@correo.com", "clave3",
                "3154445566", "Calle 8 # 9-10", "Cali", Rol.CONTRATISTA,
                false, "Sistemas"
        );

        assertEquals(Rol.ADMINISTRADOR, servicio.rolLogueado("6568777378"));
        assertEquals(Rol.CONTRATANTE, servicio.rolLogueado("22222222"));
        assertEquals(Rol.CONTRATISTA, servicio.rolLogueado("33333333"));
    }

    @Test
    void crearContratante() {
        servicio.crearContratante(
                TipoPersona.JURIDICA, TipoDocumento.NIT, "44444444",
                "Empresa ABC", "abc@empresa.com", "clave4",
                "6017001234", "Carrera 10 # 11-12", "Bogota", Rol.CONTRATANTE,
                "Energia", "Nacional", "COD004"
        );

        assertTrue(servicio.numeroDocumentoExiste("44444444"));

        Usuario u = servicio.obtenerUsuario("44444444");
        assertInstanceOf(Contratante.class, u);

        Contratante c = (Contratante) u;
        assertEquals(TipoPersona.JURIDICA, c.getTipoPersona());
        assertEquals(TipoDocumento.NIT, c.getTipoDocumento());
        assertEquals("44444444", c.getNumeroDocumento());
        assertEquals("Empresa ABC", c.getNombre());
        assertEquals("abc@empresa.com", c.getCorreo());
        assertEquals("clave4", c.getContrasenha());
        assertEquals("6017001234", c.getTelefono());
        assertEquals("Carrera 10 # 11-12", c.getDireccion());
        assertEquals("Bogota", c.getCiudad());
        assertEquals(Rol.CONTRATANTE, c.getRol());
        assertEquals("Energia", c.getSector());
        assertEquals("Nacional", c.getNivelEntidad());
        assertEquals("COD004", c.getCodigoUnicoEntidad());
    }

    @Test
    void consultarContratantes() {
        servicio.crearContratante(
                TipoPersona.NATURAL, TipoDocumento.CC, "55555555",
                "Pedro Alba", "pedro@correo.com", "clave5",
                "3007778899", "Calle 15 # 16-17", "Barranquilla", Rol.CONTRATANTE,
                "Turismo", "Municipal", "COD005"
        );

        String infoContratante = servicio.consultarContratantes("55555555");
        assertTrue(infoContratante.contains("Pedro Alba"));

        String infoInexistente = servicio.consultarContratantes("00000000");
        assertEquals("Contratante no encontrado\nIntentalo más tarde", infoInexistente);
    }

    @Test
    void actualizarUsuario() {
        servicio.crearContratante(
                TipoPersona.NATURAL, TipoDocumento.CC, "66666666",
                "Sofia Cruz", "sofia@correo.com", "clave6",
                "3209990011", "Cra 20 # 21-22", "Pereira", Rol.CONTRATANTE,
                "Mineria", "Departamental", "COD006"
        );

        servicio.actualizarUsuario(
                null, null, "66666666",
                "Sofia Cruz Actualizada", "sofia_nueva@correo.com",
                null, null, null, null
        );

        Usuario u = servicio.obtenerUsuario("66666666");
        assertEquals("Sofia Cruz Actualizada", u.getNombre());
        assertEquals("sofia_nueva@correo.com", u.getCorreo());

        assertEquals("clave6", u.getContrasenha());
        assertEquals("3209990011", u.getTelefono());
        assertEquals("Cra 20 # 21-22", u.getDireccion());
        assertEquals("Pereira", u.getCiudad());
        assertEquals(TipoPersona.NATURAL, u.getTipoPersona());
        assertEquals(TipoDocumento.CC, u.getTipoDocumento());
    }

    @Test
    void actualizarContratante() {
        servicio.crearContratante(
                TipoPersona.NATURAL, TipoDocumento.CC, "77777777",
                "Carlos Niño", "carlos@correo.com", "clave7",
                "3112223344", "Av 25 # 26-27", "Manizales", Rol.CONTRATANTE,
                "SectorOriginal", "NivelOriginal", "COD-ORIGINAL"
        );

        servicio.actualizarContratante("77777777", "SectorNuevo", "NivelNuevo", "COD-NUEVO");

        Contratante c = (Contratante) servicio.obtenerUsuario("77777777");
        assertEquals("SectorNuevo", c.getSector());
        assertEquals("NivelNuevo", c.getNivelEntidad());
        assertEquals("COD-NUEVO", c.getCodigoUnicoEntidad());
    }

    @Test
    void eliminarContratante() {
        servicio.crearContratante(
                TipoPersona.NATURAL, TipoDocumento.CC, "88888888",
                "Laura Diaz", "laura@correo.com", "clave8",
                "3185556677", "Calle 30 # 31-32", "Bucaramanga", Rol.CONTRATANTE,
                "Comercio", "Municipal", "COD007"
        );

        assertTrue(servicio.numeroDocumentoExiste("88888888"));

        servicio.eliminarContratante("88888888");

        assertFalse(servicio.numeroDocumentoExiste("88888888"));
        assertNull(servicio.obtenerUsuario("88888888"));
    }

    @Test
    void mostrarContratantes() {
        String sinNinguno = servicio.mostrarContratantes();
        assertEquals("No hay contratantes registrados.", sinNinguno);

        servicio.crearContratante(
                TipoPersona.NATURAL, TipoDocumento.CC, "91919191",
                "Roberto Lara", "roberto@correo.com", "clave17",
                "3001919191", "Calle 110 # 111-112", "Bogota", Rol.CONTRATANTE,
                "Salud", "Nacional", "COD009"
        );
        servicio.crearContratante(
                TipoPersona.JURIDICA, TipoDocumento.NIT, "92929292",
                "Empresa Delta", "delta@empresa.com", "clave18",
                "6012020202", "Cra 45 # 46-47", "Bogota", Rol.CONTRATANTE,
                "Tecnologia", "Departamental", "COD010"
        );

        String resultado = servicio.mostrarContratantes();

        assertTrue(resultado.contains("Roberto Lara"));
        assertTrue(resultado.contains("Empresa Delta"));
    }

    @Test
    void crearContratista() {
        servicio.crearContratista(
                TipoPersona.NATURAL, TipoDocumento.CC, "10101010",
                "Elena Mora", "elena@correo.com", "clave9",
                "3001010101", "Cra 35 # 36-37", "Bogota", Rol.CONTRATISTA,
                false, "Ingenieria Civil"
        );

        assertTrue(servicio.numeroDocumentoExiste("10101010"));

        Usuario u = servicio.obtenerUsuario("10101010");
        assertInstanceOf(Contratista.class, u);

        Contratista c = (Contratista) u;
        assertEquals(TipoPersona.NATURAL, c.getTipoPersona());
        assertEquals(TipoDocumento.CC, c.getTipoDocumento());
        assertEquals("10101010", c.getNumeroDocumento());
        assertEquals("Elena Mora", c.getNombre());
        assertEquals("elena@correo.com", c.getCorreo());
        assertEquals("clave9", c.getContrasenha());
        assertEquals("3001010101", c.getTelefono());
        assertEquals("Cra 35 # 36-37", c.getDireccion());
        assertEquals("Bogota", c.getCiudad());
        assertEquals(Rol.CONTRATISTA, c.getRol());
        assertFalse(c.isEsEntidadPublica());
        assertEquals("Ingenieria Civil", c.getAreaDesempenho());
    }

    @Test
    void consultarContratistas() {
        servicio.crearContratista(
                TipoPersona.JURIDICA, TipoDocumento.NIT, "20202020",
                "Constructora XYZ", "xyz@const.com", "clave10",
                "6013334455", "Av Americas # 40-41", "Bogota", Rol.CONTRATISTA,
                true, "Obras Civiles"
        );

        String infoContratista = servicio.consultarContratistas("20202020");
        assertTrue(infoContratista.contains("Constructora XYZ"));

        String infoInexistente = servicio.consultarContratistas("00000000");
        assertEquals("Contratista no encontrado\nIntentalo más tarde", infoInexistente);
    }

    @Test
    void actualizarContratista() {
        servicio.crearContratista(
                TipoPersona.NATURAL, TipoDocumento.CC, "30303030",
                "Rodrigo Silva", "rodrigo@correo.com", "clave11",
                "3157778899", "Calle 50 # 51-52", "Cucuta", Rol.CONTRATISTA,
                false, "AreaOriginal"
        );

        servicio.actualizarContratista("30303030", true, "AreaNueva");

        Contratista c = (Contratista) servicio.obtenerUsuario("30303030");
        assertTrue(c.isEsEntidadPublica());
        assertEquals("AreaNueva", c.getAreaDesempenho());
    }

    @Test
    void mostrarContratistas() {
        String sinNinguno = servicio.mostrarContratistas();
        assertEquals("No hay contratistas registrados en el sistema.", sinNinguno);

        servicio.crearContratista(
                TipoPersona.NATURAL, TipoDocumento.CC, "40404040",
                "Valentina Ruiz", "vale@correo.com", "clave12",
                "3001414141", "Calle 60 # 61-62", "Ibague", Rol.CONTRATISTA,
                false, "Arquitectura"
        );
        servicio.crearContratista(
                TipoPersona.NATURAL, TipoDocumento.CC, "50505050",
                "Andres Castro", "andres@correo.com", "clave13",
                "3001515151", "Calle 70 # 71-72", "Neiva", Rol.CONTRATISTA,
                true, "Electronica"
        );

        String resultado = servicio.mostrarContratistas();

        assertTrue(resultado.contains("Valentina Ruiz"));
        assertTrue(resultado.contains("Andres Castro"));
    }


    @Test
    void eliminarContratista() {
        servicio.crearContratista(
                TipoPersona.NATURAL, TipoDocumento.CC, "60606060",
                "Camila Herrera", "camila@correo.com", "clave14",
                "3001616161", "Calle 80 # 81-82", "Villavicencio", Rol.CONTRATISTA,
                false, "Agronomia"
        );

        assertTrue(servicio.numeroDocumentoExiste("60606060")); // precondición

        servicio.eliminarContratista("60606060");

        assertFalse(servicio.numeroDocumentoExiste("60606060"));
        assertNull(servicio.obtenerUsuario("60606060"));
    }

    @Test
    void numeroDocumentoExiste() {
        servicio.crearContratante(
                TipoPersona.NATURAL, TipoDocumento.CC, "70707070",
                "Daniela Rios", "daniela@correo.com", "clave15",
                "3001717171", "Calle 90 # 91-92", "Popayan", Rol.CONTRATANTE,
                "Comercio", "Municipal", "COD008"
        );

        assertTrue(servicio.numeroDocumentoExiste("70707070"));
        assertFalse(servicio.numeroDocumentoExiste("00000000"));
    }

    @Test
    void obtenerUsuario() {
        servicio.crearContratista(
                TipoPersona.NATURAL, TipoDocumento.CC, "80808080",
                "Sebastian Mora", "sebas@correo.com", "clave16",
                "3001818181", "Calle 100 # 101-102", "Armenia", Rol.CONTRATISTA,
                false, "Sistemas"
        );

        Usuario encontrado = servicio.obtenerUsuario("80808080");
        assertNotNull(encontrado);
        assertEquals("Sebastian Mora", encontrado.getNombre());

        Usuario noEncontrado = servicio.obtenerUsuario("00000000");
        assertNull(noEncontrado);
    }
}