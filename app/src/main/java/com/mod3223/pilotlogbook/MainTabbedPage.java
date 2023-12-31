package com.mod3223.pilotlogbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Adapter;

import com.google.android.material.tabs.TabLayout;

public class MainTabbedPage extends AppCompatActivity {

    public static String Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tabbed_page);
        Intent intent = getIntent();
        Login = intent.getStringExtra("Login");

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new LogbookFragment()).commit();
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                switch (position){
                    case 0:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new LogbookFragment()).commit();
                        break;
                    case 1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new RatingsFragment()).commit();
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}