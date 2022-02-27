package com.mhxx307.thuchanhtuan2new_bt5;

public abstract class Employee {
    private String id;
    private String tenNhanVien;
    private String loaiNhanVien;

    public Employee() {

    }

    public Employee(String id, String tenNhanVien, String loaiNhanVien) {
        this.id = id;
        this.tenNhanVien = tenNhanVien;
        this.loaiNhanVien = loaiNhanVien;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getLoaiNhanVien() {
        return loaiNhanVien;
    }

    public void setLoaiNhanVien(String loaiNhanVien) {
        this.loaiNhanVien = loaiNhanVien;
    }

    public abstract double tinhLuong();

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + this.id + '\'' +
                ", tenNhanVien='" + this.tenNhanVien + '\'' +
                ", loaiNhanVien='" + this.loaiNhanVien + '\'' +
                '}';
    }
}
