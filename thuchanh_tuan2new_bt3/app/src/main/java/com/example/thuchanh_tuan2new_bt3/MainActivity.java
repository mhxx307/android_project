package com.example.thuchanh_tuan2new_bt3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listviewName;
    EditText editName;
    Button btnNhap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.edtName);
        btnNhap = findViewById(R.id.btnNhap);
        listviewName = findViewById(R.id.listviewName);

        List<String> arrayName = new ArrayList<String>();

        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, arrayName);
        listviewName.setAdapter(arrayAdapter);

        btnNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editName.getText().toString().trim();
                arrayName.add(name);
                arrayAdapter.notifyDataSetChanged();
                editName.setText("");
            }
        });
    }
}