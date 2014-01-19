
package Dal;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JD-W
 */
public class Dal {
    
    private Connection con;
    private Statement statement;
    
    public Dal()
    {
        //Skapar objektet med loginkonfiguration.
        SQLdata sqld = new SQLdata();
        
        String user = sqld.getLogin();
        String passw = sqld.getPassw();
        String url = sqld.getUrl();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Dal.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.con = DriverManager.getConnection(url, user, passw);
            this.statement = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Dal.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("System message: Connection to DB complete.");
    }
    
    private void sendQuery(String query)
    {
        try {
            statement.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(Dal.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    private ResultSet getQuery(String query) 
    {    
        
        try {
           ResultSet result = statement.executeQuery(query);
           return result;   
        } catch (SQLException ex) {
            Logger.getLogger(Dal.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
         
    }
    
    public ResultSet getTableData(String tableName) 
    {
        String query = "select * from dbo.\"CRONUS Sverige AB$" + tableName + "\"";
        return getQuery(query);
    }
    
    
    
    
    
    
}
