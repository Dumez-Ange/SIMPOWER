package com.model.structure.power;

import com.model.structure.Structure;


/**
 * @brief abstract class that define a power plant
 * @author ang3d
 *
 */
public abstract class PowerPlant extends Structure{
	
	protected boolean isWorking;
	protected float production; 
	
	//constructor
	public PowerPlant(int X, int Y, String type) {
		
		super(X, Y, type);
		this.superType = "Power";
		
		isWorking = true;
	}
	
	//************************************
	//public overridden methods
	//************************************
	@Override
	public float getEnergy() {
		return 0;
	}
	
	
}
