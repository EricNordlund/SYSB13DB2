package Model;

import java.sql.*;

/**
 *
 * @author Eric
 */
public class Dal {

    /**
     * Laddar drivrutinerna för att ansluta mot enn MS-databas med JBDC.
     */
    public void loadDriverMSJDBC() {

        try {

            DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
            System.out.println("SQL-driver loaded");
        } catch (Exception errorMessage) {

            System.out.println("Driver manager error: " + errorMessage);

        }
    }

    /**
     * Öppnar en anslutning mot sql-serven med JDBC4.0.
     */
    public Connection openConnectionMSJDBC() throws SQLException {

        
        
        String serverURL = "jdbc:sqlserver://localhost;database=150220-ericdev;";
        String serverLogon = "150220_od57594";
        String serverPassword = "EttLosenord123";



        Connection sqlConnection = DriverManager.getConnection(serverURL, serverLogon, serverPassword);
        System.out.println("Connection oppened");

        return sqlConnection;
    }
    
    /**
     * 
     * @param connection
     * @return Returnerar namnet på den första studenten.
     * @throws SQLException 
     */
    public String getStudentName(Connection con) throws SQLException {
        
        Statement query = con.createStatement();
                
        ResultSet result = query.executeQuery("SELECT * FROM STUDENT");
        
        result.next();
        
        return result.getString("name");
        
        
    }
    
}
