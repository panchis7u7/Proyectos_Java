package prii_u301_agencia;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 * @author Katherine Arzate
 * @author Carlos Sebastian Madrigal Rodriguez
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
        
        String sql = "INSERT INTO clientes "
                +  "(nombre, apellidos, direccion, telefono, correo, ciudad) "
                +  "VALUES (?,?,?,?,?,?)";
        try{
            conector.setAutoCommit(false);
            objetoSentSql = conector.prepareStatement(sql, 
                PreparedStatement.RETURN_GENERATED_KEYS);
            //objetoSentSql.setInt(1, cliente.getId_cliente());
            objetoSentSql.setString(1, cliente.getNombre());
            objetoSentSql.setString(2, cliente.getApellidos());
            objetoSentSql.setString(3, cliente.getDireccion());
            objetoSentSql.setString(4, cliente.getTelefono());
            objetoSentSql.setString(5, cliente.getCorreo());
            objetoSentSql.setString(6, cliente.getCiudad());
            objetoSentSql.executeUpdate();
            generatedKeys = objetoSentSql.getGeneratedKeys();
            if(generatedKeys.next()){
                id = generatedKeys.getInt(1);
            }
            conector.commit();
            
        } catch (SQLException ex) {
            try{
                System.out.println("error: " + ex.toString());
                conector.rollback();
            }catch(SQLException ex1){
                System.out.println("Error en recuperación de transacción");
            }
        }
        return id;
    }
}
