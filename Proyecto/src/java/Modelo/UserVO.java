package Modelo;

public class UserVO extends Tipo {

    private String usuario;
    private String contrasena;
    private String codigo;
    private String tipodocumento;
    private String documento;
    private String nombre;
    private String telefono;
    private String correo;

    /*Liskov, la clase hija puede hacer todo lo que hace la clase padre, no agrega restricciones
    a los métodos de la padre  si un método espera recibir un objeto de tipo "Persona", 
    puede recibir un objeto de tipo "UserVO" y aún así funcionar correctamente*/
    public UserVO() { //Se utiliza para inicializar un objeto User cuando no se conoce su información
    }

    public UserVO(String usuario, String contrasena, String tipo, String codigo, String tipodocumento, String documento, String nombre, String telefono, String correo) {
        super(tipo);//Llama al constructor de la clase padre Persona para inicializar el nombre
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.codigo = codigo;
        this.tipodocumento = tipodocumento;
        this.documento = documento;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTipodocumento() {
        return tipodocumento;
    }

    public void setTipodocumento(String tipodocumento) {
        this.tipodocumento = tipodocumento;
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



}
