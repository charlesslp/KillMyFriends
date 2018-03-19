package com.charles.mygame.GameLogic.Objetos;

import android.graphics.Rect;


public abstract class GameObject {

    protected double x, y, width, height;

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public double getWidth(){
        return width;
    }

    public double getHeight(){
        return height;
    }

    public abstract Rect getRectangulo();

    public abstract Rect getRectangulo2();

}
