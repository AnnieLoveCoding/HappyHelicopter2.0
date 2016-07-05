package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.HelicopterDemo;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = HelicopterDemo.WIDTH;
		config.height = HelicopterDemo.HIGHT;
		config.title = HelicopterDemo.TITEL;

		new LwjglApplication(new HelicopterDemo(), config);
	}
}
