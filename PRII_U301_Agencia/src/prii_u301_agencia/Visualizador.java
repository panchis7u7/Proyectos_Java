package prii_u301_agencia;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
/**
 * @author Carlos Sebastian Madrigal Rodriguez
 * @author Katherine Arzate Serrano
 */
public class Visualizador extends javax.swing.JPanel implements Transporte, Serializable {

    private String nombre;
    File nombreArchivo;   
    private ObjectOutputStream salida;
    private ObjectInputStream entrada;    
    //    private Vehiculo vehiculos[] = new Vehiculo[5];
    private ArrayList <Vehiculo> vehiculos = new ArrayList<Vehiculo>();
    
    public Visualizador() throws FileNotFoundException {
        //super("Agencia Automotriz");
        initComponents();
    }
    
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
    
    public void leerDeTexto(Scanner flujo)
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
    
    public double calcularPrecio(){
        return costearInventario();
    }
    public void informe(JTextArea salida){
        salida.setText("\nAgencia automotriz "+nombre+"\n");
        mostrarVehiculos(salida);
        salida.append("\n\nCosto total del inventario "+
               Util.formato(costearInventario(), ANCHO3));
    }    
    public double costearInventario(){
        double costoTotal = 0.0;
        for (Vehiculo i : vehiculos)
            costoTotal += i.devolverCosto();
        return costoTotal;
    }   
    public void mostrarVehiculos(JTextArea salida){
        String tipo;
        salida.append("\nVehiculos\n");
        salida.append(encabezado("\t"));
        for (Vehiculo  i: vehiculos){
            if (i instanceof Auto)
                 tipo = "Automovil";
            else tipo = "Camioneta";     
            salida.append("\n"+Util.formato(tipo, ANCHO1)+"\t"+
                   i.toString("\t"));
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jtxtarea_area = new javax.swing.JTextArea();
        jbtn_mostrar_informe = new javax.swing.JButton();
        jbtn_guardar_objetos = new javax.swing.JButton();
        jbtn_leer_objetos = new javax.swing.JButton();
        jbtn_leer_texto = new javax.swing.JButton();
        jbtn_agregar = new javax.swing.JButton();
        jtxt_carga = new javax.swing.JTextField();
        jlbl_carga = new javax.swing.JLabel();
        jllbl_año = new javax.swing.JLabel();
        jtxt_anio = new javax.swing.JTextField();
        jlbl_mes = new javax.swing.JLabel();
        jtxt_mes = new javax.swing.JTextField();
        jlbl_dia = new javax.swing.JLabel();
        jtxt_dia = new javax.swing.JTextField();
        jtxt_costo = new javax.swing.JTextField();
        jlbl_costo = new javax.swing.JLabel();
        jlbl_fecha = new javax.swing.JLabel();
        jlbl_modelo = new javax.swing.JLabel();
        jlbl_marca = new javax.swing.JLabel();
        jtxt_modelo = new javax.swing.JTextField();
        jtxt_marca = new javax.swing.JTextField();
        jchbx_Tipo = new javax.swing.JCheckBox();

        jtxtarea_area.setColumns(20);
        jtxtarea_area.setRows(5);
        jScrollPane1.setViewportView(jtxtarea_area);

        jbtn_mostrar_informe.setText("Mostrar Informe");
        jbtn_mostrar_informe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_mostrar_informeActionPerformed(evt);
            }
        });

        jbtn_guardar_objetos.setText("Guardar Objetos");
        jbtn_guardar_objetos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_guardar_objetosActionPerformed(evt);
            }
        });

        jbtn_leer_objetos.setText("Leer Objetos");
        jbtn_leer_objetos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_leer_objetosActionPerformed(evt);
            }
        });

        jbtn_leer_texto.setText("Leer de Texto");
        jbtn_leer_texto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_leer_textoActionPerformed(evt);
            }
        });

        jbtn_agregar.setText("Agregar Vehiculo");
        jbtn_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_agregarActionPerformed(evt);
            }
        });

        jtxt_carga.setText("0");

        jlbl_carga.setText("Carga");

        jllbl_año.setText("Año");

        jtxt_anio.setText("2009");

        jlbl_mes.setText("Mes");

        jtxt_mes.setText("1");

        jlbl_dia.setText("Dia\n");

        jtxt_dia.setText("1");

        jlbl_costo.setText("Costo");

        jlbl_fecha.setText("Fecha");

        jlbl_modelo.setText("Modelo");

        jlbl_marca.setText("Marca\n");

        jchbx_Tipo.setText("Camioneta");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbl_modelo)
                            .addComponent(jlbl_marca)
                            .addComponent(jlbl_fecha)
                            .addComponent(jlbl_costo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jtxt_dia, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlbl_dia)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtxt_mes, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlbl_mes)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtxt_anio, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jllbl_año))
                            .addComponent(jtxt_marca, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtxt_modelo, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jchbx_Tipo)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jtxt_costo, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(80, 80, 80)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jlbl_carga)
                                        .addGap(18, 18, 18)
                                        .addComponent(jtxt_carga))
                                    .addComponent(jbtn_agregar))))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jbtn_mostrar_informe)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jbtn_leer_texto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jbtn_leer_objetos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jbtn_guardar_objetos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbtn_leer_texto)
                        .addGap(22, 22, 22)
                        .addComponent(jbtn_leer_objetos))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlbl_marca)
                            .addComponent(jtxt_marca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlbl_modelo)
                            .addComponent(jtxt_modelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtxt_dia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlbl_dia)
                            .addComponent(jtxt_mes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlbl_mes)
                            .addComponent(jlbl_fecha)
                            .addComponent(jtxt_anio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jllbl_año))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxt_costo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtn_guardar_objetos)
                    .addComponent(jlbl_costo)
                    .addComponent(jlbl_carga)
                    .addComponent(jtxt_carga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtn_mostrar_informe)
                    .addComponent(jchbx_Tipo)
                    .addComponent(jbtn_agregar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jbtn_mostrar_informeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_mostrar_informeActionPerformed
        // TODO add your handling code here:
        this.informe(jtxtarea_area);
    }//GEN-LAST:event_jbtn_mostrar_informeActionPerformed

    private void jbtn_guardar_objetosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_guardar_objetosActionPerformed
        // TODO add your handling code here:
        guardarEnArchivo();
    }//GEN-LAST:event_jbtn_guardar_objetosActionPerformed

    private void jbtn_leer_objetosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_leer_objetosActionPerformed
        // TODO add your handling code here:
        leerDeArchivo();
    }//GEN-LAST:event_jbtn_leer_objetosActionPerformed

    private void jbtn_leer_textoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_leer_textoActionPerformed
        // TODO add your handling code here:
        try {
            File archivo = new File ("AgenciaHer.txt");
            Scanner flujo = new Scanner(archivo);
            leerDeTexto(flujo);
        }catch (FileNotFoundException err){}
    }//GEN-LAST:event_jbtn_leer_textoActionPerformed

    private void jbtn_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_agregarActionPerformed
        // TODO add your handling code here:
        String marca = jtxt_marca.getText();
        String modelo = jtxt_modelo.getText();
        byte dia = Byte.parseByte(jtxt_dia.getText());
        byte mes = Byte.parseByte(jtxt_mes.getText());
        short anio = Short.parseShort(jtxt_anio.getText());
        double costo = Double.parseDouble(jtxt_costo.getText());
        if (jchbx_Tipo.isSelected()==false)
        vehiculos.add(new Auto(marca, modelo, dia, mes, anio, costo));
        else {
            short carga = Short.parseShort(jtxt_carga.getText());
            vehiculos.add(new Camioneta(marca, modelo, dia, mes, anio,
                costo, carga));
        }
    }//GEN-LAST:event_jbtn_agregarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtn_agregar;
    private javax.swing.JButton jbtn_guardar_objetos;
    private javax.swing.JButton jbtn_leer_objetos;
    private javax.swing.JButton jbtn_leer_texto;
    private javax.swing.JButton jbtn_mostrar_informe;
    private javax.swing.JCheckBox jchbx_Tipo;
    private javax.swing.JLabel jlbl_carga;
    private javax.swing.JLabel jlbl_costo;
    private javax.swing.JLabel jlbl_dia;
    private javax.swing.JLabel jlbl_fecha;
    private javax.swing.JLabel jlbl_marca;
    private javax.swing.JLabel jlbl_mes;
    private javax.swing.JLabel jlbl_modelo;
    private javax.swing.JLabel jllbl_año;
    private javax.swing.JTextField jtxt_anio;
    private javax.swing.JTextField jtxt_carga;
    private javax.swing.JTextField jtxt_costo;
    private javax.swing.JTextField jtxt_dia;
    private javax.swing.JTextField jtxt_marca;
    private javax.swing.JTextField jtxt_mes;
    private javax.swing.JTextField jtxt_modelo;
    private javax.swing.JTextArea jtxtarea_area;
    // End of variables declaration//GEN-END:variables
}
