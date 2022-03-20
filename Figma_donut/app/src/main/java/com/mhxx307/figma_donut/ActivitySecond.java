package com.mhxx307.figma_donut;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ActivitySecond extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        ImageView imgDonut = findViewById(R.id.imgDonut);
        TextView txtName = findViewById(R.id.txt_Name);
        TextView txtPrice = findViewById(R.id.txt_price);
        TextView txtContent = findViewById(R.id.txt_Content);

        Donut donut = (Donut) getIntent().getSerializableExtra("donut");
        int resId = donut.getImgDonut();
//        Toast.makeText(ActivitySecond.this, "" + resId, Toast.LENGTH_SHORT);
        imgDonut.setImageResource(resId);
        txtName.setText(donut.getDonutName());
        txtContent.setText(donut.getContent());
        txtPrice.setText("$"+donut.getPrice());
    }
}