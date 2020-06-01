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
import java.util.ArrayList;
import java.util.List;
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
    
    
    public List<ClienteDTO> clienteGeneralDAO (){
        List<ClienteDTO> clientes = new ArrayList<>();
        PreparedStatement consulta = null;
        ResultSet resultSet = null;
        String consultasSQL = "SELECT "
                + "id_cliente, nombre, apellidos, direccion, telefono, correo, ciudad "
                + "FROM Clientes";
            
        try {
            consulta = conector.prepareStatement(consultasSQL);
            resultSet = consulta.executeQuery();
            
            while(resultSet.next()){
                ClienteDTO uncliente = new ClienteDTO();
                uncliente.setId_cliente(resultSet.getInt(1));
                uncliente.setNombre(resultSet.getString(2));
                uncliente.setApellidos(resultSet.getString(3));
                uncliente.setDireccion(resultSet.getString(4));
                uncliente.setTelefono(resultSet.getString(5));
                uncliente.setCorreo(resultSet.getString(6));
                uncliente.setCiudad(resultSet.getString(7));
                clientes.add(uncliente);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clientes;
    }
    
    
    public int actualizarCliente(ClienteDTO nuevoCliente){
        PreparedStatement sentenciaSql = null;
        int resultado = 0;
        String sql = "UPDATE Clientes SET "
                + "nombre=?, apellidos=?, direccion=?, telefono=?, correo=?, ciudad=? "
                + "WHERE id_cliente=?";
        try {
            conector.setAutoCommit(false);
            sentenciaSql = conector.prepareStatement(sql);
            sentenciaSql.setString(1, nuevoCliente.getNombre());
            sentenciaSql.setString(2, nuevoCliente.getApellidos());
            sentenciaSql.setString(3, nuevoCliente.getDireccion());
            sentenciaSql.setString(4, nuevoCliente.getTelefono());
            sentenciaSql.setString(5, nuevoCliente.getCorreo());
            sentenciaSql.setString(6, nuevoCliente.getCiudad());
            sentenciaSql.setInt(7, nuevoCliente.getId_cliente());
            
            sentenciaSql.executeUpdate();
            conector.commit();
            resultado = nuevoCliente.getId_cliente();
        } catch (SQLException ex) {
            resultado = 0;
            ex.printStackTrace();
            try {
                conector.rollback();
            } catch (SQLException ex1) {
                System.out.println("Error de rollback");
            }
        }
        return resultado;
    }
    
    public int borrarCliente(ClienteDTO cliente){
        PreparedStatement sentenciaSql = null;
        int resultado = 0;
        String sql = "DELETE FROM Clientes WHERE id_cliente=?";
        try {
            conector.setAutoCommit(false);
            sentenciaSql = conector.prepareStatement(sql);
            sentenciaSql.setInt(1, cliente.getId_cliente());
            sentenciaSql.executeUpdate();
            conector.commit();
            resultado = cliente.getId_cliente();
        } catch (SQLException ex) {
            resultado = 0;
            ex.printStackTrace();
            try {
                conector.rollback();
            } catch (SQLException ex1) {
                System.out.println("Error de rollback");
            }
        }
        return resultado;
    }
}
