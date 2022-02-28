package com.mhxx307.thuchanhtuan2_3_bt1;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listviewNhanVien;
    List<NhanVien> listNhanVien;
    NhanVienAdapter nhanVienAdapter;
    Button btnNhap;
    EditText edtMaNV,edtTenNV;
    RadioGroup rdGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listviewNhanVien = findViewById(R.id.listNhanVien);
        btnNhap = findViewById(R.id.btnNhapNV);
        edtMaNV = findViewById(R.id.edtMaNV);
        edtTenNV = findViewById(R.id.edtTenNV);
        rdGroup = findViewById(R.id.rdGroup);

        listNhanVien = new ArrayList<NhanVien>();

        btnNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NhanVien nv = new NhanVien();

                String maNV = edtMaNV.getText().toString().trim();
                String tenNV = edtTenNV.getText().toString();

                if (!maNV.equals("") || !tenNV.equals("")) {
                    nv.setId(maNV);
                    nv.setTen(tenNV);
                    nv.setTen(tenNV);
                    if (rdGroup.getCheckedRadioButtonId() == R.id.rdNam) {
                        nv.setImgGioiTinh(R.drawable.male);
                    } else {
                        nv.setImgGioiTinh(R.drawable.female);
                    }
                    listNhanVien.add(nv);
                    nhanVienAdapter = new NhanVienAdapter(MainActivity.this, R.layout.layout_nhan_vien, listNhanVien);
                    listviewNhanVien.setAdapter(nhanVienAdapter);
                    nhanVienAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "Khong duoc de trong", Toast.LENGTH_SHORT);
                }

            }
        });


    }
}