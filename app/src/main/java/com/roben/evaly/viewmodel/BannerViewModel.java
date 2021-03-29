package com.roben.evaly.viewmodel;

import android.util.Log;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bumptech.glide.Glide;
import com.roben.evaly.R;
import com.roben.evaly.model.BannerList;
import com.roben.evaly.model.BannerModel;
import com.roben.evaly.model.CategoryModel;
import com.roben.evaly.remote.ApiClint;
import com.roben.evaly.remote.ApiInterface;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.motion.widget.MotionScene.TAG;

public class BannerViewModel extends ViewModel {
    public String title = "";
    public String image = "";

    private ArrayList<BannerViewModel> bannerViewModelList;
    private ArrayList<BannerModel> items;

    public BannerViewModel() {
    }

    public BannerViewModel(BannerModel bannerModel) {
        this.title = bannerModel.title;
        this.image = bannerModel.image;
    }

    public String getImageUrl(){
        return image;
    }
    @BindingAdapter({"bind:imageUrls"})

    public static void loadImage(ImageView imageView , String imageurl){
        //Glide.with(imageView.getContext()).load(imageurl).into(imageView);
        Picasso.get().load(imageurl).placeholder(R.drawable.banner1).into(imageView);
    }

    public MutableLiveData<ArrayList<BannerViewModel>> mutableLiveData = new MutableLiveData<>();

    public MutableLiveData<ArrayList<BannerViewModel>> getMutableLiveData() {

        ApiInterface apiInterface = ApiClint.getApiInterface();
        Call<BannerList> listCall = apiInterface.getBannerList();

        listCall.enqueue(new Callback<BannerList>() {
            @Override
            public void onResponse(Call<BannerList> call, Response<BannerList> response) {
                items = new ArrayList<>();
                items = response.body().getBannerModels();

                BannerModel bannerModel;
                BannerViewModel bannerViewModel;

                bannerViewModelList = new ArrayList<>();
                for(int i = 0 ; i< items.size() ; i++){
                    bannerModel = new BannerModel(items.get(i).title , items.get(i).image);
                    bannerViewModel = new BannerViewModel(bannerModel);
                    bannerViewModelList.add(bannerViewModel);
                }
                mutableLiveData.setValue(bannerViewModelList);
            }

            @Override
            public void onFailure(Call<BannerList> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }
}