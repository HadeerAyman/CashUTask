package com.example.hadeer.cashutask;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OneRepositoryModel {

    private int offline_id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;


    public OneRepositoryModel() {
    }

    public OneRepositoryModel(int offline_id, String name, String description) {
        this.offline_id = offline_id;
        this.name = name;
        this.description = description;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
