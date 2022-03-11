package com.example.figma_demo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.Serializable;

import javax.xml.transform.Result;

public class MainActivity extends AppCompatActivity {

    ImageView imgSanPham;
    Button chonMau, chonMua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();

        chonMau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivityForResult(intent, 999);
            }
        });
    }
    private void anhXa() {
        imgSanPham = findViewById(R.id.imgView_SanPhamMain);
        chonMau = findViewById(R.id.btn_ChonMau);
        chonMua = findViewById(R.id.btn_ChonMua);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 999 && resultCode == RESULT_OK) {

            SanPham sanPham = (SanPham) data.getSerializableExtra("sanpham");

            Bitmap bitmap = BitmapFactory.decodeByteArray(sanPham.getHinhSanPham(), 0, sanPham.getHinhSanPham().length);

            imgSanPham.setImageBitmap(bitmap);
        }
    }
}