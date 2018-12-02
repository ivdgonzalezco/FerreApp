package com.ferreapp.ferreapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewFBAdapter extends RecyclerView.Adapter<RecyclerViewFBAdapter.RecyclerViewHolder> {

    private Context context;
    private ArrayList<Product> productList;

    public RecyclerViewFBAdapter(Context context, ArrayList<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.single_row, viewGroup, false);

        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder recyclerViewHolder, int i) {

        final Product product = productList.get(i);

        recyclerViewHolder.mProductName.setText(product.getProductName());
        recyclerViewHolder.mProductBrand.setText(product.getProductBrand());
        recyclerViewHolder.mProductPrice.setText(product.getProductPrice());

        recyclerViewHolder.mProductLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailedProductActivity.class);
                intent.putExtra("productName", product.getProductName());
                intent.putExtra("productBrand", product.getProductBrand());
                intent.putExtra("productPrice", product.getProductPrice());
                intent.putExtra("productDescription", product.getProductDescription());

                context.startActivity(intent);
            }
        });

        recyclerViewHolder.mShoppingCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Product> productToSingleton= ProductSingleton.getInstance().getProducts();

                Log.d("WTF", "Size: " + productToSingleton.size());
                productToSingleton.add(product);
                ProductSingleton.getInstance().setProducts(productToSingleton);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public void filteredList(ArrayList<Product> filteredProducts) {
        productList = filteredProducts;
        notifyDataSetChanged();
    }


    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        public ImageView mProductImage;
        public TextView mProductName;
        public TextView mProductBrand;
        public TextView mProductPrice;
        public ImageButton mShoppingCart;
        public RelativeLayout mProductLayout;


        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            mProductImage = itemView.findViewById(R.id.productImage);
            mProductName = itemView.findViewById(R.id.productName);
            mProductBrand = itemView.findViewById(R.id.productBrand);
            mProductPrice = itemView.findViewById(R.id.productPrice);
            mShoppingCart = itemView.findViewById(R.id.shoppingCartBtn);
            mProductLayout = itemView.findViewById(R.id.productLayout);
        }
    }
}
