package com.mhxx307.gridviewproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.PopupMenu;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    GridView listSanPham;
    List<SanPham> sanPhams;
    SanPhamAdapter adapter;
    int position;
    Button btnSave;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();

        sharedPreferences = getSharedPreferences("products", MODE_PRIVATE);
        loadListSanPham();

        listSanPham.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                position = i;
                PopupMenu ppMenu = new PopupMenu(MainActivity.this, view);
                ppMenu.getMenuInflater().inflate(R.menu.menu_gridview, ppMenu.getMenu());
                ppMenu.show();
                ppMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.mnXoa:
                                sanPhams.remove(position);
                                adapter.notifyDataSetChanged();
                                break;
                            case R.id.mnSua:

                                break;
                        }

                        return false;
                    }
                });
                return false;
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Gson gson = new Gson();
                String json = gson.toJson(sanPhams);
                editor.putString("danhsachsanpham", json);
                editor.commit();
            }
        });


    }

    private void anhXa() {
        listSanPham = findViewById(R.id.listSanPham);
        sanPhams = new ArrayList<>();
        btnSave = findViewById(R.id.btnSave);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnThem:
                Intent intent = new Intent(MainActivity.this, AddProductActivity.class);
                startActivityForResult(intent, 999);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 999 && resultCode == RESULT_OK && data != null) {
            SanPham sanPham = (SanPham) data.getSerializableExtra("sanpham");
            sanPhams.add(sanPham);
            adapter = new SanPhamAdapter(sanPhams, MainActivity.this, R.layout.sanpham_layout);
            listSanPham.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }

    public void loadListSanPham() {
        if (getList() != null) {
            for (SanPham sp : getList()) {
                sanPhams.add(sp);
            }
        }

        adapter = new SanPhamAdapter(sanPhams, MainActivity.this, R.layout.sanpham_layout);
        listSanPham.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public List<SanPham> getList() {
        List<SanPham> arrayItems = null;
        String jsonList = sharedPreferences.getString("danhsachsanpham", null);
        if (jsonList != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<SanPham>>(){}.getType();
            arrayItems = gson.fromJson(jsonList, type);
        }

        return arrayItems;
    }
}