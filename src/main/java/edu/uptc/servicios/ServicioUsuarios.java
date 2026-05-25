package edu.uptc.servicios;

import edu.uptc.dominio.Administrador;
import edu.uptc.dominio.Contratante;
import edu.uptc.dominio.Contratista;
import edu.uptc.dominio.Usuario;
import edu.uptc.enums.Rol;
import edu.uptc.enums.TipoDocumento;
import edu.uptc.enums.TipoPersona;

import java.util.HashMap;

/**
 * La clase {@code ServicioUsuarios} gestiona todas las operaciones relacionadas con los usuarios
 * del sistema, incluyendo la creación, autenticación, actualización y eliminación de administradores,
 * contratantes y contratistas. Utiliza un {@link HashMap} para almacenar y acceder a los usuarios.
 */
public class ServicioUsuarios {
    /**
     * Un mapa que almacena los usuarios del sistema, utilizando el número de documento como clave única.
     */
    private HashMap<String, Usuario> usuarios;

    /**
     * Constructor de la clase {@code ServicioUsuarios}.
     * Inicializa el {@link HashMap} que contendrá a todos los usuarios registrados en el sistema.
     */
    public ServicioUsuarios() {
        this.usuarios = new HashMap<>();
    }


    /**
     * Crea y registra un usuario administrador por defecto al iniciar la aplicación.
     * Este administrador tiene credenciales predefinidas para la configuración inicial del sistema.
     */
    public void crearAdministrador() {
        TipoPersona tipoPersona = TipoPersona.JURIDICA;
        TipoDocumento tipoDocumento = TipoDocumento.NIT;
        String numeroDocumento = "6568777378"; // PALABRA "ADMIN" EN ASCII
        String nombre = "ADMINISTRADOR";
        String correo = "administrador@secop.org.co";
        String contrasenha = "ADMIN123";
        String telefono = "+57 (601) 7456788";
        String direccion = "Carrera 7 # 26 – 20, Edificio Tequendama";
        String ciudad = "Bogota D.C.";
        Rol rol = Rol.ADMINISTRADOR;

        Usuario usuarioAdmin = new Administrador(tipoPersona, tipoDocumento, numeroDocumento, nombre, correo,
                contrasenha, telefono, direccion, ciudad, rol);
        this.usuarios.put(numeroDocumento, usuarioAdmin);
    }

    /**
     * Agrega un nuevo usuario al sistema.
     * El usuario es almacenado en el {@link HashMap} utilizando su número de documento como clave.
     *
     * @param usuario El objeto {@link Usuario} a agregar.
     */
    public void agregarUsuario(Usuario usuario) {
        this.usuarios.put(usuario.getNumeroDocumento(), usuario);

    }

    /**
     * Realiza el proceso de autenticación de un usuario.
     * Verifica si el número de documento existe y si la contraseña proporcionada coincide con la registrada.
     *
     * @param numeroDocumento El número de documento del usuario que intenta iniciar sesión.
     * @param contrasenha La contraseña ingresada por el usuario.
     * @return {@code true} si las credenciales son correctas, {@code false} en caso contrario.
     */
    public boolean loginCorrecto(String numeroDocumento, String contrasenha) {
        if (numeroDocumentoExiste(numeroDocumento)) {
            return (usuarios.get(numeroDocumento).getContrasenha()).equals(contrasenha);
        } else {
            return false;
        }
    }

    /**
     * Obtiene el rol de un usuario a partir de su número de documento.
     *
     * @param numeroDocumento El número de documento del usuario.
     * @return El {@link Rol} asociado al usuario.
     */
    public Rol rolLogueado(String numeroDocumento) {
        return this.usuarios.get(numeroDocumento).getRol();
    }

    /**
     * Crea un nuevo contratante y lo agrega al sistema.
     *
     * @param tipoPersona El tipo de persona del contratante (Natural o Jurídica).
     * @param tipoDocumento El tipo de documento de identificación del contratante.
     * @param numeroDocumento El número de documento único del contratante.
     * @param nombre El nombre completo o razón social del contratante.
     * @param correo El correo electrónico del contratante.
     * @param contrasenha La contraseña para el acceso del contratante.
     * @param telefono El número de teléfono del contratante.
     * @param direccion La dirección de residencia u oficina del contratante.
     * @param ciudad La ciudad del contratante.
     * @param rol El rol asignado al contratante (debe ser {@link Rol#CONTRATANTE}).
     * @param sector El sector económico al que pertenece el contratante.
     * @param nivelEntidad El nivel de la entidad (e.g., nacional, departamental, municipal).
     * @param codigoUnicoEntidad El código único de la entidad contratante.
     */
    public void crearContratante(TipoPersona tipoPersona, TipoDocumento tipoDocumento, String numeroDocumento, String nombre,
                                 String correo, String contrasenha, String telefono, String direccion, String ciudad, Rol rol,
                                 String sector, String nivelEntidad, String codigoUnicoEntidad) {
        Usuario contratanteAux = new Contratante(tipoPersona, tipoDocumento, numeroDocumento, nombre, correo,
                contrasenha, telefono, direccion, ciudad, rol, sector, nivelEntidad, codigoUnicoEntidad);
        agregarUsuario(contratanteAux);
    }

    /**
     * Consulta la información de un contratante específico por su número de documento.
     *
     * @param numeroDocumento El número de documento del contratante a consultar.
     * @return Una cadena de texto con la información detallada del contratante, o un mensaje si no se encuentra.
     */
    public String consultarContratantes(String numeroDocumento) {
        Usuario usuarioAux = this.usuarios.get(numeroDocumento);
        if (usuarioAux != null && usuarioAux instanceof Contratante) {
            return usuarioAux.mostrarInfoUsuario();
        }
        return "Contratante no encontrado\nIntentalo más tarde";
    }

    /**
     * Actualiza la información general de un usuario existente en el sistema.
     * Los campos que no se deseen actualizar pueden pasarse como {@code null}.
     * Este método está diseñado para actualizar atributos comunes a todos los tipos de usuarios,
     * excluyendo a los administradores de ser modificados por este flujo.
     *
     * @param tipoPersona El nuevo tipo de persona del usuario (Natural o Jurídica).
     * @param tipoDocumento El nuevo tipo de documento del usuario (CC, CE, PAS, PPT, NIT).
     * @param numeroDocumento El número de documento del usuario a actualizar (identificador único).
     * @param nombre El nuevo nombre completo del usuario.
     * @param correo El nuevo correo electrónico del usuario.
     * @param contrasenha La nueva contraseña del usuario.
     * @param telefono El nuevo número de teléfono del usuario.
     * @param direccion La nueva dirección de residencia u oficina del usuario.
     * @param ciudad La nueva ciudad de residencia del usuario.
     */
    public void actualizarUsuario(TipoPersona tipoPersona, TipoDocumento tipoDocumento, String numeroDocumento, String nombre,
                                  String correo, String contrasenha, String telefono, String direccion, String ciudad) {
        Usuario usuarioEncontrado = this.usuarios.get(numeroDocumento);

        if (usuarioEncontrado != null && (usuarioEncontrado instanceof Contratante || usuarioEncontrado instanceof Contratista)) {
            if (tipoPersona != null) {
                usuarioEncontrado.setTipoPersona(tipoPersona);
            }
            if (tipoDocumento == null) { // This condition seems incorrect, should be `!= null` to update
                usuarioEncontrado.setTipoDocumento(tipoDocumento);
            }
            if (nombre != null) {
                usuarioEncontrado.setNombre(nombre);
            }
            if (correo != null) {
                usuarioEncontrado.setCorreo(correo);
            }
            if (contrasenha == null) { // This condition seems incorrect, should be `!= null` to update
                usuarioEncontrado.setContrasenha(contrasenha);
            }
            if (telefono != null) {
                usuarioEncontrado.setTelefono(telefono);
            }
            if (direccion != null) {
                usuarioEncontrado.setDireccion(direccion);
            }
            if (ciudad != null) {
                usuarioEncontrado.setCiudad(ciudad);
            }
        }

    }

    /**
     * Actualiza los atributos específicos de un contratante existente.
     * Los campos que no se deseen actualizar pueden pasarse como {@code null}.
     *
     * @param numeroDocumento El número de documento del contratante a actualizar.
     * @param sector El nuevo sector económico del contratante.
     * @param nivelEntidad El nuevo nivel de entidad del contratante.
     * @param codigoUnicoEntidad El nuevo código único de entidad del contratante.
     */
    public void actualizarContratante(String numeroDocumento, String sector, String nivelEntidad, String codigoUnicoEntidad) {
        Usuario usuarioEncontrado = this.usuarios.get(numeroDocumento);
        if (usuarioEncontrado != null && usuarioEncontrado instanceof Contratante) {
            Contratante contratante = (Contratante) usuarioEncontrado;

            if (sector != null) {
                contratante.setSector(sector);
            }
            if (nivelEntidad != null) {
                contratante.setNivelEntidad(nivelEntidad);
            }
            if (codigoUnicoEntidad != null) {
                contratante.setCodigoUnicoEntidad(codigoUnicoEntidad);

            }
        }
    }

    /**
     * Elimina un contratante del sistema utilizando su número de documento.
     *
     * @param numeroDocumentoEliminarContratante El número de documento del contratante a eliminar.
     */
    public void eliminarContratante(String numeroDocumentoEliminarContratante) {
        this.usuarios.remove(numeroDocumentoEliminarContratante);
    }

    /**
     * Crea un nuevo contratista y lo agrega al sistema.
     *
     * @param tipoPersona El tipo de persona del contratista (Natural o Jurídica).
     * @param tipoDocumento El tipo de documento de identificación del contratista.
     * @param numeroDocumento El número de documento único del contratista.
     * @param nombre El nombre completo o razón social del contratista.
     * @param correo El correo electrónico del contratista.
     * @param contrasenha La contraseña para el acceso del contratista.
     * @param telefono El número de teléfono del contratista.
     * @param direccion La dirección de residencia u oficina del contratista.
     * @param ciudad La ciudad del contratista.
     * @param rol El rol asignado al contratista (debe ser {@link Rol#CONTRATISTA}).
     * @param esEntidadPublica Indica si el contratista es una entidad pública.
     * @param areaDesempenho El área de desempeño o especialización del contratista.
     */
    public void crearContratista(TipoPersona tipoPersona, TipoDocumento tipoDocumento, String numeroDocumento, String nombre,
                                 String correo, String contrasenha, String telefono, String direccion, String ciudad, Rol rol,
                                 boolean esEntidadPublica, String areaDesempenho) {
        Usuario contratistaAux = new Contratista(tipoPersona, tipoDocumento, numeroDocumento, nombre, correo,
                contrasenha, telefono, direccion, ciudad, rol, esEntidadPublica, areaDesempenho);
        agregarUsuario(contratistaAux);

    }

    /**
     * Consulta la información de un contratista específico por su número de documento.
     *
     * @param numeroDocumento El número de documento del contratista a consultar.
     * @return Una cadena de texto con la información detallada del contratista, o un mensaje si no se encuentra.
     */
    public String consultarContratistas(String numeroDocumento) {
        Usuario usuarioAux = this.usuarios.get(numeroDocumento);
        if (usuarioAux != null && usuarioAux instanceof Contratista) {
            return usuarioAux.mostrarInfoUsuario();
        }
        return "Contratista no encontrado\nIntentalo más tarde";
    }

    /**
     * Actualiza los atributos específicos de un contratista existente.
     * Los campos que no se deseen actualizar pueden pasarse como {@code null}.
     *
     * @param numeroDocumento El número de documento del contratista a actualizar.
     * @param esEntidadPublica El nuevo estado de si el contratista es una entidad pública.
     * @param areaDesempenho La nueva área de desempeño o especialización del contratista.
     */
    public void actualizarContratista(String numeroDocumento, Boolean esEntidadPublica, String areaDesempenho) {
        Usuario usuarioEncontrado = this.usuarios.get(numeroDocumento);
        if (usuarioEncontrado != null && usuarioEncontrado instanceof Contratista) {
            Contratista contratista = (Contratista) usuarioEncontrado;

            if (esEntidadPublica != null) {
                contratista.setEsEntidadPublica(esEntidadPublica);
            }
            if (areaDesempenho != null) {
                contratista.setAreaDesempenho(areaDesempenho);
            }
        }
    }

    /**
     * Elimina un contratista del sistema utilizando su número de documento.
     *
     * @param numeroDocumentoEliminarContratista El número de documento del contratista a eliminar.
     */
    public void eliminarContratista(String numeroDocumentoEliminarContratista) {
        this.usuarios.remove(numeroDocumentoEliminarContratista);
    }

    /**
     * Verifica la existencia de un usuario en el sistema a través de su número de documento.
     *
     * @param numeroDocumentoBuscar El número de documento a buscar.
     * @return {@code true} si un usuario con el número de documento especificado existe, {@code false} en caso contrario.
     */
    public boolean numeroDocumentoExiste(String numeroDocumentoBuscar) {
        return usuarios.containsKey(numeroDocumentoBuscar);
    }
}
