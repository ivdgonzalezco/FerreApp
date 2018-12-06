package com.ferreapp.ferreapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.RecyclerViewHolder> {

    private Context context;
    private ArrayList<Order> orders;

    public OrderAdapter(Context context, ArrayList<Order> orders) {
        this.context = context;
        this.orders = orders;
    }

    @NonNull
    @Override
    public OrderAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.orders_row, viewGroup, false);

        return new OrderAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.RecyclerViewHolder recyclerViewHolder, int i) {

        final Order order = orders.get(i);

        recyclerViewHolder.mOrderDate.setText(order.getOrderDate());
        recyclerViewHolder.mOrderTotalAmount.setText(order.getTotalPrice());

        recyclerViewHolder.mOrderLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, OrderDetails.class);
                intent.putExtra("order", order);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return orders.size();
    }


    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        public TextView mOrderDate, mOrderTotalAmount;
        public LinearLayout mOrderLayout;


        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            mOrderDate = itemView.findViewById(R.id.orderDate);
            mOrderTotalAmount = itemView.findViewById(R.id.totalAmount);
            mOrderLayout = itemView.findViewById(R.id.orderLayout);
        }
    }
}
