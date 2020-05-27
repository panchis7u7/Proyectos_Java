/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agregar_gestionar;
import agregar_gestionar.AgenciaDTO;
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
public class AgenciaDAO {
    Connection conector = null;
    public AgenciaDAO(Connection conn){
        conector = conn;
    }
    
    public int agregarAgencia(AgenciaDTO agencia){
        PreparedStatement objetoSentSql = null;
        ResultSet generatedKeys = null;
        int id = 0;
        
        String sql = "INSERT INTO Agencias "
                +  "(id_agencia, nombre, ciudad, telefono)"
                +  "VALUES (?,?,?,?)";    
        
         try{
            conector.setAutoCommit(false);
            objetoSentSql = conector.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            objetoSentSql.setInt(1, agencia.getId_agencia());
            objetoSentSql.setString(2, agencia.getNombre());
            objetoSentSql.setString(3, agencia.getCiudad());
            objetoSentSql.setString(4, agencia.getTelefono());
            
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
    
    public List<AgenciaDTO> agenciaGeneralDAO (){
        List<AgenciaDTO> agencia = new ArrayList<>();
        PreparedStatement consulta = null;
        ResultSet resultSet = null;
        String consultasSQL = "SELECT "
                + "id_agencia, nombre, ciudad, telefono "
                + "FROM Agencias";
        try {
            consulta = conector.prepareStatement(consultasSQL);
            resultSet = consulta.executeQuery();
            
            while(resultSet.next()){
                AgenciaDTO unagencia = new AgenciaDTO();
                unagencia.setId_agencia(resultSet.getInt(1));
                unagencia.setNombre(resultSet.getString(2));
                unagencia.setCiudad(resultSet.getString(3));
                unagencia.setTelefono(resultSet.getString(4));
                agencia.add(unagencia);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AgenciaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return agencia;
    }
    
    
    public int actualizarAgencia(AgenciaDTO nuevoAgencia){
        PreparedStatement sentenciaSql = null;
        int resultado = 0;
        String sql = "UPDATE Agencias SET "
                + "nombre=?, ciudad=?, telefono=? "
                + "WHERE id_agencia=?";
        try {
            conector.setAutoCommit(false);
            sentenciaSql = conector.prepareStatement(sql);
            sentenciaSql.setString(1, nuevoAgencia.getNombre());
            sentenciaSql.setString(2, nuevoAgencia.getCiudad());
            sentenciaSql.setString(3, nuevoAgencia.getTelefono());
            sentenciaSql.setInt(4, nuevoAgencia.getId_agencia());
            
            sentenciaSql.executeUpdate();
            conector.commit();
            resultado = nuevoAgencia.getId_agencia();
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
    
    public int borrarAgencia(AgenciaDTO agencia){
        PreparedStatement sentenciaSql = null;
        int resultado = 0;
        String sql = "DELETE FROM Agencias WHERE id_agencia=?";
        try {
            conector.setAutoCommit(false);
            sentenciaSql = conector.prepareStatement(sql);
            sentenciaSql.setInt(1, agencia.getId_agencia());
            sentenciaSql.executeUpdate();
            conector.commit();
            resultado = agencia.getId_agencia();
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
