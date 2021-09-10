package com.example.lenovo.scannapp.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.example.lenovo.scannapp.Adapter;
import com.example.lenovo.scannapp.R;

public class pdfCeartor extends Fragment {
    private Adapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    Toolbar toolbar ;

    @SuppressLint("NewApi")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        getActivity().setTitle("    PDFCreator");

        /*
        tabLayout.addTab(tabLayout.newTab().setText("Files"));

        tabLayout.addTab(tabLayout.newTab().setText("Create"));
        tabLayout.addTab(tabLayout.newTab().setText("Upload"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setVerticalFadingEdgeEnabled(true);
        tabLayout.setHorizontalFadingEdgeEnabled(true);

        Adapter adpter = new Adapter(getFragmentManager() ,tabLayout.getTabCount());

        viewPager.setAdapter(adpter);


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }
            @Override
            public void onPageSelected(int i) {
                tabLayout.setScrollPosition(i , 0 , true);
                tabLayout.setSelected(true);
            }
            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



*/
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.pdfcreator ,null);

        tabLayout = (TabLayout)v.findViewById(R.id.tabs);
        viewPager = (ViewPager)v.findViewById(R.id.container);
        viewPager.setAdapter(new Adapter(getFragmentManager()));
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);

            }
        });


        return v;

    }


}
