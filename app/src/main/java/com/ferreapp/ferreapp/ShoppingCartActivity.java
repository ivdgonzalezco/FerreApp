package com.ferreapp.ferreapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

public class ShoppingCartActivity extends Activity{

    RecyclerView mRecyclerView;
    ShoppingCartAdapter recyclerViewAdapter;

    ArrayList<Product> products = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_cart_activity);

        products = ProductSingleton.getInstance().getProducts();

        Log.d("wtf", "size coming home: " + products.size());

        mRecyclerView = findViewById(R.id.scRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAdapter = new ShoppingCartAdapter(this, products);
        mRecyclerView.setAdapter(recyclerViewAdapter);


    }
}
