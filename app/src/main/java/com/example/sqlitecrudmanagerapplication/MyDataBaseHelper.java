package com.example.sqlitecrudmanagerapplication;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "test.db";
    private static final int DATABASE_VERSION = 2;

    private static final String TABLE_NAME = "test";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "_name";
    private static final String COLUMN_PASSWORD = "_password";


    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME
                + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_PASSWORD + " TEXT )";
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void deleteTable()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public String[] getUser(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] str = new String[2];
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + "WHERE " + COLUMN_ID + " = " + id, null);
        if(cursor.getCount() == 1)
        {
            str[0] = cursor.getString(1);
            str[2] = cursor.getString(2);
        }
        db.close();
        return str;
    }

    public List<String[]> getAllUsers()
    {
        List<String[]> users = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        while(cursor.moveToNext())
        {
            users.add(new String[]{cursor.getString(1), cursor.getString(2)});
        }
        db.close();
        return users;
    }

    public long addUser(String name, String password)
    {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_PASSWORD, password);

        SQLiteDatabase db = this.getWritableDatabase();

        long res = db.insert(TABLE_NAME, null,cv);

        db.close();
        return res;
    }

    public long updateUser(int id, String name, String password)
    {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_PASSWORD, password);

        SQLiteDatabase db = this.getWritableDatabase();

        long res = db.update(TABLE_NAME, cv,COLUMN_ID + " = " + id, null);
        db.close();
        return res;
    }

    public long deleteUser(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        long res = db.delete(TABLE_NAME,COLUMN_ID + " = " + id, null);
        db.close();
        return res;
    }
}
