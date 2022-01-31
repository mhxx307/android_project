package com.minhquan.a19441111_lavominhquan_bt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    Button btnSubmit;
    EditText edtA, edtB, edtC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        anhXa();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String A = edtA.getText().toString().trim();
                String B = edtB.getText().toString().trim();
                String C = edtC.getText().toString().trim();

                double a = Double.parseDouble(A);
                double b = Double.parseDouble(B);
                double c = Double.parseDouble(C);

                PhuongTrinhBac2 phuongTrinhBac2 = new PhuongTrinhBac2(a, b, c);
                String rs = phuongTrinhBac2.giai();

                Intent intent = new Intent();
                intent.putExtra("ketqua", rs);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private void anhXa() {
        btnSubmit = findViewById(R.id.btnSubmit);
        edtA = findViewById(R.id.edtA);
        edtB = findViewById(R.id.edtB);
        edtC = findViewById(R.id.edtC);
    }
}