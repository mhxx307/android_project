package com.mhxx307.sqliteontap_15_5_2022.sqliteHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.mhxx307.sqliteontap_15_5_2022.entity.Classroom;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, "classroom_directory", null, 1);
    }

    public void query(String sql) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL(sql);
    }

    public Cursor getData(String sql) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        return sqLiteDatabase.rawQuery(sql, null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS classrooms(id INTEGER PRIMARY KEY, name TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS students(id INTEGER PRIMARY KEY," +
                " name TEXT, email TEXT," +
                " classroomId INTEGER NOT NULL, CONSTRAINT classroom_fk FOREIGN KEY (classroomId) REFERENCES classrooms(id) ON DELETE CASCADE ON UPDATE CASCADE )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS classrooms");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS students");
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
        super.onConfigure(db);
    }

    public List<Classroom> findAllClass() {
        List<Classroom> classrooms = new ArrayList<>();
        Cursor result = getData("SELECT * FROM classrooms");
        if (result != null && result.moveToFirst()) {
            while (!result.isAfterLast()) {
                int id = result.getInt(0);
                String name = result.getString(1);
                Classroom classroom = new Classroom(id, name);
                classrooms.add(classroom);

                result.moveToNext();
            }
            result.close();
            return classrooms;
        }
        return null;
    }

    public Classroom findClassById(int id) {
        Classroom classroom = null;
        Cursor result = getData("SELECT * FROM classrooms where id = " + id);
        if (result != null && result.moveToFirst()) {
            while (!result.isAfterLast()) {
                String name = result.getString(1);
                classroom = new Classroom(id, name);

                result.moveToNext();
            }
            result.close();
        }
        return classroom;
    }

    public void insertClass(Classroom classroom) {
        query("INSERT INTO classrooms VALUES('" + classroom.getId() + "','" + classroom.getName() + "')");
    }

    public int insertClassContentValues(Classroom classroom) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", classroom.getId());
        contentValues.put("name", classroom.getName());
        int rs = (int) database.insert("classrooms", null, contentValues);
        database.close();
        return rs;
    }

    public void deleteClass(int id) {
        query("DELETE FROM classrooms WHERE id = " + id);
    }

    public void updateClass(Classroom classroom) {
        query("UPDATE classrooms SET name = '"+classroom.getName()+"' WHERE id = '"+classroom.getId()+"'");
    }
}
