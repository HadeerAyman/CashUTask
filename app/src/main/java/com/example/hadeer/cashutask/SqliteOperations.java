package com.example.hadeer.cashutask;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class SqliteOperations {

    public static final String TABLE_NAME = "repositories_table";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NAME + " TEXT,"
                    + COLUMN_DESCRIPTION + " TEXT"
                    + ")";

    public long insertRepository(Context context, String name, String description) {
        DatabaseHelper mDbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(OneRepositoryModel.COLUMN_NAME, name);
        values.put(OneRepositoryModel.COLUMN_description, description);

        long id = db.insert(OneRepositoryModel.TABLE_NAME, null, values);

        db.close();

        return id;
    }

    public void insertReposerories(Context context, List<OneRepositoryModel> repositoryModelList) {
        DatabaseHelper mDbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        for (int i=1 ; i < repositoryModelList.size(); i++){
            ContentValues values = new ContentValues();
            values.put(OneRepositoryModel.COLUMN_NAME, repositoryModelList.get(i).getName());
            values.put(OneRepositoryModel.COLUMN_description, repositoryModelList.get(i).getDescription());
            db.insert(OneRepositoryModel.TABLE_NAME, null, values);
        }
        db.close();
    }

    public OneRepositoryModel getRepository(Context context, long id) {
        DatabaseHelper mDbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        Cursor cursor = db.query(OneRepositoryModel.TABLE_NAME,
                new String[]{OneRepositoryModel.COLUMN_ID, OneRepositoryModel.COLUMN_NAME, OneRepositoryModel.COLUMN_description},
                OneRepositoryModel.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        OneRepositoryModel repositoryModel = new OneRepositoryModel(
                cursor.getInt(cursor.getColumnIndex(OneRepositoryModel.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(OneRepositoryModel.COLUMN_NAME)),
                cursor.getString(cursor.getColumnIndex(OneRepositoryModel.COLUMN_description)));

        cursor.close();

        return repositoryModel;
    }

    public List<OneRepositoryModel> getAllRepositories(Context context) {
        List<OneRepositoryModel> repositoryModelList = new ArrayList<>();
        DatabaseHelper mDbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + OneRepositoryModel.TABLE_NAME ;
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                OneRepositoryModel repositoryModel = new OneRepositoryModel();
                repositoryModel.setName(cursor.getString(cursor.getColumnIndex(OneRepositoryModel.COLUMN_NAME)));
                repositoryModel.setDescription(cursor.getString(cursor.getColumnIndex(OneRepositoryModel.COLUMN_description)));

                repositoryModelList.add(repositoryModel);
            } while (cursor.moveToNext());
        }

        db.close();

        return repositoryModelList;
    }

}
