/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import controller.Controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Josselin
 */
public class JDBC {
    
    private final String url = "jdbc:mysql://localhost:3306/intern_project";
    private final String username = "root";
    private final String password = "";
    private Connection connection;

    public JDBC() throws SQLException {
        this.connection = null;
        this.openConnection();
    }

    
    /**
     * Open connection to the database
     * @throws java.sql.SQLException
     */
    private void openConnection() throws SQLException {
        try{
                Class.forName("com.mysql.jdbc.Driver");
                this.connection = DriverManager.getConnection(this.url, this.username, this.password);
        } catch(ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Close connection to the database
     * @throws java.sql.SQLException
     */
    public void closeConnection() throws SQLException{
       this.connection.close();
    }
    
    /**
     * Excécution SQL request
     * @param query
     * @return
     * @throws SQLException 
     */
    public ResultSet exceuteQuery(String query) throws SQLException{
        Statement st;
        st = this.connection.createStatement();
        ResultSet data = st.executeQuery(query);
        st.close();
        return data;
    }
    
    
    
}
