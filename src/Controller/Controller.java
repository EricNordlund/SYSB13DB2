package Controller;

import Dal.Dal;
import java.sql.ResultSet;
import View.MainWindow;

/**
 * Controller class. Connection handler between the GUI and Controller.
 * Functions described in other parts of the javadoc are missing comments.
 *
 * @author G14
 */
public class Controller {

    Dal dal;
    MainWindow view;
    long resTime; //Response time variable.

    public Controller() {
        this.dal = new Dal();

    }

    public ResultSet getTableData(String tableName) {
        responseTime(true);
        ResultSet result = dal.getTableData(tableName);
        responseTime(false);
        return result;
    }

    public ResultSet getTableMetaData(String tableName) {
        responseTime(true);
        ResultSet result = dal.getTableMetaData(tableName);
        responseTime(false);
        return result;
    }

    public ResultSet getDBKeys() {
        responseTime(true);
        ResultSet result = dal.getDBKeys();
        responseTime(false);
        return result;
    }

    public ResultSet getDBIndexes() {
        responseTime(true);
        ResultSet result = dal.getDBIndexes();
        responseTime(false);
        return result;
    }

    public ResultSet getDBConstraints() {
        responseTime(true);
        ResultSet result = dal.getDBConstraints();
        responseTime(false);
        return result;
    }

    public ResultSet getDBTables() {
        responseTime(true);
        ResultSet result = dal.getDBTables();
        responseTime(false);
        return result;
    }

    public ResultSet getDBTablesAlternative() {
        responseTime(true);
        ResultSet result = dal.getDBTablesAlternative();
        responseTime(false);
        return result;
    }

    public ResultSet getDBTableMostRows() {
        responseTime(true);
        ResultSet result = dal.getDBTableMostRows();
        responseTime(false);
        return result;
    }

    /**
     * Measures response time of mainly SQL-querys. Outputs time to the console.
     *
     * @param start Set to true to start measuring time, false to output result.
     */
    protected void responseTime(Boolean start) {
        long result = 0;

        if (start) {
            resTime = System.currentTimeMillis();

        } else if (start == false) {
            result = System.currentTimeMillis() - resTime;
            System.out.println("Response time: " + result + "ms");
        }

    }

}
