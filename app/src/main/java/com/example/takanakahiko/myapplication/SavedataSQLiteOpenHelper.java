package com.example.takanakahiko.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by takan on 2017/08/29.
 */

public class SavedataSQLiteOpenHelper extends SQLiteOpenHelper {
    Context context;

    public SavedataSQLiteOpenHelper(Context context) {
        super(context, "data.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE `savedata` (" +
                "  `name_c` TEXT  DEFAULT NULL," +
                "  `value_c` TEXT DEFAULT NULL," +
                "  PRIMARY KEY (`name_c`)" +
                ")");
        ContentValues values = new ContentValues();
        values.put("`name_c`","score");
        values.put("`value_c`","0");
        db.insert("`savedata`", null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
