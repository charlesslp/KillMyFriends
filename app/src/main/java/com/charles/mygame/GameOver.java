package com.charles.mygame;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;


public class GameOver extends ActionBarActivity {

    private int puntuacion, record;

    private int[] puntos = {
            R.drawable.zz0,R.drawable.zz1,R.drawable.zz2,R.drawable.zz3,R.drawable.zz4,
            R.drawable.zz5,R.drawable.zz6,R.drawable.zz7,R.drawable.zz8, R.drawable.zz9
    };
    private ImageView[] imagenesPuntos = {
            (ImageView) findViewById(R.id.imageView2),(ImageView) findViewById(R.id.imageView3),(ImageView) findViewById(R.id.imageView4),
            (ImageView) findViewById(R.id.imageView5),(ImageView) findViewById(R.id.imageView6),(ImageView) findViewById(R.id.imageView7),
            (ImageView) findViewById(R.id.imageView8),(ImageView) findViewById(R.id.imageView9),(ImageView) findViewById(R.id.imageView10),
            (ImageView) findViewById(R.id.imageView11)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_game_over);

        getSupportActionBar().hide();

        /*
        Bundle param = getIntent().getExtras();
        if( param!= null) {
            puntuacion = param.getInt("puntuacion");
            //record = param.getInt("record");
        }

        record = 12345;

        drawScore();
        */
    }


    public void drawScore() {

        int aux = record, aux2;

        for(int i = 10; i > 0; i--) {

            if(aux > 0) {
                aux2 = aux % 10;
                aux = aux / 10;
            }
            else
                aux2 = 0;

            if(i == 5)
                aux = puntuacion;


            switch (aux2) {
                case 0:
                    imagenesPuntos[i].setImageResource(puntos[0]);
                    break;
                case 1:
                    imagenesPuntos[i].setImageResource(puntos[1]);
                    break;
                case 2:
                    imagenesPuntos[i].setImageResource(puntos[2]);
                    break;
                case 3:
                    imagenesPuntos[i].setImageResource(puntos[3]);
                    break;
                case 4:
                    imagenesPuntos[i].setImageResource(puntos[4]);
                    break;
                case 5:
                    imagenesPuntos[i].setImageResource(puntos[5]);
                    break;
                case 6:
                    imagenesPuntos[i].setImageResource(puntos[6]);
                    break;
                case 7:
                    imagenesPuntos[i].setImageResource(puntos[7]);
                    break;
                case 8:
                    imagenesPuntos[i].setImageResource(puntos[8]);
                    break;
                case 9:
                    imagenesPuntos[i].setImageResource(puntos[9]);
                    break;
            }
        }

    }
}
