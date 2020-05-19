package prii_u301_agencia;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author smadr
 */
public class Auto extends Vehiculo {
    public Auto(){
        super();
    }
    public Auto(String marca, String modelo, byte dia, byte mes, short anio,double costo){
    super(marca,modelo,dia,mes,anio,costo);
    }    
    
    public Auto(Scanner flujo) throws InputMismatchException{
        super(flujo);
    }
}