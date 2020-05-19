package prii_u301_agencia;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 *
 * @author smadr
 */
public abstract class Vehiculo implements Transporte, Serializable{
    private String marca;
    private String modelo;
    private Fecha fabricacion;
    private double costo;
    public Vehiculo(){
        marca = "Marca";modelo = "modelo";
        fabricacion = new Fecha(); costo = 0.0;
    }
 public Vehiculo(String marca, String modelo, byte dia, byte mes, short anio,
         double costo){
        this.marca = marca;
        this.modelo = modelo;
        fabricacion = new Fecha(dia, mes, anio);
        this.costo = costo;
    }    
    public Vehiculo(Scanner flujo) throws InputMismatchException{
        marca = flujo.next();
        modelo = flujo.next();
        fabricacion = new Fecha(flujo);
        costo = flujo.nextDouble();
    }
    public double devolverCosto(){
        return costo;
    }
    public double calcularPrecio(){
        double ganancia = costo*UTILIDAD;
        double precio = (costo+ganancia)*(1.0 + IVA);
        return precio;
    }
    public String toString(){
        return Util.formato(marca,ANCHO2)+Util.formato(modelo, ANCHO2)+
               Util.formato(fabricacion, ANCHO2)+Util.formato(costo, ANCHO3)+
               Util.formato(calcularPrecio(), ANCHO3);
    }
    public String toString(String tab){
        return marca+tab+modelo+tab+Util.formato(fabricacion, ANCHO1)+tab+
                Util.formato(costo, ANCHO2)+tab+
                Util.formato(calcularPrecio(), ANCHO2);
    }
}