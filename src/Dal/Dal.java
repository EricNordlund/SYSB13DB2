
package Dal;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eric
 */
public class Dal {
    
    private Connection con;
    private Statement statement;
    
    public Dal()
    {
        //Creates the logindata-object
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
        String query = "SELECT * FROM dbo.\"CRONUS Sverige AB$" + tableName + "\"";
        return getQuery(query);
    }
    
    /**
     * Returns metadata for each column in a table.
     * @param tableName Name of the identifying name of the table.
     * @return -
     */
    public ResultSet getTableMetaData(String tableName)
    {
        String query = "SELECT COLUMN_NAME as 'Column name', IS_NULLABLE as 'Is null', DATA_TYPE as 'Data type', CHARACTER_MAXIMUM_LENGTH as Size, COLLATION_NAME as 'Coallition namne' FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'CRONUS Sverige AB$" + tableName + "'";
        return getQuery(query);
    }
    
    public ResultSet getDBKeys()
    {
        String query = "SELECT object_id as ID, name as Name FROM sys.key_constraints";
        return getQuery(query);
    }
    
    public ResultSet getDBIndexes()
    {
        String query = "SELECT object_id as ID, name as Name FROM sys.indexes";
        return getQuery(query);
    }
    
    public ResultSet getDBConstraints()
    {
        String query = "SELECT CONSTRAINT_NAME as Name FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS";
        return getQuery(query);
    }
    
    public ResultSet getDBTables()
    {
        String query = "SELECT TABLE_NAME as Name FROM INFORMATION_SCHEMA.TABLES";
        return getQuery(query);
    }
    
    public ResultSet getDBTablesAlternative()
    {
        String query = "SELECT object_id as ID, name as Name FROM sys.tables";
        return getQuery(query);
    }
    
    public ResultSet getDBTableMostRows()
    {
        String query = "SELECT TOP 1 o.name as Name, MAX(i.rows) as Rows FROM sysobjects o, sysindexes i WHERE o.xtype = 'U' AND i.id = OBJECT_ID(o.name) GROUP BY o.name ORDER BY 2 DESC";
        return getQuery(query);
    }
    
    
    
    
    
    
    
    
}
