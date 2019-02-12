package com.example.hadeer.cashutask;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "repositories_db";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        create_table(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        drop_table(db);
        create_table(db);
    }

    private void create_table(SQLiteDatabase db){
        db.execSQL(SqliteOperations.CREATE_TABLE);

    }

    private void drop_table(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " + SqliteOperations.TABLE_NAME);
    }

}
