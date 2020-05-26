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
import java.util.ArrayList;
import java.util.List;

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
    
    
    public List<VehiculoDTO> vehiculoGeneralDAO (){
        List<VehiculoDTO> vehiculo = new ArrayList<>();
        PreparedStatement consulta = null;
        ResultSet resultSet = null;
        String consultasSQL = "SELECT "
                + "id_vehiculo, matricula, tipo, marca, modelo, fabrica, costo, carga "
                + "FROM Vehiculos";
        try {
            consulta = conector.prepareStatement(consultasSQL);
            resultSet = consulta.executeQuery();
            
            while(resultSet.next()){
                VehiculoDTO unvehiculo = new VehiculoDTO();
                unvehiculo.setId_vehiculo(resultSet.getInt(1));
                unvehiculo.setMatricula(resultSet.getString(2));
                unvehiculo.setTipo(resultSet.getString(3));
                unvehiculo.setMarca(resultSet.getString(4));
                unvehiculo.setModelo(resultSet.getString(5));
                unvehiculo.setFabrica(resultSet.getString(6));
                unvehiculo.setCosto(resultSet.getFloat(7));
                unvehiculo.setCarga(resultSet.getInt(8));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(VehiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vehiculo;
    }
    
    
    public int actualizarVehiculo(VehiculoDTO nuevoVehiculo){
        PreparedStatement sentenciaSql = null;
        int resultado = 0;
        String sql = "UPDATE Vehiculos SET "
                + "matricula=?, tipo=?, marca=?, modelo=?, fabrica=?, costo=?, carga=? "
                + "WHERE id_vehiculos=?";
        try {
            conector.setAutoCommit(false);
            sentenciaSql = conector.prepareStatement(sql);
            sentenciaSql.setString(1, nuevoVehiculo.getMatricula());
            sentenciaSql.setString(2, nuevoVehiculo.getTipo());
            sentenciaSql.setString(3, nuevoVehiculo.getMarca());
            sentenciaSql.setString(4, nuevoVehiculo.getModelo());
            sentenciaSql.setString(5, nuevoVehiculo.getFabrica());
            sentenciaSql.setFloat(6, nuevoVehiculo.getCosto());
            sentenciaSql.setInt(7, nuevoVehiculo.getCarga());
            sentenciaSql.executeUpdate();
            conector.commit();
            resultado = nuevoVehiculo.getId_vehiculo();
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
    
    public int borrarVehiculo(VehiculoDTO vehiculo){
        PreparedStatement sentenciaSql = null;
        int resultado = 0;
        String sql = "DELETE FROM Vehiculos WHERE id_vehiculos=?";
        try {
            conector.setAutoCommit(false);
            sentenciaSql = conector.prepareStatement(sql);
            sentenciaSql.setInt(1, vehiculo.getId_vehiculo());
            sentenciaSql.executeUpdate();
            conector.commit();
            resultado = vehiculo.getId_vehiculo();
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
