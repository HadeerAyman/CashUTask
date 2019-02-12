package com.example.hadeer.cashutask;


import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class DataHandler {
    private static DataHandler instance ;
    public List<OneRepositoryModel> repositories = new ArrayList<OneRepositoryModel>();
    public RepositoriesAdapter adapter;
    public int offset = -1;

    private DataHandler(Context context) {
        adapter = new RepositoriesAdapter(context, repositories);
    }

    public static DataHandler  create_new_instance(Context context){
        return instance = new DataHandler(context);
    }

    public static DataHandler get_instance(Context context){
        if (instance == null){
            return create_new_instance(context);
        }
        return instance;
    }

    public void add_all (List<OneRepositoryModel> repositoriesList){
        repositories.addAll(repositoriesList);
        adapter.notifyDataSetChanged();
    }

    public void clear_all (){
        repositories.clear();
        adapter.notifyDataSetChanged();
    }

}
