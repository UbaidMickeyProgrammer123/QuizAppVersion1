package com.mickeyprogrammer.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class recyclerView_position extends AppCompatActivity  {
RecyclerView recyclerView;
int for_break=0;
    public UserQuiz tempUserQuiz=null;
    int count=0;
int tempPosition = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_position);

       recyclerView=findViewById(R.id.recyclerView);

        ArrayList<UserQuiz> arrayList=new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference();



        databaseReference.child("User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterable<DataSnapshot> children = snapshot.getChildren();
//                Toast.makeText(getApplicationContext(), "Total objects " +children, Toast.LENGTH_LONG).show();
                // shake hands with each of them.'
                for_break++;
                count=0;
                for (DataSnapshot child : children) {

                    UserQuiz quizUserList = child.getValue(UserQuiz.class);
//                    Toast.makeText(getApplicationContext(), "Object id call "+quizUserList.getId(), Toast.LENGTH_SHORT).show();
//                     public UserQuiz(String userName, int Islam, int PakStudy, int Java,
//                     int Html, int GK, int Science, int totalMarks) {
//                    count++;

                    arrayList.add(new UserQuiz(
                            quizUserList.getUserName(),
                            quizUserList.getIslam(),
                            quizUserList.getPakStudy(),
                            quizUserList.getJava(), quizUserList.getHtml(), quizUserList.getGK(),
                            quizUserList.getScience(), quizUserList.getTotalMarks(), ++count));
                    Log.d("count", String.valueOf(count));


                }
                if (for_break == 1) {
                    setDataPositionRecycle(arrayList);

                }




            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }


    public  void setDataPositionRecycle(ArrayList<UserQuiz> listUser){
        Toast.makeText(getApplicationContext(), "setDataPositionRecycle fun listUser.size() call "+listUser.size(), Toast.LENGTH_SHORT).show();

        ArrayList<UserQuiz> list=sortingByposition(listUser);
        CustomAdapterQuiz customAdapterQuiz =new CustomAdapterQuiz(list,getApplicationContext());

        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView_position.this));
        recyclerView.setAdapter(customAdapterQuiz);
//        recyclerView.setHasFixedSize(true);

    }
     public ArrayList<UserQuiz> sortingByposition(ArrayList<UserQuiz> listUser){

         ArrayList<UserQuiz> temp_listUser=listUser;
         for (int i = 0; i <temp_listUser.size() ; i++) {
             Log.d("object","Before"+temp_listUser.get(i).toString());
         }
//         Log.d("Object",temp_listUser.get(i).getTotalMarks()+">="+temp_listUser.get(j).getTotalMarks());
//         tempUserQuiz=temp_listUser.get(0);
         for(int i = 0; i<temp_listUser.size(); i++){

             for (int j=i+1;j<temp_listUser.size();j++){
                 if(temp_listUser.get(i).getTotalMarks()<temp_listUser.get(j).getTotalMarks()){

                     tempUserQuiz=temp_listUser.get(i);
                     temp_listUser.set(i,temp_listUser.get(j));
                     temp_listUser.set(j,tempUserQuiz);
                 }

             }
             temp_listUser.get(i).setPosition(i+1);

         }
////try form here
         Collections  .sort(temp_listUser);
//         Toast.makeText(getApplicationContext(), "after setDataPositionRecycle fun listUser.size() call "+listUser.size(), Toast.LENGTH_SHORT).show();
         for (int i = 0; i <temp_listUser.size() ; i++) {
             Log.d("object","After"+temp_listUser.get(i).toString());
         }

         return temp_listUser;
     }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

//    @Override
//    public void onBackPressed() {
//        //super.onBackPressed();
//        SweetAlertDialog progressDialog = new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE);
//        progressDialog.setCancelable(false);
//        progressDialog.setTitleText("Are you sure you want to exit?");
//        progressDialog.setCancelText("No");
//        progressDialog.setConfirmText("Yes");
//        progressDialog.setCanceledOnTouchOutside(true);
//        progressDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//            @Override
//            public void onClick(SweetAlertDialog sweetAlertDialog) {
//                sweetAlertDialog.dismiss();
//                MainActivity.super.onBackPressed();
//            }
//        });
//        progressDialog.show();
//    }
}