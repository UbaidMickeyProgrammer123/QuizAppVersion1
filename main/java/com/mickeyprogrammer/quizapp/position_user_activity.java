package com.mickeyprogrammer.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class position_user_activity extends AppCompatActivity {
Button res;
    TextView position1,position2,position3;
    @Override
    protected void
    onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_position_user);

        res=findViewById(R.id.restart_prizes_id);
        position1=findViewById(R.id.textView4_po1);
        position2=findViewById(R.id.textView4_po2);
        position3=findViewById(R.id.textView4_po3);


        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        String name=bundle.getString("name");
        int correctAnswer=bundle.getInt("correctAns",0);
        int totalQ=bundle.getInt("totalQ",0);
        Toast.makeText(getApplicationContext(), "Value    "+name+"     "+correctAnswer+"       "+totalQ, Toast.LENGTH_SHORT).show();




        SharedPreferences getSharedPref = getSharedPreferences("quizApp",MODE_PRIVATE);
        String firstcheck=getSharedPref.getString("firstTimeApp","0");
        if(firstcheck=="0"){
//
            Toast.makeText(getApplicationContext(), "First time result check default value", Toast.LENGTH_SHORT).show();

//            String firstcheck=sharedPref.getString("firstTimeApp","0");
            SharedPreferences setSharedPref = getSharedPreferences("quizApp",MODE_PRIVATE);
            SharedPreferences.Editor editor = setSharedPref.edit();
            editor.putString("firstTimeApp","1");
            editor.putString("1st_Name","Zaidii");
            editor.putInt("1st_Score",totalQ);

            editor.putString("2nd_Name","Aqsa");
            editor.putInt("2nd_Score",totalQ-2);
            editor.putString("3rd_Name","Hammad");
            editor.putInt("3rd_Score",totalQ-3);

            editor.putInt("total_Questions",totalQ);
//            int first=getSharedPref.getInt("1st_Score",111);
//            int second=getSharedPref.getInt("2nd_Score",222);
//            int third=getSharedPref.getInt("3rd_Score",333);
            editor.apply();

            position1.setText(getSharedPref.getString("1st_Name","Nill")
                    +"   "+getSharedPref.getInt("1st_Score",00)+"/"+
                    getSharedPref.getInt("total_Questions",000));

            position2.setText(getSharedPref.getString("2nd_Name","Nill")
                    +"   "+getSharedPref.getInt("2nd_Score",00)+"/"+
                    getSharedPref.getInt("total_Questions",000));

            position3.setText(getSharedPref.getString("3rd_Name","Nill")
                    +"   "+getSharedPref.getInt("3rd_Score",00)+"/"+
                    getSharedPref.getInt("total_Questions",000));

        }
        else{

            int first=getSharedPref.getInt("1st_Score",111);
            int second=getSharedPref.getInt("2nd_Score",222);
            int third=getSharedPref.getInt("3rd_Score",333);
                            if(correctAnswer>=first){
                                 position1.setText(name+"   "+correctAnswer+"/"+totalQ);
                            }
                            else  if(correctAnswer>=second){
                                position2.setText(name+"   "+correctAnswer+"/"+totalQ);
                            }
                            else  if(correctAnswer>=third){
                                position2.setText(name+"     "+correctAnswer+"/"+totalQ);
                            }
            Toast.makeText(getApplicationContext(), " No First time result check default value", Toast.LENGTH_SHORT).show();
        }
res.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent1=new Intent(getApplicationContext(), enterUserNameActivity.class);
        startActivity(intent1);
        finish();
    }
});

    }
}