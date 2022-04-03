package com.mhxx307.lavominhquan_tk3_fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class ProductInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);

        Intent intent = getIntent();
        Product product = (Product) intent.getSerializableExtra("product");

        FragmentProductInfo fragmentProductInfo = (FragmentProductInfo) getFragmentManager().findFragmentById(R.id.fragment_product_info);
        fragmentProductInfo.setInfo(product);
    }
}