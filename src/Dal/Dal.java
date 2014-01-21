package Dal;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The DAL handles the connection between the software and the database.
 *
 * @author G14
 */
public class Dal {

    private Connection con;
    private Statement statement;

    /**
     * The constructor, loads the database drivers, credentials and connects to
     * the DB.
     */
    public Dal() {

        SQLdata sqld = new SQLdata();

        String user = sqld.getLogin();
        String passw = sqld.getPassw();
        String url = sqld.getUrl();

        //Loads the sql driver
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); //Microsoft drivers: sqljdbc4.jar
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Dal.class.getName()).log(Level.SEVERE, "Failed to load SQL driver", ex);

        }
        //Connects to the database.
        try {
            this.con = DriverManager.getConnection(url, user, passw);
            this.statement = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Dal.class.getName()).log(Level.SEVERE, "Failed to connect to database.", ex);

        }
        System.out.println("System message: Connection to DB complete.");
    }

    /**
     * Updates the sql database by sending a query.
     *
     * @param query Query to send
     * @return Returns a ResultSet
     */
    private ResultSet getQuery(String query) {

        try {
            ResultSet result = statement.executeQuery(query);
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(Dal.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    /**
     * Gets the table data.
     *
     * @param tableName Name of the table.
     * @return Returns a ResultSet.
     */
    public ResultSet getTableData(String tableName) {
        String query = "SELECT * FROM dbo.\"CRONUS Sverige AB$" + tableName + "\"";
        return getQuery(query);
    }

    /**
     * Returns metadata for each column in a table.
     *
     * @param tableName Name of the identifying name of the table.
     * @return A ResultSet.
     */
    public ResultSet getTableMetaData(String tableName) {
        String query = "SELECT COLUMN_NAME AS 'Column name', IS_NULLABLE AS 'Is null', DATA_TYPE AS 'Data type', CHARACTER_MAXIMUM_LENGTH AS Size, COLLATION_NAME AS 'Coallition namne' FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'CRONUS Sverige AB$" + tableName + "'";
        return getQuery(query);
    }

    /**
     * Gets all the keys in the database.
     *
     * @return A ResultSet.
     */
    public ResultSet getDBKeys() {
        String query = "SELECT object_id AS ID, name AS Name FROM sys.key_constraints";
        return getQuery(query);
    }

    /**
     * Gets all the indexes from the database.
     *
     * @return A ResultSet.
     */
    public ResultSet getDBIndexes() {
        String query = "SELECT object_id AS ID, name AS Name FROM sys.indexes";
        return getQuery(query);
    }

    /**
     * Returns all the constraints from the database.
     *
     * @return A ResultSet.
     */
    public ResultSet getDBConstraints() {
        String query = "SELECT CONSTRAINT_NAME AS Name FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS";
        return getQuery(query);
    }

    /**
     * Returns a list of the Tables in the database.
     *
     * @return A ResultSet.
     */
    public ResultSet getDBTables() {
        String query = "SELECT TABLE_NAME AS Name FROM INFORMATION_SCHEMA.TABLES";
        return getQuery(query);
    }

    /**
     * Alternative way of returning all the tables in the Database.
     *
     * @return A ResultSet.
     */
    public ResultSet getDBTablesAlternative() {
        String query = "SELECT object_id AS ID, name AS Name FROM sys.tables";
        return getQuery(query);
    }

    /**
     * Returns the table with the most rows from the database.
     *
     * @return A ResultSet.
     */
    public ResultSet getDBTableMostRows() {
        String query = "SELECT TOP 1 o.name AS Name, MAX(i.rows) AS Rows FROM sysobjects o, sysindexes i WHERE o.xtype = 'U' AND i.id = OBJECT_ID(o.name) GROUP BY o.name ORDER BY 2 DESC";
        return getQuery(query);
    }

}
