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
	
        private Integer matriculation;
	private String name;

	
	/**
	 * CONSTRUCTOR
	 * @param name
         * @param matriculation
	 */

	public Company(Integer matriculation, String name) {
		this.name = name;
                this.matriculation = matriculation;
	}

	
	/**
	 * GETTERS / SETTERS
	 * 
         * @return 
	 */

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
        
        public Integer getMatriculation(){
            return matriculation; 
        }
        
        public void setMatriculation(Integer matriculation){
            this.matriculation = matriculation;
        }
	
}

