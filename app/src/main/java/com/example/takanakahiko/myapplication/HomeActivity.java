package com.example.takanakahiko.myapplication;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Random;

import static com.example.takanakahiko.myapplication.R.id.imageButton;


public class HomeActivity extends AppCompatActivity {

    String Player_Name = "大遅刻太郎";
    int Player_Level = 1;
    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //時刻表示
        Date now = new Date(System.currentTimeMillis());
        DateFormat formatter = new SimpleDateFormat("M月dd日 HH時mm分");
        String nowText = formatter.format(now);
        TextView Time = (TextView)findViewById(R.id.Time);
        Time.setText(nowText);

        //プレイヤ名表示
        TextView PlayerName = (TextView)findViewById(R.id.PlayerName);
        PlayerName.setText(Player_Name);

        // 夏レベル表示
        String PL = String.valueOf(Player_Level);
        TextView PlayerLevel = (TextView)findViewById(R.id.PlayerLevel);
        PlayerLevel.setText("夏レベル：" + PL);

        imageButton = (ImageButton)findViewById(R.id.imageButton);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.huyu_hutuu_kuti00);
        imageButton.setImageBitmap(bitmap);
    }


    public void imageButton(View v){

        imageButton = (ImageButton)findViewById(R.id.imageButton);
        TextView Charactor_Comment =(TextView)findViewById(R.id.Charactor_Comment);
        int r = new Random().nextInt(4);

        int[] imageId = {

                R.drawable.huyu_tere_akire_kuti02,
                R.drawable.huyu_tere_kurai_kuti01,
                R.drawable.huyu_tere_kurai_kuti02,
                R.drawable.huyu_tere_odoroki_kuti01,
        };

        String[] strings = {
                "セクハラですよ･･･",
                "･････････",
                "なにか用ですか？",
                "え･･･････"
        };

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),imageId[r]);
        imageButton.setImageBitmap(bitmap);
        Charactor_Comment.setVisibility(View.VISIBLE);
        Charactor_Comment.setText(strings[r]);
    }

    //  シナリオ画面に遷移
    public void Home_Scenario(View v) {
        Intent intent_Scenario = new Intent(getApplication(), ScenarioActivity.class);
        startActivity(intent_Scenario);
    }

    //カメラ画面に遷移
    public void Home_Camera(View v) {
        Intent intent_Camera = new Intent(getApplication(), CameraActivity.class);
        startActivity(intent_Camera);
    }
}
