package com.ferreapp.ferreapp;

public class Product {

    private String name;
    private String category;
    private int price;
    private String description;
    private String brand;

    public Product(String name, String category, int price, String description, String brand) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.description = description;
        this.brand = brand;
    }

    public Product() {
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getBrand() {
        return brand;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }
}
