package com.ferreapp.ferreapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class Calificar_Pedido extends AppCompatActivity implements RatingBar.OnRatingBarChangeListener {

    RatingBar mRatingBar;
    TextView mRatingScale;
    EditText comentario;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calificar_pedido);
        mRatingBar = (RatingBar) findViewById(R.id.ratingBar);
        mRatingScale = (TextView) findViewById(R.id.RatingScale);
        comentario = (EditText) findViewById(R.id.comentarios);

    }

    public void enviar(View view) {
        String comentarios;
        int valor = (int) mRatingBar.getRating();
        comentarios = comentario.getText().toString();
        //enviar a la BD
        Toast.makeText(this, "Gracias por sus comentarios", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
        mRatingScale.setText(String.valueOf(v));
        switch ((int) ratingBar.getRating()) {
            case 1:
                mRatingScale.setText("Malo");
                break;
            case 2:
                mRatingScale.setText("Necesita mejorar");
                break;
            case 3:
                mRatingScale.setText("Bueno");
                break;
            case 4:
                mRatingScale.setText("Muy bueno");
                break;
            case 5:
                mRatingScale.setText("Excelente");
                break;
            default:
                mRatingScale.setText("");
        }
    }
}
