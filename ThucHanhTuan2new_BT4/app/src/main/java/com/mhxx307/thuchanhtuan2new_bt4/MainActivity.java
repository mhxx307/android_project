package com.mhxx307.thuchanhtuan2new_bt4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity {

    EditText edtNhapTen;
    Button btnNhapTen;
    TextView txtKetQua;
    List<String> nameList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNhapTen = findViewById(R.id.edtNhapTen);
        btnNhapTen = findViewById(R.id.btnNhapTen);
        txtKetQua = findViewById(R.id.txtKetQua);

        nameList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nameList);

        setListAdapter((ListAdapter) adapter);

        btnNhapTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameList.add(edtNhapTen.getText().toString());
                adapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String item = (String)getListAdapter().getItem(position);
        txtKetQua.setText("position: " + position + "; value: " + item + "");
    }
}