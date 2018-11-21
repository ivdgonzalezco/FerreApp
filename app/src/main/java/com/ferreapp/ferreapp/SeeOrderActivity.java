package com.ferreapp.ferreapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Iterator;

public class SeeOrderActivity extends AppCompatActivity {

    private ListView mList;
    private TextView mBuyerName, mTotalPrice;
    private FirebaseFirestore db;
    private static final String TAG = "MyActivity";
    private Order mOrder;
    private ArrayList<Product> mProducts;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevas_pedidas);
        setTitle("Nuevas pedidas");

        mBuyerName = (TextView) findViewById(R.id.usuarioComprador);
        mTotalPrice = (TextView) findViewById(R.id.price);
        mList = (ListView) findViewById(R.id.list_view_products);

        mProducts = new ArrayList<Product>();


        db = FirebaseFirestore.getInstance();

        db.collection("orders")
                .whereEqualTo("state", null)
                .limit(1)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            Log.d(TAG, "onComplete: data fetched");
                            mOrder = task.getResult().getDocuments().get(0).toObject(Order.class);
                            mBuyerName.setText(mOrder.getBuyer());
                            mTotalPrice.setText(String.valueOf(mOrder.getTotalPrice())+ " $");
                            mProducts = new ArrayList<Product>();
                            Iterator<Product> it = mOrder.getProducts().iterator();
                            while(it.hasNext()){
                                mProducts.add(it.next());
                            }

                            ProductArrayAdapter adap = new ProductArrayAdapter(SeeOrderActivity.this,mProducts);
                            mList.setAdapter(adap);
                            Log.d(TAG, "onComplete: adapter must be changed");



                        }else {
                            Log.d(TAG, "Error getting document: "+ task.getException());
                        }
                    }
                });


    }

}
