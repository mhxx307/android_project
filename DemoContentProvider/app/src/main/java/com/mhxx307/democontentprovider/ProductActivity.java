package com.mhxx307.democontentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.mhxx307.democontentprovider.adapter.ProductAdapter;
import com.mhxx307.democontentprovider.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity {
    EditText edtName, edtUnit, edtMadeIn;
    Button btnSave, btnDelete, btnUpdate;
    GridView gridviewProduct;
    ProductAdapter productAdapter;
    List<Product> products = new ArrayList<>();
    static final String URI = "content://com.mhxx307.democontentprovider.provider/products";
    int id;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        anhXa();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("name", edtName.getText().toString());
                contentValues.put("unit", edtUnit.getText().toString());
                contentValues.put("made_in", edtMadeIn.getText().toString());
                Uri productUri = Uri.parse(URI);
                Uri insertUri = getContentResolver().insert(productUri, contentValues);
                if (insertUri != null) {
                    products.clear();
                    rendering();
                    Toast.makeText(getApplicationContext(), "Save successful", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("id", id);
                contentValues.put("name", edtName.getText().toString());
                contentValues.put("unit", edtUnit.getText().toString());
                contentValues.put("made_in", edtMadeIn.getText().toString());

                String url_ = URI + "/" + id;
                Uri productUri = Uri.parse(url_);
                int isUpdate = getContentResolver().update(productUri, contentValues,null, null);
                if (isUpdate != 0) {
                    products.clear();
                    rendering();
                    Toast.makeText(getApplicationContext(), "Update successful", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url_ = URI + "/" + id;
                Uri productUri = Uri.parse(url_);
                int isDelete = getContentResolver().delete(productUri, null, null);
                if (isDelete != 0) {
                    products.clear();
                    rendering();
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

                position = i;

                Toast.makeText(ProductActivity.this, "id:"+id, Toast.LENGTH_SHORT).show();
            }
        });

    }

    // rendering gridview = custom adapter
    private void rendering() {
        Product product = null;
        Uri productUri = Uri.parse(URI);
        Cursor cursor = getContentResolver().query(productUri, null, null, null, null);
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
        productAdapter = new ProductAdapter(ProductActivity.this, R.layout.layout_product_item, products);
        gridviewProduct.setAdapter(productAdapter);
        productAdapter.notifyDataSetChanged();
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


}