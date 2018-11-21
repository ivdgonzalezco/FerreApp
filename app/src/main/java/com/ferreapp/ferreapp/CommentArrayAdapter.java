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


public class CommentArrayAdapter extends ArrayAdapter<Order> {

    private Context mContext;
    private List<Order> orders;

    public CommentArrayAdapter(@NonNull Context context, ArrayList<Order> list) {
        super(context, 0 , list);
        mContext = context;
        orders = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.item_comment,parent,false);

        Order currentOrder = orders.get(position);

        TextView com = (TextView) listItem.findViewById(R.id.comment_text_view);
        com.setText(currentOrder.getComment());

        TextView desc = (TextView) listItem.findViewById(R.id.description_text_view);
        desc.setText(currentOrder.getCommentDescription());

        TextView buyer = (TextView) listItem.findViewById(R.id.buyerName);
        buyer.setText(currentOrder.getBuyer());



        return listItem;
    }
}