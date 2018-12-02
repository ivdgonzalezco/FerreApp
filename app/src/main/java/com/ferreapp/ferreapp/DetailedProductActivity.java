package com.ferreapp.ferreapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DetailedProductActivity extends Activity {

    private TextView mDetailedProductName;
    private TextView mDetailedProductBrand;
    private TextView mDetailedProductPrice;
    private TextView mDetailedProductDescription;
    private ImageView mDetailedProductImage;
    private Button mShoppingCart;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_activity);

        mDetailedProductName = findViewById(R.id.detailedViewName);
        mDetailedProductBrand = findViewById(R.id.detailedViewBrand);
        mDetailedProductPrice = findViewById(R.id.detailedViewPrice);
        mDetailedProductDescription = findViewById(R.id.detailedViewDescription);

        Intent intent = getIntent();

        Log.d("WTF", intent.getStringExtra("productName") + intent.getStringExtra("productBrand") + intent.getStringExtra("productPrice") + intent.getStringExtra("productDescription"));

        mDetailedProductName.setText(intent.getStringExtra("productName"));
        mDetailedProductBrand.setText(intent.getStringExtra("productBrand"));
        mDetailedProductPrice.setText(intent.getStringExtra("productPrice"));
        mDetailedProductDescription.setText(intent.getStringExtra("productDescription"));

    }
}


