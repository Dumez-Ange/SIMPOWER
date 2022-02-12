package com.UI.menu;

import com.GameConstant.GUIConstants;
import com.GameConstant.ModelConstants;
import com.GameConstant.UIConstants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.model.Model;
import com.screen.GameScreen;

/**
 * @brief define the menu to add a plant onto the map (5 buttons)
 * @author ang3d
 *
 */
public class PowerMenu {

	private Table powerPlantMenu;
	private Skin mySkin;
	
	//constructor
	public PowerMenu(final GameScreen screen, final Model game) {
		
		mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
		
	
		
		TextButton button1 = new TextButton("Nuclear(" + ModelConstants.PRICE_NUCLEAR + "$)", mySkin, "small");
		TextButton button2 = new TextButton("Coal-plant(" + ModelConstants.PRICE_COALPLANT + "$)", mySkin, "small");
		TextButton button3 = new TextButton("Solar(" + ModelConstants.PRICE_SOLAR + "$)", mySkin, "small");
		TextButton button4 = new TextButton("Turbine(" + ModelConstants.PRICE_WINDTURBINE + "$)", mySkin, "small");
		TextButton button5 = new TextButton("Dam(" + ModelConstants.PRICE_DAM + "$)", mySkin, "small");
		
		button1.setSize(UIConstants.BUTTON_DEFAULT_WIDTH, UIConstants.BUTTON_DEFAULT_HEIGHT);
		button2.setSize(UIConstants.BUTTON_DEFAULT_WIDTH, UIConstants.BUTTON_DEFAULT_HEIGHT);
		button3.setSize(UIConstants.BUTTON_DEFAULT_WIDTH, UIConstants.BUTTON_DEFAULT_HEIGHT);
		button4.setSize(UIConstants.BUTTON_DEFAULT_WIDTH, UIConstants.BUTTON_DEFAULT_HEIGHT);
		button5.setSize(UIConstants.BUTTON_DEFAULT_WIDTH, UIConstants.BUTTON_DEFAULT_HEIGHT);
		
		
		button1.addListener(new InputListener() {
			
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				
				if(game.getMoney() >= ModelConstants.PRICE_NUCLEAR) {
					
					screen.setSelect(true);
					game.setMoney(- ModelConstants.PRICE_NUCLEAR);
					screen.setTexture(GUIConstants.NUCLEAR_PLANT_TEXTURE);
					screen.setBuildType("Nuclear");
					screen.setUserChoice("Nuclear");
					screen.setTile("u");
					screen.setMessage("Select an uranium tile!");
				}
				
				return true;
			}
		});
		
		button2.addListener(new InputListener() {
			
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				
				if(game.getMoney() >= ModelConstants.PRICE_COALPLANT) {
					screen.setSelect(true);
					game.setMoney(- ModelConstants.PRICE_COALPLANT);
					screen.setTexture(GUIConstants.COAL_PLANT_TEXTURE);
					screen.setBuildType("Coal");
					screen.setUserChoice("Coal");
					screen.setTile("c");
					screen.setMessage("Select a coal tile!");
				}
			
				return true;
			}
		});
		
		button3.addListener(new InputListener() {
			
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				
				if(game.getMoney() >= ModelConstants.PRICE_SOLAR) {
					screen.setSelect(true);
					game.setMoney(- ModelConstants.PRICE_SOLAR);
					screen.setTexture(GUIConstants.SOLAR_PANELS_TEXTURE);
					screen.setBuildType("Solar");
					screen.setUserChoice("Solar");
					screen.setTile("g");
					screen.setMessage("Select a grass tile!");
				}
			
				return true;
			}
		});
		
		button4.addListener(new InputListener() {
			
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				
				if(game.getMoney() >= ModelConstants.PRICE_WINDTURBINE) {
					screen.setSelect(true);
					game.setMoney(- ModelConstants.PRICE_WINDTURBINE);
					screen.setTexture(GUIConstants.WINDTURBINE_TEXTURE);
					screen.setBuildType("Turbine");
					screen.setUserChoice("Wind");
					screen.setTile("g");
					screen.setMessage("Select a grass tile!");
				}
			
				return true;
			}
		});
		
		button5.addListener(new InputListener() {
			
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				
				if(game.getMoney() >= ModelConstants.PRICE_DAM) {
					screen.setSelect(true);
					game.setMoney(- ModelConstants.PRICE_DAM);
					screen.setTexture(GUIConstants.DAM_RIGHT_TEXTURE);
					screen.setBuildType("Dam");
					screen.setUserChoice("DamRightOriented");
					screen.setTile("w");
					screen.setMessage("Select a water tile!");
				}
			
				return true;
			}
		});
		

		
		
		powerPlantMenu = new Table();
		powerPlantMenu.setVisible(false);
		powerPlantMenu.setPosition(650, 100);
		
		powerPlantMenu.add(button1);
		powerPlantMenu.add(button2);
		powerPlantMenu.add(button3);
		powerPlantMenu.add(button4);
		powerPlantMenu.add(button5);
	}



	public Table getPowerPlantMenu() {
		return powerPlantMenu;
	}

	
	
}
