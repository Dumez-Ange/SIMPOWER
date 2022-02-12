package com.model.structure.housing;

import com.model.Model;
import com.model.structure.Structure;

/**
 * @brief abstract class to define an accommodation
 * @author ang3d
 *
 */
public abstract class Housing extends Structure{
	
	//constructor
	public Housing(int X, int Y, String type) {
		
		super(X, Y, type);
		this.superType = "Housing";
		
		
	}
	
	//***********************************
	//Public overridden methods
	//***********************************
	@Override
	public float getProd(Model game) {
			
		return 0;
	}
	
	@Override
	public float getPollution() {
			
		return 0;
	}

	
}
