/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.DAOUtils.fermeturesSilencieuses;
import static dao.DAOUtils.initialisationRequetePreparee;
import static java.lang.Math.toIntExact;
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
import model.Question;
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
    private static final String SQL_INSERT = "INSERT INTO questionnaire (subject, status_questionnaire, createur_id) VALUES (?,?,?)";
    private static final String SQL_UPDATE = "UPDATE questionnaire SET subject=?, status_questionnaire=? WHERE id_questionnaire = ?";
    private static final String SQL_SOFT_DELETE = "UPDATE questionnaire SET status_questionnaire = 0 WHERE questionnaire.id_questionnaire = ?";
    private static final String SQL_SELECT_QUESTION ="SELECT * FROM questionnaire, question, question_questionnaire WHERE questionnaire.id_questionnaire = ? AND question_questionnaire.questionnaire_id=questionnaire.id_questionnaire AND question_questionnaire.question_id = question.id_question";
    
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
    public int create(Questionnaire questionnaire) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int new_id = 0;
        
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = DAOFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true,
                     questionnaire.getSubject(), questionnaire.getStatus(), questionnaire.getCreateur_id().getId_user());
            int status = preparedStatement.executeUpdate();
            ResultSet value = preparedStatement.getGeneratedKeys();
            if ( value.next() ) {
                new_id = value.getInt(1);
            }
            
            // récupération id du questionnaire
            ResultSet valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if ( valeursAutoGenerees.next() ) {
                long key = valeursAutoGenerees.getLong(1);
                int k = toIntExact(key);
                questionnaire.setId_questionnaire(k);
            } else {
                throw new DAOException( "Échec de la création du questionnaire en base, aucun ID auto-généré retourné." );
            }
            
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(QuestionnaireDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        return new_id;
        
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
            
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
    }
    
    //get the questions from a questionnaire
    public List<Question> getQuestion(int id) throws DAOException {
        List<Question> questions = new ArrayList();  
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //User user = null;
        
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = DAOFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_QUESTION, false, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {              
                questions.add(mapQuestion(resultSet));
            }
            
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(QuestionnaireDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
    
    return questions;
    }
    
    private static Questionnaire map(ResultSet resultSet) throws SQLException {
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setId_questionnaire(resultSet.getInt("id_questionnaire"));
        questionnaire.setSubject(resultSet.getString("subject"));
        questionnaire.setStatus(resultSet.getBoolean("status_questionnaire"));
        questionnaire.setCreateur_id(mapUser(resultSet));
        return questionnaire;
    }
    
    private static Question mapQuestion(ResultSet resultSet) throws SQLException{
        Question question = new Question();
        question.setId_question(resultSet.getInt("id_question"));
        question.setStatus(resultSet.getBoolean("status_question"));
        question.setSentence(resultSet.getString("name"));
        question.setOrder(resultSet.getInt("question_order"));
        return question;
    }
    
    private static User mapUser(ResultSet resultSet) throws SQLException{
         return UserDao.map(resultSet);
    }


        
}
