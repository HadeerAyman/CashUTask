package com.example.hadeer.cashutask;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    ListView listview_repos;
    private int page = 1;
    RemoteRequest request = new RemoteRequest();
    ProgressBar progressBar;
    SqliteOperations sqliteOperations ;
    DataHandler dataHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create references to UI elements
        listview_repos = findViewById(R.id.repositories_lv);
        progressBar = findViewById(R.id.progress);


        // create the most first data request
       initData();


       // listView scroll listener
       handleListScroll();

    }

    private void initData(){
        sqliteOperations = new SqliteOperations(this);
        dataHandler = DataHandler.create_new_instance(this);
        listview_repos.setAdapter(dataHandler.adapter);

        if (Connection.getConnectivityStatus(getApplicationContext()) != 0) {
            sqliteOperations.deleteAll();
            request.requestRepositories(MainActivity.this, page, progressBar, sqliteOperations);
        } else {
            Toast.makeText(this, "no internet connection!", Toast.LENGTH_LONG).show();
            List<OneRepositoryModel> repositories = sqliteOperations.getAllRepositories();
            dataHandler.add_all(repositories);
        }
    }

    private void handleListScroll(){
        listview_repos.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                //
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                                 int totalItemCount) {
                if (firstVisibleItem + visibleItemCount + 3 == totalItemCount) {

                    if (dataHandler.offset != 0){
                        if (!RemoteRequest.requestInBG) {
                            page++;
                            if (Connection.getConnectivityStatus(getApplicationContext()) != 0) {

                                request.requestRepositories(MainActivity.this,
                                        page, progressBar, sqliteOperations);
                            }
                        }
                    }

                }
            }
        });
    }

}
