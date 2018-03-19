package com.charles.mygame.GameLogic.Objetos;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.charles.mygame.GameLogic.GamePanel;

public class Player extends GameObject {

    private int score, vida;
    private boolean up, playing, disparo, disparado;
    private Animation animation = new Animation();
    private long startTime, startDisparo;
    private double posMax;
    private int speed;

    public Player(Bitmap res, double posMax, double w, double h, int numFrames){
        x = w*1.3;
        y = GamePanel.HEIGHT/2;
        score = 0;
        vida = 10;
        height = h;
        width = w;

        speed = 9;

        this.posMax = posMax;

        Bitmap[] image = new Bitmap[numFrames];

        for(int i = 0; i < image.length; i++){
            image[i] = Bitmap.createBitmap(res, (int) (i*width), 0, (int) width, (int) height);

        }

        animation.setFrames(image);
        animation.setDelay(0);
    }

    public void setStartTime(long nanoTime){
        startTime = nanoTime;
    }

    public  void setUp(boolean b){
        up = b;
    }

    public void setDisparo(boolean b){disparo = b;}

    public void update(){

        long elapsed = (System.nanoTime() - startTime) / 1000000;


        if(elapsed > 1000){
            score++;
            startTime = System.nanoTime();
        }



        if (up) {
            if (y > 0)
                y -= speed;
        }
        else {
            if (y < GamePanel.HEIGHT - (getHeight() - posMax))
                y += speed;
        }


    }

    public void draw(Canvas canvas){
        if(disparo) {
            startDisparo = System.nanoTime();
            canvas.drawBitmap(animation.getPlayerImage2(), (int) x, (int) y, null);
            disparado = true;
        }
        else {
            long disparoElapsed = (System.nanoTime() - startDisparo) / 1000000;

            if(disparoElapsed < 400 && disparado)
                canvas.drawBitmap(animation.getPlayerImage2(), (int) x, (int) y, null);
            else {
                canvas.drawBitmap(animation.getPlayerImage1(), (int) x, (int) y, null);
                startDisparo = System.nanoTime();
                disparado = false;
            }
        }
    }

    public void drawScore(Canvas canvas, Bitmap[] puntuacion, int posX, int posY, int desplazamiento, int record) {

        int aux, aux2;

        if(record == -1)
            aux = score;
        else
            aux = record;

        for(int i = 0; i < 5; i++) {

            if(aux > 0) {
                aux2 = aux % 10;
                aux = aux / 10;
            }
            else
                aux2 = 0;



            switch (aux2) {
                case 0:
                    canvas.drawBitmap(puntuacion[0], posX - i*desplazamiento, posY, null);
                    break;
                case 1:
                    canvas.drawBitmap(puntuacion[1], posX - i*desplazamiento, posY, null);
                    break;
                case 2:
                    canvas.drawBitmap(puntuacion[2], posX - i*desplazamiento, posY, null);
                    break;
                case 3:
                    canvas.drawBitmap(puntuacion[3], posX - i*desplazamiento, posY, null);
                    break;
                case 4:
                    canvas.drawBitmap(puntuacion[4], posX - i*desplazamiento, posY, null);
                    break;
                case 5:
                    canvas.drawBitmap(puntuacion[5], posX - i*desplazamiento, posY, null);
                    break;
                case 6:
                    canvas.drawBitmap(puntuacion[6], posX - i*desplazamiento, posY, null);
                    break;
                case 7:
                    canvas.drawBitmap(puntuacion[7], posX - i*desplazamiento, posY, null);
                    break;
                case 8:
                    canvas.drawBitmap(puntuacion[8], posX - i*desplazamiento, posY, null);
                    break;
                case 9:
                    canvas.drawBitmap(puntuacion[9], posX - i*desplazamiento, posY, null);
                    break;
            }
        }

    }

    public void drawVida(Canvas canvas, Bitmap[] fotoVida) {


        switch (vida) {
            case 10:
                canvas.drawBitmap(fotoVida[0], 10, 10, null);
                break;
            case 9:
                canvas.drawBitmap(fotoVida[1], 10, 10, null);
                break;
            case 8:
                canvas.drawBitmap(fotoVida[2], 10, 10, null);
                break;
            case 7:
                canvas.drawBitmap(fotoVida[3], 10, 10, null);
                break;
            case 6:
                canvas.drawBitmap(fotoVida[4], 10, 10, null);
                break;
            case 5:
                canvas.drawBitmap(fotoVida[5], 10, 10, null);
                break;
            case 4:
                canvas.drawBitmap(fotoVida[6], 10, 10, null);
                break;
            case 3:
                canvas.drawBitmap(fotoVida[7], 10, 10, null);
                break;
            case 2:
                canvas.drawBitmap(fotoVida[8], 10, 10, null);
                break;
            case 1:
                canvas.drawBitmap(fotoVida[9], 10, 10, null);
                break;
            case 0:
                canvas.drawBitmap(fotoVida[10], 10, 10, null);
                break;
        }



    }


    public int getScore(){return score;}
    public int getVida(){return vida;}

    public void sumaScore(int x){
        score += x;
    }


    public void sumaVida(){
        vida++;
    }
    public void restaVida(){
        vida--;
    }

    public boolean getPlaying(){return playing;}
    public void setPlaying(boolean b){playing = b;}



    public Rect getRectangulo(){
        return new Rect((int) x + 60, (int) (y + 30), (int) (x+width - 60), (int) (y+height-20));
    }

    public Rect getRectangulo2(){
        return new Rect((int) x+50, (int) y+70, (int) (x+width-60), (int) (y+height-60));
    }

}
