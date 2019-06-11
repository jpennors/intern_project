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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Response;

/**
 *
 * @author lolal
 */

/** TO DO 
 * 
 * attention a ne pas pouvoir dsactiver la réponse valide 
 */
public class ResponseDao implements DAOInterface<Response>{
    
    private DAOFactory daoFactory;
    public ResponseDao(DAOFactory daoFactory){
        this.daoFactory = daoFactory;
    }
    
    private static final String SQL_SELECT_BY_ID_RESPONSE = "SELECT * FROM response WHERE response.id_response = ?";
    private static final String SQL_SELECT_BY_QUESTION_ID = "SELECT * FROM response WHERE response.question_id = ? ORDER BY response.ordre";
    private static final String SQL_SOFT_DELETE ="UPDATE response SET status_response = 0 WHERE response.id_response = ?";
    private static final String SQL_INSERT = "INSERT INTO response (status_response, name, ordre, validity, question_id) VALUES (?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE response SET status_response=?, name=?, ordre=? WHERE id_response=?";
    private static final String SQL_UPDATE_VALIDITY_FALSE ="UPDATE response SET validity = 0 WHERE question_id=?";
    private static final String SQL_UPDATE_VALIDITY_TRUE ="UPDATE response SET validity = 1 WHERE id_response=?";
    private static final String SQL_UPDATE_ORDER ="UPDATE response SET ordre = ? WHERE id_response=?";
    
    // not usefull
    @Override
    public List<Response> index() throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int create(Response response) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int new_id = 0;
        
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = DAOFactory.getConnection();
            
            //update
            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true, 
                    response.getStatus(), response.getName(), response.getOrder(), response.getValidity(), response.getQuestion_id());
            int status = preparedStatement.executeUpdate();
            ResultSet value = preparedStatement.getGeneratedKeys();
            if ( value.next() ) {
                new_id = value.getInt(1);
            }
            
            // récupération id de la question
            ResultSet valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if ( valeursAutoGenerees.next() ) {
                long key = valeursAutoGenerees.getLong(1);
                int k = toIntExact(key);
                response.setId(k);
            } else {
                throw new DAOException( "Échec de la création de la reponse en base, aucun ID auto-généré retourné." );
            }
            
            //gestion de la validité
            if(response.getValidity()== true){
                preparedStatement = initialisationRequetePreparee( connexion, SQL_UPDATE_VALIDITY_FALSE, true, response.getQuestion_id() );
                status = preparedStatement.executeUpdate();
                System.out.println("passe toutes réponses à faux " +status);
                
                preparedStatement = initialisationRequetePreparee( connexion, SQL_UPDATE_VALIDITY_TRUE, true, response.getId() );
                status = preparedStatement.executeUpdate();
                System.out.println("passe la réponse vrai "+status);
            
            }
            //gestion de l'ordre
            List<Response> responses = showByIdQuestion(response.getQuestion_id());
            for(int i =0; i <responses.size(); i++){
                if(response.getOrder()<= responses.get(i).getOrder() && !response.getId().equals(responses.get(i).getId())){
                    int o = responses.get(i).getOrder();
                    responses.get(i).setOrder(o+1);
                    preparedStatement = initialisationRequetePreparee( connexion, SQL_UPDATE_ORDER, true, responses.get(i).getOrder(), responses.get(i).getId() );
                    status = preparedStatement.executeUpdate();
                    System.out.println("set new order "+ responses.get(i).getOrder() + "id " + responses.get(i).getId());
                    
                }                    
            }
            if(response.getOrder()>responses.size()){
                response.setOrder(responses.size());
                preparedStatement = initialisationRequetePreparee( connexion, SQL_UPDATE_ORDER, true, response.getOrder(), response.getId() );
                status = preparedStatement.executeUpdate();
            }
            
            
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(QuestionDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        return new_id;
    }

    @Override
    public Response show(int id) throws DAOException {
    Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Response response = null;
        
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = DAOFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_BY_ID_RESPONSE, false, id );
            resultSet = preparedStatement.executeQuery();
            //Parcours de la ligne de données de l'éventuel ResulSet retourné */
            if ( resultSet.next() ) {
                response = map( resultSet );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(QuestionDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        
        return response;
    }
    
    public List<Response> showByIdQuestion(int id) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Response> responses = new ArrayList();
        
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = DAOFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_BY_QUESTION_ID, false, id );
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {              
                responses.add(map(resultSet));
            }
            //Parcours de la ligne de données de l'éventuel ResulSet retourné */
            if ( resultSet.next() ) {
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(QuestionDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        
        return responses;
    }

    @Override
    public void update(int id, Response r) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = DAOFactory.getConnection();
            
            //recupère ancien ordre
            Response resp = show(id);
            int oldOrder = resp.getOrder();
            System.out.println("ancien ordre "+ oldOrder);
            
            
            //update
            preparedStatement = initialisationRequetePreparee( connexion, SQL_UPDATE, true,
                    r.getStatus(), r.getName(), r.getOrder(), id );
            int status = preparedStatement.executeUpdate();
            
            //gestion de la validité
            if(r.getValidity()== true){
                preparedStatement = initialisationRequetePreparee( connexion, SQL_UPDATE_VALIDITY_FALSE, true, r.getQuestion_id() );
                status = preparedStatement.executeUpdate();
                System.out.println("passe toutes réponses à faux" +status);
                
                preparedStatement = initialisationRequetePreparee( connexion, SQL_UPDATE_VALIDITY_TRUE, true, r.getId() );
                status = preparedStatement.executeUpdate();
                System.out.println("passe la réponse vrai"+status);    
            
            }   
            
            //gestion de l'ordre
            List<Response> responses = showByIdQuestion(r.getQuestion_id());
            for(int i =0; i <responses.size(); i++){
                if(r.getOrder()<= responses.get(i).getOrder() && !r.getId().equals(responses.get(i).getId())){
                    int o = responses.get(i).getOrder();
                    responses.get(i).setOrder(o+1);
                    preparedStatement = initialisationRequetePreparee( connexion, SQL_UPDATE_ORDER, true, responses.get(i).getOrder(), responses.get(i).getId() );
                    status = preparedStatement.executeUpdate();
                    System.out.println("set new order "+ responses.get(i).getOrder() + "id " + responses.get(i).getId()); 
                }
                if(responses.get(i).getOrder() > oldOrder && !r.getId().equals(responses.get(i).getId())){
                    int o = responses.get(i).getOrder();
                    responses.get(i).setOrder(o-1);
                    preparedStatement = initialisationRequetePreparee( connexion, SQL_UPDATE_ORDER, true, responses.get(i).getOrder(), responses.get(i).getId() );
                    status = preparedStatement.executeUpdate();
                    System.out.println("set new order "+ responses.get(i).getOrder() + "id " + responses.get(i).getId()); 
                }
            }
            if(r.getOrder()>responses.size()){
                r.setOrder(responses.size());
                preparedStatement = initialisationRequetePreparee( connexion, SQL_UPDATE_ORDER, true, r.getOrder(), r.getId() );
                status = preparedStatement.executeUpdate();
            }
            
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
    
    private static Response map(ResultSet resultSet) throws SQLException{
    Response response = new Response();
    response.setId(resultSet.getInt("id_response"));
    response.setName(resultSet.getString("name"));
    response.setOrder(resultSet.getInt("ordre"));
    response.setQuestion_id(resultSet.getInt("question_id"));
    response.setStatus(resultSet.getBoolean("status_response"));
    response.setValidity(resultSet.getBoolean("validity"));
    return response;
    }
    
    
}
