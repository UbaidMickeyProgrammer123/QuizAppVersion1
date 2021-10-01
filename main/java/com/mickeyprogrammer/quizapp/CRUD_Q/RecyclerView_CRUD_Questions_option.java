package com.mickeyprogrammer.quizapp.CRUD_Q;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mickeyprogrammer.quizapp.Question;
import com.mickeyprogrammer.quizapp.R;

import java.util.ArrayList;

public class RecyclerView_CRUD_Questions_option extends AppCompatActivity {
RecyclerView recyclerView;
TextView textView;
FloatingActionButton float_Add_btn;
    String subjectType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_crud_questions_option);
        recyclerView=findViewById(R.id.recyclerView_Subject_Question_id);
        textView=findViewById(R.id.textView_subjectName_id);
        float_Add_btn=findViewById(R.id.floatingActionButton2);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        subjectType=  bundle.get("keyButton").toString();
        textView.setText(subjectType);
        getDataFirebase(subjectType);
        SharedPreferences sharedPref = getSharedPreferences("QuizApp", Context.MODE_PRIVATE);;
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("CRUD_Subject",subjectType);
        editor.apply();

        float_Add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(), CRUD_ADD_Update_Delete_Question.class);
                intent.putExtra("btn_type","add");

                startActivity(intent);
                finish();
            }
        });
    }

    private   void getDataFirebase(String subjectType){

        ArrayList<Question> arrayListTemp=new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference();
        databaseReference.child("QuizApp").child(subjectType).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterable<DataSnapshot> children = snapshot.getChildren();
//                Toast.makeText(getApplicationContext(), "Total objects " +children, Toast.LENGTH_LONG).show();
                // shake hands with each of them.'
                for (DataSnapshot child : children) {

                    Question question = child.getValue(Question.class);
//                    Toast.makeText(getApplicationContext(), "Object id call "+question.getId(), Toast.LENGTH_SHORT).show();
                    arrayListTemp.add(new Question(
                            question.getId(),
                            question.getQuestion() ,
                            question.getOption1(),
                            question.getOption2(), question.getOption3(), question.getOption4(),

                            R.drawable.think,question.getCorrectAns()));


                }
                setdata(arrayListTemp);

            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });






    }
    private void setdata(ArrayList<Question> arrayList) {
        CRUD_CustumAdapter crudAdapter =new CRUD_CustumAdapter(arrayList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(RecyclerView_CRUD_Questions_option.this));
        recyclerView.setAdapter(crudAdapter);

    }
}