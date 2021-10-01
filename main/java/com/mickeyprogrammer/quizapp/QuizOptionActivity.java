package com.mickeyprogrammer.quizapp;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class QuizOptionActivity extends Activity{
Button btnAdmin,btnStart;

//    int val=0;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_option);

        btnAdmin=findViewById(R.id.check_admin_btn_id);
        btnStart=findViewById(R.id.start_user_btn_id);
        builder = new AlertDialog.Builder(this);

        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getApplicationContext(),AdminAuthentication.class);
//                intent.putExtra("userType","admin");
                startActivity(intent);
finish();

            }
        });
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    SharedPreferences getshared=getSharedPreferences("QuizApp",MODE_PRIVATE);
                    String tempUser=getshared.getString("userName","noUser");

                    if(tempUser.equals("noUser")){
                        Intent intent=new Intent(getApplicationContext(), enterUserNameActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else {

                        SharedPreferences sharedPref = getSharedPreferences("QuizApp", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString("subject_submit", "nill");
                        editor.apply();
                        Intent intent = new Intent(getApplicationContext(), typeHardnestgame.class);
//                    intent.putExtra("userType","user");

                        startActivity(intent);


                    }






            }
        });

    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Do you want to Exit QuizApp?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
                finish();
            }
        });
        builder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        AlertDialog alert=builder.create();
        alert.show();
    }





}