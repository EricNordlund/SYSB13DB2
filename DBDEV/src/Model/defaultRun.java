package Model;

import java.sql.*;

/**
 *
 * @author Eric
 */
public class defaultRun {

    /**
     *
     * @param args
     * @throws SQLException
     */
    public static void main(String[] args) throws SQLException {

        
        
        Student student = new Student();
        
        System.out.println(student.getSingleStudent(1));

        

        
        

    }
}
