package Model;

import java.sql.*;

public class Course {

    //Anslutning till data access layer
    Dal dal;

    public Course() throws SQLException {
        this.dal = new Dal();
    }

    //Borttagning av kurs//
    public void removeCourse(int courseId) throws SQLException {
        String query = "delete from course where courseId = " + courseId;
        dal.sendQuery(query);
        System.out.println("Removed course: " + courseId);
    }

    //Hitta en kurs och dess information
    public void findCourse(int courseId) throws SQLException {
        String query = "select * from course where courseId = " + courseId;
        ResultSet result = dal.getQuery(query);
        return result;
    }

    //Registrering av kurs
    public void addCourse(String courseName,
            String coursePoints) throws SQLException {
        String query
                = "insert into student (courseName, coursePoints) values ('"
                + courseName + "', '" + coursePoints + "')";
        dal.sendQuery(query);
        System.out.println("Registered new course: " + courseName);
    }

    //Visa resultat för angiven kurs
    public void showResult(int courseId) throws SQLException {
            String query = "select studentName, coursePoints from course c join student s on";
            ResultSet result = dal.getQuery(query);
            return result;
    }

}
