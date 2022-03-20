package com.mhxx307.gridviewproject;

import java.io.Serializable;

public class SanPham implements Serializable {
    private byte[] imgSanPham;
    private String tenSanPham;
    private Double giaSanPham;
    private int giamGia;

    public SanPham() {

    }

    public SanPham(byte[] imgSanPham, String tenSanPham, Double giaSanPham, int giamGia) {
        this.imgSanPham = imgSanPham;
        this.tenSanPham = tenSanPham;
        this.giaSanPham = giaSanPham;
        this.giamGia = giamGia;
    }

    public byte[] getImgSanPham() {
        return imgSanPham;
    }

    public void setImgSanPham(byte[] imgSanPham) {
        this.imgSanPham = imgSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public Double getGiaSanPham() {
        return giaSanPham;
    }

    public void setGiaSanPham(Double giaSanPham) {
        this.giaSanPham = giaSanPham;
    }

    public int getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(int giamGia) {
        this.giamGia = giamGia;
    }
}
