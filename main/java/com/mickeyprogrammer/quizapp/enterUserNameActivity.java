package com.mickeyprogrammer.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class enterUserNameActivity extends AppCompatActivity {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private  DatabaseReference databaseReference = database.getReference();
Button btn1;
EditText ed1;
    int checkUser = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_user_name);


btn1=findViewById(R.id.buttonStart);
ed1=findViewById(R.id.id_name);
btn1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
//        Toast.makeText(MainActivity.this, "check please enter your name first", Toast.LENGTH_LONG).show();
if(ed1.getText().toString().isEmpty()){
    Toast.makeText(enterUserNameActivity.this, "check please enter your name first", Toast.LENGTH_LONG).show();
}
else {
//    Toast.makeText(getApplicationContext(), "input name in enter name activity   "+ ed1.getText().toString(), Toast.LENGTH_LONG).show();

    checkUserNameDataFirebase(ed1.getText().toString());

}
    }
});

    }

    public   void checkUserNameDataFirebase(String name) {
        checkUser = 0;
         FirebaseDatabase database = FirebaseDatabase.getInstance();
         DatabaseReference databaseReference = database.getReference();

        databaseReference.child("User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterable<DataSnapshot> children = snapshot.getChildren();
//                Toast.makeText(getApplicationContext(), "Total objects " +children, Toast.LENGTH_LONG).show();
                // shake hands with each of them.'
//                Toast.makeText(getApplicationContext(), "user check in database out of for loop  ", Toast.LENGTH_SHORT).show();

                for (DataSnapshot child : children) {

                    UserQuiz users = child.getValue(UserQuiz.class);
//                    Toast.makeText(getApplicationContext(), "user check in database  "+users.userName, Toast.LENGTH_SHORT).show();

                    if (name.equals( users.userName)) {
                        checkUser = 3;
//                     Toast.makeText(getApplicationContext(), "user match in database ", Toast.LENGTH_SHORT).show();
                        TextInputLayout til = (TextInputLayout) findViewById(R.id.input_layout_id);
                        til.setError("This name already exit please select unique name !! ");
                    }


                }
                if (checkUser == 0) {
//                    Toast.makeText(getApplicationContext(), "user Not match in database ", Toast.LENGTH_SHORT).show();
                    SharedPreferences sharedPref = getSharedPreferences("QuizApp", Context.MODE_PRIVATE);;
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("userName",ed1.getText().toString());
                    editor.apply();
             Intent intent=new Intent(getApplicationContext(),typeHardnestgame.class);
//            intent.putExtra("userType","user");
            //create user in below line
            create_user(ed1.getText().toString());

            startActivity(intent);
               finish();


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    public void create_user(String user){

        FirebaseDatabase firebaseDatabase2 = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference2 = firebaseDatabase2.getReference();

        databaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserQuiz userQuiz=new UserQuiz();
                userQuiz.setUserName(user);
                databaseReference2.child("User").child("User: "+user).setValue(userQuiz);
                Toast.makeText(getApplicationContext(), "User Created!!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}