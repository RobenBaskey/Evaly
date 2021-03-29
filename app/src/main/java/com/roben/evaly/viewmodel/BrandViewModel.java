package com.roben.evaly.viewmodel;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.roben.evaly.R;
import com.roben.evaly.model.BannerList;
import com.roben.evaly.model.BannerModel;
import com.roben.evaly.model.BrandModel;
import com.roben.evaly.remote.ApiClint;
import com.roben.evaly.remote.ApiInterface;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BrandViewModel extends ViewModel {

    public String title = "";
    public String image = "";

    private ArrayList<BrandViewModel> brandViewModelList;
    private ArrayList<BrandModel> items;

    public BrandViewModel() {
    }

    public BrandViewModel(BrandModel brandModel) {
        this.title = brandModel.title;
        this.image = brandModel.image;
    }

    public String getImageUrl(){
        return image;
    }
    @BindingAdapter({"bind:imageUrl"})

    public static void loadImage(ImageView imageView , String imageUrl){
        Picasso.get().load(imageUrl).placeholder(R.drawable.banner1).into(imageView);
    }

    private MutableLiveData<ArrayList<BrandViewModel>> mutableLiveData = new MutableLiveData<>();

    public MutableLiveData<ArrayList<BrandViewModel>> getMutableLiveData() {
        ApiInterface apiInterface = ApiClint.getApiInterface();
        Call<BannerList> listCall = apiInterface.getBrandList();

        listCall.enqueue(new Callback<BannerList>() {
            @Override
            public void onResponse(Call<BannerList> call, Response<BannerList> response) {
                items = new ArrayList<>();
                items = response.body().getBrandModels();

                BrandModel brandModel;
                BrandViewModel brandViewModel;

                brandViewModelList = new ArrayList<>();
                for(int i = 0 ; i< items.size() ; i++){
                    brandModel = new BrandModel(items.get(i).title , items.get(i).image);
                    brandViewModel = new BrandViewModel(brandModel);
                    brandViewModelList.add(brandViewModel);
                }
                mutableLiveData.setValue(brandViewModelList);
            }

            @Override
            public void onFailure(Call<BannerList> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }
}
