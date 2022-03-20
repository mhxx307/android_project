package com.mhxx307.thuchanhtuan3_bt3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText edtNoiDung;
    Button btnReadInternalFile, btnWriteInternalFile;
    String fileName = "sample.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNoiDung = findViewById(R.id.edtNoiDung);
        btnReadInternalFile = findViewById(R.id.btnReadInternalFile);
        btnWriteInternalFile = findViewById(R.id.btnWriteInternalFile);

        btnReadInternalFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readInternalFile();
            }
        });

        btnWriteInternalFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeInternalFile(edtNoiDung.getText().toString());
            }
        });

    }

    public void readInternalFile() {
        try {
            FileInputStream inputStream = openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(inputStream);
            char[] inputBuffer = new char[100];
            String str = "";
            int charRead;
            while((charRead = isr.read(inputBuffer)) > 0) {
                String readStr = String.copyValueOf(inputBuffer, 0, charRead);
                str = str + readStr;
            }
            Toast.makeText(MainActivity.this, "" + str, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void writeInternalFile(String noiDung) {
        try {
            FileOutputStream outputStream = openFileOutput(fileName, MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(outputStream);
            osw.write(noiDung);
            osw.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}