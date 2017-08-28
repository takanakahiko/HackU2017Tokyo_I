package com.example.takanakahiko.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class ScenarioActivity extends AppCompatActivity {

    //シナリオ開放処理用
    boolean Scenario_TorF[] = {true,true,true,false,false,false,false,false};
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
        for(int i = 0;i < 8;i++){
            if(Scenario_TorF[i] == false){
                Button button = (Button)findViewById(Scenario[i]);
                button.setText("未開放");
            }
        }
    }

    //メイン画面に遷移
    public void Scenario_Home(View v){
        finish();
    }

    //それぞれのシナリオ画面に遷移
    //public void Scenario1(View v){
        //Intent intent1 = new Intent(getApplication(),シナリオ1.class);
        //startActivity(intent1);
    //}

    //public void Scenario2(View v){
    //Intent intent1 = new Intent(getApplication(),シナリオ2.class);
    //startActivity(intent2);
    //}

    //public void Scenario3(View v){
    //Intent intent1 = new Intent(getApplication(),シナリオ3.class);
    //startActivity(intent3);
    //}

    //public void Scenario4(View v){
    //Intent intent1 = new Intent(getApplication(),シナリオ4.class);
    //startActivity(intent4);
    //}

    //public void Scenario5(View v){
    //Intent intent1 = new Intent(getApplication(),シナリオ5.class);
    //startActivity(intent5);
    //}

    //public void Scenario6(View v){
    //Intent intent1 = new Intent(getApplication(),シナリオ6.class);
    //startActivity(intent6);
    //}

    //public void Scenario7(View v){
    //Intent intent1 = new Intent(getApplication(),シナリオ7.class);
    //startActivity(intent7);
    //}

    //public void Scenario8(View v){
    //Intent intent1 = new Intent(getApplication(),シナリオ8.class);
    //startActivity(intent8);
    //}

}
