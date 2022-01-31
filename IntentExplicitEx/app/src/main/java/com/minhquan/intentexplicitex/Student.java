package com.minhquan.intentexplicitex;

import java.io.Serializable;

public class Student implements Serializable {
    private String ten;
    private String namSinh;

    public Student() {
    }

    public Student(String ten, String namSinh) {
        this.ten = ten;
        this.namSinh = namSinh;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }

    @Override
    public String toString() {
        return "Student{" +
                "ten='" + ten + '\'' +
                ", namSinh='" + namSinh + '\'' +
                '}';
    }
}
