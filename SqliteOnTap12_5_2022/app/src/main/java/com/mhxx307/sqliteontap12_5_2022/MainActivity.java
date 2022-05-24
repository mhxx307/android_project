package com.mhxx307.sqliteontap12_5_2022;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import com.mhxx307.sqliteontap12_5_2022.SqliteHelper.DatabaseHelper;
import com.mhxx307.sqliteontap12_5_2022.entity.Category;
import com.mhxx307.sqliteontap12_5_2022.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText edtId, edtName, edtPrice;
    Spinner spinnerCategory;
    GridView gridViewProducts;
    Button btnAdd, btnDelete, btnUpdate;
    DatabaseHelper databaseHelper;
    ArrayAdapter<String> adapter;
    List<Product> products;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapping();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Category category = databaseHelper.getCategory(spinnerCategory.getSelectedItem().toString());

                int idProduct = Integer.parseInt(edtId.getText().toString().trim());
                String name = edtName.getText().toString();
                Double price = Double.parseDouble(edtPrice.getText().toString());

                Product product = new Product(idProduct, name, price, category);
                Toast.makeText(MainActivity.this, "id:"+product.getId(), Toast.LENGTH_SHORT).show();
                if (product != null) {
                    databaseHelper.addProduct(product);
                    rendering();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseHelper.deleteProduct(id);
                rendering();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Product product = new Product(id, edtName.getText().toString(),
                        Double.parseDouble(edtPrice.getText().toString()), databaseHelper.getCategory(spinnerCategory.getSelectedItem().toString()));
                if (product != null) {
                    databaseHelper.updateProduct(product);
                    rendering();
                }
            }
        });

        gridViewProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Product product = products.get(i);
                id = product.getId();
                edtId.setText(""+id);
                edtName.setText(product.getName());
                edtPrice.setText(""+product.getPrice());
                spinnerCategory.setSelection(i);
            }
        });

    }

    private void rendering() {
        products = databaseHelper.getProducts();
        if (products != null) {
            List<String> productsString = new ArrayList<>();
            for(Product product : products) {
                productsString.add("id: " + product.getId() + "\nname: "
                        + product.getName() + "\nprice: " + product.getPrice() + "category name: " + product.getCategory().getName() );
            }
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, productsString);
            gridViewProducts.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }

    private void mapping() {
        btnAdd = findViewById(R.id.btnAdd);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);
        edtId = findViewById(R.id.edtId);
        edtName = findViewById(R.id.edtName);
        edtPrice = findViewById(R.id.edtPrice);
        spinnerCategory = findViewById(R.id.spinnerCaterogy);
        gridViewProducts = findViewById(R.id.gridviewProducts);

        databaseHelper = new DatabaseHelper(MainActivity.this);

        loadDataSpinner();

        rendering();

    }

    private void loadDataSpinner() {
        List<Category> categories = databaseHelper.getCategories();
        if (categories != null) {
            List<String> categoriesString = new ArrayList<>();
            for (Category tempCategory : categories) {
                categoriesString.add(tempCategory.getName());
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categoriesString);
            spinnerCategory.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnCategory:
                startActivity(new Intent(MainActivity.this, CategoryActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}