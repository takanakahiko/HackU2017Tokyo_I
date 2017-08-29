package com.example.takanakahiko.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by takan on 2017/08/29.
 */

public class SavedataSQLiteWrapper {
    public static String get(Context ctx, String name){
        String ret = "";
        SavedataSQLiteOpenHelper soh;
        try {
            soh = new SavedataSQLiteOpenHelper(ctx);
            SQLiteDatabase db = soh.getWritableDatabase();
            Cursor cursor = db.rawQuery("SELECT *  name FROM savedata WHERE name = " + name + " ;", null);
            if (cursor.moveToNext()) {
                ret = cursor.getString(0);
            }
            db.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ret;
    }
    public static void set(Context ctx, String name, String value){
        SavedataSQLiteOpenHelper soh = new SavedataSQLiteOpenHelper(ctx);
        SQLiteDatabase db = soh.getWritableDatabase();
        db.beginTransaction();
        ContentValues values = new ContentValues();
        values.put("`name`",name);
        values.put("`value`",value);
        db.insert("`savedata`", null, values);
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
    }

}
