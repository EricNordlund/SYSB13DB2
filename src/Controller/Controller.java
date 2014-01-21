
package Controller;

import Dal.Dal;
import java.sql.ResultSet;
import java.text.DecimalFormat;

public class Controller 
{
    Dal dal;
    long resTime;

    public Controller()
    {
        this.dal = new Dal();
    }
    
    public ResultSet getTableData(String tableName)
    {
        responseTime(true);
        ResultSet result = dal.getTableData(tableName);
        responseTime(false);
        return result;
    }
    
    public ResultSet getTableMetaData(String tableName)
    {
        responseTime(true);
        ResultSet result = dal.getTableMetaData(tableName);
        responseTime(false);
        return result;
    }
    
    public ResultSet getDBKeys()
    {
        responseTime(true);
        ResultSet result = dal.getDBKeys();
        responseTime(false);
        return result;
    }
    
    public ResultSet getDBIndexes()
    {
        responseTime(true);
        ResultSet result = dal.getDBIndexes();
        responseTime(false);
        return result;
    }
    
    public ResultSet getDBConstraints()
    {
        responseTime(true);
        ResultSet result = dal.getDBConstraints();
        responseTime(false);
        return result;
    }
    
    public ResultSet getDBTables()
    {
        responseTime(true);
        ResultSet result = dal.getDBTables();
        responseTime(false);
        return result;
    }
    
    public ResultSet getDBTablesAlternative()
    {
        responseTime(true);
        ResultSet result = dal.getDBTablesAlternative();
        responseTime(false);
        return result;
    }
    
    public ResultSet getDBTableMostRows()
    {
        responseTime(true);
        ResultSet result = dal.getDBTableMostRows();
        responseTime(false);
        return result;
    }
    
    protected void responseTime(Boolean start)
    {
        long time = 0;
        long result = 0;
        
        if(start)
        {
            resTime = System.currentTimeMillis();
            
        }
        else if(start == false)
        {
            result = System.currentTimeMillis() - resTime;
            System.out.println("Response time: " + result + "ms");
        }
        
    }
            
    
    
    
    
}
