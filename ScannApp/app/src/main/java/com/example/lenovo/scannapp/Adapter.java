package com.example.lenovo.scannapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.lenovo.scannapp.fragments.CreateFragment;
import com.example.lenovo.scannapp.fragments.FilesFragment;
import com.example.lenovo.scannapp.fragments.UpladFragment;


public class Adapter extends FragmentPagerAdapter {
    public static int int_items = 3;


    public Adapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {

        switch (i){
            case 0:
                return  new FilesFragment();

            case 1:
                return new CreateFragment();
            case 2:
                return new UpladFragment();




        }
        return null;
    }

    @Override
    public int getCount() {

        return int_items;
    }
    public CharSequence getPageTitle (int k){
        switch (k){

            case 0:
                return "Files";
            case 1:
                return "Create";
            case 2:
                return "Upload";

        }
        return null;


    }
}
