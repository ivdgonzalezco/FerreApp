package com.ferreapp.ferreapp;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class OrderDetails extends Activity {

    private TableLayout tablapos;

    Resources res;
    Locale currentLocale = Locale.getDefault();
    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(currentLocale);

    private String mTotalPrice;
    private ArrayList<Product> mProducts = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_details_layout);

        Intent intent = getIntent();

        Order order = (Order) intent.getSerializableExtra("order");

        mTotalPrice = order.getTotalPrice();
        mProducts = order.getProducts();

        tablapos = findViewById(R.id.tablaComprasDetails);
        TextView tot= findViewById(R.id.totalPagarDetails);

        tot.setText(mTotalPrice);

        llenar_tabla();
    }

    public void llenar_tabla() {

        for(int i = 0; i<mProducts.size(); i++){
            TableRow tableRow = new TableRow(this);
            tablapos.addView(tableRow);

            for(int j= 0; j < 4; j++){
                TextView tv1 = new TextView(this);
                tv1.setTextColor(Color.BLACK);
                if (j == 0){
                    tv1.setText(mProducts.get(i).getProductName());
                }else if(j == 1){
                    tv1.setText(mProducts.get(i).getProductPrice());
                }else if(j == 2){
                    tv1.setText(mProducts.get(i).getProductAmount());
                }else{
                    res = getResources();

                    Integer count = Integer.parseInt(mProducts.get(i).getProductPrice().replace("$", "").replace(" ", "").replace(".", "")) * Integer.parseInt(mProducts.get(i).getProductAmount());
                    tv1.setText(currencyFormatter.format(count));
                }
                tableRow.addView(tv1);
            }
        }

    }
}
