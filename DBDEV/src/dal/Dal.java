package dal;

import java.sql.*;

/**
 *
 * @author Eric
 */
public class Dal {
    private Connection con;
    private Statement statement;
    
    
    /*
     * ********************************************************************************************
     * ********************************DATABASE CONNECTION METHODS********************************
     * ********************************************************************************************
     */
    
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
        
        statement.executeUpdate(query);
        
    }
    
    
    public ResultSet getQuery(String query) throws SQLException {
        
        ResultSet result = statement.executeQuery(query);
        return result;
        
    }
    
    
    /**
     * Stänger anslutningen mot sql-servern.
     * @throws SQLException
     */    
    public void closeConnection() throws SQLException {
        
        this.con.close();
        
    }
    
     /**
     * ********************************************************************************************
     * ********************************STUDENT QUERYS**********************************************
     * ********************************************************************************************
     */
    
    /**
     * Ger ett resultset med alla studenter i.
     * @return 
     * @throws java.sql.SQLException 
     */
    public ResultSet getAllStudents() throws SQLException
    {
        String query = "SELECT * FROM student";
        ResultSet result = getQuery(query);
        System.out.println("Getting student list.");
        return result;  
    }
     
    
    /**
     * En funktion som hämtar samtlig data för en student.
     * @param studentID ID på den student som är av intresse.
     * @return Samtliga data i form av ett resultset.
     * @throws SQLException 
     */
    public ResultSet getSingleStudent(int studentID) throws SQLException {
        String query = "SELECT * FROM student WHERE" + studentID;
        ResultSet result = getQuery(query);
        return result;
        
    }
    
    /**
     * Uppdaterar uppgifterna för en student
     * @param studentID ID på studenten som skall uppdateras.
     * @param name Det nya eller förändrade namnet.
     * @param adress Den nya eller förändrade adressen. 
     * @throws SQLException 
     */
    public void modifyStudent(int studentID, String name, String adress) throws SQLException {
        String query = "UPDATE student SET name='" + name + "', adress='" + adress + "' WHERE studentID='" + studentID + "'";
        sendQuery(query);
        System.out.println("Updated student " + studentID + ".");
    }
    
    /**
     * Skapar en student i tabellen Student
     * @param name Namnet på den nye studenten.
     * @param adress Adressen på den nye studenten.
     * @throws SQLException 
     */
    public void insertStudent(String name, String adress) throws SQLException {
        String query = "INSERT INTO student (name, adress) VALUES('" + name + "', '" + adress + "')";
        sendQuery(query);
        System.out.println("Registered new student.");
    }
    
    /**
     * Tar bort en student från databasen.
     * @param studentID ID på den student som skall tas bort. 
     * @throws SQLException 
     */
    public void removeStudent(int studentID) throws SQLException {
        String query = "DELETE FROM student WHERE studentID = " + studentID;
        sendQuery(query);
        System.out.println("Removed student " + studentID + ".");
    }
    
    /**
     * Söker efter en speciell student. Söker endast efter det specifika studentIDt, inga wildcards där med andra ord.
     * @param searchString Det som funktionen skall söka efter.
     * @return Resultset med samtliga studenter som motsvarar sökningen.
     * @throws SQLException
     */
    public ResultSet searchForStudent(String searchString) throws SQLException {
        String query = "SELECT * FROM student WHERE studentID LIKE '" + searchString +"' OR name LIKE '%" + searchString +"%' OR adress LIKE '%" + searchString +"%'";
        ResultSet result = getQuery(query);
        if(!result.next())
        {
            System.out.println("Search returned empty.");
            return null;
        }
        return result;       
    }
    
    
    
    /**
     * ********************************************************************************************
     * ********************************Reading Querys**********************************************
     * ********************************************************************************************
     */
    
    public void addStudentReading(int studentID, int courseID ) throws SQLException {
        String query = "INSERT INTO reading (studentID, courseID) VALUES('" + studentID +"', '" + courseID +"')";
        sendQuery(query);
        System.out.println("Added student to course.");
    }
    
    public void removeStudentReading(int studentID, int courseID) throws SQLException {
        String query ="DELETE FROM reading WHERE studentID = '" + studentID +"' AND courseID = '" + courseID +"'";
        sendQuery(query);
        System.out.println("Removed student from course.");
    }
    
}
