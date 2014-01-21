package Dal;

/**
 * The class contains login information and the address to the database.
 *
 * @author Eric
 */
public class SQLdata {

    private String url = "jdbc:sqlserver://127.0.0.1;databaseName=Navision";
    private String login = "sa";
    private String passw = "aiel1234";

    public String getUrl() {
        return url;
    }

    public String getPassw() {
        return passw;
    }

    public String getLogin() {
        return login;
    }

}
