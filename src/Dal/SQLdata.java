

package Dal;

/**
 * Klassen är till för att kunna ändra ip och inloggning utan att ändra dalen.
 * @author Eric
 */
public class SQLdata 
{
    private String url =  "jdbc:sqlserver://127.0.0.1;databaseName=Navision";
    private String login = "sa";
    private String passw = "aiel1234";

    public String getUrl() 
    {
        return url;
    }

    public String getPassw() 
    {
        return passw;
    }

    public String getLogin() 
    {
        return login;
    }
    
}
