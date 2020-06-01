package agregar_gestionar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Katherine Arzate
 */
public class ReservasclienteDAO {
    Connection conector = null;
    public ReservasclienteDAO(Connection conn){
        conector = conn;    

    }

    public List<ReservasclienteDTO> reservaclienteGeneralDAO (){
        List<ReservasclienteDTO> reservacliente = new ArrayList<>();
        PreparedStatement consulta = null;
        ResultSet resultSet = null;
        String consultasSQL = "SELECT nombre, apellidos, fecha_inicio, fecha_final, reservas.precio_t FROM clientes " +
            "INNER JOIN reservasclientes ON reservasclientes.id_cliente = clientes.id_cliente " +
            "INNER JOIN reservas ON reservas.id_reserva = reservasclientes.id_reserva";
        try {
            consulta = conector.prepareStatement(consultasSQL);
            resultSet = consulta.executeQuery();
            
            while(resultSet.next()){
                ReservasclienteDTO unreserva = new ReservasclienteDTO();
                unreserva.setNombre(resultSet.getString(1));
                unreserva.setApellidos(resultSet.getString(2));
                unreserva.setFecha_inicio(resultSet.getString(3));
                unreserva.setFecha_final(resultSet.getString(4));
                unreserva.setPrecio_t(resultSet.getFloat(5));
                reservacliente.add(unreserva);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReservasclienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reservacliente;
    }
    
    public int getId(String query){
        PreparedStatement consulta = null;
        int id = 0;
        ResultSet resultSet = null;
        
        try {
            consulta = conector.prepareStatement(query);
            resultSet = consulta.executeQuery();
            
            while(resultSet.next()){
                id = (resultSet.getInt(1));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReservasclienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;    
    }

    
    public int actualizarReservacli(ReservasclienteDTO cliente){
        PreparedStatement sentenciaSql = null;
        int resultado = 0;
        String[] sql = {"UPDATE Clientes SET nombre=?, apellidos=? WHERE id_cliente=?;",
                    "UPDATE Reservas SET fecha_inicio=?, fecha_final=?, precio_t=? WHERE id_reserva=?"};
        String getIdcli = "SELECT id_cliente FROM clientes WHERE " 
                + "concat(clientes.nombre, ' ', clientes.apellidos) = concat('"+cliente.getNombre().trim()+"', ' ', '"+cliente.getApellidos().trim()+"')";;
        int id = getId(getIdcli);
        String getIdres = "SELECT reservasclientes.id_reserva FROM reservas "
            + "INNER JOIN reservasclientes ON reservasclientes.id_reserva = reservas.id_reserva "
            + "WHERE reservasclientes.id_cliente = "+id;
        
        try {
            
            conector.setAutoCommit(false);
            sentenciaSql = conector.prepareStatement(sql[0]);
            sentenciaSql.setString(1, cliente.getNombre());
            sentenciaSql.setString(2, cliente.getApellidos());
            sentenciaSql.setInt(3, id);
            
            sentenciaSql.executeUpdate();
            
            sentenciaSql = conector.prepareStatement(sql[1]);
            sentenciaSql.setString(1, cliente.getFecha_inicio());
            sentenciaSql.setString(2, cliente.getFecha_final());
            sentenciaSql.setFloat(3, cliente.getPrecio_t());
            sentenciaSql.setInt(4, getId(getIdres));
            sentenciaSql.executeUpdate();
            conector.commit();
            resultado = id;
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

