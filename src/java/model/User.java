/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.Company;
import java.util.Date;

/**
 *
 * @author Josselin
 */
public class User {
    
    
    	/**
	 *  ATTRIBUTES
	 * 
	 */
	
	private String email;
	private String password;
	private String name;
	private String first_name;
	private Boolean status;
	private String phone;
	private Date created_date;
	private Boolean is_admin;
	private Company company_id;
	
	// TO DO : Add company
	

	/**
	 * CONSTRUCTOR
	 * @param email
	 * @param password
	 * @param name
	 * @param first_name
	 * @param etatus
	 * @param phone
	 * @param created_date
	 * @param is_admin
	 * @param company_id
	 */
	
	public User(String email, String password, String name, String first_name, Boolean status, String phone,
			Date created_date, Boolean is_admin, Company company_id) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.first_name = first_name;
		this.status = status;
		this.phone = phone;
		this.created_date = created_date;
		this.is_admin = is_admin;
		this.company_id = company_id;
	}

    public User(String email, String password, String name, String first_name, String phone, Boolean isAdmin) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        this.email = email;
        this.password = password;
        this.name = name;
        this.first_name = first_name;
        this.status = true;
        this.phone = phone;
        this.created_date = new Date();
        this.is_admin = isAdmin;
        this.company_id = null;
    }
	
	
	/**
	 * GETTERS / SETTERS
	 * 
     * @return 
	 */
	
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
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		
		// TO DO Check chaîne de caractère
		
		this.name = name;
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
		
		// Si user est admin, permission !
		
		this.is_admin = is_admin;
	}
	
	public Company getCompany_id() {
		return company_id;
	}
	
	public void setCompany_id(Company company_id) {
		this.company_id = company_id;
	}
	
	
	/**
	 * ToString Method
	 * 
     * @return 
	 */
	
	@Override
	public String toString() {
		return "User{" + "name=" + name + ", first_name=" + first_name + ", email=" + email + ", status=" 
				+ status + ", phone=" + phone + ", created_date=" + created_date + ", company_id=" 
				+ company_id + ", is_admin=" + is_admin + "}";
	}
	
}

