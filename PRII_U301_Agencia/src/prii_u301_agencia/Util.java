package prii_u301_agencia;

import java.text.DecimalFormat;

/**
 *
 * @author smadr
 */
public class Util {
    static String formato(String msg, int ancho){
        for(int i = msg.length(); i < ancho; i++)
                msg = msg.concat(" ");
        return msg;
    }
    static String formato(Fecha fecha, int ancho){
        return formato(""+fecha,ancho); // Cuidado con la recursion
    }
    static String formato(double valor, int ancho){
        DecimalFormat fto = new DecimalFormat("0.00");
        return formato(fto.format(valor), ancho);
    }
}