package com.example.hadeer.cashutask;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    ListView lv_repositories;
    public static int offset = -1;
    private int page = 1;
    RemoteRequest request = new RemoteRequest();
    ProgressBar progressBar;
    SqliteOperations sqliteOperations = new SqliteOperations();
    public static List<OneRepositoryModel> repositories = new ArrayList<OneRepositoryModel>();
    public static repositoriesAdapter adapt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv_repositories = findViewById(R.id.repositories_lv);
        progressBar = findViewById(R.id.progress);

        adapt = new repositoriesAdapter(this, repositories);
        lv_repositories.setAdapter(adapt);

        if (Connection.getConnectivityStatus(getApplicationContext()) != 0) {
            DatabaseHelper mDbHelper = new DatabaseHelper(this);
            SQLiteDatabase db = mDbHelper.getWritableDatabase();
            db.execSQL("delete from "+ SqliteOperations.TABLE_NAME);
            request.requestRepositories(MainActivity.this, lv_repositories, page, progressBar);

        } else {
            Toast.makeText(this, "no internet connection!", Toast.LENGTH_LONG).show();
            repositories = sqliteOperations.getAllRepositories(MainActivity.this);

            repositoriesAdapter adapter = new repositoriesAdapter(this, repositories);
            lv_repositories.setAdapter(adapter);
        }

        lv_repositories.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                //
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                                 int totalItemCount) {
                if (firstVisibleItem + visibleItemCount + 3 == totalItemCount) {

                    if (offset != 0){
                        if (!RemoteRequest.requestInBG) {
                            page++;
                            if (Connection.getConnectivityStatus(getApplicationContext()) != 0) {

                                request.requestRepositories(MainActivity.this, lv_repositories,
                                        page, progressBar);
                            }
                        }
                    }

                }
            }
        });

    }

}
