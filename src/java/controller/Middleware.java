/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author Josselin
 */
public class Middleware {

    static public boolean isConnected(HttpServletRequest request, HttpServletResponse response) throws IOException{
    
        HttpSession session = request.getSession();
        if(session.getAttribute("user") == null){
            return false;
        }
        return true;
    }
    
    static public boolean isAdmin(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        if(session.getAttribute("user") != null){
            User user = (User) session.getAttribute("user");
            if(user.getIs_admin()){
                return true;
            }
        }
        return false;
    }
    
    static public User getLoggedUser(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        if(session.getAttribute("user") != null){
            return (User) session.getAttribute("user");
        }
        return null;
    }
    
    static public void logout(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        session.invalidate();
    }
}
