package com.roben.evaly.viewmodel;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.roben.evaly.model.BannerList;
import com.roben.evaly.model.CategoryModel;
import com.roben.evaly.remote.ApiClint;
import com.roben.evaly.remote.ApiInterface;

import java.lang.reflect.Type;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.motion.widget.MotionScene.TAG;

public class CategoryViewModel extends ViewModel {
    public String title = "";
    public String image = "";
    private Context context;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    private ArrayList<CategoryViewModel> categoryList;
    private ArrayList<CategoryModel> items;

    public CategoryViewModel() {
    }

    public CategoryViewModel(CategoryModel categoryModel) {
        this.title = categoryModel.title;
        this.image = categoryModel.image;
    }

    public String getImageUrl(){
        return image;
    }
    @BindingAdapter({"bind:imageUrl"})

    public static void loadImage(ImageView imageView , String imageurl){
        Glide.with(imageView.getContext()).load(imageurl).into(imageView);
    }

    public MutableLiveData<ArrayList<CategoryViewModel>> categoryMutableList = new MutableLiveData<>();

    public MutableLiveData<ArrayList<CategoryViewModel>> getCategoryMutableList() {

        ApiInterface apiInterface = ApiClint.getApiInterface();
        Call<BannerList> categoryListCall = apiInterface.getCategoryList();
        categoryListCall.enqueue(new Callback<BannerList>() {
            @Override
            public void onResponse(Call<BannerList> call, Response<BannerList> response) {
                items = new ArrayList<>();
                items = response.body().getCategoryModels();



                CategoryModel categoryModel;
                CategoryViewModel categoryViewModel;

                //loadData();
                categoryList = new ArrayList<>();
                for(int i = 0 ; i< items.size() ; i++){
                    categoryModel = new CategoryModel(items.get(i).title , items.get(i).image);
                    Log.d(TAG, "onResponse: "+categoryModel);
                    categoryViewModel = new CategoryViewModel(categoryModel);
                    categoryList.add(categoryViewModel);
                }

                categoryMutableList.setValue(categoryList);
            }

            @Override
            public void onFailure(Call<BannerList> call, Throwable t) {

            }
        });
        return categoryMutableList;
    }

    private void loadData(){
        preferences = context.getSharedPreferences("saveCategory" , 0);
        Gson gson = new Gson();
        String json = preferences.getString("task" , null);
        Log.d(TAG, "loadData: "+json);
        Type type = new TypeToken<ArrayList<CategoryViewModel>>() {}.getType();
        categoryList = gson.fromJson(json , type);

    }
}
