package com.ferreapp.ferreapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.RatingBar;
import android.widget.TextView;

public class CommentsDetails extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comments_details_layout);

        Intent intent = getIntent();

        Classification classification = (Classification) intent.getSerializableExtra("Classifications");

        RatingBar mRatingBar = findViewById(R.id.ratingBarDetails);
        TextView mRatingBarScale = findViewById(R.id.RatingScaleDetails);
        TextView comment = findViewById(R.id.comentariosDetails);

        mRatingBar.setRating(classification.getClassification());

        if (classification.getClassification() <= 1.0){
            mRatingBarScale.setText("Malo");
        }else if (classification.getClassification() > 1.0 && classification.getClassification() <= 2.0){
            mRatingBarScale.setText("Necesita mejorar");
        }else if (classification.getClassification() > 2.0 && classification.getClassification() <= 3.0){
            mRatingBarScale.setText("Bueno");
        }else if (classification.getClassification() > 3.0 && classification.getClassification() <= 4.0){
            mRatingBarScale.setText("Muy bueno");
        }else {
            mRatingBarScale.setText("Excelente");
        }

        comment.setText(classification.getComments());

    }


}
