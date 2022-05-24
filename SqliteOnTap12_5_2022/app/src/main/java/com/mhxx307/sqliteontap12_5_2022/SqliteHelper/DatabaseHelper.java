package com.mhxx307.sqliteontap12_5_2022.SqliteHelper;

import android.content.Context;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.mhxx307.sqliteontap12_5_2022.entity.Category;
import com.mhxx307.sqliteontap12_5_2022.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context) {
        super(context, "product_directory", null, 1);
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
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS categories(id INTEGER PRIMARY KEY, name TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS products(id INTEGER PRIMARY KEY, name TEXT, price REAL," +
                " categoryId INTEGER NOT NULL," +
                " CONSTRAINT fk_categories FOREIGN KEY (categoryId) REFERENCES categories(id) ON DELETE CASCADE ON UPDATE CASCADE )");
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
        super.onConfigure(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS categories");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS products");
        onCreate(sqLiteDatabase);
    }

    public List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();
        Cursor result = getData("SELECT * FROM categories");
//        if (result != null) {
//            while (result.moveToNext()) {
//                int id = result.getInt(0);
//                String name = result.getString(1);
//                Category category = new Category(id, name);
//                categories.add(category);
//            }
//            result.close();
//            return categories;
//        }
        if (result != null && result.moveToFirst()) {
            while (!result.isAfterLast()) {
                int id = result.getInt(0);
                String name = result.getString(1);
                Category category = new Category(id, name);
                categories.add(category);
                result.moveToNext();
            }
            result.close();
            return categories;
        }
        return null;
    }

    public Category getCategory(int id) {
        Category category = null;
        Cursor result = getData("SELECT * FROM categories WHERE id = " + id);
        if (result != null && result.moveToFirst()) {
            while (!result.isAfterLast()) {
                String name = result.getString(1);
                category = new Category(id, name);
                result.moveToNext();
            }
            result.close();
        }
        return category;
    }

    public Category getCategory(String name) {
        Category category = null;
        Cursor result = getData("SELECT * FROM categories WHERE name = '"+name+"'");
        if (result != null && result.moveToFirst()) {
            while (!result.isAfterLast()) {
                int id = result.getInt(0);
                category = new Category(id, name);
                result.moveToNext();
            }
            result.close();
        }
        return category;
    }

    public void addCategory(Category category) {
        query("INSERT INTO categories VALUES ('" + category.getId() + "','" + category.getName() + "')");
    }

    public void deleteCategory(int id) {
        query("DELETE FROM categories WHERE id = " + id);
    }

    public void updateCategory(Category category) {
        query("UPDATE categories SET name = '" + category.getName() + "' WHERE id = '" + category.getId() + "'");
    }


    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        Cursor result = getData("SELECT * FROM products");

        if (result != null) {
            while (result.moveToNext()) {
                int id = result.getInt(0);
                String name = result.getString(1);
                double price = result.getDouble(2);
                int categoryId = result.getInt(3);

                Product product = new Product(id, name, price, getCategory(categoryId));
                products.add(product);
            }
            result.close();
            return products;
        }

        return null;
    }

    public Product getProduct(int id) {
        Product product = null;
        Cursor result = getData("SELECT * FROM products WHERE id = " + id);
        if (result != null && result.moveToFirst()) {
            while (!result.isAfterLast()) {
                String name = result.getString(1);
                double price = result.getDouble(2);
                int categoryId = result.getInt(3);
                Category category = getCategory(categoryId);
                product = new Product(id, name, price, category);
                result.moveToNext();
            }
            result.close();
        }
        return product;
    }

    public void addProduct(Product product) {
        query("INSERT INTO products VALUES ('" + product.getId() + "','" + product.getName() + "','" + product.getPrice() + "','" + product.getCategory().getId() + "')");
    }

    public void deleteProduct(int id) {
        query("DELETE FROM products WHERE id = " + id);
    }

    public void updateProduct(Product product) {
        query("UPDATE products SET name = '" + product.getName() + "', price = '" + product.getPrice() + "', categoryId = '" + product.getCategory().getId() + "' WHERE id = '" + product.getId() + "'");
    }

}
