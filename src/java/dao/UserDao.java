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
import model.User;

/**
 *
 * @author Josselin
 */
public class UserDao implements DAOInterface<User> {

    private DAOFactory daoFactory;

    // SQL Scripts
    private static final String SQL_SELECT_ALL = "SELECT * FROM user, company WHERE user.company = company.matriculation";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM user, company WHERE user.id_user = ? AND user.company = company.matriculation";
    private static final String SQL_INSERT = "INSERT INTO user (email, password, name_user, first_name, status, phone, is_admin, company) VALUES (?,?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE user SET email=?, name_user=?, first_name=?, status=?, phone=?, is_admin=?, company=? WHERE id_user=?";
    private static final String SQL_SOFT_DELETE = "UPDATE user SET status = 0 WHERE id_user = ?";
    private static final String SQL_LOGIN = "SELECT * FROM user, company WHERE email = ? AND password = ? AND user.company = company.matriculation";
    
    public UserDao(DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }
    
    
    @Override
    public List<User> index() throws DAOException {
        List<User> users = new ArrayList();
        
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = DAOFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_ALL, false);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {              
                users.add(map(resultSet));
            }
            //Parcours de la ligne de données de l'éventuel ResulSet retourné */
            if ( resultSet.next() ) {
                System.out.println(resultSet);
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        
        return users;
    }

    @Override
    public int create(User user) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int new_id = 0;
        
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = DAOFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true, user.getEmail(), user.getPassword(), user.getName_user(),
                                user.getFirst_name(), user.getStatus(), user.getPhone(), user.getIs_admin(), user.getCompany().getMatriculation() );
            int status = preparedStatement.executeUpdate();
            ResultSet value = preparedStatement.getGeneratedKeys();
            if ( value.next() ) {
                new_id = value.getInt(1);
            }
            
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        return new_id;
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
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        
        return user;
    }

    @Override
    public void update(int id, User user) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = DAOFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_UPDATE, true, user.getEmail(), user.getName_user(), user.getFirst_name(),
                                user.getStatus(), user.getPhone(), user.getIs_admin(), user.getCompany().getMatriculation(), id );
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
    
    /**
     * Map ResultSet to User
     * @param resultSet
     * @return
     * @throws SQLException 
     */
    public static User map(ResultSet resultSet) throws SQLException {
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
        return CompanyDao.map(resultSet);
    }
    
    public User login(String email, String password){
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = DAOFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_LOGIN, false, email, password );
            resultSet = preparedStatement.executeQuery();
            System.out.println(preparedStatement);
            //Parcours de la ligne de données de l'éventuel ResulSet retourné */
            if ( resultSet.next() ) {
                user = map( resultSet );
                System.out.println("Trouvé");
                return user;
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        return null;
    }
}
