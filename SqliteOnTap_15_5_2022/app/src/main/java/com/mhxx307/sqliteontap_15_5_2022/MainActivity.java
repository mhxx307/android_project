package com.mhxx307.sqliteontap_15_5_2022;

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
import android.widget.Toast;

import com.mhxx307.sqliteontap_15_5_2022.entity.Classroom;
import com.mhxx307.sqliteontap_15_5_2022.sqliteHelper.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText edtId, edtName;
    Button btnAdd, btnDelete, btnUpdate;
    GridView gridViewClass;
    List<Classroom> classrooms;
    DatabaseHelper dbHelper;
    ArrayAdapter<String> adapter;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapping();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = Integer.parseInt(edtId.getText().toString().trim());
                String name = edtName.getText().toString();
                Classroom classroom = new Classroom(id, name);

                int isInsert = dbHelper.insertClassContentValues(classroom);
                if (isInsert > 0) {
                    Toast.makeText(MainActivity.this, "Them thanh cong", Toast.LENGTH_SHORT).show();
                    rendering();
                } else {
                    Toast.makeText(MainActivity.this, "Them khong thanh cong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("canh bao");
                alert.setMessage("Ban co chac muon xoa?");
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dbHelper.deleteClass(id);
                        rendering();
                    }
                });
                alert.show();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = Integer.parseInt(edtId.getText().toString().trim());
                String name = edtName.getText().toString();
                Classroom classroom = new Classroom(id, name);
                if (classroom != null) {
                    dbHelper.updateClass(classroom);
                    rendering();
                }
            }
        });

        gridViewClass.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Classroom classroom = classrooms.get(i);
                id = classroom.getId();
                edtId.setText(""+classroom.getId());
                edtName.setText(classroom.getName());
            }
        });
    }

    private void rendering() {
        classrooms = dbHelper.findAllClass();
        if (classrooms != null) {
            List<String> classroomsString = new ArrayList<>();
            for (Classroom classroom : classrooms) {
                classroomsString.add("id: " + classroom.getId() + "\nname: " + classroom.getName());
            }
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, classroomsString);
            gridViewClass.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }

    private void mapping() {
        edtId = findViewById(R.id.edtId);
        edtName = findViewById(R.id.edtName);
        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        gridViewClass = findViewById(R.id.gridviewClassrooms);

        dbHelper = new DatabaseHelper(this);

        rendering();
    }
}