/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.DAOInterface;
import dao.UserDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import model.Response;


/**
 *
 * @author Josselin
 */
public class DAOFactory {
    
    private static String url = "";
    private static final String username = "root";
    private static final String password = "";
    
    public DAOFactory(){
        DAOFactory.url = "jdbc:mysql://localhost:3306/intern_project";
    }
    
    /*
    * Initialise la requête préparée basée sur la connexion passée en argument,
    * avec la requête SQL et les objets donnés.
    */
   public static PreparedStatement initialisationRequetePreparee( Connection connexion, String sql, boolean returnGeneratedKeys, Object... objets ) throws SQLException {
       PreparedStatement preparedStatement = connexion.prepareStatement( sql, returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS );
       for ( int i = 0; i < objets.length; i++ ) {
           preparedStatement.setObject( i + 1, objets[i] );
       }
       return preparedStatement;
   }
    
   
   /**
     * Open connection to the database
     * @return 
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(DAOFactory.url, DAOFactory.username, DAOFactory.password);
    }
    
    /*
     * Méthodes de récupération de l'implémentation des différents DAO (un seul
     * pour le moment)
     */
    public UserDao getUserDao() {
        return new UserDao( this );
    }
    
    public CompanyDao getCompanyDao(){
        return new CompanyDao(this);
    }
    
    public QuestionnaireDao getQuestionnaireDao(){
        return new QuestionnaireDao(this);
    }
    
    public ParcoursDao getParcoursDao(){
        return new ParcoursDao(this);
    }
    
    public QuestionDao getQuestionDao(){
        return new QuestionDao(this);
    }
    
    public ResponseDao getResponseDao(){
        return new ResponseDao(this);
    }

}