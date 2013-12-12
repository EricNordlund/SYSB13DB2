
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
        return result;
    }
     
    
    //Inte helt klar, jobbar på att få den att automatiskt skriva ut tabellen utan att antalet 
    //kolumner i svaret spelar någon roll.
    public String getSingleStudent(int studentID) throws SQLException
    {
        String query = "SELECT * FROM student WHERE" + studentID;
        String resultString = null;
        
        ResultSet result = dal.getQuery(query);
        result.next();
      /*  for(int i=1; i <= result.getMetaData().getColumnCount(); i++)
        {
            resultString = result.getString(i);
            resultString = " ";
        }
        
        System.out.println(result.getMetaData().getColumnCount());*/
        return resultString;
    }
    
}
