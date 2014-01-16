
package Dal;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JD-W
 */
public class Dal {
    
    Connection con;
    
    
    public Dal()
    {
        String user = "sa";
        String passw = "aiel1234";
        String url = "jdbc:sqlserver://127.0.0.1;databaseName=Navision";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Dal.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.con = DriverManager.getConnection(url, user, passw);
        } catch (SQLException ex) {
            Logger.getLogger(Dal.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("System message: Connection to DB complete.");
    }
    
    
    
    
    
}
