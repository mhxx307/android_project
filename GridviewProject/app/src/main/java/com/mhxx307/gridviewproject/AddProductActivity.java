package com.mhxx307.gridviewproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class AddProductActivity extends AppCompatActivity {

    EditText edtTenSanPham, edtGiaSanPham, edtGiamGia;
    Button btnChupHinh, btnConfirm, btnPick;
    ImageView imgSanPham;
    int CAMERA = 111;
    int PICK = 222;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        anhXa();

        btnChupHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(AddProductActivity.this, new String[]{Manifest.permission.CAMERA}, CAMERA);
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String tenSanPham = edtTenSanPham.getText().toString();
                Double giaSanPham = Double.parseDouble(edtGiaSanPham.getText().toString().trim());
                int giamGia = Integer.parseInt(edtGiamGia.getText().toString().trim());

                Bitmap bitmap = ((BitmapDrawable)imgSanPham.getDrawable()).getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();

                SanPham sanPham = new SanPham(byteArray, tenSanPham, giaSanPham, giamGia);
                Intent intent = new Intent();
                intent.putExtra("sanpham", sanPham);
                setResult(RESULT_OK, intent);

                finish();
            }
        });
    }

    private void anhXa() {
        edtTenSanPham = findViewById(R.id.edtTenSanPham);
        edtGiaSanPham = findViewById(R.id.edtGiaTien);
        edtGiamGia = findViewById(R.id.edtGiamGia);
        btnChupHinh = findViewById(R.id.btnChupHinh);
        btnConfirm = findViewById(R.id.btnConfirm);
        imgSanPham = findViewById(R.id.imgviewSanPham);
        btnPick = findViewById(R.id.btnPick);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA && resultCode == RESULT_OK && data!=null) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgSanPham.setImageBitmap(bitmap);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, CAMERA);
        }
        else {
            Toast.makeText(this, "Không cấp quyền, cút", Toast.LENGTH_SHORT);
        }

    }

}