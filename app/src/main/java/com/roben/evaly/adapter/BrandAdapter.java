package com.roben.evaly.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.roben.evaly.R;
import com.roben.evaly.databinding.BannerBinding;
import com.roben.evaly.databinding.BrandBinding;
import com.roben.evaly.viewmodel.BannerViewModel;
import com.roben.evaly.viewmodel.BrandViewModel;

import java.util.ArrayList;

public class BrandAdapter extends RecyclerView.Adapter<BrandAdapter.ViewHolder> {
    private ArrayList<BrandViewModel> brandList;
    Context context;
    LayoutInflater layoutInflater;

    public BrandAdapter(ArrayList<BrandViewModel> brandList, Context context) {
        this.brandList = brandList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(layoutInflater == null){
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        BrandBinding brandBinding = DataBindingUtil.inflate(layoutInflater , R.layout.brand_sample , parent , false);
        return new ViewHolder(brandBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BrandViewModel model = brandList.get(position);
        holder.bind(model);
    }

    @Override
    public int getItemCount() {
        return brandList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private BrandBinding brandBinding;
        public ViewHolder(@NonNull BrandBinding brandBinding) {
            super(brandBinding.getRoot());

            this.brandBinding = brandBinding;
        }

        public void bind(BrandViewModel brandViewModel){
            this.brandBinding.setBrandView(brandViewModel);
            brandBinding.executePendingBindings();

        }

        public BrandBinding getBrandBinding(){
            return brandBinding;
        }
    }
}
