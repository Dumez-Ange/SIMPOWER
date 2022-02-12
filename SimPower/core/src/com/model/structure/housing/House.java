package com.model.structure.housing;

import com.GameConstant.ModelConstants;

/**
 * @brief define a House
 * @author ang3d
 *
 */
public class House extends Housing{

	//constructor
	public House(int X, int Y, String type, int index) {
			
		super(X, Y, type);
		this.capacity = ModelConstants.CAPACITY_HOUSE;
		this.name = "H" + index;
		this.tenants = 0;
	}
	
	//**********************************
	//Public overridden methods
	//**********************************
	
	//method that return the energy used depending of the number of tenants 
	@Override
	public float getEnergy() {
		
		return this.tenants * ModelConstants.CONSUMPTION_HOUSE;
	}

}
