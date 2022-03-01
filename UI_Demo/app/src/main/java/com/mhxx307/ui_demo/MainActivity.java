package com.mhxx307.ui_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<NhanVien> listNhanVien = new ArrayList<NhanVien>();
    String[] phongBanList;
    Spinner spinner_Phongban;
    EditText edtMaSo, edtHoTen;
    ListView listviewNhanVien;
    RadioGroup rd_group;
    RadioButton rd_nam, rd_Nu;
    Button btnThem, btnSua, btnTruyVan;

    String phongBan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();

        ArrayAdapter adapter = new ArrayAdapter(MainActivity.this,
                android.R.layout.simple_list_item_1, phongBanList);
        spinner_Phongban.setAdapter(adapter);

        spinner_Phongban.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                phongBan = phongBanList[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int maSo = Integer.parseInt(edtMaSo.getText().toString().trim());
                String hoTen = edtHoTen.getText().toString();
//                String gioiTinh = ((RadioButton)findViewById(rd_group.getCheckedRadioButtonId())).getText().toString();
                String gioiTinh = rd_group.getCheckedRadioButtonId() == R.id.radioButton_Nam ? "Nam" : "Nữ";
                NhanVien nhanVien = new NhanVien(maSo, hoTen, gioiTinh, phongBan);

                listNhanVien.add(nhanVien);

                List<String> listToString = new ArrayList<String>();
                for (NhanVien nv : listNhanVien) {
                    listToString.add(nv.toString());
                }
                ArrayAdapter arrAdapter = new ArrayAdapter(MainActivity.this,
                        android.R.layout.simple_list_item_1, listToString);
                listviewNhanVien.setAdapter(arrAdapter);
                arrAdapter.notifyDataSetChanged();
            }
        });

    }

    private void anhXa() {
        edtMaSo = findViewById(R.id.editText_MaSo);
        edtHoTen = findViewById(R.id.editText_HoTen);
        listviewNhanVien = findViewById(R.id.listView_NhanVien);
        rd_nam = findViewById(R.id.radioButton_Nam);
        rd_Nu = findViewById(R.id.radioButton_Nu);
        rd_group = findViewById(R.id.radioGroup);
        btnThem = findViewById(R.id.button_Them);
        btnSua = findViewById(R.id.button_Sua);
        btnTruyVan = findViewById(R.id.button_TruyVan);
        spinner_Phongban = findViewById(R.id.spinner_phongBan);

        phongBanList = getResources().getStringArray(R.array.department_list);
    }
}