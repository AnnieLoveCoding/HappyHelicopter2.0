package com.mygdx.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by hefang on 6/29/16.
 */
public class Helicopter {
    public static final int GRAVITY = -15;

    private static final int MOVEMENT = 100;

    private Vector3 position;
    private Vector3 velocity;

   // private Texture helicopter;
    private Rectangle bound;
    private Animation helicopterAnimation;
    Texture texture;
    private Sound flap;


    public Helicopter(int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(x, y, 0);
      //  helicopter = new Texture("helicopter.png");
        texture = new Texture("helicopteranimation.png");
        helicopterAnimation = new Animation(new TextureRegion(texture), 3, 0.5f);

        bound = new Rectangle(x, y, texture.getWidth()/3, texture.getHeight()/3);
        flap = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));

    }

    public void update(float dt){
       /* if(position.y > 0) {
            velocity.add(0, GRAVITY, 0);
        }*/
        helicopterAnimation.update(dt);

        velocity.add(0, GRAVITY, 0);
        velocity.scl(dt);

        position.add(MOVEMENT*dt, velocity.y, 0);

        if(position.y < 0){
            position.y = 0;
        }

        velocity.scl(1/dt);
        bound.setPosition(position.x, position.y);
    }

    public Rectangle getBound(){
        return bound;
    }

    public TextureRegion getHelicopter(){
        return helicopterAnimation.getFrame();
    }

  /*  public Texture getHelicopter() {
        return helicopter;
    }
*/
    public Vector3 getPosition() {
        return position;
    }

    public void jump(){
        velocity.y = 250;
        flap.play(0.5f);
    }

    public void dispose(){
        texture.dispose();
        flap.dispose();
    }
}
