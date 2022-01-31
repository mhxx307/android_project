package com.minhquan.radiobutton_kpt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioGroup rdGroup;
    RadioButton rdSang, rdChieu, rdToi;
    Button btn1;
    TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rdGroup = findViewById(R.id.rdGroup);
        rdSang = findViewById(R.id.rdSang);
        rdChieu = findViewById(R.id.rdChieu);
        rdToi = findViewById(R.id.rdToi);
        btn1 = findViewById(R.id.btn1);
        txtResult = findViewById(R.id.txtResult);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rdSang.isChecked()) {
                    txtResult.setText("Morning");
                }
                if (rdChieu.isChecked()) {
                    txtResult.setText("Afternoon");
                }
                if (rdToi.isChecked()) {
                    txtResult.setText("Night");
                }
            }
        });

        rdGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                rdGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        switch (i) {
                            case R.id.rdSang:
                                Toast.makeText(MainActivity.this, "Ban chon sang", Toast.LENGTH_LONG).show();
                                break;
                            case R.id.rdChieu:
                                Toast.makeText(MainActivity.this, "Ban chon chieu", Toast.LENGTH_LONG).show();
                                break;
                            case R.id.rdToi:
                                Toast.makeText(MainActivity.this, "Ban chon toi", Toast.LENGTH_LONG).show();
                                break;
                        }
                    }
                });
            }
        });
    }
}