package com.ferreapp.ferreapp;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private String seller;
    private String buyer;
    private int totalPrice;
    private String state; //1-pagado 2-por_pagar 3-entregago 4-rechazado
    private String comment; //bueno, deficiente, excelente, regular
    private String commentDescription;
    private ArrayList<Product> products;

    public Order() {
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCommentDescription(String commentDescription) {
        this.commentDescription = commentDescription;
    }

    public String getSeller() {

        return seller;
    }

    public String getBuyer() {
        return buyer;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public String getState() {
        return state;
    }

    public String getComment() {
        return comment;
    }

    public String getCommentDescription() {
        return commentDescription;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Order(String seller, String buyer, int totalPrice, String state, String comment, String commentDescription, ArrayList<Product> products) {
        this.seller = seller;
        this.buyer = buyer;
        this.totalPrice = totalPrice;
        this.state = state;
        this.comment = comment;
        this.commentDescription = commentDescription;
        this.products = products;
    }

    public Order(String seller, String buyer, int totalPrice, ArrayList<Product> products) {
        this.seller = seller;
        this.buyer = buyer;
        this.totalPrice = totalPrice;
        this.products = products;
    }

    @Override
    public String toString() {
        return "Order{" +
                "seller='" + seller + '\'' +
                ", buyer='" + buyer + '\'' +
                ", totalPrice=" + totalPrice +
                ", state='" + state + '\'' +
                ", comment='" + comment + '\'' +
                ", commentDescription='" + commentDescription + '\'' +
                ", products=" + products +
                '}';
    }
}
