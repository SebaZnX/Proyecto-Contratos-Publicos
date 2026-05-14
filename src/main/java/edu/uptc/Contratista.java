package edu.uptc;

public class Contratista extends Usuario{
    private boolean esEntidadPublica;
    private String areDesempenho;

    public Contratista(TipoPersona tipoPersona, String tipoDocumento, String numeroDocumento, String nombre,
                       String correo, String contrasenha, String telefono, String direccion, String ciudad, boolean esEntidadPublica, String areDesempenho) {
        super(tipoPersona, tipoDocumento, numeroDocumento, nombre, correo, contrasenha, telefono, direccion, ciudad);
        this.esEntidadPublica = esEntidadPublica;
        this.areDesempenho = areDesempenho;
    }

    public boolean isEsEntidadPublica() {
        return esEntidadPublica;
    }

    public void setEsEntidadPublica(boolean esEntidadPublica) {
        this.esEntidadPublica = esEntidadPublica;
    }

    public String getAreDesempenho() {
        return areDesempenho;
    }

    public void setAreDesempenho(String areDesempenho) {
        this.areDesempenho = areDesempenho;
    }

    public void seleccionarContrato() {

    }

    // falta mirar esto( c: Contrato, f: FaseContrato,
    //informe: String) en los atributos para iniciar este metodo
    public void cambiarEstadoContrato() {

    }

    @Override
    public void mostrarMenu() {

    }
}
