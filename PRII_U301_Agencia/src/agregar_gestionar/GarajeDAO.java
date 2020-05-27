/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agregar_gestionar;
import agregar_gestionar.GarajeDTO;
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
public class GarajeDAO {
    Connection conector = null;
    public GarajeDAO(Connection conn){
        conector = conn;
    }
    
    public int agregarGaraje(GarajeDTO garaje){
        PreparedStatement objetoSentSql = null;
        ResultSet generatedKeys = null;
        int id = 0;
        
        String sql = "INSERT INTO Garajes "
                +  "(id_garaje, direccion, ciudad, telefono)"
                +  "VALUES (?,?,?,?)";    
        
         try{
            conector.setAutoCommit(false);
            objetoSentSql = conector.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            objetoSentSql.setInt(1, garaje.getId_garaje());
            objetoSentSql.setString(2, garaje.getDireccion());
            objetoSentSql.setString(3, garaje.getCiudad());
            objetoSentSql.setString(4, garaje.getTelefono());
            
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
    
    
    public List<GarajeDTO> garajeGeneralDAO (){
        List<GarajeDTO> garaje = new ArrayList<>();
        PreparedStatement consulta = null;
        ResultSet resultSet = null;
        String consultasSQL = "SELECT "
                + "id_garaje, direccion, ciudad, telefono "
                + "FROM Garajes";
        try {
            consulta = conector.prepareStatement(consultasSQL);
            resultSet = consulta.executeQuery();
            
            while(resultSet.next()){
                GarajeDTO ungaraje = new GarajeDTO();
                ungaraje.setId_garaje(resultSet.getInt(1));
                ungaraje.setDireccion(resultSet.getString(2));
                ungaraje.setCiudad(resultSet.getString(3));
                ungaraje.setTelefono(resultSet.getString(4));
                garaje.add(ungaraje);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(GarajeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return garaje;
    }
    
    
    public int actualizarGaraje(GarajeDTO nuevoGaraje){
        PreparedStatement sentenciaSql = null;
        int resultado = 0;
        String sql = "UPDATE Garajes SET "
                + "direccion=?, ciudad=?, telefono=? "
                + "WHERE id_garaje=?";
        try {
            conector.setAutoCommit(false);
            sentenciaSql = conector.prepareStatement(sql);
            sentenciaSql.setString(1, nuevoGaraje.getDireccion());
            sentenciaSql.setString(2, nuevoGaraje.getCiudad());
            sentenciaSql.setString(3, nuevoGaraje.getTelefono());
            sentenciaSql.setInt(4, nuevoGaraje.getId_garaje());
            
            sentenciaSql.executeUpdate();
            conector.commit();
            resultado = nuevoGaraje.getId_garaje();
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
    
    public int borrarGaraje(GarajeDTO garaje){
        PreparedStatement sentenciaSql = null;
        int resultado = 0;
        String sql = "DELETE FROM Garajes WHERE id_garaje=?";
        try {
            conector.setAutoCommit(false);
            sentenciaSql = conector.prepareStatement(sql);
            sentenciaSql.setInt(1, garaje.getId_garaje());
            sentenciaSql.executeUpdate();
            conector.commit();
            resultado = garaje.getId_garaje();
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
