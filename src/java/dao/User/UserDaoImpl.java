/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.User;

import dao.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.User;
import dao.DAOFactory;
import static dao.DAOUtils.*;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Company;

/**
 *
 * @author Josselin
 */
public class UserDaoImpl implements UserDao {
    
    private DAOFactory          daoFactory;

    public UserDaoImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }
    private static final String SQL_SELECT_ALL = "SELECT * FROM user, company WHERE user.company = company.matriculation";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM user, company WHERE user.id_user = ? AND user.company = company.matriculation";
    //private static final String SQL_SELECT_ALL = "SELECT * FROM user, company WHERE user.company_id = company.matriculation";

    @Override
    public void create(User user) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> index() throws DAOException {
        List<User> users = new ArrayList();
        
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
                users.add(map(resultSet));
            }
            //Parcours de la ligne de données de l'éventuel ResulSet retourné */
            if ( resultSet.next() ) {
                System.out.println(resultSet);
                //user = map( resultSet );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        
        return users;
    }

    @Override
    public User show(int id) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = DAOFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_BY_ID, false, id );
            resultSet = preparedStatement.executeQuery();
            //Parcours de la ligne de données de l'éventuel ResulSet retourné */
            if ( resultSet.next() ) {
                user = map( resultSet );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        
        return user;
        
    }

    @Override
    public void update(int i) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int i) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private static User map(ResultSet resultSet) throws SQLException {
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
