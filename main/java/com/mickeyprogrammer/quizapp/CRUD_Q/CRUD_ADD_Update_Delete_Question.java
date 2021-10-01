package com.mickeyprogrammer.quizapp.CRUD_Q;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mickeyprogrammer.quizapp.Question;
import com.mickeyprogrammer.quizapp.R;

public class CRUD_ADD_Update_Delete_Question extends AppCompatActivity {
    // creating variables for
    // EditText and buttons.
    String btn_type=null;
    private EditText question_id,questionEnter, option1, option2,option3,option4,correctAns;
    TextView sub_head;
    private Button sendDatabtn;
private TextView subject_id;

    // creating a variable for our
    // Firebase Database.
    FirebaseDatabase firebaseDatabase;

    // creating a variable for our Database
    // Reference for Firebase.
    DatabaseReference databaseReference;

    // creating a variable for
    // our object class
    Question questionInfo=null;
    String subjectType=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_question);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
//        String subjectType=  bundle.get("keyButton").toString();
        SharedPreferences sharedPref = getSharedPreferences("QuizApp", Context.MODE_PRIVATE);;
       subjectType=sharedPref.getString("CRUD_Subject","Nill");
        // initializing our edittext and button
// private EditText questionEnter, option1, option2,option3,option4,correctAns;
//    private Button sendDatabtn;
//        check btn type add update or delete
        btn_type=bundle.get("btn_type").toString();


        question_id=findViewById(R.id.id_question);
        questionEnter=findViewById(R.id.id_enter_question);
        option1=findViewById(R.id.id_option1);
        option2=findViewById(R.id.id_option2);
        option3=findViewById(R.id.id_option3);
        option4=findViewById(R.id.id_option4);
        correctAns=findViewById(R.id.id_correctAns);
        sendDatabtn=findViewById(R.id.sendDatabutton);
        subject_id=findViewById(R.id.textView5_subject_id);
        sub_head=findViewById(R.id.textView4_subheading_id);
        //set all  field
        if(btn_type.equals("update")){
        question_id.setText( bundle.get("Id").toString());
            question_id.setEnabled(false);
        questionEnter.setText( bundle.get("question").toString());
        option1.setText( bundle.get("option1").toString());
        option2.setText( bundle.get("option2").toString());
        option3.setText( bundle.get("option3").toString());
        option4.setText( bundle.get("option4").toString());
        correctAns.setText( bundle.get("correctAns").toString());
        sendDatabtn.setText(btn_type);
            sub_head.setText(" Confirm Update Please!! ");

        }
        //get delete intent data
        if(btn_type.equals("delete")){
            question_id.setText( bundle.get("Id").toString());
            questionEnter.setText( bundle.get("question").toString());
            option1.setText( bundle.get("option1").toString());
            option2.setText( bundle.get("option2").toString());
            option3.setText( bundle.get("option3").toString());
            option4.setText( bundle.get("option4").toString());
            correctAns.setText( bundle.get("correctAns").toString());
            sendDatabtn.setText(btn_type);
            question_id.setEnabled(false);
            questionEnter.setEnabled(false);
            option1.setEnabled(false);
            option2.setEnabled(false);
            option3.setEnabled(false);
            option4.setEnabled(false);
            correctAns.setEnabled(false);

            sub_head.setText(" Confirm delete Please!! ");

        }

        subject_id.setText(subjectType);
        // below line is used to get the
        // instance of our FIrebase database.


        // below line is used to get reference for our database.


        // initializing our object
        // class variable.
        questionInfo = new Question();

//        sendDatabtn = findViewById(R.id.idBtnSendData);

        // adding on click listener for our button.
        sendDatabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // getting text from our edittext fields.


//                 below line is for checking weather the
//                 edittext fields are empty or not.

                if(btn_type.equals("delete")){
                    deleteDataFireBase(Integer.parseInt(question_id.getText().toString()));

                    Intent   intent = new Intent(getApplicationContext(), RecyclerView_CRUD_Questions_option.class);
                    intent.putExtra("keyButton",subjectType);
                    startActivity(intent);
                    finish();
                }
               else if(btn_type.equals("update")||btn_type.equals("add")){
                if (
                        question_id.getText().toString().isEmpty()||
                                questionEnter.getText().toString().isEmpty() ||
                                questionEnter.getText().toString().isEmpty()||
                                questionEnter.getText().toString().isEmpty()||
                                questionEnter.getText().toString().isEmpty()||
                                questionEnter.getText().toString().isEmpty()||
                        correctAns.getText().toString().isEmpty()
                        ) {
                    // if the text fields are empty
                    // then show the below message.
                    Toast.makeText(getApplicationContext(), "Please complete  your add  data some thing missing.", Toast.LENGTH_SHORT).show();
                } else {
                    // else call the method to add
                    // data to our database.
                int id=Integer.parseInt(question_id.getText().toString());

                String questionE=questionEnter.getText().toString();
                String op1=option1.getText().toString();
                String op2=option2.getText().toString();
                String op3=option3.getText().toString();
                String op4=option4.getText().toString();
                int corrAns=Integer.parseInt(correctAns.getText().toString());
                    Toast.makeText( getApplicationContext(), "id value is "+ id+" Ques   "+questionE

                            , Toast.LENGTH_SHORT).show();



                        addDatatoFirebase(id, questionE, op1, op2, op3, op4, corrAns);
                    Intent   intent = new Intent(getApplicationContext(), RecyclerView_CRUD_Questions_option.class);
                    intent.putExtra("keyButton",subjectType);

                    startActivity(intent);
                    finish();

                }
                }
//refresh recyclerView


            }

        });
    }
private  void deleteDataFireBase(int id){
    firebaseDatabase = FirebaseDatabase.getInstance();
    databaseReference = firebaseDatabase.getReference("QuizApp").child(subjectType);
    databaseReference.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            if( databaseReference.child("Question"+id) != null){
                String msg =  databaseReference.child("Question"+id).toString();
                Toast.makeText(getApplicationContext(), "data deleted"+msg , Toast.LENGTH_LONG).show();
                databaseReference.child("Question"+id).removeValue();


            }else {
                Toast.makeText(getApplicationContext(), " no data found in DB  " , Toast.LENGTH_LONG).show();
            }

        }
/// inside the method of on Data change we are setting
        // our object class to our database reference.
        // data base reference will sends data to firebase.


        // after adding this data we are showing toast message.
//

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            Toast.makeText(getApplicationContext(), "no  data added", Toast.LENGTH_SHORT).show();
        }
    });

}
    private void addDatatoFirebase(int id,String questionE, String op1, String op2,String op3, String op4, int corrAns) {
        // below 3 lines of code is used to set
        // data in our object class.

        questionInfo.setId(id);
        questionInfo.setQuestion(questionE);
        questionInfo.setOption1(op1);
        questionInfo.setOption2(op2);
        questionInfo.setOption3(op3);
        questionInfo.setOption4(op4);
        questionInfo.setCorrectAns(corrAns);
        // we are use add value event listener method
        // which is called with database reference.
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("QuizApp").child(subjectType);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


//                databaseReference.push().setValue(questionInfo);

                 databaseReference.child("Question"+questionInfo.getId()).setValue(questionInfo);
                Toast.makeText(getApplicationContext(), "data added", Toast.LENGTH_LONG).show();

            }
/// inside the method of on Data change we are setting
            // our object class to our database reference.
            // data base reference will sends data to firebase.


            // after adding this data we are showing toast message.
//

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "no  data added", Toast.LENGTH_SHORT).show();
            }
        });
    }
}