package com.roben.evaly.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BrandModel {
    @SerializedName("brand_title")
    @Expose
    public String title = "";

    @SerializedName("brand_image")
    @Expose
    public String image = "";

    public BrandModel(String title, String image) {
        this.title = title;
        this.image = image;
    }

    public BrandModel() {
    }
}
