package com.GUI;

//the following block was to be used to read texts files from the assets folder but i couldn't make it work from the executable (it works fine in the IDE) 

/*
import java.io.BufferedReader;
import com.badlogic.gdx.files.FileHandle;
import java.io.FileReader;
import com.badlogic.gdx.Gdx;

*/


import java.io.IOException;
import java.util.ArrayList;
import com.GameConstant.GUIConstants;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * @brief a 10x10 map composed of tiles
 * @author ang3d
 *
 */
public class Tilemap {
	
	//private FileHandle file; ---> report to line 3
	
	private ArrayList<Tile> base;
	private ArrayList<Tile> objects;
	
	private String[][] map;
	
	
	// constructor
	public Tilemap() {
		
		base = new ArrayList<Tile>();
		objects = new ArrayList<Tile>();
		
		map = new String[10][10];
		
		
		try {
			fillMap(); 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	//****************************************
	// Public methods
	//****************************************
	
	
	
	//method to render the map tile by tile
	public void render(SpriteBatch batch) {
		for(Tile tile : base) {
			
			tile.render(batch);
		}
		for(Tile tile : objects) {
			
			tile.render(batch);

		}
	}
	
	// method called just once to initialize the map
	public void fillMap() throws IOException {
		
		
		// I am aware that the following block is inconvenient ; report to line 3 for details
		String[][] mapAlt = {
				{"g", "w", "g", "g", "g", "g", "g", "g", "g", "g"},
				{"g", "w", "w", "w", "w", "g", "g", "c", "g", "c"},
				{"g", "g", "g", "g", "w", "g", "g", "g", "g", "g"},
				{"g", "g", "g", "g", "w", "g", "g", "g", "g", "g"},
				{"g", "g", "g", "g", "w", "w", "g", "g", "g", "u"},
				{"g", "g", "g", "g", "g", "w", "g", "g", "g", "g"},
				{"g", "g", "g", "g", "g", "w", "g", "c", "g", "g"},
				{"g", "u", "u", "g", "g", "w", "g", "g", "g", "g"},
				{"g", "g", "g", "g", "g", "w", "w", "g", "g", "g"},
				{"g", "g", "g", "g", "g", "g", "w", "g", "g", "g"}	
		};
		
		
		int i = 0;
		for(String[] tab : mapAlt) {
			
			this.map[i] = tab;
			i++;
		}
		
		// --> report to line 3
		/*
		file = Gdx.files.internal("map.txt");
		
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file.path()));
		String s = "";
		int count = 0;
		while((s = bufferedReader.readLine()) != null) {
			
			map[count] = s.split(" ");
			count++;
			System.out.println(count);
		}
		bufferedReader.close();
		*/
		
		// this goes backward to respect in which order the tiles are rendering the one closer to the player is the first one the farthest away the last one 
		for(int row = 9; row >= 0; row--) {
			for(int col = 9; col >= 0; col--) {
				
				float x = (row - col) * 64 / 2F; 
				float y = (col + row) * 32 / 2F;
				
				if(map[row][col].equals("g")) {
					base.add(new Tile(GUIConstants.GRASS_TEXTURE, new Vector2(row, col), new Vector2(x, y)));
				} else if(map[row][col].equals("w")) {
					base.add(new Tile(GUIConstants.WATER_TEXTURE, new Vector2(row, col), new Vector2(x, y)));
				} else if(map[row][col].equals("c")){
					base.add(new Tile(GUIConstants.COAL_TEXTURE, new Vector2(row, col), new Vector2(x, y)));
				} else if(map[row][col].equals("u")) {
					base.add(new Tile(GUIConstants.URANIUM_TEXTURE, new Vector2(row, col), new Vector2(x, y)));
				}
			}
		}
		
	
	}
	
	//this method is used to obtain the number of a tile, 0 for the first 99 for the last
	public int search(int x, int y) {
		
		int index = -1;
		for (Tile t : objects) {
			index += 1;
			if(t.getTilemapPos().x == x && t.getTilemapPos().y == y) {
				return index;
			}
		}
		index = -1;
		
		return index;
	}
	
	//***********************************
	// Getters and Setters 
	//***********************************
	
	public String[][] getMap(){
		return this.map;
	}
	
	public ArrayList<Tile> getObjectsList(){
		return this.objects;
	}
	
	public ArrayList<Tile> getBaseList(){
		return this.base;
	}
	
}
