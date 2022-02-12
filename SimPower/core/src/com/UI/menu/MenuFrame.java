package com.UI.menu;


import com.GameConstant.UIConstants;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * @brief class frame that extends actor to display a texture onto the stage 
 * @author ang3d
 *
 */
public class MenuFrame extends Actor{
	
	private Texture frame;
	
	//constructor
	public MenuFrame() {
		
		frame = UIConstants.TASK_BAR_MENU_TEXTURE;
		
	}
	
	//method to render the texture
	public void draw(SpriteBatch batch, int coordx, int coordy) {
		batch.draw(frame, coordx, coordy);
	}
}
