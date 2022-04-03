package com.mhxx307.animationdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imgAlpha = findViewById(R.id.imageView_Alpha);
        ImageView imgBell = findViewById(R.id.imageView_bell);
        ImageView imgScale = findViewById(R.id.imageView_scale);
        ImageView imgTranslate = findViewById(R.id.imageView_translate);

        Animation anim_alpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        Animation anim_rotate = AnimationUtils.loadAnimation(this, R.anim.anim_rotate);
        Animation anim_scale = AnimationUtils.loadAnimation(this, R.anim.anim_scale);
        Animation anim_translate = AnimationUtils.loadAnimation(this, R.anim.anim_translate);

        imgAlpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(anim_alpha);
            }
        });

        imgBell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(anim_rotate);
            }
        });

        imgScale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(anim_scale);
            }
        });

        imgTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(anim_translate);
            }
        });
    }
}