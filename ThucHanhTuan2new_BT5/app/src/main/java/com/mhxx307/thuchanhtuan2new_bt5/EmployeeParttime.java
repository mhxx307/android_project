package com.mhxx307.thuchanhtuan2new_bt5;

public class EmployeeParttime extends Employee{
    @Override
    public double tinhLuong() {
        return 150;
    }

    @Override
    public String toString() {
        return super.toString() + "--> Salary parttime: " + tinhLuong();
    }
}
