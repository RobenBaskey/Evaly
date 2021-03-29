package com.roben.evaly.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClint {
    private static final String BASE_URL = "https://8mm.polash.tech/api/";

    private static Retrofit getRetrofitInstance(){
        Gson gson = new GsonBuilder().setLenient().create();
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public static ApiInterface getApiInterface(){
        return getRetrofitInstance().create(ApiInterface.class);
    }
}
