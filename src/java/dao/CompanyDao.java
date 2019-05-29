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
public class CompanyDao implements DAOInterface<Company> {
    
    private DAOFactory daoFactory;
    Connection connexion;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    // SQL Script
    private static final String SQL_SELECT_ALL = "SELECT * FROM company";
    private static final String SQL_INSERT = "INSERT INTO company (matriculation, name_company) VALUES (?,?)" ;
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM company WHERE matriculation = ?";
    
    
    public CompanyDao(DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }
    
    
    @Override
    public List<Company> index() throws DAOException {
        
        List<Company> companies = new ArrayList();
        
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = DAOFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_SELECT_ALL, false);
            resultSet = preparedStatement.executeQuery();
            
            // Iterate results
            while (resultSet.next()) {              
                companies.add(map(resultSet));
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CompanyDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        return companies;
    }

    
    @Override
    public int create(Company t) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int new_id = 0;
        
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = DAOFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true, t.getMatriculation(), t.getName_company());
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
    public Company show(int id) throws DAOException {
        Company company = null;
        
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = DAOFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_BY_ID, false, id );
            resultSet = preparedStatement.executeQuery();
            //Parcours de la ligne de données de l'éventuel ResulSet retourné */
            if ( resultSet.next() ) {
                company = map( resultSet );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        return company;
    }

    
    @Override
    public void update(int i, Company t) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    @Override
    public void delete(int i) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    /**
     * Map ResulSet to Company
     * @param resultSet
     * @return
     * @throws SQLException 
     */
    public static Company map(ResultSet resultSet) throws SQLException{
        Company company = new Company();
        company.setMatriculation(resultSet.getInt("matriculation"));
        company.setName_company(resultSet.getString("name_company"));
        return company;
    }
    
}
