package dal;

import java.sql.*;

/**
 *
 * @author Eric
 */
public class Dal {
    private Connection con = null;
    private Statement statement;
    
    /*
     * ********************************************************************************************
     * ********************************DATABASE CONNECTION METHODS********************************
     * ********************************************************************************************
     */
    
    /**
     * Konstruktor: Initierar en anslutning mot servern samt laddar drivrutinerna och skapar en statement.
     * @throws SQLException 
     */
    public Dal() throws SQLException {
        Dal.loadDriver();
        this.con = Dal.openConnection();
        this.statement = con.createStatement();
    }
    
    
    /**
     * Laddar drivrutinerna för att ansluta mot en MS-databas med JBDC 4.0.
     */
    private static void loadDriver() {

        try {

            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            System.out.println("SQL-driver loaded");
        } catch (Exception errorMessage) {

            System.out.println("Driver manager error: " + errorMessage);

        }
    }

    
    /**
     * Öppnar en anslutning mot sql-serven med JDBC 4.0.
     * @return En SQL-Anslutning
     * @throws SQLException 
     */
    private static Connection openConnection() throws SQLException {

        String serverURL = "jdbc:mysql://localhost:8889/dbproject";
        String serverLogon = "root";
        String serverPassword = "root";

        Connection sqlConnection = DriverManager.getConnection(serverURL, serverLogon, serverPassword);
        System.out.println("Connection opened");

        return sqlConnection;
    }
    
    
    public void sendQuery(String query) throws SQLException {
        
        statement.executeUpdate(query);
        
    }
    
    
    public ResultSet getQuery(String query) throws SQLException {
        
        ResultSet result = statement.executeQuery(query);
        return result;
        
    }
    
    
    /**
     * Stänger anslutningen mot sql-servern.
     * @throws SQLException
     */    
    public void closeConnection() throws SQLException {
        
        this.con.close();
        
    }
    
     /**
     * ********************************************************************************************
     * ********************************STUDENT QUERYS**********************************************
     * ********************************************************************************************
     */
    
    /**
     * Ger ett resultset med alla studenter i.
     * @return 
     * @throws java.sql.SQLException 
     */
    public ResultSet getAllStudents() throws SQLException
    {
        String query = "SELECT studentID StudentID, name Name, adress Adress FROM student";
        ResultSet result = getQuery(query);
        System.out.println("Getting student list.");
        return result;  
    }
     
    
    /**
     * En funktion som hämtar samtlig data för en student.
     * @param studentID ID på den student som är av intresse.
     * @return Samtliga data i form av ett resultset.
     * @throws SQLException 
     */
    public ResultSet getSingleStudent(int studentID) throws SQLException {
        String query = "SELECT * FROM student WHERE studentID='"+studentID+"'";
        ResultSet result = getQuery(query);
        System.out.println("getSingleStudent done");
        return result;
        
        
    }
    
    /**
     * Uppdaterar uppgifterna för en student
     * @param studentID ID på studenten som skall uppdateras.
     * @param name Det nya eller förändrade namnet.
     * @param adress Den nya eller förändrade adressen. 
     * @throws SQLException 
     */
    public void modifyStudent(int studentID, String name, String adress) throws SQLException {
        String query = "UPDATE student SET name='" + name + "', adress='" + adress + "' WHERE studentID='" + studentID + "'";
        sendQuery(query);
        System.out.println("Updated student " + studentID + ".");
    }
    
    /**
     * Skapar en student i tabellen Student
     * @param name Namnet på den nye studenten.
     * @param adress Adressen på den nye studenten.
     * @throws SQLException 
     */
    public void insertStudent(String name, String adress) throws SQLException {
        String query = "INSERT INTO student (name, adress) VALUES('" + name + "', '" + adress + "')";
        sendQuery(query);
        System.out.println("Registered new student.");
    }
    
    /**
     * Tar bort en student från databasen.
     * @param studentID ID på den student som skall tas bort. 
     * @throws SQLException 
     */
    public void removeStudent(int studentID) throws SQLException {
        String query = "DELETE FROM student WHERE studentID = " + studentID;
        sendQuery(query);
        System.out.println("Removed student " + studentID + ".");
    }
    
    /**
     * Söker efter en speciell student. Söker endast efter det specifika studentIDt, inga wildcards där med andra ord.
     * @param searchString Det som funktionen skall söka efter.
     * @return Resultset med samtliga studenter som motsvarar sökningen.
     * @throws SQLException
     */
    public ResultSet searchForStudent(String searchString) throws SQLException {
        String query = "SELECT * FROM student WHERE studentID LIKE '" + searchString +"' OR name LIKE '%" + searchString +"%' OR adress LIKE '%" + searchString +"%'";
        ResultSet result = getQuery(query);
        if(!result.next())
        {
            System.out.println("Search returned empty.");
            return null;
        }
        return result;       
    }
    
    
    
    /**
     * ********************************************************************************************
     * ********************************Reading Querys**********************************************
     * ********************************************************************************************
     */
    
    public void addStudentReading(int studentID, int courseID ) throws SQLException {
        String query = "INSERT INTO reading (studentID, courseID) VALUES('" + studentID +"', '" + courseID +"')";
        sendQuery(query);
        System.out.println("Added student to course.");
    }
    
    public void removeStudentReading(int studentID, int courseID) throws SQLException {
        String query = "DELETE FROM reading WHERE studentID = '" + studentID +"' AND courseID = '" + courseID +"'";
        sendQuery(query);
        System.out.println("Removed student from course.");
    }
    
    /**
     * ********************************************************************************************
     * ********************************Read Querys**********************************************
     * ********************************************************************************************
     */
    
     public void addStudentRead(int studentID, int courseID, int grade) throws SQLException {
        String query = "DELETE FROM reading WHERE studentID = '" + studentID +"' AND courseID = '" + courseID +"'";
        sendQuery(query);
        
        query = "INSERT INTO haveRead (studentID, courseID, grade) VALUES('" + studentID +"', '" + courseID +"', '" + grade +"')";
        sendQuery(query);
        
        System.out.println("Ended course for student.");
    }
    
    public void removeStudentRead(int studentID, int courseID) throws SQLException {
        String query ="DELETE FROM reading WHERE studentID = '" + studentID +"' AND courseID = '" + courseID +"'";
        sendQuery(query);
        System.out.println("Ended course for student.");
    
    }
    
    /**
     * ********************************************************************************************
     * ********************************Course Querys**********************************************
     * ********************************************************************************************
     */
    
    
    /**
     * Borttagning av kurs.
     * @param courseId
     * @throws SQLException 
     */
    public void removeCourse(int courseId) throws SQLException {
        String query = "delete from course where courseId = " + courseId;
        sendQuery(query);
        System.out.println("Removed course: " + courseId);
    }

    /**
     * Hitta en kurs och dess information
     * @param courseId
     * @throws SQLException 
     */
    public ResultSet findCourse(int courseId) throws SQLException {
        String query = "select * from course where courseId = " + courseId;
        ResultSet result = getQuery(query);
        return result;
    }

    /**
     * Registrering av kurs
     * @param courseName
     * @param coursePoints
     * @throws SQLException 
     */
    public void addCourse(String courseName,
            String coursePoints) throws SQLException {
        String query = "insert into student (courseName, coursePoints) values ('" + courseName + "', '" + coursePoints + "')";
        sendQuery(query);
        System.out.println("Registered new course: " + courseName);
    }

    /**
     * Visa resultat för angiven kurs.
     * @param courseId
     * @throws SQLException 
     */
    public ResultSet getCourseResult(int courseID) throws SQLException {
            String query = "SELECT st.studentID StudentID ,st.name Name, rd.grade Grade FROM student st INNER JOIN (SELECT studentID, grade FROM haveRead WHERE courseID = '" + courseID + "') AS rd ON st.studentID = rd.studentID";
            ResultSet result = getQuery(query);
            return result;
    }
    
    
    /**
     * Hämtar samtliga studenter som läser en kurs.
     * @param courseID ID på den kurs som är av intresse.
     * @return 
     * @throws SQLException 
     */
    public ResultSet getReadingStudents(int courseID) throws SQLException {
        String query ="SELECT st.studentID StudentID, st.name Name FROM student st INNER JOIN (SELECT studentID FROM reading WHERE courseID = '" + courseID + "') AS rd ON st.studentID = rd.studentID";
        ResultSet result = getQuery(query);
        return result;
    }
    
    
    /**
     * Hämtar samtliga studenter som har betyg A i en kurs.
     * @param courseID
     * @return
     * @throws SQLException 
     */
    public ResultSet getAStudents(int courseID) throws SQLException {
        String query ="SELECT st.studentID StudentID, st.name Name FROM student st INNER JOIN (SELECT studentID FROM haveRead WHERE courseID = '" + courseID + "' AND grade = '6') AS rd ON st.studentID = rd.studentID";
        ResultSet result = getQuery(query);
        return result;
    }

    /**
     * Retunerar antalet studenter med A i betyg på en kurs.
     * @param courseID
     * @return Procenten returneras i ett heltal mellan 0 och 100.
     * @throws SQLException 
     */
    public ResultSet getPercentageAStudents(int courseID) throws SQLException {
        String query = "SELECT (SELECT count(studentID) FROM haveRead WHERE courseID = '" + courseID + "' AND grade = '6') * 100 / (SELECT count(studentID) FROM haveRead WHERE courseID = '" + courseID + "') AS Percentage";
        ResultSet result = getQuery(query);
        return result;            
    }   
     
   /**
    * Retunerar en tabell med samtliga kurser med den högsta genomströmningen överst.
    * @return
    * @throws SQLException 
    */ 
    public ResultSet getCourseThroughput() throws SQLException {
        String query = "SELECT courseID CourseID, count(studentID) Students FROM (SELECT * FROM haveRead WHERE grade > 0) AS CD GROUP BY courseID ORDER BY Students DESC";
        ResultSet result = getQuery(query);
        return result;    
    }
    
    
    /**
     * Hämtar en lista på de kurser en specifik student avslutat inkluderat betygen.
     * @param studentID
     * @return
     * @throws SQLException 
     */
    public ResultSet getStudentResults(int studentID) throws SQLException {
        String query = "SELECT cr.courseID CourseID, cr.name Name, grade Grade FROM course cr INNER JOIN (SELECT courseID, grade FROM haveRead WHERE studentID = '" + studentID + "') AS rd ON cr.courseID = rd.courseID";
        ResultSet result = getQuery(query);
        return result; 
    }
    
    public ResultSet getStudentsOngoingCourses(int studentID) throws SQLException {
        String query = "SELECT cr.courseID CourseID, cr.name Name, cr.points Points FROM course cr INNER JOIN (SELECT courseID, studentID FROM reading WHERE studentID = '" + studentID + "') AS rd ON cr.courseID = rd.courseID";
        ResultSet result = getQuery(query);
        return result; 
    }
    
    
    
}
