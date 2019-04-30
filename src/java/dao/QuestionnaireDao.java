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
import model.Company;
import model.Questionnaire;
import model.User;
/**
 *
 * @author lolal
 */

/** 
 TO DO 
 * delete mapUser and use UserDao 
 * get question from the questionnaire -> JOIN
 * update
 * delete
 */
public class QuestionnaireDao implements DAOInterface<Questionnaire>{
    
    private DAOFactory daoFactory;
    public QuestionnaireDao( DAOFactory daoFactory){
        this.daoFactory = daoFactory;
    }
    
    private static final String SQL_SELECT_ALL = "SELECT * FROM questionnaire, user WHERE questionnaire.createur_id = user.id_user ";
    private static final String SQL_SELECT_BY_SUBJECT = "SELECT * FROM questionnaire, user WHERE questionnaire.subject = ? AND questionnaire.createur_id = user.id_user";
    private static final String SQL_INSERT = "INSERT INTO questionnaire (subject, status, createur_id) VALUES (?,?,?)";
    private static final String SQL_UPDATE_ALL = "";
    private static final String SQL_SOFT_DELETE = "";
    
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
            System.out.println(resultSet);
            
            ResultSetMetaData metadata = resultSet.getMetaData();
            int numberOfColumns = metadata.getColumnCount();
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
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
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
            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true, questionnaire.getSubject(),
                    questionnaire.getStatus(), questionnaire.getAdmin_id() );
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
    
    private static Questionnaire map(ResultSet resultSet) throws SQLException {
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setSubject(resultSet.getString("subject"));
        questionnaire.setStatus(resultSet.getBoolean("status"));
        questionnaire.setAdmin_id(mapUser(resultSet));
        return questionnaire;
    }
    
    private static User mapUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId_user(resultSet.getInt("id_user"));
        user.setFirst_name(resultSet.getString("first_name"));
        user.setName_user(resultSet.getString("name_user"));
        user.setEmail(resultSet.getString("email"));
        user.setPhone(resultSet.getString("phone"));
        user.setIs_admin(resultSet.getBoolean("is_admin"));
        user.setStatus(resultSet.getBoolean("status"));
        user.setCompany(mapCompany(resultSet));
        user.setCreated_date(resultSet.getDate("created_date"));
        
        return user;
    }
 
    private static Company mapCompany(ResultSet resultSet) throws SQLException{
        Company company = new Company();
        company.setMatriculation(resultSet.getInt("matriculation"));
        company.setName_company(resultSet.getString("name_company"));
        return company;
    }
        
}
