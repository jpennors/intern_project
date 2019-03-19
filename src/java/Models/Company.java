/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Josselin
 */
public class Company {

	/**
	 *  ATTRIBUTES
	 * 
	 */
	
	private Integer id;
	private String name;

	
	/**
	 * CONSTRUCTOR
	 * @param id
	 * @param name
	 */

	public Company(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	
	/**
	 * GETTERS / SETTERS
	 * 
	 */
	
	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
