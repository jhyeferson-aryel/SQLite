package com.unipac.jhyef.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListaComprasDAO {
    private SQLiteDatabase db;
    private SQLiteHelper helper;

    public ListaComprasDAO(Context context) {
        helper = new SQLiteHelper(context);
        db = helper.openDatabase();
    }

    private static final String table_ListaCompras = "ListaCompras";

    private static final String key_id = "id";
    private static final String key_nomeProd = "nomeProd";
    private static final String key_quant = "quant";

    private static final String[] columns = {key_id, key_quant, key_nomeProd};

    public void addItem(ListaCompras l) {
        Log.d("addItem", l.toString());

        ContentValues values = new ContentValues();
        values.put(key_nomeProd, l.getNomeProd());
        values.put(key_quant, l.getQuantidade());

        db.insert(table_ListaCompras, null, values);
    }

    public ListaCompras getListaCompras(int id) {

        Cursor cursor = db.query(table_ListaCompras, columns, " id = ? ", new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        ListaCompras listaCompras = new ListaCompras();
        listaCompras.setNomeProd(cursor.getString(1));
        listaCompras.setQuantidade(Integer.parseInt(cursor.getString(2)));

        Log.d("getLista(" + id + ")", listaCompras.toString());

        return listaCompras;
    }

    public ArrayList<ListaCompras> retornaItens() {
        ArrayList<ListaCompras> listaCompras = new ArrayList<ListaCompras>();

        String query = "select * from " + table_ListaCompras;

        Cursor cursor = db.rawQuery(query, null);

        ListaCompras produto = null;
        if (cursor.moveToFirst()) {
            do {
                produto = new ListaCompras();
                produto.setId((cursor.getLong(0)));
                produto.setNomeProd((cursor.getString(1)));
                produto.setQuantidade(Integer.parseInt(cursor.getString(2)));

                listaCompras.add(produto);
            } while (cursor.moveToNext());
        }
        return listaCompras;
    }
    public int updateListaCompras(ListaCompras listaCompras) {

        ContentValues values = new ContentValues();

        values.put(key_nomeProd, listaCompras.getNomeProd());

        values.put(key_quant, listaCompras.getQuantidade());

        int update = db.update(table_ListaCompras, //table
                values, // column/value
                key_id + " = ?", // selections
                new String[] { String.valueOf(listaCompras.getId()) }); //selection args

        return update;

    }
    public void deleteItem(ListaCompras listaCompras) {
        db.delete(table_ListaCompras,
                key_id+" = ?",
                new String[] { String.valueOf(listaCompras.getId()) });

    }

    public void deleteAll() {
        db.delete(table_ListaCompras, null,null);
    }
}

