package com.ferreapp.ferreapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class SellerInitActivity extends AppCompatActivity {

    private Button mButtonOrder;
    private Button mButtonComments;
    private FirebaseFirestore db;
    private Order mOrder;
    private ArrayList<Product> mProducts;
    private static final String TAG = "MyActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_init);


        mButtonOrder = (Button) findViewById(R.id.buttonOrder);
        mButtonComments = (Button) findViewById(R.id.buttonComments);

        mButtonOrder.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(SellerInitActivity.this,SeeOrderActivity.class);
                startActivity(intent);
            }
        });

        mButtonComments.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(SellerInitActivity.this,CommentsActivity.class);
                startActivity(intent);
            }
        });
    }
}

