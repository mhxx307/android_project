package com.minhquan.a19441111_lavominhquan_bt;

public class PhuongTrinhBac2 {
    private double a;
    private double b;
    private double c;

    public PhuongTrinhBac2() {
    }

    public PhuongTrinhBac2(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public String giai() {
        double delta = b*b - 4*a*c;
        double x, x1, x2;
        String rs = "";

        if (delta < 0) {
            rs += "Phuong trinh vo nghiem";
            return rs;
        } else if (delta == 0) {
            x = (-b/(2*a));
            rs += "Phuong trinh co 2 nghiem kep " + x;
            return rs;
        } else if (delta > 0) {
            x1 = (float) ((-b + Math.sqrt(delta)) / (2*a));
            x2 = (float) ((-b - Math.sqrt(delta)) / (2*a));
            rs += "Phuong trinh co 2 nghiem phan biet: x1 = " + x1 + ", x2 = " + x2;
            return rs;
        }
        return null;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }
}
