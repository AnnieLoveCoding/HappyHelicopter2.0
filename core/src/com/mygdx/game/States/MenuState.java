package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.HelicopterDemo;

/**
 * Created by hefang on 6/29/16.
 */
public class MenuState extends State{
    private Texture bg;
    private Texture playbtn;

    public MenuState(GameStateManager manager) {
        super(manager);
        bg = new Texture("bg.png");
        playbtn = new Texture("playbtn.png");
        cam.setToOrtho(false, HelicopterDemo.WIDTH/2, HelicopterDemo.HIGHT/2);

    }

    @Override
    public void handleInput() {
        if(Gdx.input.isTouched()){
            manager.set(new PlayState(manager));
            //this state will not be used ant more
            //free memory, avoid memory leak
            dispose();
        }

    }

    @Override
    public void update(float dt) {
        handleInput();

    }

    @Override
    public void render(SpriteBatch sb) {
        //open box
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, 0, 0);
        sb.draw(playbtn, cam.position.x - playbtn.getWidth()/2, cam.position.y);
        //sb.draw(playbtn, 0, 0, HelicopterDemo.WIDTH, HelicopterDemo.HIGHT);
        sb.end();
    }

    //call this when transition state
    @Override
    public void dispose() {
        bg.dispose();
        playbtn.dispose();
        System.out.println("MenuState disposed");
    }
}
