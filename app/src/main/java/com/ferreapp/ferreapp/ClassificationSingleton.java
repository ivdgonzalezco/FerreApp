package com.ferreapp.ferreapp;

import java.util.ArrayList;

public class ClassificationSingleton {

    private ArrayList<Classification> classifications;

    private static ClassificationSingleton instance;

    private ClassificationSingleton(){
        classifications = new ArrayList<Classification>();
    }

    public static ClassificationSingleton getInstance(){
        if (instance == null){
            instance = new ClassificationSingleton();
        }
        return instance;
    }

    public ArrayList<Classification> getClassifications() {
        return classifications;
    }

    public void setClassifications(ArrayList<Classification> classifications) {
        this.classifications = classifications;
    }
}
