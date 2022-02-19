package com.mhxx307.thuchanhtuan2_1_bt2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtCelsius, edtFahrenheit;
    Button btnConvertToC, btnConvertToF, btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();

        btnConvertToC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_fahrenheit = edtFahrenheit.getText().toString().trim();
                if (!str_fahrenheit.equals("")) {
                    double fahrenheit = Double.parseDouble(str_fahrenheit);
                    double celsius = (fahrenheit - 32) * (5*1.0/9);
                    edtCelsius.setText("" + celsius);
                } else {
                    Toast.makeText(MainActivity.this, "Edit text not allow null", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnConvertToF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_celsius = edtCelsius.getText().toString().trim();
                if (!str_celsius.equals("")) {
                    double celsius = Double.parseDouble(str_celsius);
                    double fahrenheit = celsius * (9/5) + 32;
                    edtFahrenheit.setText("" + fahrenheit);
                } else {
                    Toast.makeText(MainActivity.this, "Edit text not allow null", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void anhXa() {
        edtCelsius = findViewById(R.id.edtCelsius);
        edtFahrenheit = findViewById(R.id.edtFahrenheit);
        btnConvertToC = findViewById(R.id.btnConvertToC);
        btnConvertToF = findViewById(R.id.btnConvertToF);
        btnClear = findViewById(R.id.btnClear);
    }
}