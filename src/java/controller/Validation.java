/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Josselin
 */
public class Validation {
    
    
    String message;
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    
    Validation(){}
    
    Validation(String message){
        this.message = message;
    }
    
    public String getMessage(){
        return this.message;
    }
    
    static public List<Validation> userRequestValidation(HttpServletRequest request){
        List<Validation> errors = new ArrayList<Validation>();
        
        if(request.getParameterMap().containsKey("phone")){
            try{
                int id = Integer.parseInt(request.getParameter("phone"));
             }catch(NumberFormatException ex){
                //errors.set(errors.size(), new Validation("Numéro de téléphone incorrect"));  
                errors.add(new Validation("Numéro de téléphone incorrect"));
             }
        }
        if(request.getParameterMap().containsKey("email")){
            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(request.getParameter("phone"));
            if (!matcher.find()){
                errors.add(new Validation("Email incorrect"));
                //errors.set(errors.size(), new Validation("Email incorrect"));
            }
        }
        return errors;
    }
 
}
