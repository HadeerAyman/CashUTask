package com.example.hadeer.cashutask;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class SqliteOperations {

    private SQLiteDatabase db;
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

    public SqliteOperations(Context context) {
        DatabaseHelper mDbHelper = new DatabaseHelper(context);
        db = mDbHelper.getWritableDatabase();
    }

    public void insertReposerories( List<OneRepositoryModel> repositoryModelList) {
        for (int i=1 ; i < repositoryModelList.size(); i++){
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, repositoryModelList.get(i).getName());
            values.put(COLUMN_DESCRIPTION, repositoryModelList.get(i).getDescription());
            db.insert(TABLE_NAME, null, values);
        }
    }

    public List<OneRepositoryModel> getAllRepositories() {
        List<OneRepositoryModel> repositoryModelList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME ;
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                OneRepositoryModel repositoryModel = new OneRepositoryModel();
                repositoryModel.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                repositoryModel.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)));

                repositoryModelList.add(repositoryModel);
            } while (cursor.moveToNext());
        }

        db.close();

        return repositoryModelList;
    }

    public void deleteAll(){
        db.execSQL("delete from "+ TABLE_NAME);
    }

}
