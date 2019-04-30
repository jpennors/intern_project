/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;


/**
 *
 * @author lolal
 */
public interface DAOInterface<T> {
    List<T> index() throws DAOException;
    
    void create(T t) throws DAOException;
    
    T show(int id) throws DAOException;
    
    void update(int i) throws DAOException;
    
    void delete(int i) throws DAOException;
}
