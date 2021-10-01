package com.mickeyprogrammer.quizapp;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.mickeyprogrammer.quizapp.CRUD_Q.RecyclerView_CRUD_Questions_option;

public class UserPageLayoutActivity extends AppCompatActivity {
Button  Islamic_btn,pakStudy_btn, Java_btn, html_btn,GK_btn, science_btn,checkPositionBtn;
TextView txt1,txt2;
    String userType="";
    Intent intent=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.user_page_layout);
//check userType then perform action on button
        Intent intent1=getIntent();
        Bundle bundle=intent1.getExtras();
         userType=  bundle.get("userType").toString();

        SharedPreferences sharedPref = getSharedPreferences("QuizApp", Context.MODE_PRIVATE);
      String subject_restart=  sharedPref.getString("subject_submit","nill");


//        SharedPreferences getShared = getSharedPreferences("QuizApp", Context.MODE_PRIVATE);

//        String userName1 = (String) bundle.get(userName).toString();
//        Toast.makeText(getApplicationContext(), "check user name fron shared prefrence " +
//                getShared.getString("Name","nooo founnnd")+"user Type is "+userType, Toast.LENGTH_SHORT).show();



        Islamic_btn=findViewById(R.id.Islamic_btn);
        pakStudy_btn=findViewById(R.id.pakStudy_btn);
        Java_btn=findViewById(R.id.Math_btn);
        html_btn=findViewById(R.id.physics_btn);
        GK_btn=findViewById(R.id.GK_btn);
        science_btn =findViewById(R.id.chemistry_btn);
        txt1=findViewById(R.id.textView_subjectRestart);
        txt2=findViewById(R.id.textView4_time);
checkPositionBtn=findViewById(R.id.position_btn);

        if(!subject_restart.equals("nill")){
            txt1.setText(subject_restart +" quiz restart in : ");
            if(subject_restart.equals("islam")){
Islamic_btn.setVisibility(View.GONE);
            }
            else if(subject_restart.equals("pakStudy")){
                pakStudy_btn.setVisibility(View.GONE);

            }
            else if(subject_restart.equals("java")){
                Java_btn.setVisibility(View.GONE);
            }
            else if(subject_restart.equals("html")){
                html_btn.setVisibility(View.GONE);
            }
            else if(subject_restart.equals("science")){
                science_btn.setVisibility(View.GONE);
            }
            else if(subject_restart.equals("gk")){
                GK_btn.setVisibility(View.GONE);
            }

//set count
            new CountDownTimer(30000, 1000) {

                public void onTick(long millisUntilFinished) {
                    txt2.setText((int) (millisUntilFinished / 1000)+"s");
                }

                public void onFinish() {
                    txt2.setText("");
                    txt1.setText("");
                    Islamic_btn.setVisibility(View.VISIBLE);
                    pakStudy_btn.setVisibility(View.VISIBLE);
                    Java_btn.setVisibility(View.VISIBLE);
                    html_btn.setVisibility(View.VISIBLE);
                    science_btn.setVisibility(View.VISIBLE);
                    GK_btn.setVisibility(View.VISIBLE);
                    SharedPreferences sharedPref = getSharedPreferences("QuizApp", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("subject_submit","nill");
                    editor.apply();
                }
            }.start();

        }
        Islamic_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userType.equals("user")) {
                     intent = new Intent(getApplicationContext(), QuestionsActivity.class);

                } else if (userType.equals("admin")) {
                     intent = new Intent(getApplicationContext(), RecyclerView_CRUD_Questions_option.class);



                }
                intent.putExtra("keyButton", "Islam");

                startActivity(intent);
            }
        });

        pakStudy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(userType.equals("user")){
           intent=new Intent(getApplicationContext(),QuestionsActivity.class);

                }
                else if (userType.equals("admin")) {
                    intent = new Intent(getApplicationContext(), RecyclerView_CRUD_Questions_option.class);
                }
                intent.putExtra("keyButton","PakStudy");
                startActivity(intent);
            }
        });
        Java_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(userType.equals("user")){
            intent=new Intent(getApplicationContext(),QuestionsActivity.class);

                }
                else if (userType.equals("admin")) {
                    intent = new Intent(getApplicationContext(), RecyclerView_CRUD_Questions_option.class);
                }
                intent.putExtra("keyButton","Java");
                startActivity(intent);
            }
        });
        html_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(userType.equals("user")){
         intent=new Intent(getApplicationContext(),QuestionsActivity.class);

                }
                else if (userType.equals("admin")) {
                    intent = new Intent(getApplicationContext(), RecyclerView_CRUD_Questions_option.class);
                }
                intent.putExtra("keyButton","Html");
                startActivity(intent);
            }
        });
        GK_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(userType.equals("user")){
                intent=new Intent(getApplicationContext(),QuestionsActivity.class);

                }
                else if (userType.equals("admin")) {
                    intent = new Intent(getApplicationContext(), RecyclerView_CRUD_Questions_option.class);
                }
                intent.putExtra("keyButton","GK");
                startActivity(intent);
            }
        });
        science_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(userType.equals("user")){
                     intent=new Intent(getApplicationContext(),QuestionsActivity.class);

                }
                else if (userType.equals("admin")) {
                    intent = new Intent(getApplicationContext(), RecyclerView_CRUD_Questions_option.class);
                }
                intent.putExtra("keyButton","Science");
                startActivity(intent);
            }
        });

        checkPositionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),recyclerView_position.class);
                startActivity(intent);
            }
        });

    }
}