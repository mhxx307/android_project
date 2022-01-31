package com.minhquan.intentdataresult_edittext;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText edtText;
    Button btnEdt;
    TextView txtName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtText = findViewById(R.id.edtText);
        btnEdt = findViewById(R.id.btnEdit);
        txtName = findViewById(R.id.txtName);

        btnEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivityForResult(intent, 123);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123 && resultCode == RESULT_OK && data != null) {
            String ten = data.getStringExtra("newName");
            String namSinh = data.getStringExtra("bd");

            int ns = Integer.parseInt(namSinh);
            int namHienTai = Calendar.getInstance().get(Calendar.YEAR);
            int tuoi = namHienTai - ns;

            String rs = "";

            rs += "Ho ten: " + ten;
            rs += "\nTuoi: " + tuoi;

            txtName.setText(rs);
        }
    }
}