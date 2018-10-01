package com.project.POJOS;

public class Item {

    String item;
    double  mis;
    int support;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public double getMis() {
        return mis;
    }

    public void setMis(double mis) {
        this.mis = mis;
    }

    public int getSupport() {
        return support;
    }

    public void setSupport(int support) {
        this.support = support;
    }

    @Override
    public String toString() {
        return item;
    }
}
