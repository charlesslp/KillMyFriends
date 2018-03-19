package com.charles.mygame.GameLogic.Objetos.Enemigos;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.charles.mygame.GameLogic.Objetos.Animation;
import com.charles.mygame.GameLogic.Objetos.GameObject;

import java.util.Random;

public class Enemigo extends GameObject {



    private int speed;
    private Animation animation = new Animation();
    private boolean esCorazon;

    public Enemigo(Bitmap res, double x, double y, double w, double h, int nivel, int numFrames){

        this.x = x;
        this.y = y;
        width = w;
        height = h;

        this.esCorazon = false;

        Bitmap[] image = new Bitmap[numFrames];

        for (int i = 0; i < image.length; i++) {
            image[i] = Bitmap.createBitmap(res, (int) (i * width), 0, (int) width, (int) height);
        }

        animation.setFrames(image);
        animation.setDelay(30);

        Random rand = new Random();
        int randomValue = rand.nextInt(10);

        if(nivel > 3){
            if(nivel >= 12)
                nivel = 8;
            else
                nivel = nivel - 3;
        }
        else
            nivel = 0;

        speed = 8 + randomValue + nivel*2;


    }

    public void update(){
        x-=speed;
    }

    public void draw(Canvas canvas){
        try{
            canvas.drawBitmap(animation.getEnemigoImage1(), (int) x, (int) y, null);
        }catch(Exception e) {System.out.println(e.getMessage());}
    }

    public Rect getRectangulo(){
        return new Rect((int) x + 60, (int) (y + 10), (int) (x+width - 60), (int) (y+height-20));
    }


    public Rect getRectangulo2(){
        return new Rect((int) x, (int) y, (int) (x+width), (int) (y+height));
    }

    public void setEsCorazon(){
        esCorazon = true;
        speed = 10;
    }

    public boolean getEsCorazon(){
        return esCorazon;
    }

}
