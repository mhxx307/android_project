package com.mhxx307.thuchanhtuan2_1_bt3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText edtYear;
    Button btnChuyendoi;
    TextView txtKetqua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();

        btnChuyendoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String can = null, chi = null;
                String nam = edtYear.getText().toString().trim();

                if (!nam.equals("")) {
                    int namDuong = Integer.parseInt(nam);
                    int caseCan = namDuong % 10;
                    int caseChi = namDuong % 12;

                    switch (caseCan) {
                        case 0: {
                            can = "Canh";
                            break;
                        }
                        case 1: {
                            can = "Tân";
                            break;
                        }
                        case 2: {
                            can = "Nhâm";
                            break;
                        }
                        case 3: {
                            can = "Quý";
                            break;
                        }
                        case 4: {
                            can = "Giáp";
                            break;
                        }
                        case 5: {
                            can = "ất";
                            break;
                        }
                        case 6: {
                            can = "Bính";
                            break;
                        }
                        case 7: {
                            can = "đinh";
                            break;
                        }
                        case 8: {
                            can = "Mậu";
                            break;
                        }
                        case 9: {
                            can = "Kỷ";
                            break;
                        }
                    }

                    switch (caseChi) {
                        case 0: {
                            chi = "Thân";
                            break;
                        }
                        case 1: {
                            chi = "Dậu";
                            break;
                        }
                        case 2: {
                            chi = "Tuất";
                            break;
                        }
                        case 3: {
                            chi = "Hợi";
                            break;
                        }
                        case 4: {
                            chi = "Tí";
                            break;
                        }
                        case 5: {
                            chi = "Sửu";
                            break;
                        }
                        case 6: {
                            chi = "Dần";
                            break;
                        }
                        case 7: {
                            chi = "Mẹo";
                            break;
                        }
                        case 8: {
                            chi = "Thìn";
                            break;
                        }
                        case 9: {
                            chi = "Tỵ";
                            break;
                        }
                        case 10: {
                            chi = "Ngọ";
                            break;
                        }
                        case 11: {
                            chi = "Mùi";
                            break;
                        }
                    }


                    String namAmLich = can + " " + chi;
                    txtKetqua.setText(namAmLich);
                }
            }
        });
    }

    private void anhXa() {
        edtYear = findViewById(R.id.edtYear);
        btnChuyendoi = findViewById(R.id.btnChuyendoi);
        txtKetqua = findViewById(R.id.txtKetqua);
    }

}