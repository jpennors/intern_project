/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author lolal
 */
public class Questionnaire {
    
    /**
     * ATTRIBUTES
     */
    private Integer id_questionnaire;
    private String subject; 
    private Boolean status; 
    private String status_name;
    private User createur_id; 

    /**
     * CONSTRUCTOR
     * @param id_questionnaire
     * @param subject
     * @param status
     * @param createur_id 
     */
    public Questionnaire(Integer id_questionnaire, String subject, Boolean status, User createur_id) {
        this.id_questionnaire = id_questionnaire;
        this.subject = subject;
        this.status = status;
        this.createur_id = createur_id;
    }
    
    public Questionnaire(String subject){
    // temporary, need get admin_id 
        this.subject = subject;
        this.status = true;
        this.createur_id = null;
    }

    public Questionnaire() {
        this.status =true;
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
        if (this.status == true)
            this.setStatus_name("Actif");
        else 
            this.setStatus_name("Inactif");
            
    }    

    public int getId_questionnaire() {
        return id_questionnaire;
    }

    public void setId_questionnaire(Integer id_questionnaire) {
        this.id_questionnaire = id_questionnaire;
    }

    public User getCreateur_id() {
        return createur_id;
    }

    public void setCreateur_id(User createur_id) {
        this.createur_id = createur_id;
    }
    
    public String getStatus_name(){
        return status_name;
        }
        
    private void setStatus_name(String status_name){
        this.status_name = status_name;
    }
    
    static public Questionnaire mapRequestToQuestionnaire(HttpServletRequest request){
        Questionnaire questionnaire = new Questionnaire();
        String id_questionnaire = request.getParameter("id_questionnaire");
        try{
            questionnaire.setId_questionnaire(Integer.parseInt(id_questionnaire));
        }catch(NumberFormatException ex){}
        questionnaire.setSubject(request.getParameter("subject"));
        if (request.getParameterMap().containsKey("status")){
            questionnaire.setStatus(Boolean.parseBoolean(request.getParameter("status")));
        }
        return questionnaire;
    }
    
}


