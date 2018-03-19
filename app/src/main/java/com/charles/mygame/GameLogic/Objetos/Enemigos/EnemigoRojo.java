package com.charles.mygame.GameLogic.Objetos.Enemigos;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.charles.mygame.GameLogic.Objetos.Animation;
import com.charles.mygame.GameLogic.Objetos.GameObject;

public class EnemigoRojo extends GameObject {


    private int speed;
    private boolean bocaAbierta, lanzar;
    private Animation animation = new Animation();
    private double posBoca;

    private int nivel;

    private long enemigoStartTime, enemigosElapsed;

    public EnemigoRojo(Bitmap res, double x, double y, double w, double h, double posBoca, int nivel, int numFrames){

        this.x = x;
        this.y = y;
        width = w;
        height = h;
        this.posBoca = posBoca;
        this.nivel = nivel;

        Bitmap[] image = new Bitmap[numFrames];


        for (int i = 0; i < image.length; i++) {
            image[i] = Bitmap.createBitmap(res, (int) (i * width), 0, (int) width, (int) height);
        }

        animation.setFrames(image);
        animation.setDelay(30);

        speed = 9;

        enemigoStartTime = System.nanoTime();

    }

    public void update(){

        x-=speed;


        enemigosElapsed = (System.nanoTime() - enemigoStartTime) / 1000000;

        if(nivel > 4){
            if(nivel >= 10){

                enemigosElapsed += 400;
            }
            else
                enemigosElapsed += (nivel-5)*100;
        }

        if(enemigosElapsed > 750){
            if(bocaAbierta)
                bocaAbierta = false;
            else {
                bocaAbierta = true;
                lanzar = true;
            }

            enemigoStartTime = System.nanoTime();
        }

    }

    public void draw(Canvas canvas){
        try{
                if (bocaAbierta)
                    canvas.drawBitmap(animation.getEnemigoImage1(), (int) x, (int) y, null);
                else
                    canvas.drawBitmap(animation.getEnemigoImage2(), (int) x, (int) y, null);

        }catch(Exception e) {}
    }

    public boolean lanzar(){

        if(lanzar) {
            lanzar = false;
            return true;
        }
        else {
            return false;
        }

    }

    public void esperar(){
        enemigoStartTime = System.nanoTime() - enemigosElapsed*1000000;
    }

    public Rect getRectangulo(){
        return new Rect((int) x + 60, (int) (y + 10), (int) (x+width - 60), (int) (y+height-20));
    }

    public Rect getRectangulo2(){
        return new Rect((int) x, (int) y, (int) (x+width), (int) (y+height));
    }

    public double getPosBoca(){
        return posBoca;
    }
}
