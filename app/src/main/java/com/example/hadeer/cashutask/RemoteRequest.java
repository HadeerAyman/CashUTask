package com.example.hadeer.cashutask;


import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteRequest {

    SqliteOperations sqliteOperations = new SqliteOperations();
    public static boolean requestInBG = true;

    public void requestRepositories(final Context context, final ListView listView_repositories,
                                    int page, final ProgressBar progressBar){
        progressBar.setVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/users/JakeWharton/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RepositoriesRequestInterface retrofitInterface = retrofit.create(RepositoriesRequestInterface.class);

        Call<List<OneRepositoryModel>> connection = retrofitInterface.getRepositories(page,
                15, "650624d31ffb78f9c3f52cb4e91219ab13eb5626");

        connection.enqueue(new Callback<List<OneRepositoryModel>>() {
            @Override
            public void onResponse(Call<List<OneRepositoryModel>> call, Response<List<OneRepositoryModel>> response) {
                responseEnded(progressBar);
                Log.d("TTTT", response.headers().get("Link"));
                if (response.headers().get("Link") != null) {
                    if (response.headers().get("Link").contains("next")) {
                        MainActivity.offset = 1;

                    } else {
                        MainActivity.offset = 0;
                    }
                }

                List<OneRepositoryModel> repositories = response.body();
                MainActivity.repositories.addAll(repositories);
                MainActivity.adapt.notifyDataSetChanged();
                sqliteOperations.insertReposerories(context, repositories);

            }

            @Override
            public void onFailure(Call<List<OneRepositoryModel>> call, Throwable t) {
                responseEnded(progressBar);
                Toast.makeText(context, "Something went wrong .. please try again", Toast.LENGTH_LONG).show();
                Log.d("TTTT", t.getMessage());
            }
        });
    }

    private void responseEnded(ProgressBar progressBar) {
        requestInBG = false;
        progressBar.setVisibility(View.GONE);
    }
}
