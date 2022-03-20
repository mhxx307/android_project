package com.mhxx307.figma_donut;

import java.io.Serializable;

public class Donut implements Serializable {
    private int imgDonut;
    private String donutName;
    private String content;
    private double price;

    public Donut() {

    }

    public Donut(int imgDonut, String donutName, String content, double price) {
        this.imgDonut = imgDonut;
        this.donutName = donutName;
        this.content = content;
        this.price = price;
    }

    public int getImgDonut() {
        return imgDonut;
    }

    public void setImgDonut(int imgDonut) {
        this.imgDonut = imgDonut;
    }

    public String getDonutName() {
        return donutName;
    }

    public void setDonutName(String donutName) {
        this.donutName = donutName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
