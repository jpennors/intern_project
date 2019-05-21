/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.DAOUtils.fermeturesSilencieuses;
import static dao.DAOUtils.initialisationRequetePreparee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Company;
import model.Questionnaire;
import model.User;
/**
 *
 * @author lolal
 */

/** 
 TO DO 
 * get question from the questionnaire -> JOIN
 */
public class QuestionnaireDao implements DAOInterface<Questionnaire>{
    
    private DAOFactory daoFactory;
    public QuestionnaireDao( DAOFactory daoFactory){
        this.daoFactory = daoFactory;
    }
    
    private static final String SQL_SELECT_ALL = "SELECT * FROM questionnaire, user, company WHERE questionnaire.createur_id = user.id_user AND user.company = company.matriculation ";
    private static final String SQL_SELECT_BY_SUBJECT = "SELECT * FROM questionnaire, user, company WHERE questionnaire.id_questionnaire = ? AND questionnaire.createur_id = user.id_user AND user.company = company.matriculation";
    private static final String SQL_INSERT = "INSERT INTO questionnaire (subject, status, createur_id) VALUES (?,?,?)";
    private static final String SQL_UPDATE = "UPDATE questionnaire SET subject=?, status=? WHERE id_questionnaire = ?";
    private static final String SQL_SOFT_DELETE = "UPDATE questionnaire SET status = 0 WHERE questionnaire.id_questionnaire = ?";
    
    @Override
    public List<Questionnaire> index() throws DAOException {
        List<Questionnaire> questionnaires = new ArrayList();
        
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //User user = null;
        
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = DAOFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_ALL, false);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {              
                questionnaires.add(map(resultSet));
            }
            //Parcours de la ligne de données de l'éventuel ResulSet retourné */
            if ( resultSet.next() ) {
                System.out.println(resultSet);
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(QuestionnaireDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        
        return questionnaires;
    }

    @Override
    public void create(Questionnaire questionnaire) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = DAOFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true,
                     questionnaire.getSubject(), questionnaire.getStatus(), questionnaire.getCreateur_id().getId_user());
            int status = preparedStatement.executeUpdate();
            System.out.println(status);
            
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(QuestionnaireDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        
    }

    @Override
    public Questionnaire show(int id) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Questionnaire questionnaire = null;
        
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = DAOFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_BY_SUBJECT, false, id );
            resultSet = preparedStatement.executeQuery();
            System.out.println(preparedStatement.toString());
            System.out.println(id);
            //Parcours de la ligne de données de l'éventuel ResulSet retourné */
            if ( resultSet.next() ) {
                questionnaire = map( resultSet );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(QuestionnaireDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        return questionnaire;   
    }

    @Override
    public void update(int id, Questionnaire questionnaire) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = DAOFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_UPDATE,
                    true, questionnaire.getSubject(), questionnaire.getStatus(), id );
            int status = preparedStatement.executeUpdate();
            System.out.println(status);
            
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }       
    }
    
    @Override
    public void delete(int id) throws DAOException {
               Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = DAOFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SOFT_DELETE, true, id );
            int status = preparedStatement.executeUpdate();
            System.out.println(status);
            
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
    }
    
    private static Questionnaire map(ResultSet resultSet) throws SQLException {
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setId_questionnaire(resultSet.getInt("id_questionnaire"));
        questionnaire.setSubject(resultSet.getString("subject"));
        questionnaire.setStatus(resultSet.getBoolean("status"));
        questionnaire.setCreateur_id(mapUser(resultSet));
        return questionnaire;
    }
    
    private static User mapUser(ResultSet resultSet) throws SQLException{
         return UserDao.map(resultSet);
    }


        
}
