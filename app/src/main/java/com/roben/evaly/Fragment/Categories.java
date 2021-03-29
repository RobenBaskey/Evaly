package com.roben.evaly.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.roben.evaly.R;
import com.roben.evaly.Search;
import com.roben.evaly.adapter.CatAdapter;
import com.roben.evaly.viewmodel.BannerViewModel;
import com.roben.evaly.viewmodel.CategoryViewModel;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Categories extends Fragment {

    private RecyclerView recyclerView , recent;
    private ArrayList<CategoryViewModel> saveList = new ArrayList<>();
    private CatAdapter adapter  , recentAdapter;
    private CategoryViewModel categoryViewModel , recentModel;
    private TextView searchBtn;
    View v;

    private SharedPreferences sharedPreferences;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_categories, container, false);

        categoryViewModel = ViewModelProviders.of(this).get(CategoryViewModel.class);
        recyclerView = v.findViewById(R.id.category_recycler);

        sharedPreferences = getContext().getSharedPreferences("Category",0);

        categorySaveRecycler();
        goToSearch();
        recentProductRecycler();
        return v;
    }

    private void categorySaveRecycler() {

        Gson gson = new Gson();
        String json = sharedPreferences.getString("CategoryData", null);
        Type type = new TypeToken<ArrayList<CategoryViewModel>>() {}.getType();
        saveList = gson.fromJson(json , type);


        if(saveList == null){
            categoryRecycler();
            Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
        }else{

            saveRecyclerData();

        }
    }

    private void saveRecyclerData() {

        adapter = new CatAdapter(saveList , getContext());
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext() , 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

    }

    private void goToSearch() {
        searchBtn = v.findViewById(R.id.search_category);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext() , Search.class);
                intent.putExtra("name","category");
                startActivity(intent);
            }
        });
    }

    private void recentProductRecycler() {
        recentModel = ViewModelProviders.of(this).get(CategoryViewModel.class);
        recent = v.findViewById(R.id.recent_product_recycler);

        recentModel.getCategoryMutableList().observe((LifecycleOwner) getContext(), new Observer<ArrayList<CategoryViewModel>>() {
            @Override
            public void onChanged(ArrayList<CategoryViewModel> categoryViewModels) {
                recentAdapter = new CatAdapter(categoryViewModels , getContext());
                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext() , 2);
                recent.setLayoutManager(layoutManager);
                recent.setAdapter(recentAdapter);
                recent.setHasFixedSize(true);


            }
        });
    }

    private void categoryRecycler() {

        categoryViewModel.getCategoryMutableList().observe((LifecycleOwner) getContext(), new Observer<ArrayList<CategoryViewModel>>() {
            @Override
            public void onChanged(ArrayList<CategoryViewModel> categoryViewModels) {


                SharedPreferences.Editor editor = sharedPreferences.edit();
                Gson gson = new Gson();
                String json = gson.toJson(categoryViewModels);
                editor.putString("CategoryData",json);
                editor.apply();

                adapter = new CatAdapter(categoryViewModels , getContext());
                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext() , 3);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
                recyclerView.setHasFixedSize(true);


            }
        });
    }


}
