package com.ferreapp.ferreapp;

import java.io.Serializable;

public class Product implements Serializable{

    private String productName;
    private String productBrand;
    private String productDescription;
    private String productPrice;

    public Product(String productName, String productBrand, String productDescription, String productPrice) {
        this.productName = productName;
        this.productBrand = productBrand;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }
}
