package com.example.figma_demo;

import java.io.Serializable;

public class SanPham implements Serializable {
    private byte[] hinhSanPham;

    public SanPham(){}

    public SanPham(byte[] hinhSanPham) {
        this.hinhSanPham = hinhSanPham;
    }

    public byte[] getHinhSanPham() {
        return hinhSanPham;
    }

    public void setHinhSanPham(byte[] hinhSanPham) {
        this.hinhSanPham = hinhSanPham;
    }
}
