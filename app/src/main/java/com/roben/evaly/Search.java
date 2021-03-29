package com.roben.evaly;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.roben.evaly.adapter.CatAdapter;
import com.roben.evaly.viewmodel.CategoryViewModel;

import java.util.ArrayList;

public class Search extends AppCompatActivity {

    EditText search_bar;
    private RecyclerView recyclerView , recent;
    private ArrayList<CategoryViewModel> categoryList;
    private CatAdapter adapter  , recentAdapter;
    private CategoryViewModel categoryViewModel , recentModel;
    private TextView searchBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        search_bar = findViewById(R.id.search_bar_tool);
        search_bar.setSelection(0);
        search_bar.requestFocus();

        search_bar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                recentAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        category();
    }

    private void category() {
        recentModel = ViewModelProviders.of(this).get(CategoryViewModel.class);
        recent = findViewById(R.id.category_search_recyclerview);

        recentModel.getCategoryMutableList().observe(this, new Observer<ArrayList<CategoryViewModel>>() {
            @Override
            public void onChanged(ArrayList<CategoryViewModel> categoryViewModels) {
                recentAdapter = new CatAdapter(categoryViewModels , getApplicationContext());
                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext() , 2);
                recent.setLayoutManager(layoutManager);
                recent.setAdapter(recentAdapter);
                recent.setHasFixedSize(true);


            }
        });
    }
}
