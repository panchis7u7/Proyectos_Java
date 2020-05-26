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
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(VehiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return agencia;
    }
}
