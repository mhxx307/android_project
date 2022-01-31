package com.minhquan.intentexplicitex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    TextView txtKetQua;
    Button btnSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();

        Bundle bundle = intent.getBundleExtra("bundle");

        String ten = bundle.getString("ten");
        int tuoi = bundle.getInt("tuoi", 20);

        txtKetQua.setText("String: " + ten + "\n Tuoi: " + tuoi);
    }
}