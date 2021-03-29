package com.roben.evaly;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;
import com.roben.evaly.Fragment.Cart;
import com.roben.evaly.Fragment.Dashboard;
import com.roben.evaly.Fragment.Express;
import com.roben.evaly.Fragment.Favourite;
import com.roben.evaly.Fragment.Home;

public class MainActivity extends AppCompatActivity {

    private SpaceNavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        navigationView = findViewById(R.id.bottom_navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmenmt_container , new Home()).commit();
        navigationView.initWithSaveInstanceState(savedInstanceState);

        initNavigationView();


    }
    private void initNavigationView() {

        navigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_home_black_24dp));
        navigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_favorite_border_black_24dp));
        navigationView.addSpaceItem(new SpaceItem("", R.drawable.cart));
        navigationView.addSpaceItem(new SpaceItem("", R.drawable.dashboard));

        navigationView.setSpaceOnClickListener(new SpaceOnClickListener() {

            @Override
            public void onCentreButtonClick() {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmenmt_container , new Express()).commit();
                navigationView.setCentreButtonSelectable(true);
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
                Fragment fragment = null;
                switch (itemIndex){
                    case 0:
                        fragment = new Home();
                        break;
                    case 1:
                        fragment = new Favourite();
                        break;
                    case 2:
                        fragment = new Cart();
                        break;
                    case 3:
                        fragment = new Dashboard();
                        break;
                    default:
                        return;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmenmt_container , fragment).commit();

            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {

            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        navigationView.onSaveInstanceState(outState);
    }
}
