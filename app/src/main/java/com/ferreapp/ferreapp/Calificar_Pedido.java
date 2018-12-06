package com.ferreapp.ferreapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Calificar_Pedido extends AppCompatActivity {

    private RatingBar mRatingBar;
    private TextView mRatingScale;
    private EditText comentario;

    private Float rate;

    private ArrayList<Classification> classifications = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calificar_pedido);

        mRatingBar = findViewById(R.id.ratingBar);
        mRatingScale = findViewById(R.id.RatingScale);
        comentario =  findViewById(R.id.comentarios);

        classifications = ClassificationSingleton.getInstance().getClassifications();

        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (rating <= 1.0){
                    mRatingScale.setText("Malo");
                    rate = rating;
                }else if (rating > 1.0 && rating <= 2.0){
                    mRatingScale.setText("Necesita mejorar");
                    rate = rating;
                }else if (rating > 2.0 && rating <= 3.0){
                    mRatingScale.setText("Bueno");
                    rate = rating;
                }else if (rating > 3.0 && rating <= 4.0){
                    mRatingScale.setText("Muy bueno");
                    rate = rating;
                }else {
                    mRatingScale.setText("Excelente");
                    rate = rating;
                }
            }
        });
    }

    public void enviar(View view) {

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();

        String orderDate = formatter.format(date);

        String comentarios = comentario.getText().toString();

        Classification classification = new Classification(comentarios, orderDate, rate);

        classifications.add(classification);

        ClassificationSingleton.getInstance().setClassifications(classifications);

        Toast.makeText(this, "Gracias por sus comentarios", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, MainProductsActivity.class);
        startActivity(intent);
    }

}
