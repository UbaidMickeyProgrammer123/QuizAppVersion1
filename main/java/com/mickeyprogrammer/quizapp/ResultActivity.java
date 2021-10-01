package com.mickeyprogrammer.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ResultActivity extends AppCompatActivity {
TextView congrs,username,score;
int totalQ=0;
int count_in_resultActivity=1;
int correctA=0;
    String user="";
    String subject="";
ImageView imageView1,imageView2;
Button start_next_quiz_btn_id=null;
Button check_position_btn_id=null;
int update_count=0;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
//    private boolean alreadyExecuted=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        congrs=findViewById(R.id.textView3_congragulation);
        username=findViewById(R.id.textView4_username);
        score=findViewById(R.id.textView5_score);
        imageView1=findViewById(R.id.result_image);
        imageView2=findViewById(R.id.position_image);
        start_next_quiz_btn_id=findViewById(R.id.start_next_quiz_btn_id);
//        check_position_btn_id=findViewById(R.id.check_your_position_btn_id);
         firebaseDatabase=FirebaseDatabase.getInstance();;

        // creating a variable for our Database
        // Reference for Firebase.
         databaseReference = firebaseDatabase.getReference();
        SharedPreferences getShared = getSharedPreferences("QuizApp", Context.MODE_PRIVATE);
        user= getShared.getString("userName","user error");
        username.setText(user);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();

        subject=bundle.get("subjectType").toString();


         totalQ= bundle.getInt("totalQuestions",0);
         correctA= bundle.getInt("correctAnswer",0);
//        update_count++;



        if(correctA > (totalQ/2)){
            congrs.setText("Hey Congragulation!!!");
            score.setText("Your score is "+correctA+" out of "+totalQ+"  in "+subject);
            imageView1.setImageResource(R.drawable.trophy3);
            imageView2.setImageResource(R.drawable.ground);


        }
        else{
            congrs.setText("Better Luck next time try again!!!");
            score.setText("Your score is "+correctA+" out of "+totalQ+"  in "+subject);
            imageView1.setImageResource(R.drawable.fail);
            imageView2.setImageResource(R.drawable.ground);
        }

//        if(!alreadyExecuted) {
//
//            alreadyExecuted = true;
//        }

        start_next_quiz_btn_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                update_user();
                Intent intent1=new Intent(getApplicationContext(), UserPageLayoutActivity.class);
                intent1.putExtra("userType","user");
                SharedPreferences sharedPref = getSharedPreferences("QuizApp", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("subject_submit",subject);
                editor.apply();
                startActivity(intent1);

            }
        });

    }



}