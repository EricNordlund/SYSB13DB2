/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import Model.*;
import java.sql.*;

/**
 *
 * @author Eric
 */
public class Controller2 {
    private Dal dal;

       
    Controller2(Dal dl) throws SQLException 
    {
         this.dal = dl;
    }
    
    
    public ResultSet getAllStudents() throws SQLException
    {
        ResultSet rs = dal.getAllStudents();
        return rs;
        
    }
    
}
