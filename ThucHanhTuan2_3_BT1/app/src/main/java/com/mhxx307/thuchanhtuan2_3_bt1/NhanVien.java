package com.mhxx307.thuchanhtuan2_3_bt1;

public class NhanVien {
    private String id;
    private String ten;
    private int imgGioiTinh;

    public NhanVien() {

    }

    public NhanVien(String id, String ten, int imgGioiTinh) {
        this.id = id;
        this.ten = ten;
        this.imgGioiTinh = imgGioiTinh;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getImgGioiTinh() {
        return imgGioiTinh;
    }

    public void setImgGioiTinh(int imgGioiTinh) {
        this.imgGioiTinh = imgGioiTinh;
    }
}
