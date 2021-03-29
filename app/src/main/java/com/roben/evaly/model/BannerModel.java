package com.roben.evaly.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BannerModel {
    @SerializedName("banner_title")
    @Expose
    public String title = "";

    @SerializedName("banner_image")
    @Expose
    public String image = "";

    public BannerModel(String title, String image) {
        this.title = title;
        this.image = image;
    }

    public BannerModel() {
    }
}
