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
                "  `id` int(11) NOT NULL," +
                "  `name` varchar(255)  DEFAULT NULL," +
                "  `value` varchar(255)  DEFAULT NULL," +
                "  PRIMARY KEY (`id`)" +
                ")");
        db.beginTransaction();
        ContentValues values = new ContentValues();
        values.put("`name`","score");
        values.put("`value`","0");
        db.insert("`savedata`", null, values);
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
