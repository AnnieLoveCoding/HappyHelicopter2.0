package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.HelicopterDemo;
import com.mygdx.game.Sprites.Helicopter;
import com.mygdx.game.Sprites.Obstacle;

import java.util.ArrayList;

/**
 * Created by hefang on 6/29/16.
 */
public class PlayState extends State {
    private static final int OBSTACLE_SPACING = 125;
    private static final int OBSTACLE_COUNT = 4;
    private static final int GROUND_OFFSET = -70;

    private Helicopter helicopter;
    private Texture bg;
    private Texture ground;
    private Vector2 groundPos1, groundPos2;
    private Music explosion;

    private ArrayList<Obstacle> obstacles;

    protected PlayState(GameStateManager manager) {
        super(manager);
        helicopter = new Helicopter(50, 250);
        bg = new Texture("bg.png");
        ground = new Texture("ground.png");
        explosion = Gdx.audio.newMusic(Gdx.files.internal("explosion.mp3"));
        explosion.setVolume(0.1f);


        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth/2, GROUND_OFFSET);
        groundPos2 = new Vector2(cam.position.x - cam.viewportWidth/2 + ground.getWidth(), GROUND_OFFSET);


        obstacles = new ArrayList<Obstacle>();

        //zoom in
        cam.setToOrtho(false, HelicopterDemo.WIDTH/2, HelicopterDemo.HIGHT/2);

        for(int i = 1; i <= OBSTACLE_COUNT; i++){
            obstacles.add(new Obstacle(i*(OBSTACLE_SPACING + Obstacle.OBSTACLE_WIDTH)));
        }
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.isTouched()){
            helicopter.jump();
        }

    }

    @Override
    public void update(float dt) {
        handleInput();
        updateGround();

        helicopter.update(dt);
        //view point in the game should be based on the position of the helicopter, 80 offset
        cam.position.x = helicopter.getPosition().x + 80;


        for(Obstacle obstacle : obstacles){
            //if obstacle is out of the screen
            if(cam.position.x - (cam.viewportWidth/2) > obstacle.getPostopOb().x +
                    obstacle.getTopOb().getWidth()){
                obstacle.reposition(obstacle.getPostopOb().x + ((Obstacle.OBSTACLE_WIDTH + OBSTACLE_SPACING)*OBSTACLE_COUNT));
            }

            if(obstacle.collision(helicopter.getBound())){
                explosion.play();
                    manager.set(new PlayState(manager));

            }
        }
        if(helicopter.getPosition().y <= ground.getHeight() + GROUND_OFFSET ){
            explosion.play();
                manager.set(new PlayState(manager));

        }

        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, cam.position.x - (cam.viewportWidth / 2), 0);
        sb.draw(helicopter.getHelicopter(), helicopter.getPosition().x, helicopter.getPosition().y);
        for(Obstacle obstacle : obstacles) {
            sb.draw(obstacle.getTopOb(), obstacle.getPostopOb().x, obstacle.getPostopOb().y);
            sb.draw(obstacle.getBottomOb(), obstacle.getPosbottomOb().x, obstacle.getPosbottomOb().y);
        }

        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);
        sb.end();

    }

    @Override
    public void dispose() {
        bg.dispose();
        helicopter.dispose();
        ground.dispose();
        //explosion.dispose();

        for(Obstacle o : obstacles){
            o.dispose();
        }
        System.out.println("PlayState disposed");

    }

    private void updateGround(){
        if(cam.position.x - (cam.viewportWidth / 2) > groundPos1.x + ground.getWidth()){
            groundPos1.add(ground.getWidth()*2, 0);
        }

        if(cam.position.x - (cam.viewportWidth / 2) > groundPos2.x + ground.getWidth()){
            groundPos2.add(ground.getWidth()*2, 0);
        }

    }
}
