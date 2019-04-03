/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Josselin
 */
public class Company {

	/**
	 *  ATTRIBUTES
	 * 
	 */
	
	private String name;

	
	/**
	 * CONSTRUCTOR
	 * @param name
	 */

	public Company(String name) {
		this.name = name;
	}

	
	/**
	 * GETTERS / SETTERS
	 * 
	 */

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}

