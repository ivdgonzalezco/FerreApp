package com.ferreapp.ferreapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

        Resources res = context.getResources();

        recyclerViewHolder.mProductName.setText(product.getProductName());
        recyclerViewHolder.mProductBrand.setText(product.getProductBrand());
        recyclerViewHolder.mProductPrice.setText(product.getProductPrice());
        recyclerViewHolder.mProductAmount.setText(String.format(res.getString(R.string.amount_format),product.getProductAmount()));

        new DownloadImageTask(recyclerViewHolder.mProductImage).execute(product.getProductImageURL());

        recyclerViewHolder.mProductLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailedProductActivity.class);
                intent.putExtra("productName", product.getProductName());
                intent.putExtra("productBrand", product.getProductBrand());
                intent.putExtra("productPrice", product.getProductPrice());
                intent.putExtra("productDescription", product.getProductDescription());
                intent.putExtra("productAmount", product.getProductAmount());
                intent.putExtra("productImage", product.getProductImageURL());

                context.startActivity(intent);
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

    public ArrayList<Product> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        public ImageView mProductImage;
        public TextView mProductName;
        public TextView mProductBrand;
        public TextView mProductPrice, mProductAmount;
        public LinearLayout mProductLayout;


        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            mProductImage = itemView.findViewById(R.id.productImage);
            mProductName = itemView.findViewById(R.id.productName);
            mProductBrand = itemView.findViewById(R.id.productBrand);
            mProductPrice = itemView.findViewById(R.id.ratingClass);
            mProductLayout = itemView.findViewById(R.id.productLayout);
            mProductAmount = itemView.findViewById(R.id.productAmount);
        }
    }


}
