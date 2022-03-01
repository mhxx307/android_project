package com.mhxx307.lavominhquan_tk2_listviewcustomize;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import gun0912.tedbottompicker.TedBottomPicker;

public class MainActivity extends AppCompatActivity {
    EditText edtMaSo, edtHoTen;
    RadioGroup rdgGioiTinh;
    RadioButton rdb_Nam, rdb_Nu;
    Spinner spinner_PhongBan;
    Button btnChooseImg, btnThemNhanVien;
    ImageView imgNhanVien;
    ListView listviewNhanVien;

    List<NhanVien> listNhanVien = new ArrayList<NhanVien>();
    String[] listPhongBan;
    String phongBan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();

        spinner_PhongBan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                phongBan = listPhongBan[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnChooseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestPermisson();
            }
        });

        btnThemNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String maSo = edtMaSo.getText().toString();
                String hoTen = edtHoTen.getText().toString();
                String gioiTinh = rdgGioiTinh.getCheckedRadioButtonId() == R.id.rdb_Nam ? "Nam" : "Nữ";
                int imgNhanVienID = getResources().getIdentifier((String) imgNhanVien.getTag(), "drawable", getPackageName());

                NhanVien nv = new NhanVien();
                nv.setId(maSo);
                nv.setFullName(hoTen);
                nv.setGender(gioiTinh);
                nv.setDepartment(phongBan);
                nv.setImage(imgNhanVienID);

                listNhanVien.add(nv);
                AdapterNhanVien adapter = new AdapterNhanVien(MainActivity.this, R.layout.list_nhanvien_custom, listNhanVien);
                listviewNhanVien.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void anhXa() {
        edtMaSo = findViewById(R.id.edtId);
        edtHoTen = findViewById(R.id.edtFullName);
        rdgGioiTinh = findViewById(R.id.rdg_Gender);
        rdb_Nam = findViewById(R.id.rdb_Nam);
        rdb_Nu = findViewById(R.id.rdb_Nu);
        spinner_PhongBan = findViewById(R.id.spinner_PhongBan);
        btnChooseImg = findViewById(R.id.btn_selectImg);
        btnThemNhanVien = findViewById(R.id.btnThemNhanVien);
        imgNhanVien = findViewById(R.id.imgNhanVien);
        listviewNhanVien = findViewById(R.id.listviewNhanVien);

        listPhongBan = getResources().getStringArray(R.array.department_array);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listPhongBan);
        spinner_PhongBan.setAdapter(adapter);
    }

    private void requestPermisson() {
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                openImagePicker();
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(MainActivity.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }
        };
        TedPermission.create()
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
                .check();
    }

    private void openImagePicker() {
        TedBottomPicker.OnImageSelectedListener listener = new TedBottomPicker.OnImageSelectedListener() {
            @Override
            public void onImageSelected(Uri uri) {
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    imgNhanVien.setImageBitmap(bitmap);
                    imgNhanVien.setTag("imgnv1");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        TedBottomPicker tedBottomPicker = new TedBottomPicker.Builder(MainActivity.this).setOnImageSelectedListener(listener).create();
        tedBottomPicker.show(getSupportFragmentManager());
    }

}