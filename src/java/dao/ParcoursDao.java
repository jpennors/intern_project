/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.DAOUtils.fermeturesSilencieuses;
import static dao.DAOUtils.initialisationRequetePreparee;
import dao.User.UserDaoImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Parcours;


/**
 *
 * @author lolal
 */

/**
 TO DO
 * map parcours
 * use UserDao and QUestionnaireDao 
 * update 
 * delete
 */
public class ParcoursDao implements DAOInterface<Parcours>{
    
    private DAOFactory daoFactory;

    public ParcoursDao(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }  
    
    private static final String SQL_SELECT_ALL = "SELECT * FROM parcours, user, questionnaire WHERE parcours.user_id = user.id_user AND parcours.user_id = questionnaire.createur_id ";
    private static final String SQL_SELECT_BY_SUBJECT = "SELECT * FROM parcours, questionnaire, user WHERE parcours.user_id = ? AND parcours.user_id = user.id_user AND parcours.user_id = questionnaire.createur_id ";
    private static final String SQL_INSERT = "INSERT INTO questionnaire (id, duration, user_id, questionnaire_id) VALUES (?,?,?)";
    private static final String SQL_UPDATE_ALL = "";
    private static final String SQL_SOFT_DELETE = "";   
    
    @Override
    public List<Parcours> index() throws DAOException {
        List<Parcours> parcours = new ArrayList();
        
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //User user = null;
        
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = DAOFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_ALL, false);
            resultSet = preparedStatement.executeQuery();
            System.out.println(resultSet);
            
            ResultSetMetaData metadata = resultSet.getMetaData();
            int numberOfColumns = metadata.getColumnCount();
            while (resultSet.next()) {              
                parcours.add(map(resultSet));
            }
            //Parcours de la ligne de données de l'éventuel ResulSet retourné */
            if ( resultSet.next() ) {
                System.out.println(resultSet);
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        
        return parcours;
    }

    @Override
    public void create(Parcours parcours) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = DAOFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true, parcours.getId(), parcours.getQuestionnaire_id(),
                    parcours.getUser_id(), parcours.getDuration());
            int status = preparedStatement.executeUpdate();
            System.out.println(status);
            
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        
    }

    @Override
    public Parcours show(int id) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Parcours questionnaire = null;
        
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = DAOFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_BY_SUBJECT, false, id );
            resultSet = preparedStatement.executeQuery();
            //Parcours de la ligne de données de l'éventuel ResulSet retourné */
            if ( resultSet.next() ) {
                questionnaire = map( resultSet );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        
        return questionnaire;   
    }

    @Override
    public void update(int i) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int i) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private static Parcours map(ResultSet resultSet){
        Parcours parcours = new Parcours();
        
        return parcours;
    }
}
