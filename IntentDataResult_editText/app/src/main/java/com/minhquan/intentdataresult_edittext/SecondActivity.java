package com.minhquan.intentdataresult_edittext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity {

    Button btnConfirm;
    EditText edtText, edtBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btnConfirm = findViewById(R.id.btnConfirm);
        edtText = findViewById(R.id.edtText);
        edtBD = findViewById(R.id.edtBD);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newName = edtText.getText().toString().trim();
                String namSinh = edtBD.getText().toString().trim();

                Intent intent = new Intent();
                intent.putExtra("newName", newName);
                intent.putExtra("bd", namSinh);

                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }
}