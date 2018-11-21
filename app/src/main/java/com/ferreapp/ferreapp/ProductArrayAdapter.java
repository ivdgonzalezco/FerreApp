package com.ferreapp.ferreapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.support.constraint.Constraints.TAG;


public class ProductArrayAdapter extends ArrayAdapter<Product> {

    private Context mContext;
    private List<Product> products;

    public ProductArrayAdapter(@NonNull Context context, ArrayList<Product> list) {
        super(context, 0 , list);
        mContext = context;
        products = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.item_product_order,parent,false);

        Product currentProd = products.get(position);

        TextView price = (TextView) listItem.findViewById(R.id.price_text_view);
        price.setText(Integer.toString(currentProd.getPrice()));

        TextView name = (TextView) listItem.findViewById(R.id.name_text_view);
        name.setText(currentProd.getName());

        TextView cat = (TextView) listItem.findViewById(R.id.category_text_view);
        cat.setText(currentProd.getCategory());



        return listItem;
    }
}