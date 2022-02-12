package com.screen;

import java.util.Collections;

import com.GUI.Tile;
import com.GUI.Tilemap;
import com.GameConstant.GUIConstants;
import com.UI.ASHMenu;
import com.UI.Text;
import com.UI.menu.MenuFrame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.model.Model;
import com.model.structure.housing.Building;
import com.model.structure.housing.House;
import com.model.structure.power.CoalPlant;
import com.model.structure.power.Dam;

import com.model.structure.power.Nuclear;
import com.model.structure.power.Solar;
import com.model.structure.power.WindTurbine;

/**
 * @brief main screen of the game containing 2 cameras a batch and a stage one main menu and different other informations 
 * @author ang3d
 *
 */
public class GameScreen extends ScreenAdapter {
	
	private Stage stage;
	private SpriteBatch batch;
	
	private ASHMenu user;
	
	private Model game;
	
	private Tilemap map;
	
	private Text money;
	private Text satisfaction;
	private Text production;
	private Text people;
	private Text pollution;
	private Text energyUsed;
	private Text wind;
	private Text rain;
	private Text sun;
	
	
	private Text instructions;
	private String message;
	
	private MenuFrame bandeau;
	
	private OrthographicCamera camera;
	
	private Texture texture;
	
	private String type;
	
	private boolean addStructureSelect;
	private boolean infoMenuSelect;
	private boolean deleteSelect;
	
	
	private int tile_x;
	private int tile_y;
	
	private int index;
	
	private float periodS;
	private float periodW;
	
	private float timeS;
	private float timeW;
	
	private String userChoice;
	private String tile;

	
	//constructor
	public GameScreen(SpriteBatch batch) {
		
	
		this.stage = new Stage(new ScreenViewport());
		this.batch = batch;
		
		this.game = new Model();
		
		this.user = new ASHMenu(this, this.game);
		
		this.map = new Tilemap();
		
		this.wind = new Text(0.8f);
		this.rain = new Text(0.8f);
		this.sun = new Text(0.8f);
		this.money = new Text(0.5f);
		this.satisfaction = new Text(0.5f);
		this.production = new Text(0.5f);
		this.people = new Text(0.5f);
		this.pollution = new Text(0.5f);
		this.energyUsed = new Text(0.5f);
		
		
		
		this.instructions = new Text(1);
		
		this.bandeau = new MenuFrame();
		
		this.camera = new OrthographicCamera(1280, 720);

		this.addStructureSelect = false;
		this.infoMenuSelect = false;
		this.deleteSelect = false;
		
		
		this.timeS = 0f;
		this.timeW = 0f;
		this.periodS = 1f;
		this.periodW = 5f;
		
		
		this.index = 1;
		
		this.tile = "";
			
	}
	
	
	//method called once at the initialization of the screen
	@Override
	public void show() {
		
		Gdx.input.setInputProcessor(this.stage);
		
		this.camera.position.x = GUIConstants.CAMERA_INIT_POS_X;
		this.camera.position.y = GUIConstants.CAMERA_INIT_POS_Y;
		
		this.user.addActors(this.stage);
		
		
		// the following block add actors to the stage 
		this.stage.addActor(this.wind);
		this.stage.addActor(this.rain);
		this.stage.addActor(this.sun);
		this.stage.addActor(this.money);
		this.stage.addActor(this.satisfaction);
		this.stage.addActor(this.production);
		this.stage.addActor(this.people);
		this.stage.addActor(this.pollution);
		this.stage.addActor(this.energyUsed);
		this.stage.addActor(this.instructions);
		this.stage.addActor(this.bandeau);
		
		
		

		
	}

	//method called every delta time 
	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(0,  0,  0,  0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);
		
		timeW += Gdx.graphics.getDeltaTime();
		timeS += Gdx.graphics.getDeltaTime();
	    
		if(timeW > periodW){
	        timeW-=periodW;
	        this.game.getWeather().update();
	        this.game.updateMoney();
	    }
	    
	    if(timeS > periodS) {
	    	timeS-=periodS;
	    	
	    	this.game.updateSatis();
	    	this.game.updateProd();
	    	this.game.updateEnergy();
	    	this.game.updatePeople();
	    	this.game.updatePollution();
	    }
	    
	    
		if(addStructureSelect && !deleteSelect) {
			tileselection(texture, type);
		}
			
	    if(!addStructureSelect && !deleteSelect) {
	    	objectSelection();
	    }
	    
	    if(deleteSelect && !addStructureSelect) {
	    	delete();
	    }
	    
		
		cameraInput();
		camera.update();
		
		
		
		//render all the textures at the screen
		batch.begin();
		
		//render the map
		map.render(batch);
		
		
		bandeau.draw(batch, 0, 670);
		
		//render the stage 
		stage.act();
		stage.draw();
		
		//render the weather 
		if(game.getWeather().getWind()) {
			wind.draw(batch, "Wind" , 50, 100);
		}
		if(game.getWeather().getRain()) {
			rain.draw(batch, "Rain" , 50, 50);
		}else if(game.getWeather().getSun()) {
			sun.draw(batch, "Sun" , 50, 50);
		}
		
		//render the informations 
		money.draw(batch, this.game.getMoney() + "$", 100, 700);
		satisfaction.draw(batch, this.game.getSatisfaction() + "%", 230, 700);
		production.draw(batch, this.game.getProduction() + "", 420, 700);
		people.draw(batch, this.game.getPeople() + "", 920, 700);
		pollution.draw(batch, this.game.getPollution() + "", 1100, 700);
		energyUsed.draw(batch, this.game.getEnergyUsed() + "", 1230, 700);
		
		//draw instructions 
		if (addStructureSelect) {
			instructions.draw(batch, message, 450, 600);
		}
		
		if (infoMenuSelect) {
			user.getInformationMenu().getBackground().setVisible(true);
		}else {
			user.getInformationMenu().getBackground().setVisible(false);
		}
		
		if (deleteSelect) {
			instructions.draw(batch, "Click on the object you want to delete !", 300, 600);
		}
		
		
		batch.end();
		
		
	}
	
	//****************************************
	//Public methods
	//****************************************
	
	//method to handle the camera input 
	private void cameraInput() {
		if(Gdx.input.isKeyPressed(Input.Keys.Q)) {
			camera.position.x -= GUIConstants.CAMERA_SPEED;
			
		} else if(Gdx.input.isKeyPressed(Input.Keys.D)) {
			camera.position.x += GUIConstants.CAMERA_SPEED;
			
		} else if(Gdx.input.isKeyPressed(Input.Keys.S)) {
			camera.position.y -= GUIConstants.CAMERA_SPEED;
			
		} else if(Gdx.input.isKeyPressed(Input.Keys.Z)) {
			camera.position.y += GUIConstants.CAMERA_SPEED;
			
		} else if(Gdx.input.isKeyPressed(Input.Keys.R)) {
			camera.position.x = GUIConstants.CAMERA_INIT_POS_X;
			camera.position.y = GUIConstants.CAMERA_INIT_POS_Y;
			camera.zoom = GUIConstants.CAMERA_INIT_ZOOM;
		}
		
		
	}
	
	//method to add a Structure to the map 
	private void tileselection(Texture texture, String type) {
		
		getMouseClick();
		
		if(tile_x <10 && tile_x >= 0 && tile_y < 10 && tile_y >= 0 && user.isPossible(this, this.game, this.tile_x, this.tile_y, this.tile)) {
			
			map.getObjectsList().add(new Tile(texture, new Vector2(tile_x, tile_y), new Vector2((tile_x-tile_y)*64/2F, (tile_x+tile_y)*32/2F)));
			
			switch(userChoice) {
			
			case "Building" : 	
				game.getStructureList().add(new Building(tile_x, tile_y, type, index));
			break;
			
			case "House" :	
				game.getStructureList().add(new House(tile_x, tile_y, type, index));
			break;
			
			case "Nuclear" :	
				game.getStructureList().add(new Nuclear(tile_x, tile_y, type, index));
			break;
			
			case "Coal" :	
				game.getStructureList().add(new CoalPlant(tile_x, tile_y, type, index));
			break;
			
			case "Solar" :	
				game.getStructureList().add(new Solar(tile_x, tile_y, type, index));
			break;
			
			case "Wind" :	
				game.getStructureList().add(new WindTurbine(tile_x, tile_y, type, index));
			break;
			
			case "DamRightOriented" :	
				game.getStructureList().add(new Dam(tile_x, tile_y, type, index));
			break;
			
			case "DamLeftOriented" :	
				game.getStructureList().add(new Dam(tile_x, tile_y, type, index));
			break;
				
			}
			
			game.getMap()[tile_x][tile_y] = 1;
			
			//sort the list of the texture structure to render it properly
			Collections.sort(map.getObjectsList()); 
			
			infoMenuSelect = false;
			addStructureSelect = false;
				
			user.getPowerMenuTable().getPowerPlantMenu().setVisible(false);
			user.getHouseMenu().getHousingMenuTable().setVisible(false);
				
			
			index += 1;
			if(game.getStructureList().get(game.getStructureList().size()-1).getSuperType() == "Housing") {
				game.setnbHousing(1);
			}else if(game.getStructureList().get(game.getStructureList().size()-1).getSuperType() == "Power") {
				game.setnbPower(1);
			}
			
			user.getWorkerMenu().updateStructure(game);
			
			
			
		} else {
			addStructureSelect = true;
		}
			
		
	}
	
	//method to select a structure to delete
	private void delete() {
		
		getMouseClick();
		String bType;
		if(tile_x <10 && tile_x >= 0 && tile_y < 10 && tile_y >= 0 && game.search(tile_x, tile_y) != -1) {
			bType = game.getStructureList().get(game.search(tile_x, tile_y)).getSuperType();
			deleteSelect = false;
			
			
			
			user.getInformationMenu().update(game, tile_x, tile_y);
			
			user.getWorkerMenu().DeletePeopleStructure(game.getStructureList().get(game.search(tile_x, tile_y)).getName(), game);
			
			map.getObjectsList().remove(map.search(tile_x, tile_y));
			game.getStructureList().remove(game.search(tile_x, tile_y));
			
			game.getMap()[tile_x][tile_y] = 0;
			
			if(bType == "Housing") {
				game.setnbHousing(-1);
			}else if(bType == "Power") {
				game.setnbPower(-1);
			}
			
			user.getWorkerMenu().updateStructure(game);
			
		} else {
			
			deleteSelect = true;
		
		}
		
	}
	
	//method to show the information menu when the user click on a structure
	private void objectSelection() {
		
		getMouseClick();
			
		if(tile_x <10 && tile_x >= 0 && tile_y < 10 && tile_y >= 0 && game.search(tile_x, tile_y) != -1) {
			
			infoMenuSelect = true;		
			user.getInformationMenu().update(game, tile_x, tile_y);
			user.getInformationMenu().getInformationMenuTable().setVisible(true);
				
				
		} else {
			
			infoMenuSelect = false;
			user.getInformationMenu().getInformationMenuTable().setVisible(false);
		}
		
	}
	
	//method to capture the position of the mouse in the screen and convert it to map position 
	private void getMouseClick() {
		
		if(Gdx.input.justTouched()) {
			Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(mousePos);
			
			float mapx = (mousePos.x/64 + mousePos.y/32)- 1.3f;
			float mapy = (mousePos.y/32 - mousePos.x/64)+ 0.7f;
			//System.out.println(mousePos.x + " : " + mousePos.y);
			
			tile_x = (int) mapx;
			tile_y = (int) mapy;
		}
	}
	
	
	//********************************************
	// Getters and setters
	//********************************************
	
	
	
	public void setUserChoice(String userChoice) {
		this.userChoice = userChoice;
	}
	
	
	public boolean getSelect() {
		return this.addStructureSelect;
	}
	
	public void setSelect(boolean bool) {
		this.addStructureSelect = bool;
	}
	
	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	

	public void setselect2(boolean b) {
		this.infoMenuSelect = b;
		
	}
	
	public void setselect3(boolean b) {
		this.deleteSelect = b;
		
	}

	public void setBuildType(String string) {
		this.type = string;
		
	}
	
	public Stage getStage() {
		return this.stage;
	}

	public void setTile(String s) {
		this.tile = s;
	}
	
	public Tilemap getTilemap() {
		return this.map;
	}
	
	public void setMessage(String s) {
		this.message = s;
	}
}
