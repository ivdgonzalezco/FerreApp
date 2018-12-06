package com.ferreapp.ferreapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Iterator;

public class SeeOrderActivity extends AppCompatActivity {

    private ArrayList<Order> orders;
    RecyclerView mRecyclerView;
    OrderAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevas_pedidas);

        orders = OrderSingleton.getInstance().getOrders();

        mRecyclerView = findViewById(R.id.recyclerViewComments);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewAdapter = new OrderAdapter(this, orders);
        mRecyclerView.setAdapter(recyclerViewAdapter);
    }

}
