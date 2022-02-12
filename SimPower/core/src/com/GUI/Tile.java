package com.GUI;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;


/**
 * @brief A tile is basically what the map is made of (tiles next to each others) 
 * @author ang3d
 *
 */

public class Tile implements Comparable<Tile>{
	
	private Texture texture;
	private Vector2 tilemapPos;
	private Vector2 worldPos;
	
	// Constructor
	public Tile(Texture texture, Vector2 tilemapPos, Vector2 worldPos) {
		this.texture = texture;
		this.tilemapPos = tilemapPos;
		this.worldPos = worldPos;
		
	}
	
	// ********************************************
	// Public methods
	// ********************************************
	public void render(SpriteBatch batch) {
		
		batch.draw(texture,  worldPos.x, worldPos.y);
		
	}
	
	//method for comparing tiles with each others
	@Override
	public int compareTo(Tile t) {

		return (int) ((t.tilemapPos.x*10 + t.tilemapPos.y) - (this.tilemapPos.x*10 + this.tilemapPos.y)) ;
		
	}
	
	//*******************************************
	// Getters and setters
	//*******************************************
	
	public Texture getTexture() {
		return this.texture;
	}
	
	public Vector2 getTilemapPos() {
		return this.tilemapPos;
	}
	
	public Vector2 getWorldPos() {
		return this.worldPos;
	}
	
	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	
	public void setTilemapPos(int x, int y) {
		this.tilemapPos.x = x; 
		this.tilemapPos.y = y;
	}
	
	public void setTilemapPosX(int x) {
		this.tilemapPos.x = x;
	}
	
	public void setTilemapPosY(int y) {
		this.tilemapPos.y = y;
	}
	
	public void setWorldPos(float x, float y) {
		this.worldPos.x = x;
		this.worldPos.y = y;
	}
	
	public void setWorldPosX(float x) {
		this.worldPos.x = x;
	}
	
	public void setWorldPosY(float y) {
		this.worldPos.y = y;
	}
}
