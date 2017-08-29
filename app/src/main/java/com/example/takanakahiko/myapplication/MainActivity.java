package com.example.takanakahiko.myapplication;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MediaPlayer m_mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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