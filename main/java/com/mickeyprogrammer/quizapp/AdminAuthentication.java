package com.mickeyprogrammer.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AdminAuthentication extends AppCompatActivity {
Button submit;
TextView userName,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_authentication);
        submit=findViewById(R.id.button_submit_admin_login);
        userName=findViewById(R.id.id_admin_name);
        pass=findViewById(R.id.id_password);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!userName.getText().toString().equals("admin")){
                    userName.setError("Your UserName is incorrect please Try Again!! ");
                }
                if(!pass.getText().toString().equals("54321UBAID$$$")){
                    pass.setError("Your password is incorrect please Try Again!! ");
                }
                 if(userName.getText().toString().equals("admin")&&pass.getText().toString().equals("54321UBAID$$$")){
                    Intent intent=new Intent(getApplicationContext(),UserPageLayoutActivity.class);
                    intent.putExtra("userType","admin");
                    startActivity(intent);
                    finish();
                }

            }
        });
    }
}