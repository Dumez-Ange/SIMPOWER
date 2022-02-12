package com.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
/**
 * @brief class text that extends actor to draw a text on the stage and follow the screen orthographic camera 
 * @author ang3d
 *
 */
public class Text extends Actor {

	BitmapFont font;
	//constructor
	public Text(float scale) {
		font = new BitmapFont(Gdx.files.internal("myfont.fnt"), Gdx.files.internal("myfont.png"), false);
		font.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		font.getData().setScale(scale);
		
	}
	
	//method to draw the text on the stage 
	public void draw(SpriteBatch batch, String string1, int coordx, int coordy) {
		font.draw(batch , string1, coordx, coordy);
	}
	
}
