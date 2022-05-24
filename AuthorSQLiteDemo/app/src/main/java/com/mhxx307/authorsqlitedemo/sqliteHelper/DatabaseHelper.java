package com.mhxx307.authorsqlitedemo.sqliteHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.mhxx307.authorsqlitedemo.entity.Author;
import com.mhxx307.authorsqlitedemo.entity.Book;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context) {
        super(context, "authors_demo.sqlite", null, 1);
    }

    // truy vấn không trả kết quả: CREATE, INSERT, DELETE, UPDATE,...
    public void QueryData(String sql) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL(sql);
    }

    // truy vấn có trả kết quả: SELECT
    public Cursor getData(String sql) {
        SQLiteDatabase database = this.getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // tạo bảng authors
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " +
                "authors(id INTEGER PRIMARY KEY AUTOINCREMENT, author_name NVARCHAR(100),author_address NVARCHAR(200),author_email VARCHAR(200))");

        // tạo bảng books có author_id tham chiếu đến authors
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " +
                "books(id INTEGER PRIMARY KEY AUTOINCREMENT, book_title NVARCHAR(100), author_id INTEGER NOT NULL, CONSTRAINT fk_authors FOREIGN KEY (author_id) REFERENCES authors(id) ON DELETE CASCADE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS authors");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS books");
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
        super.onConfigure(db);
    }

    public ArrayList<Author> getAllAuthors() {
        ArrayList<Author> listAuthor = new ArrayList<>();
        Cursor resultData = getData("SELECT * FROM authors");

        if (resultData != null) {
            while (resultData.moveToNext()) {
                int id = resultData.getInt(0);
                String authorName = resultData.getString(1);
                String authorAddress = resultData.getString(2);
                String authorEmail = resultData.getString(3);

                Author author = new Author(id, authorName, authorAddress, authorEmail);
                listAuthor.add(author);
            }
            resultData.close();
        }

        return listAuthor;
    }

    public void addAuthor(Author author) {
        if (author != null) {
            QueryData("INSERT INTO authors VALUES(null, '" + author.getName() + "', '" + author.getAddress() + "', '" + author.getEmail() + "')");
        }
    }

    public void deleteAuthor(int id) {
        QueryData("DELETE FROM authors\n" +
                "WHERE id = '" + id + "';");
    }

    public void updateAuthor(Author author) {
        if (author != null) {
            String sql = "UPDATE authors SET author_name = '" + author.getName() + "', author_address = '" + author.getAddress() + "', author_email = '" + author.getEmail() + "' where id = '" + author.getId() + "'    ";
            QueryData(sql);
        }
    }

    public Author getAuthorById(int id) {
        Author author = null;
        String sql = "SELECT * FROM authors WHERE id = '" + id + "'";
        Cursor resultData = getData(sql);
        if (resultData != null) {
            while (resultData.moveToNext()) {
                String authorName = resultData.getString(1);
                String authorAddress = resultData.getString(2);
                String authorEmail = resultData.getString(3);

                author = new Author(id, authorName, authorAddress, authorEmail);
            }
            resultData.close();
        }
        return author;
    }

    public Author getAuthorByName(String authorName) {
        Author author = null;
        String sql = "SELECT * FROM authors WHERE author_name = '" + authorName + "'";
        Cursor resultData = getData(sql);
        if (resultData != null) {
            while (resultData.moveToNext()) {
                int id = resultData.getInt(0);
                String authorAddress = resultData.getString(2);
                String authorEmail = resultData.getString(3);

                author = new Author(id, authorName, authorAddress, authorEmail);
            }
            resultData.close();
        }
        return author;
    }

    //    books dao
    public ArrayList<Book> getAllBooks() {
        ArrayList<Book> books = new ArrayList<>();
        Cursor resultData = getData("SELECT * FROM books");

        if (resultData != null) {
            while (resultData.moveToNext()) {
                int id = resultData.getInt(0);
                String bookTitle = resultData.getString(1);
                int authorId = resultData.getInt(2);

                Author author = getAuthorById(authorId);

                Book book = new Book(id, bookTitle, author);

                books.add(book);
            }
            resultData.close();
        }

        return books;
    }

    public void addBook(Book book) {
        if (book != null) {
            QueryData("INSERT INTO books VALUES(null, '" + book.getTitle() + "', '" + book.getAuthor().getId() + "')");
        }
    }

    public void deleteBook(int id) {
        QueryData("DELETE FROM books\n" +
                "WHERE id = '" + id + "' ");
    }

    public void updateBook(Book book) {
        if (book != null) {
            String sql = "UPDATE books SET book_title = '" + book.getTitle() + "', author_id = '" + book.getAuthor().getId() + "' where id = '" + book.getId() + "' ";
            QueryData(sql);
        }
    }
}
