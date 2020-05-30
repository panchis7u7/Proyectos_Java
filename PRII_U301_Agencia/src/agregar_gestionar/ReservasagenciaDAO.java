package agregar_gestionar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Katherine Arzate
 */
public class ReservasagenciaDAO {
    Connection conector = null;
    public ReservasagenciaDAO(Connection conn){
        conector = conn;
    }
   
}
