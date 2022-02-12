package com.model;

import com.GameConstant.ModelConstants;

/**
 * @brief This is the weather of game base on random generation
 * @author ang3d
 *
 */
public class Weather {
	
	private boolean wind;
	private boolean rain;
	private boolean sun;
	
	// constructor
	public Weather() {
		
		this.wind = false;
		this.rain = false;
		this.sun = false;
	}
	
	//*************************************************
	// Public methods
	//*************************************************
	public void update() {
		
		double random = Math.random() * 100;
		
		if(random < ModelConstants.RATE_RAIN) {
			this.rain = true;
			this.sun = false;
		}else if (random >= ModelConstants.RATE_RAIN && random < (100 - ModelConstants.RATE_SUN)) {
			this.rain = false;
			this.sun = false;
		}else {
			this.rain = false;
			this.sun = true;
		}
		
		if(random < ModelConstants.RATE_WIND) {
			this.wind = true;
		}else {
			this.wind = false;
		}
	}
	
	//****************************************************
	// Getters and setters
	//****************************************************
	public boolean getWind() {
		return this.wind;
	}
	public boolean getSun() {
		return this.sun;
	}
	public boolean getRain() {
		return this.rain;
	}
}
