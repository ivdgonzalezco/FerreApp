package com.ferreapp.ferreapp;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        Product product = productList.get(i);

        recyclerViewHolder.mSCProductName.setText(product.getProductName());
        recyclerViewHolder.mSCProductBrand.setText(product.getProductBrand());
        recyclerViewHolder.mSCProductPrice.setText(product.getProductPrice());

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{

        public TextView mSCProductName;
        public TextView mSCProductBrand;
        public TextView mSCProductPrice;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            mSCProductName = itemView.findViewById(R.id.scProductName);
            mSCProductBrand = itemView.findViewById(R.id.scProductBrand);
            mSCProductPrice = itemView.findViewById(R.id.scProductPrice);
        }
    }
}
