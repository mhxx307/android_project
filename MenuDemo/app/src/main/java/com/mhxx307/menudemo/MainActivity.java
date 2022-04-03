package com.mhxx307.menudemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMenu();
            }
        });
    }

    private void showMenu() {
        PopupMenu ppMenu = new PopupMenu(this, btn);
        getMenuInflater().inflate(R.menu.menu_demo, ppMenu.getMenu());
        ppMenu.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_demo, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.mnAdd:
                Toast.makeText(MainActivity.this, "Thêm" , Toast.LENGTH_SHORT);
                break;
            case R.id.mnDelete:
                Toast.makeText(MainActivity.this, "Xóa" , Toast.LENGTH_SHORT);
                break;
            case R.id.mnUpdate:
                Toast.makeText(MainActivity.this, "Cập nhật" , Toast.LENGTH_SHORT);
                break;
            case R.id.mnList:
                Toast.makeText(MainActivity.this, "Danh sách" , Toast.LENGTH_SHORT);
                break;
            case R.id.mnListNhanVien:
                Toast.makeText(MainActivity.this, "Danh sách nhân viên" , Toast.LENGTH_SHORT);
                break;
            case R.id.mnListLopHoc:
                Toast.makeText(MainActivity.this, "Danh sách lớp học" , Toast.LENGTH_SHORT);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}