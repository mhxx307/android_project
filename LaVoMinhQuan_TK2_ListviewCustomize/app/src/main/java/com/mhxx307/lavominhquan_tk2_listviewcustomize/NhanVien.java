package com.mhxx307.lavominhquan_tk2_listviewcustomize;

public class NhanVien {
    private String id;
    private String fullName;
    private String gender;
    private String department;
    private byte[] image;

    public NhanVien(){}

    public NhanVien(String id, String fullName, String gender, String department, byte[] image) {
        this.id = id;
        this.fullName = fullName;
        this.gender = gender;
        this.department = department;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
