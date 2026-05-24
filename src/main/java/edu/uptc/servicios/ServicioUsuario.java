package edu.uptc.servicios;

import edu.uptc.dominio.Administrador;
import edu.uptc.dominio.Usuario;
import edu.uptc.enums.TipoDocumento;
import edu.uptc.enums.TipoPersona;

import java.util.HashMap;

public class ServicioUsuario {
    private HashMap<String, Usuario> usuarios;

    // METODO CONSTRUCTOR INICIANDO LAS LISTAS DE LOS USUARIOS

    public ServicioUsuario() {
        this.usuarios = new HashMap<>();
    }

    // METODOS DEL ADMINISTRADOR

    public void crearContratante() {

    }

    public void consultarContratantes() {

    }

    public void actualizarContratante() {

    }

    public void eliminarContratante() {

    }

    public void crearContratista() {

    }

    public void consultarContratistas() {

    }

    public void actualizarContratista() {
    }

    public void eliminarContratista() {

    }

    // METODOS DEL CONTRATANTE

    public void crearContrato() {

    }

    public void consultarContratos() {

    }

    public void actualizarContrato() {

    }

    public void eliminarContrato() {

    }

    // METODOS DEL CONTRATISTA

    // falta mirar esto( c: Contrato, f: FaseContrato,
    //informe: String) en los atributos para iniciar este metodo
    public void cambiarEstadoContrato() {

    }

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

        Usuario usuarioAdmin = new Administrador(tipoPersona, tipoDocumento, numeroDocumento, nombre, correo,
                contrasenha, telefono, direccion, ciudad);
        this.usuarios.put(numeroDocumento, usuarioAdmin);
    }

    // METODOS PARA EL LOGIN


}
