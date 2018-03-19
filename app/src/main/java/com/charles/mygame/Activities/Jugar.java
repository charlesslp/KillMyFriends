package com.charles.mygame.Activities;

import android.content.pm.ActivityInfo;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;

import com.charles.mygame.GameLogic.GamePanel;
import com.charles.mygame.R;


public class Jugar extends ActionBarActivity {

    private int jugador;
    private GamePanel gamePanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


        Display display = getWindowManager().getDefaultDisplay();
        int width = display.getWidth();  // deprecated
        int height = display.getHeight();  // deprecated


        Bundle param = getIntent().getExtras();
        if( param!= null) {
            jugador = param.getInt("quien_juega");
        }

        switch (jugador){
            case 0:{
                gamePanel = new GamePanel(Jugar.this, BitmapFactory.decodeResource(getResources(), R.drawable.b_player_gamero), 85, 5, width, height, jugador);
                break;
            }
            case 1: {
                gamePanel = new GamePanel(Jugar.this, BitmapFactory.decodeResource(getResources(), R.drawable.b_player_gulyk), 100, -10, width, height, jugador);
                break;
            }
            case 2: {
                gamePanel = new GamePanel(Jugar.this, BitmapFactory.decodeResource(getResources(), R.drawable.b_player_montero), 76, 5, width, height, jugador);
                break;
            }
            case 3: {
                gamePanel = new GamePanel(Jugar.this, BitmapFactory.decodeResource(getResources(), R.drawable.b_player_chavi), 72, 5, width, height, jugador);

                break;
            }
            case 4: {
                gamePanel = new GamePanel(Jugar.this, BitmapFactory.decodeResource(getResources(), R.drawable.b_player_alvaro), 100, 10, width, height, jugador);

                break;
            }
        }

        setContentView(gamePanel);
        getSupportActionBar().hide();




    }

}
