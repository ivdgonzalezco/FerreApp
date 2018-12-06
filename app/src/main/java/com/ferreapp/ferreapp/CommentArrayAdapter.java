package com.ferreapp.ferreapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class CommentArrayAdapter extends RecyclerView.Adapter<CommentArrayAdapter.RecyclerViewHolder> {

    private Context context;
    private ArrayList<Classification> classifications;

    public CommentArrayAdapter(Context context, ArrayList<Classification> classifications) {
        this.context = context;
        this.classifications = classifications;
    }

    @NonNull
    @Override
    public CommentArrayAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.comments_row, viewGroup, false);

        return new CommentArrayAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentArrayAdapter.RecyclerViewHolder recyclerViewHolder, int i) {

        final Classification classification = classifications.get(i);

        recyclerViewHolder.mClassDate.setText(classification.getClassDate());
        recyclerViewHolder.mClassRating.setText(classification.getClassification().toString());

        recyclerViewHolder.mClassLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, CommentsDetails.class);
                intent.putExtra("Classifications", classification);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return classifications.size();
    }


    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        public TextView mClassDate, mClassRating;
        public LinearLayout mClassLayout;


        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            mClassDate = itemView.findViewById(R.id.commentDate);
            mClassRating = itemView.findViewById(R.id.ratingClass);
            mClassLayout = itemView.findViewById(R.id.commentLayout);
        }
    }


}