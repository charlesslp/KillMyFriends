package com.charles.mygame.GameLogic.Objetos.Misiles;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.charles.mygame.GameLogic.Objetos.Animation;
import com.charles.mygame.GameLogic.Objetos.GameObject;

import java.util.Random;

public class MissileClever extends GameObject {

    private int speed;
    private Animation animation = new Animation();

    public MissileClever(Bitmap res, double x, double y, double w, double h, int nivel, int numFrames){

        this.x = x;
        this.y = y;
        width = w;
        height = h;

        if(nivel > 5) {
            if(nivel >= 10)
                speed = 15 + 7;
            else {
                nivel -= 3;
                speed = 15 + nivel;
            }
        }
        else
            speed = 15;

        Bitmap[] image = new Bitmap[numFrames];

        for (int i = 0; i < image.length; i++){
            image[i] = Bitmap.createBitmap(res, 0, (int) (i*height), (int) width, (int) height);
        }

        animation.setFrames(image);
        animation.setDelay(30);
    }

    public void update(double posPlayer){
        x-=speed;

        if(posPlayer > y)
            y += speed / 5;
        else {
            if(posPlayer < y)
                y -= speed / 5;
        }

    }

    public void draw(Canvas canvas){
        try{
            canvas.drawBitmap(animation.getImage(), (int) x, (int) y, null);
        }catch(Exception e) {System.out.println(e.getMessage());}
        animation.update();
    }

    public Rect getRectangulo(){
        return new Rect((int) x, (int) y, (int) (x+width), (int) (y+height));
    }

    public Rect getRectangulo2(){
        return new Rect((int) x, (int) y, (int) (x+width), (int) (y+height));
    }
}
