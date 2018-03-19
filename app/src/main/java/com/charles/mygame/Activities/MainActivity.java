package com.charles.mygame.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.charles.mygame.R;


public class MainActivity extends ActionBarActivity {

    private ImageButton gamero, gulyk, montero, chavi, alvaro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //turn tittle off
        // requestWindowFeature(Window.FEATURE_NO_TITLE);
        // no funciona.

        //fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();


        gamero = (ImageButton) findViewById(R.id.imageButton);
        gamero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, Jugar.class);
                in.putExtra("quien_juega", 0);
                startActivity(in);
            }
        });


        gulyk = (ImageButton) findViewById(R.id.imageButton2);
        gulyk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(MainActivity.this, Jugar.class);
                in.putExtra("quien_juega", 1);
                startActivity(in);

                /*
                Intent in = new Intent(MainActivity.this, Camara.class);
                startActivity(in);
                */
            }
        });

        montero = (ImageButton) findViewById(R.id.imageButton3);
        montero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, Jugar.class);
                in.putExtra("quien_juega", 2);
                startActivity(in);
            }
        });

        chavi = (ImageButton) findViewById(R.id.imageButton4);
        chavi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, Jugar.class);
                in.putExtra("quien_juega", 3);
                startActivity(in);
            }
        });

        alvaro = (ImageButton) findViewById(R.id.imageButton5);
        alvaro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, Jugar.class);
                in.putExtra("quien_juega", 4);
                startActivity(in);
            }
        });




    }

}
