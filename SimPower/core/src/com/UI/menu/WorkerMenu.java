package com.UI.menu;

import com.GameConstant.ModelConstants;
import com.GameConstant.UIConstants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.model.Model;
import com.model.people.People;
import com.screen.GameScreen;


/**
 * @brief define the menu that display the 3 buttons to manage the workers (add delete and re assign)
 * @author ang3d
 *
 */
public class WorkerMenu extends Actor{

	
	private Texture background;
	private Image bg;
	
	private Table WorkerMenuTable;
	private Table addWorkerMenuTable;
	private Table deleteWorkerMenuTable;
	private Table assignMenuTable;
	
	private Skin mySkin;
	private SelectBox<String> assignPeopleSelect;
	private SelectBox<String> addPowerSelect;
	private SelectBox<String> addHouseSelect;
	private SelectBox<String> deletePeopleSelect;
	private SelectBox<String> assignPowerSelect;
	private SelectBox<String> assignHouseSelect;
	private TextField name;
	
	private String[] s1;
	private String[] s2;
	private String[] s3;
	
	private	int indexPower;
	private	int indexHouse;
	
	
	
	//constructor
	public WorkerMenu(final GameScreen screen, final Model game) {
		

		background = UIConstants.WORKER_MENU_TEXTURE;
		bg = new Image(background);
		
		screen.getStage().addActor(bg);
		bg.setPosition(350, 300);
		bg.setVisible(false);
		
		mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
		
		deletePeopleSelect = new SelectBox<String>(mySkin);
		assignPeopleSelect = new SelectBox<String>(mySkin);
		addPowerSelect = new SelectBox<String>(mySkin);
		addHouseSelect = new SelectBox<String>(mySkin);
		assignPowerSelect = new SelectBox<String>(mySkin);
		assignHouseSelect = new SelectBox<String>(mySkin);
		
		Label worker = new Label("Name : ", mySkin, "black");
		Label powerplant = new Label("    Power plant : ", mySkin, "black");
		Label housing = new Label("    House : ", mySkin, "black");
		
		name = new TextField("", mySkin);
		
		
		
		TextButton addButton = new TextButton("NEW(" + ModelConstants.PRICE_WORKER + "$)", mySkin, "small");
		TextButton deleteButton = new TextButton("DELETE", mySkin, "small");
		TextButton assignButton = new TextButton("RE-ASSIGN", mySkin, "small");
		
		TextButton addConfirmButton = new TextButton("OK", mySkin, "small");
		TextButton deleteConfirmButton = new TextButton("OK", mySkin, "small");
		TextButton assignConfirmButton = new TextButton("OK", mySkin, "small");
		
		addButton.addListener(new InputListener() {
			
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				bg.setVisible(true);
				addWorkerMenuTable.setVisible(true);
				deleteWorkerMenuTable.setVisible(false);
				assignMenuTable.setVisible(false);
				return true;
			}
		});
		
		deleteButton.addListener(new InputListener() {
			
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				bg.setVisible(true);
				addWorkerMenuTable.setVisible(false);
				deleteWorkerMenuTable.setVisible(true);
				assignMenuTable.setVisible(false);
				return true;
			}
		});

		assignButton.addListener(new InputListener() {
	
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				bg.setVisible(true);
				addWorkerMenuTable.setVisible(false);
				deleteWorkerMenuTable.setVisible(false);
				assignMenuTable.setVisible(true);
				return true;
			}
		});
		
		
		
		
		
		addConfirmButton.addListener(new InputListener() {
			
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				
				indexPower = game.search(addPowerSelect.getSelected());
				indexHouse = game.search(addHouseSelect.getSelected());
				
				
				addWorkerMenuTable.setVisible(false);
				bg.setVisible(false);
				
				if(name.getText() != "" && addPowerSelect.getSelected() != null && addHouseSelect.getSelected() != null && game.getMoney() >= ModelConstants.PRICE_WORKER) {
					
					game.setMoney(-ModelConstants.PRICE_WORKER);
					
					game.getPeopleList().add(new People(name.getText(), addPowerSelect.getSelected(), addHouseSelect.getSelected()));
					
					//the following block update the people SelectBox
					int index3 = 0;
					
					s3 = new String[game.getPeopleList().size()];
					
					for(People p : game.getPeopleList()) {
						s3[index3] = p.getName();
						index3++;
					}
		
					assignPeopleSelect.setItems(s3);
					deletePeopleSelect.setItems(s3);
					
					
					if(game.getStructureList().get(indexPower).getTenants() < game.getStructureList().get(indexPower).getCapacity()) {
						game.getStructureList().get(indexPower).setTenants(1);
					}
					if(game.getStructureList().get(indexHouse).getTenants() < game.getStructureList().get(indexHouse).getCapacity()) {
						game.getStructureList().get(indexHouse).setTenants(1);
					}
				}
				
				return true;
			}


		});
		
		deleteConfirmButton.addListener(new InputListener() {
			
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				bg.setVisible(false);
				deleteWorkerMenuTable.setVisible(false);
				
				if(game.getPeopleList().size() > 0) {
					
					
					if(game.getPeopleList().get(deletePeopleSelect.getSelectedIndex()).getNameHouse() != null) {
						indexHouse = game.search(game.getPeopleList().get(deletePeopleSelect.getSelectedIndex()).getNameHouse());
						game.getStructureList().get(indexHouse).setTenants(-1);
					}
					
					if(game.getPeopleList().get(deletePeopleSelect.getSelectedIndex()).getNamePower() != null) {
						indexPower = game.search(game.getPeopleList().get(deletePeopleSelect.getSelectedIndex()).getNamePower());
						game.getStructureList().get(indexPower).setTenants(-1);
					}
					
					
					
					
					
					game.getPeopleList().remove(deletePeopleSelect.getSelectedIndex());
					
					//the following block update the people SelectBox
					int index3 = 0;
					
					s3 = new String[game.getPeopleList().size()];
					
					for(People p : game.getPeopleList()) {
						s3[index3] = p.getName();
						index3++;
					}
					
					assignPeopleSelect.setItems(s3);
					deletePeopleSelect.setItems(s3);
					
					
				}
				
				
				return true;
			}
		});
		
		assignConfirmButton.addListener(new InputListener() {
			
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				bg.setVisible(false);
				assignMenuTable.setVisible(false);
				
				
				if(game.getPeopleList().size() > 0) {
					
					if(game.getPeopleList().get(assignPeopleSelect.getSelectedIndex()).getNamePower() != null) {
						
						int indexP = game.search(game.getPeopleList().get(assignPeopleSelect.getSelectedIndex()).getNamePower());
						
						if(game.getStructureList().get(indexP).getTenants() > 0) {
							game.getStructureList().get(indexP).setTenants(-1);
					
						}
						
					}
					
					if(assignPowerSelect.getSelected() != null) {
						//get the index of the selected housing
						indexPower = game.search(assignPowerSelect.getSelected());
						//set the tenants of the selected housing
						game.getStructureList().get(indexPower).setTenants(1);
						//attribute the name of the selected housing to the worker
						game.getPeopleList().get(assignPeopleSelect.getSelectedIndex()).setNamePower(assignPowerSelect.getSelected());
					}
					
					if(game.getPeopleList().get(assignPeopleSelect.getSelectedIndex()).getNameHouse() != null) {
						
						int indexH = game.search(game.getPeopleList().get(assignPeopleSelect.getSelectedIndex()).getNameHouse());
						
						if(game.getStructureList().get(indexH).getTenants() > 0) {
						
							game.getStructureList().get(indexH).setTenants(-1);
						}
						
					}
					
					if(assignHouseSelect.getSelected() != null) {
						//get the index of the selected plant
						indexHouse = game.search(assignHouseSelect.getSelected());
						//set the tenants of the selected plant 
						game.getStructureList().get(indexHouse).setTenants(1);
						//attribute the name of the selected plant to the worker
						game.getPeopleList().get(assignPeopleSelect.getSelectedIndex()).setNameHouse(assignHouseSelect.getSelected());
					}
					
				}
				
				return true;
			}
		});

		
		
		
		
		
		WorkerMenuTable = new Table();
		WorkerMenuTable.setPosition(650, 100);
		WorkerMenuTable.setVisible(false);
		WorkerMenuTable.add(addButton);
		WorkerMenuTable.add(deleteButton);
		WorkerMenuTable.add(assignButton);
		
		addWorkerMenuTable = new Table();
		addWorkerMenuTable.setPosition(670, 380);
		addWorkerMenuTable.setVisible(false);
		addWorkerMenuTable.add(worker);
		addWorkerMenuTable.add(name).width(100);
		addWorkerMenuTable.add(powerplant);
		addWorkerMenuTable.add(addPowerSelect).size(100, 30);
		addWorkerMenuTable.add(housing);
		addWorkerMenuTable.add(addHouseSelect).size(100, 30);
		addWorkerMenuTable.row();
		addWorkerMenuTable.add(addConfirmButton).size(50, 50).colspan(6);
		
		deleteWorkerMenuTable = new Table();
		deleteWorkerMenuTable.setPosition(670, 380);
		deleteWorkerMenuTable.setVisible(false);
		deleteWorkerMenuTable.add(deletePeopleSelect).size(100, 30);
		deleteWorkerMenuTable.add(deleteConfirmButton).size(50,50);
		
		assignMenuTable = new Table();
		assignMenuTable.setPosition(670, 380);
		assignMenuTable.setVisible(false);
		assignMenuTable.add(assignPeopleSelect).size(100, 30);
		assignMenuTable.add(assignPowerSelect).size(100, 30);
		assignMenuTable.add(assignHouseSelect).size(100, 30);
		assignMenuTable.add(assignConfirmButton).size(50, 50);
	}
	
	//****************************************
	//Public methods
	//****************************************
	
	//update the structure SelectBoxs 
	public void updateStructure(Model game) {
		
		int index1 = 0;
		int index2 = 0;
		
			
			
		s1 = new String[game.getnbHousing()];
		s2 = new String[game.getnbPower()];
		
		
				
		
		for(int i = 0; i < game.getStructureList().size() ; i++) {
			
			if(game.getStructureList().get(i).getSuperType() == "Housing") {
				
				s1[index1] = game.getStructureList().get(i).getName();
				
				index1++;
			}else if(game.getStructureList().get(i).getSuperType() == "Power") {
				
				s2[index2] = game.getStructureList().get(i).getName();
				
				index2++;
			}
			
			
		}	
		
		
		
		
		addHouseSelect.setItems(s1);
		addPowerSelect.setItems(s2);
		
		assignHouseSelect.setItems(s1);
		assignPowerSelect.setItems(s2);
		
		
	}
	
	//method to assign null to a worker structure (housing / plant) when deleting the structure at which he was assign
	public void DeletePeopleStructure(String name, Model game) {
		
		for(People p : game.getPeopleList()) {
			
			if(p.getNameHouse() == name) {
				p.setNameHouse(null);
				}
			if(p.getNamePower() == name) {
				p.setNamePower(null);
			}
		}
	}
	
	
	public Table getWorkerMenuTable() {
		return this.WorkerMenuTable;
	}
	
	public Table getAddWorkerMenuTable() {
		return this.addWorkerMenuTable;
	}
	
	public Table getDeleteWorkerMenuTable() {
		return this.deleteWorkerMenuTable;
	}
	
	public Table getAssignWorkerMenuTable() {
		return this.assignMenuTable;
	}

	public Image getBackground() {
		return this.bg;
	}
	
	
	
}
