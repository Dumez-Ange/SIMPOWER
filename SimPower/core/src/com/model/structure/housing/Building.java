package com.model.structure.housing;

import com.GameConstant.ModelConstants;


/**
 * @brief define a Building
 * @author ang3d
 *
 */
public class Building extends Housing{

	public Building(int X, int Y, String type, int index) {
		
		super(X, Y, type);
		
		this.capacity = ModelConstants.CAPACITY_BUILDING;
		this.name = "B" + index;
		this.tenants = 0;
	}
	
	//**********************************
	//Public overridden methods
	//**********************************
	
	//method that return the energy used depending of the number of tenants 
	@Override
	public float getEnergy() {
		
		return ModelConstants.CONSUMPTION_BUILDING * tenants;
	}

}
