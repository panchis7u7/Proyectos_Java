package agregar_gestionar;

/**
 *
 * @author Katherine Arzate
 */
public class ReservasagenciaDTO {
    /*
        nombre CHAR(15) NOT NULL,
    	ciudad CHAR(15) NOT NULL,
        fecha_inicio DATE NOT NULL,
	fecha_final DATE NOT NULL,
    */
    
    //select (max(id_cliente)+1) from clientes
    private String nombre, ciudad, fecha_inicio, fecha_final;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
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
    
}
