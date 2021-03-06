package com.simpower.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.simpower.SimPower;


public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "SIM POWER";
		config.width = 1280; 
		config.height = 720;
		config.fullscreen = false;
		config.resizable = false;
		new LwjglApplication(new SimPower(), config);
	}
}
