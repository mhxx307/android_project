package com.mhxx307.grid_view_product;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity2 extends AppCompatActivity {
    ImageView imgProduct;
    TextView txtName, txtPrice, txtContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        anhXa();

        SanPham sanPham = (SanPham) getIntent().getSerializableExtra("sanpham");

        imgProduct.setImageResource(sanPham.getImg());
        txtName.setText(sanPham.getTenSanPham());
        Locale locale = new Locale("vi", "VN");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        txtPrice.setText(currencyFormatter.format(sanPham.getGiaSanPham()) + "");
        txtContent.setText(sanPham.getThongTinSanPham());
    }

    private void anhXa() {
        imgProduct = findViewById(R.id.imgProduct);
        txtName = findViewById(R.id.txt_Name);
        txtPrice = findViewById(R.id.txt_price);
        txtContent = findViewById(R.id.txt_Content);
    }
}