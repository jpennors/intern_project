/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Josselin
 */
public class User {
    
    
    	/**
	 *  ATTRIBUTES
	 * 
	 */
	
        private Integer id_user;
	private String email;
	private String password;
	private String name_user;
	private String first_name;
	private Boolean status;
        private String status_name;
	private String phone;
	private Date created_date;
	private Boolean is_admin;
        private String type_name;
	private Company company;
	
	
        public User(){
            this.is_admin = false;
            this.status = true;
            this.company = new Company();
        }
	
	/**
	 * GETTERS / SETTERS
	 * 
     * @return 
	 */
    
        public Integer getId_user(){
            return id_user;
        }
        
        public void setId_user(int id_user){
            this.id_user = id_user;
        }
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		
		// TO DO Vérification email unique + Regex
		
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName_user() {
		return name_user;
	}
	
	public void setName_user(String name_user) {		
		this.name_user = name_user;
	}
	
	public String getFirst_name() {
		return first_name;
	}
	
	public void setFirst_name(String first_name) {
		
		// TO DO Check chaîne de caractère
		
		this.first_name = first_name;
	}
	
	public Boolean getStatus() {
		return status;
	}
	
	public void setStatus(Boolean status) {
		this.status = status;
                if (this.status){
                    this.setStatus_name("Actif");
                } else {
                    this.setStatus_name("Inactif");
                }
	}
        
        public String getStatus_name(){
            return status_name;
        }
        
        private void setStatus_name(String status_name){
            this.status_name = status_name;
        }
	
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		
		// TO DO Check format 10 integers
		
		this.phone = phone;
	}
	
	public Date getCreated_date() {
		return created_date;
	}
	
	public void setCreated_date(Date created_date) {
		
		// A voir, nécessité de la créer automatiquement dans le back
		
		this.created_date = created_date;
	}
	
	public Boolean getIs_admin() {
		return is_admin;
	}
	
	public void setIs_admin(Boolean is_admin) {				
		this.is_admin = is_admin;
                if(this.is_admin){
                    this.setType_name("Administrateur");
                } else {
                    this.setType_name("Stagiaire");
                }
	}
        
        public String getType_name(){
            return type_name;
        }
        
        private void setType_name(String type_name){
            this.type_name = type_name;
        }
	
	public Company getCompany() {
		return company;
	}
	
	public void setCompany(Company company) {
		this.company = company;
	}
	
	
	/**
	 * ToString Method
	 * 
     * @return 
	 */
	
	@Override
	public String toString() {
		return "User{" + "name=" + name_user + ", first_name=" + first_name + ", email=" + email + ", status=" 
				+ status + ", phone=" + phone + ", created_date=" + created_date + ", company_id=" 
				+ company + ", is_admin=" + is_admin + "}";
	}
        
        static public User mapRequestToUser(HttpServletRequest request){
            User user = new User();
            String id_user = request.getParameter("id_user");
            try{
                user.setId_user(Integer.parseInt(id_user));
             }catch(NumberFormatException ex){}
            user.setFirst_name(request.getParameter("first_name"));
            user.setEmail(request.getParameter("email"));
            user.setName_user(request.getParameter("name_user"));
            user.setPhone(request.getParameter("phone"));
            if (request.getParameterMap().containsKey("password")){
                user.setPassword(request.getParameter("password"));
            }
            if (request.getParameterMap().containsKey("status")){
                user.setStatus(Boolean.parseBoolean(request.getParameter("status")));
            }
            user.setIs_admin(Boolean.parseBoolean(request.getParameter("is_admin")));
            return user;
        }
	
}

