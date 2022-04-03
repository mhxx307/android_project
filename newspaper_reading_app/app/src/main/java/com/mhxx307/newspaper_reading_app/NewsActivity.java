package com.mhxx307.newspaper_reading_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class NewsActivity extends AppCompatActivity {

    WebView webTinTuc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        webTinTuc = findViewById(R.id.web_tin_tuc);

        String link = getIntent().getStringExtra("link");

        webTinTuc.loadUrl(link);

        webTinTuc.setWebViewClient(new WebViewClient());
    }
}