package com.mhxx307.lavominhquan_cardgame_v1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

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
    Button btnRutNgauNhien, btnChoiLai;
    TextView txtKetQua, txtKetQuaMay, txtScore;
    int score = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();

        txtScore.setText("" + score);

        btnRutNgauNhien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler();
            }
        });

        btnChoiLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thongBaoChoiLai("Thông báo", "Bạn có chắc muốn chơi lại!");
            }
        });
    }

    private void handler() {
        if (img4.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.matlunglabai).getConstantState())) {
            int[] valuePlayer = layBaSoNgauNhien(0, 51);
            int[] valueMachine = layBaSoNgauNhien(0, 51);

            img1.setImageResource(arrayCard[valuePlayer[0]]);
            img2.setImageResource(arrayCard[valuePlayer[1]]);
            img3.setImageResource(arrayCard[valuePlayer[2]]);
            txtKetQua.setText("PLAYER: " + tinhKetQua(valuePlayer));
            txtKetQuaMay.setText("MÁY:...");

            CountDownTimer countDownTimer = new CountDownTimer(5000, 3000) {
                @Override
                public void onTick(long l) {
                    img4.setImageResource(arrayCard[valueMachine[0]]);
                    img5.setImageResource(arrayCard[valueMachine[1]]);
                    img6.setImageResource(arrayCard[valueMachine[2]]);
                    txtKetQuaMay.setText("MÁY:" + tinhKetQua(valueMachine));
                    String ketQua = soSanhKetQua(valuePlayer, valueMachine);
                    Toast.makeText(MainActivity.this, ketQua, Toast.LENGTH_SHORT).show();

                    if (ketQua.toLowerCase().contains("hòa")) {
                        score -= 5;
                    }
                    else if (ketQua.toLowerCase().contains("player")) {
                        score += 10;
                    } else {
                        score -= 10;
                        if (score == 0) {
                            thongBaoChoiLai("Thông báo", "Bạn đã hết điểm, chơi lại nhé!");
                        }
                    }
                    txtScore.setText("" + score);
                }

                @Override
                public void onFinish() {
                    img1.setImageResource(R.drawable.matlunglabai);
                    img2.setImageResource(R.drawable.matlunglabai);
                    img3.setImageResource(R.drawable.matlunglabai);
                    img4.setImageResource(R.drawable.matlunglabai);
                    img5.setImageResource(R.drawable.matlunglabai);
                    img6.setImageResource(R.drawable.matlunglabai);
                }
            }.start();
        } else {
            Toast.makeText(MainActivity.this, "Chờ tí nhé!", Toast.LENGTH_SHORT).show();
        }
    }

    private void thongBaoChoiLai(String title, String message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                txtScore.setText("" + 100);
                img1.setImageResource(R.drawable.matlunglabai);
                img2.setImageResource(R.drawable.matlunglabai);
                img3.setImageResource(R.drawable.matlunglabai);
                img4.setImageResource(R.drawable.matlunglabai);
                img5.setImageResource(R.drawable.matlunglabai);
                img6.setImageResource(R.drawable.matlunglabai);
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

    private void anhXa() {
        btnRutNgauNhien  = findViewById(R.id.btnRutNgauNhien);
        btnChoiLai = findViewById(R.id.btnChoiLai);
        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        img4 = findViewById(R.id.img4);
        img5 = findViewById(R.id.img5);
        img6 = findViewById(R.id.img6);
        txtKetQua = findViewById(R.id.txtKetQua);
        txtKetQuaMay = findViewById(R.id.txtKetQuaMay);
        txtScore = findViewById(R.id.txtScore);
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

    private String soSanhKetQua(int[] valuePlayer, int[] valueMachine) {
        String ketQua = "";
        int soTayPlayer = tinhSoTay(valuePlayer);
        int soTayMachine = tinhSoTay(valueMachine);

        if (soTayPlayer == 3 && soTayMachine != 3) {
            return "Player thắng";
        } else if (soTayPlayer != 3 && soTayMachine == 3) {
            return "Máy thắng";
        }

        if (soTayPlayer == 3 && soTayMachine == 3) {
            ketQua = "Hòa";
        } else {
            int tongPlayer = 0;
            int tongMachine = 0;

            for (int i = 0; i < valuePlayer.length; i++) {
                if (valuePlayer[i] % 13 < 10)
                    tongPlayer += valuePlayer[i] % 13 + 1;
            }

            for (int i = 0; i < valueMachine.length; i++) {
                if (valueMachine[i] % 13 < 10)
                    tongMachine += valueMachine[i] % 13 + 1;
            }

            int soNutPlayer = tongPlayer % 10;
            int soNutMachine = tongMachine % 10;

            if (soNutPlayer == soNutMachine) {
                ketQua = "Hòa";
            } else if (soNutPlayer > soNutMachine) {
                ketQua = "Player thắng";
            } else if (soNutPlayer < soNutMachine) {
                ketQua = "Máy thắng";
            }
        }

        return ketQua;
    }
}