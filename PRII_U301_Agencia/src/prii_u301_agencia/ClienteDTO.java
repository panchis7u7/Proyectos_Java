package prii_u301_agencia;

/**
 * @author Katherine Arzate
 * @author Carlos Sebastian Madrigal Rodriguez
 */
public class ClienteDTO {
    /*
    La clase DTO nos sirve para modelar un registro
    
    id_cliente INTEGER PRIMARY KEY,
    nombre CHAR(25) NOT NULL,
    apellidos CHAR(30) NOT NULL,
    direccion CHAR(30) NOT NULL,
    telefono CHAR(10) NOT NULL,
    correo CHAR(40),
    ciudad CHAR(15) NOT NULL    
*/
    
    private int id_cliente;
    private String nombre, apellidos, direccion, telefono, correo, ciudad;

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    
}
