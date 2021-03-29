package com.roben.evaly.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BannerList {

    @SerializedName("banners")
    @Expose
    private ArrayList<BannerModel> bannerModels = new ArrayList<>();

    public ArrayList<BannerModel> getBannerModels() {
        return bannerModels;
    }

    public void setBannerModels(ArrayList<BannerModel> bannerModels) {
        this.bannerModels = bannerModels;
    }

    @SerializedName("categories")
    @Expose
    private ArrayList<CategoryModel> categoryModels = new ArrayList<>();

    public ArrayList<CategoryModel> getCategoryModels() {
        return categoryModels;
    }

    public void setCategoryModels(ArrayList<CategoryModel> categoryModels) {
        this.categoryModels = categoryModels;
    }

    @SerializedName("brands")
    @Expose
    private ArrayList<BrandModel> brandModels = new ArrayList<>();

    public ArrayList<BrandModel> getBrandModels() {
        return brandModels;
    }

    public void setBrandModels(ArrayList<BrandModel> brandModels) {
        this.brandModels = brandModels;
    }
}
