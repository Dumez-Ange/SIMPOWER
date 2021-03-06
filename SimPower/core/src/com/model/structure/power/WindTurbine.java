package com.model.structure.power;

import com.GameConstant.ModelConstants;
import com.model.Model;

/**
 * @brief define a wind turbine
 * @author ang3d
 *
 */
public class WindTurbine extends PowerPlant{
	
	//constructor
	public WindTurbine(int X, int Y, String type, int index) {
		super(X, Y, type);
		this.capacity = ModelConstants.CAPACITY_WINDTURBINE;
		this.name = "W" + index;
		this.tenants = 0;
		
	}
	
	//*******************************************
	//public overridden methods
	//******************************************
	
	//return the production of the plant if it is working (if there is wind)
	@Override
	public float getProd(Model game) {
		
		if(game.getWeather().getWind()) {
			
			this.isWorking = true;
			
		}else {
			
			this.isWorking = false;
			
		}
		
		if(isWorking) {
			return this.production = ModelConstants.PRODUCTIVITY_WINDTURBINE * this.tenants;
		}
		
		return 0;
		
	}

	
	//return the pollution generated by the plant 
	@Override
	public float getPollution() {
		
		return ModelConstants.POLLUTION_WINDTURBINE;
	}
	
	

}
