package com.minhquan.randombackground;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    LinearLayout myLinearlayout;
    ArrayList<Integer> arrayImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        myLinearlayout = findViewById(R.id.myLinearlayout);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayImage = new ArrayList<>();

        arrayImage.add(R.drawable.bg1);
        arrayImage.add(R.drawable.bg2);
        arrayImage.add(R.drawable.bg3);
        arrayImage.add(R.drawable.bg4);

        Random random = new Random();
        int lengths = arrayImage.size();
        int locate = random.nextInt(lengths);

        myLinearlayout.setBackgroundResource(arrayImage.get(locate));

//        myLinearlayout.setBackgroundResource(R.drawable.ic_launcher_background);
    }
}