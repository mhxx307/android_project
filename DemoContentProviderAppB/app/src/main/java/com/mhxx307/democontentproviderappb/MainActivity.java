package com.mhxx307.democontentproviderappb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static final String AUTHORITY = "com.mhxx307.democontentprovider.provider";
    static final String PRODUCT_TABLE = "products";
    static final String URL = "content://"+AUTHORITY+"/"+PRODUCT_TABLE;
    static final Uri CONTENT_URI = Uri.parse(URL);

    EditText edtName, edtUnit, edtMadeIn;
    Button btnSave, btnDelete, btnUpdate;
    GridView gridviewProduct;

    ProductAdapter productAdapter;
    List<Product> products = new ArrayList<>();
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhXa();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("name", edtName.getText().toString());
                contentValues.put("unit", edtUnit.getText().toString());
                contentValues.put("made_in", edtMadeIn.getText().toString());

                Uri insertUri = getContentResolver().insert(CONTENT_URI, contentValues);
                if (insertUri != null) {
                    productAdapter.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(), "Save successful", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("name", edtName.getText().toString());
                contentValues.put("unit", edtUnit.getText().toString());
                contentValues.put("made_in", edtMadeIn.getText().toString());

                String urlProductId = URL + "/" + id;
                Uri uriProductId = Uri.parse(urlProductId);
                int isUpdate = getContentResolver().update(uriProductId, contentValues,null, null);
                if (isUpdate != 0) {
                    productAdapter.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(), "Update successful", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String urlProductId = URL + "/" + id;
                Uri uriProductId = Uri.parse(urlProductId);
                int isDelete = getContentResolver().delete(uriProductId, null, null);
                if (isDelete != 0) {
                    productAdapter.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(), "Delete successful", Toast.LENGTH_SHORT).show();
                }
            }
        });

        gridviewProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Product product = products.get(i);
                edtName.setText(product.getName());
                edtMadeIn.setText(product.getMadeIn());
                edtUnit.setText(product.getUnit());
                id = product.getId();
            }
        });
    }

    private void anhXa() {
        edtName = findViewById(R.id.edt_name);
        edtUnit = findViewById(R.id.edt_unit);
        edtMadeIn = findViewById(R.id.edt_madeIn);
        btnSave = findViewById(R.id.btn_save);
        btnUpdate = findViewById(R.id.btn_update);
        btnDelete = findViewById(R.id.btn_delete);
        gridviewProduct = findViewById(R.id.gridview_products);

        rendering();
    }

    // rendering gridview = custom adapter
    private void rendering() {
        Product product = null;
        Cursor cursor = getContentResolver().query(CONTENT_URI, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                product = new Product();
                product.setId(cursor.getInt(0));
                product.setName(cursor.getString(1));
                product.setUnit(cursor.getString(2));
                product.setMadeIn(cursor.getString(3));

                products.add(product);
                cursor.moveToNext();
            }
        } else {
            Toast.makeText(this, "No records found", Toast.LENGTH_SHORT).show();
        }
        productAdapter = new ProductAdapter(MainActivity.this, R.layout.layout_product_item, products);
        gridviewProduct.setAdapter(productAdapter);
        productAdapter.notifyDataSetChanged();
    }
}