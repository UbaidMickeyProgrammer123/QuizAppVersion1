package com.mickeyprogrammer.quizapp.CRUD_Q;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mickeyprogrammer.quizapp.Question;
import com.mickeyprogrammer.quizapp.R;

import java.util.ArrayList;

public class CRUD_CustumAdapter extends RecyclerView.Adapter<CRUD_CustumAdapter.QuestionViewHolder> {
    public ArrayList<Question> arrayListQuestion;

    public CRUD_CustumAdapter(ArrayList<Question> arrayListQuestion) {
        this.arrayListQuestion = arrayListQuestion;
    }


    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.crud_operation_item_layout, parent, false);


        return new QuestionViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        Question question=arrayListQuestion.get(position);
        TextView a,b;
        a=holder.no;
        b=holder.question;
        a.setText(Integer.toString(question.getId()));
       b.setText(question.getQuestion());


        ImageView update,delete;
        update=holder.update_img;
        delete=holder.delete_img;

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(), CRUD_ADD_Update_Delete_Question.class);
                intent.putExtra("Id",question.getId());
                intent.putExtra("question",question.getQuestion());
                intent.putExtra("option1",question.getOption1());
                intent.putExtra("option2",question.getOption2());
                intent.putExtra("option3",question.getOption3());
                intent.putExtra("option4",question.getOption4());
                intent.putExtra("correctAns",question.getCorrectAns());
                intent.putExtra("btn_type","update");
                view.getContext().startActivity(intent);
                ((RecyclerView_CRUD_Questions_option)view.getContext()).finish();
            }
        });


        //delete object
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(), CRUD_ADD_Update_Delete_Question.class);
                intent.putExtra("Id",question.getId());
                intent.putExtra("question",question.getQuestion());
                intent.putExtra("option1",question.getOption1());
                intent.putExtra("option2",question.getOption2());
                intent.putExtra("option3",question.getOption3());
                intent.putExtra("option4",question.getOption4());
                intent.putExtra("correctAns",question.getCorrectAns());
                intent.putExtra("btn_type","delete");
                view.getContext().startActivity(intent);

                ((RecyclerView_CRUD_Questions_option)view.getContext()).finish();
            }
        });
    }


    @Override
    public int getItemCount() {
        return arrayListQuestion.size();
    }

    public class QuestionViewHolder extends RecyclerView.ViewHolder {
        TextView no,question;
        ImageView update_img,delete_img;
        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);
            no=itemView.findViewById(R.id.textView_question_no_id);
            question=itemView.findViewById(R.id.textView6_question_id);
            update_img=itemView.findViewById(R.id.imageView5_update);
            delete_img=itemView.findViewById(R.id.textView_delete);
        }
    }
}
