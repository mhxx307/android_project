package com.mhxx307.democontentprovider.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mhxx307.democontentprovider.sqliteHelper.DBHelper;

import java.util.HashMap;

public class ProductProvider extends ContentProvider {

    public static String AUTHORITY = "com.mhxx307.democontentprovider.provider";
    public static String PRODUCTS_TABLE = "products";
    public static String URL_PRODUCTS_TABLE = "content://" + AUTHORITY + "/" + PRODUCTS_TABLE;
    static final Uri CONTENT_URI = Uri.parse(URL_PRODUCTS_TABLE);
    public static UriMatcher uriMatcher;
    public static final int PRODUCTS = 100;
    public static final int PRODUCT_ID = 110;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        // content://com.mhxx307.democontentprovider.provider/products
        uriMatcher.addURI(AUTHORITY, PRODUCTS_TABLE, PRODUCTS);
        // content://com.mhxx307.democontentprovider.provider/products/#
        uriMatcher.addURI(AUTHORITY, PRODUCTS_TABLE + "/#", PRODUCT_ID);
    }

    private SQLiteDatabase db;
    private static HashMap<String, String> PROJECTION_MAP;


    public ProductProvider() {
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        DBHelper dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();

        if (db == null) {
            return false;
        }

        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteQueryBuilder sqLiteQueryBuilder = new SQLiteQueryBuilder();
        sqLiteQueryBuilder.setTables(PRODUCTS_TABLE);
        int matcher = uriMatcher.match(uri);
        switch (matcher) {
            case PRODUCTS:
                sqLiteQueryBuilder.setProjectionMap(PROJECTION_MAP);
                break;
            case PRODUCT_ID:
                sqLiteQueryBuilder.appendWhere("id" + "=" + uri.getPathSegments().get(1));
                break;
        }

        if (sortOrder == null || sortOrder == "") {
            sortOrder = "name";
        }

        Cursor cursor = sqLiteQueryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);

        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        long number_row = db.insert(PRODUCTS_TABLE, "", contentValues);
        if (number_row > 0) {
            Uri uri_ = ContentUris.withAppendedId(CONTENT_URI, number_row);
            getContext().getContentResolver().notifyChange(uri_, null);
            return uri_;
        }
        throw new SQLException("Failed to add a record into " + uri);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)) {
            case PRODUCTS:
                count = db.delete(PRODUCTS_TABLE, selection, selectionArgs);
                break;
            case PRODUCT_ID:
                String id = uri.getPathSegments().get(1);
                count = db.delete(PRODUCTS_TABLE, "id" + " = " + id +
                        (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)) {
            case PRODUCTS:
                count = db.update(PRODUCTS_TABLE, values, selection, selectionArgs);
                break;
            case PRODUCT_ID:
                String id = uri.getPathSegments().get(1);
                count = db.update(PRODUCTS_TABLE, values, "id" + " = " + id +
                        (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }
}
