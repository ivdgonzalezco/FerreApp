package com.ferreapp.ferreapp;

import java.io.Serializable;

public class Classification implements Serializable {
    private String comments;
    private String classDate;
    private Float classification;

    public Classification(String comments, String classDate, Float classification) {
        this.comments = comments;
        this.classDate = classDate;
        this.classification = classification;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getClassDate() {
        return classDate;
    }

    public void setClassDate(String classDate) {
        this.classDate = classDate;
    }

    public Float getClassification() {
        return classification;
    }

    public void setClassification(Float classification) {
        this.classification = classification;
    }
}
