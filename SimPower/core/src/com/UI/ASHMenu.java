package com.UI;

import com.GameConstant.UIConstants;
import com.UI.menu.HousingMenu;
import com.UI.menu.InformationMenu;
import com.UI.menu.PowerMenu;
import com.UI.menu.WorkerMenu;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.model.Model;
import com.screen.GameScreen;

/**
 * @brief primary menu that is used to show add/delete structure menus and show the workers' menus (add delete re-assign)
 * @author ang3d
 *
 */
public class ASHMenu {
	
	private PowerMenu powerMenu;
	private HousingMenu houseMenu;
	private InformationMenu infoMenu;
	private WorkerMenu workerMenu;
	
	private Table ASHMenuTable;
	private Table addMenuTable;
	
	private Skin mySkin;
	
	// constructor
	public ASHMenu(final GameScreen screen, final Model game) {
		
		powerMenu = new PowerMenu(screen, game);
		houseMenu = new HousingMenu(screen, game);
		workerMenu = new WorkerMenu(screen, game);
		infoMenu = new InformationMenu(screen, game);
		
		mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
		
		ASHMenuTable = new Table();
	
		TextButton ASHMenuButton1 = new TextButton("ADD", mySkin, "small");
		TextButton ASHMenuButton2 = new TextButton("DELETE", mySkin, "small");
		TextButton ASHMenuButton3 = new TextButton("WORKERS", mySkin, "small");
		
		ASHMenuButton1.setSize(UIConstants.BUTTON_DEFAULT_WIDTH, UIConstants.BUTTON_DEFAULT_HEIGHT);
		ASHMenuButton2.setSize(UIConstants.BUTTON_DEFAULT_WIDTH, UIConstants.BUTTON_DEFAULT_HEIGHT);
		ASHMenuButton3.setSize(UIConstants.BUTTON_DEFAULT_WIDTH, UIConstants.BUTTON_DEFAULT_HEIGHT);
		
		
		ASHMenuButton1.addListener(new InputListener() {
			
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				
				screen.setSelect(false);
				screen.setselect2(false);
				screen.setselect3(false);
				workerMenu.getWorkerMenuTable().setVisible(false);
				addMenuTable.setVisible(true);
				
				return true;
			}
		});
		
		ASHMenuButton2.addListener(new InputListener() {
			
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				
				screen.setSelect(false);
				screen.setselect2(false);
				screen.setselect3(true);
				workerMenu.getWorkerMenuTable().setVisible(false);
				powerMenu.getPowerPlantMenu().setVisible(false);
				houseMenu.getHousingMenuTable().setVisible(false);
				addMenuTable.setVisible(false);
				
				return true;
			}
		});
		
		ASHMenuButton3.addListener(new InputListener() {
			
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				
				screen.setSelect(false);
				screen.setselect2(false);
				screen.setselect3(false);
				addMenuTable.setVisible(false);
				powerMenu.getPowerPlantMenu().setVisible(false);
				houseMenu.getHousingMenuTable().setVisible(false);
				workerMenu.getWorkerMenuTable().setVisible(true);
				
				return true;
			}
		});
		
		ASHMenuTable.setPosition(1200, 550);
		
		ASHMenuTable.add(ASHMenuButton1);
		ASHMenuTable.row();
		ASHMenuTable.add(ASHMenuButton2);
		ASHMenuTable.row();
		ASHMenuTable.add(ASHMenuButton3);
		
		addMenuTable = new Table();
		addMenuTable.setVisible(false);
		
		TextButton addMenuButton1 = new TextButton("Power", mySkin, "small");
		TextButton addMenuButton2 = new TextButton("Housing", mySkin, "small");
		

		addMenuButton1.setSize(UIConstants.BUTTON_DEFAULT_WIDTH, UIConstants.BUTTON_DEFAULT_HEIGHT);
		addMenuButton2.setSize(UIConstants.BUTTON_DEFAULT_WIDTH, UIConstants.BUTTON_DEFAULT_HEIGHT);
		
		
		addMenuButton1.addListener(new InputListener() {
			
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				
				
				houseMenu.getHousingMenuTable().setVisible(false);
				powerMenu.getPowerPlantMenu().setVisible(true);
				
				
				return true;
			}
		});
		
		addMenuButton2.addListener(new InputListener() {
	
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				
				powerMenu.getPowerPlantMenu().setVisible(false);
				houseMenu.getHousingMenuTable().setVisible(true);
				
				return true;
			}
		});
		
		
		addMenuTable.setPosition(1200, 300);
		
		addMenuTable.add(addMenuButton1);
		addMenuTable.row();
		addMenuTable.add(addMenuButton2);
		

	}
	
	//***************************************
	//Public methods
	//***************************************
	
	//method to determine if it is possible to place the structure onto a certain tile
	public boolean isPossible(GameScreen screen, Model game, int X, int Y, String tile) {
		
		if(screen.getTilemap().getMap()[X][Y].equals(tile) && game.getMap()[X][Y] == 0) {
			return true;
		}
		
		return false;
	}
	
	//method to add actors to the GameScreen stage 
	public void addActors(Stage stage)
	{
		
		stage.addActor(ASHMenuTable);
		stage.addActor(addMenuTable);
		stage.addActor(infoMenu);
		stage.addActor(workerMenu);
		
		stage.addActor(powerMenu.getPowerPlantMenu());
		stage.addActor(houseMenu.getHousingMenuTable());
		stage.addActor(infoMenu.getInformationMenuTable());
		stage.addActor(workerMenu.getWorkerMenuTable());
		stage.addActor(workerMenu.getAddWorkerMenuTable());
		stage.addActor(workerMenu.getDeleteWorkerMenuTable());
		stage.addActor(workerMenu.getAssignWorkerMenuTable());
	}

	
	
	//******************************************
	//Getters and setters
	//******************************************
	
	
	
	
	public Table getASHMenuTable() {
		
		return this.ASHMenuTable;
	}

	public PowerMenu getPowerMenuTable() {
		
		return this.powerMenu;
	}

	public Table getaddMenuTable() {
		
		return this.addMenuTable;
	}
	
	public HousingMenu getHouseMenu() {
		return this.houseMenu;
	}
	
	public InformationMenu getInformationMenu() {
		return this.infoMenu;
	}
	
	public WorkerMenu getWorkerMenu() {
		return this.workerMenu;
	}
	
}	
