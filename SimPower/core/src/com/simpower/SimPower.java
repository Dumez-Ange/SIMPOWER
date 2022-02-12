package com.simpower;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.screen.GameScreen;
import com.screen.MainMenuScreen;

/**
 * @brief 
 * @author ang3d
 *
 */
public class SimPower extends Game{
	 
	private SpriteBatch batch;
	public GameScreen screen;
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		screen = new GameScreen(batch);
		setScreen(new MainMenuScreen(batch, this));
	}
	
	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		
	}
}
