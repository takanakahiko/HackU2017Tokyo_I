package com.example.takanakahiko.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class ScenarioActivity extends AppCompatActivity {

    //シナリオ開放処理用
    boolean Scenario_TorF[] = {true, true, true, false, false, false, false, false};
    int Scenario[] = {R.id.Scenario1,
            R.id.Scenario2,
            R.id.Scenario3,
            R.id.Scenario4,
            R.id.Scenario5,
            R.id.Scenario6,
            R.id.Scenario7,
            R.id.Scenario8};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenario);

        //未開放シナリオボタン非表示
        for (int i = 0; i < 8; i++) {
            if (Scenario_TorF[i] == false) {
                Button button = (Button) findViewById(Scenario[i]);
                button.setText("未開放");
            }
        }

        Button Scenario1 = (Button) findViewById(R.id.Scenario1);
        Scenario1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), StoryActivity.class);
                intent.putExtra("storyFile", "TextAssets.txt");
                startActivity(intent);
            }
        });

        Button Scenario2 = (Button) findViewById(R.id.Scenario2);
        Scenario2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), StoryActivity.class);
                intent.putExtra("storyFile", "TextAssets.txt");
                startActivity(intent);
            }
        });

        Button Scenario3 = (Button) findViewById(R.id.Scenario3);
        Scenario3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), StoryActivity.class);
                intent.putExtra("storyFile", "TextAssets.txt");
                startActivity(intent);
            }
        });

        Button Scenario4 = (Button) findViewById(R.id.Scenario4);
        Scenario4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), StoryActivity.class);
                intent.putExtra("storyFile", "TextAssets.txt");
                startActivity(intent);
            }
        });

        Button Scenario5 = (Button) findViewById(R.id.Scenario5);
        Scenario5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), StoryActivity.class);
                intent.putExtra("storyFile", "TextAssets.txt");
                startActivity(intent);
            }
        });

        Button Scenario6 = (Button) findViewById(R.id.Scenario6);
        Scenario6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), StoryActivity.class);
                intent.putExtra("storyFile", "TextAssets.txt");
                startActivity(intent);
            }
        });

        Button Scenario7 = (Button) findViewById(R.id.Scenario7);
        Scenario7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), StoryActivity.class);
                intent.putExtra("storyFile", "TextAssets.txt");
                startActivity(intent);
            }
        });

        Button Scenario8 = (Button) findViewById(R.id.Scenario8);
        Scenario8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), StoryActivity.class);
                intent.putExtra("storyFile", "TextAssets.txt");
                startActivity(intent);
            }
        });


        Button Scenario_Home = (Button) findViewById(R.id.Scenario_Home);
        Scenario_Home.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {finish();}
        });
    }
}