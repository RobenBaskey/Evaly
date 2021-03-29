package com.roben.evaly.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.roben.evaly.R;
import com.roben.evaly.databinding.BannerBinding;
import com.roben.evaly.databinding.CategoryBinding;
import com.roben.evaly.viewmodel.BannerViewModel;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;

public class BannerAdapter extends SliderViewAdapter<BannerAdapter.SliderModel> {

    private ArrayList<BannerViewModel> bannerList;
    private Context context;
    LayoutInflater layoutInflater;

    public BannerAdapter(ArrayList<BannerViewModel> bannerList, Context context) {
        this.bannerList = bannerList;
        this.context = context;
    }

    @Override
    public SliderModel onCreateViewHolder(ViewGroup parent) {
        if(layoutInflater == null){
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        BannerBinding bannerBinding = DataBindingUtil.inflate(layoutInflater , R.layout.banner_sample , parent , false);
        return new SliderModel(bannerBinding);
    }

    @Override
    public void onBindViewHolder(SliderModel viewHolder, int position) {
        BannerViewModel model = bannerList.get(position);
        viewHolder.bind(model);
    }

    @Override
    public int getCount() {
        return bannerList.size();
    }

    public class SliderModel extends SliderViewAdapter.ViewHolder {

        public BannerBinding bannerBinding;
        public SliderModel(BannerBinding bannerBinding) {
            super(bannerBinding.getRoot());

            this.bannerBinding = bannerBinding;
        }

        public void bind(BannerViewModel bannerViewModel){
          this.bannerBinding.setBannerView(bannerViewModel);
          bannerBinding.executePendingBindings();

        }

        public BannerBinding getBannerBinding(){
            return bannerBinding;
        }
    }
}
