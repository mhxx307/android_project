package com.mhxx307.thuchanhtuan2new_bt5;

public class EmployeeFulltime extends Employee{
    @Override
    public double tinhLuong() {
        return 500;
    }

    @Override
    public String toString() {
        return super.toString() + " --> Salary fulltime: " + tinhLuong();
    }
}
