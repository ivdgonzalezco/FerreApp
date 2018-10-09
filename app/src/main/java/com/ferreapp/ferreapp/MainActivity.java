package com.ferreapp.ferreapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button mButtonPedidas;
    private Button mButtonAddOrder;
    private Product mProduct1, mProduct2, mProduct3, mProduct4;
    private DatabaseReference mDatabase;
    private FirebaseFirestore db;
    private Order mOrder;
    private ArrayList<Product> mProducts;
    private static final String TAG = "MyActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mButtonPedidas = (Button) findViewById(R.id.buttonPedidas);

        mButtonPedidas.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(MainActivity.this,NuevasPedidas.class);
                startActivity(intent);
            }
        });

        //will be remove
        mProduct1 = new Product("p1","c1",1,"d1","b1");
        mProduct2 = new Product("p2","c2",2,"d2","b2");
        mProduct3 = new Product("p3","c3",3,"d3","b3");
        mProduct4 = new Product("p4","c4",4,"d4","b4");

        db = FirebaseFirestore.getInstance();

        //button to add order, only for testing, will be remove
        mButtonAddOrder = (Button) findViewById(R.id.addOrder);
        mButtonAddOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<Product> mProducts = new ArrayList<>();
                mProducts.add(mProduct1);
                mProducts.add(mProduct2);
                mProducts.add(mProduct3);

                int mPrice = (int) (Math.random()*100);
                mOrder = new Order("seller1","buyer 88",mPrice,mProducts);

                //firebase
                db.collection("orders")
                        .add(mOrder)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d(TAG, "Document snapshot added with ID : "+ documentReference.getId());
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error adding document", e);
                            }
                        });

            }
        });
    }
}

