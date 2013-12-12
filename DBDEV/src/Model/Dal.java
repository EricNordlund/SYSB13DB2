package Model;

import java.sql.*;

/**
 *
 * @author Eric
 */
public class Dal {
    private Connection con;
    private Statement statement;
    
    
    /**
     * Konstruktor: Initierar en anslutning mot servern samt laddar drivrutinerna och skapar en statement.
     * @throws SQLException 
     */
    public Dal() throws SQLException {
        Dal.loadDriverMSJDBC();
        this.con = Dal.openConnectionMSJDBC();
        this.statement = con.createStatement();
    }
    
    
    
    
    /**
     * Laddar drivrutinerna för att ansluta mot en MS-databas med JBDC 4.0.
     */
    private static void loadDriverMSJDBC() {

        try {

            DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
            System.out.println("SQL-driver loaded");
        } catch (Exception errorMessage) {

            System.out.println("Driver manager error: " + errorMessage);

        }
    }

    
    /**
     * Öppnar en anslutning mot sql-serven med JDBC 4.0.
     * @return En SQL-Anslutning
     * @throws SQLException 
     */
    private static Connection openConnectionMSJDBC() throws SQLException {
     
        String serverURL = "jdbc:sqlserver://localhost;database=150220-ericdev;";
        String serverLogon = "150220_od57594";
        String serverPassword = "EttLosenord123";

        Connection sqlConnection = DriverManager.getConnection(serverURL, serverLogon, serverPassword);
        System.out.println("Connection oppened");

        return sqlConnection;
    }
    
    
    public void sendQuery(String query) throws SQLException {
        
        statement.executeQuery(query);
        
    }
    
    
    public ResultSet getQuery(String query) throws SQLException {
        
        ResultSet result = statement.executeQuery(query);
        return result;
        
    }
    
    
    /**
     * 
     * @param Anslutning 
     * @param Student
     * @return Returnerar information om en student i ett studentobjekt.
     * @throws SQLException 
     */
    private String getStudentInformation(Student student) throws SQLException {
        
        String query = "SELECT * FROM student WHERE" + student.studentID;
        ResultSet result = this.getQuery(query);
        
        result.next();
        return result.getString("name");
        
        
    }
    
}
