package prii_u301_agencia;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * @author Katherine Arzate Serrano
 * @author Carlos Sebastian Madrigal Rodriguez
 */
public class Programa {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JTextArea jtxtarea_area = new JTextArea(20,80);
        jtxtarea_area.setTabSize(7);
        JScrollPane areaSc = new JScrollPane(jtxtarea_area);
        File archivo = new File ("AgenciaHer.txt");
        try {
            Scanner flujo = new Scanner(archivo);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Programa.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(areaSc, ex);
            Agencia obj = new Agencia();
            obj.leerDeArchivo();
            obj.informe();
        }
    }   
}