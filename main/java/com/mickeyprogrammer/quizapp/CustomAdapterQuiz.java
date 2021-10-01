package com.mickeyprogrammer.quizapp;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapterQuiz extends RecyclerView.Adapter<CustomAdapterQuiz.ViewHolder>  {

    public ArrayList <UserQuiz> listUserQuiz;
    public Context context;



    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {


        public final View contextItem;
        public  TextView no,name,score;
        public  ImageView imageView;
        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            contextItem=view;
            no =  view.findViewById(R.id.textView3_position_id);
            name =  view.findViewById(R.id.textView5_name_id);
            score =  view.findViewById(R.id.textView_score_id);
            imageView=view.findViewById(R.id.imageView4_prize_item);

        }


    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public CustomAdapterQuiz(ArrayList<UserQuiz> dataSet, Context context) {
        this.listUserQuiz = dataSet;
        this.context=context;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public CustomAdapterQuiz.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.position_item_layout, viewGroup, false);


        return new ViewHolder(view);
    }



    // Replace the contents of a view (invoked by the layout manager)
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int pos) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(view.getContext(),UserPageLayoutActivity.class);
        intent.putExtra("userType","user");
        Toast toast = Toast.makeText(view.getContext(), "Restart Your Quiz", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 5);
        toast.show();
        view.getContext().startActivity(intent);
        ((recyclerView_position)view.getContext()).finish();

    }
});
UserQuiz userQuiz=listUserQuiz.get(pos);
TextView number=viewHolder.no;
ImageView imageView=viewHolder.imageView;
number.setText(Integer.toString(userQuiz.getPosition()));
        if (userQuiz.getPosition() == 1) {
         imageView.setImageResource(R.drawable.prize1);
        }
        else  if (userQuiz.getPosition() == 2) {
            imageView.setImageResource(R.drawable.prize2);
        }
        else  if (userQuiz.getPosition() == 3) {
            imageView.setImageResource(R.drawable.prize3);
        }
        else {
            imageView.setImageResource(R.drawable.retry);
        }
//        viewHolder.no.setText(Integer.toString(listUserQuiz.get(pos).position));
        viewHolder.name.setText(userQuiz.getUserName());
//        viewHolder.score.setText(Integer.toString(listUserQuiz.get(pos).totalMarks));
        TextView score=viewHolder.score;
        score.setText(Integer.toString(userQuiz.getTotalMarks()));

//imageView.setOnClickListener(this);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return listUserQuiz.size();
    }


}
