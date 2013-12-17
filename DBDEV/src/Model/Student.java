
package Model;

import java.sql.*;

/**
 *
 * @author Eric
 */
public class Student {
    int studentID;
    
    //Skapar en anslutning mot Data access layer.
    Dal dal;

    public Student() throws SQLException {
        this.dal = new Dal();
    }
    
    
    /**
     * Ger ett resultset med alla studenter i.
     * @return 
     * @throws java.sql.SQLException 
     */
    public ResultSet getAllStudents() throws SQLException
    {
        String query = "SELECT * FROM student";
        ResultSet result = dal.getQuery(query);
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
        ResultSet result = dal.getQuery(query);
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
        dal.sendQuery(query);
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
        dal.sendQuery(query);
        System.out.println("Registered new student.");
    }
    
    /**
     * Tar bort en student från databasen.
     * @param studentID ID på den student som skall tas bort. 
     * @throws SQLException 
     */
    public void removeStudent(int studentID) throws SQLException {
        String query = "DELETE FROM student WHERE studentID = " + studentID;
        dal.sendQuery(query);
        System.out.println("Removed student " + studentID + ".");
    }
}
