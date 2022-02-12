package com.model.people;

import com.GameConstant.ModelConstants;
import com.model.Model;


/**
 * @brief class defining what is a worker 
 * @author ang3d
 *
 */
public class People {
	
	private String name;
	private String namePower;
	private String nameHouse;
	
	private int satisfaction;
	
	
	//constructor
	public People(String name, String namePower, String nameHouse) {
		this.name = name;
		this.nameHouse = nameHouse;
		this.namePower = namePower;
		this.satisfaction = ModelConstants.INITIALISATION_SATISFACTION;
	}
	
	//*************************************************
	//Public methods
	//*************************************************
	
	//method that calculate the satisfaction of the worker 
	public void SatisUpdate(Model game) {
		
		
		float coef = (float) (1 - (0.001*game.getPollution()));
		
		this.satisfaction = ModelConstants.INITIALISATION_SATISFACTION;
		
		if(this.namePower == null) {
			this.satisfaction -= ModelConstants.UNEMPLOYED_MALUS;
		}
		if(this.nameHouse == null) {
			this.satisfaction -= ModelConstants.HOMELESS_MALUS;
		}
		
		this.satisfaction *= coef;
	}
	
	
	//*********************************************
	//Getters and setters
	//*********************************************
	
	public String getName() {
		return this.name;
	}
	
	public String getNamePower() {
		return this.namePower;
	}
	
	public String getNameHouse() {
		return this.nameHouse;
	}
	
	public float getSatisfaction() {
		return this.satisfaction;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setNamePower(String name) {
		this.namePower = name;
	}
	
	public void setNameHouse(String name) {
		this.nameHouse = name;
	}
	
	public void setSatis(int satis) {
		this.satisfaction = satis;
	}
	
	
}
