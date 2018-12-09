package com.example.seekm.assignment2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.jar.Attributes;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Accounts.db";
    public static final String TABLE_NAME = "AccountsTable";
    public static final String col_ID = "ID";
    public static final String col_NAME = "NAME";
    public static final String col_USERNAME = "USERNAME";
    public static final String col_EMAIL = "EMAIL";
    public static final String col_PASSWORD = "PASSWORD";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
//        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, USERNAME TEXT, EMAIL TEXT, PASSWORD TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void drop(){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME,null,null);
//        db.execSQL("DELETE TABLE AccountsTable FROM Accounts.db");
    }

    public boolean insertData(String name, String username, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_NAME, name);
        contentValues.put(col_USERNAME, username);
        contentValues.put(col_EMAIL, email);
        contentValues.put(col_PASSWORD, password);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from AccountsTable where email=?", new String[]{email});
        if (cursor.getCount() > 0) {
            return false;
        } else
            return true;
    }

    public String authenticate(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c1 = db.rawQuery("select * from AccountsTable where email=? and password=? ", new String[]{email, password});
        if (c1.getCount() > 0) {
            c1.moveToFirst();
            return c1.getString(1);
        }
        return "invalid";
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from " + TABLE_NAME;
        Cursor data = db.rawQuery(query,null);
        return data;
    }
}
