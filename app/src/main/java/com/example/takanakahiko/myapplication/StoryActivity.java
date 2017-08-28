package com.example.takanakahiko.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StoryActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private SoundPool mSoundPool;
    private Map<String, Integer> soundIds = new HashMap<>();
    private Map<String, Integer> imageIds = new HashMap<>();

    private ArrayList<String> list = new ArrayList<>();
    int step = 0;

    TextView textLabel, nameLabel;
    ImageView imageViews[] = new ImageView[3];

    String storyFile = "TextAssets.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        // MainActivity からのデータを受け取る
        Intent intent = getIntent();
        storyFile = intent.getStringExtra("storyFile");

        imageViews[0] = (ImageView)findViewById(R.id.imageView0);
        imageViews[1] = (ImageView)findViewById(R.id.imageView1);
        imageViews[2] = (ImageView)findViewById(R.id.imageView2);

        textLabel = (TextView) this.findViewById(R.id.mainText);
        nameLabel = (TextView) this.findViewById(R.id.nameText);

        readFile();
        loadResource();
    }

    @Override
    protected void onResume(){
        super.onResume();

        next_event();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSoundPool.release();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("TouchEvent", "X:" + event.getX() + ",Y:" + event.getY());
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                next_event();
                break;
        }
        return true;
    }


    protected void readFile(){
        InputStream is;
        BufferedReader br;
        AssetManager as = getResources().getAssets();
        try{
            is = as.open(storyFile);
            br = new BufferedReader(new InputStreamReader(is));
            String str;
            while ((str = br.readLine()) != null) {
                list.add(str);
            }
        } catch (IOException e) {
            Toast.makeText(this, "読み込み失敗", Toast.LENGTH_SHORT).show();
        }
    }

    protected void loadResource(){
        int maxStreams=1;
        int streamType= AudioManager.STREAM_MUSIC;
        int srcQuality = 0;
        mSoundPool=new SoundPool(maxStreams,streamType,srcQuality);

        Context context = this;

        while(true) {
            String row = list.get(step);
            step++;
            if (row.contains("[SoundDef]")) {
                String words[] = row.split(" ");
                String filename = words[1];
                try {
                    R.raw rRaw = new R.raw();
                    Field field = rRaw.getClass().getField(filename);
                    int resId = field.getInt(rRaw);
                    int id = mSoundPool.load(context, resId, 0);
                    Log.d(TAG, "Id " + id);
                    soundIds.put( filename, id );
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }else if(row.contains("[ImageDef]")){
                String words[] = row.split(" ");
                String filename = words[1];
                try {
                    R.drawable rDrawable = new R.drawable();
                    Field field = rDrawable.getClass().getField(filename);
                    int resId = field.getInt(rDrawable);
                    imageIds.put( filename, resId );
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }else {
                break;
            }
        }
    }

    private void next_event(){
        float leftVolume = 1.0f; // 左のスピーカーからの再生音量。(0.0〜1.0)
        float rightVolume = 1.0f; // 右のスピーカーからの再生音量。(0.0〜1.0)
        int priority = 0; // プライオリティ（0が一番優先度が高い）
        int loop = 0; // ループ回数（-1の場合は無限にループ、0の場合はループしない）
        float rate = 1.0f; // 再生速度（0.5〜2.0：0.5倍から2倍の速度まで設定できる）

        if(step>=list.size()) {
            finish();
            return;
        }

        String row;
        while(true) {
            row = list.get(step);
            step++;
            if (row.contains("[Sound]")) {
                String words[] = row.split(" ");
                String fileName = words[1];
                mSoundPool.play(soundIds.get(fileName), leftVolume, rightVolume, priority, loop, rate);
            }else if(row.contains("[Image]")) {
                String words[] = row.split(" ");
                int imageViewNum = Integer.parseInt(words[1]);
                String fileName = words[2];
                if (fileName.equals("null")) {
                    imageViews[imageViewNum].setImageBitmap(null);
                } else {
                    imageViews[imageViewNum].setImageResource(imageIds.get(fileName));
                }
            }else if(row.contains("[Name]")){
                String words[] = row.split(" ");
                String name = words[1];
                nameLabel.setText(name);
            }else {
                textLabel.setText(row);
                break;
            }
        }
    }
}
