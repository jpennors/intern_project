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
public class Question {
    
    /**
     * ATTRIBUTES
     */
    private Integer id; 
    private Boolean status; 
    private String sentence; 
    private Integer order; 

    /**
     * CONSTUCTOR
     * @param id
     * @param status
     * @param sentence
     * @param order 
     */
    public Question(Integer id, Boolean status, String sentence, Integer order) {
        this.id = id;
        this.status = status;
        this.sentence = sentence;
        this.order = order;
    }
    
    
    /**
     * GETTERS SETTERS 
     */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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
    
    
    
    
    
    
}
