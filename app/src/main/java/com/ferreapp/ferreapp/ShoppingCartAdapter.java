package com.ferreapp.ferreapp;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.RecyclerViewHolder>{

    private Context context;
    private ArrayList<Product> productList;

    public ShoppingCartAdapter(Context context, ArrayList<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ShoppingCartAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.shopping_cart_row, viewGroup, false);

        return new ShoppingCartAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingCartAdapter.RecyclerViewHolder recyclerViewHolder, int i) {

        final Product product = productList.get(i);

        recyclerViewHolder.mSCProductName.setText(product.getProductName());
        recyclerViewHolder.mSCProductBrand.setText(product.getProductBrand());
        recyclerViewHolder.mSCProductPrice.setText(product.getProductPrice());
        recyclerViewHolder.mSCProductAmount.setText(product.getProductAmount());

        recyclerViewHolder.mSCProductLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailedProductActivity.class);
                intent.putExtra("productName", product.getProductName());
                intent.putExtra("productBrand", product.getProductBrand());
                intent.putExtra("productPrice", product.getProductPrice());
                intent.putExtra("productDescription", product.getProductDescription());
                intent.putExtra("productAmount", product.getProductAmount());

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{

        public TextView mSCProductName;
        public TextView mSCProductBrand;
        public TextView mSCProductPrice;
        public EditText mSCProductAmount;
        public LinearLayout mSCProductLayout;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            mSCProductName = itemView.findViewById(R.id.scProductName);
            mSCProductBrand = itemView.findViewById(R.id.scProductBrand);
            mSCProductPrice = itemView.findViewById(R.id.scProductPrice);
            mSCProductAmount = itemView.findViewById(R.id.scProductAmount);
            mSCProductLayout = itemView.findViewById(R.id.scProductLayout);
        }
    }
}
