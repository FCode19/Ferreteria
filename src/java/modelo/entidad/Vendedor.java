
package modelo.entidad;

public class Vendedor {

   private int idVendedor;
    private String nombre;
    private String usuario;
    private String contraseña;
    private String correo;
    private boolean estado;
    private Rol rol;

    public Vendedor() {
    }

    public Vendedor(int idVendedor, String nombre, String usuario, String contraseña, String correo, boolean estado, Rol rol) {
        this.idVendedor = idVendedor;
        this.nombre = nombre;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.correo = correo;
        this.estado = estado;
        this.rol = rol;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Vendedor{" + "idVendedor=" + idVendedor + ", nombre=" + nombre + ", usuario=" + usuario + ", contrase\u00f1a=" + contraseña + ", correo=" + correo + ", estado=" + estado + ", rol=" + rol + '}';
    }
    
}
