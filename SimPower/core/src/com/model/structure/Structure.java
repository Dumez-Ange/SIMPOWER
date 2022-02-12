package com.model.structure;

import com.model.Model;

//Abstract class of a structure
public abstract class Structure {
	
	protected String name;
	protected String type;
	protected String superType;
	
	protected int X;
	protected int Y;
	
	protected int capacity;
	protected int tenants;
	
	
	
	
	//constructor
	public Structure(int X, int Y, String type) {
		this.X = X;
		this.Y = Y;
		this.type = type;
		
	}
	
	//*********************************
	//Public abstract methods
	//*********************************
	
	public abstract float getProd(Model game);
	
	public abstract float getEnergy();
	
	public abstract float getPollution();
	
	
	//*******************************
	//Getters and setters
	//******************************
	
	public String getSType() {
		return this.type;
	}
	
	public int getX() {
		return this.X;
	}
	
	public int getY() {
		return this.Y;
	}
	
	public int getTenants() {
		return this.tenants;
	}
	
	public int getCapacity() {
		return this.capacity;
	}
	
	public String getName() {	
		return this.name;
	}
	
	public String getSuperType() {
		return this.superType;
	}
	
	public void setTenants(int nb) {
		this.tenants += nb;
	}
	
	

	
}
