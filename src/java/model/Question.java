/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author lolal
 */
public class Question {
    
    /**
     * ATTRIBUTES
     */
    private Boolean status; 
    private String status_name;
    private String sentence; 
    private Integer order; 
    private Integer id_question;
    private List<Response> responses;

    /**
     * CONSTUCTOR
     * @param id_question
     * @param status
     * @param sentence
     * @param order 
     */
    public Question(Boolean status, String status_name, String sentence, Integer order, Integer id_question) {
        this.status = status;
        this.status_name = status_name;
        this.sentence = sentence;
        this.order = order;
        this.id_question = id_question;
    }

    public Question() {
        this.status = true;
    }
    
    
    /**
     * GETTERS SETTERS 
     */
    public Integer getId_question() {
        return id_question;
    }

    public void setId_question(Integer id) {
        this.id_question = id;
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

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }
    
    public List<Response> get_reponses(){
        return responses;
    }
    
    public void set_response(List<Response> responses){
        this.responses = responses;
    }
    
    static public Question mapRequestToQuestion(HttpServletRequest request){
        Question question = new Question();
        String id_question = request.getParameter("id_question");
        try{
            question.setId_question(Integer.parseInt(id_question));
        }catch(NumberFormatException ex){}
        question.setSentence(request.getParameter("sentence"));
        //question.setOrder(Integer.parseInt(request.getParameter("order")));
        if (request.getParameterMap().containsKey("status")){
            question.setStatus(Boolean.parseBoolean(request.getParameter("status")));
        }
        return question;
    }
    
    
    
    
}
