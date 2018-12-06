package com.ferreapp.ferreapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable{

    private String orderBuyer;
    private String totalPrice;
    private String orderDate;
    private ArrayList<Product> products;

    public Order(String orderBuyer, String totalPrice, String orderDate, ArrayList<Product> products) {
        this.orderBuyer = orderBuyer;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.products = products;
    }

    public String getOrderBuyer() {
        return orderBuyer;
    }

    public void setOrderBuyer(String orderBuyer) {
        this.orderBuyer = orderBuyer;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
}
