package agregar_gestionar;

/**
 *
 * @author Katherine Arzate
 */
public class VehiculoDTO {
/*
        id_vehiculo INTEGER AUTO_INCREMENT,
	matricula CHAR(12), 
	tipo CHAR(20) NOT NULL,
	marca CHAR(20) NOT NULL,
	modelo CHAR(20) NOT NULL,
	fabrica DATE NOT NULL,
	costo DECIMAL(10,2) NOT NULL,
	carga INTEGER,
	PRIMARY KEY(id_vehiculo, matricula)
    */    

    private int id_vehiculo, carga;
    private float costo;
    private String matricula, tipo, marca, modelo, fabrica;

    public int getId_vehiculo() {
        return id_vehiculo;
    }

    public void setId_vehiculo(int id_vehiculo) {
        this.id_vehiculo = id_vehiculo;
    }

    public int getCarga() {
        return carga;
    }

    public void setCarga(int carga) {
        this.carga = carga;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getFabrica() {
        return fabrica;
    }

    public void setFabrica(String fabrica) {
        this.fabrica = fabrica;
    }
    
}
