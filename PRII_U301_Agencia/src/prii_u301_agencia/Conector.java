package prii_u301_agencia;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 * @author Katherine Arzate Serrano
 * @author Carlos Sebastian Madrigal Rodriguez
 */
public class Conector {
    public java.sql.Connection miconector = null;
    String contrasena = "";
 
    public Conector() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/Agencia";
            miconector = DriverManager.getConnection(url, "root", contrasena);
            if(miconector != null)System.out.println(" Conexión exitosa! ");   
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
        } catch(SQLException ex){
            Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error al hacer conexion con la base de datos!");
        }
    }    
     
    public void cerrar(){
        try {
            miconector.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        }
    
}