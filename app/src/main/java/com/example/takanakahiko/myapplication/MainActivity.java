package com.example.takanakahiko.myapplication;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity {

    MediaPlayer m_mediaPlayer;
    public static SQLiteDatabase db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        SavedataSQLiteOpenHelper soh = new SavedataSQLiteOpenHelper(getApplicationContext());
        db = soh.getWritableDatabase();
    }

    //タップしたらメイン画面に遷移
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("TouchEvent", "X:" + event.getX() + ",Y:" + event.getY());
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                m_mediaPlayer = MediaPlayer.create(this, R.raw.startsound);
                m_mediaPlayer.start();
                Intent intent = new Intent(this,HomeActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }

}