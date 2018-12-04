package com.ferreapp.ferreapp;

import java.util.ArrayList;

public class ProductSingleton {

    private ArrayList<Product> products;

    private static ProductSingleton instance;

    private ProductSingleton(){
        products = new ArrayList<Product>();
    }

    public static ProductSingleton getInstance(){
        if (instance == null){
            instance = new ProductSingleton();
        }
        return instance;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
}
