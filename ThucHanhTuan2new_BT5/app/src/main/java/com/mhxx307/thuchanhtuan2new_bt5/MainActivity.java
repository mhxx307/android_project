package com.mhxx307.thuchanhtuan2new_bt5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText edtMaNV, edtTenNV;
    Button btnNhap;
    RadioGroup rdGroup;
    ListView listNhanVien;
    List<Employee> employees = new ArrayList<Employee>();
    ArrayAdapter<Employee> arrAdapter = null;
    Employee employee = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();
        arrAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, employees);
        listNhanVien.setAdapter(arrAdapter);
        btnNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nhap();
            }
        });
    }

    private void anhXa() {
        edtMaNV = findViewById(R.id.edtMaNV);
        edtTenNV = findViewById(R.id.edtTenNV);
        btnNhap = findViewById(R.id.btnNhapNV);
        rdGroup = findViewById(R.id.rdGroup);
        listNhanVien = findViewById(R.id.listNhanVien);
    }

    private void nhap() {
        int radId = rdGroup.getCheckedRadioButtonId();
        String id = edtMaNV.getText().toString().trim();
        String tenNV = edtTenNV.getText().toString().trim();

        if (radId == R.id.rdChinhThuc) {
            employee = new EmployeeFulltime();
            employee.setLoaiNhanVien("Chính thức");
        } else {
            employee = new EmployeeParttime();
            employee.setLoaiNhanVien("Thời vụ");
        }
        employee.setId(id);
        employee.setTenNhanVien(tenNV);
        employees.add(employee);
        arrAdapter.notifyDataSetChanged();
    }
}