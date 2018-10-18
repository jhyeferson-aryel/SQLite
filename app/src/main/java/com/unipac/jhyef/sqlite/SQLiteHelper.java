package com.unipac.jhyef.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class SQLiteHelper extends SQLiteOpenHelper{
    private static final int database_version = 1;
    private static final String database_name = "listacompras.db";

    public SQLiteHelper(Context context){
        super(context, database_name, null, database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_LISTACOMPRAS_TABLE = "create table ListaCompras ( " + "id Integer primary key autoincrement, " + "nomeProd text, " + "quant integer)";

        db.execSQL(CREATE_LISTACOMPRAS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("drop table if exists ListaCompras");

        this.onCreate(db);
    }

    public SQLiteDatabase openDatabase(){
        return this.getWritableDatabase();
    }
}
