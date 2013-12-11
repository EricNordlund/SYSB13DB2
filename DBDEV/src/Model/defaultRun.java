package Model;

import java.sql.*;

/**
 *
 * @author Eric
 */
public class defaultRun {

    public static void main(String[] args) throws SQLException {

        Dal dal = new Dal();

        dal.loadDriverMSJDBC();

        Connection con = dal.openConnectionMSJDBC();

        System.out.println(dal.getStudentName(con));
        
        con.close();

    }
}
