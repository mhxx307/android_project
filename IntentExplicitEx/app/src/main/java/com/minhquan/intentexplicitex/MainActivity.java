package com.minhquan.intentexplicitex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnMain = findViewById(R.id.btnMainActivity);

        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("ten", "La Vo Minh Quan");
                bundle.putInt("tuoi", 20);

                ArrayList<String> listString = new ArrayList<>();
                listString.add("String 1");
                listString.add("String 2");

                bundle.putStringArrayList("list", listString);

                Student student1 = new Student("Minh Quan", "2001");

                bundle.putSerializable("hocsinh", student1);

                intent.putExtra("bundle", bundle);

                startActivity(intent);
            }
        });
    }
}