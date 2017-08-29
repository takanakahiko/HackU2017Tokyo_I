package com.example.takanakahiko.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by takan on 2017/08/29.
 */

public class SavedataSQLiteWrapper {
    public static String get(SQLiteDatabase db, String name){
        String ret = "";
        SavedataSQLiteOpenHelper soh;
        try {
            Cursor cursor = db.rawQuery("SELECT name_c, value_c FROM savedata WHERE name_c='"+name+"';", null);
            if(cursor.moveToNext())ret = cursor.getString(1);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ret;
    }
    public static void set(SQLiteDatabase db, String name, String value){
        db.execSQL("UPDATE `savedata` SET value_c="+value+" WHERE name_c='"+name+"';");
    }

}
