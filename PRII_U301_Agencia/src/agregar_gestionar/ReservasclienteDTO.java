package agregar_gestionar;

/**
 *
 * @author Katherine Arzate
 */
public class ReservasclienteDTO {
    /*
    nombre CHAR(25) NOT NULL,
    apellidos CHAR(30) NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_final DATE NOT NULL,
    precio_t DECIMAL(8,2) NOT NULL,
    */
    
    private String nombre, apellidos, fecha_inicio, fecha_final;
    private float precio_t;
    private int id_reserva, id_cliente;

    public int getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(int id_reserva) {
        this.id_reserva = id_reserva;
    }

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

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_final() {
        return fecha_final;
    }

    public void setFecha_final(String fecha_final) {
        this.fecha_final = fecha_final;
    }

    public float getPrecio_t() {
        return precio_t;
    }

    public void setPrecio_t(float precio_t) {
        this.precio_t = precio_t;
    }
    
}
