package com.minhquan.tong2so;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtFirstNum, edtSecondNum;
    TextView txtResult;
    Button btnSum, btnTru, btnNhan, btnChia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();

        btnSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strFirstNum = edtFirstNum.getText().toString().trim();
                String strSecondNum = edtSecondNum.getText().toString().trim();

                if (strFirstNum.isEmpty() || strSecondNum.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Nhap day du vao", Toast.LENGTH_LONG).show();
                } else {
                    double firstNum = Double.parseDouble(strFirstNum);
                    double secondNum = Double.parseDouble(strSecondNum);

                    double tong = firstNum + secondNum;
                    txtResult.setText(String.valueOf(tong));
                }
            }
        });

        btnTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strFirstNum = edtFirstNum.getText().toString().trim();
                String strSecondNum = edtSecondNum.getText().toString().trim();

                if (strFirstNum.isEmpty() || strSecondNum.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Nhap day du vao", Toast.LENGTH_LONG).show();
                } else {
                    double firstNum = Double.parseDouble(strFirstNum);
                    double secondNum = Double.parseDouble(strSecondNum);

                    double hieu = firstNum - secondNum;
                    txtResult.setText(String.valueOf(hieu));
                }
            }
        });

        btnNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strFirstNum = edtFirstNum.getText().toString().trim();
                String strSecondNum = edtSecondNum.getText().toString().trim();

                if (strFirstNum.isEmpty() || strSecondNum.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Nhap day du vao", Toast.LENGTH_LONG).show();
                } else {
                    double firstNum = Double.parseDouble(strFirstNum);
                    double secondNum = Double.parseDouble(strSecondNum);

                    double tich = firstNum * secondNum;
                    txtResult.setText(String.valueOf(tich));
                }
            }
        });

        btnChia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strFirstNum = edtFirstNum.getText().toString().trim();
                String strSecondNum = edtSecondNum.getText().toString().trim();

                if (strFirstNum.isEmpty() || strSecondNum.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Nhap day du vao", Toast.LENGTH_LONG).show();
                } else {
                    double firstNum = Double.parseDouble(strFirstNum);
                    double secondNum = Double.parseDouble(strSecondNum);

                    double thuong = firstNum / secondNum;
                    txtResult.setText(String.valueOf(thuong));
                }
            }
        });

    }

    private void anhXa() {
        edtFirstNum = findViewById(R.id.edtFirstNum);
        edtSecondNum = findViewById(R.id.edtSecondNum);
        txtResult = findViewById(R.id.txtResult);
        btnSum = findViewById(R.id.btnSum);
        btnTru = findViewById(R.id.btnTru);
        btnNhan = findViewById(R.id.btnNhan);
        btnChia = findViewById(R.id.btnChia);
    }

}