package com.mhxx307.lavominhquan_tk2_listviewcustomize;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


import gun0912.tedbottompicker.TedBottomPicker;

public class MainActivity extends AppCompatActivity {
    EditText edtMaSo, edtHoTen;
    RadioGroup rdgGioiTinh;
    RadioButton rdb_Nam, rdb_Nu;
    Spinner spinner_PhongBan;
    Button btnChooseImg, btnThemNhanVien, btnXoaNhanVien, btnCapNhat, btnSave;
    ImageView imgNhanVien;
    ListView listviewNhanVien;
    AdapterNhanVien adapter;

    List<NhanVien> listNhanVien = new ArrayList<NhanVien>();
    String[] listPhongBan;
    String phongBan;
    int position;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();
        sharedPreferences = getSharedPreferences("DanhSachNhanVien", MODE_PRIVATE);

        loadListNhanVien();

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
                
                Bitmap bitmap = ((BitmapDrawable) imgNhanVien.getDrawable()).getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
//                bitmap.recycle();

                if (kiemTraChinhQuy()) {
                    NhanVien nv = new NhanVien();
                    nv.setId(maSo);
                    nv.setFullName(hoTen);
                    nv.setGender(gioiTinh);
                    nv.setDepartment(phongBan);
                    nv.setImage(byteArray);

                    listNhanVien.add(nv);
                    adapter = new AdapterNhanVien(MainActivity.this, R.layout.list_nhanvien_custom, listNhanVien);
                    listviewNhanVien.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                    lamMoiText();
                }

            }
        });

        listviewNhanVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                position = i;
                NhanVien nv = listNhanVien.get(i);
                edtMaSo.setText(nv.getId());
                edtHoTen.setText(nv.getFullName());
                if (nv.getGender().equals("Nam")) {
                    rdb_Nam.setChecked(true);
                    rdb_Nu.setChecked(false);
                } else if (nv.getGender().equals("Nữ")) {
                    rdb_Nu.setChecked(true);
                    rdb_Nam.setChecked(false);
                }

                for (int index = 0; index < listPhongBan.length; index++) {
                    if(spinner_PhongBan.getItemAtPosition(index).equals(nv.getDepartment())) {
                        spinner_PhongBan.setSelection(index);
                    }
                }

                Bitmap bitmap = BitmapFactory.decodeByteArray(nv.getImage(), 0, nv.getImage().length);
                imgNhanVien.setImageBitmap(bitmap);
            }
        });

        btnXoaNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listNhanVien.remove(position);
                adapter.notifyDataSetChanged();
            }
        });

        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NhanVien nv = listNhanVien.get(position);

                String ma = edtMaSo.getText().toString();
                String ten = edtHoTen.getText().toString();
                String gioiTinh = rdgGioiTinh.getCheckedRadioButtonId() == R.id.rdb_Nam ? "Nam" : "Nữ";
                String phongBan = (String) spinner_PhongBan.getSelectedItem();
                Bitmap bitmap = ((BitmapDrawable) imgNhanVien.getDrawable()).getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();

                nv.setId(ma);
                nv.setFullName(ten);
                nv.setGender(gioiTinh);
                nv.setDepartment(phongBan);
                nv.setImage(byteArray);

                adapter.notifyDataSetChanged();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Gson gson = new Gson();
                String json = gson.toJson(listNhanVien);
                editor.putString("listNhanVien", json);
                editor.commit();
            }
        });
    }

    private void lamMoiText() {
        edtMaSo.setText("");
        edtHoTen.setText("");
        rdb_Nam.isChecked();
        imgNhanVien.setImageResource(android.R.color.transparent);
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
        btnXoaNhanVien = findViewById(R.id.btnXoaNhanVien);
        btnCapNhat = findViewById(R.id.btnCapNhatNhanVien);
        btnSave = findViewById(R.id.btnSave);
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
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        TedBottomPicker tedBottomPicker = new TedBottomPicker.Builder(MainActivity.this).setOnImageSelectedListener(listener).create();
        tedBottomPicker.show(getSupportFragmentManager());
    }

    private boolean kiemTraChinhQuy() {
        if (edtMaSo.getText().toString().equals("")) {
            Toast.makeText(MainActivity.this, "Khong duoc de trong ma so", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (edtHoTen.getText().toString().equals("")) {
            Toast.makeText(MainActivity.this, "Khong duoc de trong ho ten", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void loadListNhanVien() {
        if (getList() != null) {
            for (NhanVien nv : getList()) {
                listNhanVien.add(nv);
            }
            adapter = new AdapterNhanVien(MainActivity.this, R.layout.list_nhanvien_custom, listNhanVien);
            listviewNhanVien.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }

    public List<NhanVien> getList() {
        List<NhanVien> arrayItems = null;
        String serializedObject = sharedPreferences.getString("listNhanVien", null);
        if (serializedObject != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<NhanVien>>(){}.getType();
            arrayItems = gson.fromJson(serializedObject, type);
        }
        return arrayItems;
    }

}