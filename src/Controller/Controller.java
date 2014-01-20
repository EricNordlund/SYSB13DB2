
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
    
    public ResultSet getDBKeys()
    {
        return dal.getDBKeys();
    }
    
    public ResultSet getDBIndexes()
    {
        return dal.getDBIndexes();
    }
    
    public ResultSet getDBConstraints()
    {
        return dal.getDBConstraints();
    }
    
    public ResultSet getDBTables()
    {
        return dal.getDBTables();
    }
    
    public ResultSet getDBTablesAlternative()
    {
        return dal.getDBTablesAlternative();
    }
    
    public ResultSet getDBTableMostRows()
    {
        return dal.getDBTableMostRows();
    }
    
    
}
