package controller;

import dal.Dal;
import java.sql.*;

public class Controller2 {
    Dal dal;

    public Controller2(Dal dl) throws SQLException 
    {
         this.dal = dl;
    }
    
    public void insertStudent(String name, String adress) throws SQLException {
        dal.insertStudent(name, adress);
    }
 
    public void insertCourse(String name, String points) throws SQLException {
        dal.addCourse(name, points);
    }
    
    public void deleteStudent(int studentID) throws SQLException {
        dal.removeStudent(studentID);
    }
    
    public void deleteCourse(int courseId) throws SQLException {
        dal.removeCourse(courseId);
    }
    /*
    public void setCourse(String courseID, String name, String points) throws SQLException {
        dal.setCourse(courseID, points, name);
    }*/
    
    public void setStudent(int studentID, String name, String adress) throws SQLException {
        dal.modifyStudent(studentID, name, adress);
    }
    
    public ResultSet getStudent(int studentID) throws SQLException {

        ResultSet rs = dal.getSingleStudent(studentID);
        return rs;
    }
    
    public ResultSet getCourse(int courseId) throws SQLException {

        ResultSet rs = dal.findCourse(courseId);
        return rs;
    }
    
    public ResultSet getAllStudents() throws SQLException
    {
        ResultSet rs = dal.getAllStudents();
        return rs;
        
    }
    
    public void addStudentReading(int studentID, int courseID ) throws SQLException {
        dal.addStudentReading(studentID, courseID);
    }
    
    public void removeStudentReading(int studentID, int courseID) throws SQLException {
        dal.removeStudentReading(studentID, courseID);
    }
    
     public void addStudentRead(int studentID, int courseID, int grade) throws SQLException {
         dal.addStudentRead(studentID, courseID, grade);
    }
    
    public void removeStudentRead(int studentID, int courseID) throws SQLException {
        dal.removeStudentRead(studentID, courseID);
    
    }
    
}
