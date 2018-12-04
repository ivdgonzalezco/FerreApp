package com.ferreapp.ferreapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class ShoppingCartActivity extends Activity{

    RecyclerView mRecyclerView;
    ShoppingCartAdapter recyclerViewAdapter;

    ArrayList<Product> products = new ArrayList<>();

    SwipeControllerSC swipeController = null;

    private Button mCreateOrder;

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

        mCreateOrder = findViewById(R.id.createOrder);
        mCreateOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShoppingCartActivity.this, Confirmar_Compra.class);
                startActivity(intent);
            }
        });

        swipeController = new SwipeControllerSC(new SwipeControllerActions() {
            @Override
            public void onLeftClicked(int position) {
                products.remove(position);
                recyclerViewAdapter.notifyItemRemoved(position);
                recyclerViewAdapter.notifyItemRangeChanged(position, recyclerViewAdapter.getItemCount());
            }
        });

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(mRecyclerView);

        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                swipeController.onDraw(c);
            }
        });


    }
}
