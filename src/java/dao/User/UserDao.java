/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.User;

import dao.DAOException;
import java.util.List;
import model.User;

/**
 *
 * @author Josselin
 */
public interface UserDao {
    
    List<User> index() throws DAOException;
    
    void create(User user) throws DAOException;
    
    User show(int id) throws DAOException;
    
    void update(int i) throws DAOException;
    
    void delete(int i) throws DAOException;
    
}
