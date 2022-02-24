package com.mhxx307.imageviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

    ImageView img1, img2, img3;
    Button btnRutNgauNhien;
    TextView txtKetQua;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();

        btnRutNgauNhien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int[] value = layBaSoNgauNhien(0, 51);
                img1.setImageResource(arrayCard[value[0]]);
                img2.setImageResource(arrayCard[value[1]]);
                img3.setImageResource(arrayCard[value[2]]);
                txtKetQua.setText(tinhKetQua(value) + "");
            }
        });
    }

    private void anhXa() {
        btnRutNgauNhien  = findViewById(R.id.btnRutNgauNhien);
        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        txtKetQua = findViewById(R.id.txtKetQua);
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
        Toast.makeText(MainActivity.this, "" + soTay, Toast.LENGTH_LONG).show();
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


}