package com.charles.mygame.GameLogic.Objetos;

import android.graphics.Bitmap;


public class Animation {

    private Bitmap[] frames;
    private int currentFrame;
    private long startTime, delay;
    private boolean playedOnce;

    public  void setFrames(Bitmap[] frames){

        this.frames = frames;

        currentFrame = 0;

        startTime = System.nanoTime();

        playedOnce = false;

    }

    public void setDelay(long d){delay = d;}
    public void setFrame(int i){currentFrame = i;}


    public void update(){

        long elapsed = (System.nanoTime() - startTime) / 1000000;

        if(elapsed > delay){
            currentFrame++;
            startTime = System.nanoTime();
        }

        if(currentFrame == frames.length){
            currentFrame = 0;
            playedOnce = true;
        }

    }

    public Bitmap getImage(){
        return frames[currentFrame];
    }

    public Bitmap getPlayerImage1(){return frames[0];} // USAR SOLO PARA EL PLAYER

    public Bitmap getPlayerImage2(){return frames[1];} // USAR SOLO PARA EL PLAYER

    public Bitmap getEnemigoImage1(){return frames[0];} // USAR SOLO PARA EL ENEMIGO Y ENEMIGO_ESPECIAL

    public Bitmap getEnemigoImage2(){return frames[1];} // USAR SOLO PARA EL ENEMIGO Y ENEMIGO_ESPECIAL

    public int getFrame(){
        return currentFrame;
    }
    public boolean getPlayedOnce(){
        return playedOnce;
    }



}
