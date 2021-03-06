package com.model.structure.power;

import com.GameConstant.ModelConstants;
import com.model.Model;

/**
 * @brief define a solar panel
 * @author ang3d
 *
 */
public class Solar extends PowerPlant {
	
	//constructor
	public Solar(int X, int Y, String type, int index) {
		super(X, Y, type);
		
		this.capacity = ModelConstants.CAPACITY_SOLAR;
		this.name = "S" + index;
		this.tenants = 0;
		
	}
	
	//***************************************
	//Public overridden methods
	//**************************************
	
	//return the production of the plant if it is working (if there is sun)
	@Override
	public float getProd(Model game) {
		
		if(game.getWeather().getSun()) {
			
			this.isWorking = true;
			
		}else {
			
			this.isWorking = false;
			
		}
		
		if(isWorking) {
			return this.production = ModelConstants.PRODUCTIVITY_SOLAR * this.tenants;
		}
		return 0;
		
	}

	//return the pollution generated by the plant 
	@Override
	public float getPollution() {
		
		return ModelConstants.POLLUTION_SOLAR;
	}
	
	
	
	
}
