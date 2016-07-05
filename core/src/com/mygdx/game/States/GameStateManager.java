package com.mygdx.game.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by hefang on 6/28/16.
 */
public class GameStateManager {
    private Stack<State> states;

    public GameStateManager(){
        states = new Stack<State>();
    }

    public void push(State s){
        states.push(s);
    }

    public void pop(State s){
        states.pop();
    }


    public void set(State s){
        //dispose right away avoid memory leak
        states.pop().dispose();


        states.push(s);
    }

    public void update(float dt){
        states.peek().update(dt);
    }

    public void render(SpriteBatch sb){
        states.peek().render(sb);

    }


}
