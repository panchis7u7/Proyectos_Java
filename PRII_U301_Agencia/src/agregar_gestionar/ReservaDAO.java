/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agregar_gestionar;
import agregar_gestionar.ReservaDTO;
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
public class ReservaDAO {
    Connection conector = null;
    public ReservaDAO(Connection conn){
        conector = conn;
    }
    
    public int agregarReserva(ReservaDTO reserva){
        PreparedStatement objetoSentSql = null;
        ResultSet generatedKeys = null;
        int id = 0;
        
        String sql = "INSERT INTO Reservas "
                +  "(id_reserva, fecha_inicio, fecha_final, precio_t, ciudad)"
                +  "VALUES (?,?,?,?,?)";    
        
         try{
            conector.setAutoCommit(false);
            objetoSentSql = conector.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            objetoSentSql.setInt(1, reserva.getId_reserva());
            objetoSentSql.setString(2, reserva.getFecha_inicio());
            objetoSentSql.setString(3, reserva.getFecha_final());
            objetoSentSql.setFloat(4, reserva.getPrecio_t());
            objetoSentSql.setString(5, reserva.getCiudad());
            
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
    
    
    public int agregarReservaCliente(ReservaDTO reserva, ClienteDTO cliente){
        PreparedStatement objetoSentSql = null;
        ResultSet generatedKeys = null;
        int id = 0;
        
        String[] sql = {"INSERT INTO Reservas (id_reserva, fecha_inicio, fecha_final, precio_t, ciudad) "
                + "VALUES (?,?,?,?,?);", 
                 "INSERT INTO ReservasClientes (id_cliente, id_reserva) VALUES (?,?)"};    
        
         try{
            conector.setAutoCommit(false);
            objetoSentSql = conector.prepareStatement(sql[0], PreparedStatement.RETURN_GENERATED_KEYS);
            objetoSentSql.setInt(1, reserva.getId_reserva());
            objetoSentSql.setString(2, reserva.getFecha_inicio());
            objetoSentSql.setString(3, reserva.getFecha_final());
            objetoSentSql.setFloat(4, reserva.getPrecio_t());
            objetoSentSql.setString(5, reserva.getCiudad());
            
            objetoSentSql.executeUpdate();
            generatedKeys = objetoSentSql.getGeneratedKeys();
            
            if(generatedKeys.next()){
                id = generatedKeys.getInt(1);
            }
            
            objetoSentSql = conector.prepareStatement(sql[1], PreparedStatement.RETURN_GENERATED_KEYS);
            objetoSentSql.setInt(1, cliente.getId_cliente());
            objetoSentSql.setInt(2, getId("SELECT MAX(id_reserva) FROM Reservas"));
            
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
    
    public int getId(String query){
        PreparedStatement consulta = null;
        ResultSet resultSet = null;
        int id = 0;
        try {
            consulta = conector.prepareStatement(query);
            resultSet = consulta.executeQuery();
            
            while(resultSet.next()){
                id = resultSet.getInt(1);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
    
    public List<ReservaDTO> reservaGeneralDAO (){
        List<ReservaDTO> reserva = new ArrayList<>();
        PreparedStatement consulta = null;
        ResultSet resultSet = null;
        String consultasSQL = "SELECT "
                + "id_reserva, fecha_inicio, fecha_final, precio_t, ciudad "
                + "FROM Reservas";
        try {
            consulta = conector.prepareStatement(consultasSQL);
            resultSet = consulta.executeQuery();
            
            while(resultSet.next()){
                ReservaDTO unreserva = new ReservaDTO();
                unreserva.setId_reserva(resultSet.getInt(1));
                unreserva.setFecha_inicio(resultSet.getString(2));
                unreserva.setFecha_final(resultSet.getString(3));
                unreserva.setPrecio_t(resultSet.getFloat(4));
                unreserva.setCiudad(resultSet.getString(5));
                reserva.add(unreserva);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReservaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reserva;
    }
    
    
    public int actualizarReserva(ReservaDTO nuevoReserva){
        PreparedStatement sentenciaSql = null;
        int resultado = 0;
        String sql = "UPDATE Reservas SET "
                + "fecha_inicio=?, fecha_final=?, precio_t=?, ciudad=? "
                + "WHERE id_reserva=?";
        try {
            conector.setAutoCommit(false);
            sentenciaSql = conector.prepareStatement(sql);
            sentenciaSql.setString(1, nuevoReserva.getFecha_inicio());
            sentenciaSql.setString(2, nuevoReserva.getFecha_final());
            sentenciaSql.setFloat(3, nuevoReserva.getPrecio_t());
            sentenciaSql.setString(4, nuevoReserva.getCiudad());
            sentenciaSql.setInt(5, nuevoReserva.getId_reserva());
            
            sentenciaSql.executeUpdate();
            conector.commit();
            resultado = nuevoReserva.getId_reserva();
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
    
    public int borrarReserva(ReservaDTO reserva){
        
        PreparedStatement sentenciaSql = null;
        int resultado = 0;
        String sql = "DELETE FROM Reservas WHERE id_reserva=?";
        try {
            conector.setAutoCommit(false);
            sentenciaSql = conector.prepareStatement(sql);
            sentenciaSql.setInt(1, reserva.getId_reserva());
            sentenciaSql.executeUpdate();
            conector.commit();
            resultado = reserva.getId_reserva();
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
