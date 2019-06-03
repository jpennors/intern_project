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
    private String status_name;
    private String name; 
    private Integer order; 
    private Boolean validity; 
    private String validity_name;
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
    public Response(Integer id, String name,Integer order, Boolean validity, Integer question_id) {
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
        if (this.status == true)
            this.setStatus_name("Actif");
        else 
            this.setStatus_name("Inactif");
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
        if (this.validity == true)
            this.setValidity_name("Valide");
        else 
            this.setValidity_name("Non valide");
    }

    public Integer getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Integer question_id) {
        this.question_id = question_id;
    } 

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }

    public String getValidity_name() {
        return validity_name;
    }

    public void setValidity_name(String validity_name) {
        this.validity_name = validity_name;
    }
    
    static public Response mapRequestToResponse(HttpServletRequest request){
        Response response = new Response();
        String id = request.getParameter("id_response");
        try{
            response.setId(Integer.parseInt(id));
        }catch(NumberFormatException ex){}
        response.setName(request.getParameter("name"));
        response.setValidity(Boolean.parseBoolean(request.getParameter("validity")));
        response.setOrder(Integer.parseInt(request.getParameter("ordre")));
        if (request.getParameterMap().containsKey("status")){
            response.setStatus(Boolean.parseBoolean(request.getParameter("status")));
        }
        return response;
    }
        
    
}
