package com.UI.menu;

import com.badlogic.gdx.Gdx;
import com.GameConstant.UIConstants;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.model.Model;
import com.screen.GameScreen;


/**
 * @brief define the information menu 
 * @author ang3d
 *
 */
public class InformationMenu extends Actor{

	private Texture background;
	private Image bg;
	private Table informationMenuTable;
	private Skin mySkin;
	
	private Label nameLabel;
	private Label typeLabel;
	private Label tenantsCapacityLabel;
	private Label pollutionLabel;
	private Label energyLabel;
	
	private String name;
	private String type;
	private int tenants;
	private int capacity;
	private float pollution;
	private float energy;
	
	//constructor
	public InformationMenu(GameScreen screen, Model game) {
		
		background = UIConstants.INFO_MENU_TEXTURE;
		bg = new Image(background);
		mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
		
		name = "";
		type = "";
		tenants = 0;
		capacity = 0;
		pollution = 0;
		energy = 0;
		
		nameLabel = new Label("Name : " + name, mySkin, "black" );
		typeLabel = new Label("Type : " + type, mySkin, "black");
		tenantsCapacityLabel = new Label("Capacity : " + tenants + "/" + capacity, mySkin, "black");
		pollutionLabel = new Label("CO2 Emissions : " + pollution, mySkin, "black");
		energyLabel = new Label("Energy : " + energy, mySkin, "black");
		
		screen.getStage().addActor(bg);
		bg.setPosition(0, 370);
		informationMenuTable = new Table();
		informationMenuTable.setPosition(90, 550);
		
		
		informationMenuTable.add(typeLabel);
		informationMenuTable.row();
		informationMenuTable.add(nameLabel);
		informationMenuTable.row();
		informationMenuTable.add(tenantsCapacityLabel);
		informationMenuTable.row();
		informationMenuTable.add(pollutionLabel);
		informationMenuTable.row();
		informationMenuTable.add(energyLabel);
	}
	
	//************************************
	//Public methods
	//************************************
	
	//update the informations to be display on the menu
	public void update(Model game, int x, int y) {
		
		this.type = game.getStructureList().get(game.search(x, y)).getSType();
		this.name = game.getStructureList().get(game.search(x, y)).getName();
		this.capacity = game.getStructureList().get(game.search(x,y)).getCapacity();
		this.tenants = game.getStructureList().get(game.search(x,y)).getTenants();
		this.pollution = game.getStructureList().get(game.search(x,y)).getPollution();
		this.energy = game.getStructureList().get(game.search(x,y)).getEnergy();
		
		typeLabel.setText("Type : " + type);
		nameLabel.setText("Name : " + name);
		tenantsCapacityLabel.setText("Capacity : " + tenants + "/" + capacity);
		pollutionLabel.setText("CO2 Emissions : " + pollution);
		energyLabel.setText("Energy : " + energy);
		
		
		if(this.type == "House" || this.type == "Building" ) {
			pollutionLabel.setVisible(false);
			energyLabel.setVisible(true);
		}else {
			pollutionLabel.setVisible(true);
			energyLabel.setVisible(false);
		}
		
	}
	
	//******************************************
	//Getters and setters
	//******************************************
	
	public Table getInformationMenuTable() {
		return this.informationMenuTable;
	}

	public Image getBackground() {
		return this.bg;
	}
	
	public String getType() {
		return this.type;
	}
}
