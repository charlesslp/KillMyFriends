package com.charles.mygame.GameLogic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "Record.sqlite";
    private static final int DB_SCHEME_VERSION = 1;

    private static final String TABLE_NAME = "tablaRecord";
    public static final String CN_ID = "_id";
    public static final String CN_RECORD = "record";
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + " ("
            + CN_ID + " integer primary key, "
            + CN_RECORD + " integer not null);";

    private SQLiteDatabase db;

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_SCHEME_VERSION);

        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    private ContentValues generarContentValues(String id, int record){

        ContentValues valores = new ContentValues();
        valores.put(CN_ID, id);
        valores.put(CN_RECORD, record);

        return valores;
    }

    public void insertar(int record){

        db.insert(TABLE_NAME, null, generarContentValues("0", record));

    }

    public void modificar(int nuevoRecord){

        db.update(TABLE_NAME, generarContentValues("0", nuevoRecord), CN_ID + "=0", null);

    }

    public int cargarRecord(){
        Cursor c = db.rawQuery("select record from " + TABLE_NAME, null);

        int r = -1;
        if(c.moveToFirst()){
            r = c.getInt(0);
        }

        return r;
    }


}









