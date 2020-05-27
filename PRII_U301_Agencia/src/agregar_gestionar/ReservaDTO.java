/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agregar_gestionar;

/**
 *
 * @author Katherine Arzate
 */
public class ReservaDTO {
/*
        id_reserva INTEGER AUTO_INCREMENT PRIMARY KEY,
	fecha_inicio DATE NOT NULL,
	fecha_final DATE NOT NULL,
	precio_t DECIMAL(8,2) NOT NULL,
	ciudad CHAR(15) NOT NULL
    */

    private int id_reserva;
    private float precio_t;
    private String fecha_inicio, fecha_final, ciudad;

    public int getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(int id_reserva) {
        this.id_reserva = id_reserva;
    }

    public float getPrecio_t() {
        return precio_t;
    }

    public void setPrecio_t(float precio_t) {
        this.precio_t = precio_t;
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

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    
    
}
