

package Controller;
import Dal.Dal;
import View.MainWindow;
import java.sql.*;


/**
 *
 * @author JD-W
 */
public class Main {
    
    public static void main(String[] args) throws SQLException
    {
        Controller controller = new Controller();
        
        MainWindow view = new MainWindow();
        view.setController(controller);
        view.resultSetToTable(null, true);
        
        view.setVisible(true);
        
        
        /**
        ResultSet rs = dal.getTest();
        rs.next();
        System.out.println(rs.getString("First Name"));
        */
        
        
        
    }
}
