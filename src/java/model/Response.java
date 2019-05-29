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
public class Response {
    
    /**
     * ATTRIBUTES
     */
    private Integer id; 
    private Boolean status; 
    private String name; 
    private Integer order; 
    private Boolean validity; 
    private Integer question_id; // id question in BDD

    
    /**
     * CONSTRUCTOR
     * @param id
     * @param status
     * @param name
     * @param order
     * @param validity
     * @param question_id 
     */
    public Response(Integer id, String name, Integer order, Boolean validity, Integer question_id) {
        this.id = id;
        this.status = true;
        this.name = name;
        this.order = order;
        this.validity = validity;
        this.question_id = question_id;
    }

    public Response() {
        this.status = true; //To change body of generated methods, choose Tools | Templates.
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Boolean getValidity() {
        return validity;
    }

    public void setValidity(Boolean validity) {
        this.validity = validity;
    }

    public Integer getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Integer question_id) {
        this.question_id = question_id;
    }   
    
    
}
