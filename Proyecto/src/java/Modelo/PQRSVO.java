package Modelo;

public class PQRSVO {

    
    private String radicado;
    private String documento;
    private String nombre;
    private String telefono;
    private String correo;
    private String estado;
    private String tipo;
    private String codigo;
    private String categoria;
    private String solicitud;
    private String respuesta;
    private String fecha;


    public PQRSVO(String radicado, String documento, String nombre, String telefono, String correo, String estado, String tipo, String codigo, String categoria, String solicitud) {
        this.radicado = radicado;
        this.documento = documento;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.estado = estado;
        this.tipo = tipo;
        this.codigo = codigo;
        this.categoria = categoria;
        this.solicitud = solicitud;
    }

    public PQRSVO() {
    }

    public String getRadicado() {
        return radicado;
    }

    public void setRadicado(String radicado) {
        this.radicado = radicado;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(String solicitud) {
        this.solicitud = solicitud;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    
}
