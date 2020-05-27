/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agregar_gestionar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Katherine Arzate
 */
public class Conector {
    public Connection miconector = null;
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