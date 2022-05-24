package com.mhxx307.sqliteontap12_5_2022;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import com.mhxx307.sqliteontap12_5_2022.SqliteHelper.DatabaseHelper;
import com.mhxx307.sqliteontap12_5_2022.adapter.CategoryAdapter;
import com.mhxx307.sqliteontap12_5_2022.entity.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {
    EditText edtId, edtName;
    Button btnAdd, btnDelete, btnUpdate;
    GridView gridviewCategories;
    DatabaseHelper databaseHelper;
    CategoryAdapter categoryAdapter;
    List<Category> categories;

    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        mapping();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = Integer.parseInt(edtId.getText().toString());
                String name = edtName.getText().toString();

                Category category = new Category(id, name);

                if (category != null) {
                    databaseHelper.addCategory(category);
                    rendering();
                }
            }
        });

        gridviewCategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Category categorie = categories.get(i);
                id = categorie.getId();
                edtName.setText(categorie.getName());
                edtId.setText("" +id);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(CategoryActivity.this);
                alert.setTitle("xóa loại sản phẩm");
                alert.setMessage("Bạn có chắc muốn xóa");
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        databaseHelper.deleteCategory(id);
                        rendering();
                    }
                });
                alert.show();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = Integer.parseInt(edtId.getText().toString());
                String name = edtName.getText().toString();
                Category category = new Category(id, name);
                if (category != null) {
                    databaseHelper.updateCategory(category);
                    rendering();
                }
            }
        });
    }

    private void rendering() {
        categories = databaseHelper.getCategories();
        if (categories != null) {
            categoryAdapter = new CategoryAdapter(CategoryActivity.this, R.layout.category_adapter_layout, categories);
            gridviewCategories.setAdapter(categoryAdapter);
            categoryAdapter.notifyDataSetChanged();
        }
    }

    private void mapping() {
        edtId = findViewById(R.id.edtId);
        edtName = findViewById(R.id.edtName);
        btnAdd = findViewById(R.id.btnAdd);
        btnDelete= findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);
        gridviewCategories = findViewById(R.id.gridviewCategories);
        databaseHelper = new DatabaseHelper(this);

        rendering();
    }
}