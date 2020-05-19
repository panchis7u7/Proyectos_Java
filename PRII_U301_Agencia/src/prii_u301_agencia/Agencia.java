package prii_u301_agencia;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author smadr
 */
public class Agencia implements Transporte {
    private String nombre;
    File nombreArchivo;   
    private ObjectOutputStream salida;
    private ObjectInputStream entrada;    
    //    private Vehiculo vehiculos[] = new Vehiculo[5];
    private ArrayList <Vehiculo> vehiculos = new ArrayList<Vehiculo>();
    
    public boolean definirArchivo(boolean abrir){
        JFileChooser selectorArchivo = new JFileChooser();
        selectorArchivo.setFileSelectionMode( JFileChooser.FILES_ONLY );
      
        int resultado = abrir?selectorArchivo.showSaveDialog( null ):
            selectorArchivo.showOpenDialog( null );
        // si el usuario hizo clic en el botón Cancelar regresar
        if ( resultado == JFileChooser.CANCEL_OPTION )
            return false;

        nombreArchivo = selectorArchivo.getSelectedFile(); 
        // mostrar error si es inválido
        if ( nombreArchivo == null || nombreArchivo.getName().equals( "" ) ){
            JOptionPane.showMessageDialog( null, "Nombre de archivo inválido", 
            "Nombre de archivo inválido", JOptionPane.ERROR_MESSAGE );
            return false;
        }
        return true;
    }
    public void leerDeArchivo() {                                              
       if (this.definirArchivo(false)){
            try {
            entrada = new ObjectInputStream(new FileInputStream(nombreArchivo));
         }
         catch ( IOException excepcionES ) {
            JOptionPane.showMessageDialog( null, "Error al abrir el archivo", 
            "Error", JOptionPane.ERROR_MESSAGE );
            return;
         }
         boolean band = true;
         while (band) {
             try {
                vehiculos.add( ( Vehiculo ) entrada.readObject() );
            }
            catch ( EOFException excepcionFinDeArchivo ) {
                band = false;
            }
            catch ( IOException excepcionES ) {
                JOptionPane.showMessageDialog( null,
                "Error al leer del archivo"+excepcionES,
                "Error de lectura", JOptionPane.ERROR_MESSAGE );
                return;
            }
            catch ( ClassNotFoundException excepcionClaseNoEncontrada ) {
                JOptionPane.showMessageDialog(null,"No se pudo crear el objeto",
                "Clase no encontrada", JOptionPane.ERROR_MESSAGE );
                return;
            }
         }
         try {
             entrada.close();
         }
         catch( IOException excepcionES ) {
             JOptionPane.showMessageDialog( null, "Error al cerrar el archivo", 
             "Error", JOptionPane.ERROR_MESSAGE );
             System.exit( 1 );
         }
        }
    }
    public void guardarEnArchivo() {                                               
       if (this.definirArchivo(true)){
            try {
                salida = new ObjectOutputStream(
                         new FileOutputStream( nombreArchivo ) );
            }
            catch ( IOException excepcionES ) {
                JOptionPane.showMessageDialog(null,"Error al abrir el archivo", 
                "Error", JOptionPane.ERROR_MESSAGE );
            }
            for (Vehiculo i : vehiculos)
               try {
                   salida.writeObject( (Vehiculo) i );
                   salida.flush();
               }
               catch ( IOException excepcionES ) {
                   JOptionPane.showMessageDialog( null,
                   "Error al escribir en el archivo"+excepcionES,
                   "Excepción de ES", JOptionPane.ERROR_MESSAGE );
               }
            try {
                salida.close();
            }
            catch( IOException excepcionES ) {
                JOptionPane.showMessageDialog(null,"Error al cerrar el archivo", 
                "Error", JOptionPane.ERROR_MESSAGE );
                System.exit( 1 );
            }
       }
    }                     
    private String encabezado(){
        return Util.formato("Tipo", ANCHO1)+Util.formato("Marca", ANCHO2)+
               Util.formato("Modelo", ANCHO2)+
               Util.formato("Fábrica", ANCHO2)+Util.formato("Costo", ANCHO3)+
               Util.formato("Precio", ANCHO3)+Util.formato("Carga", ANCHO2);
    }
    private String encabezado(String tab){
        return "Tipo"+tab+"Marca"+tab+"Modelo"+tab+"Fábrica"+tab+"Costo"+
               tab+"Precio"+tab+"Carga";    
    }
    public Agencia(){
    }
    public void leerDeArchivo(Scanner flujo)
           throws ArrayIndexOutOfBoundsException {
        nombre = flujo.next();
        for (int i = 0 ; flujo.hasNext(); i++){
            String tipo = flujo.next();
            if (tipo.compareToIgnoreCase("A")==0)
                try{
//                  vehiculos[i] = new Auto(flujo);
                    vehiculos.add(new Auto(flujo));
                }catch (InputMismatchException e){
//                  vehiculos[i] = new Auto();
                    vehiculos.add(new Auto());
                }
            else
                try {
//                  vehiculos[i] = new Camioneta(flujo);
                    vehiculos.add(new Camioneta(flujo));                    
                } catch (InputMismatchException e){
//                  vehiculos[i] = new Camioneta();
                    vehiculos.add(new Camioneta(flujo));                    
                } 
        }
    }
    public double calcularPrecio(){
        return costearInventario();
    }
    public void informe(){
        System.out.println("\nAgencia automotriz "+nombre+"\n");        
        mostrarVehiculos();
        System.out.println("\n\nCosto total del inventario "+
               Util.formato(costearInventario(), ANCHO3));
    }
    public double costearInventario(){
        double costoTotal = 0.0;
        for (Vehiculo i : vehiculos)
            costoTotal += i.devolverCosto();
        return costoTotal;
    }   
    public void mostrarVehiculos(){
        String tipo;
        System.out.println("\nVehiculos\n");
        System.out.println(encabezado());
        for (Vehiculo  i: vehiculos){
            if (i instanceof Auto)
                 tipo = "Automovil";
            else tipo = "Camioneta";
            System.out.println(Util.formato(tipo, ANCHO1)+i);
        }
    }
}