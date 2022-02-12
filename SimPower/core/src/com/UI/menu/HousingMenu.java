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
 * @brief define the menu to add a housing (2 buttons)
 * @author ang3d
 *
 */
public class HousingMenu {

	private Table HousingMenuTable;
	private Skin mySkin;
	
	
	//constructor
	public HousingMenu(final GameScreen screen, final Model game) {
		
		mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
	
		
		TextButton button1 = new TextButton("Building(" + ModelConstants.PRICE_BUILDING + "$)", mySkin, "small");
		TextButton button2 = new TextButton("House(" + ModelConstants.PRICE_HOUSE + "$)", mySkin, "small");
		
		button1.setSize(UIConstants.BUTTON_DEFAULT_WIDTH, UIConstants.BUTTON_DEFAULT_HEIGHT);
		button2.setSize(UIConstants.BUTTON_DEFAULT_WIDTH, UIConstants.BUTTON_DEFAULT_HEIGHT);
		
		button1.addListener(new InputListener() {
			
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				
				if(game.getMoney() >= ModelConstants.PRICE_BUILDING) {
					
					screen.setSelect(true);
					game.setMoney(- ModelConstants.PRICE_BUILDING);
					screen.setTexture(GUIConstants.BUILDING_TEXTURE);
					screen.setBuildType("Building");
					screen.setUserChoice("Building");
					screen.setTile("g");
					screen.setMessage("Select a grass tile!");
					
				}
				
				return true;
			}
		});
		
		button2.addListener(new InputListener() {
			
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				
				if(game.getMoney() >= ModelConstants.PRICE_HOUSE) {
					screen.setSelect(true);
					game.setMoney(- ModelConstants.PRICE_HOUSE);
					screen.setTexture(GUIConstants.HOUSE_TEXTURE);
					screen.setBuildType("House");
					screen.setUserChoice("House");
					screen.setTile("g");
					screen.setMessage("Select a grass tile!");
				}
			
				return true;
			}
		});
		
		HousingMenuTable = new Table();
		HousingMenuTable.setVisible(false);
		HousingMenuTable.setPosition(650, 100);
		
		HousingMenuTable.add(button1);
		HousingMenuTable.add(button2);

	}
	
	public Table getHousingMenuTable() {
		return this.HousingMenuTable;
	}


}
