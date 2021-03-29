package com.roben.evaly.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryModel {
    @SerializedName("cat_title")
    @Expose
    public String title = "";

    @SerializedName("cat_image")
    @Expose
    public String image = "";

    public CategoryModel() {
    }

    public CategoryModel(String title, String image) {
        this.title = title;
        this.image = image;
    }
}
