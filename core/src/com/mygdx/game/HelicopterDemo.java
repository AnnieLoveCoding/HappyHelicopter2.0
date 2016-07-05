package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.States.GameStateManager;
import com.mygdx.game.States.MenuState;

public class HelicopterDemo extends ApplicationAdapter {
	public static final int WIDTH = 480;
	public static final int HIGHT = 800;
	public static final String TITEL = "HappyHelicopter";
	private GameStateManager GSM;
	SpriteBatch batch;
	Texture img;

	private Music music;


	@Override
	public void create () {
		batch = new SpriteBatch();
		GSM = new GameStateManager();
		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		music.setLooping(true);
		music.setVolume(0.1f);
		music.play();


		Gdx.gl.glClearColor(1, 0, 0, 1);
		GSM.push(new MenuState(GSM));
	}

	@Override
	public void render () {
		//white the screen, redraw everything
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		GSM.update(Gdx.graphics.getDeltaTime());
		GSM.render(batch);

	}

	@Override
	public void dispose (){
		batch.dispose();
		img.dispose();
		music.dispose();
	}
}
