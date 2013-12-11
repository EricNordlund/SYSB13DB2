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

        Dal dal = new Dal();
        
        Student student = new Student();
        student.studentID = 1;
        
        
        System.out.println(dal.getStudentInformation(student));

        

        
        

    }
}
