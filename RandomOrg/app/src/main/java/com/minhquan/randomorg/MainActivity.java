package com.minhquan.randomorg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText edtMax;
    EditText edtMin;
    Button btnRandom;
    TextView txtRandom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();

        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String chuoiMax = edtMax.getText().toString().trim();
                String chuoiMin = edtMin.getText().toString().trim();

                if (chuoiMax.isEmpty() || chuoiMin.isEmpty()) {
                    String noiDung = "Vui long nhap day du noi dung";
                    Toast.makeText(MainActivity.this, noiDung, Toast.LENGTH_LONG).show();
                } else {
                    int max = Integer.valueOf(chuoiMax);
                    int min = Integer.valueOf(chuoiMin);

                    if (max <= min) {
                        Toast.makeText(MainActivity.this, "max phai lon hon min", Toast.LENGTH_LONG).show();
                    } else {
                        Random random = new Random();
                        int randomNumber = random.nextInt((max - min) + 1) + min;
                        txtRandom.setText(randomNumber + "");
                    }
                }

            }
        });
    }

    private void anhXa() {
        edtMax = findViewById(R.id.edtMax);
        edtMin = findViewById(R.id.edtMin);
        btnRandom = findViewById(R.id.btnRandom);
        txtRandom = findViewById(R.id.randomNumber);
    }
}