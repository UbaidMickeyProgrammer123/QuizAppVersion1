package com.mickeyprogrammer.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class typeHardnestgame extends AppCompatActivity {
Button btn_easy,btn_medium,btn_hard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_hardnestgame);

        btn_easy=findViewById(R.id.easy_btn);
        btn_medium=findViewById(R.id.medium_btn);
        btn_hard=findViewById(R.id.hard_btn);
        btn_easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),UserPageLayoutActivity.class);
                intent.putExtra("userType","user");
                SharedPreferences sharedPref = getSharedPreferences("QuizApp", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("selectDificulty",60000);
                editor.apply();
                startActivity(intent);
                finish();
            }
        });
        btn_medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),UserPageLayoutActivity.class);
                intent.putExtra("userType","user");
                SharedPreferences sharedPref = getSharedPreferences("QuizApp", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("selectDificulty",30000);
                editor.apply();
                startActivity(intent);
                finish();
            }
        });
        btn_hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),UserPageLayoutActivity.class);
                intent.putExtra("userType","user");
//                intent.putExtra("selectDificulty",60);
                SharedPreferences sharedPref = getSharedPreferences("QuizApp", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("selectDificulty",10000);
                editor.apply();
                startActivity(intent);
                finish();
            }
        });
    }
}