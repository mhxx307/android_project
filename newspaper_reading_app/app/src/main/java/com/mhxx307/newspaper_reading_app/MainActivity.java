package com.mhxx307.newspaper_reading_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listviewTitle;
    List<String> arrayTitle, arrayLink;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listviewTitle = findViewById(R.id.listviewTitle);
        arrayTitle = new ArrayList<String>();
        arrayLink = new ArrayList<String>();

        adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, arrayTitle);
        listviewTitle.setAdapter(adapter);

        listviewTitle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, NewsActivity.class);
                intent.putExtra("link", arrayLink.get(i));
                startActivity(intent);
            }
        });

        new ReadRSS().execute("https://vnexpress.net/rss/the-thao.rss");
    }

    private class ReadRSS extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder content = new StringBuilder();
            try {
                URL url = new URL(strings[0]);
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String line = "";
                while((line = bufferedReader.readLine()) != null) {
                    content.append(line);
                }

                bufferedReader.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content.toString();
        }

        @Override
        protected void onPostExecute(String content) {
            super.onPostExecute(content);

            XMLDOMParser parser = new XMLDOMParser();
            Document document = parser.getDocument(content);

            NodeList nodeList = document.getElementsByTagName("item");

            String title = "";

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                title += parser.getValue(element, "title");
                arrayTitle.add(title);
                arrayLink.add(parser.getValue(element, "link"));
            }
        }
    }
}