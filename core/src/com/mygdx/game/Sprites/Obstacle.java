package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;



/**
 * Created by hefang on 7/1/16.
 */
public class Obstacle {
    public  static final int OBSTACLE_WIDTH = 52;

    private static final int RANGE = 50;
    private static final int GAP = 100;
    private static final int LOWEST_OPEN = 150;

    private Texture topOb, bottomOb;
    private Vector2 postopOb, posbottomOb;
    private Rectangle boundTop, boundBottom;

    private Random rand;


    public Texture getTopOb() {
        return topOb;
    }

    public Texture getBottomOb() {
        return bottomOb;
    }

    public Vector2 getPostopOb() {
        return postopOb;
    }

    public Vector2 getPosbottomOb() {
        return posbottomOb;
    }

    public Obstacle(float x){
        topOb = new Texture("topObstacle.png");
        bottomOb = new Texture("obstacle.png");
        rand = new Random();

        postopOb = new Vector2(x, rand.nextInt(RANGE) + GAP + LOWEST_OPEN + 60);
        posbottomOb = new Vector2(x, postopOb.y - GAP - bottomOb.getHeight() - 60);

        boundTop = new Rectangle(postopOb.x, postopOb.y, topOb.getWidth(), topOb.getHeight());
        boundBottom = new Rectangle(posbottomOb.x, posbottomOb.y, bottomOb.getWidth(), bottomOb.getHeight());


    }

    public void reposition(float x){
        postopOb.set(x, rand.nextInt(RANGE) + GAP + LOWEST_OPEN);
        posbottomOb = new Vector2(x, postopOb.y - GAP - bottomOb.getHeight());
        boundTop.setPosition(postopOb.x, postopOb.y);
        boundBottom.setPosition(posbottomOb.x, posbottomOb.y);
    }

    public boolean collision(Rectangle helicopter){
        return helicopter.overlaps(boundBottom) || helicopter.overlaps(boundTop);
    }

    public void dispose(){
        topOb.dispose();
        bottomOb.dispose();
    }
}
