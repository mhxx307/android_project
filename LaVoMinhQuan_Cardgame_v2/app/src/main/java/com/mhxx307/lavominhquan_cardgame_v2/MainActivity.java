package com.mhxx307.lavominhquan_cardgame_v2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    int arrayCard[]={
            R.drawable.c1,R.drawable.c2,R.drawable.c3,R.drawable.c4,R.drawable.c5,
            R.drawable.c6,R.drawable.c7,R.drawable.c8,R.drawable.c9,R.drawable.c10,
            R.drawable.cj,R.drawable.cq,R.drawable.ck,
            R.drawable.d1,R.drawable.d2,R.drawable.d3,R.drawable.d4,R.drawable.d5,
            R.drawable.d6,R.drawable.d7,R.drawable.d8,R.drawable.d9,R.drawable.d10,
            R.drawable.dj,R.drawable.dq,R.drawable.dk,
            R.drawable.h1,R.drawable.h2,R.drawable.h3,R.drawable.h4,R.drawable.h5,
            R.drawable.h6,R.drawable.h7,R.drawable.h8,R.drawable.h9,R.drawable.h10,
            R.drawable.hj,R.drawable.hq,R.drawable.hk,
            R.drawable.s1,R.drawable.s2,R.drawable.s3,R.drawable.s4,R.drawable.s5,
            R.drawable.s6,R.drawable.s7,R.drawable.s8,R.drawable.s9,R.drawable.s10,
            R.drawable.sj,R.drawable.sq,R.drawable.sk};

    ImageView img1, img2, img3, img4, img5, img6;
    Button btnBatDau, btnChoiLai;
    TextView txtKetQuaMay1, txtKetQuaMay2, txtTiSoMay1, txtTiSoMay2;
    EditText edtChonThoiGian, edtChonBuocNhay;
    int score1 = 0, score2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();

        btnBatDau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_second = edtChonThoiGian.getText().toString().trim();
                String str_secondBuocNhay = edtChonBuocNhay.getText().toString().trim();
                if (!str_second.equals("") && !str_secondBuocNhay.equals("")) {
                    int second = Integer.parseInt(str_second);
                    int secondBuocNhay = Integer.parseInt(str_secondBuocNhay);
                    handler(second, secondBuocNhay);
                } else {
                    Toast.makeText(MainActivity.this, "Cần phải chọn thời gian cho máy chơi", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnChoiLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thongBaoChoiLai("Thông báo", "Bạn có chắc muốn chơi lại!");
            }
        });

    }

    private void anhXa() {
        btnBatDau  = findViewById(R.id.btnBatDau);
        btnChoiLai = findViewById(R.id.btnChoiLai);
        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        img4 = findViewById(R.id.img4);
        img5 = findViewById(R.id.img5);
        img6 = findViewById(R.id.img6);
        txtKetQuaMay1 = findViewById(R.id.txtKetQuaMay1);
        txtKetQuaMay2 = findViewById(R.id.txtKetQuaMay2);
        txtTiSoMay1 = findViewById(R.id.txtTiSoMay1);
        txtTiSoMay2 = findViewById(R.id.txtTiSoMay2);
        edtChonThoiGian = findViewById(R.id.edtChonThoiGian);
        edtChonBuocNhay = findViewById(R.id.edtChonBuocNhay);
    }

    private void handler(int second, int secondBuocNhay) {
        CountDownTimer countDownTimer = new CountDownTimer(second * 1000, secondBuocNhay * 1000) {
            @Override
            public void onTick(long l) {
                int[] valueMachine1 = layBaSoNgauNhien(0, 51);
                int[] valueMachine2 = layBaSoNgauNhien(0, 51);

                img1.setImageResource(arrayCard[valueMachine1[0]]);
                img2.setImageResource(arrayCard[valueMachine1[1]]);
                img3.setImageResource(arrayCard[valueMachine1[2]]);
                txtKetQuaMay1.setText("MÁY 1: " + tinhKetQua(valueMachine1));

                img4.setImageResource(arrayCard[valueMachine2[0]]);
                img5.setImageResource(arrayCard[valueMachine2[1]]);
                img6.setImageResource(arrayCard[valueMachine2[2]]);
                txtKetQuaMay2.setText("MÁY 2:" + tinhKetQua(valueMachine2));

                String ketQua = soSanhKetQua(valueMachine1, valueMachine2);
                Toast.makeText(MainActivity.this, ketQua, Toast.LENGTH_SHORT).show();

                if (ketQua.toLowerCase().contains("1")) {
                    score1 += 1;
                } else if (ketQua.toLowerCase().contains("2")) {
                    score2 += 1;
                } else {
                    score1 += 0;
                    score2 += 0;
                }
                txtTiSoMay1.setText("Tỉ số máy 1: " + score1);
                txtTiSoMay2.setText("Tỉ số máy 2: " + score2);
            }

            @Override
            public void onFinish() {
                img1.setImageResource(R.drawable.matlunglabai);
                img2.setImageResource(R.drawable.matlunglabai);
                img3.setImageResource(R.drawable.matlunglabai);
                img4.setImageResource(R.drawable.matlunglabai);
                img5.setImageResource(R.drawable.matlunglabai);
                img6.setImageResource(R.drawable.matlunglabai);

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Thông báo");
                if (score1 > score2) {
                    alert.setMessage("Người thắng là máy 1");
                } else if (score1 < score2) {
                    alert.setMessage("Người thắng là máy 2");
                } else {
                    alert.setMessage("Hòa");
                }
                alert.setNegativeButton("Đóng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                alert.show();
            }
        }.start();
    }

    private void thongBaoChoiLai(String title, String message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                score1 = 0;
                score2 = 0;
                txtKetQuaMay1.setText("Máy 1:");
                txtKetQuaMay2.setText("Máy 2:");
                txtTiSoMay1.setText("Tỉ số máy 1: " + score1);
                txtTiSoMay2.setText("Tỉ số máy 2: " + score2);
                edtChonThoiGian.setText("");
            }
        });

        alertDialog.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        alertDialog.show();
    }

    private int[] layBaSoNgauNhien(int min, int max) {
        int[] randomNumbers = new int[3];
        int i = 0;
        randomNumbers[i++] = random(min, max);
        do {
            int k = random(min, max);
            if (!kiemTraKhongTrung(k, randomNumbers)) {
                randomNumbers[i++] = k;
            }
        } while (i < 3);
        return randomNumbers;
    }


    private boolean kiemTraKhongTrung(int k, int a[]) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == k) {
                return true;
            }
        }
        return false;
    }

    private int random(int min, int max) {
        return min + (int)(Math.random() * (max-min) + 1);
    }

    private int tinhSoTay(int[] baSoNgauNhien) {
        int k = 0;
        for (int i = 0; i < baSoNgauNhien.length; i++) {
            if (baSoNgauNhien[i] % 13 >= 10 && baSoNgauNhien[i] % 13 < 13) {
                k++;
            }
        }
        return k;
    }

    private String tinhKetQua(int[] baSoNgauNhien) {
        String ketQua = "";
        int soTay = tinhSoTay(baSoNgauNhien);
        if (soTay == 3) {
            ketQua = "Kết quả: 3 tây";
        } else {
            int tong = 0;

            for (int i = 0; i < baSoNgauNhien.length; i++) {
                if (baSoNgauNhien[i] % 13 < 10)
                    tong += baSoNgauNhien[i] % 13 + 1;
            }

            if (tong % 10 == 0) {
                ketQua = "Kết quả bù, trong đó có " + soTay + " tây";
            } else {
                ketQua = "Kết quả là " + (tong % 10) + " nút, trong đó có " + soTay + " tây";
            }
        }

        return ketQua;
    }

    private String soSanhKetQua(int[] valueMachine1, int[] valueMachine2) {
        String ketQua = "";
        int soTayMachine1 = tinhSoTay(valueMachine1);
        int soTayMachine2 = tinhSoTay(valueMachine2);

        if (soTayMachine1 == 3 && soTayMachine2 != 3) {
            return "Máy 1 thắng";
        } else if (soTayMachine1 != 3 && soTayMachine2 == 3) {
            return "Máy 2 thắng";
        }

        if (soTayMachine1 == 3 && soTayMachine2 == 3) {
            ketQua = "Hòa";
        } else {
            int tongMachine1 = 0;
            int tongMachine2 = 0;

            for (int i = 0; i < valueMachine1.length; i++) {
                if (valueMachine1[i] % 13 < 10)
                    tongMachine1 += valueMachine1[i] % 13 + 1;
            }

            for (int i = 0; i < valueMachine2.length; i++) {
                if (valueMachine2[i] % 13 < 10)
                    tongMachine2 += valueMachine2[i] % 13 + 1;
            }

            int soNutMachine1 = tongMachine1 % 10;
            int soNutMachine2 = tongMachine2 % 10;

            if (soNutMachine1 == soNutMachine2) {
                ketQua = "Hòa";
            } else if (soNutMachine1 > soNutMachine2) {
                ketQua = "Máy 1 thắng";
            } else if (soNutMachine1 < soNutMachine2) {
                ketQua = "Máy 2 thắng";
            }
        }

        return ketQua;
    }
}