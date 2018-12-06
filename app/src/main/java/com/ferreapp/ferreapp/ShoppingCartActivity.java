package com.ferreapp.ferreapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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

    private Button mCreateOrder, mDeleteSC;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_cart_activity);

        products = ProductSingleton.getInstance().getProducts();

        mRecyclerView = findViewById(R.id.scRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAdapter = new ShoppingCartAdapter(this, products);
        mRecyclerView.setAdapter(recyclerViewAdapter);

        mCreateOrder = findViewById(R.id.createOrder);
        mDeleteSC = findViewById(R.id.deleteSC);

        mCreateOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ShoppingCartActivity.this);
                builder.setMessage(R.string.create_message)
                        .setTitle(R.string.create_title);

                builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(ShoppingCartActivity.this, Confirmar_Compra.class);
                        startActivity(intent);
                    }
                });

                builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        mDeleteSC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ShoppingCartActivity.this);
                builder.setMessage(R.string.delete_sc)
                        .setTitle(R.string.delete_sc_title);

                builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        int size = products.size();
                        products.clear();
                        for(int i = 0; i < size; i++){
                            recyclerViewAdapter.notifyItemRemoved(i);
                            recyclerViewAdapter.notifyItemRangeChanged(i, recyclerViewAdapter.getItemCount());
                        }
                    }
                });

                builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

        swipeController = new SwipeControllerSC(new SwipeControllerActions() {
            @Override
            public void onLeftClicked(int position) {

                final int i = position;

                AlertDialog.Builder builder = new AlertDialog.Builder(ShoppingCartActivity.this);
                builder.setMessage(R.string.create_message)
                        .setTitle(R.string.create_title);

                builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        products.remove(i);
                        recyclerViewAdapter.notifyItemRemoved(i);
                        recyclerViewAdapter.notifyItemRangeChanged(i, recyclerViewAdapter.getItemCount());
                    }
                });

                builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
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
