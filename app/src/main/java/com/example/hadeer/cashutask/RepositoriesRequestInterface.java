package com.example.hadeer.cashutask;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RepositoriesRequestInterface {

    @GET("repos")
    Call<List<OneRepositoryModel>> getRepositories(@Query("page") int page,
                                                   @Query("per_page") int per_page,
                                                   @Query("access_token") String access_token);
}
