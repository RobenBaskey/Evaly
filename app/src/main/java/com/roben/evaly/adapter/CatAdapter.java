package com.roben.evaly.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.roben.evaly.R;
import com.roben.evaly.databinding.CategoryBinding;
import com.roben.evaly.viewmodel.CategoryViewModel;

import java.util.ArrayList;
import java.util.Collection;

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.ViewHolder> implements Filterable {

    private ArrayList<CategoryViewModel> arrayList;
    private ArrayList<CategoryViewModel> arrayListall;
    private Context context;
    LayoutInflater layoutInflater;

    public CatAdapter(ArrayList<CategoryViewModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
        this.arrayListall = new ArrayList<>(arrayList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(layoutInflater == null){
            layoutInflater =  LayoutInflater.from(parent.getContext());
        }

        CategoryBinding categoryBinding = DataBindingUtil.inflate(layoutInflater , R.layout.category_sample , parent , false);
        return new ViewHolder(categoryBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CategoryViewModel model = arrayList.get(position);
        holder.bind(model);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<CategoryViewModel> list = new ArrayList<>();

            if(constraint.toString().isEmpty()){
                list.addAll(arrayListall);
            }else{
                for(CategoryViewModel arrayList : arrayListall){
                    if(arrayList.title.toLowerCase().contains(constraint.toString().toLowerCase())){
                        list.add(arrayList);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = list;

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            arrayList.clear();
            arrayList.addAll((Collection<? extends CategoryViewModel>) results.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CategoryBinding categoryBinding;
        public ViewHolder(CategoryBinding categoryBinding) {
            super(categoryBinding.getRoot());

            this.categoryBinding = categoryBinding;
        }

        public void bind(CategoryViewModel categoryViewModel){
            this.categoryBinding.setCategoryView(categoryViewModel);
            categoryBinding.executePendingBindings();
        }
        public CategoryBinding getCategoryBinding(){
            return categoryBinding;
        }
    }
}
