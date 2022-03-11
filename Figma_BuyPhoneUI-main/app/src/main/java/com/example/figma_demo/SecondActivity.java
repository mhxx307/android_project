package com.example.figma_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class SecondActivity extends AppCompatActivity {

    ImageView imgSanPham, imgDo, imgXanhNhat, imgDen, imgXanhDuong;
    Button btnXong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        anhXa();

        imgXanhNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgSanPham.setImageResource(R.drawable.vs_bac);
            }
        });

        imgDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgSanPham.setImageResource(R.drawable.vs_do);
            }
        });

        imgDen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgSanPham.setImageResource(R.drawable.vs_den);
            }
        });

        imgXanhDuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgSanPham.setImageResource(R.drawable.vs_xanh);
            }
        });

        btnXong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();

                Bitmap bitmap = ((BitmapDrawable) imgSanPham.getDrawable()).getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();

                SanPham sanPham = new SanPham(byteArray);

                intent.putExtra("sanpham", sanPham);

                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private void anhXa() {
        imgXanhNhat = findViewById(R.id.img_xanhnhat);
        imgDo = findViewById(R.id.img_do);
        imgDen = findViewById(R.id.img_den);
        imgXanhDuong = findViewById(R.id.img_xanhduong);
        imgSanPham = findViewById(R.id.imgView_SanPham);
        btnXong = findViewById(R.id.btn_Xong);
    }

}