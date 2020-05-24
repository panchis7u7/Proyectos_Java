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
public class GarajeDTO {
    /*
        id_garaje INTEGER AUTO_INCREMENT PRIMARY KEY,
	direccion CHAR(30) NOT NULL,
	ciudad CHAR(15),
	telefono CHAR(10) NOT NULL
    */
    private int id_garaje;
    private String direccion, ciudad, telefono; 

    public int getId_garaje() {
        return id_garaje;
    }

    public void setId_garaje(int id_garaje) {
        this.id_garaje = id_garaje;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
}
