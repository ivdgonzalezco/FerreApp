package com.ferreapp.ferreapp;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
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
    private TextView mDetailedProductDescription, mDetailedProductAmount;
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
        mDetailedProductAmount = findViewById(R.id.detailedViewAmount);
        mDetailedProductImage = findViewById(R.id.detailedViewImage);

        Resources res = getResources();

        Intent intent = getIntent();

        mDetailedProductName.setText(intent.getStringExtra("productName"));
        mDetailedProductBrand.setText(intent.getStringExtra("productBrand"));
        mDetailedProductPrice.setText(intent.getStringExtra("productPrice"));
        mDetailedProductDescription.setText(intent.getStringExtra("productDescription"));
        mDetailedProductAmount.setText(String.format(res.getString(R.string.amount_format), intent.getStringExtra("productAmount")));
        new DownloadImageTask(mDetailedProductImage).execute(intent.getStringExtra("productImage"));
    }
}


