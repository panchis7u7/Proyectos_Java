/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agregar_gestionar;
import agregar_gestionar.ClienteDTO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author Katherine Arzate
 */
public class ClienteDAO {
    Connection conector = null;
    public ClienteDAO(Connection conn){
        conector = conn;
    }
    
    public int agregarCliente(ClienteDTO cliente){
        PreparedStatement objetoSentSql = null;
        ResultSet generatedKeys = null;
        int id = 0;
        
        String sql = "INSERT INTO Clientes "
                +  "(id_cliente, nombre, apellidos, direccion, telefono, correo, ciudad)"
                +  "VALUES (?,?,?,?,?,?,?)";
        try{
            conector.setAutoCommit(false);
            objetoSentSql = conector.prepareStatement(sql, 
                PreparedStatement.RETURN_GENERATED_KEYS);
            objetoSentSql.setInt(1, cliente.getId_cliente());
            objetoSentSql.setString(2, cliente.getNombre());
            objetoSentSql.setString(3, cliente.getApellidos());
            objetoSentSql.setString(4, cliente.getDireccion());
            objetoSentSql.setString(5, cliente.getTelefono());
            objetoSentSql.setString(6, cliente.getCorreo());
            objetoSentSql.setString(7, cliente.getCiudad());
            objetoSentSql.executeUpdate();
            generatedKeys = objetoSentSql.getGeneratedKeys();
            if(generatedKeys.next()){
                id = generatedKeys.getInt(1);
            }
            conector.commit();
            
        } catch (SQLException ex) {
            try{
                conector.rollback();
            }catch(SQLException ex1){
                System.out.println("Error en recuperación de transacción");
            }
        }
        return id;
    }
}
