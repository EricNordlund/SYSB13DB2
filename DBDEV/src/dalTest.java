import java.sql.*;

/**
 *
 * @author Eric
 */



public class dalTest {
    
    public static void main(String[] args) throws SQLException
    {
        
        String url = "jdbc:sqlserver://localhost;database=150220-ericdev;";
        String name = "150220_od57594";
        String pwd = "EttLosenord123";
        
                        
        try 
        {
            
            DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
            
        } 
        catch(Exception e) 
        {
            
            System.out.println("Unable to load driver class: " + e);
            
        }
        
        Connection con = DriverManager.getConnection(url, name, pwd);
        
        Statement query = con.createStatement ();
        
        ResultSet answer = query.executeQuery("SELECT * FROM STUDENT");
        
       answer.next();
       System.out.print(answer.getString("name") + " " + answer.getString("adress"));
        
        
    }
        
    }
    
    
    
    
    

