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
	private String name_company;

        
        
	
	/**
	 * CONSTRUCTOR
	 * @param name
         * @param matriculation
	 */

	public Company(Integer matriculation, String name_company) {
		this.name_company = name_company;
                this.matriculation = matriculation;
	}
        
        public Company(){
            this.name_company = null;
            this.matriculation = null;
        }

	
	/**
	 * GETTERS / SETTERS
	 * 
         * @return 
	 */

	public String getName_company() {
		return name_company;
	}
	
	public void setName_company(String name_company) {
		this.name_company = name_company;
	}
        
        public Integer getMatriculation(){
            return matriculation; 
        }
        
        public void setMatriculation(Integer matriculation){
            this.matriculation = matriculation;
        }
	
}

