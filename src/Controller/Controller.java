
package Controller;

import Dal.Dal;
import java.sql.ResultSet;

public class Controller 
{
    Dal dal;

    public Controller()
    {
        this.dal = new Dal();
    }
    
    public ResultSet getTableData(String tableName)
    {
        return dal.getTableData(tableName);
    }
    
    public ResultSet getTableMetaData(String tableName)
    {
        return dal.getTableMetaData(tableName);
    }
    
    
    
    
}
