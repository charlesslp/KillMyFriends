package com.charles.mygame.GameLogic.Objetos;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;


public class Explosion extends GameObject{


    private Animation animation = new Animation();
    private boolean end;

    public Explosion(Bitmap res, double x, double y, double w, double h, int numFrames){

        this.x = x;
        this.y = y;
        width = w;
        height = h;

        end = false;

        Bitmap[] image = new Bitmap[numFrames];

        int cont = 0;


        for (int i = 6; i >= 0; i--) {
            image[cont] = Bitmap.createBitmap(res, (int) (i*width), (int) (4*height), (int) width, (int) height);
            cont++;
        }


        for (int i = 3; i >= 0; i--){
            for (int j = 9; j >= 0; j--) {
                image[cont] = Bitmap.createBitmap(res, (int) (j*width), (int) (i*height), (int) width, (int) height);
                cont++;
            }
        }


        animation.setFrames(image);
        animation.setDelay(5);
    }

    public void update(){
    }

    public void draw(Canvas canvas){
        try{
            canvas.drawBitmap(animation.getImage(), (int) x, (int) y, null);
        }catch(Exception e) { System.out.println(e.getMessage());}

        if(!animation.getPlayedOnce())
            animation.update();
        else
            end = true;
    }

    public Rect getRectangulo(){
        return new Rect((int) x, (int) y, (int) (x+width), (int) (y+height));
    }

    public Rect getRectangulo2(){
        return new Rect((int) x, (int) y, (int) (x+width), (int) (y+height));
    }

    public boolean isEnd(){
        return end;
    }

}
