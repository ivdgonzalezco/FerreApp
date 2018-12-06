package com.ferreapp.ferreapp;

import java.util.ArrayList;

public class OrderSingleton {

    private ArrayList<Order> orders;

    private static OrderSingleton instance;

    private OrderSingleton(){
        orders = new ArrayList<Order>();
    }

    public static OrderSingleton getInstance(){
        if (instance == null){
            instance = new OrderSingleton();
        }
        return instance;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }
}
