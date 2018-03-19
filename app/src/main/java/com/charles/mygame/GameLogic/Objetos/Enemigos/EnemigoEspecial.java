package com.charles.mygame.GameLogic.Objetos.Enemigos;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.charles.mygame.GameLogic.GamePanel;
import com.charles.mygame.GameLogic.Objetos.Animation;
import com.charles.mygame.GameLogic.Objetos.GameObject;


public class EnemigoEspecial extends GameObject {


    private int speed;
    private boolean bocaAbierta;
    private Animation animation = new Animation();

    private long enemigoStartTime;

    private boolean subir;

    public EnemigoEspecial(Bitmap res, double x, double y, double w, double h, int nivel, int numFrames){

        this.x = x;
        this.y = y;
        width = w;
        height = h;

        Bitmap[] image = new Bitmap[numFrames];


        for (int i = 0; i < image.length; i++) {
            image[i] = Bitmap.createBitmap(res, (int) (i * width), 0, (int) width, (int) height);
        }

        animation.setFrames(image);
        animation.setDelay(30);

        if(nivel > 3){
            if(nivel >= 9)
                nivel = 5;
            else
                nivel = nivel - 3;
        }
        else
            nivel = 0;

        speed = 8 + nivel;
        subir = true;

        enemigoStartTime = System.nanoTime();

    }

    public void update(){

        x-=speed;

        if(x >= GamePanel.PLAYER_WIDTH*1.3 + 20) {
            if (subir) {
                y -= speed * 2;
                if (y <= 0) {
                    subir = false;
                }
            } else {
                y += speed * 2;
                if (y > GamePanel.HEIGHT - height) {
                    subir = true;
                }
            }
        }


        long enemigosElapsed = (System.nanoTime() - enemigoStartTime) / 1000000;

        if(enemigosElapsed > 250){
            if(bocaAbierta)
                bocaAbierta = false;
            else
                bocaAbierta = true;

            enemigoStartTime = System.nanoTime();
        }

    }

    public void draw(Canvas canvas){
        try{
            if (bocaAbierta)
                canvas.drawBitmap(animation.getEnemigoImage1(), (int) x, (int) y, null);
            else
                canvas.drawBitmap(animation.getEnemigoImage2(), (int) x, (int) y, null);

        }catch(Exception e) {System.out.println(e.getMessage());}
    }

    public Rect getRectangulo(){
        return new Rect((int) x + 60, (int) (y + 10), (int) (x+width - 80), (int) (y+height-30));
    }

    public Rect getRectangulo2(){
        return new Rect((int) x, (int) y, (int) (x+width), (int) (y+height));
    }

}