package com.charles.mygame.GameLogic;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.graphics.Rect;
import android.os.Build;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.charles.mygame.Activities.MainThread;
import com.charles.mygame.GameLogic.Objetos.Background;
import com.charles.mygame.GameLogic.Objetos.Enemigos.Enemigo;
import com.charles.mygame.GameLogic.Objetos.Enemigos.EnemigoEspecial;
import com.charles.mygame.GameLogic.Objetos.Enemigos.EnemigoRojo;
import com.charles.mygame.GameLogic.Objetos.Explosion;
import com.charles.mygame.GameLogic.Objetos.GameObject;
import com.charles.mygame.GameLogic.Objetos.Misiles.Missile;
import com.charles.mygame.GameLogic.Objetos.Misiles.MissileClever;
import com.charles.mygame.GameLogic.Objetos.Misiles.MissileEnemigo;
import com.charles.mygame.GameLogic.Objetos.Player;
import com.charles.mygame.R;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;


public class GamePanel extends SurfaceView implements SurfaceHolder.Callback{


    //MEDIDAS
    //MEDIDAS
    public static final int WIDTH = 1097;
    public static final int HEIGHT = 600;
    public static final int PLAYER_WIDTH = 95; //110
    public static final int PLAYER_HEIGHT = 130; //150
    public static final int MISSILE_WIDTH = 60; //69
    public static final int MISSILE_HEIGHT = 30; //34
    public static final int ENEMIGO_WIDTH = 95; //110
    public static final int ENEMIGO_HEIGHT = 130; //150
    public static final int ENEMIGO_ESPECIAL_WIDTH = 138; //158
    public static final int ENEMIGO_ESPECIAL_HEIGHT = 142; //162
    public static final int EXPLOSION_WIDTH = 200;
    public static final int EXPLOSION_HEIGHT = 200;
    public static final int CORAZON_WIDTH = 85;
    public static final int CORAZON_HEIGHT = 88;
    public static final int MOVESPEED = -3;
    public static int SCREEN_WIDTH = 1100;
    public static int SCREEN_HEIGHT = 600;

    private static final double[] posicion_boca_enemigo = {85, 100, 76, 72, 100};
    //MEDIDAS
    //MEDIDAS

//---------------------------------------------------------------------------------------------------------------------------------------------------

    //IMAGENES
    //IMAGENES
    private Bitmap jugador;

    private final Bitmap fotoFondo = BitmapFactory.decodeResource(getResources(),R.drawable.c_fondo);
    private final Bitmap fotoPene = BitmapFactory.decodeResource(getResources(),R.drawable.c_pene);
    private final Bitmap fotoPeneEnemigo = BitmapFactory.decodeResource(getResources(),R.drawable.c_pene_enemigo);
    private final Bitmap fotoTextoAyuda = BitmapFactory.decodeResource(getResources(),R.drawable.d_texto_ayuda);
    private final Bitmap fotoGameOver = BitmapFactory.decodeResource(getResources(),R.drawable.d_texto_game_over);
    private final Bitmap fotoPausa = BitmapFactory.decodeResource(getResources(),R.drawable.d_texto_pausa);
    private final Bitmap fotoBotonPausa = BitmapFactory.decodeResource(getResources(),R.drawable.c_boton_pausa);
    private final Bitmap fotoPuntuacion = BitmapFactory.decodeResource(getResources(),R.drawable.z_puntuacion);
    private final Bitmap fotoExplosion = BitmapFactory.decodeResource(getResources(),R.drawable.c_explosion);
    private final Bitmap fotoCorazon = BitmapFactory.decodeResource(getResources(),R.drawable.c_corazon);

    private final Bitmap[] fotoEnemigo = {
            BitmapFactory.decodeResource(getResources(),R.drawable.e_enemigo_gamero),
            BitmapFactory.decodeResource(getResources(),R.drawable.e_enemigo_gulyk),
            BitmapFactory.decodeResource(getResources(),R.drawable.e_enemigo_montero),
            BitmapFactory.decodeResource(getResources(),R.drawable.e_enemigo_chavi),
            BitmapFactory.decodeResource(getResources(),R.drawable.e_enemigo_alvaro)
    };
    private final Bitmap[] fotoEnemigoEspecial = {
            BitmapFactory.decodeResource(getResources(),R.drawable.e_especial_gamero),
            BitmapFactory.decodeResource(getResources(),R.drawable.e_especial_gulyk),
            BitmapFactory.decodeResource(getResources(),R.drawable.e_especial_montero),
            BitmapFactory.decodeResource(getResources(),R.drawable.e_especial_chavi),
            BitmapFactory.decodeResource(getResources(),R.drawable.e_especial_alvaro)
    };
    private final Bitmap[] fotoEnemigoRojo = {
            BitmapFactory.decodeResource(getResources(),R.drawable.e_rojo_gamero),
            BitmapFactory.decodeResource(getResources(),R.drawable.e_rojo_gulyk),
            BitmapFactory.decodeResource(getResources(),R.drawable.e_rojo_montero),
            BitmapFactory.decodeResource(getResources(),R.drawable.e_rojo_chavi),
            BitmapFactory.decodeResource(getResources(),R.drawable.e_rojo_alvaro)
    };

    private final Bitmap[] fotoEnemigoAzul = {
            BitmapFactory.decodeResource(getResources(),R.drawable.e_azul_gamero),
            BitmapFactory.decodeResource(getResources(),R.drawable.e_azul_gulyk),
            BitmapFactory.decodeResource(getResources(),R.drawable.e_azul_montero),
            BitmapFactory.decodeResource(getResources(),R.drawable.e_azul_chavi),
            BitmapFactory.decodeResource(getResources(),R.drawable.e_azul_alvaro)
    };


    private final Bitmap[] puntuacion = {
            BitmapFactory.decodeResource(getResources(), R.drawable.z0),
            BitmapFactory.decodeResource(getResources(), R.drawable.z1),
            BitmapFactory.decodeResource(getResources(), R.drawable.z2),
            BitmapFactory.decodeResource(getResources(), R.drawable.z3),
            BitmapFactory.decodeResource(getResources(), R.drawable.z4),
            BitmapFactory.decodeResource(getResources(), R.drawable.z5),
            BitmapFactory.decodeResource(getResources(), R.drawable.z6),
            BitmapFactory.decodeResource(getResources(), R.drawable.z7),
            BitmapFactory.decodeResource(getResources(), R.drawable.z8),
            BitmapFactory.decodeResource(getResources(), R.drawable.z9)
    };

    private final Bitmap[] puntuacionGrande = {
            BitmapFactory.decodeResource(getResources(), R.drawable.zz0),
            BitmapFactory.decodeResource(getResources(), R.drawable.zz1),
            BitmapFactory.decodeResource(getResources(), R.drawable.zz2),
            BitmapFactory.decodeResource(getResources(), R.drawable.zz3),
            BitmapFactory.decodeResource(getResources(), R.drawable.zz4),
            BitmapFactory.decodeResource(getResources(), R.drawable.zz5),
            BitmapFactory.decodeResource(getResources(), R.drawable.zz6),
            BitmapFactory.decodeResource(getResources(), R.drawable.zz7),
            BitmapFactory.decodeResource(getResources(), R.drawable.zz8),
            BitmapFactory.decodeResource(getResources(), R.drawable.zz9)
    };

    private final Bitmap[] vida = {
            Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.c_vida), 0, 0, 250, 38),
            Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.c_vida), 0, 38, 250, 38),
            Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.c_vida), 0, 38*2, 250, 38),
            Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.c_vida), 0, 38*3, 250, 38),
            Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.c_vida), 0, 38*4, 250, 38),
            Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.c_vida), 0, 38*5, 250, 38),
            Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.c_vida), 0, 38*6, 250, 38),
            Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.c_vida), 0, 38*7, 250, 38),
            Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.c_vida), 0, 38*8, 250, 38),
            Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.c_vida), 0, 38*9, 250, 38),
            Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.c_vida), 0, 38*10, 250, 38)
    };
    //IMAGENES
    //IMAGENES

//---------------------------------------------------------------------------------------------------------------------------------------------------

    //DATOS
    //DATOS
    private Context mContext;
    private DbHelper dbHelper;
    private int record;
    private double posicion_boca;
    private double posMax;
    private boolean primeraVez;
    private boolean hayEspecial;
    private boolean hayRojo;
    private boolean hayAzul;
    private int numJugador;
    private int tiempoNivel, nivel;
    //DATOS
    //DATOS

//---------------------------------------------------------------------------------------------------------------------------------------------------

    //TIMES
    //TIMES
    private long missileStartTime;
    private long missilesElapsed;
    private long enemigoStartTime;
    private long enemigosElapsed;
    //TIMES
    //TIMES

//---------------------------------------------------------------------------------------------------------------------------------------------------

    //OBJETOS
    //OBJETOS
    private MainThread thread;
    private Background bg;
    private Player player;
    private ArrayList<Missile> missiles;
    private ArrayList<Enemigo> enemigos;
    private ArrayList<MissileEnemigo> missilesRojos;
    private ArrayList<MissileClever> missilesAzules;
    private EnemigoEspecial enemigoEspecial;
    private EnemigoRojo enemigoRojo;
    private EnemigoRojo enemigoAzul;
    private ArrayList<Explosion> explosiones;
    //OBJETOS
    //OBJETOS

//---------------------------------------------------------------------------------------------------------------------------------------------------

    public GamePanel(Context context, Bitmap jugador, double posicion_boca, double posMax, int w, int h, int numJugador){

        super(context);

        //add the callback to the surfaceholder to intercept events
        getHolder().addCallback(this);

        this.mContext = context;

        this.jugador = jugador;
        this.numJugador = numJugador;

        this.nivel = 0;
        this.tiempoNivel = 0;

        this.posicion_boca = posicion_boca;
        this.posMax = posMax;
        this.primeraVez = true;
        this.hayEspecial = false;
        this.hayRojo = false;
        this.missilesElapsed = 0;
        this.enemigosElapsed = 0;

        this.dbHelper = new DbHelper(this.mContext);

        this.record = dbHelper.cargarRecord();

        if(this.record == -1) {
            this.record = 0;
            this.dbHelper.insertar(0);
        }

        SCREEN_WIDTH = w;
        SCREEN_HEIGHT = h;


        this.thread = new MainThread(getHolder(), this);

        //make gamePanel focusable so it can handle events
        setFocusable(true);


    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder){

        boolean retry = true;

        while(retry){
            try{
                player.setPlaying(false);
                thread.setRunning(false);
                thread.join();

                retry = false;
            }catch(InterruptedException e){e.printStackTrace();}


        }

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){

        if(primeraVez) {
            bg = new Background(fotoFondo);
            player = new Player(jugador, posMax, PLAYER_WIDTH, PLAYER_HEIGHT, 2);
            missiles = new ArrayList<>();
            missilesRojos = new ArrayList<>();
            missilesAzules = new ArrayList<>();
            enemigos = new ArrayList<>();
            explosiones = new ArrayList<>();
        }



        //we can safely start the game loop;
        try {
            thread.setRunning(true);
            thread.start();
        }catch(Exception ex){
            thread = new MainThread(getHolder(), this);
            thread.setRunning(true);
            thread.start();
        }

    }


//---------------------------------------------------------------------------------------------------------------------------------------------------


    //ON-TOUCH
    //ON-TOUCH
    //ON-TOUCH
    //ON-TOUCH

    //500 w, 453 h
    // siempre w+70 y h+100

    @TargetApi(Build.VERSION_CODES.ECLAIR)
    @Override
    public boolean onTouchEvent(MotionEvent event){

        boolean ok = false, abajo = false, arriba = false;
        int pulsacion1 = 0;
        int pulsacion = event.getPointerId(0);


        //SI AUN TIENE VIDA
        if(player.getVida() > 0) {

            if (player.getPlaying()) {

                missilesElapsed = (System.nanoTime() - missileStartTime) / 1000000;

                try {
                    pulsacion1 = event.getPointerId(1);
                    ok = true;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }


                //SI ESTA PULSANDO EN DOS SITIOS A LA VEZ
                if (ok) {
                    int pointerIndex1 = event.findPointerIndex(pulsacion1);
                    float x1 = event.getX(pointerIndex1);
                    float y1 = event.getY(pointerIndex1);

                    abajo = onTouch_Pulsacion2_Parte1(x1, y1, false); //siempre es false al principio, esta funcion a lo mejor lo cambia a true
                    arriba = onTouch_Pulsacion2_Parte2(x1, y1, false);
                }


                //LA PRIMERA PULSACION
                int pointerIndex = event.findPointerIndex(pulsacion);
                float x = event.getX(pointerIndex);
                float y = event.getY(pointerIndex);


                onTouch_Pulsacion1(x, y, abajo, arriba);


                //SI LEVANTA LOS DEDOS
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    player.setUp(false);
                    player.setDisparo(false);
                }
            }
            else {
                int pointerIndex = event.findPointerIndex(pulsacion);
                float x = event.getX(pointerIndex);
                float y = event.getY(pointerIndex);

                //if (x >= 867 && x <= 1065 && y >= 822 && y <= 1002) {
                if (x >= SCREEN_WIDTH/2.214 && x <= SCREEN_WIDTH/1.803 && y >= SCREEN_HEIGHT/1.314 && y <= SCREEN_HEIGHT/1.078) {

                    player.setPlaying(true);
                    player.setStartTime(System.nanoTime());

                    if (primeraVez)
                        primeraVez = false;
                }
                else {
                    if(!primeraVez){
                        //if (x >= 873 && x <= 993 && y >= 543 && y <= 663) {
                        if (x >= SCREEN_WIDTH/5.93 && x <= SCREEN_WIDTH/3.74 && y >= SCREEN_HEIGHT/1.314 && y <= SCREEN_HEIGHT/1.078) {
                            salir();
                        }
                        else {
                            //if (x >= 240 && x <= 360 && y >= 543 && y <= 663) {
                            if (x >= SCREEN_WIDTH/1.365 && x <= SCREEN_WIDTH/1.2 && y >= SCREEN_HEIGHT/1.314 && y <= SCREEN_HEIGHT/1.078) {
                                reset();
                            }
                        }
                    }
                }
            }
        }
        //SI SE QUEDA SIN VIDA
        else{
            int pointerIndex = event.findPointerIndex(pulsacion);
            float x = event.getX(pointerIndex);
            float y = event.getY(pointerIndex);

            if (x >= SCREEN_WIDTH/5.93 && x <= SCREEN_WIDTH/3.74 && y >= SCREEN_HEIGHT/1.314 && y <= SCREEN_HEIGHT/1.078) {
                salir();
            }
            else {
                if (x >= SCREEN_WIDTH/1.365 && x <= SCREEN_WIDTH/1.2 && y >= SCREEN_HEIGHT/1.314 && y <= SCREEN_HEIGHT/1.078) {
                    reset();
                }
            }
        }
        return true;

        //return super.onTouchEvent(event);
    }

    public void onTouch_Pulsacion1(float x, float y, boolean abajo, boolean arriba){

        if (!abajo) {
            if (x >= SCREEN_WIDTH / 2 && x <= SCREEN_WIDTH + 300 && y >= 0 && y <= SCREEN_HEIGHT) {

                if (x >= SCREEN_WIDTH - (SCREEN_WIDTH/8.73) && x <= SCREEN_WIDTH + 1000 && y >= 0 && y <= (SCREEN_HEIGHT/4.9)) {
                    player.setPlaying(false);
                } else {

                    player.setDisparo(true);

                    if (missilesElapsed > 1000) {

                        missiles.add(new Missile(fotoPene,
                                player.getWidth() * 1.6 + 15, player.getY() + posicion_boca, MISSILE_WIDTH, MISSILE_HEIGHT, player.getScore(), 1));


                        missileStartTime = System.nanoTime();
                    }
                }
            } else
                player.setDisparo(false);
        }

        if (!arriba) {
            if (x >= 0 && x <= SCREEN_WIDTH / 2 && y >= 0 && y <= SCREEN_HEIGHT) {
                player.setUp(true);
            } else
                player.setUp(false);
        }

    }

    public boolean onTouch_Pulsacion2_Parte1(float x1, float y1, boolean abajo){

        if (x1 >= SCREEN_WIDTH / 2 && x1 <= SCREEN_WIDTH + 40 && y1 >= 0 && y1 <= SCREEN_HEIGHT) {

            if (x1 >= SCREEN_WIDTH - (SCREEN_WIDTH/8.73) && x1 <= SCREEN_WIDTH + 1000 && y1 >= 0 && y1 <= (SCREEN_HEIGHT/4.9)) {
                player.setPlaying(false);
            } else {

                player.setDisparo(true);

                if (missilesElapsed > 1000) {

                    missiles.add(new Missile(fotoPene,
                            player.getWidth() * 1.6 + 15, player.getY() + posicion_boca, MISSILE_WIDTH, MISSILE_HEIGHT, player.getScore(), 1));


                    missileStartTime = System.nanoTime();
                }

                abajo = true;
            }
        } else
            player.setDisparo(false);

        return abajo;
    }

    public boolean onTouch_Pulsacion2_Parte2(float x1, float y1, boolean arriba){

        if (x1 >= 0 && x1 <= SCREEN_WIDTH / 2 && y1 >= 0 && y1 <= SCREEN_HEIGHT) {
            player.setUp(true);
            arriba = true;
        } else
            player.setUp(false);

        return arriba;
    }

    //ON-TOUCH
    //ON-TOUCH
    //ON-TOUCH
    //ON-TOUCH

//---------------------------------------------------------------------------------------------------------------------------------------------------

    //UPDATE
    //UPDATE
    //UPDATE
    //UPDATE

    public void update(){

        if(player.getPlaying()) {
            bg.update();
            player.update();


            enemigosElapsed = (System.nanoTime() - enemigoStartTime) / 1000000;

            //CREA EL ENEMIGO
            if(enemigosElapsed > 1800){
                creaEnemigo();
            }

            //ACTUALIZA LOS ENEMIGOS NORMALES
            for(int i = 0; i < enemigos.size(); i++){
                enemigos.get(i).update();


                //SI SE CHOCA CON UN ENEMIGO
                if(collisionEnemigoPlayer(enemigos.get(i), player)){

                    if(enemigos.get(i).getEsCorazon()){
                        enemigos.remove(i);
                        if(player.getVida() < 10)
                            player.sumaVida();
                    }
                    else {
                        enemigos.remove(i);

                        player.restaVida();

                        explosiones.add(new Explosion(fotoExplosion, player.getX() - 50, player.getY() - 30,
                                EXPLOSION_WIDTH, EXPLOSION_HEIGHT, 47));

                        if (player.getVida() == 0) {
                            player.setPlaying(false);
                            gameOver();
                        }
                    }

                }

                if(enemigos.get(i).getX() < -200){
                    enemigos.remove(i);
                    break;
                }
            }

            //ACTUALIZA LOS MISILES
            for(int i = 0; i < missiles.size(); i++){
                missiles.get(i).update();

                if(missiles.get(i).getX() > WIDTH + 100){
                    missiles.remove(i);
                    break;
                }
            }

            //MATA A LOS ENEMIGOS NORMALES
            misiles_EnemigoNormal();


            update_EnemigoEspecial(); //ACTUALIZA EL ENEMIGO ESPECIAL Y LO ELIMINA SI LE HAN DADO
            update_EnemigoRojo(); //ACTUALIZA EL ENEMIGO ROJO Y LO ELIMINA SI LE HAN DADO, LOS MISILES QUE LANZAN SE ACTUALIZAN SI O SI
            update_EnemigoAzul(); //ACTUALIZA EL ENEMIGO AZUL Y LO ELIMINA SI LE HAN DADO, LOS MISILES QUE LANZAN SE ACTUALIZAN SI O SI

            update_Explosiones(); // ACTUALIZA LAS EXPLOSIONES

            tiempoNivel++;
            if(tiempoNivel >= 300){
                tiempoNivel = 0;
                nivel++;
            }

        }
        else {
            missileStartTime = System.nanoTime() - missilesElapsed*1000000;
            enemigoStartTime = System.nanoTime() - enemigosElapsed*1000000;
            if(hayRojo)
                enemigoRojo.esperar();
            if(hayAzul)
                enemigoAzul.esperar();
        }

    }

    public void creaEnemigo(){

        Random rand = new Random();
        Random randEnemigo = new Random();
        int numeroAleat = randEnemigo.nextInt(fotoEnemigo.length);

        while(numeroAleat == numJugador){
            randEnemigo = new Random();
            numeroAleat = randEnemigo.nextInt(fotoEnemigo.length);
        }

        double randomValue = (HEIGHT - ENEMIGO_HEIGHT) * rand.nextDouble();

        rand = new Random();



        if(rand.nextInt(25) == 1){
            enemigos.add(new Enemigo(fotoCorazon,
                    WIDTH, randomValue, CORAZON_WIDTH, CORAZON_HEIGHT, 0, 1));
            enemigos.get(enemigos.size()-1).setEsCorazon();
        }
        else {
            if (rand.nextInt(20) <= 4 && !hayEspecial && nivel > 0) {
                enemigoEspecial = new EnemigoEspecial(fotoEnemigoEspecial[numeroAleat],
                        WIDTH, randomValue, ENEMIGO_ESPECIAL_WIDTH, ENEMIGO_ESPECIAL_HEIGHT, nivel, 2);
                hayEspecial = true;
            } else {
                if (rand.nextInt(20) <= 6 && !hayRojo && nivel > 1) {
                    enemigoRojo = new EnemigoRojo(fotoEnemigoRojo[numeroAleat],
                            WIDTH, randomValue, ENEMIGO_WIDTH, ENEMIGO_HEIGHT, posicion_boca_enemigo[numeroAleat], nivel, 2);
                    hayRojo = true;
                } else {
                    if (rand.nextInt(20) <= 8 && !hayAzul && nivel > 2) {
                        enemigoAzul = new EnemigoRojo(fotoEnemigoAzul[numeroAleat],
                                WIDTH, randomValue, ENEMIGO_WIDTH, ENEMIGO_HEIGHT, posicion_boca_enemigo[numeroAleat], nivel, 2);
                        hayAzul = true;
                    } else {
                        enemigos.add(new Enemigo(fotoEnemigo[numeroAleat],
                                WIDTH, randomValue, ENEMIGO_WIDTH, ENEMIGO_HEIGHT, nivel, 1));
                    }
                }
            }
        }

        enemigoStartTime = System.nanoTime();
    }

    public void misiles_EnemigoNormal(){
        int misilesMuertos[] = new int[100];
        int enemigosMuertos[] = new int[100];
        int cont = 0;

        for(int i = 0; i < missiles.size(); i++){
            for(int j = 0; j < enemigos.size(); j++){

                if(collision(missiles.get(i), enemigos.get(j)) &&
                        missiles.get(i).getX() > (PLAYER_WIDTH*1.6 + PLAYER_WIDTH - 10) && missiles.get(i).getX() < WIDTH - 20) {

                    if(!enemigos.get(j).getEsCorazon()) {
                        misilesMuertos[cont] = i;
                        enemigosMuertos[cont] = j;
                        cont++;
                    }
                }
            }
        }

        for(int i = 0; i < cont; i++){
            player.sumaScore(20);
            missiles.remove(misilesMuertos[i]);
            //enemigos.get(enemigosMuertos[i]).changeAnimation();

            explosiones.add(new Explosion(fotoExplosion, enemigos.get(i).getX() - 50, enemigos.get(i).getY() - 50,
                    EXPLOSION_WIDTH, EXPLOSION_HEIGHT, 47));

            //hacer que desaparezcan los enemigos
            enemigos.remove(enemigosMuertos[i]);
        }
    }

    public void update_EnemigoEspecial(){

        //SI HAY UN ESPECIAL
        if(hayEspecial) {

            enemigoEspecial.update();

            //SI SE CHOCA
            if(collisionEnemigoPlayer(enemigoEspecial, player)){
                hayEspecial = false;
                player.restaVida();

                explosiones.add(new Explosion(fotoExplosion, player.getX() - 50, player.getY() - 30,
                        EXPLOSION_WIDTH, EXPLOSION_HEIGHT, 47));

                if(player.getVida() == 0){
                    player.setPlaying(false);
                    gameOver();
                }

            }

            if (enemigoEspecial.getX() < -300) {
                hayEspecial = false;
            }


            for (int i = 0; i < missiles.size(); i++) {
                if (collision(missiles.get(i), enemigoEspecial) &&
                        missiles.get(i).getX() > (PLAYER_WIDTH * 1.6 + PLAYER_WIDTH - 10) && missiles.get(i).getX() < WIDTH - 20) {
                    player.sumaScore(100);
                    missiles.remove(i);
                    //enemigoEspecial.changeAnimation();

                    explosiones.add(new Explosion(fotoExplosion, enemigoEspecial.getX() - 50, enemigoEspecial.getY() - 50,
                            EXPLOSION_WIDTH, EXPLOSION_HEIGHT, 47));

                    //hacer que desaparezcan los enemigos
                    hayEspecial = false;
                }
            }
        }

    }
    public void update_EnemigoRojo(){

        for(int i = 0; i < missilesRojos.size(); i++){
            missilesRojos.get(i).update();

            if(missilesRojos.get(i).getX() > WIDTH + 100){
                missilesRojos.remove(i);
                break;
            }
        }


        for(int i = 0; i < missilesRojos.size(); i++){
            if(collision(missilesRojos.get(i), player)){
                missilesRojos.remove(i);
                player.restaVida();

                explosiones.add(new Explosion(fotoExplosion, player.getX() - 50, player.getY() - 30,
                        EXPLOSION_WIDTH, EXPLOSION_HEIGHT, 47));

                if(player.getVida() == 0){
                    player.setPlaying(false);
                    gameOver();
                }
            }
        }

        //SI HAY UN ENEMIGO ROJO
        if(hayRojo) {

            enemigoRojo.update();

            if (enemigoRojo.lanzar()) {
                missilesRojos.add(new MissileEnemigo(fotoPeneEnemigo,
                        enemigoRojo.getX(), enemigoRojo.getY() + enemigoRojo.getPosBoca(), MISSILE_WIDTH, MISSILE_HEIGHT, nivel, 1));
            }

            //SI SE CHOCA
            if(collisionEnemigoPlayer(enemigoRojo, player)){
                hayRojo = false;
                player.restaVida();

                explosiones.add(new Explosion(fotoExplosion, player.getX() - 50, player.getY() - 30,
                        EXPLOSION_WIDTH, EXPLOSION_HEIGHT, 47));

                if(player.getVida() == 0){
                    player.setPlaying(false);
                    gameOver();
                }

            }

            if (enemigoRojo.getX() < -300) {
                hayRojo = false;
            }


            for (int i = 0; i < missiles.size(); i++) {
                if (collision(missiles.get(i), enemigoRojo) &&
                        missiles.get(i).getX() > (PLAYER_WIDTH * 1.6 + PLAYER_WIDTH - 10) && missiles.get(i).getX() < WIDTH - 20) {
                    player.sumaScore(100);
                    missiles.remove(i);
                    //enemigoRojo.changeAnimation();

                    explosiones.add(new Explosion(fotoExplosion, enemigoRojo.getX() - 50, enemigoRojo.getY() - 50,
                            EXPLOSION_WIDTH, EXPLOSION_HEIGHT, 47));

                    //hacer que desaparezcan los enemigos
                    hayRojo = false;

                }
            }
        }

    }
    public void update_EnemigoAzul(){


        for (int i = 0; i < missilesAzules.size(); i++) {
            missilesAzules.get(i).update(player.getY()+posicion_boca+5);

            if (missilesAzules.get(i).getX() > WIDTH + 100) {
                missilesAzules.remove(i);
                break;
            }
        }

        for (int i = 0; i < missilesAzules.size(); i++) {
            if (collision(missilesAzules.get(i), player)) {
                missilesAzules.remove(i);
                player.restaVida();

                explosiones.add(new Explosion(fotoExplosion, player.getX() - 50, player.getY() - 30,
                        EXPLOSION_WIDTH, EXPLOSION_HEIGHT, 47));

                if (player.getVida() == 0) {
                    player.setPlaying(false);
                    gameOver();
                }
            }
        }

        //SI HAY UN ENEMIGO AZUL
        if(hayAzul) {
            enemigoAzul.update();

            if (enemigoAzul.lanzar()) {
                missilesAzules.add(new MissileClever(fotoPeneEnemigo,
                        enemigoAzul.getX(), enemigoAzul.getY() + enemigoAzul.getPosBoca(), MISSILE_WIDTH, MISSILE_HEIGHT, nivel, 1));
            }

            //SI SE CHOCA
            if(collisionEnemigoPlayer(enemigoAzul, player)){
                hayAzul = false;
                player.restaVida();

                explosiones.add(new Explosion(fotoExplosion, player.getX() - 50, player.getY() - 30,
                        EXPLOSION_WIDTH, EXPLOSION_HEIGHT, 47));

                if(player.getVida() == 0){
                    player.setPlaying(false);
                    gameOver();
                }

            }

            if (enemigoAzul.getX() < -300) {
                hayAzul = false;
            }


            for (int i = 0; i < missiles.size(); i++) {
                if (collision(missiles.get(i), enemigoAzul) &&
                        missiles.get(i).getX() > (PLAYER_WIDTH * 1.6 + PLAYER_WIDTH - 10) && missiles.get(i).getX() < WIDTH - 20) {
                    player.sumaScore(100);
                    missiles.remove(i);
                    //enemigoAzul.changeAnimation();

                    explosiones.add(new Explosion(fotoExplosion, enemigoAzul.getX() - 50, enemigoAzul.getY() - 50,
                            EXPLOSION_WIDTH, EXPLOSION_HEIGHT, 47));

                    //hacer que desaparezcan los enemigos
                    hayAzul = false;

                }
            }
        }

    }

    public void update_Explosiones(){

        for(int i = 0; i < explosiones.size(); i++){

            if(explosiones.get(i).isEnd()){
                explosiones.remove(i);
                break;
            }
        }

    }

    //Comprueba si se han chocado dos objetos cualesquiera
    public boolean collision(GameObject a, GameObject b){

        //si a y b estan dentro de la pantalla y hay colision, devuelve true
        return a.getX() < WIDTH + 5 && b.getX() < WIDTH + 5 && Rect.intersects(a.getRectangulo(), b.getRectangulo());
    }

    //lo mismo que collision sin la restriccion de si estan al lado del player
    public boolean collisionEnemigoPlayer(GameObject a, GameObject b){

        //si a y b estan dentro de la pantalla y hay colision, devuelve true
        return Rect.intersects(a.getRectangulo2(), b.getRectangulo2());
    }


    //UPDATE
    //UPDATE
    //UPDATE
    //UPDATE

//---------------------------------------------------------------------------------------------------------------------------------------------------


    //DRAW
    //DRAW
    //DRAW
    //DRAW

    @Override
    public void draw(Canvas canvas){

        final float scaleFactorX = getWidth()/(WIDTH*1.f);
        final float scaleFactorY = getHeight()/(HEIGHT*1.f);
        if(canvas != null){
            final int savedState = canvas.save();
            canvas.scale(scaleFactorX, scaleFactorY);
            bg.draw(canvas);


            //MISILES
            //MISILES

            //Misiles del jugador
            for(int i = 0; i < enemigos.size(); i++){
                enemigos.get(i).draw(canvas);
            }

            //Misiles rojos
            for(int i = 0; i < missilesRojos.size(); i++){
                missilesRojos.get(i).draw(canvas);
            }

            //Misiles azules
            for (int i = 0; i < missilesAzules.size(); i++) {
                missilesAzules.get(i).draw(canvas);
            }

            //MISILES
            //MISILES

            //PERSONAJES
            //PERSONAJES

            if(hayEspecial) {
                enemigoEspecial.draw(canvas);
            }
            if(hayRojo){
                enemigoRojo.draw(canvas);
            }
            if(hayAzul){
                enemigoAzul.draw(canvas);
            }

            for(int i = 0; i < missiles.size(); i++){
                missiles.get(i).draw(canvas);
            }

            player.draw(canvas);

            for(int i = 0; i < explosiones.size(); i++){
                explosiones.get(i).draw(canvas);
            }

            //PERSONAJES
            //PERSONAJES

            drawTexto(canvas);

            canvas.restoreToCount(savedState);

        }
    }

    public void drawTexto(Canvas canvas){

        if(primeraVez){
            canvas.drawBitmap(fotoTextoAyuda, 0, 0, null);
        }
        else {
            if(!player.getPlaying()){
                if(player.getVida() > 0) {
                    canvas.drawBitmap(fotoPausa, 0, 0, null);
                    if(player.getScore() < record)
                        player.drawScore(canvas, puntuacionGrande, 830, 230, 55, record);
                    else
                        player.drawScore(canvas, puntuacionGrande, 830, 230, 55, -1);
                }
                else {
                    canvas.drawBitmap(fotoGameOver, 0, 0, null);
                    player.drawScore(canvas, puntuacionGrande, 830, 180, 55, -1);
                    player.drawScore(canvas, puntuacionGrande, 830, 270, 55, record);

                }
            }
            else {
                canvas.drawBitmap(fotoBotonPausa, WIDTH - 100, 5, null);
                canvas.drawBitmap(fotoPuntuacion, 20, HEIGHT - 50, null);
                player.drawScore(canvas, puntuacion, 400, HEIGHT-50, 30, -1);
                //player.drawScore(canvas, puntuacion, 400, HEIGHT-50, 30, nivel); // prueba
                player.drawVida(canvas, vida);
            }
        }
    }

    //DRAW
    //DRAW
    //DRAW
    //DRAW

//---------------------------------------------------------------------------------------------------------------------------------------------------

    //EXTRAS
    //EXTRAS
    //EXTRAS
    //EXTRAS

    public void gameOver(){

        if(player.getScore() > record){
            record = player.getScore();

            dbHelper.modificar(record);
        }

    }

    public void salir(){
        ((Activity) this.getContext()).finish();
    }


    public void reset(){

        this.primeraVez = true;
        this.hayEspecial = false;
        this.hayRojo = false;
        this.hayAzul = false;
        this.missilesElapsed = 0;
        this.enemigosElapsed = 0;

        this.nivel = 0;
        this.tiempoNivel = 0;

        bg = new Background(fotoFondo);
        player = new Player(jugador, posMax, PLAYER_WIDTH, PLAYER_HEIGHT, 2);
        missiles = new ArrayList<>();
        missilesRojos = new ArrayList<>();
        missilesAzules= new ArrayList<>();
        enemigos = new ArrayList<>();

        player.setPlaying(false);
    }


    //EXTRAS
    //EXTRAS
    //EXTRAS
    //EXTRAS

}
