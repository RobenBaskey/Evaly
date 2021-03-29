package com.roben.evaly.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.roben.evaly.R;
import com.roben.evaly.adapter.BrandAdapter;
import com.roben.evaly.adapter.CatAdapter;
import com.roben.evaly.viewmodel.BrandViewModel;
import com.roben.evaly.viewmodel.CategoryViewModel;

import java.util.ArrayList;

public class Brands extends Fragment {

    private RecyclerView brandRecyclerView;
    private ArrayList<BrandViewModel> brandList;
    private BrandAdapter adapter;
    private BrandViewModel brandViewModel;
    private TextView searchBtn;
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_brands, container, false);
        setBrandRecycler();
        return view;
    }

    private void setBrandRecycler() {
        brandViewModel = ViewModelProviders.of(this).get(BrandViewModel.class);
        brandRecyclerView = view.findViewById(R.id.brand_recycler);

        brandViewModel.getMutableLiveData().observe((LifecycleOwner) getContext(), new Observer<ArrayList<BrandViewModel>>() {
            @Override
            public void onChanged(ArrayList<BrandViewModel> brandViewModels) {
                adapter = new BrandAdapter(brandViewModels , getContext());
                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext() , 3);
                brandRecyclerView.setLayoutManager(layoutManager);
                brandRecyclerView.setAdapter(adapter);
                brandRecyclerView.setHasFixedSize(true);


            }
        });
    }
}
