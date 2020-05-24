/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agregar_gestionar;
import agregar_gestionar.VehiculoDTO;
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
public class VehiculoDAO {
    Connection conector = null;
    public VehiculoDAO(Connection conn){
        conector = conn;
    }
    
    public int agregarVehiculo(VehiculoDTO vehiculo){
        PreparedStatement objetoSentSql = null;
        ResultSet generatedKeys = null;
        int id = 0;
        
        String sql = "INSERT INTO Vehiculos "
                +  "(id_vehiculo, matricula, tipo, marca, modelo, fabrica, costo, carga)"
                +  "VALUES (?,?,?,?,?,?,?,?)";    
        
         try{
            conector.setAutoCommit(false);
            objetoSentSql = conector.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            objetoSentSql.setInt(1, vehiculo.getId_vehiculo());
            objetoSentSql.setString(2, vehiculo.getMatricula());
            objetoSentSql.setString(3, vehiculo.getTipo());
            objetoSentSql.setString(4, vehiculo.getMarca());
            objetoSentSql.setString(5, vehiculo.getModelo());
            objetoSentSql.setString(6, vehiculo.getFabrica());
            objetoSentSql.setFloat(7, vehiculo.getCosto());
            objetoSentSql.setInt(8, vehiculo.getCarga());
            
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
