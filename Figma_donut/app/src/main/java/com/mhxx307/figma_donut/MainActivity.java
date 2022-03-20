package com.mhxx307.figma_donut;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView rclView;
    DonutAdapter donutAdapter;
    ArrayList<Donut> donuts;
    SearchView edtSearchFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();

        donutAdapter = new DonutAdapter(donuts, this);
        rclView.setAdapter(donutAdapter);
    }

    private void anhXa() {
        rclView = findViewById(R.id.recylerViewDonut);
        rclView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rclView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, linearLayoutManager.getOrientation());
        rclView.addItemDecoration(dividerItemDecoration);

        edtSearchFood = findViewById(R.id.edtSearchFood);

        donuts = new ArrayList<>();
        donuts.add(new Donut(R.drawable.donut_yellow, "Tasty donut", "Spicy tasty donut family", 10.00));
        donuts.add(new Donut(R.drawable.pink_donut, "Pink donut", "Spicy tasty donut family", 20.00));
        donuts.add(new Donut(R.drawable.green_donut, "Floating donut", "Spicy tasty donut family", 30.00));
        donuts.add(new Donut(R.drawable.red_donut, "Tasty donut", "Spicy tasty donut family", 40.00));
    }
}