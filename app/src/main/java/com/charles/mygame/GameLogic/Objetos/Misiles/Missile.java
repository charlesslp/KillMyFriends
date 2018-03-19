package com.charles.mygame.GameLogic.Objetos.Misiles;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.charles.mygame.GameLogic.Objetos.Animation;
import com.charles.mygame.GameLogic.Objetos.GameObject;

import java.util.Random;

public class Missile extends GameObject {

    private int score;
    private int speed;
    private Random rand = new Random();
    private Animation animation = new Animation();
    private Bitmap spritesheet;

    public Missile(Bitmap res, double x, double y, double w, double h, int s, int numFrames){

        this.x = x;
        this.y = y;
        width = w;
        height = h;
        score = s;

        speed = 15;

        Bitmap[] image = new Bitmap[numFrames];

        spritesheet = res;

        for (int i = 0; i < image.length; i++){
            image[i] = Bitmap.createBitmap(spritesheet, 0, (int) (i*height), (int) width, (int) height);
        }

        animation.setFrames(image);
        animation.setDelay(30);
    }

    public void update(){
        x+=speed;
    }

    public void draw(Canvas canvas){
        try{
            canvas.drawBitmap(animation.getImage(), (int) x, (int) y, null);
        }catch(Exception e) {}
        animation.update();
    }

    //No esta siendo usada;
    @Override
    public double getWidth(){

        //offset slightly for more realistic collision detection
        return width-10;
    }


    public Rect getRectangulo(){
        return new Rect((int) x, (int) y, (int) (x+width), (int) (y+height));
    }

    public Rect getRectangulo2(){
        return new Rect((int) x, (int) y, (int) (x+width), (int) (y+height));
    }
}
