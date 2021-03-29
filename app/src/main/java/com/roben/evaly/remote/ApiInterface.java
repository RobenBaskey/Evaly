package com.roben.evaly.remote;

import com.roben.evaly.model.BannerList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("banner.php")
    Call<BannerList> getBannerList();

    @GET("categories.php")
    Call<BannerList> getCategoryList();

    @GET("brands.php")
    Call<BannerList> getBrandList();
}
