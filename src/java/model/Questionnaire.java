/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author lolal
 */
public class Questionnaire {
    
    /**
     * ATTRIBUTES
     */
    private String subject; 
    private Boolean status; 
    private User admin_id; // (/!\ BDD access) all User info
    
    /**
     * CONSTUCTOR
     * @param subject
     * @param status
     * @param admin_id 
     */
    public Questionnaire(String subject, User admin_id) {
        this.subject = subject;
        this.status = true;
        this.admin_id = admin_id;
    }
    
    public Questionnaire(String subject){
    // temporary, need get admin_id 
        this.subject = subject;
        this.status = true;
        this.admin_id = null;
    }
    
    /**
     * GETTERS SETTERS 
     */
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public User getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(User admin_id) {
        this.admin_id = admin_id;
    }
    
    
    
}


