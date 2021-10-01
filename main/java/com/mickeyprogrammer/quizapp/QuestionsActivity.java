package com.mickeyprogrammer.quizapp;




import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class QuestionsActivity extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    CountDownTimer countDownTimer;

    // creating a variable for our Database
    // Reference for Firebase.
    DatabaseReference databaseReference = firebaseDatabase.getReference();
//    int sizeArray=0;

//    public  String correctAnswer;
//    public  String  totalQuestions;
    Button nextBtn,previousBtn;
    int dificultType;
TextView textViewQuestion;
//ImageView imageView;
    TextView countdown;
ProgressBar progressBar;
TextView textViewProgress,op1,op2,op3,op4;
Button buttonSubmit;
int correctAnsCount=0;
int selectedOptionPosition=0;
    int currentPosition=0;
    boolean btn_check_after_correct_answer=false;
    ArrayList<Question> questionArrayList=new ArrayList<Question>();
    ///attribute for solved answer
    int []solvedMarkQuestionArray ;
    int countSolvedAnwer=0;
    String   subjectType="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //create timer

        //end timer
        SharedPreferences sharedPref = getSharedPreferences("QuizApp", Context.MODE_PRIVATE);
         dificultType=sharedPref.getInt("selectDificulty",10000);


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_questions);
        //get intent
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
//        userName1= (String) bundle.get(userName).toString();
    subjectType=  bundle.get("keyButton").toString();



//        Toast.makeText(this, "Intent username "+userName1, Toast.LENGTH_SHORT).show();
//get all ids
        textViewQuestion=findViewById(R.id.text_question);
//        imageView=findViewById(R.id.imageView);
        progressBar=findViewById(R.id.progressBar);
        textViewProgress=findViewById(R.id.textView_progress);
        op1=findViewById(R.id.textView_OP1);
        op2=findViewById(R.id.textView_OP2);
        op3=findViewById(R.id.textView_OP3);
        op4=findViewById(R.id.textView_OP4);
        buttonSubmit=findViewById(R.id.btn_submit);
        nextBtn=findViewById(R.id.button3_next);
        previousBtn=findViewById(R.id.button2_previous);
        countdown=findViewById(R.id.textView4_count_down_10s);

        getDataFirebase();

      op1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        selectedOptionView(op1,1);
    }
       });
        op2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedOptionView(op2,2);
            }
        });
        op3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedOptionView(op3,3);
            }
        });
        op4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedOptionView(op4,4);
            }
        });

buttonSubmit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        countDownTimer.cancel();
               if(countSolvedAnwer==questionArrayList.size()+1){
                   nextBtn.setVisibility(View.GONE);
                   previousBtn.setVisibility(View.GONE);
               }
        if(buttonSubmit.getText().toString().equals("View Result")||
                (buttonSubmit.getText().toString().equals("select option than check answer")
                        &&currentPosition==questionArrayList.size()+1)){
            SharedPreferences getShared = getSharedPreferences("QuizApp", Context.MODE_PRIVATE);
           String user= getShared.getString("userName","user error");
//UserQuiz userQuiz=new UserQuiz();

//            public UserQuiz(String userName, int Islam, int PakStudy, int Java, int Html, int GK, int Science, int totalMarks) {
            if(subjectType.equals("Islam")){
                Intent intent=new Intent(getApplicationContext(),ResultActivity.class);
                intent.putExtra("subjectType","islam");
                intent.putExtra("correctAnswer",correctAnsCount);
                intent.putExtra("totalQuestions",questionArrayList.size());
                update_user(user,"islam",correctAnsCount);
                startActivity(intent);

            }
            else if(subjectType.equals("PakStudy")){

                 Intent intent=new Intent(getApplicationContext(),ResultActivity.class);
                 intent.putExtra("subjectType","pakStudy");
                 intent.putExtra("correctAnswer",correctAnsCount);
                 intent.putExtra("totalQuestions",questionArrayList.size());
                update_user(user,"pakStudy",correctAnsCount);
                 startActivity(intent);

            }
            else  if(subjectType.equals("Java")){

                 Intent intent=new Intent(getApplicationContext(),ResultActivity.class);
                 intent.putExtra("subjectType","java");
                 intent.putExtra("correctAnswer",correctAnsCount);
                 intent.putExtra("totalQuestions",questionArrayList.size());
                update_user(user,"java",correctAnsCount);
                 startActivity(intent);

            }
            else   if(subjectType.equals("Html")){

                 Intent intent=new Intent(getApplicationContext(),ResultActivity.class);
                 intent.putExtra("subjectType","html");
                 intent.putExtra("correctAnswer",correctAnsCount);
                 intent.putExtra("totalQuestions",questionArrayList.size());
                update_user(user,"html",correctAnsCount);

                startActivity(intent);


            }
            else if(subjectType.equals("GK")){

                 Intent intent=new Intent(getApplicationContext(),ResultActivity.class);
                 intent.putExtra("subjectType","gk");
                 intent.putExtra("correctAnswer",correctAnsCount);
                 intent.putExtra("totalQuestions",questionArrayList.size());
                update_user(user,"gk",correctAnsCount);

                startActivity(intent);

            }
            else  if(subjectType.equals("Science")){

                 Intent intent=new Intent(getApplicationContext(),ResultActivity.class);
                 intent.putExtra("subjectType","science");
                 intent.putExtra("correctAnswer",correctAnsCount);
                 intent.putExtra("totalQuestions",questionArrayList.size());
                update_user(user,"science",correctAnsCount);

                startActivity(intent);

            }

            finish();
        }


        if(selectedOptionPosition==0){
            Toast.makeText(getApplicationContext(), "Please Select Option First", Toast.LENGTH_SHORT).show();
        }
              else {
            if (currentPosition < questionArrayList.size() + 1 && !btn_check_after_correct_answer) {
                Question tempQ = questionArrayList.get(currentPosition - 1);
                if (tempQ.correctAns > 4) {
                    Toast.makeText(getApplicationContext(), "Problem in option ", Toast.LENGTH_SHORT).show();
                } else {
                    if (tempQ.correctAns != selectedOptionPosition) {

                        setAnswerView(selectedOptionPosition, R.drawable.failure_bg_text_color);

                    } else {
                        correctAnsCount++;
                    }
                    btn_check_after_correct_answer = true;
                    solvedMarkQuestionArray[currentPosition - 1] = 1;
                    countSolvedAnwer++;
                    setAnswerView(tempQ.correctAns, R.drawable.sucess_bg_text_color);
//                    buttonSubmit.setText("NEXT QUESTION");
                    buttonSubmit.setVisibility(View.GONE);
                    if (btn_check_after_correct_answer && currentPosition == questionArrayList.size() + 1) {
                        buttonSubmit.setText("View Result");
                    } else if (btn_check_after_correct_answer) {
//                        buttonSubmit.setText("NEXT QUESTION");
                        buttonSubmit.setVisibility(View.GONE);
                    } else {
                        buttonSubmit.setText("select option than check answer");
                    }

                    selectedOptionPosition = 0;
                }

            }

        }


    }
});


//next button work
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countDownTimer.cancel();
                buttonSubmit.setVisibility(View.VISIBLE);
                if(selectedOptionPosition==0|| btn_check_after_correct_answer){

                    if(currentPosition<questionArrayList.size()+1){

                        currentPosition++;

                        if(currentPosition==questionArrayList.size()+1){

                            buttonSubmit.setText("View Result");
                            nextBtn.setVisibility(View.GONE);

                        }
                        else{
                            setQuestion();
                            countdown.setText("");
                            countDownTimer.cancel();
                            countDownTimer= new CountDownTimer(dificultType, 1000) {

                                public void onTick(long millisUntilFinished) {
                                    countdown.setText((int) (millisUntilFinished / 1000) + "s");
                                }

                                public void onFinish() {

                                    buttonSubmit.setVisibility(View.VISIBLE);
                                    if (selectedOptionPosition == 0 || btn_check_after_correct_answer) {

                                        if (currentPosition < questionArrayList.size() + 1) {

                                            currentPosition++;

                                            if (currentPosition == questionArrayList.size() + 1) {

                                                buttonSubmit.setText("View Result");
                                                nextBtn.setVisibility(View.GONE);
                                                countdown.setText("00s");

                                            } else {
                                                setQuestion();
                                                countdown.setText("");



                                                if (solvedMarkQuestionArray[currentPosition - 1] == 1) {
                                                    buttonSubmit.setVisibility(View.GONE);

                                                }

//                            if(solvedMarkQuestionArray[currentPosition-1]==1){
//                                buttonSubmit.setVisibility(View.GONE);
//                            }
                                                else {
                                                    if (solvedMarkQuestionArray[currentPosition - 1] == 0) {
                                                        buttonSubmit.setVisibility(View.VISIBLE);
                                                        buttonSubmit.setText("select option than check answer");
                                                    } else if (solvedMarkQuestionArray[currentPosition - 1] == 0) {
                                                        buttonSubmit.setVisibility(View.GONE);
                                                    }

                                                }
                                                btn_check_after_correct_answer = false;
                                            }

                                        }
                                    }
                                    previousBtn.setVisibility(View.VISIBLE);
                                }
                            }.start();




                            if(solvedMarkQuestionArray[currentPosition-1]==1){
                                buttonSubmit.setVisibility(View.GONE);

                            }

//                            if(solvedMarkQuestionArray[currentPosition-1]==1){
//                                buttonSubmit.setVisibility(View.GONE);
//                            }
                            else{
                               if(solvedMarkQuestionArray[currentPosition-1]==0){
                                buttonSubmit.setVisibility(View.VISIBLE);
                            buttonSubmit.setText("select option than check answer");
                               }
                               else if(solvedMarkQuestionArray[currentPosition-1]==0) {
                                   buttonSubmit.setVisibility(View.GONE);
                               }

                            }
                            btn_check_after_correct_answer=false;
                        }

                    }
                }
                previousBtn.setVisibility(View.VISIBLE);
            }
        });
//previoud button work
        previousBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if()
                countDownTimer.cancel();
                if(selectedOptionPosition==0|| btn_check_after_correct_answer){

                    if(currentPosition>0){

                        currentPosition--;
                        if(currentPosition==0){
                            previousBtn.setVisibility(View.GONE);

                        }
                        else{
                            setQuestion();
                            countDownTimer.cancel();
                            countDownTimer= new CountDownTimer(dificultType, 1000) {

                                public void onTick(long millisUntilFinished) {
                                    countdown.setText((int) (millisUntilFinished / 1000) + "s");
                                }

                                public void onFinish() {

                                    buttonSubmit.setVisibility(View.VISIBLE);
                                    if (selectedOptionPosition == 0 || btn_check_after_correct_answer) {

                                        if (currentPosition < questionArrayList.size() + 1) {

                                            currentPosition++;

                                            if (currentPosition == questionArrayList.size() + 1) {

                                                buttonSubmit.setText("View Result");
                                                nextBtn.setVisibility(View.GONE);
                                                countdown.setText("00s");

                                            } else {
                                                setQuestion();
                                                countdown.setText("");



                                                if (solvedMarkQuestionArray[currentPosition - 1] == 1) {
                                                    buttonSubmit.setVisibility(View.GONE);

                                                }

//                            if(solvedMarkQuestionArray[currentPosition-1]==1){
//                                buttonSubmit.setVisibility(View.GONE);
//                            }
                                                else {
                                                    if (solvedMarkQuestionArray[currentPosition - 1] == 0) {
                                                        buttonSubmit.setVisibility(View.VISIBLE);
                                                        buttonSubmit.setText("select option than check answer");
                                                    } else if (solvedMarkQuestionArray[currentPosition - 1] == 0) {
                                                        buttonSubmit.setVisibility(View.GONE);
                                                    }

                                                }
                                                btn_check_after_correct_answer = false;
                                            }

                                        }
                                    }
                                    previousBtn.setVisibility(View.VISIBLE);
                                }
                            }.start();

                            if(solvedMarkQuestionArray[currentPosition-1]==1){
                                buttonSubmit.setVisibility(View.GONE);
                            }

                            else{
                                if(solvedMarkQuestionArray[currentPosition-1]==0){
                                    buttonSubmit.setVisibility(View.VISIBLE);
                                    buttonSubmit.setText("select option than check answer");
                                }
                                else if(solvedMarkQuestionArray[currentPosition-1]==0) {
                                    buttonSubmit.setVisibility(View.GONE);
                                }

                            }
                        }
                        btn_check_after_correct_answer=false;

                    }
                }
                nextBtn.setVisibility(View.VISIBLE);
            }
        });

         countDownTimer= new CountDownTimer(dificultType, 1000) {

            public void onTick(long millisUntilFinished) {
                countdown.setText((int) (millisUntilFinished / 1000) + "s");
            }

            public void onFinish() {

                buttonSubmit.setVisibility(View.VISIBLE);
                if (selectedOptionPosition == 0 || btn_check_after_correct_answer) {

                    if (currentPosition < questionArrayList.size() + 1) {

                        currentPosition++;

                        if (currentPosition == questionArrayList.size() + 1) {

                            buttonSubmit.setText("View Result");
                            nextBtn.setVisibility(View.GONE);
                            countdown.setText("00s");

                        } else {
                            setQuestion();
                            countdown.setText("");



                            if (solvedMarkQuestionArray[currentPosition - 1] == 1) {
                                buttonSubmit.setVisibility(View.GONE);

                            }

//                            if(solvedMarkQuestionArray[currentPosition-1]==1){
//                                buttonSubmit.setVisibility(View.GONE);
//                            }
                            else {
                                if (solvedMarkQuestionArray[currentPosition - 1] == 0) {
                                    buttonSubmit.setVisibility(View.VISIBLE);
                                    buttonSubmit.setText("select option than check answer");
                                } else if (solvedMarkQuestionArray[currentPosition - 1] == 0) {
                                    buttonSubmit.setVisibility(View.GONE);
                                }

                            }
                            btn_check_after_correct_answer = false;
                        }

                    }
                }
                previousBtn.setVisibility(View.VISIBLE);
            }
        }.start();
    }


//setAnswerView
    public void setAnswerView(int answer,int drawableView){
switch (answer){
    case 1:
        op1.setBackground(ContextCompat.getDrawable(this,drawableView));
        break;
    case 2:
        op2.setBackground(ContextCompat.getDrawable(this,drawableView));
        break;
    case 3:
        op3.setBackground(ContextCompat.getDrawable(this,drawableView));
        break;
    case 4:
        op4.setBackground(ContextCompat.getDrawable(this,drawableView));
        break;
    default:
        Log.d("error","some error in setAnswer View");
//        Toast.makeText(this, "some error in setAnswer View", Toast.LENGTH_SHORT).show();
}
    }
public  void setQuestion(){
//    Toast.makeText(getApplicationContext(), "Current position" + currentPosition, Toast.LENGTH_SHORT).show();
    Question questions= questionArrayList.get(currentPosition - 1);
//        Log.d("check","list is "+questions.question);
    setDefaultOption();

    if(btn_check_after_correct_answer&&currentPosition == questionArrayList.size()){
        buttonSubmit.setText("View Result");
        progressBar.setProgress(currentPosition);
    }
    else if(btn_check_after_correct_answer){
//        buttonSubmit.setText("NEXT QUESTION");
        buttonSubmit.setVisibility(View.GONE);
    }
    else{
        buttonSubmit.setText("select option than check answer");
    }

    textViewQuestion.setText(questions.question);
//    imageView.setImageResource(questions.image);
    progressBar.setProgress(currentPosition);
    textViewProgress.setText((currentPosition)+"/"+progressBar.getMax());
    op1.setText(questions.option1);
    op2.setText(questions.option2);
    op3.setText(questions.option3);
    op4.setText(questions.option4);
}
private void setDefaultOption(){
        ArrayList<TextView> options=new ArrayList<>();
        options.add(0,op1);
    options.add(1,op2);
    options.add(2,op3);
    options.add(3,op4);

    for (int i = 0; i <options.size() ; i++) {
options.get(i).setTextColor(Color.parseColor("#665252"));
        options.get(i).setTypeface(Typeface.DEFAULT);
        options.get(i).setBackground(ContextCompat.getDrawable(this,R.drawable.bg_text_color));

    }
}
    public  void selectedOptionView(TextView tv,int selectedOption){
        setDefaultOption();
        selectedOptionPosition=selectedOption;
        tv.setTextColor(Color.parseColor("#665252"));
        tv.setTypeface(tv.getTypeface(),Typeface.BOLD);
        tv.setBackground(ContextCompat.getDrawable(this,R.drawable.selected_bg_text_color));
    }

public void setdata(ArrayList<Question> arrayList){
        //get fetch all data from firebase and copy into new arraylist
    questionArrayList=arrayList;
    //this array is for check user already submit ansers
    solvedMarkQuestionArray=new int[arrayList.size()];

    /////check problem from here
//    Toast.makeText(getApplicationContext(), "questionArrayList size "+questionArrayList.size(), Toast.LENGTH_LONG).show();
    currentPosition=1;
   setQuestion();
   countdown.setText("");



    progressBar.setProgress(currentPosition);
        progressBar.setMax(questionArrayList.size());
        textViewProgress.setText((currentPosition)+"/"+progressBar.getMax());
        Log.d("check","list is "+questionArrayList.size());

}
    private   void getDataFirebase(){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference1 = database.getReference();
        ArrayList<Question> arrayList = new ArrayList<>();
        databaseReference1.child("QuizApp").child(subjectType).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterable<DataSnapshot> children = snapshot.getChildren();
//                Toast.makeText(getApplicationContext(), "Total objects " +children, Toast.LENGTH_LONG).show();
                // shake hands with each of them.'
                for (DataSnapshot child : children) {

                    Question question = child.getValue(Question.class);
//                    Toast.makeText(getApplicationContext(), "Object id call "+question.getId(), Toast.LENGTH_SHORT).show();
                    arrayList.add(new Question(
                            question.getId(),
                            question.getQuestion() ,
                            question.getOption1(),
                            question.getOption2(), question.getOption3(), question.getOption4(),

                            R.drawable.think,question.getCorrectAns()));


                }
                setdata(arrayList);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });






    }
    private void  update_user(String user,String subject,int correctA){



        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                databaseReference = firebaseDatabase.getReference();
                databaseReference.child("User").child("User: "+user).child(subject).setValue(correctA);
//                Toast.makeText(getApplicationContext(), "data updated", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "no  data added", Toast.LENGTH_SHORT).show();
            }
        });
    }


}