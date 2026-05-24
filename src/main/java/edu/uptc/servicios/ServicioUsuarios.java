package edu.uptc.servicios;

import edu.uptc.dominio.Administrador;
import edu.uptc.dominio.Contratante;
import edu.uptc.dominio.Contratista;
import edu.uptc.dominio.Usuario;
import edu.uptc.enums.Rol;
import edu.uptc.enums.TipoDocumento;
import edu.uptc.enums.TipoPersona;

import java.util.HashMap;

public class ServicioUsuarios {
    private HashMap<String, Usuario> usuarios;

    // METODO CONSTRUCTOR INICIANDO LAS LISTAS DE LOS USUARIOS

    public ServicioUsuarios() {
        this.usuarios = new HashMap<>();
    }

    // ===============================================================================================================

    // METODO PARA CREAR EL ADMIN
/*   ESTE METODO NO DEVUELVE NADA, SIEMPRE SE LLAMA CUANDO SE ABRE EL PROGRAMA
     EL METODO TAMBIEN NO REQUIERE LLAMAR NINGUN ATRIBUTO
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

    // ===============================================================================================================
    // METODOS PARA MANIPULAR EL HASH MAP

    public void agregarUsuario(Usuario usuario) {
        this.usuarios.put(usuario.getNumeroDocumento(), usuario);

    }

    // ===============================================================================================================
    // METODO PARA EL LOGIN
    public boolean loginCorrecto(String numeroDocumento, String contrasenha) {
        if (numeroDocumentoExiste(numeroDocumento)) {
            return (usuarios.get(numeroDocumento).getContrasenha()).equals(contrasenha);
        } else {
            return false;
        }
    }

    public Rol rolLogueado(String numeroDocumento) {
        return this.usuarios.get(numeroDocumento).getRol();
    }
    // ===============================================================================================================
    // METODOS DEL ADMINISTRADOR

    public void crearContratante(TipoPersona tipoPersona, TipoDocumento tipoDocumento, String numeroDocumento, String nombre,
                                 String correo, String contrasenha, String telefono, String direccion, String ciudad, Rol rol,
                                 String sector, String nivelEntidad, String codigoUnicoEntidad) {
        Usuario contratanteAux = new Contratante(tipoPersona, tipoDocumento, numeroDocumento, nombre, correo,
                contrasenha, telefono, direccion, ciudad, rol, sector, nivelEntidad, codigoUnicoEntidad);
        agregarUsuario(contratanteAux);
    }

    /*public void crearContratante(TipoPersona tipoPersona, TipoDocumento tipoDocumento, String numeroDocumento, String nombre,
                                 String correo, String contrasenha, String telefono, String direccion, String ciudad,
                                 String sector, String nivelEntidad, String codigoUnicoEntidad) {
        Usuario contratanteAux = new Contratante(tipoPersona, tipoDocumento, numeroDocumento, nombre, correo,
                contrasenha, telefono, direccion, ciudad, sector, nivelEntidad, codigoUnicoEntidad);
        this.usuarios.put(contratanteAux.getNumeroDocumento(), contratanteAux);
    }*/

    public void consultarContratantes() {

    }

    // METODO PARA ACTUALIZAR USUARIO
    public void actualizarUsuario(TipoPersona tipoPersona, TipoDocumento tipoDocumento, String numeroDocumento, String nombre,
                                  String correo, String contrasenha, String telefono, String direccion, String ciudad) {
        // SE AGARRA EL OBJETO AUXILIAR
        Usuario usuarioEncontrado = this.usuarios.get(numeroDocumento);

//        VALIDA SI EL USUARIO ENCONTRADO NO ES NULL
//        TAMBIEN VALIDA QUE EL ADMIN NO PUEDA MODIFICARSE A SI MISMO
//        VALIDA QUE EL METODO QUE SE PUEDE REUTILIZAR MODIFICANDO EN CONTRATISTA Y CONTRATANTE
//        TIENEN QUE SER OBJETOS OBLIGATORIAMENTE CONTRATISTA Y CONTRATANTE, NO ADMINISTRADOR
        if (usuarioEncontrado != null && (usuarioEncontrado instanceof Contratante || usuarioEncontrado instanceof Contratista)) {
            if (tipoPersona != null) {
                usuarioEncontrado.setTipoPersona(tipoPersona);
            } else if (tipoDocumento == null) {
                usuarioEncontrado.setTipoDocumento(tipoDocumento);
            } else if (nombre != null) {
                usuarioEncontrado.setNombre(nombre);
            } else if (correo != null) {
                usuarioEncontrado.setCorreo(correo);
            } else if (contrasenha == null) {
                usuarioEncontrado.setContrasenha(contrasenha);
            } else if (telefono != null) {
                usuarioEncontrado.setTelefono(telefono);
            } else if (direccion != null) {
                usuarioEncontrado.setDireccion(direccion);
            } else if (ciudad != null) {
                usuarioEncontrado.setCiudad(ciudad);
            }
        }

    }

    public void actualizarContratante(String numeroDocumento, String sector, String nivelEntidad, String codigoUnicoEntidad) {
        // SE AGARRA EL OBJETO AUXILIAR
        Usuario usuarioEncontrado = this.usuarios.get(numeroDocumento);
        if (usuarioEncontrado != null && usuarioEncontrado instanceof Contratante) {
            // EN LA LISTA DE USUARIOS TODOS SE COMPORTAN ASI ENTONCES AGARRO EL OBJETO EN USUARIO ENCONTRADO
            // PARA CASTEARLO COMO CONTRATANTE Y SE COMPORTE DE ESA MANERA
            Contratante contratante = (Contratante) usuarioEncontrado;

            if (sector != null) {
                contratante.setSector(sector);
            } else if (nivelEntidad != null) {
                contratante.setNivelEntidad(nivelEntidad);
            } else if (codigoUnicoEntidad != null) {
                contratante.setCodigoUnicoEntidad(codigoUnicoEntidad);

            }
        }
    }

    public void eliminarContratante(String numeroDocumento) {

    }

    public void crearContratista() {

    }

    public void consultarContratistas() {

    }

    public void actualizarContratista() {
    }

    public void eliminarContratista() {

    }

    // ===============================================================================================================
    // METODOS DEL CONTRATANTE

    public void crearContrato() {

    }

    public void consultarContratos() {

    }

    public void actualizarContrato() {

    }

    public void eliminarContrato() {

    }
    // ===============================================================================================================
    // METODOS DEL CONTRATISTA

    // falta mirar esto( c: Contrato, f: FaseContrato,
    //informe: String) en los atributos para iniciar este metodo
    public void cambiarEstadoContrato() {

    }

    // ===============================================================================================================
    // VERIFICACION SI EL USUARIO EXISTE USANDO LA LLAVE NUMERO DEL DOCUMENTO
    public boolean numeroDocumentoExiste(String numeroDocumentoBuscar) {
        return usuarios.containsKey(numeroDocumentoBuscar);
    }


}
