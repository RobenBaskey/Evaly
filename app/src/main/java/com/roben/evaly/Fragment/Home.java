package com.roben.evaly.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.roben.evaly.R;
import com.roben.evaly.adapter.BannerAdapter;
import com.roben.evaly.adapter.TabAdapter;
import com.roben.evaly.viewmodel.BannerViewModel;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Home extends Fragment implements View.OnClickListener {

    private Context context;
    public DrawerLayout drawerLayout;
    public ImageView navigationBar,iv_logout;
    public TextView ll_First;
    private ArrayList<BannerViewModel> saveList = new ArrayList<>();
    private LinearLayout home,dashboard , order, message , cart , wishlist , favourite_shop , vouchers , invite , contact;
    public NavigationView navigationView;
    View v;


    /// for tab layout///
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabAdapter tabAdapter;
    /// for tab layout///


    private ArrayList<BannerViewModel> bannerList;
    private BannerAdapter adapter;
    private BannerViewModel bannerViewModel;
    private SliderView sliderView;

    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_home, container, false);
        sharedPreferences = getContext().getSharedPreferences("Banner",0);
        onSetNavigationDrawerEvents();

        bannerViewModel = ViewModelProviders.of(this).get(BannerViewModel.class);
        sliderView = v.findViewById(R.id.imageSlider);

        Gson gson = new Gson();
        String json = sharedPreferences.getString("data", null);
        Type type = new TypeToken<ArrayList<BannerViewModel>>() {}.getType();
        saveList = gson.fromJson(json , type);

        bannerSlider();

//        if(saveList.isEmpty()){
//            bannerSlider();
//            Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
//        }else{
//
//            saveBannerSlider();
//
//        }

        fragmentTablayout();
        return v;
    }

    private void saveBannerSlider() {
        adapter = new BannerAdapter(saveList , getContext());

        sliderView.setSliderAdapter(adapter);

        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
        sliderView.startAutoCycle();
    }

    private void bannerSlider() {

        bannerViewModel.getMutableLiveData().observe((LifecycleOwner) getContext(), new Observer<ArrayList<BannerViewModel>>() {
            @Override
            public void onChanged(ArrayList<BannerViewModel> bannerViewModels) {

                SharedPreferences.Editor editor = sharedPreferences.edit();
                Gson gson = new Gson();
                String json = gson.toJson(bannerViewModels);
                editor.putString("data",json);
                editor.apply();

                adapter = new BannerAdapter(bannerViewModels , getContext());

                sliderView.setSliderAdapter(adapter);

                sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
                sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
                sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
                sliderView.setIndicatorSelectedColor(Color.WHITE);
                sliderView.setIndicatorUnselectedColor(Color.GRAY);
                sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
                sliderView.startAutoCycle();
            }
        });
    }

    private void fragmentTablayout() {
        tabLayout = v.findViewById(R.id.item_tablayout);
        viewPager = v.findViewById(R.id.tab_viewpager);

        tabAdapter = new TabAdapter(getFragmentManager());
        tabAdapter.addFragment(new Categories() , "Categories");
        tabAdapter.addFragment(new Brands() , "Brands");
        tabAdapter.addFragment(new Shops() , "Shops");

        viewPager.setAdapter(tabAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //tabLayout.addTab(tabLayout);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //Toast.makeText(context, "tab.getPosition()", Toast.LENGTH_SHORT).show();
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
        });
        tabLayout.setupWithViewPager(viewPager);
    }



    public void onSetNavigationDrawerEvents() {
        drawerLayout = (DrawerLayout) v.findViewById(R.id.drawerLayout);
        navigationView = (NavigationView) v.findViewById(R.id.navigationView);


        navigationBar = (ImageView) v.findViewById(R.id.navigationBar);
        home = v.findViewById(R.id.side_home);
        dashboard = v.findViewById(R.id.side_dashboard);
        order = v.findViewById(R.id.side_orders);
        message = v.findViewById(R.id.side_message);
        cart = v.findViewById(R.id.side_cart);
        wishlist = v.findViewById(R.id.side_wishlist);
        favourite_shop = v.findViewById(R.id.side_favourite_shop);
        vouchers = v.findViewById(R.id.side_voucher);
        invite = v.findViewById(R.id.side_invite_friends);
        contact = v.findViewById(R.id.side_contact);

        navigationBar.setOnClickListener(this);
        home.setOnClickListener(this);
        dashboard.setOnClickListener(this);
        order.setOnClickListener(this);
        message.setOnClickListener(this);
        cart.setOnClickListener(this);
        wishlist.setOnClickListener(this);
        favourite_shop.setOnClickListener(this);
        vouchers.setOnClickListener(this);
        invite.setOnClickListener(this);
        contact.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.navigationBar:
                drawerLayout.openDrawer(navigationView, true);
                break;
            case R.id.side_home:
                showToast("Home");
                drawerLayout.closeDrawer(navigationView, true);
                break;
            case R.id.side_dashboard:
                showToast("Dashboard");
                drawerLayout.closeDrawer(navigationView, true);
                break;
            case R.id.side_orders:
                showToast("Order");
                drawerLayout.closeDrawer(navigationView, true);
                break;
            case R.id.side_message:
                showToast("Message");
                drawerLayout.closeDrawer(navigationView, true);
                break;
            case R.id.side_cart:
                showToast("Cart");
                drawerLayout.closeDrawer(navigationView, true);
                break;
            case R.id.side_wishlist:
                showToast("Wishlist");
                drawerLayout.closeDrawer(navigationView, true);
                break;
            case R.id.side_favourite_shop:
                showToast("Favourite");
                drawerLayout.closeDrawer(navigationView, true);
                break;
            case R.id.side_voucher:
                showToast("Voucher");
                drawerLayout.closeDrawer(navigationView, true);
                break;
            case R.id.side_invite_friends:
                showToast("Invite and Earn");
                drawerLayout.closeDrawer(navigationView, true);
                break;
            case R.id.side_contact:
                showToast("Contact");
                drawerLayout.closeDrawer(navigationView, true);
                break;
            default:
                showToast("Default");
                drawerLayout.closeDrawer(navigationView, true);
                break;

        }
    }

    private void showToast(String message){
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }


}

